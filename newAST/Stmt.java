package newAST;

import VisitorPkg.*;
import parser.*;
import lexer.*;

public abstract class Stmt {
    public abstract void accept(Visitor v);
}
