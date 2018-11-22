#!/bin/bash

if [ -z $1 ]; then
    echo "$0 target-folder"
    echo "Error, target is empty"
    exit -1
fi

mvn clean compile test package

cp target/rpncalc-1.0.0-SNAPSHOT.jar $1
