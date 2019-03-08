package expression;

import AllExceptions.ArithmeticException;
import AllExceptions.OverflowException;

import static expression.OverflowCheck.divideOverflowCheck;

public class CheckedDivide extends Operation {
    public CheckedDivide(final TripleExpression x, final TripleExpression y) {
        super(x, y);
    }

    protected int apply(final int x, final int y) throws ArithmeticException, OverflowException {
        divideOverflowCheck(x, y);
        return x / y;
    }
}