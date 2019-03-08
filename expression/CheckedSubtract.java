package expression;

import AllExceptions.OverflowException;

import static expression.OverflowCheck.subtractOverflowCheck;

public class CheckedSubtract extends Operation {
    public CheckedSubtract(final TripleExpression x, final TripleExpression y) {
        super(x, y);
    }

    protected int apply(final int x, final int y) throws OverflowException {
        subtractOverflowCheck(x, y);
        return x - y;
    }
}