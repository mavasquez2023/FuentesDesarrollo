package cl.araucana.ctasfam.presentation.struts.actions;

import java.security.Principal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TreeSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import cl.araucana.ctasfam.business.service.PropuestaRentasService;
import cl.araucana.ctasfam.business.service.impl.PropuestaRentasServiceImpl;
import cl.araucana.ctasfam.business.to.AceptaPropuestaForm;
import cl.araucana.ctasfam.business.to.ParametrosTO;
import cl.araucana.ctasfam.integration.jdbc.dao.AraucanaJdbcDao;
import cl.araucana.ctasfam.integration.jdbc.dao.impl.Mejoras2016DaoImpl;
import cl.araucana.ctasfam.integration.jdbc.dao.impl.RentaPropuestaDaoImpl;
import cl.araucana.ctasfam.integration.jdbc.exception.RentaPropuestasException;
import cl.araucana.ctasfam.presentation.struts.resources.ServiceLocatorWeb;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;
import cl.araucana.ctasfam.resources.util.CustomDataSource;
import cl.araucana.ctasfam.resources.util.Parametros;
import cl.araucana.ctasfam.resources.util.Utils;
import cl.araucana.ea.credito.dto.OpcionTO;
import cl.araucana.ea.ctacte.dto.EmpresaTO;
import cl.araucana.ea.ctacte.dto.RutTO;

public class InitPageAction extends Action {
	
	
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

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 
		
		/*Properties Config = new Properties();
		Utils util = new Utils();
		
		
		Config.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("configuracion.properties"));
	*/
		//código de control de sesiones
		if(Parametros.getInstance().getParam().getControlSesiones().equals("true"))
		{
			//codigo que permite nos permitirá saber si el servidor tiene el máximo de sesiones abiertas 
			ServletContext contexto = servlet.getServletContext(); 
			Integer usuarioConectados= null; 
			
			if(contexto != null)
			{
				synchronized (contexto) {
					usuarioConectados= (Integer) contexto.getAttribute("usuariosConectados"); 
				}
			}
			else
			{
				System.out.println("contexto NULO");
				usuarioConectados=new Integer(0);
			}
			
			HttpSession sesion = request.getSession();
			
			if(usuarioConectados.intValue() <= Integer.valueOf(Parametros.getInstance().getParam().getMaxSesiones()).intValue() && sesion.getAttribute("numeroSesion") == null){
				sesion.setAttribute("numeroSesion", String.valueOf(usuarioConectados.intValue()));
			}

			if(usuarioConectados.intValue() > Integer.valueOf(Parametros.getInstance().getParam().getMaxSesiones()).intValue()){
				if(sesion.getAttribute("numeroSesion") == null){ 
					System.out.println("sesion denegada");
					request.getRequestDispatcher("/sesionDenegada.jsp").forward(request, response);
					//return mapping.findForward("onFailure");
					return null;
				}
			}
		}
		
		/* Properties Param = new Properties();
		 Param.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		 String proceso=Param.getProperty("PROCESO");
		 */
		 AceptaPropuestaForm aceptar= new AceptaPropuestaForm();
		 String periodo=Parametros.getInstance().getParam().getPeriodoProceso();
		 periodo= periodo.substring(0, 4);
		 aceptar.setProceso("Período " + periodo + " a " + (Integer.parseInt(periodo)+1));
		 Object encargado=request.getSession().getAttribute("edocs_encargado");
		 String rol= (String)request.getSession().getAttribute("rol");
		 request.getSession().setAttribute("proceso", aceptar);
		 
			//---------------security
		 int c=0;
		  while(encargado==null){
			  
			  HttpSession session = request.getSession();
				AraucanaJdbcDao dao=new AraucanaJdbcDao();
				Profile profile = (Profile) session.getAttribute("ea_user_profile");
				UserRegistryConnection connection = null;
				//Principal principal = (Principal) session.getAttribute("userPrincipal");
				Principal principal =request.getUserPrincipal();
				//System.out.println("principal " + principal.getName());
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
						 
						
						String fullName = user.getFullName();
						//System.out.println("dao " + dao.getEmpresas(userID).size()); 
					    Collection enterprises=connection.getEnterprises("PorEmpAdhe","PorEmpAdheEnc");
				        //Collection enterprises = dao.getEmpresas(userID);  				
						//-------------------------------------------
						//System.out.println(enterprises.size());

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
				    c++;
			  
				    encargado=request.getSession().getAttribute("edocs_encargado");
		  }
		  
		 // System.out.println("LDAP envio edocs_encargado nulo " + c + " veces");
		//----------------
		 
		 /* Map<String, String> result = null;
		  Mejoras2016DaoImpl daoMejoras = new Mejoras2016DaoImpl();
		  try {
			  result = daoMejoras.selectFecha();
			} catch (RentaPropuestasException e) {
				e.printStackTrace();
			} 
		  */
		  String procesoAbierto = Parametros.getInstance().getParam().getFechaApertura();
		  String procesoCerrado = Parametros.getInstance().getParam().getFechaCierre();
		  
		  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		  Date fecIni = sdf.parse(procesoAbierto.trim() + " 00:00:00");
		  Date fecFin = sdf.parse(procesoCerrado.trim() + " 23:59:59");
		  request.getSession().setAttribute("parametros", Parametros.getInstance().getParam());
		if(rol.equals("Ejecutivo")){
			
			return mapping.findForward("showHome");
		}else{
			if(fecIni.before(new Date()) && fecFin.after(new Date())){
				return mapping.findForward("onSuccess");
			}else{
				if(fecFin.before(new Date())){
					return mapping.findForward("onClose");
				}else {
					return mapping.findForward("onBeforeOpening");
				}
			}
		}

	}
	
	
}