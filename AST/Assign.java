package AST;

import lexer.*;
import parser.*;
import VisitorPkg.*;

import java.util.*;
import java.io.*;

public class Assign extends Stmt {

    public String name;
    public int value;
    public Token token;
    
    public Expr factor = null;
    public Expr expr = null;

    public Assign(Expr expr, Expr factor) {
	this.expr = expr;
	this.factor = factor;
	// need to add more functionality here?
	// Environment or scope stff
    }


    
    public void accept(Visitor v) {
	System.out.println("Assign need overriding anyway");
    }
    
}
