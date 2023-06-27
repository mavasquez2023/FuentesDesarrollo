package conector.excepciones;

/**
 * Representa un error o excepción de sistema, es decir, un error que no es
 * causado por los datos ingresados por el usuario sino por una falla en algún
 * componente de sistema o programación.
 * 
 * La mayoría de las veces la solución pasa por que el usuario reintente la
 * operación o informe el problema al área de operaciones.
 * 
 * @author amartoq@microsystem.cl
 */
public class SysException extends BaseException {
    /** Serial Version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Crea una nueva excepción de sistema.
     * 
     * @param throwable
     *            excepción que causó el error.
     * @param mensaje
     *            mensaje para el operador.
     */
    public SysException(Throwable throwable, String mensaje) {
        super(throwable, mensaje);
        if (throwable != null) {
            throwable.printStackTrace();
        }
    }
}
