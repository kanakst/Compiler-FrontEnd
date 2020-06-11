package newAST;

import VisitorPkg.*;
import parser.*;
import lexer.*;

public class If extends Stmt {
    public Expr expr;
    public Stmt stmt;

    public If(Expr a, Stmt b){
	expr = a;
	stmt = b;
	// ANYTHING ELSE??????????????????
    }
    public void accept(Visitor v) {
	v.visit(this);
    }
}
