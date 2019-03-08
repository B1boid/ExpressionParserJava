package AllExceptions;

public class ParsingException extends Exception {
    public ParsingException(String message,int position) {
        super(message+(position+1));
    }
}
