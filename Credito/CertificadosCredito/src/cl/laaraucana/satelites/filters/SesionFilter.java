package cl.laaraucana.satelites.filters;

import java.io.IOException;
import java.rmi.RemoteException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.araucana.core.registry.User;
import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.Utils.PerfilarSingleton;
import cl.laaraucana.satelites.webservices.client.InfoAfiliado.ClienteInfoAfiliado;
import cl.laaraucana.satelites.webservices.client.InfoAfiliado.VO.EntradaInfoAfiliadoVO;
import cl.laaraucana.satelites.webservices.client.InfoAfiliado.VO.SalidainfoAfiliadoVO;

import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasWS;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;
import cl.laaraucana.servicios.rolesLDAP.CredentialWSTGR;
import cl.laaraucana.servicios.rolesLDAP.RequestRolesWS;
import cl.laaraucana.servicios.rolesLDAP.RolesAPP;
import cl.laaraucana.servicios.rolesLDAP.RolesLDAPPortBindingStub;
/**
 * Servlet Filter implementation class CertificadosFilter
 */
public class SesionFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		HttpSession sesion = request.getSession();
		Principal principal = request.getUserPrincipal();
		if (principal != null && principal.getName() != null) {
			String username = principal.getName();
			sesion.setAttribute("user", username);
			
			String[] roles= getRolesLDAP(username);
			for (int i = 0; i < roles.length; i++) {
				sesion.setAttribute(roles[i], "X");
			}
			sesion.setAttribute("cotizaciones_pagadas", ServiciosConst.URL_INF_COT_PAGADAS);
			sesion.setAttribute("cotizaciones_trabajador", ServiciosConst.URL_INF_COT_TRABAJADOR);
			sesion.setAttribute("deuda_previsional", ServiciosConst.URL_INF_DEU_PREVISIONAL);
			String perfilamiento= PerfilarSingleton.getInstance().getPerfilamientoActivo();
			sesion.setAttribute("perfilamiento", perfilamiento);
			
			User userInfo=new User();
			userInfo.setID(username);
			userInfo.setFirstName("");
			userInfo.setLastName("");
			// se llaman a los servicios enviado por parametro el rut
			String nombre=username;
			try {
				ClienteInfoAfiliado infoafil= new ClienteInfoAfiliado();
				EntradaInfoAfiliadoVO entradainfoVO = new EntradaInfoAfiliadoVO();
				entradainfoVO.setRut(username);
				SalidainfoAfiliadoVO salidainfoVO= (SalidainfoAfiliadoVO)infoafil.call(entradainfoVO);
				if(salidainfoVO.getCodigoError().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS ) && salidainfoVO.getNombreCompleto()!=null){
					nombre= salidainfoVO.getNombreCompleto();
					userInfo.setFirstName(nombre);
					sesion.setAttribute("user", nombre);
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
			sesion.setAttribute("userInfo", userInfo);
		} 
		
		
		
		
/*		Principal userPrincipal = request.getUserPrincipal();
		String userID = null;
		//Verifica si el usuario está autorizado
		if (userPrincipal == null || (userID = userPrincipal.getName()) == null) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/main/login.jsp");
			request.setAttribute("mensaje", "El usuario no está autenticado");
			requestDispatcher.forward(request, response);
			return;
		}*/
		/*String origen= request.getParameter("origen");
		if(origen==null){
			origen= (String)sesion.getAttribute("origen");
		}else{
			sesion.setAttribute("origen", "DD");
		}
		origen= (origen==null)?"":origen;
		System.out.println("origen=" + origen);*/
/*		System.out.println("rut=" + request.getParameter("rut"));
		//Autentica usuario en Ldap
		String uc = request.getParameter("uc");
		System.out.println("uc=" + uc);
		if(uc!=null){
			UserRegistryConnection urConnection = null;
			try {
				UserPrincipal newUser = UserPrincipal.decodeUserCredentials(uc);
				System.out.println("user=" + newUser.getName());
				urConnection = new UserRegistryConnection(newUser.getName(),newUser.getPassword());
				userInfo = urConnection.getUser();
				sesion.setAttribute("userInfo", userInfo);

			} catch (Exception e) {
				System.out.println("Error, mensaje="+ e.getMessage());
			}
		}
		if (userInfo==null){
			userInfo = (User) sesion.getAttribute("userInfo");
		}
		System.out.println("userInfo=" + userInfo);
		//Si usuario no está autenticado
		if (userInfo == null) {
			System.out.println("redireccionado login");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/main/login.do");
			requestDispatcher.forward(request, response);
			return;
		}*/
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
	
    public SesionFilter() {
    }
    
    public void destroy() {
	}
    
    public String[] getRolesLDAP(String rut) throws RemoteException{
		RolesLDAPPortBindingStub stub= new RolesLDAPPortBindingStub();
		String ep= Configuraciones.getConfig("ep.rolesLDAP");
		stub._setProperty(RolesLDAPPortBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		
		CredentialWSTGR credentials= new CredentialWSTGR();
		String username = ServiciosConst.LDAP_USERNAME;
		String password= ServiciosConst.LDAP_PASSWORD;
		credentials.setUser(username);
		credentials.setPassword(password);
		
		String appId= ServiciosConst.LDAP_APPID;
		String token= stub.autenticacionWS(credentials);
		RequestRolesWS req= new RequestRolesWS();
		req.setUserId(rut);
		req.setAppId(appId);
		RolesAPP respuesta= stub.getRolesinApp(token, req);
		if(respuesta.getCODIGO_RESPUESTA()==1){
			return respuesta.getROLES();
		}
		
		return null;
	}
}
