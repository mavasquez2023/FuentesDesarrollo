package cl.araucana.ctasfam.presentation.struts.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import cl.araucana.ctasfam.business.to.AceptaPropuestaForm;
import cl.araucana.ctasfam.business.to.EstadoTO;
import cl.araucana.ctasfam.integration.jdbc.dao.AraucanaJdbcDao;
import cl.araucana.ctasfam.integration.jdbc.dao.impl.Mejoras2016DaoImpl;
import cl.araucana.ctasfam.presentation.struts.vo.Empresa;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;
import cl.araucana.ctasfam.resources.util.Parametros;
import cl.araucana.ctasfam.resources.util.Utils;

public class DescargaPropuestasAction extends Action{
	
	 
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		Mejoras2016DaoImpl dao=new Mejoras2016DaoImpl();
		ActionForward forward=new ActionForward();
		ActionMessages errors=new ActionMessages();
		 Properties Param = new Properties();
		 Utils util=new Utils();
		
		String periodo= Parametros.getInstance().getParam().getPeriodoProceso();
		String mensaje=null;
	 
		List<String> empresas=null;
		List<EstadoTO> lista=new ArrayList();
		String rol="";
		try {

			HttpSession session = request.getSession();
			Encargado encargado = (Encargado) session.getAttribute("edocs_encargado");
			rol = (String) session.getAttribute("rol");

			//session.setAttribute("edocs_encargado", encargado);

			String listaempresas="";
			if(!rol.equals("Ejecutivo")){


				for (Iterator iter = encargado.getEmpresas().iterator(); iter
						.hasNext();) {
					Empresa empresa = (Empresa) iter.next();
					listaempresas += empresa.getRut() + "," ;
				}	
				if(listaempresas.length()>1){
					listaempresas= listaempresas.substring(0, listaempresas.length()-1);
				}
				periodo= periodo.substring(0, 4) + "00";
				empresas= dao.getEmpresasPropuesta(Integer.parseInt(periodo), listaempresas);
				for (Iterator iter = encargado.getEmpresas().iterator(); iter
						.hasNext();) {
					Empresa empresa = (Empresa) iter.next();
					if(empresas.contains(String.valueOf(empresa.getRut()))){
						empresa.setFlag(1);
					}				
					EstadoTO oestados=new EstadoTO();
					oestados.setEmpresa(String.valueOf(empresa.getRut()));
					oestados.setEtapa("0");
					lista.add(oestados);
				}
				session.setAttribute("edocs_encargado", encargado);
			}

		} catch (Exception e) {
			e.printStackTrace();
			 mensaje="La sesión expiró o el sistema encontro una excepción";
			 errors.add("name", new ActionMessage("id"));
			 
		}
		
		if(!errors.isEmpty()){
			
			request.setAttribute("mensaje", mensaje);
			if(rol.equals("Ejecutivo")){
				forward=mapping.findForward("onErrorEjecutivo");
			}else{
				forward=mapping.findForward("onError");
			}
		}
		else{
			request.getSession().setAttribute("lista", lista);
			//request.setAttribute("proceso", aceptar);
			String tipo= Parametros.getInstance().getParam().getTipoDescarga();
			if(rol.equals("Ejecutivo")){
				if(tipo.equals("PROPUESTA")){
					forward= mapping.findForward("onEjecutivo");
				}else{
					forward= mapping.findForward("onEjecutivoInforme");
				}
				
			}else{
				if(tipo.equals("PROPUESTA")){
					forward= mapping.findForward("onSuccess");
				}else{
					forward= mapping.findForward("onSuccessInforme");
				}
				
			}
		}

		 return forward;
	}
	
	 


}
