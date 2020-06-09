package newAST;

import VisitorPkg.*;
import parser.*;
import lexer.*;

public abstract class Stmt {
    public abstract int accept(Visitor v);
}
