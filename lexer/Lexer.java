package lexer;

import java.io.*;
import java.util.*;

public class Lexer{

    public char peek = ' ';
    public int line = 1;

    Hashtable<String, Token>  words = new Hashtable<String, Token>();

    void reserve(Word w){
	words.put(w.lexeme, w);
    }
    
    //add more reserved words here
    public Lexer(){
	reserve(new Word(Tag.IF,  "if")  );
	reserve(new Word(Tag.ELSE, "else") );

	reserve(new Word(Tag.WHILE, "while"));
	reserve(new Word(Tag.BEGIN, "begin"));

	reserve(new Word(Tag.THEN, "then"));
	reserve(new Word(Tag.DO, "do"));
	reserve(new Word(Tag.END, "end"));
	
	reserve(new Word(Tag.LEFTBRACKET, "("));
	reserve(new Word(Tag.RIGHTBRACKET, ")"));

	//reserve(new Word(Tag.SEMICOLON, ";"));
	    
	reserve(Operator.assign);
	reserve(Operator.division);
	reserve(Operator.mod);
	reserve(Operator.minusSign);
	reserve(Operator.plusSign);
	reserve(Operator.multiplySign);
	reserve(Operator.divideSign);
	//reserve(Operator.semicolonSign); // ???
    }
    
    // void ? or returning token?
    public Token scan() throws IOException{

	//2.6.1. Removing of White Spaces and Comments
	if(peek == ' ') { //checking this because, expressions can be given without spaces
	    while(true){
		peek = (char) System.in.read();
		if(peek == ' ' || peek == '\t')
		    continue;
		else if(peek == '\n') {
		    line++;
		    return null; // check again this!!!
		}
		else break;
	    }
	}
	// Now, we got rid of the white spaces
	if(peek == ':') {
	    peek = (char)System.in.read();
	    if(peek == '=') {
		peek = ' ';
		return Operator.assign;
	    }
	    else
		return new Token(':'); // EDIT THIS ONE!!! TOKEN OR OPERATOR???
	}
	//CASE FOR DEALING WITH INTEGER VALUES
	// ALSO NEED TO INCLUDE THE FLOAT VALUES
	if(Character.isDigit(peek)){
	    int value = 0;
	    while(Character.isDigit(peek)){
		value *= 10;
		value += Character.getNumericValue(peek);
		peek = (char)System.in.read();
	    }

	    if(peek == '.'){
		peek = (char)System.in.read();//reading one more input rught after the dot
		if(Character.isDigit(peek)){
		    double divider = 1;
		    double afterDot = 0;
		    double decimal = (double)value; //casting of 'value'
		    while(Character.isDigit(peek)){
			divider *= 10;
			afterDot *= 10;
			afterDot =  (double)Character.getNumericValue(peek);
			decimal += afterDot/divider;
			peek = (char)System.in.read();
		    }
		    //Should check:
		    if (Character.isLetter(peek))
			throw new IOException("DEC: Scanner Error");
		    

		    //and if it is okay, then
		    //using else statement create token and return it

		    //also: do I need to peek = ' '; here?
		    Decimal decimal_obj = new Decimal(decimal);
		    return decimal_obj;
		}
		
	    }
	    if (Character.isLetter(peek))
		throw new IOException("NUM: Scanner Error");
	    
	    Num num_obj = new Num(value);
	    return num_obj;
	    
	}

	// now checking the case dealing with

	//2.6.4 Recognizing Keywords and Identifiers

	else if(Character.isLetter(peek)){
	    String s = "";
	    while(Character.isLetterOrDigit(peek)){
		s+=peek;
		peek = (char)System.in.read();
	    }
	    
	    Word word_obj =(Word) words.get(s);
	    if(word_obj != null) {
		//System.out.println("GOT IT FROM HASHTABLE");
		return word_obj;
	    }
	    else{
		word_obj = new Word(Tag.ID, s);
		words.put(s, word_obj);
		return word_obj;
	    }
	}
		
	else{

	    String s = "";
	    s += peek;

	    Word word_obj = (Word) words.get(s);
	    if(word_obj != null) {
		peek = (char)System.in.read();
		return word_obj;
	    }
	    else {
		//if(peek == '\n') return null;
		Token t = new Token(peek);
		//System.out.println("peek is : " + peek);
		peek = ' ';
		return t;
	    }
	}
	

	
    }

}
