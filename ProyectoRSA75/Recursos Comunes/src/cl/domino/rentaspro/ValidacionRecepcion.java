/**
 * 
 */
package cl.domino.rentaspro;

import lotus.domino.*;

import java.util.*;
import java.io.PrintWriter;
import java.lang.StringBuffer;
import cl.recursos.EnviarMail;
/**
 * @author usist24
 *
 */
public class ValidacionRecepcion extends AgentBase {
	private Vector mensaje;
	private ParametrosEnvio param=null;
	private ParametrosSistema paramsist=null;
	private Database db;
		
	public void NotesMain() {
		int conerror_envio=0;
		String formato="";
		try {
			System.out.println(" .....................COMIENZA (Valida Recepcion Archivos)............");
			Session s = getSession();
			AgentContext ac	= s.getAgentContext();
			db 		= ac.getCurrentDatabase();
			
			//se rescatan parámetros del Sistema
			paramsist= new ParametrosSistema(db);
			
			//se rescata documento del request
			Document doc= ac.getDocumentContext();
			conerror_envio=0;
			mensaje= new Vector();
			param= new ParametrosEnvio(doc);

			if (param.getArchivo()!=null) {
				formato=param.getFormatoAttach();
				if (formato.equals("TXT")||formato.equals("CSV")||formato.equals("XLS")||formato.equals("ZIP")){
					if(formato.equals("ZIP")){
						ExtraerArchivoZip zip= new ExtraerArchivoZip(db, "/planos/");
						mensaje= zip.extraerZip(doc);
						param.setCantidadArchivos(zip.getCountAttach());
						if(zip.getCountError()>0){
							conerror_envio++;
							param.setEstadoProceso('2');
						}
					}else{
						String rutempresa= param.getNombreArchivo();
						try {
							int rutemp= Integer.parseInt(rutempresa.substring(0, rutempresa.lastIndexOf(".")));
						} catch (NumberFormatException e) {
							mensaje.add("Formato de archivo no válido. El nombre del archivo debe ser el Rut de la Empresa sin dígito verificador.<BR>");
							conerror_envio++;
						}
					}
				}else{
					mensaje.add("Formato de archivo no válido. Solo se permite extensiones txt, csv, xls o zip." + "<BR>");
					conerror_envio++;
				} // fin de TXT, CSV
			} // fin de has file
			else{
				mensaje.add("Sin archivo adjunto." + "<BR>");
				conerror_envio++;
			}
			//System.out.println("Cambiando el estado al archivo, conerror_envio=" + conerror_envio);
			if (conerror_envio> 0){
				param.setEstadoProceso('2');
			}
			if(!formato.equalsIgnoreCase("ZIP") && conerror_envio==0){
				doc.replaceItemValue("Form", "cargaarchivos");
				doc.save(true,true);
			}else{
				//doc.remove(true);
				doc.replaceItemValue("estado", "9");
			}
			//procesar último envío
			enviarMail(conerror_envio, param.getMailEncargados(), mensaje, param) ;
			//Se abre conexión a Db2 para registrar recepción
			ParametrosSistema paramSistema= new ParametrosSistema(db);
			RentasPromedioDAO rentasDAO= new RentasPromedioDAO(paramSistema.getSistema(), paramSistema.getUsuario(), paramSistema.getPassword());
			rentasDAO.insertRecepcion(param);
			rentasDAO.desconectaDB2();
			
			//se redirige la salida
			PrintWriter pw = getAgentOutput();
			if (conerror_envio> 0){
				pw.println("[[/circular2511.nsf/paginaerror?openpage]]");
			}else{
				pw.println("[[/circular2511.nsf/pagina?openpage]]");
			}

		} catch(Exception e) {
			System.out.println("VAB: CAI EN MAIN " );
			e.printStackTrace();
		}
	}
 	   
	   //Mail de aviso al cliente  x existencia de Observaciones o éxito proceso
    public void enviarMail(int conerror_envio, Vector mailEncargados, Vector mensaje, ParametrosEnvio paramEnvio) {
    	String subject="", casilla="", mensajeRut="";
		try {			
		     EnviarMail mail= new EnviarMail("portal", "portal08");
		     StringBuffer body= new  StringBuffer();
		     Vector blindCopyTo= new Vector();
		     //se rescata mmail de usuarios para incluir en correos ocultos
		     String[] emailUsuarios= paramsist.getEmailUsuarios();
		     //se rescata rut Archivo
		     if(param.getRutArchivo()!= null){
		    	 mensajeRut= ", asociado a la empresa " + paramEnvio.getRutArchivo().toString() + ", ";
		     }
		     for (int i = 0; i < emailUsuarios.length; i++) {
		    	 blindCopyTo.add(emailUsuarios[i]);
		     }
			if(conerror_envio==0){
				subject= " Recepción exitosa en archivo " + paramEnvio.getNombreEnvio() ;
				body.append("Señor Empleador: su archivo ha sido recibido.<BR>");
				body.append("Le informaremos dentro de las próximas horas el resultado del proceso. Por lo tanto, debe estar atento a dicha información.<BR>");
				blindCopyTo.add("ingresospromedios@laaraucana.cl");
			}else{
				subject= "No recepcionado archivo " + paramEnvio.getNombreEnvio() ;
				if(param.getFormatoEnvio().equalsIgnoreCase("ZIP")){
					body.append("Señor Empleador (RUT: " + paramEnvio.getRutEmpresa() + "): su archivo <b>presenta " + conerror_envio + " empresa(s) con observaciones</b> <BR>");
					body.append("Consecuente con lo anterior, es necesario corregir y reenviar archivos erroneos dentro de los plazos establecidos.<BR><BR>");
					body.append("Detalle recepción:<BR>");
				}else{
					body.append("Señor Empleador: su archivo " + mensajeRut + "<b>no pudo ser recepcionado</b>. <BR>");
					body.append("Consecuente con lo anterior, es necesario corregir y reenviar dentro de los plazos establecidos.<BR><BR>");
				}
		  	 	blindCopyTo.add("ingresospromedioscontacto@laaraucana.cl");
		  	 	blindCopyTo.add("ingresospromedios@laaraucana.cl");
		  	}
		  	//Se rescatan los primeros 100 mensajes
		  	int tope = mensaje.size();
			if( tope>500 ){
				tope=500;
			}
		  	for ( int	i = 0; 	i< tope;	i++ ) {
				body.append(mensaje.elementAt(i));
			}
		  	 body.append("<br><br>");
		  	 body.append("Saluda atte. a Ud. "+"<BR>");
		  	 body.append("La Araucana - Soluciones Sociales.");

		  	mail.mailTo("portal@laaraucana.cl", mailEncargados, null, blindCopyTo , subject, body.toString());
		  	
		  	System.out.println(".............. EMAIL GENERADO .................... " );
		  	
			}catch(Exception e) {
				System.out.println("CAI EN MAIL  " );
				e.printStackTrace();
			}
 	 }
}
