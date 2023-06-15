package cl.laaraucana.satelites.main.actions;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.lautaro.xi.BS.WEB_Mobile.QueryCompContHeaderOUTBindingStub;

import cl.araucana.core.registry.AppRole;
import cl.araucana.core.registry.Application;
import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;
import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.Utils.PerfilarSingleton;
import cl.laaraucana.satelites.main.forms.LoginForm;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;
import cl.laaraucana.servicios.rolesLDAP.CredentialWSTGR;
import cl.laaraucana.servicios.rolesLDAP.RequestRolesWS;
import cl.laaraucana.servicios.rolesLDAP.RolesAPP;
import cl.laaraucana.servicios.rolesLDAP.RolesLDAPPortBindingStub;

public class LoginAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forward = "";
		LoginForm form = (LoginForm) _form;
		ActionErrors errores = form.validate(mapping, request);
		try {
			HttpSession sesion = request.getSession();
			User userInfo = (User) sesion.getAttribute("userInfo");
			//Si usuario no está autenticado
			if (userInfo == null) {		
				//Autentica usuario en Ldap
				UserRegistryConnection urConnection = null;
				try {
					String username= form.getJ_username().replaceAll("\\.", "");
					urConnection = new UserRegistryConnection(username,form.getJ_password());
					userInfo = urConnection.getUser();
					sesion.setAttribute("userInfo", userInfo);
					
					String[] roles= getRolesLDAP(username);
					for (int i = 0; i < roles.length; i++) {
						sesion.setAttribute(roles[i], "X");
					}
					forward = "success";
					sesion.setAttribute("cotizaciones_pagadas", ServiciosConst.URL_INF_COT_PAGADAS);
					sesion.setAttribute("cotizaciones_trabajador", ServiciosConst.URL_INF_COT_TRABAJADOR);
					sesion.setAttribute("deuda_previsional", ServiciosConst.URL_INF_DEU_PREVISIONAL);
					String perfilamiento= PerfilarSingleton.getInstance().getPerfilamientoActivo();
					sesion.setAttribute("perfilamiento", perfilamiento);
				} catch (UserRegistryException e) {
					e.printStackTrace();
						try {
							urConnection.close();
						} catch (Exception a) {}
						//Obtener mensaje desde archivo propertie
					errores.add("rut", new ActionMessage("error.login.failed"));
					saveErrors(request, errores);
					forward = "loginPage";
				}
			}else{
				forward = "success";
			}
		} catch (Exception e) {
			errores.add("rut", new ActionMessage("error.login.failed"));
			saveErrors(request, errores);
			forward = "loginPage";
		}
		return mapping.findForward(forward);
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
