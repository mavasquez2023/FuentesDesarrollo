package cl.araucana.ctasfam.presentation.struts.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;


import cl.araucana.ctasfam.business.service.impl.PropuestaRentasServiceImpl;
import cl.araucana.ctasfam.integration.jdbc.dao.impl.Mejoras2016DaoImpl;
import cl.araucana.ctasfam.presentation.struts.resources.ServiceLocatorWeb;
import cl.araucana.ctasfam.resources.util.Utils;
import org.apache.struts.action.ActionMessage;




public class CambioTramoFecPageAction extends Action{
	
	private static final Log log = LogFactory
	.getLog(DivisionPrevisionalAction.class);
	ActionMessages succes=new ActionMessages();
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Apéndice de método generado automáticamente
     Properties Config = new Properties();
     Config.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
	 ActionForward forward=new ActionForward();
	 ActionMessages errors=new ActionMessages();
	 String mensaje=null;
	 Utils util = new Utils();
	 int update = 0;
	 String fechaCorteInicio="";
	 String fechaCorteFin="";
	 
	 Map<String, String> result = new HashMap<String, String>();
	 Mejoras2016DaoImpl daoMejoras = new Mejoras2016DaoImpl();
	  
	  
	 
		try {

			fechaCorteInicio = request.getParameter("fechaCorteInicio"); 
			fechaCorteFin = request.getParameter("fechaCorteFin");
			String proceso=Config.getProperty("PROCESO");
			request.setAttribute("procesoTramo", proceso);


			
			if (fechaCorteInicio != null && fechaCorteFin != null && util.compararFechasInicFin(fechaCorteInicio, fechaCorteFin) == 1) {
				    
			    	update = daoMejoras.updateFechaInicio(fechaCorteInicio);
					update = daoMejoras.updateFechafin(fechaCorteFin);
			 
		     }else{
			 
				result = daoMejoras.selectFecha();
				  
				String procesoAbierto = result.get("FEC_CORTE_INIC");
				String procesoCerrado = result.get("FEC_CORTE_FIN");
				request.setAttribute("fechaCorteInicio", procesoAbierto);
				request.setAttribute("fechaCorteFin", procesoCerrado);
				
				return mapping.findForward("onSuccess");
			
			}
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if (update == 1) {
			
    		mensaje="Cambio tramo de fechas, ha sido actualizado exitosamente";
			errors.add("name", new ActionMessage("id"));
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("fechaCorteInicio", fechaCorteInicio);
			request.setAttribute("fechaCorteFin", fechaCorteFin);
			
			
			return mapping.findForward("onSuccess");
		}
		
		if (update == 2) {
			
			mensaje="Cambio tramo de fechas, no pudo ser actualizado";
			errors.add("name", new ActionMessage("id"));
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("fechaCorteInicio", fechaCorteInicio);
			request.setAttribute("fechaCorteFin", fechaCorteFin);

			return mapping.findForward("onSuccess");
		}
    	
    	else {

			return mapping.findForward("onSuccess");
		}
	 

	 
  }
	
}
