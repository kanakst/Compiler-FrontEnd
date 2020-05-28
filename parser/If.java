package parser;

import java.util.*;
import java.io.*;

public class If extends Stmt {
    private int val;
    private String name;

    Expr left;
    Stmt right;

    public If(Expr expr, Stmt stmt) {
	name = "If";
	left = expr;
	right = stmt;
	System.out.println("If constructor was called!");
    }

    public void printNodes() {
	System.out.println("root  node: " + name);
	System.out.println("left  node: expr");
	System.out.println("right node: stmt");
    }

    
}
