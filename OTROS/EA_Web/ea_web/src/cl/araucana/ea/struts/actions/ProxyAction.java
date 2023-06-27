

package cl.araucana.ea.struts.actions;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import cl.araucana.common.Profile;
import cl.araucana.common.UserProfile;
import cl.araucana.core.util.http.Utils;
import cl.araucana.ea.ctacte.delegate.BusinessDelegate;
import cl.araucana.ea.ctacte.delegate.BusinessDelegateFactory;
import cl.araucana.ea.struts.exceptions.ServiceUnavailableException;
import cl.araucana.ea.struts.exceptions.UnauthorizedAccessException;


/**
 * @version 	1.0
 * @author
 * 
 */
public class ProxyAction extends Action {

	static final String DELEGATE_IMPL =
			"cl.araucana.ea.ctacte.delegate.BusinessDelegateImpl"; 

	/* 
	 *	La accion ProxyAction
	 *	Hay tres posible precedencia que estan identificado por el parametro status
	 *
	 * 		status: 
	 * 		  ""  : 1. un usuario nuevo a acedido, obtiener el perfile de este usuario
	 * 	               2. depende de las empresas asociadas al usuario
	 *  	              2.1 si no hay ninguna empresas en su atributo despacha pagina de error.
	 *                     Al aceptar la pagina de error, esta pagina invalida usuario al apretar boton aceptar
	 *                     redireccionando a /ibm_security_logout?logoutExitPage=/
	 *                 2.2 si hay una sola empresa en su atributo enviar a la pagina servicio.jsp
	 *                 2.3 si hay mas de una empresas, enviar a la pagina seleccionEmpresa.jsp
	 *        "c" : viene de la pagina seleccion de empresa. reemplazar atributo empresa en session por nueva empresa
	 *              luego enviar a la pagina servicio.jsp
	 *  	  "r" : usuario solicito cambiar empresas, eliminar empresa desde session,
	 *              enviar a la pagina seleccionEmpresa.
	 */
	 	
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		/*
		 *  Used in START PAGE ONLY.
		 */
		if (Utils.needsForwaring(request)) {
			Utils.forwardPage(request, response);
		
			return null;
		}
		
		HttpSession session = request.getSession();
		
		UserProfile xProfile =
				(UserProfile) session.getAttribute("ea_user_profile");

		String toPage = null;
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		ActionMessages messages = new ActionMessages();
		session.removeAttribute("errorEmpresa");

		/*
		 * 	Gets an instance of business delegate
		 */
		String delegateImpl = request.getSession()
									   .getServletContext()
									   .getInitParameter("DELEGATE_IMPL");
		BusinessDelegate delegate = null;
		
		try {
			delegate = (BusinessDelegate) 
									BusinessDelegateFactory
									.singlton()
									.newInstance(DELEGATE_IMPL);
		} catch (Exception e) {
			e.printStackTrace();
			
			StringBuffer msg = new StringBuffer();
			msg.append("\r\n\tAtrapado en: ").append(this.getClass().getName()).append("#execute");
			msg.append("\r\n\tCausa: error propagado.");
			throw new ServiceUnavailableException(msg.toString(), e);
		}		
	
		/*
		 * 	perform business logic
		 */
		UserProfile profile =
				(UserProfile) session.getAttribute("ea_user_profile");

		Collection empresas = (Collection) profile.getAttribute("empresas");
		
		if (profile == null || empresas.size() == 0) {
			toPage = "unauthorizedAccess";				
		} else {
			String status =
					(String) PropertyUtils.getSimpleProperty(form, "status");

			if("c".equalsIgnoreCase(status)) {
				/*
				 * 	Una nueva empresa ha sido eleido.
				 * 	agregarla en la session
				 */ 
				Long empresaElegida = (Long) PropertyUtils.getSimpleProperty(form, "empresaElegida");
				profile.setAttribute("empresa", empresaElegida);
				toPage = "servicio";				
			} else {
				String rol = (String) session.getAttribute("rol");
				if (rol=="Ejecutivo"){
					/*
					 * 	ejecutivo captura una empresa
					 */
					toPage = "capturaEmpresa"; 
				}else{
					/*
					 * 	seleccion de empresa
					 */
					Long empresaSeleccionada = null;
					Collection listaEmpresas = (Collection) profile.getAttribute("empresas");
					if(listaEmpresas.size() == 1) {
						toPage = "servicio";
					} else if(listaEmpresas.size() == 0) {
						StringBuffer msg = new StringBuffer();

						msg.append("\r\n\tAtrapado en: ").append(this.getClass().getName()).append("#execute");
						msg.append("\r\n\tCausa: Usuario no tiene autorizacion.");

						throw new UnauthorizedAccessException(msg.toString());					
					} else if(listaEmpresas.size() > 1) {
						PropertyUtils.setSimpleProperty(form, "empresaElegida", profile.getAttribute("empresa"));
						toPage = "seleccionaEmpresa";		
					}
				}			
			}
		}			

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			toPage = "";
		}

		forward = mapping.findForward(toPage);		
		return (forward);
	}
}
