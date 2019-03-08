package parser;


import AllExceptions.ParsingException;

import java.util.Map;

public class Tokenizer {

    private int position;
    private String expression;
    private Token previousToken;
    private Token currentToken;
    private static Map<String, Token> TOKEN_CHECKER = Map.of("low", Token.LOW, "high", Token.HIGH,
            "x", Token.VARIABLE, "y", Token.VARIABLE, "z", Token.VARIABLE);

    public Tokenizer(String expression) {
        this.expression = expression;
        position = -1;
        previousToken = null;
        currentToken = Token.START;
    }

    public int getConstant() throws ParsingException {
        StringBuilder currentConst = new StringBuilder();
        if (expression.charAt(position) == '-') {
            currentConst.append("-");
            skipWhitespaces();
        }
        if (Character.isDigit(expression.charAt(position))) {
            while (position < expression.length() && Character.isDigit(expression.charAt(position))) {
                currentConst.append(expression.charAt(position));
                position++;
            }
        }
        position--;
        try {
            return Integer.parseInt(currentConst.toString());
        } catch (NumberFormatException e) {
            throw new ParsingException("Integer constant expected,but fount another at position ", position);
        }
    }

    public String getVariable() {
        return String.valueOf(expression.charAt(position));
    }

    public int getPosition() {
        return position;
    }

    public String getExpression() {
        return expression;
    }

    public void prevToken() {
        position--;
        currentToken = previousToken;
    }

    private boolean isConstant() {
        int startPosition = position;
        skipWhitespaces();
        if (position < expression.length() && Character.isDigit(expression.charAt(position))) {
            position = startPosition;
            return true;
        }
        position = startPosition;
        return false;
    }

    private void switchToken(Token currentToken) {
        this.previousToken = this.currentToken;
        this.currentToken = currentToken;
    }

    private void skipWhitespaces() {
        do {
            position++;
        } while (position < expression.length() && Character.isWhitespace(expression.charAt(position)));
    }

    private String defineToken() {
        int l = position;
        while (position < expression.length() && Character.isLetter(expression.charAt(position))) {
            ++position;
        }
        int r = position;
        --position;
        return expression.substring(l, r);
    }

    public Token nextToken() {
        skipWhitespaces();
        if (position >= expression.length()) {
            switchToken(Token.END);
        } else {
            char currentChar = expression.charAt(position);
            switch (currentChar) {
                case '(':
                    switchToken(Token.OPENING_BRACKET);
                    break;
                case ')':
                    switchToken(Token.CLOSING_BRACKET);
                    break;
                case '*':
                    switchToken(Token.MULTIPLY);
                    break;
                case '/':
                    switchToken(Token.DIVIDE);
                    break;
                case '+':
                    switchToken(Token.ADD);
                    break;
                case '-':
                    if (currentToken == Token.CONST || currentToken == Token.VARIABLE
                            || currentToken == Token.CLOSING_BRACKET) {
                        switchToken(Token.SUBTRACT);
                    } else {
                        if (isConstant()) {
                            switchToken(Token.CONST);
                        } else {
                            switchToken(Token.NEGATE);
                        }
                    }
                    break;
                default:
                    if (Character.isDigit(currentChar)) {
                        switchToken(Token.CONST);
                    } else {
                        switchToken(TOKEN_CHECKER.getOrDefault(defineToken(), Token.ERROR));
                    }
            }
        }
        return currentToken;
    }
}