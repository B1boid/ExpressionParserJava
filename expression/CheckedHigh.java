package expression;

public class CheckedHigh extends UnaryOperation {
    public CheckedHigh(final TripleExpression x) {
        super(x);
    }

    protected int apply(final int x) {
        return Integer.highestOneBit(x);
    }
}