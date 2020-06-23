package newAST;

import VisitorPkg.*;

public class While extends Stmt {

    public Expr expr;
    public Stmt stmt;

    public While(Expr a, Stmt b){
        expr = a;
        stmt = b;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
