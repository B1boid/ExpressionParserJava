package expression;

import AllExceptions.OverflowException;

import static expression.OverflowCheck.multiplyOverflowCheck;

public class CheckedMultiply extends Operation {
    public CheckedMultiply(final TripleExpression x, final TripleExpression y) {
        super(x, y);
    }

    protected int apply(final int x, final int y) throws OverflowException {
        multiplyOverflowCheck(x, y);
        return x * y;
    }
}