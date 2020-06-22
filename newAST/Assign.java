package newAST;

import VisitorPkg.*;



public class Assign extends Stmt {
    public Identifier id;
    public Expr expr;
    public Assign(Identifier a, Expr b) {
        id = a;
        expr = b;
	
	
    }       
    public void accept(Visitor v) {
	v.visit(this);
    }
}
