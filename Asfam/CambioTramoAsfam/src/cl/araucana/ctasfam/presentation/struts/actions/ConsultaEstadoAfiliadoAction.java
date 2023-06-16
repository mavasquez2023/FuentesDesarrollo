package cl.araucana.ctasfam.presentation.struts.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import cl.araucana.ctasfam.business.to.AceptaPropuestaForm;
import cl.araucana.ctasfam.business.to.FlujoTO;
import cl.araucana.ctasfam.integration.jdbc.dao.AraucanaJdbcDao;
import cl.araucana.ctasfam.presentation.struts.resources.ServiceLocatorWeb;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;

public class ConsultaEstadoAfiliadoAction extends Action{
	
	private static final Log log = LogFactory
	.getLog(DivisionPrevisionalAction.class);
	
	public ConsultaEstadoAfiliadoAction(){
		
	}
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	 
	
	
	FlujoTO flujo=  new FlujoTO();
	AceptaPropuestaForm acepta=new AceptaPropuestaForm();
	 Properties Carpetas = new Properties();
	  Carpetas.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
	  String periodo=Carpetas.getProperty("PERIODO");
	  String proceso=Carpetas.getProperty("PROCESO");
	  acepta.setProceso(proceso);
	  Encargado enc =new Encargado();
	  int total=0;
	  
	  AraucanaJdbcDao dao=new AraucanaJdbcDao();
	  ArrayList resu= new ArrayList();
	  
	  int afiliados=0;
	  int propuesta=0;
	  enc=(Encargado)request.getSession().getAttribute("edocs_encargado");
	try {
		ServiceLocatorWeb service = new ServiceLocatorWeb(request);
		DynaActionForm dForm = (DynaActionForm) form;
		Integer oficina = (Integer) dForm.get("oficina");
		String empresa = (String) dForm.get("empresa");
		Integer sucursal = (Integer) dForm.get("sucursal");
      
       
		dForm.set("afiliados_propuesta", null);
		dForm.set("afiliados_propuesta_informados", null);
		dForm.set("afiliados_propuesta_propuesta", null);
		dForm.set("afiliados_propuesta_size", null);
		if (oficina != null && empresa != null && sucursal != null) {
			
			
			String[] rutEmpresa = empresa.split("-");
			
		
			
			List afiliadosInformados = service.getPropuestaRentasService()
					.obtenerAfiliadosPropuestaInformados(
							oficina.intValue(),
							Integer.parseInt(rutEmpresa[0]), rutEmpresa[1],
							sucursal.intValue());
			List afiliadosPropuesta = service.getPropuestaRentasService()
					.obtenerAfiliadosPropuesta(oficina.intValue(),
							Integer.parseInt(rutEmpresa[0]), rutEmpresa[1],
							sucursal.intValue());
			
		 resu=(ArrayList)dao.getAfiliadosIngresados(oficina.intValue(), sucursal.intValue(), Integer.parseInt(rutEmpresa[0]));
			total=resu.size();
			
			if (afiliadosInformados != null) {
				
				afiliados=afiliadosInformados.size();
				propuesta=afiliadosPropuesta.size();
				
				dForm.set("afiliados_propuesta", afiliadosInformados);
				dForm.set("afiliados_propuesta_informados", new Integer(afiliadosInformados.size()));
				if(afiliadosPropuesta != null) {
					System.out.println(">>propuestas " + afiliadosPropuesta.size());
					dForm.set("afiliados_propuesta_propuesta", new Integer(afiliadosPropuesta.size()));
				} else {
					dForm.set("afiliados_propuesta_propuesta", new Integer(0));
				}
			} else {
				dForm.set("afiliados_propuesta_size", new Integer(0));
				dForm.set("afiliados_propuesta_propuesta", new Integer(afiliadosPropuesta.size()));
				propuesta=afiliadosPropuesta.size();
			}
		} else {
			dForm.set("oficina", null);
			dForm.set("empresa", null);
			dForm.set("sucursal", null);
			dForm.set("afiliados_propuesta", null);
			dForm.set("afiliados_propuesta_informados", null);
			dForm.set("afiliados_propuesta_propuesta", null);
			dForm.set("afiliados_propuesta_size", null);
		}
		
		 
		 
		 
	} catch (Exception e) {
		String mensaje="La sesión expiró o el sistema encontro una excepción";
		request.setAttribute("mensaje", mensaje);
		log.error("Error: " + e.getLocalizedMessage(), e);
		return mapping.findForward("onError");
	}
	
	request.setAttribute("proceso", acepta);
	request.setAttribute("insertados", String.valueOf(total));
	request.setAttribute("informados", String.valueOf(afiliados));
	request.setAttribute("propuesta", String.valueOf(propuesta));
	return mapping.findForward("consulta1");

}
}
