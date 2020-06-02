package parser;

import lexer.*; // is this even legal?
import java.io.*;
import java.util.*;

public class Parser {

    private Lexer lex;         
    private Token lookahead;
    private String tokenType;
    
    public Parser(Lexer lexer) {
	lex = lexer;
    }
       

    // public void statement(); implement this later on, start with just an expression for now (it includes: factor, term, ...)...
    
    public void stmt() throws IOException {
	//Assignment

	
	
	// id := expr          {stmt =  new  Assign(id, expr);}  -->  is this where scope or environment should be used?
	
	// if expr then stmt   {stmt =  new    If(expr, stmt);}

	// while expr do stmt  {stmt =  new While(expr, stmt);}

	// begin opt_stmts end {stmt = opt_stmts;             }
	
    }
    
    
    public Cell expr() throws IOException {

	Cell term1 = term();
	Cell terms = moreterms(term1);
	return terms;
    }

    public Cell term() throws IOException {
		
	Cell factor1 = factor();
	Cell factors = morefactors(factor1);

	return factors; 
    }
    
    public Cell moreterms(Cell termFirst) throws IOException {

	if (tokenType.equals("Operator") && (lookahead.toString().equals("+") || lookahead.toString().equals("-"))) {
	    
	    Cell operatorNode = new Cell(lookahead);
	    
	    lookahead = lex.scan();
	    tokenType = lookahead.getClass().toString().substring(12);

	    operatorNode.left = termFirst;
	    
	    Cell termSecond = term();                        
  
	    operatorNode.right = moreterms(termSecond);  

	    if (operatorNode.right == null) {
		operatorNode.right = termSecond;
	    }
	    
	    return operatorNode;
	    
	    
	}
	else if (lookahead.toString().equals(";")) return termFirst; 
	else throw new IOException("Syntax error : not a valid operator for term!");
    }   

    public Cell factor() throws IOException {

	if (tokenType.equals("Word") || tokenType.equals("Num")) {
	    
	    Cell cell = new Cell(lookahead);
	    
	    
	    lookahead = lex.scan();                                    
	    tokenType = lookahead.getClass().toString().substring(12); 

	    
	    return cell;
	}
	
	else throw new IOException("Syntax error : not a valid factor!");

    }
    
    public Cell morefactors(Cell factorFirst) throws IOException {
	
	if (tokenType.equals("Operator") && ( lookahead.toString().equals("*") || lookahead.toString().equals("/") || lookahead.toString().equals("div") || lookahead.toString().equals("mod") )) { // should be one of these operators : " * / div mod "
		
	   
	    Cell operatorNode = new Cell(lookahead);

	    lookahead = lex.scan();
	    tokenType = lookahead.getClass().toString().substring(12);

	    operatorNode.left = factorFirst;
	    
	    Cell factorSecond = factor();                   
  
	    operatorNode.right = morefactors(factorSecond); 

	    if (operatorNode.right == null) {
		operatorNode.right = factorSecond;
	    }
	    
	    return operatorNode;
	   
	}
	else if (lookahead.toString().equals(";"))  return factorFirst;

	
	else if (lookahead.toString().equals("+") || lookahead.toString().equals("-"))  return factorFirst;
	    
	
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
	
	// 2. As soon as the scanner reads input and makes a token, it passes both the token and token's type to 'expression'('statement') method

	Cell tree = expr();
	System.out.println("Tree is : ");
	traverse(tree);
	
	
	
    }
}
    

