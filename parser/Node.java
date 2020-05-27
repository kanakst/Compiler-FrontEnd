package parser;

import java.util.*;
import java.io.*;

public class Node {
   
    private String name;

    public Node left;
    public Node right;

    public Node(){}
    /*
    Node(int val, String name) {
	this.val = val;
	this.name = name;
    }
    */
    public Node( String name, Node left, Node right) {
	
	this.name = name;
	this.left = left;
	this.right = right;
    }
    
}
