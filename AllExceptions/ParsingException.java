package AllExceptions;

public class ParsingException extends Exception {
    public ParsingException(String message,int position) {
        super(message+(position+1));
    }

   /* static protected String positionShow(int index) {
        StringBuilder pointer = new StringBuilder();
        for (int i = 0; i < index; i++) {
            pointer.append(' ');
        }
        pointer.append('|');
        return pointer.toString();
    }*/

}