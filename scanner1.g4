lexer grammar scanner1;
// given
KEYWORD: 'PROGRAM'|'BEGIN'|'END'|'FUNCTION'|'READ'
        |'WRITE'|'IF'|'ELSE'|'FI'|'FOR'|'ROF'|'RETURN'
        |'INT'|'VOID'|'STRING'|'FLOAT'|'WHILE'|'ENDIF'
        |'ENDWHILE';

// any letter followed by zero or more letters or numbers
IDENTIFIER: LETTER + (LETTER|DIGIT)*;

// any combination of one or more digits
INTLITERAL: DIGIT +;

// any combination of zero or more digits with a decimal and more digits
FLOATLITERAL: DIGIT* '.' DIGIT+;

// any item(s) between quotes
STRINGLITERAL: ('"') (CHAR|DIGIT|LETTER|WHITESPACE)+ ('"');

// every item after -- on a single line
COMMENT: ('--')(CHAR|DIGIT|LETTER|WHITESPACE|OPERATOR)* -> skip;

// ignore space
WHITESPACE : [ \t\r]+ -> skip; 

// given  ~('-'|'0'..'9')?)
OPERATOR : (':='|'+'|'-'|'*'|'/'|'='|'!='|'<'|'>'|'('
        |')'|';'|','|'<='|'>=');

// and new lines
NEWLINE : [\n]+ -> skip;

// fragment -> basically variable

// everything thats not a comment (used to make comment work correctly)
fragment DIGIT : [0-9];
fragment LOWERCASE  : [a-z] ;
fragment UPPERCASE  : [A-Z] ;
fragment LETTER : LOWERCASE|UPPERCASE;
fragment CHAR : ('~'|'!'|'@'|'#'|'$'|'%'|'^'|'&'|'*'|'('|')'|'_'
        |'-'|'+'|'='|'{'|'['|']'|'}'|'|'|':'|';'|'<'|'>'|','|'.'
        |'/'|'?'|'\\'|'\''| LETTER);