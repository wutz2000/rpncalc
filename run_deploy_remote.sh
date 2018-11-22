#!/bin/bash


if [ -z $1 ]; then
    echo "$0 remote-target"
    echo "Error, remote target is empty"
    exit -1
fi

mvn clean compile test package

scp target/rpncalc-1.0.0-SNAPSHOT.jar $1
