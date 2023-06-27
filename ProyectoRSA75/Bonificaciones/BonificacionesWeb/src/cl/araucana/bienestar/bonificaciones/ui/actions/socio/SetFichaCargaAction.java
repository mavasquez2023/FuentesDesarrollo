package cl.araucana.bienestar.bonificaciones.ui.actions.socio;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.model.Carga;
import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesSociosDelegate;

/**
 * @version 	1.0
 * @author
 */
public class SetFichaCargaAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		// Objeto de Permisos de la Aplicación
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		String rutCarga=request.getParameter("carga");
		String rutSocio=request.getParameter("socio");
		Socio socio=(Socio)request.getSession(false).getAttribute("socio");

		ServicesSociosDelegate delegate= new ServicesSociosDelegate();
		if(rutSocio!=null){
			if(!rutSocio.equals(socio.getRut())){
				socio=delegate.getSocio(rutSocio);	
				request.getSession(false).setAttribute("socio",socio);								
			}
		}
		Carga carga=delegate.getCarga(rutCarga, rutSocio);
		
		ArrayList opciones=new ArrayList();
		ArrayList opcionesValores=new ArrayList();
		/*
		if (userinformation.hasAccess("socActualizaCargaFam")) {
			opciones.add("Actualizar Carga");
			opcionesValores.add("1");
		}*/

		request.getSession(false).setAttribute("carga",carga);
		request.getSession(false).setAttribute("carga.opciones",opciones);
		request.getSession(false).setAttribute("carga.opciones.valores",opcionesValores);
		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("carga");
		this.saveToken(request);
		return (forward);

	}
}
