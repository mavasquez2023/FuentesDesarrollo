package cl.araucana.bienestar.bonificaciones.ui.actions.ajax;

import java.io.Writer;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.model.Caso;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;

public class ValidateCasoAjaxAction extends Action{

	static Logger logger = Logger.getLogger( ValidateCasoAjaxAction.class );
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	ServicesCasosDelegate delegate = new ServicesCasosDelegate();    	

    	String rutSocio	= request.getParameter("rutSocio");
    	String casoId 	= request.getParameter("casoId");
    	String numDoc 	= request.getParameter("numDoc");
    	String tipoDoc 	= request.getParameter("tipoDoc");
    	
    	logger.debug("Verificando Caso rutSocio : " + rutSocio + " Caso ID : "+ casoId + " Numero Documento : "+numDoc +" Tipo Documento : "+tipoDoc );
    	    	    	
    	Caso caso = new Caso();
    	StringTokenizer st = new StringTokenizer(rutSocio,"-");				
    	//rut=st.nextToken();   	
    	//digito= st.nextToken().charAt(0);
    	
    	caso.setRutSocio(st.nextToken());
    	//caso.setr
    	caso.setCasoID(Long.parseLong(casoId));
    	caso.setNumeroDocumento(numDoc);
    	caso.setTipoDocumento(tipoDoc);
    	
    	boolean registrado = false;
    	registrado = delegate.documentoYaRegistrado(caso); 
    	
    	response.setHeader("Content-Type", "application/xml");
		response.setContentType("application/xml");
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control",	"must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		
		Writer writer = response.getWriter();		
		
		String respuesta = "<respuesta>" + registrado + "</respuesta>";
		
		logger.debug("Verificando Caso registrado : " + registrado );
		writer.write(respuesta);
		
    	return null;
    }
}
