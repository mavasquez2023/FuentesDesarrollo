package conector.excepciones;

/**
 * Base para todas las excepciones de microsystem. No se debe utilizar esta
 * clase, usar SysException ó NegException según corresponda.
 * 
 * @author amartoq@microsystem.cl
 */
public class BaseException extends Exception {
    /** Serial Version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Crea una nueva excepción base.
     * 
     * @param throwable
     *            excepción origen.
     * @param mensaje
     *            mensaje a desplegar.
     */
    protected BaseException(Throwable throwable, String mensaje) {
        super(mensaje, throwable);
    }
}
