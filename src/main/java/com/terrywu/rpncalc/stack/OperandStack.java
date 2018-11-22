/*
 * OperandStack.java
 * Created on 11/22/18 3:16 PM
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

public interface OperandStack<E> {
    /**
     * Pushes an item onto the top of a stack
     * @param item
     * @return
     */
    public E push(E item);

    /**
     * Removes the object at the top of a stack and returns that
     *      * object as the value of this function.
     * @return
     */
    public E pop();

    /**
     *  Returns the number of elements in the stack
     * @return
     */
    public int size();

    /**
     *  Tests if this stack is empty.
     * @return
     */
    public boolean empty();

    /**
     * Removes all of the elements from a stack.
     */
    public void clear();
}
