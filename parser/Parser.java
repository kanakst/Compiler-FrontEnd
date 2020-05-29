package parser;

import lexer.*; // is this even legal?
import java.io.*;
import java.util.*;

public class Parser {

    private Lexer lex;         // belong to parser class only
    private Token lookahead;
      
    public Parser(Lexer lexer) {
	lex = lexer;
    }
       

    // public void statement(); implement this later on, start with just an expression for now (it includes: factor, term, ...)...
    //public void expression(); // this is the main function I have to implement
            
    public void program() throws IOException {

	lookahead = lex.scan();
	String tokenType = lookahead.getClass().toString().substring(12);
	System.out.println("lookahead : " + tokenType + "--->  " + lookahead.toString()); // printer for scanner stuff


		
    }// program
}// parser class
    

