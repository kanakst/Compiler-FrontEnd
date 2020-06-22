package newAST;

import VisitorPkg.*;
import lexer.*;

public class Numerical extends Expr {
    public int value;
    public Token token;
    
    
    public Numerical(Token t) {
        this.token = t;
        this.value = Integer.parseInt(token.toString());
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
