package emitter;

import newparser.*;
import newAST.*;
import VisitorPkg.*;
import java.util.*;

public class Emitter {

    private Parser pars;

    public Translator visitor = new Translator();
    
    public Emitter(Parser parser) {

        pars = parser;
    }

    public void stmtEmitter(List<Stmt> list) {
       
        for (int i = 0; i < list.size(); i++) {
            System.out.println();
            pars.stmtList.get(i).accept(visitor);
            System.out.println();
        }
    }
}
