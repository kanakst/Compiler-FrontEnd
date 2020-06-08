package newparser;

import lexer.*;

import newAST.*;

import java.io.*;
import java.util.*;

public class Parser {
    private Lexer lex;
    private Token lookahead;

    public Parser(Lexer lexer) {
	lex = lexer;
    }

    public void match(String str) throws IOException {
	if(lookahead.toString().equals(str)) { // hope this is correct form
	    System.out.println("Token is matched!");
	    lookahead  = lex.scan();
	}
	else throw new IOException ("in match : Syntax Error!");
    }

    public void parse() throws IOException {
	lookahead = lex.scan();
	while (lookahead != null) { //this one maybe???
	    expr(); match(";");
	}
     }

    

    
    public Expr Start() throws IOException {
	Expr e;
	e = expr();
	return e;
    }

    public Expr expr() throws IOException {

	Expr e1, e2;
	e1 = term();

	if(lookahead.tag == Tag.PLUSSIGN) {
	    lookahead = lex.scan(); // moving the pointer
	    e2 = term();
	    e1 = new PlusExpr(e1, e2);
	    return e1;
	    
	}
	else if(lookahead.tag == Tag.MINUSSIGN) {
	    lookahead = lex.scan();
	    e2 = term();
	    e1 = new MinusExpr(e1, e2);
	    return e1;
	}
	else throw new IOException ("in expr() : Syntax Error");




    }

    public Expr term() throws IOException {

	Expr e1, e2;

	//Token t;
	e1 = factor();

	System.out.println(lookahead.toString());
	
	if(lookahead.tag == Tag.MULTIPLYSIGN) {
	    lookahead = lex.scan(); // moving the pointer
	    e2 = factor();
	    e1 = new TimesExpr(e1, e2);
	    return e1;
	    
	}
	else if(lookahead.tag == Tag.DIVIDESIGN) {
	    lookahead = lex.scan();
	    e2 = term();
	    e1 = new DivideExpr(e1, e2);
	    return e1;
	}
	else throw new IOException ("in term() : Syntax Error");

	//return e1;




	

	/*
	while(true) {
	    switch(lookahead.toString()) {
	    case "*":
		t = lookahead;
		match(lookahead.toString());
		factor();
		// emit???
	    case "/":
		t = lookahead;
		match(lookahead.toString());
		factor();
		// emit???
	    }
	}
	*/
    }

    public Expr factor() throws IOException {
	//if(lookahead.toString().equals("(")) {
	//    match("(");expr();match(")");break;
	//}
	
	if (lookahead.tag == Tag.NUM) {
	    //emit (NUM, tokenval);
	    Numerical numerical = new Numerical(lookahead);
	    lookahead = lex.scan();
	    return numerical; // ???
	    
	    
	}
	else if (lookahead.tag == Tag.ID) {
	    // emit (ID, tokenval);
	    // how to match in here?

	    //match and break
	    Identifier identifier = new Identifier(lookahead);
	    lookahead = lex.scan();
	    return identifier;
	    //break; 
	}
	else throw new IOException ("in factor : Syntax Error");
    }

    
}
