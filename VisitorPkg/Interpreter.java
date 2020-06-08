package VisitorPkg;

import newAST.*;
import lexer.*;
import parser.*;

public class Interpreter implements Visitor {
    
    public int visit(TimesExpr n) {
	return n.e1.accept(this) * n.e2.accept(this);
    }

    public int visit(DivideExpr n) {
	return n.e1.accept(this) / n.e2.accept(this);
    }

    public int visit(PlusExpr n) {
	return n.e1.accept(this) + n.e2.accept(this);
    }

    public int visit(MinusExpr n) {
	return n.e1.accept(this) - n.e2.accept(this);
    }

    //how to add Num and Word? Identifier?
    //shd be in the book

    public int visit(Identifier n) {
	return n.token.tag; // what is lookup???
    }

    public int visit(Numerical n) {
	return n.value; // hmmmmmm FIX THIS!
    }
}
