package newAST;

import VisitorPkg.*;
import parser.*;
import lexer.*;

public abstract class Expr {
    public abstract int accept(Visitor v);
}
