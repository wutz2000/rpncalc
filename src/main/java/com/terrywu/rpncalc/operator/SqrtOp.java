/*
 * SqrtOp.java
 * Created on 11/22/18 12:04 PM
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

package com.terrywu.rpncalc.operator;

public class SqrtOp extends AbstractOperator {

    public SqrtOp()
    {
        super("sqrt", "Sqrt", OperatorExecType.oetMathOpOne, "Sqrt(x) = sqrt(x)");
    }

    @Override
    public double exec(double... operands) {
        return Math.sqrt(operands[0]);
    }
}
