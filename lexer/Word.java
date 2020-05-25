package lexer;

public class Word extends Token{
    public final String lexeme;
    private int rangeOfOperator;
    public Word(int i, String s){
	super(i);
	rangeOfOperator = i;
	lexeme = new String(s);
    }
    public String toString(){
	//based on tag value determinethe type
	/*
	   if(rangeOfOperator < 200) {
	    return "Operator lool : " + lexeme;
	}
	
	else {
	    return "Word  : "+ lexeme;
	}
	*/
	return "Word    : " + lexeme;
    }

    //some of the reserved words
    /*public static final Word
	assign = new Word(Tag.ASSIGN,":=");
    */	
}
