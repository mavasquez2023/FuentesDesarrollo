/**
 * 
 */
package conector.test.manual;

import java.net.URL;

import conector.configuracion.ClasspathConfig;
import conector.configuracion.excepciones.ConfiguracionArchivoNoEncontradoException;
import conector.configuracion.excepciones.ConfiguracionException;


/**
 * 
 * 
 * @author amartoq@microsystem.cl
 */
public class Prueba1B {
    /** Serial Version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * @param args
     */
    public static void main(String[] args) {
        leerParametros();
    }

    public static void leerParametros() {
        try {
            /* ruta del archivo dentro del EAR */
            String ruta = "/as400.properties";

            /* creamos objeto de configuración */
            ClasspathConfig config = new ClasspathConfig(ruta);

            /* obtenemos los parámetros */
            String user = config.getString("user");
            int port = config.getInt("port");
            URL url = config.getURL("endp");

        } catch (ConfiguracionException e) {
            System.out.println("Hay un error en la configuración: " + e);
        }

    }
}
