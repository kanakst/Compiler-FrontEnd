package newAST;

import VisitorPkg.*;




public class DivideExpr extends Expr {
    public Expr e1, e2;

    public DivideExpr(Expr a1, Expr a2) {
        e1 = a1;
        e2 = a2;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

}
