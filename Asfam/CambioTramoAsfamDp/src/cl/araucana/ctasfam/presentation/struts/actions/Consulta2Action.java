package cl.araucana.ctasfam.presentation.struts.actions;

import java.io.File;
import java.util.ArrayList;
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
import com.ibm.as400.access.AS400;
import com.ibm.as400.access.IFSJavaFile;
import cl.araucana.ctasfam.business.to.AceptaPropuestaForm;
import cl.araucana.ctasfam.presentation.struts.resources.ServiceLocatorWeb;
import cl.araucana.ctasfam.presentation.struts.vo.ArchivoPrpuesta;
import cl.araucana.ctasfam.resources.util.Utils;

public class Consulta2Action extends Action{
	private static final Log log = LogFactory
	.getLog(DivisionPrevisionalAction.class);

	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		 Properties Param = new Properties();
		 Param.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		 Utils util=new Utils();
		 
		String proceso=Param.getProperty("PROCESO");
		AceptaPropuestaForm aceptar= new AceptaPropuestaForm();
		aceptar.setProceso(proceso);
		ArchivoPrpuesta oarchivos=new ArchivoPrpuesta();

		try {
			ServiceLocatorWeb service = new ServiceLocatorWeb(request);
			DynaActionForm dForm = (DynaActionForm) form;
			String empresa = (String) dForm.get("empresa");
			if ("POST".equalsIgnoreCase(request.getMethod()) && empresa != null) {
				String[] rutEmpresa = empresa.split("-");
				if (util.empresaTieneArchivosPropuesta(rutEmpresa[0])) {
					File[] archivos = util.obtenerArchivosPropuesta(rutEmpresa[0]);
					List lista=new ArrayList();
					 
					 
					AS400 as400=util.creaConexionAS400();
					for (int i=0;i<archivos.length;i++) {
						  File file = archivos[i];
						  System.out.println(file.getAbsolutePath());
						  file = new IFSJavaFile(as400, file.getAbsolutePath());
						if (file.exists()) {
							oarchivos=new ArchivoPrpuesta();
							oarchivos.setNombreArchivo(file.getName());
							oarchivos.setUrlDescarga(service.prepareDescargaPropuesta(rutEmpresa[0],
											util.extencion(file)));
							lista.add(oarchivos);
						}
					}
					util.cierraconexionAS400(as400);					
					 
					dForm.set("afiliados_propuesta", lista);
				} else {
					System.out.println("archivos 0");
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
