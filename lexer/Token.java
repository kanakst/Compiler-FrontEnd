package lexer;

public class Token{
    public final int tag;
    public Token(int t){ tag = t;}
    //implement
    public String toString(){
	return ""+(char)tag; //is it possible?
    }
}
