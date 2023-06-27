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

/**
 * @version 	1.0
 * @author
 * 
 */
public class Proxy1Action extends Action {

	static BusinessDelegateFactory factory;

	/* La accion ProxyAction
	   Hay tres posible precedencia que estan identificado por el parametro status
		  status:
			 ""  : 1. un usuario nuevo a acedido, obtiener el perfile de este usuario
				   2. depende de las empresas asociadas al usuario
					  2.1 si no hay ninguna empresas en su atributo despacha pagina de error.
						  Al aceptar la pagina de error, esta pagina invalida usuario al apretar boton aceptar
						  redireccionando a /ibm_security_logout?logoutExitPage=/
					  2.2 si hay una sola empresa en su atributo enviar a la pagina servicio.jsp
					  2.3 si hay mas de una empresas, enviar a la pagina seleccionEmpresa.jsp
			 "c" : viene de la pagina seleccion de empresa. reemplazar atributo empresa en session por nueva empresa
				   luego enviar a la pagina servicio.jsp
			 "r" : usuario solicito cambiar empresas, eliminar empresa desde session,
				   enviar a la pagina seleccionEmpresa.
	*/
	
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		BusinessDelegate delegate = null;

		String toPage = null;		
		
		try {
			InitialContext initCtx = new InitialContext();
			
			HttpSession session = request.getSession();
			ServletContext svrCtx = session.getServletContext();
			
			// obtener propiedades de la aplicacion.
			Properties properties = (Properties) initCtx.lookup("APP_PROPS");
			//Properties porperties = (Properties) svrCtx.getAttribute("APP_PROPS");
			
			// getting business service delegator
			factory = BusinessDelegateFactory.singlton();
			delegate = factory.newInstance("cl.araucana.ea.ctacte.delegate.BusinessDelegateImpl");

			UserProfile profile = (UserProfile) session.getAttribute("ea_user_profile");
			session.removeAttribute("ea_user_profile");
			
			
			String status = (String) PropertyUtils.getSimpleProperty(form, "status");			
			if("r".equalsIgnoreCase(status) && (profile != null)) {
				// eliminar la empresa elegida para seleccionar una nueva
				profile.removeAttribute("empresa");
				session.setAttribute("ea_user_profile", profile);

				// setear valor predeterminado de las opciones
				Long empresaElegida = (Long) profile.getAttribute("empresa");
				PropertyUtils.setSimpleProperty(form, "empresaElegida", empresaElegida);				
				toPage = "seleccionaEmpresa";
			} else if("c".equalsIgnoreCase(status) && (profile != null)) {
				// una empresa fue seleccionada desde la pagina seleccionEmpresa
				// asegura eliminar empresa elegida anterior
				profile.removeAttribute("empresa");
				
				// agregar empresa elegida a la session
				Long empresaElegida = (Long) PropertyUtils.getSimpleProperty(form, "empresaElegida");
				profile.setAttribute("empresa", empresaElegida);
				session.setAttribute("ea_user_profile", profile);
				toPage = "servicio";				
			} else {
				// No tiene perfil aun, crear.
				Principal principal = request.getUserPrincipal();
				String principalName = principal.getName();
				
				// asegurar a eliminar perfil anterior por si a caso.
				session.removeAttribute("ea_user_profile");		
						
				profile = new UserProfile();
				
				// empresas en su atributo
				Long empresa = null;
				Collection empresas = null;
				
				// obtener lista de empresas desde el repositorio de registro de usuario				
				String userRegistry = (String) properties.getProperty("USER_REGISTRY"); 
				//ServletContext ctx = session.getServletContext();
				//String userRegistry = ctx.getInitParameter("USER_REGISTRY");
				if("LDAP".equalsIgnoreCase(userRegistry)) {
					LDAPUserRegistry ldap = new LDAPUserRegistry(
													(String) properties.getProperty("LDAP_HOST"),
													(String) properties.getProperty("LDAP_PORT"),
													(String) properties.getProperty("LDAP_INITIAL_CONTEXT_FACTORY"));
					empresas = ldap.getEmpresasDeUsuario(principalName);					
				} else if("FILE".equalsIgnoreCase(userRegistry)) {
					// development purpose only.
					try {
						empresa = new Long(principalName);
					} catch (NumberFormatException e) {
						throw new Exception();
					}
					empresas = new ArrayList();
					empresas.add(principalName);	 
				}	
				
				Collection opciones = new ArrayList();
				EmpresaTO dtoEmpresa = null;
				long empresaElegida = 0L;
				int count = 1;
				Iterator it = empresas.iterator();
				while(it.hasNext()) {
					String elem = (String) it.next();
					
					Rut rut = null;
					try {
						rut = new Rut(elem);
					} catch (RutInvalidoException e) {
						// ignorar los rut invalidos
						rut = new Rut();
						rut.setId(new Long(elem).longValue());
					}
					
					if(rut != null) {
						try {
							dtoEmpresa = delegate.getEmpresa(new RutTO(rut.getId(), rut.getDv()));
							OpcionTO opcion = new OpcionTO(
														new Long(rut.getId()).toString(), 
														elem + ": " + dtoEmpresa.getNombre());					
							opciones.add(opcion);					
							if(count==1) {
								// primera empresa
								empresaElegida = rut.getId();						
								PropertyUtils.setSimpleProperty(form, "empresaElegida", new Long(rut.getId()));
							}
							count++;
						} catch (Exception e) {
							// ignorar empresas que no figuran en la base de datos 
						}
					}
				}
				
				profile.setAttribute("empresa", new Long(empresaElegida));
				profile.setAttribute("empresas", empresas);
				session.setAttribute("ea_user_profile", profile);
				
				System.out.println("size of empresas: " + empresas.size());
				
				if((empresas == null) || (empresas.size() == 0)) {
					// usuario no tiene empresas en su atributo
					toPage = "error";
					// usuario tiene una sola empresa en su atributo
				} else if(empresas.size() == 1) {
					toPage = "servicio";
				} else if(empresas.size() > 1) {
					// usuario tiene mas de una empresa en su atributo
					it = opciones.iterator();
					while(it.hasNext()) {
						OpcionTO opcion = (OpcionTO) it.next();
						System.out.println("opcion: " + opcion);
					}
					session.setAttribute("opciones", opciones);
					toPage = "seleccionaEmpresa";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("==== error en proxy ==== ");
			e.printStackTrace(System.out);
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
		}

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			forward = mapping.findForward(toPage);
		} else {
			forward = mapping.findForward(toPage);
		}
		
		return (forward);
	}
	
	private void setEmpresas() {
		
	}
}
