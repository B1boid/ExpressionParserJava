package expression;

import AllExceptions.OverflowException;

import static expression.OverflowCheck.minusOverflowCheck;

public class CheckedNegate extends UnaryOperation {
    public CheckedNegate(final TripleExpression x) {
        super(x);
    }

    protected int apply(final int x) throws OverflowException {
        minusOverflowCheck(x);
        return -x;
    }
}