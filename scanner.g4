// we only need the lexer for now
lexer grammar scanner;

// given
KEYWORD : ('PROGRAM'|'BEGIN'|'END'|'FUNCTION'|'READ'
        |'WRITE'|'IF'|'ELSE'|'FI'|'FOR'|'ROF'|'RETURN'
        |'INT'|'VOID'|'STRING'|'FLOAT'|'WHILE'|'ENDIF'
        |'ENDWHILE');

// any letter followed by zero or more letters or numbers
IDENTIFIER: LETTER + (LETTER|DIGIT)*;

// any combination of one or more digits
INTLITERAL: DIGIT +;

// any combination of zero or more digits with a decimal and more digits
FLOATLITERAL: DIGIT* '.' DIGIT+;

// any item(s) between quotes
STRINGLITERAL: ('"') (COMMENT|NOTCOMMENT)+ ('"');

// every item after -- on a single line
COMMENT: ('--')(NOTCOMMENT)* -> skip;

// given
OPERATOR : (':='|'+'|('-' ~('-'|'0'..'9')?)|'*'|'/'|'='|'!='|'<'|'>'|'('
        |')'|';'|','|'<='|'>=');

// ignore space
WHITESPACE : (' ' | '\t' | '\r' | '\f')+ -> skip; 

// and new lines
NEWLINE : ('\n')+ -> skip;

// fragment -> basically variable

// everything thats not a comment (used to make comment work correctly)
fragment NOTCOMMENT : (CHAR|DIGIT|OPERATOR|KEYWORD|IDENTIFIER|INTLITERAL|FLOATLITERAL|STRINGLITERAL|WHITESPACE);
fragment DIGIT : [0-9];
fragment LOWERCASE  : [a-z] ;
fragment UPPERCASE  : [A-Z] ;
fragment LETTER : LOWERCASE|UPPERCASE;
fragment CHAR : ('~'|'!'|'@'|'#'|'$'|'%'|'^'|'&'|'*'|'('|')'|'_'
        |'-'|'+'|'='|'{'|'['|']'|'}'|'|'|':'|';'|'<'|'>'|','|'.'
        |'/'|'?'|'\\'|'\''| LETTER);