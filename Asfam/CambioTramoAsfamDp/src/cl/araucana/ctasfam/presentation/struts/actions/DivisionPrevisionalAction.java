package cl.araucana.ctasfam.presentation.struts.actions;

 

import java.security.Principal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping; 

import cl.araucana.common.Profile;
import cl.araucana.common.Rut;
import cl.araucana.common.RutInvalidoException;
import cl.araucana.common.UserProfile;
import cl.araucana.core.registry.Enterprise;
import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;
import cl.araucana.ctasfam.business.service.impl.PropuestaRentasServiceImpl;
import cl.araucana.ctasfam.business.to.AceptaPropuestaForm;
import cl.araucana.ctasfam.integration.jdbc.dao.AraucanaJdbcDao;
import cl.araucana.ctasfam.presentation.struts.resources.ServiceLocatorWeb;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;
import cl.araucana.ea.credito.dto.OpcionTO;
import cl.araucana.ea.ctacte.dto.EmpresaTO;
import cl.araucana.ea.ctacte.dto.RutTO;
 

public class DivisionPrevisionalAction extends Action {
	private static final Log log = LogFactory
			.getLog(DivisionPrevisionalAction.class);

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
				 e.printStackTrace();
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

	
	public ActionForward execute(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		AceptaPropuestaForm acepta=new AceptaPropuestaForm();
		 Properties Param = new Properties();
		 Param.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		String proceso=Param.getProperty("PROCESO");
		 Object encargado=null;
		
		  acepta.setProceso(proceso);
		  encargado=request.getSession().getAttribute("edocs_encargado");
		  
		  
		//---------------security
		  while(encargado==null){
			  
			  HttpSession session = request.getSession();
				AraucanaJdbcDao dao=new AraucanaJdbcDao();
				Profile profile = (Profile) session.getAttribute("ea_user_profile");
				UserRegistryConnection connection = null;
				//Principal principal = (Principal) session.getAttribute("userPrincipal");
				Principal principal =request.getUserPrincipal();
				System.out.println("principal " + principal.getName());
				if (principal != null) {
					String appId = "CambioTramo";
					String appSecundary = "PorEmpAdhe";
					String userID = principal.getName();
				
					try {
						connection = new UserRegistryConnection();
						connection.setUserID(userID);
						User user = connection.getUser();
						Collection userRoles = connection.getUserRoles(userID,appId);
						Collection userRolesSecundary = connection.getUserRoles(userID, appSecundary);
						 
						
						userRoles.addAll(userRolesSecundary);
						userRoles. contains("ValidUser");
						if(userRoles.contains("Consulta1")) {
							session.setAttribute("profile_type", new Integer(1));
						} else {
							session.setAttribute("profile_type", new Integer(0));
						}
						
						String fullName = user.getFullName();
						//System.out.println("dao " + dao.getEmpresas(userID).size()); 
					  //	Collection enterprises=connection.getEnterprises("PorEmpAdhe","PorEmpAdheEnc");
				        Collection enterprises = dao.getEmpresas(userID);  				
						//-------------------------------------------
						System.out.println(enterprises.size());
						System.out.println("aqui2");
						profile = createProfile(userID, fullName, enterprises);
						profile.setAttribute("roles", userRoles);
						session.setAttribute("opciones", profile.getAttribute("opcionesEmpresa"));
						session.setAttribute("ea_user_profile", profile);

						
						
						try {
							String rut = (String) profile.getId();
							String nomEnc = (String) profile.getAttribute("nombreCompleto");
							List empresas = (List) profile.getAttribute("empresas");
							request.getSession().setAttribute("edocs_encargado", new Encargado(rut, nomEnc, empresas));
							 
						} catch (Exception e) {
						e.printStackTrace();
						 
						}
					} catch (UserRegistryException e) {
						e.printStackTrace();
					 
						
						session.invalidate();
						throw new ServletException(e.getLocalizedMessage());
					} finally {
						if (connection != null) {
							try {
								connection.close();
							} catch (Exception e) {
								 
							}
						}
					}
				} else {
					session.invalidate();
					return null;
				}
			     
				 String timeStamp = null;
				    Locale locale = request.getLocale();
				    DateFormat df= DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.FULL, locale);
				    timeStamp = df.format(new Date());
				    request.getSession().setAttribute("LOGINTIME", timeStamp);
			  
				    encargado=request.getSession().getAttribute("edocs_encargado");
		  }
		  
		//----------------
		 
		
		request.setAttribute("proceso", acepta);
		
		return mapping.findForward("showHome");
	}

 
}