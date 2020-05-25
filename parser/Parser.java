package parser;

import lexer.*; // is this even legal?
import java.io.*;
import java.util.*;

public class Parser {

    private Token token;
    
    public Parser(Token t) {
	token = t;
    }

    public void program() {
	String tokenName;

	tokenName = token.toString();
	if(token.tag >= 500) { // tag range for operations/ statements

	}
	else if(token.tag >= 400 && token.tag < 500) {

	}
	else if(token.tag >= 300 && token.tag < 400) {

	}
	
    }
}
