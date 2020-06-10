package VisitorPkg;
import newAST.*;

public interface Visitor {
    //Expr
    public int visit(TimesExpr n);
    public int visit(DivideExpr n);
    public int visit(ModExpr n);
    
    public int visit(PlusExpr n);
    public int visit(MinusExpr n);

    public int visit(Identifier n);
    public int visit(Numerical n);

    //Stmt
    public int visit(Assign n);
    public int visit(If n);
    public int visit(While n);
}
