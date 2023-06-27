/**
 * 
 */
package conector.configuracion.excepciones;

/**
 * Excepción que indica el valor de un parámetro de configuración no corresponde
 * al tipo de dato esperado. El administrador del sistema debe corregir la
 * configuración para que la aplicación vuelva a funcionar.
 * 
 * @author amartoq@microsystem.cl
 */
public class ConfiguracionParametroTipoException extends ConfiguracionException {
    /** Serial Version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Crea una excepción que indica el valor del parámetro no corresponde al
     * tipo de dato esperado.
     * 
     * @param nombreArchivo
     *            nombre del archivo donde está definido el parámetro.
     * @param nombreParametro
     *            nombre del parámetro con problemas.
     * @param tipoDato
     *            tipo de dato esperado para el parámetro.
     * @param valor
     *            valor del parámetro.
     */
    public ConfiguracionParametroTipoException(String nombreArchivo, String nombreParametro, String tipoDato,
            String valor) {
        super(null, "El parámetro " + nombreParametro + " en el recurso " + nombreArchivo + " no corresponde al tipo "
                + tipoDato + ": [" + valor + "]");
    }
}
