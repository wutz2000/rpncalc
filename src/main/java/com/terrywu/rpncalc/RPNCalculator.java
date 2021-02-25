/*
 * RPNCalculator.java
 * Created on 11/22/18 10:25 AM
 * Author: terry
 *
 * Copyright (c) 2018 Terry Wu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.terrywu.rpncalc;

import com.terrywu.rpncalc.operator.*;
import com.terrywu.rpncalc.stack.StackMgr;

import java.text.DecimalFormat;
import java.util.*;

import static com.terrywu.rpncalc.operator.OperatorExecType.oetControl;
import static com.terrywu.rpncalc.operator.OperatorExecType.oetMathOpOne;
import static com.terrywu.rpncalc.operator.OperatorExecType.oetMathOpTwo;

public class RPNCalculator {
    private StackMgr<Double> stackMgr;

    private Map<String, Operator> operatorMap;

    public RPNCalculator(int maxUndo) {
        stackMgr = new StackMgr<>(maxUndo);

        initOperatorMap();
    }

    public RPNCalculator() {
        this(5);
    }

    protected void initOperatorMap() {
        operatorMap = new HashMap<String, Operator>(10);

        Operator[] ops = new Operator[] {
                new AddOp(),
                new SubOp(),
                new MulOp(),
                new DivOp(),
                new SqrtOp(),
                new ClearControlOperator(stackMgr),
                new UndoControlOperator(stackMgr),
        };

        for (Operator op : ops) {
            operatorMap.put(op.getToken(), op);
        }
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        DecimalFormat fmt = new DecimalFormat("0.##########");
        for (Object obj : stackMgr.toArray()) {
            double d = (double) obj;
            buffer.append(String.format(" %s", fmt.format(d)));
        }
        return "stack:" + buffer.toString();
    }

    protected ProcessResult processTokenTryBlock(int position, String token) {
        ProcessResult processResult = null;

        try {
            processResult = processToken (position, token);
        }
        catch(Exception e){
            processResult = new ProcessResult(
                    ProcessResult.INTERNL_ERROR,
                    position,
                    token,
                    null,
                    e.toString());
        }

        // Snapshot if not undo and stack modified
        if (UndoControlOperator.TOKEN.compareTo(token) != 0) {
            stackMgr.snapshot();
        }

        return processResult;
    }


        private ProcessResult processToken(int position, String token) {
        ProcessResult processResult = new ProcessResult(
                ProcessResult.SUCCESS,
                position,
                token);
        if (token == null || token.isEmpty()) {
            return processResult;
        }

        if (operatorMap.containsKey(token)) {
            // Operator
            Operator op = operatorMap.get(token);
            OperatorExecType execType = op.getExecType();

            // Check
            if ((execType == oetMathOpTwo && stackMgr.size() < 2)
                || (execType == oetMathOpOne && stackMgr.size() < 1)) {
                processResult.result = ProcessResult.INSUFFICIENT_PARAMETERS;
                processResult.detail.position = position;
                processResult.detail.msg = String.format("operator %s (position: %d): insufficient parameters",
                        token,
                        position);

                return processResult;
            }

            double value = 0;
            if (execType == oetMathOpTwo) {
                double d1 = stackMgr.pop();
                double d2 = stackMgr.pop();

                value = op.exec(d2, d1);
                stackMgr.push(value);
            }
            else if (execType == oetMathOpOne) {
                value = op.exec(stackMgr.pop());
                stackMgr.push(value);
            }
            else if (execType == oetControl) {
                value = op.exec();
            }
            else {
                throw new UnsupportedOperationException("Unsupported execType: " + execType + " token: " + token);
            }

            processResult.detail.value = value;
        }
        else {
            // operands
            double value = Double.parseDouble(token);
            stackMgr.push(value);

            processResult.detail.value = value;
        }

        return processResult;
    }

    public ProcessResult processLine(String line) {
        ProcessResult processResult = new ProcessResult();

        if (line == null) {
            return processResult;
        }

        char[] c = line.toCharArray();
        int length = c.length;

        StringBuffer token = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            if (c[i] == ' ') {
                if (token.length() > 0) {
                    processResult = processTokenTryBlock(i - token.length(), token.toString());
                    if (processResult.result != ProcessResult.SUCCESS ) {
                        return processResult;
                    }
                }

                token.setLength(0);
            } else
            {
                // Not whitespace
                token.append(c[i]);
            }
        }

        if (token.length() > 0) {
            processResult = processTokenTryBlock(length - token.length(), token.toString());
        }

        return processResult;
    }


}
