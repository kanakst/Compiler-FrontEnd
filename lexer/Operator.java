package lexer;

public class Operator extends Word {

    public final String operatorType;
    
    public Operator(int i, String s) {
	super(i,s); // calling Word constructor
	operatorType = s;
    }

    public String toString() {
	return operatorType;
    }

    public static final Operator
	assign = new Operator(Tag.ASSIGN, ":="),//symbol based
	
	division = new Operator(Tag.DIVISION, "div"),//word, string type operator
	mod = new Operator(Tag.MOD, "mod"),          //word, string type operator
	
	minusSign = new Operator(Tag.MINUSSIGN, "-"),
	plusSign = new Operator(Tag.PLUSSIGN, "+"),
	multiplySign = new Operator(Tag.MULTIPLYSIGN, "*"),
	divideSign = new Operator(Tag.DIVIDESIGN, "/");
    //semicolonSign = new Operator(Tag.SEMICOLON, ";"); //also symbol based hmm
    
}
