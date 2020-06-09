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
	    //System.out.println("Token is matched!");
	    System.out.println(lookahead.toString());
	    lookahead  = lex.scan();
	}
	else throw new IOException ("in match : Syntax Error!");
    }

    public void parse() throws IOException {
	lookahead = lex.scan();
	while (lookahead != null) { //this one maybe???
	    //expr();
	    Expr ex =  expr();
	    //expr();
	    match(";");
	}
     }

    

    
    public Expr Start() throws IOException {
	Expr e;
	e = expr();
	return e;
    }

    public Expr expr() throws IOException {

	Expr term1 = term();
	Expr terms = moreterms(term1);
	return terms;
	//Expr e1, e2;
	//e1 = term();
	/*
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
	//else throw new IOException ("in expr() : Syntax Error");

	return e1;
	*/

    }
    public Expr moreterms(Expr termFirst) throws IOException {
	if(lookahead.tag == Tag.PLUSSIGN) {
	    lookahead = lex.scan();
	    Expr termSecond = term();
	    Expr e2 = moreterms(termSecond);
	    if(e2 == null) {
		e2 = termSecond;
	    }
	    Expr e1 = new PlusExpr(termFirst, e2);
	    return e1;
	}
	else if(lookahead.tag == Tag.MINUSSIGN) {
	    lookahead = lex.scan();
	    Expr termSecond = term();
	    Expr e2 = moreterms(termSecond);
	    if(e2 == null) {
		e2 = termSecond;
	    }
	    Expr e1 = new MinusExpr(termFirst, e2);
	    return e1;
	}
	else if(lookahead.toString().equals(";")) {
	    return termFirst;
	}
	else throw new IOException("in moreterms: Syntax Error");
    }
    public Expr term() throws IOException {

	//Expr e1, e2;
	
	Expr factor1 = factor();
	//Token t;
	//e1 = factor();
	Expr factors = morefactors(factor1);
	//System.out.println(lookahead.toString());
	return factors;
	/*
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
	//else throw new IOException ("in term() : Syntax Error");
	
	return e1;
	*/
    }

    public Expr morefactors(Expr factorFirst) throws IOException {
	System.out.println(lookahead.tag);
	if(lookahead.tag == Tag.MULTIPLYSIGN) {
	    lookahead = lex.scan();
	    Expr factorSecond = factor();
	    Expr e2 = morefactors(factorSecond);
	    if(e2 == null) {
		e2 = factorSecond;
	    }
	    Expr e1 = new TimesExpr(factorFirst, e2);
	    return e1;
	}
	else if(lookahead.tag == Tag.DIVIDESIGN) {
	    lookahead = lex.scan();
	    Expr factorSecond = factor();
	    Expr e2 = morefactors(factorSecond);
	    if(e2 == null) {
		e2 = factorSecond;
	    }
	    Expr e1 = new DivideExpr(factorFirst, e2);
	    return e1;
	}
	else if(lookahead.toString().equals(";")) {
	    return factorFirst;
	}
	else if(lookahead.tag == Tag.PLUSSIGN || lookahead.tag == Tag.MINUSSIGN) {
	    return factorFirst;
	}
	else throw new IOException("in morefactors() : Syntax Error");
    }


    
    public Expr factor() throws IOException {
	
	
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
