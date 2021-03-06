package lexer;

public class Decimal extends Token {

    public final double value;

    public Decimal(double v) {
        super(Tag.DECIMAL);
        value = v;
    }

    public String toString() {
        return "" + value;
    }
}
