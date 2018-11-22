/*
 * AddOpTest.java
 * Created on 11/22/18 2:57 PM
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

import org.junit.Test;

import static org.junit.Assert.*;

public class AddOpTest {
    @Test
    public void testAttr() {
        AddOp op = new AddOp();

        assertEquals("+", op.getToken());
        assertEquals("Add", op.getName());
        assertEquals(OperatorExecType.oetMathOpTwo, op.getExecType());
    }

    @Test
    public void testExec() {
        AddOp op = new AddOp();

        assertEquals(10, op.exec(2, 8), 1e-6);
    }
}