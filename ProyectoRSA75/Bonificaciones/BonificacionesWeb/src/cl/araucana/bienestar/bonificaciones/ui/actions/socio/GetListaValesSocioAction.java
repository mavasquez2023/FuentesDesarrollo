package cl.araucana.bienestar.bonificaciones.ui.actions.socio;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosDelegate;
 
/**
 * @version 	1.0
 * @author
 */
public class GetListaValesSocioAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		Logger logger = Logger.getLogger(GetListaValesSocioAction.class);
		Socio socio=(Socio)request.getSession(false).getAttribute("socio");
	
		// Objeto de Permisos de la Aplicación
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		String source=request.getParameter("source");
		String rutSocio=request.getParameter("socio");
		String convenio=request.getParameter("convenio");
		String target="listaValesSocio";
		
		if(source==null) source=""; 
		if(rutSocio==null) rutSocio=""; 
		if(convenio==null) convenio=""; 
	
		ServicesConveniosDelegate delegate = new ServicesConveniosDelegate();
		if(source.equals("caso")){
			ArrayList listaCasos=delegate.getValesNoUsadosByRut(rutSocio,(long)Long.parseLong(convenio));
			target="listaValesSocioCaso";
			request.getSession(false).setAttribute("lista",listaCasos);
		}
		else{
			socio=delegate.getValesByRut(socio);
			logger.debug("lista.size: "+socio.getVale().size());
	
			ArrayList opciones=new ArrayList();
			ArrayList opcionesValores=new ArrayList();
			if (userinformation.hasAccess("socAsignaVale")) {
				opciones.add("Asignar Vale");
				opcionesValores.add("1");
			}
				
			// Guardar en memoria el resultado
			request.getSession(false).setAttribute("opciones",opciones);
			request.getSession(false).setAttribute("opciones.valores",opcionesValores);
			request.getSession(false).setAttribute("socio",socio);
		}
		

		// Write logic determining how the user should be forwarded.

		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);

	}
}
