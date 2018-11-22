/*
 * AbstractOperator.java
 * Created on 11/22/18 11:50 AM
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

public abstract class AbstractOperator implements Operator {
    private String name;
    private String token;
    private String description;
    private OperatorExecType execType;

    public AbstractOperator(String token, String name, OperatorExecType execType, String description)
    {
        this.name = name;
        this.token = token;
        this.description = description;
        this.execType = execType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public OperatorExecType getExecType() {
        return execType;
    }
}
