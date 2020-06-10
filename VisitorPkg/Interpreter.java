package VisitorPkg;

import newAST.*;
import lexer.*;
import parser.*;

public class Interpreter implements Visitor {
    
    public int visit(TimesExpr n) {
	System.out.println("*");
	return n.e1.accept(this) * n.e2.accept(this);
    }

    public int visit(DivideExpr n) {
	System.out.println("/");
	return n.e1.accept(this) / n.e2.accept(this);
    }

    public int visit(ModExpr n) {
	System.out.println("mod");
	return n.e1.accept(this) % n.e2.accept(this);
    }

    

    public int visit(PlusExpr n) {
	System.out.println("+");
	return n.e1.accept(this) + n.e2.accept(this);
    }

    public int visit(MinusExpr n) {
	System.out.println("-");
	return n.e1.accept(this) - n.e2.accept(this);
    }

    //how to add Num and Word? Identifier?
    //shd be in the book

    public int visit(Identifier n) {
	System.out.println(n.name);
	//return n.accept(this);
	return n.token.tag; 
    }

    public int visit(Numerical n) {
	System.out.println(n.value);
	//return n.accept(this);
	return n.value; // hmmmmmm FIX THIS!
    }

    public int visit(Assign n) {
	System.out.println("Assign");
        n.id.accept(this); // is it like this???
	n.expr.accept(this);
	return 0;
    }

    public int visit(If n) {
	System.out.println("If");
	n.expr.accept(this);
	n.stmt.accept(this);
	return 0; // ???
    }
    public int visit(While n) {
	System.out.println("While");
	n.expr.accept(this);
	n.stmt.accept(this);
	return 0;
    }
    
}
