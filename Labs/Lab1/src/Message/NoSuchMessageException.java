package Message;

public class NoSuchMessageException extends Exception {
    public NoSuchMessageException(String errorMessage) {
        super(errorMessage);
    }
}
