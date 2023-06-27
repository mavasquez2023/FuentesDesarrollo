package cl.araucana.aporte.core;

public class RemoteEJBLocatorException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 6333036728003266477L;

    public RemoteEJBLocatorException(Throwable causa){
        this.initCause(causa);
    }

}
