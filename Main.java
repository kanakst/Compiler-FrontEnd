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
        // System.out.println("printing stmtlist: ");
        // myparser.printList();

        myemitter.stmtEmitter(myparser.stmtList);

    }
}
