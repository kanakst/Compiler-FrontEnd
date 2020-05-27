import lexer.*;
import parser.*;

import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {

	Lexer mylexer = new Lexer();
	Parser myparser = new Parser(mylexer);
	
	Token t; // for testing, and printing out examples
	System.out.println("Hello World!");
	while(mylexer.peek != '\n'){
	    t = mylexer.scan();
	    System.out.println(t.toString());
	} 
	
    }
}
