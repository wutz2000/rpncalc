/*
 * ProcessResult.java
 * Created on 11/22/18 4:40 PM
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

public class ProcessResult {
    public static final int SUCCESS = 0;
    public static final int INTERNL_ERROR = -1;
    public static final int INVALID_PARAMETERS = -2;
    public static final int INSUFFICIENT_PARAMETERS = -10;

    public int result;
    public DetailMsg detail;

    public class DetailMsg {
        public int position;
        public String token;
        public Object value;
        public String msg;

        public DetailMsg(int position, String token, Object value, String msg) {
            this.position = position;
            this.token = token;
            this.value = value;
            this.msg = msg;
        }
        public DetailMsg() {
            this(-1, null, null, null);
        }
    }

    public ProcessResult(int result, int position, String token, Object value, String msg){
        this.result = result;
        this.detail = new DetailMsg(position, token, value, msg);
    }

    public ProcessResult(int result, int position, String token){
        this(0, position, token, null, null);
    }
    public ProcessResult(){
        this(0, -1, null);
    }
}
