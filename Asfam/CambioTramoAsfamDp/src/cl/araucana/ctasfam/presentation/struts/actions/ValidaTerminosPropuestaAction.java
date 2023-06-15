package cl.araucana.ctasfam.presentation.struts.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import cl.araucana.ctasfam.business.to.AceptaPropuestaForm;
import cl.araucana.ctasfam.business.to.EstadoTO;
import cl.araucana.ctasfam.business.to.FlujoTO;
import cl.araucana.ctasfam.integration.jdbc.dao.AraucanaJdbcDao;
import cl.araucana.ctasfam.presentation.struts.resources.ServiceLocatorWeb;
import cl.araucana.ctasfam.presentation.struts.vo.AceptacionPropuesta;
import cl.araucana.ctasfam.presentation.struts.vo.Empresa;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;
import cl.araucana.ctasfam.resources.util.Padder;
import cl.araucana.ctasfam.resources.util.Utils;

public class ValidaTerminosPropuestaAction extends DispatchAction {

	private static final Log log = LogFactory
			.getLog(ValidaTerminosPropuestaAction.class);
	
	AraucanaJdbcDao dao=new AraucanaJdbcDao();

	public ActionForward startValidacionTerminos(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 ActionForward forward=new ActionForward();
		  ActionErrors errors=new ActionErrors();
		  String mensaje=null;
		  Utils util=new Utils();
		 
		AceptaPropuestaForm acepta=new AceptaPropuestaForm();
		
		 Properties Param = new Properties();
		 Param.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		String periodo=Param.getProperty("PERIODO");
		String proceso=Param.getProperty("PROCESO");
		try {
			ServiceLocatorWeb service = new ServiceLocatorWeb(request);
			HttpSession session = request.getSession();
			Encargado encargado = (Encargado) session.getAttribute("edocs_encargado");
			
	
			
			for (Iterator iter = encargado.getEmpresas().iterator(); iter
					.hasNext();) {
				Empresa empresa = (Empresa) iter.next();
				String rutEmpresa = "" + empresa.getRut();
				rutEmpresa = Padder.pad(rutEmpresa, 8, '0', true);
				if (util.empresaTieneArchivosPropuesta(rutEmpresa)) {
					empresa.setFlag(1);
					 
					
					 	
					 
				}
				
				
			}
			session.setAttribute("edocs_encargado", encargado);
			
		
			acepta.setProceso(proceso);
	     
			
			
		} catch (Exception e) {
			errors.add("name", new ActionMessage("id"));
			mensaje="La sesión expiró o el sistema encontro una excepción";
			e.printStackTrace();
			log.error("Horror: " + e.getLocalizedMessage(), e);
		}
		
		if(!errors.isEmpty()){
			request.setAttribute("mensaje", mensaje);
			forward=mapping.findForward("onError");
				
			}else{
				
		  request.setAttribute("proceso", acepta);
	      forward=mapping.findForward("showForm");
		
		
			}
		return forward;
	}

	public ActionForward processValicacionTerminos(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 ActionForward forward=new ActionForward();
		  ActionErrors errors=new ActionErrors();
		  String mensaje=null;
		try {
			 Properties Param = new Properties();
			 Param.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
			String periodo=Param.getProperty("PERIODO");
			DynaActionForm dynForm = (DynaActionForm) form;
			ServiceLocatorWeb service = new ServiceLocatorWeb(request);
			Encargado encargado = (Encargado) request.getSession()
					.getAttribute("edocs_encargado");
			Integer aprobarEmpresasSize = (Integer) dynForm
					.get("aprobar_empresas_size");
			Map aprobarEmpresas = (Map) dynForm.get("aprobar_empresas_map");
			for (int i = 1; i <= aprobarEmpresasSize.intValue(); i++) {
				String emp = request.getParameter("empresa_" + i);
				if (emp != null && aprobarEmpresas.containsKey(emp)) {
					Empresa empVo = (Empresa) aprobarEmpresas.get(emp);
					service.getPropuestaRentasService()
							.guardarAceptacionPropuesta(empVo, encargado);
					
					FlujoTO flujo=new FlujoTO();
					System.out.println("aqui divicion");
					
					flujo.setEtapa("4");
					flujo.setRutencargado(String.valueOf(encargado.getRut()));
					flujo.setISAJKM94("CTADMIN");
					flujo.setPeriodo(periodo);
					System.out.println(">>" + empVo.getRut());
					flujo.setRutempresa(String.valueOf(empVo.getRut()));
					flujo.setISAJKM92("CTADMIN");
					flujo.setISAJKM94("");
					
					dao.updateFlujo(flujo);
				}
			}
			
			
			
		} catch (Exception e) {
			
			errors.add("name", new ActionMessage("id"));
			mensaje="La sesión expiró o el sistema encontro una excepción";
			log.error("Error: " + e.getLocalizedMessage(), e);
			return mapping.findForward("onError");
		}
		if(!errors.isEmpty()){
		request.setAttribute("mensaje", mensaje);
		forward=mapping.findForward("onError");
			
		}else{
			forward=mapping.findForward("onSuccess");
		}
		 
		
		return forward;
	}
}