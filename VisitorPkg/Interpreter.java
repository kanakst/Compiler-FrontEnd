package VisitorPkg;

import newAST.*;
import lexer.*;
import newparser.*;

public class Interpreter implements Visitor {
    public void printTabs(int tabsNum) {
	for(int i = 0; i < tabsNum; i++) {
	    System.out.print("   ");
	}
    }
    public void visit(TimesExpr n) {
	System.out.println("*");
	n.e1.accept(this);
	n.e2.accept(this);
    }

    public void visit(DivideExpr n) {
	System.out.println("/");
	n.e1.accept(this);
	n.e2.accept(this);
    }

    public void visit(ModExpr n) {
	System.out.println("mod");
	System.out.print("identation is : " + Parser.tabs);
	System.out.print("  ");
	n.e1.accept(this);
	System.out.print("  ");
	n.e2.accept(this);
    }

    public void visit(PlusExpr n) {


	System.out.println("+");

	++Parser.tabs;
	int localtabs = Parser.tabs;
	
	
	System.out.println();
	printTabs(localtabs);
	n.e1.accept(this);

	System.out.println();
	printTabs(localtabs);
	n.e2.accept(this);
    }

    public void visit(MinusExpr n) {
	System.out.println("-");
	n.e1.accept(this);
	n.e2.accept(this);
    }

    public void visit(Identifier n) {
	//System.out.println();
	//printTabs(Parser.tabs);
	System.out.print(n.name);
	//return n.accept(this);
	//n.token.tag; 
    }

    public void visit(Numerical n) {
	//System.out.println();
	//printTabs(Parser.tabs);
	System.out.print(n.value);
	//return n.accept(this);
	//n.value; // hmmmmmm FIX THIS!
    }

    public void visit(Assign n) {

	System.out.println("Assign");

	++Parser.tabs;
	int localtabs = Parser.tabs;

	System.out.println();
	printTabs(localtabs);
        n.id.accept(this); // is it like this???

	System.out.println();
	printTabs(localtabs);
	n.expr.accept(this);
	//return 0;
    }

    public void visit(If n) {
	
	System.out.println("If");
	
	++Parser.tabs;
	int localtabs = Parser.tabs;

	//printTabs(localtabs);
	System.out.println();
	printTabs(localtabs);
	n.expr.accept(this);

	System.out.println();
	printTabs(localtabs);
	n.stmt.accept(this);
	//return 0; // ???
    }
    public void visit(While n) {
	System.out.println("While");
	n.expr.accept(this);
	n.stmt.accept(this);
	//return 0;
    }
    
}
