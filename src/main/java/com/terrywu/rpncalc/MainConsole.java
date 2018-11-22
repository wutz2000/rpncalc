/*
 * MainConsole.java
 * Created on 11/22/18 10:24 AM
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


import java.io.Console;

public class MainConsole {
    public static void main(String[] args) {

        Console console = System.console();
        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }
        System.out.println("======================================================");
        System.out.println("| Reverse Polish Notation Calculator                 |");
        System.out.println("|               Created by Terry Wu                  |");
        System.out.println("| Supported operators: +, -, *, /, sqrt, clear, undo |");
        System.out.println("| Please place space between numbers and operators.  |");
        System.out.println("| Example input: 5 5 *                               |");
        System.out.println("| 'Ctrl+C' to quit                                   |");
        System.out.println("======================================================");

        RPNCalculator calculator = new RPNCalculator(20);
        ProcessResult result;

        boolean keepRunning = true;
        while (keepRunning) {
            String inputString = console.readLine(": ");

            result = calculator.processLine(inputString);
            if (result.result != ProcessResult.SUCCESS)
                System.out.println(result.detail.msg);

            System.out.println(calculator.toString());
        }
    }
}
