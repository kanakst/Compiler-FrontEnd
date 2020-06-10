package newAST;

import VisitorPkg.*;

import parser.*;
import lexer.*;

public class ModExpr extends Expr {
    public Expr e1, e2;
    public ModExpr (Expr a1, Expr a2) {
	e1 = a1;
	e2 = a2;
    }

    public int accept (Visitor v) {
	return v.visit(this);
    }

    
}
