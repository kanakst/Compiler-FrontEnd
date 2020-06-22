package VisitorPkg;
import newAST.*;

public interface Visitor {
    //Expr
    public void visit(TimesExpr n);
    public void visit(DivideExpr n);
    public void visit(ModExpr n);

    public void visit(DivisionExpr n);
    
    public void visit(PlusExpr n);
    public void visit(MinusExpr n);

    public void visit(Identifier n);
    public void visit(Numerical n);

    //Stmt
    public void visit(Assign n);
    public void visit(If n);
    public void visit(While n);
    public void visit(Block n);
}
