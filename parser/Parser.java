package parser;

import lexer.*; // is this even legal?
import java.io.*;
import java.util.*;

public class Parser {
    private Lexer lex;         // belong to parser class only
    private Token lookahead;

    private Node root, node;
    
    public Parser(Lexer lex) {
	
    }
    public boolean match(String title) {
	
	if(lookahead.toString() == title)
	    return true;
	else return false;
    }
    public void program(Token token) {

	lookahead = token;
	System.out.println("parser:program: lookahead is " + lookahead.toString());

	switch(lookahead) {
	case "if"://actually have to check the syntax, with match() function, iterating over tokens // but for now:
	    node = new If(
	    
	}

    }

    
}
