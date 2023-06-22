/**
 * 
 */
package cl.laaraucana.satelites.Utils;

/**
 * @author J-Factory
 *
 */
import java.io.FileInputStream;
import java.io.IOException;
 
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import cl.araucana.cotcarserv.utils.CertificadoConst;

 
public class FTPUtils {
	private static Logger log = Logger.getLogger(FTPUtils.class);
	public static void main(String[] args) {
		FTPUtils ftp= new FTPUtils();
		ftp.enviarArchivo("/tmp/services/CesarTrabEmp.txt");
	}
    public static void enviarArchivo(String filename) {
 
        // Creando nuestro objeto ClienteFTP
        FTPClient client = new FTPClient();
 
        // Datos para conectar al servidor FTP
        String ftp = CertificadoConst.RES_CERTIFICADOS.getString("ftp.server.ip"); // También puede ir la IP
        String user = CertificadoConst.RES_CERTIFICADOS.getString("ftp.server.usuario");
        String password = CertificadoConst.RES_CERTIFICADOS.getString("ftp.server.clave");
        log.info("Conectando a FTP server: " + ftp);
        try {
            // Conactando al servidor
            client.connect(ftp);
  
 
            // Logueado un usuario (true = pudo conectarse, false = no pudo
            // conectarse)
            if (client.login(user, password))
            {
            	log.info("Conectando exitosamente");
                client.setFileType(FTP.BINARY_FILE_TYPE, FTP.BINARY_FILE_TYPE);
                client.setFileTransferMode(FTP.BINARY_FILE_TYPE);
                client.enterLocalPassiveMode();
     
                
                FileInputStream fis = new FileInputStream(filename);
     
                // Guardando el archivo en el servidor
               String nombreDestino= filename.substring(filename.lastIndexOf("/")+1);
               if (client.changeWorkingDirectory(CertificadoConst.RES_CERTIFICADOS.getString("ftp.server.carpeta"))){
                    if (client.storeFile(nombreDestino, fis))
                        log.info("Se ha grabado el fichero: " + filename);
                    else
                    	log.info("No se ha grabado el fichero" + filename);
               } else{
            	   log.info("No se ha grabado el fichero" + filename);
               }
                // Cerrando sesión
                client.logout();
     
                // Desconectandose con el servidor
                client.disconnect();
            }   else{
            	log.info("Error Conectando FTP!");
            }
        } catch (IOException ioe) {
            log.warn(ioe.getMessage());
        }
   }
 
}
