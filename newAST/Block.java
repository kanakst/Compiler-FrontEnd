package newAST;

import VisitorPkg.*;

public class Block extends Stmt {

    public Stmt firstStmt;

    public Block(Stmt a) {
        firstStmt = a; // ?????????
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

}
