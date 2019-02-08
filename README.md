# 1. Introduction
The project is to build a compiler to test our overall knowledge of computer science to date.

### Team: 
- Joshua Stephenson-Losey
- Keefer Sands
- Sean Jungst
- Brendan Tracey

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
Compilers Progress Report
Joshua Stephenson-Losey, Keefer Sands, Sean Jungst, Brendan Tracey
Scanner



General Description of a Scanner

    A Scanner (or Lexer) takes a sequence of characters from an input and converts them into a value and a token. The value is simply the printed characters in the object, and the token is separated into different types that will determine its functionality later down the line. It is important to note that the scanner does not care about correct syntax and order. It’s only job is to properly convert everything passed to it is set to the right value and has the correct token type. The parser will handle all of that once everything has been broken down into tokens. The purpose of this is so that the variable names can be properly stored and so that the functionality of the code can be analyzed and consistent. The most important part of a scanner is how accurately it can interpret a set of characters and then output the correct token. This step is the most straightforward but not checking all possible cases and simple errors can cause major problems down the line. Performing this step incorrectly will yield a large amount of errors down the line as not accounting for corner cases and other arrangements.

2.    Scanner Implementation/Methods

We used java as our language of choice, seeing as everyone in the group has done most of our projects in Java. It is supported by ANTLR and has pretty clear pros and cons. For resources, we spent a lot of time using ‘The Definitive ANTLR 4 reference’ online for how to use the ANTLR engine to generate the proper lexer wew needed. First we started by defining all the possible characters as fragments so that it would be easier to define the tokens. For our main categories of fragments we chose, Digit, Lowercase, Uppercase, Letter and Char. We chose these as they cover everything all possible types while also being easy enough to reference for our token types. We used the provided tokens, Keyword, Identifier, Int literal, Float Literal, String Literal, Comment, Operator, Whitespace, Newline. Using the fragment definitions we defined all the possible combinations that would qualify for each token. In the end all the tokens had an airtight list of what input would produce what token. Our output list was generated properly and successfully converted everything into its corresponding token.

To handle the denotation specifications of letters vs characters, we defined all lower case letters in the alphabet and all uppercase letters in the alphabet as a letter. We defined all letters as uppercase and lowercase letters, and then all characters as both letters, and the set of all the weird symbols on the rest of the keyboard

3. Challenges and Obstacles

Getting ANTLR set up on our machines for coordination proved more challenging and time consuming then we anticipated, but once the Classpath and folder storage location had been found, it worked reasonably. Once these criteria had been met, we moved on to make the conversion algorithm. When we first began we found it extremely tedious to list every single individual character for each token possible input so we used fragments to cut down on repetitive typing for token definitions. This definitely helped get rid of redundancies in our code and significantly cut down on time. We split the fragments into Digit, Uppercase, Lowercase, Letter, and Char. By using the built in range functions we were able to reduce it to about 100 characters total. Using these fragments we were able to quickly reference large ranges of characters and repeatedly reference them from both other fragment definitions and the token definition. 

Error checking was hard because there isn’t as much documentation for ANTLR as there is for mainstream programming languages such as Java, and we didn’t have the intimate knowledge of what was going on under the hood. Because of this when we received errors it was hard to tell what the root of the problem was and what we could do to fix it. Most of our problem solving revolved around randomly trying different things and rearranging the order in which we defined everything. We experimented with different syntax and kind of seeing what worked and what didn’t. This gave us a lot of hands on experience with the setup and we feel confident that going forward that we will be able to work out the kinks in the program. Other than that once we had configured everything to get consistent outputs, it was pretty straightforward.

Going through test cases helped eliminate several errors that we did not see. In particular dealing with whitespace and comments was particularly challenging. Ultimately the solution we rested upon was to definite possible other token as a ‘not comment’ and define the comment token as -- followed by a ’not comment’. The test cases also caused us to expand our definition of whitespace, to include not only ‘ ‘ but also ‘\t’ ‘\r’ ‘\f’ to account for line breaks, indents and returns.

