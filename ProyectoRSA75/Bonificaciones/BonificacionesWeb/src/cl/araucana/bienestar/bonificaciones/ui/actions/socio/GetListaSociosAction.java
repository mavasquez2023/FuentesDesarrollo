package cl.araucana.bienestar.bonificaciones.ui.actions.socio;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesSociosDelegate;

/**
 * @version 	1.0
 * @author
 */
public class GetListaSociosAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

			// Objeto de Permisos de la Aplicación
			cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
			if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();
			
			Logger logger = Logger.getLogger("En Action Lista Candidatos");
						
			// Recuperar los datos del Formulario
			DynaValidatorActionForm dynaValidatorActionForm =
				(DynaValidatorActionForm) form;
							
			ArrayList opciones=new ArrayList();				
			ArrayList opcionesValores=new ArrayList();				

			String source=request.getParameter("source");
			if(source==null) source="";

			Socio socio = new Socio();
			socio.setRut((String)dynaValidatorActionForm.get("rut"));
			socio.setNombre((String)dynaValidatorActionForm.get("nombre"));
			socio.setEstado(Socio.STD_ACTIVO);
			String target="listaSocios";
			String opcion=(String)dynaValidatorActionForm.get("opcion");
			String reporte=null;
			if(source.equals("caso")){
				target="listaSociosCaso";
				opcion="";
			} else if(source.equals("reporte")){
				target="listaSociosReporte";
				opcion="";
				reporte="ON";
			}

			
			if(opcion.equals("6")){
				target="listaCandidatos";
			}
			else{
				logger.debug("Recibimos: "+socio.getNombre()+", "+ socio.getRut());
			
				ServicesSociosDelegate delegate = new ServicesSociosDelegate();	
				ArrayList lista = delegate.getListaSocios(socio);
	
				if (userinformation.hasAccess("socActiva")) {
					opciones.add("Agregar Socio");
					opcionesValores.add("6");		
				}
				
				//dynaValidatorActionForm.reset(mapping,request);
				// Guardar en memoria el resultado
				request.getSession(false).setAttribute("lista.socios",lista);
				request.getSession(false).setAttribute("socios.opciones",opciones);
				request.getSession(false).setAttribute("socios.opciones.valores",opcionesValores);
				request.getSession(false).setAttribute("socios.reporte",reporte);
			}			

			// Write logic determining how the user should be forwarded.
			ActionForward forward = new ActionForward();
			forward = mapping.findForward(target);
			return (forward);


	}
}
