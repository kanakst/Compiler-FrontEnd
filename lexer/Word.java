package lexer;

public class Word extends Token {

    public final String lexeme;

    public Word(int i, String s) {

        super(i);
        lexeme = new String(s);
    }

    public String toString() {

        return lexeme;
    }
}
