package VisitorPkg;

import newAST.*;

public class Translator implements Visitor {

    private int assign_status = 0;
    private int if_label = 1;
    private int while_label = 1;
    
    public void visit(TimesExpr n) {

        n.e1.accept(this);
        n.e2.accept(this);
        System.out.println("*");
    }

    public void visit(DivideExpr n) {

        n.e1.accept(this);
        n.e2.accept(this);
        System.out.println("/");
    }

    public void visit(DivisionExpr n) {

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
            System.out.println("lvalue " + n.name);
            --assign_status;
        } else {
            System.out.println("rvalue " + n.name);
        }
    }

    public void visit(Numerical n) {

        System.out.println("push " + n.value);
    }
    
    public void visit(Assign n) {

        ++assign_status;
        n.id.accept(this);
        n.expr.accept(this);
        System.out.println("store");
    }

    public void visit(If n) {
        
        if_label = Math.max(if_label, while_label);
        int local_label = if_label;
        if_label = if_label + 1;
        n.expr.accept(this);
        System.out.println("gofalse L" + local_label);
        n.stmt.accept(this);
        System.out.println("label L" + local_label);
    }

    public void visit(While n) {
        
        while_label = Math.max(if_label, while_label);
        int true_label = while_label;
        while_label = while_label + 1;
        int false_label = while_label;
        while_label = while_label + 1;
        System.out.println("label L" + true_label);
        n.expr.accept(this);
        System.out.println("gofalse L" + false_label);
        n.stmt.accept(this);
        System.out.println("goto L" + true_label);
        System.out.println("label L" + false_label);
    }

    public void visit(Block n) {
                      
        for (int i = 0; i < n.blockStmtList.size(); i++ ) {
            n.blockStmtList.get(i).accept(this);
        }
    }
}
