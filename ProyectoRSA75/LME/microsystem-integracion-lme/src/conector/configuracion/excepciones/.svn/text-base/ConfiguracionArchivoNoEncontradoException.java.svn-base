/**
 * 
 */
package conector.configuracion.excepciones;

/**
 * Excepción que indica que el archivo de configuración no se encuentra o no
 * puede leerse. El administrador del sistema debe corregir la configuración
 * para que la aplicación vuelva a funcionar.
 * 
 * @author amartoq@microsystem.cl
 */
public class ConfiguracionArchivoNoEncontradoException extends ConfiguracionException {
    /** Serial Version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Crea una excepción que indica la configuración no existe o no puede
     * leerse.
     * 
     * @param throwable
     *            excepción origen.
     * @param nombreArchivo
     *            nombre del recurso de configuración.
     */
    public ConfiguracionArchivoNoEncontradoException(Throwable throwable, String nombreArchivo) {
        super(throwable, "El archivo de configuración " + nombreArchivo + " no existe o no puede leerse.");
    }
}
