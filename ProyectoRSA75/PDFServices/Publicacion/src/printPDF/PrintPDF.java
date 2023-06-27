package printPDF;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilPDF.UtilPDF;
import utilPub.UtilPub;

import com.ibm.as400.access.*;

public class PrintPDF extends HttpServlet {
    private String par1 = new String("");
    private String par2 = new String("");
    private String ssID = new String("");
    private AS400 system;
	private PrintWriter outprt = null;
	private OutputStream outstr = null;
    private String directorioas400;
    private CommandCall command;
    private boolean ejecuta;
    private String comando;
	private Context ctx;
	private OutputStream outBin;
	private String DirectorioLocal;

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String whereinput;
    String whereout;
    String Tipoinput;
    String Tipoutput;

    AS400Message[] messagelist;
    String message;
    int largo;
    String codmessage;

	outprt = null;
	outstr = null;

    /********************************************************************
    Ejecutar comando en AS400
    ********************************************************************/
	try {
		ctx = new InitialContext();
		String AS400IP = (String) ctx.lookup("java:comp/env/AS400IP");
		String AS400Usuario = (String) ctx.lookup("java:comp/env/AS400Usuario");
		String AS400Password = (String) ctx.lookup("java:comp/env/AS400Password");
		int MaxDoc = ((Integer) ctx.lookup("java:comp/env/MaximoDocumentosZIP")).intValue();

		system = new AS400(AS400IP, AS400Usuario, AS400Password);

	    command = new CommandCall(system);
    	ssID = command.getServerJob().getNumber();
    	ejecuta = false;

		directorioas400 = (String) ctx.lookup("java:comp/env/AS400Directorio");
		DirectorioLocal = (String) ctx.lookup("java:comp/env/DirectorioDownload");
        par1 = request.getParameter("inTipo");
        par2 = request.getParameter("inCondicion");

        whereinput = par2;
        whereout = ModificaString(whereinput);
		whereout = ModificaStringSucursal(whereout);
		Tipoinput = par1;
        Tipoutput = RetornaTipo(Tipoinput);

		comando = "QSH CMD('arsdoc query -h QUSROND -u QONDADM -p qondadm ";
		comando = comando + "-G \""+ Tipoutput + "\" ";
		comando = comando + "-d "+ directorioas400 + " -o Indice ";
		comando = comando + "-i \"WHERE TipoInforme = ''PDF'' ";
		comando = comando +  whereout + "\"')";
		ejecuta = command.run(comando);
		if (ejecuta) {
			messagelist = command.getMessageList();
			message = messagelist[0].getText();
			largo = message.length();
			codmessage = message.substring(largo-2, largo-1);
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
								if (run(response)) {
									String outFileName = DirectorioLocal + ssID;
									UtilPub.RespuestaContentType(response, outFileName, "pdf");
								}	
							} catch (NamingException e1) {
								throw new ServletException(e1.getMessage());
							}
						} else {
							outprt = UtilPub.MensajeInfoHTML(response, outprt, "No se encuentran documentos para la selección dada");	
						}
					} else {
						outprt = UtilPub.MensajeInfoHTML(response, outprt, "Error en comando de búsqueda de documentos" + command.getMessageList());	
					}
				} else {
					outprt = UtilPub.MensajeInfoHTML(response, outprt, "Se ha excedido el límite de " + MaxDoc + " documentos para la selección...<BR>Incorpore criterios a su busqueda y vuelva ha intentarlo...");	
				}
			} else {
				if (message.indexOf("254") > 0) {
					outprt = UtilPub.MensajeInfoHTML(response, outprt, "No se encuentran documentos para la selección dada");	
				} else {
					outprt = UtilPub.MensajeInfoHTML(response, outprt, "Error en comando de búsqueda de documentos: " + message);	
				}
			}
		} else {
			outprt = UtilPub.MensajeInfoHTML(response, outprt, "Error en comando de búsqueda de documentos" + command.getMessageList());	
		}
	} catch (NamingException e) {
		outprt = UtilPub.MensajeErrorHTML(response, outprt, e);	
    } catch (IOException e) {
		outprt = UtilPub.MensajeErrorHTML(response, outprt, e);	
    } catch (AS400SecurityException e) {
		outprt = UtilPub.MensajeErrorHTML(response, outprt, e);	
    } catch (ErrorCompletingRequestException e) {
		outprt = UtilPub.MensajeErrorHTML(response, outprt, e);	
    } catch (InterruptedException e) {
		outprt = UtilPub.MensajeErrorHTML(response, outprt, e);	
    } catch (PropertyVetoException e) {
		outprt = UtilPub.MensajeErrorHTML(response, outprt, e);	
	} finally {
		if (outprt != null) {
			outprt.close();
		}
		System.out.println("Cerrando conexión a servicio remoto AS400 en PrintPDF");
		//system.disconnectService(AS400.COMMAND);
		system.disconnectAllServices();
	}       
 }

boolean run(HttpServletResponse objResponse) throws NamingException
{
	boolean resp = false;
    AS400FTP ftp;
    File outFile;
    String outFileName;
    String pdfFiles = "";
	String strComando = "";
	String[] args = null;

    try {

            ftp = new AS400FTP(system);
            try {
                ftp.connect();
                ftp.cd(directorioas400);
                ftp.setDataTransferType(1);
                String [] files;
                files = ftp.ls(ssID + "*");

                if (files.length > 0) {
                    for (int i=1 ; i <= files.length; i++){
                        outFile = new File(DirectorioLocal + ssID + i + ".pdf");
                        if (files.length > 1){
                            ftp.get(ssID + "." + i , outFile);
                        } else {
                            ftp.get(ssID , outFile);
                        }
						pdfFiles = pdfFiles + DirectorioLocal + ssID + i + ".pdf ";
                    }
					outFileName = DirectorioLocal + ssID + ".pdf";
					strComando = pdfFiles + outFileName;
					//System.out.println(strComando);
					args = UtilPub.split(strComando, " ");
					if (!UtilPDF.ConcatenaPDF(args)) {
						outprt = UtilPub.MensajeInfoHTML(objResponse, outprt, "Problemas en generación de archivo PDF...");
					} else {
						resp = true;
					}

                    for (int i=1 ; i <= files.length; i++){
                        outFile = new File(DirectorioLocal + ssID + i + ".pdf");
                        outFile.delete();
                    }
                    comando = "CALL PGM(PWOBJD/PWPDELFILE) PARM('";
                    comando = comando + directorioas400 + "' '";
                    comando = comando + ssID + "' '";
                    comando = comando + files.length + "')";
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
    } catch (IOException e) {
		outprt = UtilPub.MensajeErrorHTML(objResponse, outprt, e);	
    }
	return resp;
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
	
static String ModificaString(String cadena)
    {
    String newcadena = cadena;
    String CadenaSINcoma = "";
    String CadenaCONcoma = "";
    String newCadenaCONcoma = "";
    String newaux = "";

    int pos;
    int posfin;
    int posini;
    int acumcomas = 0;

    do {
        posini = cadena.indexOf("(");
        posfin = cadena.indexOf(")")+ 1;

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
                    newCadenaCONcoma = newCadenaCONcoma + (CadenaCONcoma.substring(0, pos + 1) + " ");
                    CadenaCONcoma = CadenaCONcoma.substring(pos + 1, CadenaCONcoma.length());
                    acumcomas = acumcomas + 1;
                }
            } while(pos > 0);
            newCadenaCONcoma = newCadenaCONcoma + CadenaCONcoma;
            CadenaCONcoma =  newCadenaCONcoma;
            cadena = (cadena.substring(0, posini).concat(CadenaCONcoma));
            newaux = newaux.concat(cadena);
            if (newaux.length() - acumcomas != newcadena.length()) {
                cadena = newcadena.substring(newaux.length() + 1 - acumcomas, newcadena.length());
                cadena = " " + cadena;
            } else {
                posfin = -1;
                newcadena = newaux;
            }
        } else {
            newcadena = newaux;
        }
    } while (posfin > 0 );
    return newcadena;
    }

static String RetornaTipo(String tipo)
    {
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
        default: System.out.println("No existen parametros de Tipo");
             break;
    } //fin switch
    return App;
    }

} //fin clase PrintPDF
