package cl.laaraucana.rendicionpagonomina.exception;

 
public class MiException extends Exception {

    private ErrorMessage errorMessage;

    public MiException(ErrorMessage errorMessage) {
        super(errorMessage.getErrormessage());
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
