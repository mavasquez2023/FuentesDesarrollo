package cl.araucana.aporte.core;

public class BusinessException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 8604845854923752206L;

    public BusinessException(Throwable causa){
        initCause(causa);
    }

}
