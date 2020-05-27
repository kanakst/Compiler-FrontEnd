package parser;

import java.util.*;
import java.io.*;

public class If extends Stmt {
    private int val;
    private String name;

    Expr left;
    Stmt right;

    public If(Expr expr, Stmt stmt) {
	left = expr;
	right = stmt;
	System.out.println("If constructor is awaken!");
    }
}
