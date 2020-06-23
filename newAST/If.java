package newAST;

import VisitorPkg.*;

public class If extends Stmt {

    public Expr expr;
    public Stmt stmt;

    public If(Expr a, Stmt b){
        expr = a;
        stmt = b;
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }
}
