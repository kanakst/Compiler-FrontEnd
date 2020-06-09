package newAST;

import VisitorPkg.*;
import parser.*;
import lexer.*;

public class Assign extends Stmt {
    public Identifier id;
    public Expr expr;
    public Assign(Identifier a, Expr b) {
	id = a;
	expr = b;
	//id.value = b.value; // ????????
	// Do I need to put the reserved word and its value to hashtable?
    }       
    public int accept(Visitor v) {
	return v.visit(this);
    }
}
