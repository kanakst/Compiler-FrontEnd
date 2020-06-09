package VisitorPkg;
import newAST.*;

public interface Visitor {

    public int visit(TimesExpr n);
    public int visit(DivideExpr n);
    // add more terminals and non-terminals
    public int visit(PlusExpr n);
    public int visit(MinusExpr n);

    public int visit(Identifier n);
    public int visit(Numerical n);

    public int visit(Assign n);

}
