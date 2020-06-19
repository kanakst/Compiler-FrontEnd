package newparser;

import lexer.*;

import newAST.*;
import VisitorPkg.*;

import java.io.*;
import java.util.*;

public class Parser {
    private Lexer lex;
    private Token lookahead;
    
    public Interpreter visitor = new Interpreter(); //is this gonna work?

    public List<Stmt> stmtList = new ArrayList<Stmt>();
    public List<Stmt> opt_stmts = new ArrayList<Stmt>();

    
    public static int tabs = 0;

    
       
    public Parser(Lexer lexer) {
        lex = lexer;
    }
    
    public void match(String str) throws IOException {
        
        if (lookahead.toString().equals(str)) {
           
            lookahead  = lex.scan();
        }

        else throw new IOException ("in match : Syntax Error!");
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
            //st.accept(visitor);    // AST printing functions
            // System.out.println();
            
            if (lookahead.toString().equals(";")) { 
                
                tabs = 0;
                //System.out.println("stmtList size is : " + stmtList.size());
                //System.out.println();
                lookahead = lex.scan();
                
            } else if (lookahead.toString().equals("\n")) {

                //System.out.println("stmtList size is : " + stmtList.size());
                lookahead = null;   
            }
        }
    }

    public void printList() throws IOException {
        for (int i = 0; i < stmtList.size(); i++) {
            stmtList.get(i).accept(visitor);
            System.out.println();
            System.out.println();
            System.out.println("stmtList index is : " + i);
            System.out.println();
        }
    }
    
    public Stmt Start() throws IOException {
        
        Stmt s;
        s = stmt();
        return s;
        
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
                } else throw new IOException("Syntax error in Block : DID NOT MATCH END");
            }
           
        }

        return ;
        
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
 
        }
        
        else if (lookahead.tag == Tag.WHILE) {
            
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

            
        }
        
        else if (lookahead.tag == Tag.BEGIN) {
            
            List<Stmt> opt_stmts = new ArrayList<Stmt>();
            
            lookahead = lex.scan();
            
            if (lookahead.tag == Tag.END) { // null stmt case
                
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

            } else throw new IOException ("in stmt() in Tag.ID : Syntax Error");

        } else if (lookahead.toString().equals("\n")) {

            return null;
            
        } else throw new IOException ("in stmt() : Syntax Error");
    
        
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
            Expr e2 = moreterms(termSecond);

            if (e2 == null) {
                e2 = termSecond;
            }

            Expr e1 = new PlusExpr(termFirst, e2);
            return e1;

        } else if(lookahead.tag == Tag.MINUSSIGN) {
            lookahead = lex.scan();
            Expr termSecond = term();
            Expr e2 = moreterms(termSecond);
            if (e2 == null) {
                e2 = termSecond;
            }
            Expr e1 = new MinusExpr(termFirst, e2);
            return e1;
        } else if ( lookahead.toString().equals(";") ) { 
            return termFirst;
        }
        else if (lookahead.toString().equals("\n")) {
            
            return termFirst;
        }
        else if (lookahead.tag == Tag.THEN || lookahead.tag == Tag.DO || lookahead.tag == Tag.END || lookahead.tag == Tag.RIGHTBRACKET){
            return termFirst;
        }
        else throw new IOException("in moreterms: Syntax Error");
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
            Expr e2 = morefactors(factorSecond);
            if(e2 == null) {
                e2 = factorSecond;
            }
            Expr e1 = new TimesExpr(factorFirst, e2);
            return e1;
        } else if (lookahead.tag == Tag.DIVIDESIGN || lookahead.tag == Tag.DIVISION ) {
            lookahead = lex.scan();
            Expr factorSecond = factor();
            Expr e2 = morefactors(factorSecond);
            if(e2 == null) {
                e2 = factorSecond;
            }
            Expr e1 = new DivideExpr(factorFirst, e2);
            return e1;
        } else if (lookahead.tag == Tag.MOD) {
            lookahead = lex.scan();
            Expr factorSecond = factor();
            Expr e2 = morefactors(factorSecond);
            if (e2 == null) {
                e2 = factorSecond;
            }
            Expr e1 = new ModExpr(factorFirst, e2);
            return e1;

        } else if (lookahead.toString().equals(";") ) {
            return factorFirst;
        } else if (lookahead.tag == Tag.PLUSSIGN || lookahead.tag == Tag.MINUSSIGN) {
            return factorFirst;
        } else if (lookahead.tag == Tag.THEN || lookahead.tag == Tag.DO ||  lookahead.tag == Tag.END || lookahead.tag == Tag.RIGHTBRACKET){
            return factorFirst; 
        

        } else if (lookahead.toString().equals("\n")) {
            
            return factorFirst;
            
        } else {
            
            throw new IOException("in morefactors() : Syntax Error");
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
            
           
        }
        else throw new IOException ("in factor : Syntax Error");
    }

    
}
