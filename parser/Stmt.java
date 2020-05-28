package parser;

import java.util.*;
import java.io.*;

public class Stmt extends Node {
    private int val;
    private String name;
    
    Node left;
    Node right;
    
    public Stmt() {} //super???
    public Stmt(Node left, Node right){
	this.left = left;
	this.right = right;
    }
    public Stmt(Node left) {
	this.left = left;
	this.right = null;
    }
    /*
    public Stmt(Expr expr, Stmt stmt){
	left = expr;
	right = stmt;
    }

    public Stmt(Stmt stmt, Expr expr){
	
    }
    */
}
