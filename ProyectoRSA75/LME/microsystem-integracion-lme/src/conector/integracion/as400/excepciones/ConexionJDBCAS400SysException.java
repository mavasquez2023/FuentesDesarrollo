/**
 * 
 */
package conector.integracion.as400.excepciones;

import conector.excepciones.SysException;

/**
 * Representa un error al conectarse al AS400.
 * 
 * @author sistemas@microsystem.cl
 */
public class ConexionJDBCAS400SysException extends SysException {
    /** Serial Version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Crea una nueva excepción de conexión.
     * 
     * @param throwable
     *            excepción causante del error.
     * @param nombreDatasource
     *            nombre del datasource.
     */
    public ConexionJDBCAS400SysException(Throwable throwable, String nombreDatasource) {
        super(throwable, "nombreDatasource: " + nombreDatasource);
    }

    /**
     * Crea una nueva excepción de conexión.
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
     */
    public ConexionJDBCAS400SysException(Throwable throwable, String host, String user, String pass, String libs) {
        super(throwable, "host: " + host + " user: " + user + " pass: XXXXXXX libs: " + libs);
    }
}
