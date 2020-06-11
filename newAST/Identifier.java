package newAST;

import VisitorPkg.*;
import parser.*;
import lexer.*;

public class Identifier extends Expr {
    public String name;
    public Token token;
    public int value;
    // what about tag???
    
    public Identifier(Token t) {
	token = t;
	name = token.toString();
    }
    public void accept(Visitor v) {
	v.visit(this);
    }
}
