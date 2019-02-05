#!/bin/bash
# the above line makes this an executable after you tell your computer it is..... idk ask the internet

# this gets the file from the user
file="$1"

# this compiles and runs the grammer and the driver
antlr scanner.g4
javac Driver.java
java Driver

# here is where we should give the file to java but im not certain how
#  TODO: look into calling a method from here and passing the argument that way...
expect "File: "
send -- "$file\r"

# this shows any difference in the outputs (same working directory)
diff -b -y --suppress-common-lines fibonacci.out out.txt