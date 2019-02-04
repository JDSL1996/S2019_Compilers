grammar scanner;

fragment DIGIT : [0-9];
fragment LOWERCASE  : [a-z] ;
fragment UPPERCASE  : [A-Z] ;
fragment LETTER : LOWERCASE|UPPERCASE;
fragment CHAR : ('~'|'!'|'@'|'#'|'$'|'%'|'^'|'&'|'*'|'('|')'|'_'
        |'-'|'+'|'='|'{'|'['|']'|'}'|'|'|':'|';'|'<'|'>'|','|'.'
        |'/'|'?'|'\\'|'\''| LETTER);
fragment KEYWORDS : ('PROGRAM'|'BEGIN'|'END'|'FUNCTION'|'READ'
        |'WRITE'|'IF'|'ELSE'|'FI'|'FOR'|'ROF'|'RETURN'|'INT'|'VOID'|'STRING'|'FLOAT'|'WHILE'|'ENDIF'|'ENDWHILE');
fragment KEYWORDS : (':='|'+'|'-'|'*'|'/'|'='|'!='|'<'|'>'|'('
        |')'|';'|','|'<='|'>=');

IDENTIFIERS: LETTER + (LETTER*|DIGIT*)*;

INTLITERAL: DIGIT +;

FLOATLITERAL: DIGIT * ('.'DIGIT+)?;

STRINGLITERAL: ('"')CHAR+('"');

COMMENT: ('--'.+);

WHITESPACE : [ \t\r\n]+ -> skip;

TOKEN: IDENTIFIERS | COMMENT;

print: 