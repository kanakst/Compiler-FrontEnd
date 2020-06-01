package parser;

import lexer.*; // is this even legal?
import java.io.*;
import java.util.*;

public class Parser {

    private Lexer lex;         // belong to parser class only
    private Token lookahead;
    private String tokenType;
    
    public Parser(Lexer lexer) {
	lex = lexer;
    }
       

    // public void statement(); implement this later on, start with just an expression for now (it includes: factor, term, ...)...
    /*
    public Cell expr() throws IOException {

	Cell term1 = term();
	Cell terms = moreterms();

    }

    public Cell moreterms() throws IOException {

	if (tokenType.equals("Operator") && (lookahead.toString().equals("+") || lookahead.toString().equals("-"))) {
	    
	    
	}
	else if (lookahead.toString().equals(";")) return null;
	else throw new IOException("Syntax error : not a valid operator for factor!");
    }
    */
    public Cell term() throws IOException { // this is the main function I have to implement
		
	Cell factor1 = factor();
	Cell factors = morefactors(factor1);

	return factors; 
    }

    public Cell factor() throws IOException {

	if (tokenType.equals("Word") || tokenType.equals("Num")) {
	    
	    Cell cell = new Cell(lookahead);
	    
	    // Iterating before returning
	    lookahead = lex.scan();                                    // moving the pointer 
	    tokenType = lookahead.getClass().toString().substring(12); // updating the type tokenType variable

	    // Return saved token
	    return cell; //okay lets see
	} 
	else
	    throw new IOException("Syntax error : not a valid factor!");
    }

    public Cell morefactors(Cell factorFirst) throws IOException {
	
	if (tokenType.equals("Operator") && ( lookahead.toString().equals("*") || lookahead.toString().equals("/") || lookahead.toString().equals("div") || lookahead.toString().equals("mod") )) { // should be one of these operators : " * / div mod "
		
	    
	    Cell operatorNode = new Cell(lookahead);

	    lookahead = lex.scan();
	    tokenType = lookahead.getClass().toString().substring(12);

	    operatorNode.left = factorFirst;
	    
	    Cell factorSecond = factor();                    // checking for the second factor
  
	    operatorNode.right = morefactors(factorSecond);  // recursively assigning to operatorNode.right

	    if (operatorNode.right == null) {
		operatorNode.right = factorSecond;
	    }
	    
	    return operatorNode;
	   
	}
	else if (lookahead.toString().equals(";")) return null;
	else throw new IOException("Syntax error : not a valid operator for factor!");
    }


    public void traverse(Cell root) {
	if(root == null)
	    return;
	System.out.println(root.name);
	traverse(root.left);
	traverse(root.right);
    }



    
    public void buildAST() throws IOException {

	// 1. Have to Implement a loop here, reading Tokens until EndOfTokens
	
	lookahead = lex.scan();
	tokenType = lookahead.getClass().toString().substring(12);
	
	//System.out.println("lookahead : " + tokenType + "--->  " + lookahead.toString()); // printer for scanner stuff
	
	// 2. As soon as the scanner reads input and makes a token, it passes both the token and token's type to 'expression'('statement') method

	// doljno byt expr() poidee no poka I wanna try the term first!
	//Cell tree = new Cell();
	//tree = term();

	Cell tree = term();
	System.out.println("Tree is : ");
	traverse(tree);
	
	
	
    }// program
}// parser class
    

