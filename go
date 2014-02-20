#!/bin/bash

## Removing every previous .class file
## and documentation, if any ...
rm -rf bin doc

## Preparing a bin directory and a doc directory ...
mkdir bin doc

## Updating CLASSPATH ...
export CLASSPATH=$CLASSPATH:$(pwd)/bin:$(pwd)/src

## Compiling, hence trying to open doc and running ...
if javac -d bin src/Main.java && javadoc -d doc src/*java; then
    #(x-www-browser doc/index-all.html &) &> /dev/null
    clear; java Main
fi

## Removing every class in bin, but leaving that dir ...
rm -f bin/*
