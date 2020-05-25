import lexer.*;

import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {

	Lexer mylexer = new Lexer();
	Token t;
	/*
	Scanner sc = new Scanner(System.in);

	while (sc.hasNextLine()) {
	    t = mylexer.scan();
	    System.out.println(t.toString());
	}
	*/
	
	while(mylexer.peek != '\n'){
	    t = mylexer.scan();
	    System.out.println(t.toString());
	} 
	
    }
}
