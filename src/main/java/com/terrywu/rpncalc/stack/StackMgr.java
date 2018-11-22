/*
 * StackMgr.java
 * Created on 11/22/18 12:56 PM
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

import java.util.Iterator;
import java.util.Stack;

/**
 * StackMgr
 * @param <E>
 */
public class StackMgr<E> implements RPNCalcOperandStack<E> {
    private Stack<E> stack;
    private UndoBuffer<Stack<E>> undoBuffer;
    private boolean stackModified;

    public StackMgr(int maxUndo) {
        stack = new Stack<>();
        undoBuffer = new UndoBuffer<>(maxUndo);
        undoBuffer.snapshot(new Stack<E>());

        stackModified = false;
    }

    public StackMgr() {
        this(5);
    }

    public Object[] toArray() {
        return stack.toArray();
    }

    public void setStackModified(boolean bModified) {
        stackModified = bModified;
    }

    @Override
    public E push(E item) {
        E data = stack.push(item);
        setStackModified(true);
        return data;
    }

    @Override
    public E pop() {
        E data = stack.pop();
        setStackModified(true);
        return data;
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public boolean empty() {
        return stack.empty();
    }

    @Override
    public void clear() {
        int oldSize = size();
        stack.clear();

        if (oldSize > 0)
            setStackModified(true);
    }

    @Override
    public boolean undo(int steps) {
        Stack<E> restore = null;

        for (int i = 0; i < steps; ++i) {
            Stack<E> tmp = undoBuffer.undo();

            if (tmp != null)
                restore = tmp;
        }

        if (restore != null) {
            stack = restore;
            return true;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void snapshot() {
        if (stackModified) {
            undoBuffer.snapshot((Stack<E>) stack.clone());

            setStackModified(false);
        }
    }
}
