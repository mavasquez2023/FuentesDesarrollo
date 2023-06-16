package cl.araucana.ctasfam.presentation.servlet.filter;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.araucana.common.Profile;
import cl.araucana.common.Rut;
import cl.araucana.common.RutInvalidoException;
import cl.araucana.common.UserProfile;
import cl.araucana.core.registry.Enterprise;
import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;
import cl.araucana.ea.credito.dto.OpcionTO;
import cl.araucana.ea.ctacte.dto.EmpresaTO;
import cl.araucana.ea.ctacte.dto.RutTO;

public class LoginFilterTemp implements Filter {
	
	private static final Log log = LogFactory.getLog(LoginFilterTemp.class);

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		log.info("---	Init doFilter()	---");
		arg2.doFilter(arg0, arg1);
		HttpServletRequest request = (HttpServletRequest) arg0;
		//HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();
		Profile profile = (Profile) session.getAttribute("ea_user_profile");
		if (profile != null) {
			return;
		}
		//Principal principal = (Principal) session.getAttribute("userPrincipal");
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			String appId = "CambioTramo";
			String appSecundary = "PorEmpAdhe";
			String userID = principal.getName();
			UserRegistryConnection connection = null;
			try {
				connection = new UserRegistryConnection();
				connection.setUserID(userID);
				User user = connection.getUser();
				 
				Collection userRoles = connection.getUserRoles(userID, appId);
				Collection userRolesSecundary = connection.getUserRoles(userID, appSecundary);
				userRoles.addAll(userRolesSecundary);
				userRoles.add("ValidUser");
				if(userRoles.contains("Consulta1")) {
					session.setAttribute("profile_type", new Integer(1));
				} else {
					session.setAttribute("profile_type", new Integer(0));
				}
				String fullName = user.getFullName();
				Collection enterprises = connection.getEnterprises("PorEmpAdhe", "PorEmpAdheEnc");
				//Collection enterprises = connection.getEnterprises("PorEmpAdhe","Encargado");
				profile = createProfile(userID, fullName, enterprises);
				profile.setAttribute("roles", userRoles);
				session.setAttribute("opciones", profile.getAttribute("opcionesEmpresa"));
				session.setAttribute("ea_user_profile", profile);
			} catch (UserRegistryException e) {
				log.error("Error: Al validar usuario en LDAP, " + e.getLocalizedMessage(), e);
				session.invalidate();
				throw new ServletException(e.getLocalizedMessage());
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (Exception e) {
						log.error("Error: Al cerrar conexion LDAP, " + e.getLocalizedMessage(), e);
					}
				}
			}
		} else {
			session.invalidate();
			return;
		}
	}
	
	private Profile createProfile(String userID, String nombreCompleto,
			Collection c) {
		Profile profile = new UserProfile(userID);
		Collection empresas = new ArrayList();
		TreeSet opcionesEmpresa = new TreeSet();
		Iterator it = c.iterator();
		while (it.hasNext()) {
			Enterprise enterprise = (Enterprise) it.next();
			Rut rutEmpresa = null;
			try {
				rutEmpresa = new Rut(enterprise.getID());
			} catch (RutInvalidoException e) {
				log.error("EA: Rut de empresa '" + enterprise.getID()
						+ "'"
						+ "inválido en 'createProfile'. Empresa fue excluida.", e);
				continue;
			}
			EmpresaTO empresa = new EmpresaTO(new RutTO(rutEmpresa.getId(),
					rutEmpresa.getDv()), enterprise.getName());
			empresas.add(empresa);
			opcionesEmpresa.add(new OpcionTO(rutEmpresa.getId() + "",
					rutEmpresa.getFormattedRut() + "-" + rutEmpresa.getDv()
							+ ": " + enterprise.getName()));
		}
		profile.setAttribute("nombreCompleto", nombreCompleto);
		if (empresas.size() > 0) {
			OpcionTO firstEmpresa = (OpcionTO) opcionesEmpresa.first();
			Long defaultEmpresaID = new Long(firstEmpresa.getIdValue());
			profile.setAttribute("empresa", defaultEmpresaID);
		}
		profile.setAttribute("empresas", empresas);
		profile.setAttribute("opcionesEmpresa", opcionesEmpresa);
		profile.setStatus(Profile.VALID_STATUS);
		return profile;
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

} 
