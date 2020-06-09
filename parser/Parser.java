package parser;

import lexer.*; // is this even legal?
import AST.*;
import java.io.*;
import java.util.*;

public class Parser {
    private Lexer lex;         
    private Token lookahead;
    private String tokenType;
    
    public Parser(Lexer lexer) {
	lex = lexer;
    }
    
    public Stmt stmt() throws IOException {
	
	Stmt res;
	
	switch(lookahead.toString()) {
	case "begin" : // 
	    // ...
	case "if" :
	    // ...
	case "while" :
	    //
	default : // assign
	    // any other word (lexeme)
	    Token t = lookahead;
	    String t_type = tokenType;

	    Expr id = new Expr(t); // ??is this correct?
	    
	    lookahead = lex.scan();
	    tokenType = lookahead.getClass().toString().substring(12);

	    if (lookahead.tag == Tag.ASSIGN) {
		
		lookahead = lex.scan();
		tokenType = lookahead.getClass().toString().substring(12);
		Expr expr = expr();
		
		res = new Assign(id, expr);
	    }
	    else {
		throw new IOException ("in Statement : Syntax Error");
	    }
	    
	    
	}

	return res;
	
    }
	

    
    
    
    public Expr expr() throws IOException {

	Expr term1 = term();
	Expr terms = moreterms(term1);
	return terms;
    }

    public Expr term() throws IOException {
		
	Expr factor1 = factor();
	Expr factors = morefactors(factor1);

	return factors; 
    }
    
    public Expr moreterms(Expr termFirst) throws IOException {
	//(tokenType.equals("Operator") && (lookahead.toString().equals("+") || lookahead.toString().equals("-")))
	if (lookahead.tag == Tag.PLUSSIGN || lookahead.tag == Tag.MINUSSIGN) {
	    
	    Expr operatorNode = new Expr(lookahead);
	    
	    lookahead = lex.scan();
	    tokenType = lookahead.getClass().toString().substring(12);

	    operatorNode.left = termFirst;
	    
	    Expr termSecond = term();                        
  
	    operatorNode.right = moreterms(termSecond);  

	    if (operatorNode.right == null) {
		operatorNode.right = termSecond;
	    }
	    
	    return operatorNode;
	    
	    
	}
	else if (lookahead.toString().equals(";")) return termFirst; 
	else throw new IOException("Syntax error : not a valid operator for term!");
    }   

    public Expr factor() throws IOException {

	if (tokenType.equals("Word") || tokenType.equals("Num")) {
	    
	    Expr factor = new Expr(lookahead);
	    
	    
	    lookahead = lex.scan();                                    
	    tokenType = lookahead.getClass().toString().substring(12); 

	    
	    return factor;
	}
	
	else throw new IOException("Syntax error : not a valid factor!");

    }
    
    public Expr morefactors(Expr factorFirst) throws IOException {
	
	if (lookahead.tag == Tag.MULTIPLYSIGN || lookahead.tag == Tag.DIVIDESIGN || lookahead.tag == Tag.MOD ) { // should be one of these operators : " * / div mod "
		
	   
	    Expr operatorNode = new Expr(lookahead);

	    lookahead = lex.scan();
	    tokenType = lookahead.getClass().toString().substring(12);

	    operatorNode.left = factorFirst;
	    
	    Expr factorSecond = factor();                   
  
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

    public void traverse(Expr root) {
	if(root == null)
	    return;
	System.out.println(root.name + " tag : " + root.value);
	
	traverse(root.left);
	traverse(root.right);
    }



    
    public void buildAST() throws IOException {

	
	lookahead = lex.scan();
	tokenType = lookahead.getClass().toString().substring(12);

	Stmt tree = stmt();

	traverse(tree);
	
	
	
    }
}
    

