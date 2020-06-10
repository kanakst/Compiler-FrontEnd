package newAST;

import VisitorPkg.*;
import parser.*;
import lexer.*;

public class While extends Stmt {
    public Expr expr;
    public Stmt stmt;

    public While(Expr a, Stmt b){
	expr = a;
	stmt = b;
	// anything else???????
    }

    public int accept(Visitor v) {
	return v.visit(this);
    }
}
