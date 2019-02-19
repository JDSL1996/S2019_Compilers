// Generated from scanner.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link scannerParser}.
 */
public interface scannerListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link scannerParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(scannerParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(scannerParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(scannerParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(scannerParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#pgm_body}.
	 * @param ctx the parse tree
	 */
	void enterPgm_body(scannerParser.Pgm_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#pgm_body}.
	 * @param ctx the parse tree
	 */
	void exitPgm_body(scannerParser.Pgm_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(scannerParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(scannerParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#string_decl}.
	 * @param ctx the parse tree
	 */
	void enterString_decl(scannerParser.String_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#string_decl}.
	 * @param ctx the parse tree
	 */
	void exitString_decl(scannerParser.String_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#str}.
	 * @param ctx the parse tree
	 */
	void enterStr(scannerParser.StrContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#str}.
	 * @param ctx the parse tree
	 */
	void exitStr(scannerParser.StrContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#var_decl}.
	 * @param ctx the parse tree
	 */
	void enterVar_decl(scannerParser.Var_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#var_decl}.
	 * @param ctx the parse tree
	 */
	void exitVar_decl(scannerParser.Var_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#var_type}.
	 * @param ctx the parse tree
	 */
	void enterVar_type(scannerParser.Var_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#var_type}.
	 * @param ctx the parse tree
	 */
	void exitVar_type(scannerParser.Var_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#any_type}.
	 * @param ctx the parse tree
	 */
	void enterAny_type(scannerParser.Any_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#any_type}.
	 * @param ctx the parse tree
	 */
	void exitAny_type(scannerParser.Any_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#id_list}.
	 * @param ctx the parse tree
	 */
	void enterId_list(scannerParser.Id_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#id_list}.
	 * @param ctx the parse tree
	 */
	void exitId_list(scannerParser.Id_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#id_tail}.
	 * @param ctx the parse tree
	 */
	void enterId_tail(scannerParser.Id_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#id_tail}.
	 * @param ctx the parse tree
	 */
	void exitId_tail(scannerParser.Id_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#param_decl_list}.
	 * @param ctx the parse tree
	 */
	void enterParam_decl_list(scannerParser.Param_decl_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#param_decl_list}.
	 * @param ctx the parse tree
	 */
	void exitParam_decl_list(scannerParser.Param_decl_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#param_decl}.
	 * @param ctx the parse tree
	 */
	void enterParam_decl(scannerParser.Param_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#param_decl}.
	 * @param ctx the parse tree
	 */
	void exitParam_decl(scannerParser.Param_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#param_decl_tail}.
	 * @param ctx the parse tree
	 */
	void enterParam_decl_tail(scannerParser.Param_decl_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#param_decl_tail}.
	 * @param ctx the parse tree
	 */
	void exitParam_decl_tail(scannerParser.Param_decl_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#func_declarations}.
	 * @param ctx the parse tree
	 */
	void enterFunc_declarations(scannerParser.Func_declarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#func_declarations}.
	 * @param ctx the parse tree
	 */
	void exitFunc_declarations(scannerParser.Func_declarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#func_decl}.
	 * @param ctx the parse tree
	 */
	void enterFunc_decl(scannerParser.Func_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#func_decl}.
	 * @param ctx the parse tree
	 */
	void exitFunc_decl(scannerParser.Func_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#func_body}.
	 * @param ctx the parse tree
	 */
	void enterFunc_body(scannerParser.Func_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#func_body}.
	 * @param ctx the parse tree
	 */
	void exitFunc_body(scannerParser.Func_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#stmt_list}.
	 * @param ctx the parse tree
	 */
	void enterStmt_list(scannerParser.Stmt_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#stmt_list}.
	 * @param ctx the parse tree
	 */
	void exitStmt_list(scannerParser.Stmt_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(scannerParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(scannerParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#base_stmt}.
	 * @param ctx the parse tree
	 */
	void enterBase_stmt(scannerParser.Base_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#base_stmt}.
	 * @param ctx the parse tree
	 */
	void exitBase_stmt(scannerParser.Base_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void enterAssign_stmt(scannerParser.Assign_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void exitAssign_stmt(scannerParser.Assign_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#assign_expr}.
	 * @param ctx the parse tree
	 */
	void enterAssign_expr(scannerParser.Assign_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#assign_expr}.
	 * @param ctx the parse tree
	 */
	void exitAssign_expr(scannerParser.Assign_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#read_stmt}.
	 * @param ctx the parse tree
	 */
	void enterRead_stmt(scannerParser.Read_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#read_stmt}.
	 * @param ctx the parse tree
	 */
	void exitRead_stmt(scannerParser.Read_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#write_stmt}.
	 * @param ctx the parse tree
	 */
	void enterWrite_stmt(scannerParser.Write_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#write_stmt}.
	 * @param ctx the parse tree
	 */
	void exitWrite_stmt(scannerParser.Write_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void enterReturn_stmt(scannerParser.Return_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void exitReturn_stmt(scannerParser.Return_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(scannerParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(scannerParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(scannerParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(scannerParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#postfix_expr}.
	 * @param ctx the parse tree
	 */
	void enterPostfix_expr(scannerParser.Postfix_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#postfix_expr}.
	 * @param ctx the parse tree
	 */
	void exitPostfix_expr(scannerParser.Postfix_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#call_expr}.
	 * @param ctx the parse tree
	 */
	void enterCall_expr(scannerParser.Call_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#call_expr}.
	 * @param ctx the parse tree
	 */
	void exitCall_expr(scannerParser.Call_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list(scannerParser.Expr_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list(scannerParser.Expr_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#expr_list_tail}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list_tail(scannerParser.Expr_list_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#expr_list_tail}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list_tail(scannerParser.Expr_list_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(scannerParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(scannerParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#addop}.
	 * @param ctx the parse tree
	 */
	void enterAddop(scannerParser.AddopContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#addop}.
	 * @param ctx the parse tree
	 */
	void exitAddop(scannerParser.AddopContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#mulop}.
	 * @param ctx the parse tree
	 */
	void enterMulop(scannerParser.MulopContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#mulop}.
	 * @param ctx the parse tree
	 */
	void exitMulop(scannerParser.MulopContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void enterIf_stmt(scannerParser.If_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void exitIf_stmt(scannerParser.If_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#else_part}.
	 * @param ctx the parse tree
	 */
	void enterElse_part(scannerParser.Else_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#else_part}.
	 * @param ctx the parse tree
	 */
	void exitElse_part(scannerParser.Else_partContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond(scannerParser.CondContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond(scannerParser.CondContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#compop}.
	 * @param ctx the parse tree
	 */
	void enterCompop(scannerParser.CompopContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#compop}.
	 * @param ctx the parse tree
	 */
	void exitCompop(scannerParser.CompopContext ctx);
	/**
	 * Enter a parse tree produced by {@link scannerParser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void enterWhile_stmt(scannerParser.While_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link scannerParser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void exitWhile_stmt(scannerParser.While_stmtContext ctx);
}