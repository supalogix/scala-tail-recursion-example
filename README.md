Scala Tail Call Optimization Examples
-------------------------------------

This repository contains examples of how to use tail call optimization in
various situation using scala. It contains the following programs
 * FizzBuzz
 * DuplicationRemoving 
 * ConsecutiveNumbers

I have placed the programs in dedicated directories. Each directory has 
(a) a scala file, (b) an input file, and (c) and an output file. Each program
takes an input file as an argument and writes its results to standard
output. 

The output files represent the expected output for the program in their
directory. 

If you alter the input files then you must make an associated change to the
output file.

I have compiled and tested the programs using Scala compiler version 2.11.6

Example
-------

In the directory FizzBuzz, you will find the following files:
 * FizzBuzz.scala
 * FizzBuzz.in
 * FizzBuzz.out

Compile FizzBuzz.scala with the following command 

```bash
$> scalac FizzBuzz.scala
```

Run the application with the following command 

```bash
$> scala FizzBuzz FizzBuzz.in
```

Test the correctness of the program with the following command

```bash
$> scala FizzBuzz FizzBuzz.in | diff FizzBuzz.out - 
```

If the command above does not produce output then the expected input yields
the expected output. 

