package newAST;

import VisitorPkg.*;

public class ModExpr extends Expr {

    public Expr e1, e2;

    public ModExpr (Expr a1, Expr a2) {
        e1 = a1;
        e2 = a2;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }
}
