package cl.araucana.aporte.core;

/**
 * Excepción general para EJB
 * @author asaavedra
 *
 */
public class LocalEJBException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 7464772853091630204L;

    private String message;
    private RuntimeException cause;

    public LocalEJBException(String message, RuntimeException originalException){
        this.message = message;
        this.cause = originalException;
    }

    public Throwable getCause() {
        return cause;
    }

    public String getMessage() {
        return message;
    }



}
