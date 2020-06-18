package newAST;

import VisitorPkg.*;

import java.util.*;

public class Block extends Stmt {

    
    public List<Stmt> blockStmtList; // = new ArrayList<Stmt>();
    public Block(List<Stmt> list ) {
         
         blockStmtList = new ArrayList<>(list);
         
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

}
