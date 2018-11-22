/*
 * UndoBufferTest.java
 * Created on 11/22/18 1:39 PM
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UndoBufferTest {
    @Test
    public void testEmpty() throws Exception {
        UndoBuffer<Integer> undo = new UndoBuffer<>(5);

        assertEquals(true, undo.empty());
        assertEquals(false, undo.full());

        assertNull(undo.undo());

    }

    @Test
    public void testSimpleSnapshotUndo() throws Exception {
        UndoBuffer<Integer> undo = new UndoBuffer<>(5);

        undo.snapshot(0);
        undo.snapshot(1);
        undo.snapshot(2);
        assertEquals(1, (int)undo.undo());
        assertEquals(0, (int)undo.undo());

        undo.snapshot(3);
        assertNull(undo.undo());
    }

    @Test
    public void testSnapshotUndo2() throws Exception {
        UndoBuffer<Integer> undo = new UndoBuffer<>(5);

        for (int i = 0; i < 100; ++i)
            undo.snapshot(i);

        assertEquals(98, (int)undo.undo());
        assertEquals(97, (int)undo.undo());
        assertEquals(96, (int)undo.undo());
        assertNull(undo.undo());
    }
}
