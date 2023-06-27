/*
 * Creado el 09-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.entidades.actions;


import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.recursos.EnviarMail;
import cl.recursos.Formato;
import cl.recursos.GeneratorXLS;
import cl.araucana.cierrecpe.business.Parametros;
import cl.araucana.cierrecpe.to.ParametrosCPTO;
import cl.araucana.core.util.logging.LogManager;

/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class EnviarPropuestaxCorreoAction extends Action {
	static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");
	private static Logger logger = LogManager.getLogger();
	final int cierre=0;
	
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception 
			{	
		ActionForward forward = new ActionForward();
		try{
			logger.info("Entrando EnviarPropuestaxCorreoAction");
			HttpSession sesion= request.getSession();
			//rescatando parametros de la instancia Singleton a nivel de ear.
			ParametrosCPTO paramTO= Parametros.getInstance().getParam();
			String emails= paramTO.getEmailUsuario();
			String periodo= (String)request.getParameter("periodo");
			String cierre= (String)request.getParameter("cierre");
			String sendto= (String)request.getParameter("mail");
			String deposito= request.getParameter("deposito");
			List propuesta= (List)sesion.getAttribute("cheques");
			if (propuesta!= null){
				String filename= (String)request.getParameter("filename");
				if (filename== null || filename.equals("")){
					filename= "download_excel.xls"; 
				}
				
				//Generando la salida
				String pathfile= "/cp/cierre/" + filename;
				logger.fine("Se generará salida en: " + pathfile);
				OutputStream out = new FileOutputStream(pathfile);
				PrintStream flujo= new PrintStream(out);
				GeneratorXLS xls= new GeneratorXLS(flujo);
				
				//Configurando columnas a desplegar y titulos de estas.
				String[] columnas={"cierre", "descripcionSeccion", "razonSocial", "rut", "tipoDetalle", "montoTotal", "tipoNomina", "folioEgreso", "conceptoTesoreria", "deposito", "codigoBanco", "cuentaCorriente"};
				String[] titulos={"Cierre", "Sección", "Entidad", "Rut", "Código Entidad", "Monto", "Nómina", "Folio Egreso", "Concepto Egreso", "Tipo Depósito", "Código Banco", "Cuenta Corriente"};
				Cookie filtro= new Cookie("deposito", deposito);
				xls.generarXLSfromCollection(propuesta, columnas, titulos, "006777", filtro);
				logger.fine("Archivo " + pathfile + " ha sido generado.");
				//Cerrando salida
				out.flush();
				out.close();
				
				logger.fine("Enviando Propuesta por correo.");
				//Enviar Correo con excel adjunto
				enviarMail(Formato.split(sendto, ";"), Formato.split(emails, ";"), periodo, cierre, pathfile, filtro.getValue());
				forward = mapping.findForward("OK");
			}else{
				logger.warning("Se solicita descargar excel, sin embargo, Propuesta en sesion es null");
				forward = mapping.findForward("NOTOK");
			}
			
			
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			forward = mapping.findForward("NOTOK");
			e.printStackTrace();
		}
		return forward;
	}
	
//	Mail de aviso al cliente  x existencia de Observaciones o éxito proceso
    public void enviarMail( String[] mailDestinatarios, String[] mailEncargados, String periodo, String cierre, String adjunto, String filtro) {
    	String subject="";
		try {			
			String host=mailProperties.getString("smtp.host");
			String port=mailProperties.getString("smtp.port");
			String user=mailProperties.getString("smtp.user");
			String pass=mailProperties.getString("smtp.password");
			EnviarMail mail = new EnviarMail(host, port , user, pass);
			
			StringBuffer body= new  StringBuffer();
			subject= filtro + " Previpass" ;
			body.append("Estimado: se adjunta archivo con depósitos de " + filtro + " a realizar para periodo:" + periodo+ ", cierre:" + cierre + "<BR>");
			body.append("<br><br>");
			body.append("Saluda atte. a Ud. "+"<BR>");
			body.append("Previpass - Cotización Electrónica.<BR>");
			body.append("La Araucana - Soluciones Sociales.");
			mail.attach(adjunto);
			mail.mailTo("aplica@laaraucana.cl", mailDestinatarios, mailEncargados, null , subject, body.toString());

			System.out.println(".............. EMAIL GENERADO .................... " );

			}catch(Exception e) {
				System.out.println("CAI EN MAIL  " );
				e.printStackTrace();
			}
 	 }
}
