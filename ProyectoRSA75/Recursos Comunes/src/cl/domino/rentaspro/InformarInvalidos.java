/**
 * 
 */
package cl.domino.rentaspro;

import lotus.domino.*;
import java.util.*;
import java.lang.StringBuffer;
import cl.recursos.EnviarMail;
/**
 * @author usist24
 *
 */
public class InformarInvalidos extends AgentBase {
	private Vector mensaje;
	private ParametrosEnvio param=null;
	private ParametrosSistema paramsist=null;
	private Database db;
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InformarInvalidos valida= new InformarInvalidos();
		valida.NotesMain();

	}

	public void NotesMain() {
		int conerror_envio=0;
		try {
			System.out.println(" .....................COMIENZA (Informar Archivos Mal Validados)............");
			//Session s = getSession();
			Session s= NotesFactory.createSession("146.83.1.49", "clillo", "claudio7");
			//AgentContext ac	= s.getAgentContext();
			//db 		= ac.getCurrentDatabase();
			db =   s.getDatabase(s.getServerName(), "circular2511.nsf");
			//se rescatan parámetros del Sistema
			paramsist= new ParametrosSistema(db);
			
			View view = db.getView("validadosBAD");
			ViewEntryCollection vec = view.getAllEntries();
			ViewEntry entry	 	= vec.getFirstEntry();
			int numarchivos=vec.getCount();
			for (int i=0; i<numarchivos; i++){
				//mensaje= new Vector();
				entry	 	= vec.getNthEntry(i+1);
				Document doc= entry.getDocument();
				param= new ParametrosEnvio(doc);
				doc.replaceItemValue("estado", "2");
				doc.save(true,true);
				enviarMail(conerror_envio, param.getMailEncargados(), null, param) ;
			}

		} catch(Exception e) {
			System.out.println("VAB: CAI EN MAIN " );
			e.printStackTrace();
		}
	}
 	   
	   //Mail de aviso al cliente  x existencia de Observaciones o éxito proceso
	public void enviarMail(int conerror_envio, Vector mailEncargados, Vector mensaje, ParametrosEnvio paramEnvio) {
		String subject="1", casilla="", mensajeRut="";
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
			subject= "Aviso de Observaciones en validación de archivo " + paramEnvio.getNombreEnvio() ;
			body.append("Señor Empleador: su archivo " + mensajeRut + "<b>no pudo ser validado correctamente</b> debido a error de formato del archivo enviado, por lo tanto, no se ha procesado ningún trabajador. <BR>");
			body.append("Revise formato en Servicios en Línea y reenvié el archivo dentro del plazo establecido.<BR><BR>");
			blindCopyTo.add("ingresospromedioscontacto@laaraucana.cl");
			blindCopyTo.add("ingresospromedios@laaraucana.cl");

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
