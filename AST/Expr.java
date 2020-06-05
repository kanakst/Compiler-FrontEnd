package AST;

import lexer.*;
import parser.*;
import VisitorPkg.*;

import java.util.*;
import java.io.*;

public class Expr extends Cell {

    public String name;
    public int value;
    
    public Token token;
    
    public Expr left = null;
    public Expr right = null;

    public Expr() {}
    public Expr (Token token) {
	this.token = token;
	name = token.toString();
	value = token.tag;
    }
    
    public Expr(String name) {
	this.name = name;
    }
    public Expr(int value) {
	this.value = value;
    }


    
    public void accept(Visitor v) {
	System.out.println("Expr need overriding anyway");
    }
    
}
