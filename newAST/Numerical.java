package newAST;

import VisitorPkg.*;
import parser.*;
import lexer.*;

public class Numerical extends Expr {
    public int value; // ? or shd be String name?
    public Token token;
    // tag should be here as well??
    
    public Numerical(Token t) {
	this.token = t;
	this.value = Integer.parseInt(token.toString());
    }

    public int accept(Visitor v) {
	return v.visit(this);
    }
}
