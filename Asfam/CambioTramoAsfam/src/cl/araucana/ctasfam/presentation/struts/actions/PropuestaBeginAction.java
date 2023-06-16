package cl.araucana.ctasfam.presentation.struts.actions;

import java.util.Iterator;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import cl.araucana.ctasfam.business.to.AceptaPropuestaForm;
import cl.araucana.ctasfam.business.to.EstadoTO;
import cl.araucana.ctasfam.business.to.EtapasTO;
import cl.araucana.ctasfam.business.to.FlujoTO;
import cl.araucana.ctasfam.integration.jdbc.dao.AraucanaJdbcDao;
import cl.araucana.ctasfam.presentation.struts.vo.Empresa;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;
import cl.araucana.ctasfam.resources.util.Padder;
import cl.araucana.ctasfam.resources.util.Parametros;
import cl.araucana.ctasfam.resources.util.Utils;

public class PropuestaBeginAction extends Action{
	
	private static final Log log = LogFactory
	.getLog(PropuestaBeginAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		EstadoTO oestados=new EstadoTO();
		ActionForward forward=new ActionForward();
		ActionMessages errors=new ActionMessages();
		FlujoTO flujo=new FlujoTO();
		
		Utils util=new Utils();
		//String proceso=Param.getProperty("PROCESO");
		//AceptaPropuestaForm aceptar= new AceptaPropuestaForm();
		
		String mensaje=null;
		try {
			 
			HttpSession session = request.getSession();
			Encargado encargado = (Encargado) session.getAttribute("edocs_encargado");
			int etapafinal=0;
			
			session.setAttribute("edocs_encargado", encargado);
			oestados.setEtapa(String.valueOf(etapafinal));
		 
			
		} catch (Exception e) {
			
			mensaje="La sesión expiró o el sistema encontro una excepción";
			errors.add("name", new ActionMessage("id"));
			e.printStackTrace();
			log.error("Horror: " + e.getLocalizedMessage(), e);
		}
		if(!errors.isEmpty())
		{
			request.setAttribute("mensaje", mensaje);
			forward=mapping.findForward("onError");
			
		}
		else{
		
		 //request.setAttribute("proceso", aceptar);
		forward= mapping.findForward("onSuccess");
		}
		return forward;
		 
	}
	
	 

}