package cl.araucana.independientes.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UploadFileFTP {

    public static void subirArchivo(String ftpServer, String user, String password, String fileName, String source, String destino ) throws MalformedURLException, IOException {

        String directorio = destino;

        if (ftpServer != null && fileName != null && source != null) {
            StringBuffer sb = new StringBuffer( "ftp://" );

            if (user != null && password != null) 
            {
                sb.append( user );
                sb.append( ':' );
                sb.append( password );
                sb.append( '@' );
            }

            sb.append( ftpServer );
            sb.append( directorio );
            sb.append( fileName );
            sb.append( ";type=i" );

            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;

            try 
            {
                URL url = new URL( sb.toString() );
                URLConnection urlc = url.openConnection();

                bos = new BufferedOutputStream( urlc.getOutputStream() );
                bis = new BufferedInputStream( new FileInputStream( source ) );

                int i;
                while ((i = bis.read()) != -1) 
                {
                    bos.write( i );
                }

            }catch(IOException e){
                e.printStackTrace();
            }

            finally {
                if (bis != null)
                    try {
                        bis.close();
                    }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                if (bos != null)
                    try {
                        bos.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
            }
        } else {
            System.out.println( "Error en UPLOADFILEFTP, tipo de archivo no válido." );

        }
    }
}
