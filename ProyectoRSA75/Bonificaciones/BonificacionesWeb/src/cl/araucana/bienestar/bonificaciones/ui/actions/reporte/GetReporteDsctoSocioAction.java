/*
 * Creado el 14-11-2005
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package cl.araucana.bienestar.bonificaciones.ui.actions.reporte;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesOperacionesDelegate;
import cl.araucana.bienestar.bonificaciones.serv.ServicesSociosDelegate;

/**
 * @version 	1.0
 * @author
 */
public class GetReporteDsctoSocioAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		Logger logger = Logger.getLogger(GetReporteDsctoSocioAction.class);
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		ServicesOperacionesDelegate delegate = new ServicesOperacionesDelegate();
		
		StringTokenizer st = new StringTokenizer(userinformation.getRut(),"-");				
		String rut=st.nextToken();   	
		logger.debug("RUT: "+userinformation.getRut());
		ArrayList listaDsctoSocio=delegate.getDescuentosRealizadosBySocio(rut);
		String target="reporteDsctoSocio";
		request.getSession(false).setAttribute("lista",listaDsctoSocio);
		
		//Busco la información del Socio, ya que no está completa aún en el objeto que está en memoria
		ServicesSociosDelegate delegateSocios = new ServicesSociosDelegate();
		Socio socio = delegateSocios.getSocio(rut);
		request.getSession(false).setAttribute("socio", socio);		

		// Write logic determining how the user should be forwarded.

		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);


	}
}

