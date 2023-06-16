package cl.araucana.ctasfam.presentation.struts.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import cl.araucana.ctasfam.presentation.struts.resources.ServiceLocatorWeb;
import cl.araucana.ctasfam.presentation.struts.vo.Empresa;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;
import cl.araucana.ctasfam.resources.util.Padder;
import cl.araucana.ctasfam.resources.util.Utils;

public class DescargaPropuestasAction extends Action{
	
	private static final Log log = LogFactory
	.getLog(DescargaPropuestasAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		AraucanaJdbcDao dao=new AraucanaJdbcDao();
		ActionForward forward=new ActionForward();
		ActionMessages errors=new ActionMessages();
		 Properties Param = new Properties();
		 Param.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		String periodo=Param.getProperty("PERIODO");
		String proceso=Param.getProperty("PROCESO");
		AceptaPropuestaForm aceptar= new AceptaPropuestaForm();
		aceptar.setProceso(proceso);
		String mensaje=null;
	 Utils util=new Utils();
		int etapafinal=0;
		EstadoTO oestados=new EstadoTO();
		List lista=new ArrayList();
		int etapa=0;
		
		try {
			ServiceLocatorWeb service = new ServiceLocatorWeb(request);
			HttpSession session = request.getSession();
			Encargado encargado = (Encargado) session.getAttribute("edocs_encargado");
			
			
			for (Iterator iter = encargado.getEmpresas().iterator(); iter
					.hasNext();) {
				Empresa empresa = (Empresa) iter.next();
				String rutEmpresa = "" + empresa.getRut();
				while(rutEmpresa.length()<8){
					rutEmpresa="0".concat(rutEmpresa);
				}
				
				if (util.empresaTieneArchivosPropuesta(rutEmpresa)) {
					empresa.setFlag(1);
					 System.out.println(">>rutempresa " + rutEmpresa);
				etapa=dao.consultaEstado(Integer.parseInt(periodo), empresa.getRut());
				oestados.setEmpresa(String.valueOf(empresa.getRut()));
				oestados.setEtapa(String.valueOf(etapa));
				
				lista.add(oestados);
					 
				}
				
				
			}
			session.setAttribute("edocs_encargado", encargado);
			
		
	  
	     oestados.setEtapa(String.valueOf(etapafinal));
			
			
		} catch (Exception e) {
			e.printStackTrace();
			 mensaje="La sesión expiró o el sistema encontro una excepción";
			 errors.add("name", new ActionMessage("id"));
			log.error("Horror: " + e.getLocalizedMessage(), e);
		}
		
		if(!errors.isEmpty()){
			
			request.setAttribute("mensaje", mensaje);
			forward=mapping.findForward("onError");
		}
		else{
		request.getSession().setAttribute("lista", lista);
		request.setAttribute("proceso", aceptar);
		forward= mapping.findForward("onSuccess");
		}
		
		 return forward;
	}
	
	 


}
