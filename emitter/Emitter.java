package emitter;

import lexer.*;
import newparser.*;

import newAST.*;
import VisitorPkg.*;

import java.io.*;
import java.util.*;

public class Emitter {

    private Parser pars;

    public Translator visitor = new Translator(); // is this gonna work???
    
    public Emitter(Parser parser) {
        pars = parser;
    }

    public void stmtEmitter(List<Stmt> list) {

        System.out.println("from Emmitter : stmtlist size is : " + list.size());

        for (int i = 0; i < list.size(); i++) {

            System.out.println();
            pars.stmtList.get(i).accept(visitor);
            System.out.println();
            
        }
    }
}
