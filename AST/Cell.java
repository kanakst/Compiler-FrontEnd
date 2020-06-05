package AST;

import lexer.*;
import parser.*;
import VisitorPkg.*;

import java.util.*;
import java.io.*;

public class Cell {

    public String name;
    public int value;
    
    public Token token;
    
    public Cell left = null;
    public Cell right = null;
    
    public Cell () {}
    public Cell (String name) {
	this.name = name;
    }
    public Cell (int value) {
	this.value = value;
    }
    public Cell (String name, int value) {
	this.name = name;
	this.value = value;
    }
    
    public Cell (Token token) {
	this.token = token;
	name = token.toString();
	value = token.tag;
    }

    public void accept(Visitor v) {
	System.out.println("need overriding anyway");
    }
    
}
