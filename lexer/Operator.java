package lexer;

public class Operator extends Word {

    public final String operatorType;

    public Operator(int i, String s) {
        super(i, s);
        operatorType = s;
    }

    public String toString() {
        return operatorType;
    }

    public static final Operator assign = new Operator(Tag.ASSIGN, ":="),
            division = new Operator(Tag.DIVISION, "div"),
            mod = new Operator(Tag.MOD, "mod"),
            minusSign = new Operator(Tag.MINUSSIGN, "-"),
            plusSign = new Operator(Tag.PLUSSIGN, "+"),
            multiplySign = new Operator(Tag.MULTIPLYSIGN, "*"),
            divideSign = new Operator(Tag.DIVIDESIGN, "/");
}
