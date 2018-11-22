/*
 * StackMgrTest.java
 * Created on 11/22/18 2:44 PM
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

package com.terrywu.rpncalc.stack;

import org.junit.Test;

import static org.junit.Assert.*;

public class StackMgrTest {

    @Test
    public void pushpop() {
        StackMgr<Integer> sm = new StackMgr<>();

        sm.push(1);
        sm.push(2);
        sm.push(3);

        assertEquals(3, (int)sm.pop());
        assertEquals(2, (int)sm.pop());
        assertEquals(1, (int)sm.pop());
    }

    @Test
    public void snapshot() {
        StackMgr<Integer> sm = new StackMgr<>();

        sm.push(1);
        sm.snapshot();

        sm.push(2);
        sm.push(3);
        sm.snapshot();

        sm.push(4);
        sm.snapshot();

        sm.undo(1);

        assertEquals(3, (int)sm.pop());
        assertEquals(2, (int)sm.pop());
        assertEquals(1, (int)sm.pop());

        sm.undo(1);
        assertEquals(1, (int)sm.pop());
    }

    @Test
    public void snapshotUndo() {
        StackMgr<Integer> sm = new StackMgr<>(10);

        sm.push(1);
        sm.snapshot();

        boolean rs = sm.undo(1);
        assertEquals(true, rs);
        assertEquals(true, sm.empty());
    }

    @Test
    public void snapshotUndo2() {
        StackMgr<Integer> sm = new StackMgr<>(10);

        int data[] = new int[] { 5, 4, 3, 2};
        for (int i : data) {
            sm.push(i);
            sm.snapshot();
        }

        boolean rs = sm.undo(1);
        assertEquals(true, rs);

        assertEquals(3, (int)sm.pop());
        assertEquals(4, (int)sm.pop());
        assertEquals(5, (int)sm.pop());
        assertEquals(true, sm.empty());

    }

    @Test
    public void snapshotUndo3() {
        StackMgr<Integer> sm = new StackMgr<>(10);

        int data[] = new int[] { 5, 4, 3, 2};
        for (int i : data) {
            sm.push(i);
            sm.snapshot();
        }

        boolean rs = sm.undo(1);
        assertEquals(true, rs);

        rs = sm.undo(1);
        assertEquals(true, rs);

        assertEquals(4, (int)sm.pop());
        assertEquals(5, (int)sm.pop());
        assertEquals(true, sm.empty());

    }
}