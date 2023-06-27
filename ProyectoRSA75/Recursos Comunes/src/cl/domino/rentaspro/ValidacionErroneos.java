/**
 * 
 */
package cl.domino.rentaspro;

import lotus.domino.*;
import java.util.*;
import java.io.*;
import java.util.Iterator;
import java.lang.StringBuffer;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.usermodel.*;

import cl.araucana.core.util.Rut;
import cl.recursos.EnviarMail;
import cl.recursos.Formato;
/**
 * @author usist24
 *
 */
public class ValidacionErroneos extends AgentBase {
	int [][] poslar = new int[18][2] ;
	private Vector mensaje;
	private ParametrosEnvio param=null;
	ParametrosSistema paramSistema=null;
	private Database db;
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ValidacionErroneos valida= new ValidacionErroneos();
		valida.NotesMain();

	}

	public void NotesMain() {
		int conerror_archivo=0;
		try {
			System.out.println(" .....................COMIENZA (Validar Erroneos)   ............");
			//Session s = getSession();
			Session s= NotesFactory.createSession("10.10.10.106", "clillo", "claudio7");
			//AgentContext ac	= s.getAgentContext();
			//PrintWriter pw = getAgentOutput();
			//db 		= ac.getCurrentDatabase();
			db =   s.getDatabase(s.getServerName(), "circular2511.nsf");
			View viewerroneos = db.getView("listadoerror");
			View viewvalidados = db.getView("okxrut");
			ViewEntryCollection vec = viewerroneos.getAllEntries();
			System.out.println("numero de archivos erroneso:" + vec.getCount());
			if ( vec.getCount()>0 ){
				
				mensaje= new Vector();
				//System.out.println("VAB. Nro de archivos s validar= " + vec.getCount());
				paramSistema= new ParametrosSistema(db);
				ViewEntry entry=null;
				List empresas_informadas= new ArrayList();
				for (int i=0; i<vec.getCount(); i++){
					entry	 	= vec.getNthEntry(i+1);
					Document doc= entry.getDocument();
					conerror_archivo=0;
					mensaje= new Vector();
					param= new ParametrosEnvio(doc);
					if (param.getArchivo()!=null) {
						Object rutArchivo= param.getRutArchivo();
						
						if(rutArchivo!=null){
							mensaje.add("<li>Archivo " + param.getNombreArchivo() + " (Empresa RUT: " + rutArchivo.toString() + ") :<BR>");
						}else{
							mensaje.add("<li>Archivo " + param.getNombreArchivo() + ") :<BR>");
						}
						String rutempArchivo= param.getNombreArchivo().substring(0, param.getNombreArchivo().indexOf("."));

						if(paramSistema.getRut_excluir().indexOf(rutempArchivo)>-1){
							mensaje.add("Empresa NO autorizada. Comuníquese con su ejecutivo para mayor información.<BR>");
							conerror_archivo++;
						}else{
							ViewEntry entryok= viewvalidados.getEntryByKey(rutempArchivo.trim(), true);
							ViewEntry entryok2= viewvalidados.getEntryByKey(new Integer(rutempArchivo), true);
							System.out.println("Archivo a validar para " + param.getNombreArchivo() + ":" + entryok);
							if (entryok==null && entryok2==null){
								doc.replaceItemValue("rezagado", "1");
								doc.save(true,true);
								if (!empresas_informadas.contains(rutempArchivo)){
									//enviarMail(1, param.getMailEncargados(), mensaje, param) ;
									empresas_informadas.add(rutempArchivo.trim());
								}
							}else{
								doc.remove(true);
							}
						}
							
					} // fin de has file
				} //fin de For
				
			} //fin de IF (Mapeo())


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
			String[] emailUsuarios= paramSistema.getEmailUsuarios();
			for (int i = 0; i < emailUsuarios.length; i++) {
				blindCopyTo.add(emailUsuarios[i]);
			}
			if(param.getRutArchivo()!= null){
				mensajeRut= ", asociado a la empresa " + paramEnvio.getRutArchivo().toString() + ", ";
			}
			if(conerror_envio==0){
				subject= " Validación exitosa en archivo " + paramEnvio.getNombreEnvio() ;
				body.append("Señor Empleador: su archivo " + mensajeRut + " ha sido validado exitosamente. <BR>");
				blindCopyTo.add("ingresospromedios@laaraucana.cl");
			}else{
				subject= " Aviso térnimo plazo 18:30 hrs. - Archivo CON OBSERVACIONES: " + paramEnvio.getNombreEnvio() ;
				body.append("Señor Empleador: se recuerda que aún NO se envía correctamente sus antecedentes de Rentas Promedio, favor regularizar a la brevedad. <BR> ");
				body.append("Plazo vence inpostergablemente el lunes 16 de Agosto a las 18:30 hrs.<BR><BR>");
				if(paramEnvio.getExtensionArchivo().equalsIgnoreCase("xls")){
				body.append("Si su archivo tiene fórmulas, vaya a la opción del menú 'Archivo/Guardar Como' y escoja CSV, luego reenviar nuevo archivo.<BR><BR>");
				}
				blindCopyTo.add("ingresospromedioscontacto@laaraucana.cl");
				blindCopyTo.add("ingresospromedios@laaraucana.cl");
			}
		  	body.append("Detalle validación:<BR>");
		  	//Se rescatan los primeros 100 mensajes
		  	int tope = mensaje.size();
			if( tope>500 ){
				tope=500;
			}
		  	for ( int	i = 0; 	i< tope;	i++ ) {
				body.append(mensaje.elementAt(i));
			}
		  	 body.append("<br><br>");
		  	 body.append("Si usted ya regularizó su situación favor omitir este correo.");
		  	 body.append("<br><br>");
		  	 body.append("Saluda atte. a Ud. "+"<BR>");
		  	 body.append("La Araucana - Soluciones Sociales.");

		  	mail.mailTo("portal@laaraucana.cl", mailEncargados, null, blindCopyTo , subject, body.toString());
		  	
		  	//System.out.println(".............. EMAIL GENERADO .................... " );
		  	
			}catch(Exception e) {
				System.out.println("CAI EN MAIL  " );
				e.printStackTrace();
			}
 	 }
}
