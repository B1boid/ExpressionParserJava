package parser;

import AllExceptions.ParsingException;
import expression.*;

public class ExpressionParser implements Parser {
    private Tokenizer tokenizer;

    public TripleExpression parse(String expression) throws ParsingException {
        tokenizer = new Tokenizer(expression);
        return parseExpression(false, 0);
    }

    private int getPriority(Token currentToken) {
        if (currentToken == Token.DIVIDE || currentToken == Token.MULTIPLY) {
            return 2;
        }
        return 1;
    }

    private TripleExpression parseExpressionObject() throws ParsingException {
        switch (tokenizer.nextToken()) {
            case OPENING_BRACKET:
                TripleExpression inBracketsExpression = parseExpression(true, 0);
                if (tokenizer.nextToken() != Token.CLOSING_BRACKET) {
                    throw new ParsingException(
                            "Closing bracket expected,but another symbol found at position ",
                            tokenizer.getPosition());
                }
                return inBracketsExpression;
            case VARIABLE:
                return new Variable(tokenizer.getVariable());
            case CONST:
                return new Const(tokenizer.getConstant());
            case NEGATE:
                return new CheckedNegate(parseExpressionObject());
            case HIGH:
                return new CheckedHigh(parseExpressionObject());
            case LOW:
                return new CheckedLow(parseExpressionObject());
            default:
                throw new ParsingException(
                        "Variable,const,unary operation or open bracket expected,but another symbol found at position ",
                        tokenizer.getPosition());
        }
    }

    private TripleExpression parseExpression(boolean isRecursive, int minPriority) throws ParsingException {
        TripleExpression leftPart = parseExpressionObject();
        while (true) {
            Token currentToken = tokenizer.nextToken();
            if (currentToken == Token.END) {
                return leftPart;
            } else if (currentToken == Token.CLOSING_BRACKET && isRecursive) {
                tokenizer.prevToken();
                return leftPart;
            } else if (currentToken == Token.MULTIPLY || currentToken == Token.DIVIDE ||
                    currentToken == Token.ADD || currentToken == Token.SUBTRACT) {
                if (getPriority(currentToken) <= minPriority) {
                    tokenizer.prevToken();
                    return leftPart;
                }
            } else {
                throw new ParsingException(
                        "Closing bracket or binary operation expected,but another symbol found at position ",
                        tokenizer.getPosition());
            }
            TripleExpression rightPart = parseExpression(isRecursive, getPriority(currentToken));
            switch (currentToken) {
                case MULTIPLY:
                    leftPart = new CheckedMultiply(leftPart, rightPart);
                    break;
                case DIVIDE:
                    leftPart = new CheckedDivide(leftPart, rightPart);
                    break;
                case ADD:
                    leftPart = new CheckedAdd(leftPart, rightPart);
                    break;
                case SUBTRACT:
                    leftPart = new CheckedSubtract(leftPart, rightPart);
                    break;
            }
        }
    }
}
