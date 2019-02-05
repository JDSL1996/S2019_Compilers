lexer grammar scanner;

fragment DIGIT : [0-9];
fragment LOWERCASE  : [a-z] ;
fragment UPPERCASE  : [A-Z] ;
fragment LETTER : LOWERCASE|UPPERCASE;
fragment CHAR : ('~'|'!'|'@'|'#'|'$'|'%'|'^'|'&'|'*'|'('|')'|'_'
        |'-'|'+'|'='|'{'|'['|']'|'}'|'|'|':'|';'|'<'|'>'|','|'.'
        |'/'|'?'|'\\'|'\''| LETTER);

COMMENT: ('--') (CHAR|DIGIT|OPERATORS)* ('\n')+;

STRINGLITERAL: ('"') CHAR+ ('"');

KEYWORDS : ('PROGRAM'|'BEGIN'|'END'|'FUNCTION'|'READ'
        |'WRITE'|'IF'|'ELSE'|'FI'|'FOR'|'ROF'|'RETURN'
        |'INT'|'VOID'|'STRING'|'FLOAT'|'WHILE'|'ENDIF'
        |'ENDWHILE');

OPERATORS : (':='|'+'|'-'|'*'|'/'|'='|'!='|'<'|'>'|'('
        |')'|';'|','|'<='|'>=');

IDENTIFIERS: LETTER + (LETTER|DIGIT)*;

INTLITERAL: DIGIT +;

FLOATLITERAL: DIGIT '.' DIGIT+;

WHITESPACE : (' ' | '\t' | '\r' | '\f')+ -> skip; 

NEWLINE : ('\n')+ -> skip;
