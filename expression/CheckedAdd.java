package expression;

import AllExceptions.OverflowException;

import static expression.OverflowCheck.addOverflowCheck;

public class CheckedAdd extends Operation {
    public CheckedAdd(final TripleExpression x, TripleExpression y) {
        super(x, y);
    }

    protected int apply(final int x, final int y) throws OverflowException {
        addOverflowCheck(x, y);
        return x + y;
    }
}