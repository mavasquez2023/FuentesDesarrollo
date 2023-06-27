/**
 * 
 */
package conector.configuracion;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import conector.configuracion.excepciones.ConfiguracionArchivoNoEncontradoException;
import conector.configuracion.excepciones.ConfiguracionParametroNoEncontradoException;
import conector.configuracion.excepciones.ConfiguracionParametroTipoException;


/**
 * Obtiene configuración desde un archivo properties ubicado en el classpath
 * (por ejemplo, dentro del EAR).
 * 
 * @author amartoq@microsystem.cl
 */
public class ClasspathConfig {
    /** Serial Version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Nombre del archivo ubicable en el classpath.
     */
    private String nombreArchivo;

    /**
     * Properties con los parámetros ya cargados.
     */
    private Properties properties;

    /**
     * Crea el objeto para obtener configuraciones.
     * 
     * @param nombreArchivo
     *            nombre del archivo ubicable en el classpath, por ejemplo:
     *            "/configuracion.properties".
     * @throws ConfiguracionArchivoNoEncontradoException
     *             si el archivo no existe o no puede leerse en el classpath.
     */
    public ClasspathConfig(String nombreArchivo) throws ConfiguracionArchivoNoEncontradoException {
        this.nombreArchivo = nombreArchivo;
        leerArchivo();
    }
    
    public ClasspathConfig(String[] values) throws IllegalArgumentException{
        if( values.length % 2 != 0 )
            throw new IllegalArgumentException( "One value is missing.");
        properties = new Properties();
        for( int i = 0; i < values.length; i += 2 )
        {
            properties.setProperty( values[i], values[i+1] );
        }
    }

    /**
     * Almacena el contenido de la configuración en un Properties.
     * 
     * @throws ConfiguracionArchivoNoEncontradoException
     *             si el archivo no existe o no puede leerse en el classpath.
     */
    protected void leerArchivo() throws ConfiguracionArchivoNoEncontradoException {
        try {
            properties = new Properties();
            InputStream inputStream = ClasspathConfig.class.getResourceAsStream(nombreArchivo);

            if (inputStream == null) {
                throw new ConfiguracionArchivoNoEncontradoException(null, nombreArchivo);
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new ConfiguracionArchivoNoEncontradoException(e, nombreArchivo);
        }
    }

    /**
     * Retorna el valor String de un parámetro dentro del archivo.
     * 
     * @param nombreParametro
     *            nombre del parámetro.
     * @return el valor del parámetro como un String.
     * @throws ConfiguracionParametroNoEncontradoException
     *             si el parámetro no está definido en el archivo.
     */
    public String getString(String nombreParametro) throws ConfiguracionParametroNoEncontradoException {
        String valor = (String) properties.get(nombreParametro);
        if (valor != null) {
            return valor;
        }

        throw new ConfiguracionParametroNoEncontradoException(nombreArchivo, nombreParametro);
    }

    /**
     * Retorna el valor int de un parámetro dentro del archivo.
     * 
     * @param nombreParametro
     *            nombre del parámetro.
     * @return el valor del parámetro como un int.
     * @throws ConfiguracionParametroNoEncontradoException
     *             si el parámetro no está definido en el archivo.
     * @throws ConfiguracionParametroTipoException
     *             si el parámetro no puede ser convertido a int.
     */
    public int getInt(String nombreParametro) throws ConfiguracionParametroNoEncontradoException,
            ConfiguracionParametroTipoException {
        String valor = null;
        try {
            valor = getString(nombreParametro);
            return Integer.valueOf(valor).intValue();
        } catch (NumberFormatException e) {
            throw new ConfiguracionParametroTipoException(nombreArchivo, nombreParametro, "int", valor);
        }
    }

    /**
     * Retorna el valor long de un parámetro dentro del archivo.
     * 
     * @param nombreParametro
     *            nombre del parámetro.
     * @return el valor del parámetro como un long.
     * @throws ConfiguracionParametroNoEncontradoException
     *             si el parámetro no está definido en el archivo.
     * @throws ConfiguracionParametroTipoException
     *             si el parámetro no puede ser convertido a long.
     */
    public long getLong(String nombreParametro) throws ConfiguracionParametroNoEncontradoException,
            ConfiguracionParametroTipoException {
        String valor = null;
        try {
            valor = getString(nombreParametro);
            return Long.valueOf(valor).longValue();
        } catch (NumberFormatException e) {
            throw new ConfiguracionParametroTipoException(nombreArchivo, nombreParametro, "int", valor);
        }
    }

    /**
     * Retorna el valor URL de un parámetro dentro del archivo.
     * 
     * @param nombreParametro
     *            nombre del parámetro.
     * @return el valor del parámetro como una URL.
     * @throws ConfiguracionParametroNoEncontradoException
     *             si el parámetro no está definido en el archivo.
     * @throws ConfiguracionParametroTipoException
     *             si el parámetro no puede ser convertido a URL.
     */
    public URL getURL(String nombreParametro) throws ConfiguracionParametroNoEncontradoException,
            ConfiguracionParametroTipoException {
        String valor = null;
        try {
            valor = getString(nombreParametro);
            return new URL(valor);
        } catch (MalformedURLException e) {
            throw new ConfiguracionParametroTipoException(nombreArchivo, nombreParametro, "URL", valor);
        }
    }
}
