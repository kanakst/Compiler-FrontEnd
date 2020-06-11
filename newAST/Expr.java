package newAST;

import VisitorPkg.*;
import newparser.*;
import lexer.*;

public abstract class Expr {
    public abstract void accept(Visitor v);
}
