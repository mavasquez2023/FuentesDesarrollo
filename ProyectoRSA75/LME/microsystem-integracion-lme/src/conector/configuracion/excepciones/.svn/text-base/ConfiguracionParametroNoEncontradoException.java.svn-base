/**
 * 
 */
package conector.configuracion.excepciones;

/**
 * Excepción que indica algún problema con un parámetro en la configuración. El
 * administrador del sistema debe corregir la configuración para que la
 * aplicación vuelva a funcionar.
 * 
 * @author amartoq@microsystem.cl
 */
public class ConfiguracionParametroNoEncontradoException extends ConfiguracionException {
    /** Serial Version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Crea una excepción que indica que el parámetro no está definido.
     * 
     * @param nombreArchivo
     *            nombre del archivo donde debiese estar definido el parámetro.
     * @param nombreParametro
     *            nombre del parámetro con problemas.
     */
    public ConfiguracionParametroNoEncontradoException(String nombreArchivo, String nombreParametro) {
        super(null, "El parámetro " + nombreParametro + " en el recurso " + nombreArchivo + " no está definido");
    }
}
