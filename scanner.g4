grammar scanner;
/* Program */
program           : 'PROGRAM' id 'BEGIN' pgm_body 'END';
id                : IDENTIFIER;
pgm_body          : decl func_declarations;
decl		  : (string_decl decl | var_decl decl)?;

/* Global String Declaration */
string_decl       : 'STRING' id ':=' str ';' ;
str               : STRINGLITERAL;

/* Variable Declaration */
var_decl          : var_type id_list ';' ;
var_type	  : 'FLOAT' | 'INT';
any_type          : var_type | 'VOID' ;
id_list           : id id_tail;
id_tail           : (',' id id_tail)?;

/* Function Paramater List */
param_decl_list   : (param_decl param_decl_tail)?;
param_decl        : var_type id;
param_decl_tail   : (',' param_decl param_decl_tail)?;

/* Function Declarations */
func_declarations : (func_decl func_declarations)?;
func_decl         : 'FUNCTION' any_type id '('param_decl_list')' 'BEGIN' func_body 'END';
func_body         : decl stmt_list ;

/* Statement List */
stmt_list         : (stmt stmt_list)?;
stmt              : base_stmt | if_stmt | while_stmt;
base_stmt         : assign_stmt | read_stmt | write_stmt | return_stmt;

/* Basic Statements */
assign_stmt       : assign_expr ';' ;
assign_expr       : id ':=' expr;
read_stmt         : 'READ' '(' id_list ')' ';';
write_stmt        : 'WRITE' '(' id_list ')' ';';
return_stmt       : 'RETURN' expr ';';

/* Expressions */
expr              : factor (addop expr)?;
factor            : postfix_expr (mulop factor)?;
postfix_expr      : primary | call_expr;
call_expr         : id '(' expr_list ')';
expr_list         : (expr expr_list_tail)?;
expr_list_tail    : (',' expr expr_list_tail)?;
primary           : '(' expr ')' | id | INTLITERAL | FLOATLITERAL;
addop             : '+' | '-';
mulop             : '*' | '/';

/* Complex Statements and Condition */
if_stmt           : 'IF' '(' cond ')' decl stmt_list else_part 'ENDIF';
else_part         : 'ELSE' decl stmt_list | ;
cond              : expr compop expr;
compop            : '<' | '>' | '=' | '!=' | '<=' | '>=';

/* While statements */
while_stmt       : 'WHILE' '(' cond ')' decl stmt_list 'ENDWHILE';

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