package parser;

import java.util.*;
import java.io.*;

public class Expr extends Node {
    private int val;
    private String name;

    Node left;
    Node right;
    public Expr() {} // super???
    public Expr(String name, Node left, Node right) {
	this.name = name;
	this.left = left;
	this.right = right;
    }
}
