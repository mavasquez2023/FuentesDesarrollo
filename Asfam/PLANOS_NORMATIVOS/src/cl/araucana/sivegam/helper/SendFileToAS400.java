package cl.araucana.sivegam.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.CharConverter;
import com.ibm.as400.access.IFSFile;
import com.ibm.as400.access.IFSFileInputStream;
import com.ibm.as400.access.IFSFileOutputStream;
import com.ibm.as400.access.IFSFileReader;
import com.ibm.as400.access.IFSJavaFile;

public class SendFileToAS400 {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    private AS400 system;
    private int ccsid = 284;

    public SendFileToAS400() {
    }

    public SendFileToAS400(AS400 systemIn) {
        this.system = systemIn;
    }

    public SendFileToAS400(String server, String usuario, String password) {
        if (server != null && !"".equals(server)) {
            this.system = new AS400(server, usuario, password);
        }
    }

    public boolean copiarArchivoToAS400(String origen, String destino) {

        try {
            if (this.system != null) {
                AS400 systemIn = this.system;
                this.system = null;
                byte[] archivo = leerArchivoBin(origen);
                this.system = systemIn;
                return crearArchivo(destino, archivo);

            } else {
                throw new Exception("No se han definido credenciales para AS400");
            }
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public boolean crearArchivo(String pathfile, List texto) throws IOException {
        OutputStream out;
        try {
            if (system == null) {
                out = new FileOutputStream(pathfile);
            } else {
                out = new IFSFileOutputStream(system, pathfile, getCCSID());
            }
            PrintStream flujo = new PrintStream(out);
            escribirOutput(texto, flujo);
            flujo.close();
            out.close();
            return true;
        } catch (Exception e) {
//            logger.debug("CAI en crearArchivo()");
            e.printStackTrace();
            return false;
        }
    }

    public boolean crearArchivo(String pathfile, byte[] archivo) throws IOException {
        OutputStream out;
        try {
            if (system == null) {
                out = new FileOutputStream(pathfile);
            } else {
                out = new IFSFileOutputStream(system, pathfile, getCCSID());
            }
            PrintStream flujo = new PrintStream(out);
            if (escribirOutput(archivo, flujo)) {
                flujo.close();
                out.close();
                return true;
            }
            return false;
        } catch (Exception e) {
 //           logger.debug("CAI en crearArchivo()");
            e.printStackTrace();
            return false;
        }
    }

    public boolean crearArchivo(String pathfile, String texto) throws IOException {
        Vector vec = new Vector();
        vec.add(texto);
        return crearArchivo(pathfile, vec);
    }

    public boolean escribirOutput(List texto, PrintStream out) throws IOException {
        CharConverter conv = null;
        try {
            conv = new CharConverter(getCCSID());
            for (Iterator iter = texto.iterator(); iter.hasNext();) {
                String linea = (String) iter.next();
                if (system == null) {
                    out.println(linea);
                } else {
                    out.write(conv.stringToByteArray(linea));
                    out.write(conv.stringToByteArray("\n"));
                }
            }
            return true;
        } catch (Exception e) {
 //           logger.debug("CAI en escribirOutput()");
            e.printStackTrace();
            return false;
        }
    }

    public boolean escribirOutput(byte[] archivo, PrintStream out) throws IOException {
        try {
            out.write(archivo);
            return true;
        } catch (Exception e) {
  //          logger.debug("CAI en escribirOutput()");
            e.printStackTrace();
            return false;
        }
    }

    public void addLinea(String pathfile, String newline) throws IOException {
        Vector vec = new Vector();
        leerArchivo(pathfile, vec);
        if (vec.size() > 0) {
            int pos = vec.indexOf("</table>");
            if (pos > -1) {
                vec.insertElementAt(newline, pos);
            } else {
                vec.addElement(newline);
            }
        } else {
            if (newline.indexOf("<tr>") >= 0) {
                vec.addElement("<table>");
                vec.addElement(newline);
                vec.addElement("</table>");
            } else {
                vec.addElement(newline);
            }
        }
        crearArchivo(pathfile, vec);
    }

    public boolean existFile(String pathfile) {
        File file;
        if (system == null) {
            file = new File(pathfile);
        } else {
            file = new IFSJavaFile(system, pathfile);
        }
        return file.exists();
    }

    public void leerArchivo(String pathfile, Vector retorno) {
        BufferedReader f1;
        IFSFile file;
        String buf = "";
        try {
            if (system == null) {
                f1 = new BufferedReader(new FileReader(pathfile));
            } else {
                file = new IFSFile(system, pathfile);
                f1 = new BufferedReader(new IFSFileReader(file));
            }
            while ((buf = f1.readLine()) != null) {
                retorno.addElement(buf);
            }
            f1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] leerArchivoBin(String pathfile) {
        FileInputStream f1 = null;
        IFSFileInputStream ifsf1 = null;
        IFSJavaFile file = null;
        byte[] buffer = new byte[1024];
        try {
            if (system == null) {
                f1 = new FileInputStream(pathfile);
                int largo = f1.available();
                buffer = new byte[largo];
                while (f1.read(buffer) > 0) {
                }
                f1.close();
            } else {
                file = new IFSJavaFile(system, pathfile);
                ifsf1 = new IFSFileInputStream(file);
                int largo = ifsf1.available();
                buffer = new byte[largo];
                ifsf1.read(buffer);
                ifsf1.close();
            }

            return buffer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean borrarArchivo(String folderOfile) {
        File file;
        File[] list;
        try {
            if (system == null) {
                file = new File(folderOfile);
            } else {
                file = new IFSJavaFile(system, folderOfile);
            }
            if (file.isDirectory()) {
                list = file.listFiles();
                for (int i = 0; i < list.length; i++) {
                    if (list[i].isFile()) {
                        list[i].delete();
                    }
                }
            } else {
                file.delete();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public File[] getListaDeArchivos(String folderOfile) {
        File file;
        File[] listFiles = null;
        try {
            if (system == null) {
                file = new File(folderOfile);
            } else {
                file = new IFSJavaFile(system, folderOfile);
            }

            if (file.isDirectory()) {
                listFiles = file.listFiles();
            } else {
                listFiles = new File[1];
                listFiles[0] = file.getAbsoluteFile();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listFiles;
    }

    public static String replaceCaracter(String textoIn, char oldchar, char newchar) {
        int pos = textoIn.indexOf('\\');
        while (pos >= 0) {
            textoIn = textoIn.substring(0, pos) + "/" + textoIn.substring(pos + 1);
            pos = textoIn.indexOf('\\');
        }
        return textoIn;
    }

    public void setCCSID(int ccsidIn) {
        try {
            this.ccsid = ccsidIn;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCCSID() {
        return ccsid;
    }

    public void closeConnection() {
        this.system.disconnectAllServices();
    }

    /**
     * @return el system
     */
    public AS400 getSystem() {
        return system;
    }

    /**
     * @param system
     *            el system a establecer
     */
    public void setSystem(AS400 systemIn) {
        this.system = systemIn;
    }
}
