package newparser;

import lexer.*;

import newAST.*;
import VisitorPkg.*;

import java.io.*;
import java.util.*;

public class Parser {
    private Lexer lex;
    private Token lookahead;
    
    public Interpreter visitor = new Interpreter(); //is this gonna work?

    public List<Stmt> stmtList = new ArrayList<Stmt>();
    public int currIndex = 0;
    
    public Parser(Lexer lexer) {
	lex = lexer;
    }

   

    public void match(String str) throws IOException {
	if(lookahead.toString().equals(str)) {
	    lookahead  = lex.scan();
	}
	else throw new IOException ("in match : Syntax Error!");
    }

    public void parse() throws IOException {
	lookahead = lex.scan();
	while (lookahead != null) {
	    //Expr ex = expr();
	    //ex.accept(visitor);
	    //match(";");

	    Stmt st = stmt();
	    match(";");
	}
	/*
	Expr ex = expr();
	ex.accept(visitor);
	match(";");
	*/
     }

    public Expr Start() throws IOException {
	
	Expr e;
	e = expr();
	return e;
	
    }

    public Stmt stmt() throws IOException {

	if (lookahead.tag == Tag.IF) {
	    System.out.println("st IF : ");
 
	} else if (lookahead.tag == Tag.WHILE) {
	    System.out.println("st WHILE : ");
	} else if (lookahead.tag == Tag.BEGIN) {
	    System.out.println("st BEGIN : ");
	} else if (lookahead.tag == Tag.ID) {
	    
	    System.out.println("st IDENTIFIER/WORD : ");

	    Identifier id = new Identifier(lookahead);
       
	    lookahead = lex.scan();

	    if (lookahead.tag == Tag.ASSIGN) {

		lookahead = lex.scan();
		
		Expr expr = expr();

		Stmt stmt = new Assign(id, expr);

		return stmt;
		

	    } else throw new IOException ("in stmt() in Tag.ID : Syntax Error");
	    
	} else throw new IOException ("in stmt() : Syntax Error");
    
	return null; // SHOULD BE CHANGED!!!
	
	
    }

    public Expr expr() throws IOException {
	
	Expr term1 = term();
	Expr terms = moreterms(term1);
	return terms;
  
    }
    
    public Expr moreterms(Expr termFirst) throws IOException {
	
	if (lookahead.tag == Tag.PLUSSIGN) {
	    lookahead = lex.scan();
	    Expr termSecond = term();
	    Expr e2 = moreterms(termSecond);
	    if (e2 == null) {
		e2 = termSecond;
	    }
	    Expr e1 = new PlusExpr(termFirst, e2);
	    return e1;
	} else if(lookahead.tag == Tag.MINUSSIGN) {
	    lookahead = lex.scan();
	    Expr termSecond = term();
	    Expr e2 = moreterms(termSecond);
	    if (e2 == null) {
		e2 = termSecond;
	    }
	    Expr e1 = new MinusExpr(termFirst, e2);
	    return e1;
	} else if (lookahead.toString().equals(";")) {
	    return termFirst;
	}
	else throw new IOException("in moreterms: Syntax Error");
    }

    public Expr term() throws IOException {
	
	Expr factor1 = factor();

	Expr factors = morefactors(factor1);
	
	return factors;

    }

    public Expr morefactors(Expr factorFirst) throws IOException {
	//System.out.println(lookahead.tag);
	if (lookahead.tag == Tag.MULTIPLYSIGN) {
	    lookahead = lex.scan();
	    Expr factorSecond = factor();
	    Expr e2 = morefactors(factorSecond);
	    if(e2 == null) {
		e2 = factorSecond;
	    }
	    Expr e1 = new TimesExpr(factorFirst, e2);
	    return e1;
	} else if (lookahead.tag == Tag.DIVIDESIGN || lookahead.tag == Tag.DIVISION) {
	    lookahead = lex.scan();
	    Expr factorSecond = factor();
	    Expr e2 = morefactors(factorSecond);
	    if(e2 == null) {
		e2 = factorSecond;
	    }
	    Expr e1 = new DivideExpr(factorFirst, e2);
	    return e1;
	} else if (lookahead.toString().equals(";")) {
	    return factorFirst;
	}
	else if (lookahead.tag == Tag.PLUSSIGN || lookahead.tag == Tag.MINUSSIGN) {
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
	    
	    
	} else if (lookahead.tag == Tag.ID) {
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
