package cl.araucana.ctasfam.presentation.struts.actions;

import java.util.Iterator;
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
import cl.araucana.ctasfam.presentation.struts.resources.ServiceLocatorWeb;
import cl.araucana.ctasfam.presentation.struts.vo.ArchivoPrpuesta;

public class Consulta2Action extends Action{
	private static final Log log = LogFactory
	.getLog(DivisionPrevisionalAction.class);

	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		 Properties Param = new Properties();
		 Param.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		 
		String proceso=Param.getProperty("PROCESO");
		AceptaPropuestaForm aceptar= new AceptaPropuestaForm();
		aceptar.setProceso(proceso);

		try {
			ServiceLocatorWeb service = new ServiceLocatorWeb(request);
			DynaActionForm dForm = (DynaActionForm) form;
			String empresa = (String) dForm.get("empresa");
			if ("POST".equalsIgnoreCase(request.getMethod()) && empresa != null) {
				String[] rutEmpresa = empresa.split("-");
				if (service.empresaTieneArchivosPropuesta(rutEmpresa[0])) {
					List archivos = service.obtenerArchivosPropuesta(rutEmpresa[0]);
					for (Iterator iter = archivos.iterator(); iter.hasNext();) {
						ArchivoPrpuesta file = (ArchivoPrpuesta) iter.next();
						if (file.getExiste()) {
							file.setUrlDescarga(service
									.prepareDescargaPropuesta(rutEmpresa[0],
											file.getFormato()));
						}
					}
					
					System.out.println("archivos " + archivos.size());
					dForm.set("afiliados_propuesta", archivos);
				} else {
					dForm.set("afiliados_propuesta_size", new Integer(0));
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
			log.error("Error: " + e.getLocalizedMessage(), e);
			return mapping.findForward("onError");
		}
		request.setAttribute("proceso", aceptar);
		return mapping.findForward("consulta2");
	
	}

}
