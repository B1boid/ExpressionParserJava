package expression;

public class CheckedLow extends UnaryOperation {
    public CheckedLow(final TripleExpression x) {
        super(x);
    }

    protected int apply(final int x) {
        return Integer.lowestOneBit(x);
    }
}