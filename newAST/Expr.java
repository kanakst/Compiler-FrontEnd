package newAST;

import VisitorPkg.*;



public abstract class Expr {
    public abstract void accept(Visitor v);
}
