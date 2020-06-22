package lexer;

import java.io.*;
import java.util.*;

public class Lexer {

    public char peek = ' ';
    public int line = 1;

    Hashtable<String, Token> words = new Hashtable<String, Token>();

    void reserve(Word w) {
        words.put(w.lexeme, w);
    }

    
    public Lexer() {
        reserve(new Word(Tag.IF, "if"));
        reserve(new Word(Tag.ELSE, "else"));

        reserve(new Word(Tag.WHILE, "while"));
        reserve(new Word(Tag.BEGIN, "begin"));

        reserve(new Word(Tag.THEN, "then"));
        reserve(new Word(Tag.DO, "do"));
        reserve(new Word(Tag.END, "end"));

        reserve(new Word(Tag.LEFTBRACKET, "("));
        reserve(new Word(Tag.RIGHTBRACKET, ")"));

        reserve(Operator.assign);
        reserve(Operator.division);
        reserve(Operator.mod);
        reserve(Operator.minusSign);
        reserve(Operator.plusSign);
        reserve(Operator.multiplySign);
        reserve(Operator.divideSign);
       
    }

    
    public Token scan() throws IOException {

    
        if (peek == ' ') {
            while (true) {
                peek = (char) System.in.read();
                if (peek == ' ' || peek == '\t')
                    continue;
                else if (peek == '\n') {
                    line = line + 1;
                    Token t = new Token(peek);
                    return t;

                } else
                    break;
            }
        }

        if (peek == ':') {
            peek = (char) System.in.read();
            if (peek == '=') {
                peek = ' ';
                return Operator.assign;
            } else
                return new Token(':');
        }

        if (Character.isDigit(peek)) {
            int value = 0;
            while (Character.isDigit(peek)) {
                value *= 10;
                value += Character.getNumericValue(peek);
                peek = (char) System.in.read();
            }

            if (peek == '.') {
                peek = (char) System.in.read();
                if (Character.isDigit(peek)) {
                    double divider = 1;
                    double afterDot = 0;
                    double decimal = (double) value;
                    while (Character.isDigit(peek)) {
                        divider *= 10;
                        afterDot *= 10;
                        afterDot = (double) Character.getNumericValue(peek);
                        decimal += afterDot / divider;
                        peek = (char) System.in.read();
                    }

                    if (Character.isLetter(peek))
                        throw new IOException("DEC: Scanner Error in line : " + line);

                    Decimal decimal_obj = new Decimal(decimal);
                    return decimal_obj;
                }

            }
            if (Character.isLetter(peek))
                throw new IOException("NUM: Scanner Error in line : " + line);

            Num num_obj = new Num(value);
            return num_obj;

        }

        else if (Character.isLetter(peek)) {
            String s = "";
            while (Character.isLetterOrDigit(peek)) {
                s += peek;
                peek = (char) System.in.read();
            }

            Word word_obj = (Word) words.get(s);
            if (word_obj != null) {

                return word_obj;
            } else {
                word_obj = new Word(Tag.ID, s);
                words.put(s, word_obj);
                return word_obj;
            }
            
        } else {

            String s = "";
            s += peek;

            Word word_obj = (Word) words.get(s);
            if (word_obj != null) {
                peek = (char) System.in.read();
                return word_obj;
            } else {
                
                Token t = new Token(peek);
                
                peek = ' ';
                return t;
            }
        }
    }
}
