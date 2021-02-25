/*
 * UndoBuffer.java
 * Created on 11/22/18 1:37 PM
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

/**
 * UndoBuffer
 * UndoBuffer can record the latest (bufferSize - 1) elements;
 * It supports snapshot and undo methods.
 * Actually it is easy to support redo method.
 * @param <E>
 */
public class UndoBuffer<E> {
    protected Object[] buffer;
    protected int bufferSize;
    protected int tail;

    /**
     * head is the position to store snapshot
     */
    protected int head;

    /**
     * next index of current undo object
     */
    protected int current;

    public UndoBuffer(int size)
    {
        if (size <= 0) {
            throw new IllegalArgumentException("Illegal size: " + size);
        }

        this.buffer = new Object[size];
        this.bufferSize = size;

        this.tail = 0;
        this.head = 0;
        this.current = 0;
    }

    public boolean empty() {
        return tail == head;
    }

    public boolean full() {
        int next = (head + 1) % bufferSize;
        return next == tail;
    }

    public void clear() {
        for (int i = 0; i < bufferSize; ++i) {
            buffer[i] = null;
        }
        this.tail = 0;
        this.head = 0;
    }

    public void snapshot(E e) {
        if (current != head) {
            // Did undo before, so adjust head
            head = current;
        }
        if (full()) {
            // 丢掉最老的数据
            tail = (tail + 1) % bufferSize;
        }

        buffer[head++] = e;
        head %= bufferSize;
        current = head;
    }

    @SuppressWarnings("unchecked")
    public E undo() {
        if (current == tail) {
            return null;
        }
        int old = (current - 1 + bufferSize) % bufferSize;

        // If it the first undo sKip the latest snapshot
        if (current == head) {
            if (old == tail) {
                return null;
            }

            old = (old - 1 + bufferSize) % bufferSize;
        }

        current = old;
        Object result = buffer[current];

        return (E)result;
    }
}
