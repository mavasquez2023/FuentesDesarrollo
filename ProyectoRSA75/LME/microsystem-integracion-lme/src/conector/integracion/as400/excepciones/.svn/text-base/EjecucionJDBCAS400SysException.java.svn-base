/**
 * 
 */
package conector.integracion.as400.excepciones;

import conector.excepciones.SysException;

/**
 * Representa un error al ejecutar un programa en AS400.
 * 
 * @author sistemas@microsystem.cl
 */
public class EjecucionJDBCAS400SysException extends SysException {
    /** Serial Version UID. */
    private static final long serialVersionUID = 1L;
    
    /**
     * @param throwable
     * @param mensaje
     */
    public EjecucionJDBCAS400SysException(Throwable throwable, String mensaje) {
        super(throwable, mensaje);
    }

    /**
     * Crea una nueva excepción de ejecución.
     * 
     * @param throwable
     *            excepción causa del error.
     * @param host
     *            nombre de host o dirección IP.
     * @param user
     *            nombre de usuario.
     * @param pass
     *            clave de usuario.
     * @param libs
     *            bibliotecas usadas.
     * @param rutaPrograma
     *            ruta del programa ejecutado.
     */
    public EjecucionJDBCAS400SysException(Throwable throwable, String host, String user, String pass, String libs,
            String rutaPrograma) {
        super(throwable, "host: " + host + " user: " + user + " pass: XXXXXXX libs: " + libs + " rutaPrograma: "
                + rutaPrograma);
    }
}
