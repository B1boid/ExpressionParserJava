package expression;


public class Variable implements TripleExpression {
    private final String name;

    public Variable(final String x) {
        name = x;
    }

    public int evaluate(final int x, final int y, final int z) {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                return 0;
        }
    }

}