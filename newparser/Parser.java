package newparser;

import lexer.*;
import newAST.*;
import VisitorPkg.*;
import java.io.*;
import java.util.*;

public class Parser {

    private Lexer lex;
    private Token lookahead;
    public Interpreter visitor = new Interpreter();
    public List<Stmt> stmtList = new ArrayList<Stmt>();
    public List<Stmt> opt_stmts = new ArrayList<Stmt>();
    public static int tabs = 0;
       
    public Parser(Lexer lexer) {

        lex = lexer;
    }
    
    public void match(String str) throws IOException {
        
        if (lookahead.toString().equals(str)) {
            lookahead  = lex.scan();
        } else {
            throw new IOException ("in match() : Syntax Error in line : " + lex.line);
        }
    }

    public void parse() throws IOException {
                
        lookahead = lex.scan();
        while (lookahead != null) {
            Stmt st = stmt();
            while (st == null) {
                lookahead = lex.scan();
                st = stmt();
            }
            stmtList.add(st);
            if (lookahead.toString().equals("\n")) {
                lookahead = null;
            }
        }
    }

    public void printList() throws IOException {

        for (int i = 0; i < stmtList.size(); i++) {
            stmtList.get(i).accept(visitor);
            System.out.println();
            System.out.println();
        }
    }

    public void recursiveBlockStmt (List<Stmt> res) throws IOException {

        if (lookahead.toString().equals(";")) {
            lookahead = lex.scan();
            Stmt stmt = stmt();
            while (stmt == null) {
                lookahead = lex.scan();
                stmt = stmt();
            }
            res.add(stmt);
            recursiveBlockStmt(res);              
        }
        
        if (lookahead.toString().equals("\n")) {
            lookahead = lex.scan();
            while (lookahead != null) {
                if (lookahead.tag == Tag.END) {
                    return;
                } else if (lookahead.toString().equals("\n")) {
                    lookahead = lex.scan();
                } else {
                    throw new IOException("Syntax error in Block : DID NOT MATCH END in line : " + lex.line);
                }
            }
        }
        return;
    }

    public Stmt stmt() throws IOException { 

        if (lookahead.tag == Tag.IF) { 
            lookahead = lex.scan();
            Expr expr = expr();
            match("then");
            Stmt stmt = stmt();
            while (stmt == null) {
                lookahead = lex.scan();
                stmt = stmt();
            }
            Stmt if_stmt = new If(expr, stmt);
            return if_stmt;
        } else if (lookahead.tag == Tag.WHILE) {
            lookahead = lex.scan();
            Expr expr = expr();
            match("do");
            Stmt stmt = stmt();
            while (stmt == null) {
                lookahead = lex.scan();
                stmt = stmt();
            }
            Stmt while_stmt = new While(expr, stmt);
            return while_stmt;
        } else if (lookahead.tag == Tag.BEGIN) {
            List<Stmt> opt_stmts = new ArrayList<Stmt>();
            lookahead = lex.scan();
            if (lookahead.tag == Tag.END) {
                lookahead = lex.scan();
                return new Block(opt_stmts);
            }
            Stmt stmt = stmt();
            while (stmt == null) {
                lookahead = lex.scan();
                if (lookahead.tag == Tag.END) { 
                    lookahead = lex.scan();
                    return new Block(opt_stmts); 
                }
                stmt = stmt();
            }
            opt_stmts.add(stmt);
            recursiveBlockStmt(opt_stmts);
            lookahead = lex.scan();
            return new Block( opt_stmts );
        } else if (lookahead.tag == Tag.ID) { 
            Identifier id = new Identifier(lookahead);
            lookahead = lex.scan();
            if (lookahead.tag == Tag.ASSIGN) {
                lookahead = lex.scan();
                Expr expr = expr();
                Stmt stmt = new Assign(id, expr);
                return stmt;
            } else {
                throw new IOException ("in stmt() in Tag.ID : Syntax Error in line : " + lex.line);
            }
        } else if (lookahead.toString().equals("\n")) {
            return null;
        } else {
            throw new IOException ("in stmt() : Syntax Error in line : " + lex.line);
        }   
    }

    public Expr expr() throws IOException {
        
        Expr term1 = term();
        Expr terms = moreterms(term1);
        return terms;
    }
    
    public Expr moreterms(Expr termFirst) throws IOException {
        
        if (lookahead.tag == Tag.PLUSSIGN) {
            lookahead = lex.scan();
            Expr termSecond = term();
            Expr tester = new PlusExpr(termFirst, termSecond);
            Expr e2 = moreterms(tester);
            if (e2 == null) {
                e2 = tester;
            }
            return e2;
        } else if (lookahead.tag == Tag.MINUSSIGN) {
            lookahead = lex.scan();
            Expr termSecond = term();
            Expr tester = new MinusExpr(termFirst, termSecond);
            Expr e2 = moreterms(tester);
            if (e2 == null) {
                e2 = tester;
            }
            return e2;
        } else if (lookahead.toString().equals(";") || lookahead.toString().equals("\n")
                   || lookahead.tag == Tag.THEN || lookahead.tag == Tag.DO
                   || lookahead.tag == Tag.END || lookahead.tag == Tag.RIGHTBRACKET ) { 
            return termFirst;
        } else {
            throw new IOException("in moreterms(): Syntax Error in line : " + lex.line);
        }
    }
    
    public Expr term() throws IOException {
        
        Expr factor1 = factor();
        Expr factors = morefactors(factor1);
        return factors;
    }

    public Expr morefactors(Expr factorFirst) throws IOException {
        
        if (lookahead.tag == Tag.MULTIPLYSIGN) {
            lookahead = lex.scan();
            Expr factorSecond = factor();
            Expr tester = new TimesExpr(factorFirst, factorSecond);
            Expr e2 = morefactors(tester);
            if (e2 == null) {
                e2 = tester;
            }
            return e2;
        } else if (lookahead.tag == Tag.DIVIDESIGN ) {
            lookahead = lex.scan();
            Expr factorSecond = factor();
            Expr tester = new DivideExpr(factorFirst, factorSecond);
            Expr e2 = morefactors(tester);
            if (e2 == null) {
                e2 = tester;
            }
            return e2;
        } else if (lookahead.tag == Tag.DIVISION) {
            lookahead = lex.scan();
            Expr factorSecond = factor();
            Expr tester = new DivisionExpr(factorFirst, factorSecond);
            Expr e2 = morefactors(tester);
            if (e2 == null) {
                e2 = tester;
            }
            return e2;
        } else if (lookahead.tag == Tag.MOD) {
            lookahead = lex.scan();
            Expr factorSecond = factor();
            Expr tester = new ModExpr(factorFirst, factorSecond);
            Expr e2 = morefactors(tester);
            if (e2 == null) {
                e2 = tester;
            }
            return e2;
        } else if (lookahead.toString().equals(";") || lookahead.toString().equals("\n")
                   || lookahead.tag == Tag.PLUSSIGN || lookahead.tag == Tag.MINUSSIGN
                   || lookahead.tag == Tag.THEN || lookahead.tag == Tag.DO
                   || lookahead.tag == Tag.END || lookahead.tag == Tag.RIGHTBRACKET) {
            return factorFirst;
                
        } else {
            throw new IOException("in morefactors() : Syntax Error in line : " + lex.line);
        }
    }

    public Expr factor() throws IOException {
        
        if (lookahead.tag == Tag.NUM) {
            Numerical numerical = new Numerical(lookahead);
            lookahead = lex.scan();
            return numerical; 
        } else if (lookahead.tag == Tag.ID) {
            Identifier identifier = new Identifier(lookahead);
            lookahead = lex.scan();
            return identifier;
        } else if (lookahead.tag == Tag.LEFTBRACKET) {
            lookahead = lex.scan();
            Expr expr = expr();
            match(")");
            return expr;
        } else {
            throw new IOException ("in factor() : Syntax Error in line : " + lex.line);
        }
    }
}
