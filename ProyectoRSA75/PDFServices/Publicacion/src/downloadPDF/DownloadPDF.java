package downloadPDF;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilPub.UtilPub;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400FTP;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.CommandCall;
import com.ibm.as400.access.ErrorCompletingRequestException;

public class DownloadPDF extends HttpServlet {
	
	private static final long serialVersionUID = 7870934792768696651L;
	
	private String par1 = new String("");
    private String par2 = new String("");
    private String ssID = new String("");
    private AS400 system;
    private PrintWriter outprt = null;
    private String directorioas400;
	private String DirectorioLocal;
    private String comando;
    private boolean ejecuta = false;
	private CommandCall command;
	private Context ctx;
	private String zipName;
	 
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		outprt = null;
	 	try {
			ctx = new InitialContext();
			String AS400IP = (String) ctx.lookup("java:comp/env/AS400IP");
			String AS400Usuario = (String) ctx.lookup("java:comp/env/AS400Usuario");
			String AS400Password = (String) ctx.lookup("java:comp/env/AS400Password");
			int MaxDoc = ((Integer) ctx.lookup("java:comp/env/MaximoDocumentosZIP")).intValue();

            system = new AS400(AS400IP, AS400Usuario, AS400Password);
        	command = new CommandCall(system);
        	ssID= command.getServerJob().getNumber();
        	
			directorioas400 = (String) ctx.lookup("java:comp/env/AS400Directorio");
			DirectorioLocal = (String) ctx.lookup("java:comp/env/DirectorioDownload");
            par1 = request.getParameter("inTipo");
            par2 = request.getParameter("inCondicion");
			
            String whereinput = par2;
            String whereout = ModificaString(whereinput);
			whereout = ModificaStringSucursal(whereout);
            String Tipoinput = par1;
            String Tipoutput = retornaTipo(Tipoinput);

			comando = "QSH CMD('arsdoc query -h QUSROND -u QONDADM -p qondadm ";
			comando = comando + "-f \""+ Tipoutput + "\" ";
			comando = comando + "-G \""+ Tipoutput + "\" ";
			comando = comando + "-d "+ directorioas400 + " -o Indice ";
			comando = comando + "-i \"WHERE TipoInforme = ''PDF'' ";
			comando = comando +  whereout + "\"')";
			ejecuta = command.run(comando);
			if (ejecuta) {
				AS400Message[] messagelist = command.getMessageList();
				String message = messagelist[0].getText();
				int largo = message.length();
				String codmessage = message.substring(largo-2, largo-1);
				if (codmessage.startsWith("0")) {
					if (UtilPub.ContarPDF(system, directorioas400 + "/Indice") <= MaxDoc) {
						comando = "QSH CMD('arsdoc get -h QUSROND -u QONDADM -p qondadm ";
						comando = comando + "-G \""+ Tipoutput + "\" ";
						comando = comando + "-d "+ directorioas400 + " -o " + ssID + " ";
						comando = comando + "-i \"WHERE TipoInforme = ''PDF'' ";
						comando = comando +  whereout + "\"')";
						ejecuta = command.run(comando);
						if (ejecuta) {
							messagelist = command.getMessageList();
							message = messagelist[0].getText();
							largo = message.length();
							codmessage = message.substring(largo-2, largo-1);
							if (codmessage.startsWith("0")) {
								try {
									if (run(response)){
										String outFileName = DirectorioLocal + ssID;
										UtilPub.RespuestaContentType(response, outFileName, "zip");
									}
								} catch (NamingException e1) {
									throw new ServletException(e1.getMessage());
								}
							} else {
								outprt = UtilPub.MensajeInfoHTML(response, outprt, "No se encuentran documentos para la selección dada");	
							}
						} else {
							outprt = UtilPub.MensajeInfoHTML(response, outprt, "Error en comando de búsqueda de documentos: " + message);	
						}
					} else {
						outprt = UtilPub.MensajeInfoHTML(response, outprt, "Se ha excedido el límite de " + MaxDoc + " documentos para la selección...<BR>Incorpore criterios a su búsqueda y vuelva ha intentarlo...");
					}
				} else {
					if (message.indexOf("254") > 0) {
						outprt = UtilPub.MensajeInfoHTML(response, outprt, "No se encuentran documentos para la selección dada");	
					} else {
						outprt = UtilPub.MensajeInfoHTML(response, outprt, "Error en comando de búsqueda de documentos: " + message);	
					}
				}
			}
			

		} catch (NamingException e) {
			outprt = UtilPub.MensajeErrorHTML(response, outprt,  e);	
        } catch (IOException e) {
			outprt = UtilPub.MensajeErrorHTML(response, outprt,  e);	
        } catch (AS400SecurityException e) {
			outprt = UtilPub.MensajeErrorHTML(response, outprt,  e);	
        } catch (ErrorCompletingRequestException e) {
			outprt = UtilPub.MensajeErrorHTML(response, outprt,  e);	
        } catch (InterruptedException e) {
			outprt = UtilPub.MensajeErrorHTML(response, outprt, e);	
        } catch (PropertyVetoException e) {
			outprt = UtilPub.MensajeErrorHTML(response, outprt,  e);	
        } finally {
			if (outprt != null) {
				outprt.close();
			}
			System.out.println("Cerrando conexión a servicio remoto AS400 en DownloadPDF");
			//system.disconnectService(AS400.COMMAND);
			system.disconnectAllServices();
	 	}       
    }

    boolean run(HttpServletResponse objResponse) throws NamingException {
		boolean resp = false;
        try {
            AS400FTP ftp = new AS400FTP(system);
            try {
                ftp.connect();
                ftp.cd(directorioas400);
                ftp.setDataTransferType(1);
                String [] files;
                files = ftp.ls(ssID + "*");
                ZipOutputStream zip = null;
                byte[] buffer = new byte[1024];

                int bytesRead;
                if (files.length > 0) {
                    zipName = DirectorioLocal + ssID + ".zip";
                    zip = new ZipOutputStream(new FileOutputStream(zipName));
                    for (int i=1 ; i <= files.length; i++){
                        File outFile;
                        outFile = new File(DirectorioLocal + ssID + i + ".pdf");
                        if (files.length > 1){
                            boolean fg = ftp.get(ssID + "." + i , outFile);
                        } else {
                            boolean fg = ftp.get(ssID , outFile);
                        }
                        String outFileName = DirectorioLocal + ssID + i + ".pdf";
                        FileInputStream file = new FileInputStream(outFileName);
                        ZipEntry entry = new ZipEntry(outFileName);
                        zip.putNextEntry(entry);
                        while ((bytesRead = file.read(buffer)) != -1) {
                          zip.write(buffer, 0, bytesRead);
                        }
                        file.close();
						outFile.delete();
                    }
                    zip.close();
                    resp = true;
                    comando = "CALL PGM(PWOBJD/PWPDELFILE) PARM('";
                    comando = comando + directorioas400 + "' '";
                    comando = comando + ssID + "' '";
                    comando = comando + files.length + "')";
                    CommandCall command = new CommandCall(system);
                    ejecuta = command.run(comando);
                } else {
					outprt = UtilPub.MensajeInfoHTML(objResponse, outprt, "No existen archivos para la selección dada...");
                }

            } catch (IOException e) {
				outprt = UtilPub.MensajeErrorHTML(objResponse, outprt, e);	
            } catch (AS400SecurityException e) {
				outprt = UtilPub.MensajeErrorHTML(objResponse, outprt, e);	
            } catch (ErrorCompletingRequestException e) {
				outprt = UtilPub.MensajeErrorHTML(objResponse, outprt, e);	
            } catch (InterruptedException e) {
				outprt = UtilPub.MensajeErrorHTML(objResponse, outprt, e);	
            } catch (PropertyVetoException e) {
				outprt = UtilPub.MensajeErrorHTML(objResponse, outprt, e);	
            }
            ftp.disconnect();
        } catch (IOException e) {// Try para FTP del ind
			outprt = UtilPub.MensajeErrorHTML(objResponse, outprt, e);	
		} finally {
			if (outprt != null) {
				outprt.close();
			}
		}       
    	return resp;    
    }

   public static String ModificaString(String cadena){
        String newcadena = cadena;
        String CadenaSINcoma = "";
        String CadenaCONcoma = "";
        String newCadenaCONcoma = "";
        String neww = "";
        int pos;
        int posfin;
        int acumcomas = 0;
        do {
            int posini = cadena.indexOf("(");
            posfin = cadena.indexOf(")")+1;

             if (posfin > 0) {
                newCadenaCONcoma = "";
                CadenaCONcoma = "";
                CadenaSINcoma = "";
                CadenaSINcoma = cadena.substring(posini, posfin);
                CadenaCONcoma = CadenaSINcoma.replace(' ',',');
                do
                {
                    pos = CadenaCONcoma.indexOf(",");
                    if (pos > 0) {
                        newCadenaCONcoma = newCadenaCONcoma + (CadenaCONcoma.substring(0, pos+1) + " ");
                        CadenaCONcoma = CadenaCONcoma.substring(pos+1, CadenaCONcoma.length());
                        acumcomas = acumcomas + 1;
                     }
                }while(pos > 0);
                newCadenaCONcoma = newCadenaCONcoma + CadenaCONcoma;
                CadenaCONcoma =  newCadenaCONcoma;
                cadena = (cadena.substring(0, posini).concat(CadenaCONcoma));
                neww = neww.concat(cadena);
                if (neww.length() - acumcomas != newcadena.length()) {
                 cadena = newcadena.substring(neww.length() + 1 - acumcomas, newcadena.length());
                 cadena = " " + cadena;
                }
                else {posfin = -1;
                newcadena = neww;
                }
            }
            else {
                newcadena = neww;
            }

        } while (posfin > 0 );
        return newcadena;
    }

  public static String ModificaStringSucursal(String cadena){

  	int pos1 = cadena.lastIndexOf("Sucursal");
	if (pos1 > 0) {
		String aux1 = cadena.substring(0, pos1 + 15);
	
		int pos2 = cadena.indexOf(")", pos1);
		String aux2 = cadena.substring(pos2 - 2);
		
		String aux3 = cadena.substring(pos1 + 15, pos2 - 2);
		pos1 = aux3.indexOf(", ");
	
		while (pos1 > 0) {
			aux3 = aux3.substring(0, pos1) + "'',''" + aux3.substring(pos1 + 2); 
			pos1 = aux3.indexOf(", ");			
		}	
		
		cadena = aux1 + aux3 + aux2;
	}
	return cadena;
  }

  public static String retornaTipo(String tipo){
      String App = "";

      switch(Integer.parseInt(tipo))
      {
          case 1:
            App = "Planillas AFPs Holding";
            break;
          case 2:
            App = "Planillas APVs Holding";
            break;
          case 3:
            App = "Planillas Cajas Holding";
            break;
          case 4:
            App = "Planillas INP Holding";
            break;
          case 5:
            App = "Planillas Isapres Holding";
            break;
          case 6:
            App = "Planillas Mutuales Holding";
            break;
          case 7:
            App = "Comprobantes Pago Holding";
            break;
          case 8:
            App = "Certificado Cotizaciones Holding";
            break;
          case 9:
            App = "Informe Cotizaciones Holding";
            break;
		  case 10:
		    App = "Planillas AFPs DNP Holding";
		    break;
          case 11:
            App = "Planillas AFPs TP Holding";
            break;
          case 12:
          	App = "Planillas APVs Colectivo Holding";
            break;
          case 13:
          	App = "Planillas AFPs AV Holding";
          	break;
          case 14:
          	App = "Planillas INP DNP Holding";
          	break;
          case 15:
          	App = "Planillas INP DNP Pagadas Holding";
          	break;
          case 16:
            App = "Planillas AFPs SIL Holding";
            break;          	
          default: System.out.println("No existen parametros de Tipo");
             break;
      } //fin switch
	  return App;
 }

} //fin clase downloadPDF.DownloadPDF
