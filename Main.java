import lexer.*;
import parser.*;

import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {

	Lexer mylexer = new Lexer();
	Parser myparser = new Parser(mylexer);
	
	myparser.program();
	
    }
}
