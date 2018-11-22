# Reverse Polish Notation Calulator

RPN calculator (Command-line)

## Introduction

The reverse polish notation calculator has a **stack** that can contain **real numbers**.

## Requirements

- The calculator waits for user input and expects to receive strings containing whitespace separated lists of numbers and operators.

- Numbers are pushed on to the stack. Operators operate on numbers that are on the stack. 

- Operators pop their parameters off the stack, and push their results back onto the stack.

- Available operators are `+`, `-`, `*`, `/`, `sqrt`, `undo`, `clear`.
    * The `clear` operator removes all items from the stack.
    * The `undo` operator undoes the previous operation. `undo undo` will undo the previous two operations. 
    * sqrt performs a square root on the top item from the stack.
    * The `+`, `-`, `*`, `/` operators perform addition, subtraction, multiplication and division respectively on the top two items from the stack.

- After processing an input string, the calculator displays the current contents of the stack as a space-separated list.

- Numbers should be stored on the stack to at least 15 decimal places of precision, but displayed to 10 decimal places (or less if it causes no loss of precision).

- All numbers should be formatted as plain decimal strings (ie. no engineering formatting).

- If an operator cannot find a sufficient number of parameters on the stack, a warning is displayed:
    `operator <operator> (position: <pos>): insufficient parameters`

    After displaying the warning, all further processing of the string terminates and the current state of the stack is displayed.


## Compile, Test, Run and Packaging

- Compile: `mvn compile`

- Test: `mvn test`

- Run: `mvn exec:java`

- Packaging: `mvn package`, compiled jar in *target/* folder

## Script Files

- run_build.sh: clean, compile and run unit testing

- run_deploy_local: clean, compile, run unit testing and deploy the jar file to a local folder

- run_deploy_remote: clean, compile, run unit testing and deploy the jar file to a remote target

## TODO

- Use docker to build and deploy automatically to support CI and CD

- Use annotation to simplify the system.
  Here is an example:
  ```
   @Operator(token="+", name="Add", execType=OperatorExecType.oetMathOpOne, description="Add(x,y) = x + y")
   public double add(double x, double y) {
    return x + y;
   }
   
  ```

## Author
Terry Wu
E-mail: [wutz2000@163.com](mailto:wutz2000@163.com)
