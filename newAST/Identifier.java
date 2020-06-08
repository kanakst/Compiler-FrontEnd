package newAST;

import VisitorPkg.*;
import parser.*;
import lexer.*;

public class Identifier extends Expr {
    public String name;
    public Token token;
    // what about tag???
    
    public Identifier(Token t) {
	token = t;
	name = token.toString();
    }
    public int accept(Visitor v) {
	return v.visit(this);
    }
}
