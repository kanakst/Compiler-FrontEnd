package parser;

import java.util.*;
import java.io.*;

public class Numnode extends Factor {
    private int val;
    //private String name;

    public Numnode(int value) {
	val = value;
    }
    public int getNodeVal() {
	return val;
    }
    public void printNodeVal() {
	System.out.println("" + val);
    }
    
}
