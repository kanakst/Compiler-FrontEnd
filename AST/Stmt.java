package AST;

import lexer.*;
import parser.*;
import VisitorPkg.*;

import java.util.*;
import java.io.*;

public class Stmt extends Cell {

    public String name;
    public int value;
    public Token token;
    
    public Stmt stmt = null;
    public Expr expr = null;

    public Stmt() {}
    
    public Stmt (Token token) {
	this.token = token;
	name = token.toString();
	value = token.tag;
    }
    
    public Stmt(String name) {
	this.name = name;
    }
    public Stmt(int value) {
	this.value = value;
    }


    
    public void accept(Visitor v) {
	System.out.println("Stmt need overriding anyway");
    }
    
}
