package cl.araucana.ctasfam.presentation.struts.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.ctasfam.business.service.impl.PropuestaRentasServiceImpl;
import cl.araucana.ctasfam.business.to.AceptaPropuestaForm;
import cl.araucana.ctasfam.presentation.struts.resources.ServiceLocatorWeb;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;

public class InitPageAction extends Action {

	// private static final Log log = LogFactory.getLog(InitPageAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		 Properties Param = new Properties();
		 Param.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		 String proceso=Param.getProperty("PROCESO");
		 AceptaPropuestaForm aceptar= new AceptaPropuestaForm();
		 aceptar.setProceso(proceso);
		 Encargado enc =new Encargado();
		  enc=(Encargado)request.getSession().getAttribute("edocs_encargado");
		 request.setAttribute("proceso", aceptar);	  
		 
		 
		return mapping.findForward("onSuccess");
	}
}