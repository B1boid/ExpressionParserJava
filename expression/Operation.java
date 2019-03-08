package expression;

import AllExceptions.EvaluatingException;

public abstract class Operation implements TripleExpression {
    private final TripleExpression leftOperand;
    private final TripleExpression rightOperand;

    protected Operation(final TripleExpression x, final TripleExpression y) {
        leftOperand = x;
        rightOperand = y;
    }

    protected abstract int apply(final int x, final int y) throws EvaluatingException;

    public int evaluate(final int x, final int y, final int z) throws EvaluatingException {
        return apply(leftOperand.evaluate(x, y, z), rightOperand.evaluate(x, y, z));
    }

}

