package newAST;

import VisitorPkg.*;

public abstract class Stmt {

    public abstract void accept(Visitor v);
}
