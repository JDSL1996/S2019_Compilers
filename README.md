# 1. Introduction
The project is to build a compiler to test our overall knowledge of computer science to date.

# 2. Background
A compiler is code that is used to change one language into another, in our case: change a high level language (Tiny) into machine language that a computer can run. The components are:
- Scanner - scans the file and brakes into characters to be handled by the parser
- Parser - organizes the characters into meaningful words and symbols understood by the end language
- Linker - connects the data from the parser to a Symbol table to ensure the validity of the characters and move to finding the end meaning
- Code Generation - takes the information from the linker and translates it into machine code for the end machine to read and run
# 3. Methods
## ANTLR Setup
Each member of the team set up ANTLR on their own machine following the instructions laid out on the ANTLR website. The exception being the Mac user of the group installed by calling 'brew install' as instructed by other classmates since the instructions for mac left out many details about permanent aliasing and class path.

With ANTLR installed each member ran the test code by creating a directory and a Hello.g4 file that ANTLR then read and converted into many files to be used by the java compiler and converted into a working program.
