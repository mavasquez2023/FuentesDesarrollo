/**
 * 
 */
package cl.recursos;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import com.ibm.as400.access.*;

/**
 * @author Usist24
 *
 */
public class FTPAS400 {
	String sistema = "146.83.1.2";
    String usuario = "schema";
    String password = "schema";
	 /**
    *
    * Descripción: carga archivo desde server a IBM
    *
    * Registro de Versiones:<ul>
    * <li>23/09/2011 [emoya - schema ltda.]: Versión Inicial</li>
    * </ul><p>
    *
    * @param file
    * @param rutaOrigen
    * @param rutaDestino
    * @return
    * @throws AS400SecurityException
    * @throws IOException boolean
    */
   public boolean cargarArchivo(String file,String rutaOrigen,String rutaDestino) throws AS400SecurityException, IOException {
       boolean estado = false;
       AS400 as400 = new AS400 (sistema, usuario, password);
       AS400FTP ftp = new AS400FTP(as400);
       System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< rutaOrigen -> " + rutaOrigen +", file="+file); 
       File fileObject = new File(rutaOrigen + file);
       System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Archivo Encontrado"); 
       estado = ftp.put(fileObject, rutaDestino + file);
       System.out.println("cargarArchivoIFS -> cargando archivo v?a FTP estado = " + estado); 
       ftp.disconnect();
       return estado;
   }
   public static void main(String[] args) {
		
		//Archivo filein= new Archivo();
		System.out.println("entrando");
		//Archivo files= new Archivo();
		try {
			FTPAS400 ftp= new FTPAS400();
			ftp.cargarArchivo("prueba.txt", "/reports/Nomcobcr/", "/QDLS/intproc/");
			
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
}
