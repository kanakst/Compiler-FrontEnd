package AST;

import lexer.*;
import parser.*;
import VisitorPkg.*;

import java.util.*;
import java.io.*;

public class If extends Stmt {

    public String name;
    public int value;
    public Token token;
    
    public Stmt stmt = null;
    public Expr expr = null;

    public If(Expr expr, Stmt stmt) {
	this.expr = expr;
	this.stmt = stmt;
    }


    
    public void accept(Visitor v) {
	System.out.println("If need overriding anyway");
    }
    
}
