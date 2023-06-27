package cl.araucana.ea.struts.actions;

import java.security.Principal;
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

import com.ibm.jvm.io.FileInputStream;

import cl.araucana.common.LDAPUserRegistry;
import cl.araucana.common.Profile;
import cl.araucana.common.Rut;
import cl.araucana.common.RutInvalidoException;
import cl.araucana.common.UserProfile;
import cl.araucana.ea.ctacte.dto.OpcionTO;
import cl.araucana.ea.ctacte.dto.RutTO;
import cl.araucana.ea.ctacte.delegate.BusinessDelegate;
import cl.araucana.ea.ctacte.delegate.BusinessDelegateFactory;
import cl.araucana.ea.ctacte.dto.EmpresaTO;
import cl.araucana.ea.struts.exceptions.ServiceUnavailableException;

/**
 * @version 	1.0
 * @author
 * 
 */
public class Proxy2Action extends Action {

	static BusinessDelegateFactory factory;

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

		HttpSession session = request.getSession();
		String toPage = null;
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		//
		// precondition check
		//


		//
		// get an instance of business delegate
		//
		BusinessDelegate delegate = null;
		try {
			factory = BusinessDelegateFactory.singlton();
			delegate = factory.newInstance("cl.araucana.ea.ctacte.delegate.BusinessDelegateImp");
		} catch (Exception e) {
			StringBuffer msg = new StringBuffer();
			msg.append("\r\n\tAtrapado en: ").append(this.getClass().getName()).append("#execute");
			msg.append("\r\n\tCausa: error propagado.");
			throw new ServiceUnavailableException(msg.toString(), e);					
		}

		Properties properties = null;
		try {
			InitialContext initCtx = new InitialContext();
			properties = (Properties) initCtx.lookup("INIT_CONFIG");
		} catch (Exception e) {
			StringBuffer msg = new StringBuffer();
			msg.append("\r\n\tAtrapado en: ").append(this.getClass().getName()).append("#exceute");
			msg.append("\r\n\tCausa: error propagado.");
			throw new ServiceUnavailableException(msg.toString());					
		}

		if(properties == null) {
			StringBuffer msg = new StringBuffer();
			msg.append("\r\n\tAtrapado en: ").append(this.getClass().getName()).append("#exceute");
			msg.append("\r\n\tCausa: No esta disponible el objeto en InitialContext con el nomnre INIT_CONFIG.");
			throw new ServiceUnavailableException(msg.toString());									
		}

		UserProfile profile = (UserProfile) session.getAttribute("ea_user_profile");
						
		String status = (String) PropertyUtils.getSimpleProperty(form, "status");			
		if((profile != null) && profile.isValid() && "r".equalsIgnoreCase(status)) {
			//
			// usuario solicita cambiar la empresa elegida de la lista de empresas 
			// eliminar la "Empresa Elegida" en la session
			//
			Long empresaElegida = (Long) profile.getAttribute("empresa");
			profile.removeAttribute("empresa");
			PropertyUtils.setSimpleProperty(form, "empresaElegida", empresaElegida);				
			toPage = "seleccionaEmpresa";

		} else if((profile != null) && profile.isValid() && "c".equalsIgnoreCase(status)) {
			// 
			// una nueva empresa fue seleccionada
			// agregar a la session
			//
			Long empresaElegida = (Long) PropertyUtils.getSimpleProperty(form, "empresaElegida");
			profile.setAttribute("empresa", empresaElegida);
			toPage = "servicio";				

		} else {
			//
			// crear perfil.
			//
			
			// eliminar perfil anterior por si a caso
			session.removeAttribute("ea_user_profile");

			// quien esta accediendo?
			String remoteUser = request.getRemoteUser();
			
			profile = new UserProfile();
			Long empresaSeleccionada = null;
			Collection listaEmpresas = null;
			
			// obtener lista de empresas asociado a este usuario				
			String userRegistry = (String) properties.getProperty("REGISTRY_TYPE"); 
			//  *--ServletContext ctx = session.getServletContext();
			//  *--String userRegistry = ctx.getInitParameter("USER_REGISTRY");
			System.out.println("Registry type: " + userRegistry);
			if("LDAP".equalsIgnoreCase(userRegistry)) {
				LDAPUserRegistry ldap = new LDAPUserRegistry(
												(String) properties.getProperty("LDAP_HOST"),
												(String) properties.getProperty("LDAP_PORT"),
												(String) properties.getProperty("LDAP_INITIAL_CONTEXT_FACTORY"));
				listaEmpresas = ldap.getEmpresasDeUsuario(remoteUser);					
				System.out.println("Lista empresa: " + listaEmpresas);
			} else if("customFile".equalsIgnoreCase(userRegistry)) {
				// *--- development purpose only ---*
				try {
					empresaSeleccionada = new Long(remoteUser);
				} catch (NumberFormatException e) {
					throw new Exception();
				}
				listaEmpresas = new ArrayList();
				listaEmpresas.add(empresaSeleccionada);	 
			}	

			System.out.println("tamano empresa: " + listaEmpresas.size());
			// dependiendo del numero de epresas
			if((listaEmpresas == null) || (listaEmpresas.isEmpty())) {
				// usuario no tiene empresas en su atributo

				//	*---- Reemplazar por globar error
				toPage = "error";
				
			} else if(listaEmpresas.size() == 1) {
				// usuario tiene una sola empresa en su atributo
				long empresaElegida = 0L;
				Iterator it = listaEmpresas.iterator();
				String elem = (String) it.next();					
				Rut rut = null;
				try {
					rut = new Rut(elem);
				} catch (RutInvalidoException e) {
					// ignorar los rut invalidos
					rut = new Rut();
					rut.setId(new Long(elem).longValue());
				}
				profile.setAttribute("empresa", new Long(empresaElegida));
				profile.setAttribute("empresas", null);
				profile.setAttribute("opciones", null);
				toPage = "servicio";

			} else if(listaEmpresas.size() > 1) {
				// hay mas de una empresa asociadas a este usuario. debe elegir una de ellas
				Collection opciones = new ArrayList();
				EmpresaTO dtoEmpresa = null;
				long empresaElegida = 0L;
				int count = 1;
				Iterator it = listaEmpresas.iterator();
				while(it.hasNext()) {
					String elem = (String) it.next();					
					try {
						Rut	rut = new Rut(elem);
						dtoEmpresa = delegate.getEmpresa(new RutTO(rut.getId(), rut.getDv()));
						OpcionTO opcion = new OpcionTO(new Long(rut.getId()).toString(), elem + ": " + dtoEmpresa.getNombre());					
						opciones.add(opcion);					
						if(count==1) {
							// primera empresa
							empresaElegida = rut.getId();						
							PropertyUtils.setSimpleProperty(form, "empresaElegida", new Long(rut.getId()));
						}
						count++;
					} catch (RutInvalidoException e) {
						// ignorar rut invalidos pero registrar
						System.err.println("Usuario " + remoteUser + " tiene la empresa en su atriuto pero su RUT(" + elem + ") no es correcto.");
					} catch (Exception e) {
						// global exception
						// throw new CriticalException();
					}
				}
				System.out.println("OK:");

				profile.setAttribute("empresa", new Long(empresaElegida));
				profile.setAttribute("empresas", listaEmpresas);
				session.setAttribute("opciones", opciones);
				session.setAttribute("ea_user_profile", profile);				

				toPage = "seleccionaEmpresa";					
			}
		}			

		if((profile == null) || (!profile.isValid())) {
			// global exception
			// throw new CriticalException();
		}
		
		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			toPage = "";
		}

		forward = mapping.findForward(toPage);		
		return (forward);
	}
}
