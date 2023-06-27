/**
 * 
 */
package conector.excepciones;

/**
 * Representa un problema con los datos o excepción de negocio, es decir, un
 * error que es causa por los datos ingresados por el usuario.
 * 
 * La mayoría de las veces la solución pasa por que el usuario corriga los datos
 * y repita la operación.
 * 
 * @author amartoq@microsystem.cl
 */
public class NegException extends BaseException {
    /** Serial Version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Crea una nueva excepción de negocio.
     * 
     * @param mensaje
     *            mensaje para el usuario.
     */
    public NegException(String mensaje) {
        super(null, mensaje);
    }
}
