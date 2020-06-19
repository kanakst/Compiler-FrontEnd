package VisitorPkg;

import newAST.*;
import lexer.*;
import newparser.*;

public class Translator implements Visitor {
    private int assign_status = 0;
    public void visit(TimesExpr n) {
        n.e1.accept(this);
        n.e2.accept(this);
        System.out.println("*");
    }

    public void visit(DivideExpr n) {
        n.e1.accept(this);
        n.e2.accept(this);
        System.out.println("div");
    }

    
    public void visit(ModExpr n) {
        n.e1.accept(this);
        n.e2.accept(this);
        System.out.println("mod");
    }

    
    public void visit(PlusExpr n) {
        n.e1.accept(this);
        n.e2.accept(this);
        System.out.println("+");
    }
    

    public void visit(MinusExpr n) {
        n.e1.accept(this);
        n.e2.accept(this);
        System.out.println("-");
    }
    
    public void visit(Identifier n) {
        if (assign_status > 0) {
            System.out.println("lvalue " + n.name); // conditional!!!
            --assign_status;
        }  else
            System.out.println("rvalue " + n.name);
    }

    public void visit(Numerical n) {

        
        System.out.println("push " + n.value);

    }

    
    public void visit(Assign n) {

        ++assign_status;
        n.id.accept(this);

        // System.out.println();

        n.expr.accept(this);

        System.out.println("Assign or :=");

    }

    public void visit(If n) {

        n.expr.accept(this);

        System.out.println("gofalse out");

        n.stmt.accept(this);

        System.out.println("label out");
               
    }

    public void visit(While n) {

        System.out.println("label test");
        
        n.expr.accept(this);

        System.out.println("gofalse out");

        n.stmt.accept(this);

        System.out.println("goto test");

        System.out.println("label out");

    }

    public void visit(Block n) {

        System.out.println("{");
                      
        for (int i = 0; i < n.blockStmtList.size(); i++ ) {
       
            n.blockStmtList.get(i).accept(this);
            //System.out.println();
        }
              
        System.out.println("}");
        
    }
    

    
}
