import lexer.*;
import newparser.*;
import emitter.*;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Lexer mylexer = new Lexer();
        Parser myparser = new Parser(mylexer);
        Emitter myemitter = new Emitter(myparser);

        myparser.parse();
                
        if (args[0].equals("ast")) {
            System.out.println("printing AST : ");
            System.out.println();
            myparser.printList();
        }

        else if (args[0].equals("code")) {
            System.out.println("printing pseudo-machine code : ");
            myemitter.stmtEmitter(myparser.stmtList);
        }

    }
}
