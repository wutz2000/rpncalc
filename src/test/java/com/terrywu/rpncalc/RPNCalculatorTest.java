/*
 * RPNCalculatorTest.java
 * Created on 11/22/18 5:36 PM
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

import org.junit.Test;

import static org.junit.Assert.*;

public class RPNCalculatorTest {

    @Test
    public void processLine() {
        RPNCalculator calc = new RPNCalculator();

        ProcessResult result = calc.processLine("5");
        assertEquals(ProcessResult.SUCCESS, result.result);
        assertEquals(0, result.detail.position);
        assertEquals("5", result.detail.token);
        assertEquals(5.0, (double)result.detail.value, 1e-6);

        result = calc.processLine("4 +");
        assertEquals(ProcessResult.SUCCESS, result.result);
        assertEquals(2, result.detail.position);
        assertEquals("+", result.detail.token);
        assertEquals(9.0, (double)result.detail.value, 1e-6);
    }

    @Test
    public void processLineAdd() {
        RPNCalculator calc = new RPNCalculator();

        ProcessResult result = calc.processLine("5 4 +");
        assertEquals(ProcessResult.SUCCESS, result.result);
        assertEquals(4, result.detail.position);
        assertEquals("+", result.detail.token);
        assertEquals(9.0, (double)result.detail.value, 1e-6);
    }

    @Test
    public void processLineAddInsufficientParameters() {
        RPNCalculator calc = new RPNCalculator();

        ProcessResult result = calc.processLine("5 +");
        assertEquals(ProcessResult.InsufficientParameters, result.result);
        assertEquals(2, result.detail.position);
        assertEquals("+", result.detail.token);
        assertEquals("operator + (position: 2): insufficient parameters", result.detail.msg);
    }

    @Test
    public void processLineAddMoreWhitespaces() {
        RPNCalculator calc = new RPNCalculator();

        ProcessResult result = calc.processLine(" 5  4  +");
        assertEquals(ProcessResult.SUCCESS, result.result);
        assertEquals(7, result.detail.position);
        assertEquals("+", result.detail.token);
        assertEquals(9.0, (double)result.detail.value, 1e-6);
    }
    @Test
    public void processLineSub() {
        RPNCalculator calc = new RPNCalculator();

        ProcessResult result = calc.processLine("5 4 -");
        assertEquals(ProcessResult.SUCCESS, result.result);
        assertEquals(4, result.detail.position);
        assertEquals("-", result.detail.token);
        assertEquals(1.0, (double)result.detail.value, 1e-6);
    }

    @Test
    public void processLineMul() {
        RPNCalculator calc = new RPNCalculator();

        ProcessResult result = calc.processLine("5 4 *");
        assertEquals(ProcessResult.SUCCESS, result.result);
        assertEquals(4, result.detail.position);
        assertEquals("*", result.detail.token);
        assertEquals(20.0, (double)result.detail.value, 1e-6);
    }

    @Test
    public void processLineDiv() {
        RPNCalculator calc = new RPNCalculator();

        ProcessResult result = calc.processLine("5 4 /");
        assertEquals(ProcessResult.SUCCESS, result.result);
        assertEquals(4, result.detail.position);
        assertEquals("/", result.detail.token);
        assertEquals(1.25, (double)result.detail.value, 1e-6);
    }

    @Test
    public void processLineClear() {
        RPNCalculator calc = new RPNCalculator();

        ProcessResult result = calc.processLine("5 4 clear 3 6 +  ");
        assertEquals(ProcessResult.SUCCESS, result.result);
        assertEquals(14, result.detail.position);
        assertEquals("+", result.detail.token);
        assertEquals(9.0, (double)result.detail.value, 1e-6);
    }

    @Test
    public void processLineUndo() {
        RPNCalculator calc = new RPNCalculator();

        ProcessResult result = calc.processLine("5 4 3 2 undo undo *");
        assertEquals(ProcessResult.SUCCESS, result.result);
        assertEquals(18, result.detail.position);
        assertEquals("*", result.detail.token);
        assertEquals(20.0, (double)result.detail.value, 1e-6);
    }
}