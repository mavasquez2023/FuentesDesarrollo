/*
 * Creado el 24-08-2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.recursos;

import java.io.IOException;

import com.enterprisedt.net.ftp.FTPClient;
import com.enterprisedt.net.ftp.FTPConnectMode;
import com.enterprisedt.net.ftp.FTPException;
import com.enterprisedt.net.ftp.FTPTransferType;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class FTPCliente extends FTPClient{
	 private FTPClient ftp;

		
	public FTPCliente(String host, String usuario, String password) throws IOException, FTPException{
		super(host, 21);
		debugResponses(true);
         //login
        login(usuario, password);
	}
	 
	public boolean setType(String tipo) throws IOException, FTPException{
				if (tipo.equalsIgnoreCase("BINARY")) {
			    ftp.setType(FTPTransferType.BINARY);
			}
			else if (tipo.equalsIgnoreCase("ASCII")) {
			    ftp.setType(FTPTransferType.ASCII);
			}
			else {
			    System.out.println("Unknown transfer type: " + tipo);
			    return false;
			}
			return true;
	}
	
	public void setConnectMode(String mode){
		if (mode.equalsIgnoreCase("ACTIVE")) {
            ftp.setConnectMode(FTPConnectMode.ACTIVE);
        }else {
        	 ftp.setConnectMode(FTPConnectMode.PASV);
        }
	}
		
	public String [] ls(String arg0) throws IOException, FTPException{
		return ftp.dir(arg0);
	}
	
	public static void main(String args[]) {
		System.out.println("Entre a Client");
        // we want remote host, user name and password
        if (args.length < 8) {
            System.out.println(args.length);
            usage();
            System.exit(1);
        }
        try {

            // assign args to make it clear
            String host = args[0];
            String user = args[1];
            String password = args[2];
            String filename = args[3];
            String directory = args[4];
            String mode = args[5];
            String connMode = args[6];
            String debugMode = args[7];

            // connect and test supplying port no.
            FTPClient ftp = new FTPClient(host, 21);
            
            ftp.setDebugMode(debugMode.equals("true"));

            // switch on debug of responses
            ftp.debugResponses(true);

            ftp.login(user, password);

            // BINARY o ASCII transfer?
            if (args[5].equalsIgnoreCase("BINARY")) {
                ftp.setType(FTPTransferType.BINARY);
            }
            else if (args[5].equalsIgnoreCase("ASCII")) {
                ftp.setType(FTPTransferType.ASCII);
            }
            else {
                System.out.println("Unknown transfer type: " + args[5]);
                System.exit(-1);
            }

            // PASV or ACTIVE?
            if (args[6].equalsIgnoreCase("PASV")) {
                ftp.setConnectMode(FTPConnectMode.PASV);
            }
            else if (args[6].equalsIgnoreCase("ACTIVE")) {
                ftp.setConnectMode(FTPConnectMode.ACTIVE);
            }
            else {
                System.out.println("Unknown connect mode: " + args[6]);
                System.exit(-1);
            }

            // change dir
            ftp.chdir(directory);

            // put a local file to remote host
            int indice = filename.lastIndexOf("/");

            if (indice == -1) {
				indice = filename.lastIndexOf("\\");
			}

			String filenameaux = null;

			if (indice == -1) {
				filenameaux = filename;
			} else {
            	filenameaux = filename.substring(indice + 1);
			}

            System.out.println("filenameaux->" + filenameaux);
            ftp.put(filename, filenameaux);

            ftp.quit();
        }
        catch (IOException ex) {
			ex.printStackTrace();
            System.out.println("Caught exception: " + ex.getMessage());
        }
        catch (FTPException ex) {
			ex.printStackTrace();
            System.out.println("Caught exception: " + ex.getMessage());
        }
    }


    /**
     *  Basic usage statement
     */
    public static void usage() {

        System.out.println("Usage: ");
        System.out.println("com.enterprisedt.net.ftp.FTPClientTest " +
                           "remotehost user password filename directory " +
                           "(ascii|binary) (active|pasv)");
    }
}
