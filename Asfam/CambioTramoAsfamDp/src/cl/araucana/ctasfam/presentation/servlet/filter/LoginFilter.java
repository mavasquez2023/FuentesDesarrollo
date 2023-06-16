package cl.araucana.ctasfam.presentation.servlet.filter;

//Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 24/07/2012 9:17:27
//Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
//Decompiler options: packimports(3) 
//Source File Name:   LoginFilter.java



import cl.araucana.common.Profile;
import cl.araucana.common.Rut;
import cl.araucana.common.RutInvalidoException;
import cl.araucana.common.UserProfile;
import cl.araucana.core.registry.Enterprise;
import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;
import cl.araucana.core.util.UserPrincipal;
import cl.araucana.core.util.http.DecodedHttpServletRequest;
import cl.araucana.core.util.http.HttpUserPrincipal;
import cl.araucana.core.util.http.Router;
import cl.araucana.core.util.http.Utils;
import cl.araucana.ctasfam.business.service.impl.PropuestaRentasServiceImpl;
import cl.araucana.ctasfam.integration.jdbc.dao.AraucanaJdbcDao;
import cl.araucana.ctasfam.integration.jdbc.dao.impl.Mejoras2016DaoImpl;
import cl.araucana.ctasfam.integration.jdbc.exception.RentaPropuestasException;
import cl.araucana.ctasfam.presentation.struts.resources.ServiceLocatorWeb;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;
import cl.araucana.ea.credito.dto.OpcionTO;
import cl.araucana.ea.ctacte.dto.EmpresaTO;
import cl.araucana.ea.ctacte.dto.RutTO;

import java.io.IOException;
import java.io.PrintStream;
import java.security.Principal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.ActionMessage;

//Referenced classes of package cl.araucana.core.util.http:
//         Utils, DecodedHttpServletRequest, HttpUserPrincipal, Router



public class LoginFilter
 implements Filter
{
	 
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

 public LoginFilter()
 {
 }

 public void init(FilterConfig filterConfig)
     throws ServletException
 {
     ServletContext servletContext = filterConfig.getServletContext();
     appName = servletContext.getServletContextName();
     String sDebug = filterConfig.getInitParameter("debug");
     debug = sDebug != null && sDebug.equals("true");
     String sDump = filterConfig.getInitParameter("dump");
     dump = sDump != null && sDump.equals("true");
     String sUseRedirector = filterConfig.getInitParameter("useRedirector");
     useRedirector = sUseRedirector != null && sUseRedirector.equals("true");
     String sSecurityMode = filterConfig.getInitParameter("securityMode");
     if(sSecurityMode == null)
         throw new ServletException("Missed 'securityMode' filter init parameter");
     if(sSecurityMode.equals("j2ee"))
         securityMode = 0;
     else
     if(sSecurityMode.equals("programmed"))
     {
         loginPage = filterConfig.getInitParameter("loginPage");
         //loginPage = filterConfig.getInitParameter("logon");
         if(loginPage == null)
             throw new ServletException("Missed 'loginPage' filter init parameter");
         loginErrorPage = filterConfig.getInitParameter("loginErrorPage");
         //loginErrorPage = filterConfig.getInitParameter("logonerror");
         if(loginPage == null)
             throw new ServletException("Missed 'loginErrorPage' filter init parameter");
         securityMode = 1;
     } else
     if(sSecurityMode.equals("none"))
         securityMode = 2;
     else
         throw new ServletException("Unknown security mode '" + sSecurityMode + "'");
     debug(">> init");
     debug("   securityMode   = " + sSecurityMode);
     debug("   dump request   = " + dump);
     debug("   use redirector = " + useRedirector);
     if(securityMode == 1)
     {
         debug("   loginPage      = " + loginPage);
         debug("   loginErrorPage = " + loginErrorPage);
     }
     debug("<< init");
     System.out.println("aqui1");
 }

 public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
     throws IOException, ServletException
 {
	 

     debug(">> doFilter");
     if(dump)
     {
         log("DUMP ORIGINAL REQUEST");
         Utils.dumpRequest((HttpServletRequest)request);
     }
     DecodedHttpServletRequest httpRequest = new DecodedHttpServletRequest((HttpServletRequest)request, useJ2EESecurityMode());
     HttpServletResponse httpResponse = (HttpServletResponse)response;
     if(dump)
     {

		  
         log("DUMP DECODED REQUEST");
         Utils.dumpRequest(httpRequest);
     }
     Utils.checkSpecialParameters(httpRequest);
     httpResponse.setHeader("Location", httpRequest.getRequestURL().toString());
     if(securityMode == 0)
         J2EESecurityMode(httpRequest, httpResponse, filterChain);
     else
     if(securityMode == 1)
         ProgrammedSecurityMode(httpRequest, httpResponse, filterChain);
     else
         NoneSecurityMode(httpRequest, httpResponse, filterChain);
     debug("<< doFilter");
     
     //-------my code 24-07-2012 Alexis Mendez--------------------------------------
    
     
 	HttpSession session = httpRequest.getSession();
	AraucanaJdbcDao dao=new AraucanaJdbcDao();
	Profile profile = (Profile) session.getAttribute("ea_user_profile");
	UserRegistryConnection connection = null;
	//Principal principal = (Principal) session.getAttribute("userPrincipal");
	Principal principal =httpRequest.getUserPrincipal();
	if (principal != null) {
		System.out.println("principal " + principal.getName());
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
			//Collection enterprises=connection.getEnterprises("PorEmpAdhe","PorEmpAdheEnc");
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
				
				  List result = new ArrayList();
//				  ServiceLocatorWeb serviceLocalWeb = new ServiceLocatorWeb((HttpServletRequest) request);
//				  String dataSource =  serviceLocalWeb.getApplicationProperties().getProperty(
//							"jndiDataSource");
//				  PropuestaRentasServiceImpl propuestaRentasServiceImpl;
//					propuestaRentasServiceImpl = new PropuestaRentasServiceImpl(dataSource);
//					result = propuestaRentasServiceImpl.consultaUsuariosAdmin();
				  
				  Mejoras2016DaoImpl daoMejoras = new Mejoras2016DaoImpl(); 
					try {
						result= daoMejoras.selectUsuariosAdmin();
					} catch (RentaPropuestasException e) {
						e.printStackTrace();
					} 
					
					
					int flagAdmin = 0;
					
					if (result.size() > 0) {
						
							String[] rutAdmin = result.get(0).toString().split(";");
							
							for (int i = 0; i < rutAdmin.length; i++) {
								
								if (rutAdmin[i].equalsIgnoreCase(rut)) {
									
									flagAdmin = 1; 
									
									session.setAttribute("flagAdmin", flagAdmin);
									
									debug("login iniciado como administrador con rut: "+rutAdmin[i]);
								}
								
								
							}
							
							if (flagAdmin == 0) {
								
								debug("login iniciado, no administrador");
								
							}
												
						}
						
				httpRequest.getSession().setAttribute("edocs_encargado", new Encargado(rut, nomEnc, empresas));
				
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
		return;
	}
     
	 String timeStamp = null;
	    Locale locale = httpRequest.getLocale();
	    DateFormat df= DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.FULL, locale);
	    timeStamp = df.format(new Date());
	    httpRequest.getSession().setAttribute("LOGINTIME", timeStamp);
     //---------------------------------------------------
 }

 private void J2EESecurityMode(DecodedHttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
     throws IOException, ServletException
 {
     long ti = System.currentTimeMillis();
     filterChain.doFilter(request, response);
     long tf = System.currentTimeMillis();
     UserPrincipal userPrincipal = Utils.getUserPrincipal(request, true);
     request.untrack();
     debug("   login user = " + userPrincipal.getName() + " " + "time = " + (tf - ti) + " ms");
     setPrincipal(request, userPrincipal);
     System.out.println("aqui3");
 }

 private void ProgrammedSecurityMode(DecodedHttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
     throws IOException, ServletException
 {
	 System.out.println("aqui4");
     String principalName = getPrincipalName(request);
     if(principalName != null)
     {
         request.untrack();
         filterChain.doFilter(request, response);
         return;
     }
     UserPrincipal userPrincipal = Utils.getUserPrincipal(request, false);
     request.untrack();
     if(userPrincipal.getName() == null || userPrincipal.getPassword() == null)
     {
         RequestDispatcher dispatcher = request.getRequestDispatcher(loginPage);
         debug("forward login -> " + loginPage);
         dispatcher.forward(request, response);
         return;
     }
     long ti = System.currentTimeMillis();
     UserRegistryConnection connection = null;
     try
     {
         connection = new UserRegistryConnection(userPrincipal);
     }
     catch(Exception e)
     {
         debug("login failed. [" + e.getMessage() + "]");
         RequestDispatcher dispatcher = request.getRequestDispatcher(loginErrorPage);
         debug("forward login error -> " + loginErrorPage);
         dispatcher.forward(request, response);
         return;
     }
     finally
     {
         if(connection != null)
             try
             {
                 connection.close();
             }
             catch(Exception se) { }
     }
     long tf = System.currentTimeMillis();
     debug("   login user= [" + userPrincipal.getName() + "] " + "time= " + (tf - ti) + " ms ");
     setPrincipal(request, userPrincipal);
     filterChain.doFilter(request, response);
 }

 private void NoneSecurityMode(DecodedHttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
     throws IOException, ServletException
 {
	 System.out.println("aqui5");
     String principalName = getPrincipalName(request);
     if(principalName == null)
     {
         UserPrincipal userPrincipal = Utils.getUserPrincipal(request, false);
         if(userPrincipal != null)
             setPrincipal(request, userPrincipal);
     }
     request.untrack();
     filterChain.doFilter(request, response);
 }

 private void setPrincipal(HttpServletRequest request, UserPrincipal userPrincipal)
     throws IOException, ServletException
 {
	
	 System.out.println("aqui6");
     HttpSession session = request.getSession();
     Principal principal = new HttpUserPrincipal(userPrincipal.getName());
     session.setAttribute("userPrincipal", principal);
     if(useRedirector)
         Router.inject(request, userPrincipal);
     
     System.out.println("setPrincipal");
 }

 private String getPrincipalName(HttpServletRequest request)
     throws IOException, ServletException
 {
	 System.out.println("aqui7");
     HttpSession session = request.getSession();
     Principal principal = (Principal)session.getAttribute("userPrincipal");
     return principal != null ? principal.getName() : null;
 }

 public void destroy()
 {
 }

 private boolean useJ2EESecurityMode()
 {
     return securityMode == 0;
 }

 private void debug(String message)
 {
     if(debug)
         log(message);
 }

 private void log(String message)
 {
     System.out.println(appName + ".LoginFilter: " + message);
 }
 
  

 public static final int SECURITY_MODE_J2EE = 0;
 public static final int SECURITY_MODE_PROGRAMMED = 1;
 public static final int SECURITY_MODE_NONE = 2;
 public static final String userPrincipalAttributeName = "userPrincipal";
 private String appName;
 private int securityMode;
 private boolean debug;
 private boolean dump;
 private boolean useRedirector;
 private String loginPage;
 private String loginErrorPage;
}