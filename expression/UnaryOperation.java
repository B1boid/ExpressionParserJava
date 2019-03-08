package expression;

import AllExceptions.EvaluatingException;

public abstract class UnaryOperation implements TripleExpression {
    private final TripleExpression operand;

    protected UnaryOperation(final TripleExpression x) {
        operand = x;
    }

    protected abstract int apply(final int x) throws EvaluatingException;

    public int evaluate(final int x, final int y, final int z) throws EvaluatingException {
        return apply(operand.evaluate(x, y, z));
    }
}