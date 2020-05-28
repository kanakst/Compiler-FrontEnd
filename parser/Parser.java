package parser;

import lexer.*; // is this even legal?
import java.io.*;
import java.util.*;

public class Parser {
    private Lexer lex;         // belong to parser class only
    private Token lookahead;

    private Node root, node;
    
    public Parser(Lexer lexer) {
	lex = lexer;
    }
    public void match(String title) throws IOException { //match and moves!
	if(title.equals(lookahead.toString()) == false) {
	    
	    System.out.println("lookahead is : " + lookahead.toString());
	    System.out.println("title is     : " + title);
	    throw new IOException ("Syntax Error in match");
	}
	
	lookahead = lex.scan(); // scanning for next token
    }
            
    public void program() throws IOException {

	

	Expr expr;
	Stmt stmt;


	while(lex.peek != '\n') {
	    
	    lookahead = lex.scan();
	    System.out.println("lookahead : " + lookahead.getClass() + "--->  " + lookahead.toString()); // printer for scanner stuff
	    //System.out.println("Class of lookahead token is : " + lookahead.getClass());
	    // inside this while loop, have to make the parser AST things !
	    
	    switch(lookahead.toString()) {
	    case "if":          //actually have to check the syntax, with match() function, iterating over tokens // but for now:
		match("if");
		match("(");
		System.out.println("matched for if case!");
		//expr = new matchExpr(); //should be an expression here
		//match(")");
		//stmt = new matchStmt(); //should be a statement here

		//match(";"); //do I really need it here

		//If ifStmt = new If(expr, stmt);
		//ifStmt.printNodes();

	    default :
		
		continue;
	    }
	


	}
	

	

    }

    
}
