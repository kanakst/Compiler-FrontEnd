package VisitorPkg;

import newAST.*;
import newparser.*;

public class Interpreter implements Visitor {

    public void printTabs(int tabsNum) {

        for (int i = 0; i < tabsNum; i++) {
            System.out.print("   ");
        }
    }

    public void visit(TimesExpr n) {

        System.out.println("*");
        ++Parser.tabs;
        int localtabs = Parser.tabs;
        printTabs(localtabs);
        n.e1.accept(this);
        System.out.println();
        printTabs(localtabs);
        n.e2.accept(this);
        --Parser.tabs;
    }

    public void visit(DivideExpr n) {

        System.out.println("div");
        ++Parser.tabs;
        int localtabs = Parser.tabs;
        printTabs(localtabs);
        n.e1.accept(this);
        System.out.println();
        printTabs(localtabs);
        n.e2.accept(this);
        --Parser.tabs;
    }

    public void visit(DivisionExpr n) {

        System.out.println("/");
        ++Parser.tabs;
        int localtabs = Parser.tabs;
        printTabs(localtabs);
        n.e1.accept(this);
        System.out.println();
        printTabs(localtabs);
        n.e2.accept(this);
        --Parser.tabs; 
    }

    public void visit(ModExpr n) {

        System.out.println("Mod");
        ++Parser.tabs;
        int localtabs = Parser.tabs;
        printTabs(localtabs);
        n.e1.accept(this);
        System.out.println();
        printTabs(localtabs);
        n.e2.accept(this);
        --Parser.tabs;
    }

    public void visit(PlusExpr n) {

        System.out.println("+");
        ++Parser.tabs;
        int localtabs = Parser.tabs;
        printTabs(localtabs);
        n.e1.accept(this);
        System.out.println();
        printTabs(localtabs);
        n.e2.accept(this);
        --Parser.tabs;
    }

    public void visit(MinusExpr n) {

        System.out.println("-");
        ++Parser.tabs;
        int localtabs = Parser.tabs;
        printTabs(localtabs);
        n.e1.accept(this);
        System.out.println();
        printTabs(localtabs);
        n.e2.accept(this);
        --Parser.tabs;
    }

    public void visit(Identifier n) {

        System.out.print(n.name);
    }

    public void visit(Numerical n) {

        System.out.print(n.value);
    }

    public void visit(Assign n) {

        System.out.println("Assign");
        ++Parser.tabs;
        int localtabs = Parser.tabs;
        printTabs(localtabs);
        n.id.accept(this);
        System.out.println();
        printTabs(localtabs);
        n.expr.accept(this);
        --Parser.tabs;
    }

    public void visit(If n) {

        System.out.println("If");
        ++Parser.tabs;
        int localtabs = Parser.tabs;
        printTabs(localtabs);
        n.expr.accept(this);
        System.out.println();
        printTabs(localtabs);
        n.stmt.accept(this);
        --Parser.tabs;
    }

    public void visit(While n) {

        System.out.println("While");
        ++Parser.tabs;
        int localtabs = Parser.tabs;
        printTabs(localtabs);
        n.expr.accept(this);
        System.out.println();
        printTabs(localtabs);
        n.stmt.accept(this);
        --Parser.tabs;
    }

    public void visit(Block n) {

        System.out.println("{");
        ++Parser.tabs;
        int localtabs = Parser.tabs;
        for (int i = 0; i < n.blockStmtList.size(); i++ ) {
            printTabs(localtabs);
            n.blockStmtList.get(i).accept(this);
            System.out.println();
        }
        --Parser.tabs;
        printTabs(localtabs-1);
        System.out.print("}");
    }
}
