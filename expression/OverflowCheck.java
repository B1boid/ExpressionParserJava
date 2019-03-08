package expression;

import AllExceptions.ArithmeticException;
import AllExceptions.OverflowException;

public class OverflowCheck {
    public static void multiplyOverflowCheck(int x, int y) throws OverflowException {
        if (x > 0 && y > 0 && Integer.MAX_VALUE / x < y) {
            throw new OverflowException();
        }
        if (x > 0 && y < 0 && Integer.MIN_VALUE / x > y) {
            throw new OverflowException();
        }
        if (x < 0 && y > 0 && Integer.MIN_VALUE / y > x) {
            throw new OverflowException();
        }
        if (x < 0 && y < 0 && Integer.MAX_VALUE / x > y) {
            throw new OverflowException();
        }
    }

    public static void divideOverflowCheck(int x, int y) throws OverflowException, ArithmeticException {
        if (y == 0) {
            throw new ArithmeticException("division by zero");
        }
        if (y == -1 && x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
    }

    public static void addOverflowCheck(int x, int y) throws OverflowException {
        if (x > 0 && Integer.MAX_VALUE - x < y) {
            throw new OverflowException();
        }
        if (x < 0 && Integer.MIN_VALUE - x > y) {
            throw new OverflowException();
        }
    }

    public static void subtractOverflowCheck(int x, int y) throws OverflowException {
        if (x >= 0 && y < 0 && x - Integer.MAX_VALUE > y) {
            throw new OverflowException();
        }
        if (x <= 0 && y > 0 && Integer.MIN_VALUE - x > -y) {
            throw new OverflowException();
        }
    }

    public static void minusOverflowCheck(int x) throws OverflowException {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
    }

}
