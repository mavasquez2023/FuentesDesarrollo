/**
 * 
 */
package cl.araucana.ldap.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.Logger;

import cl.araucana.ldap.api.ProxyLDAP;
import cl.araucana.ldap.ws.util.TokenFactory;
import cl.araucana.ldap.ws.util.Utiles;
import cl.araucana.ldap.ws.vo.CredentialWS;
import cl.araucana.ldap.ws.vo.RequestRolesWS;
import cl.araucana.ldap.ws.vo.RequestUsersWS;
import cl.araucana.ldap.ws.vo.ResponseRolesWS;
import cl.araucana.ldap.ws.vo.ResponseUsersWS;


/**
 * @author J-Factory
 *
 */
@WebService( portName = "rolesLDAPPort",
serviceName = "rolesLDAPService",
targetNamespace = "http://servicios.laaraucana.cl/rolesLDAP"
)
public class GestionRolesAPPImpl implements GestionRolesAPP {
	static ResourceBundle credentials = ResourceBundle.getBundle("etc/cred_ws");
	private static final int  ERROR_TOKEN= -1;
	private static final int  ERROR_USER= 0;
	private static final int  OK= 1;
	@Resource
    private WebServiceContext wsCtx;
	
	Logger log = Logger.getLogger(this.getClass());
	
	/* (non-Javadoc)
	 * @see cl.araucana.ldap.ws.GestionRolesAPP#autenticacionWS(cl.araucana.ldap.ws.vo.CredentialWS)
	 */

	@WebMethod(action="http://servicios.laaraucana.cl/rolesLDAP/autenticacionWS",  operationName="autenticacionWS")
	public String autenticacionWS(@WebParam(name="credentials") @XmlElement(name="credentials", required=true) CredentialWS param){
		MessageContext mctx = wsCtx.getMessageContext();
		log.info("EN autenticacionWS");
		
		String usuario_settings= credentials.getString("credential.userName");
		String password_settings= credentials.getString("credential.password");
		String usuario_param= param.getUser();
		String password_param= param.getPassword();
		if(usuario_param.equals(usuario_settings) && password_param.equals(password_settings)){
			String remoteip= ((HttpServletRequest)(wsCtx.getMessageContext().get(MessageContext.SERVLET_REQUEST))).getRemoteAddr();
			String token_encode= Utiles.generaToken(remoteip, param.getUser());
			String fecha= Utiles.getFecha();
			TokenFactory.getInstance().addToken(param.getUser(), token_encode);
			return token_encode;

		}else{
			log.warn("Usuario o Contraseña No Válido");
		}
		return "Usuario o Contraseña No Válido";
		
	}

	/* (non-Javadoc)
	 * @see cl.araucana.ldap.ws.GestionRolesAPP#getRoles_inApp(java.lang.String, cl.araucana.ldap.ws.vo.RequestRolesWS)
	 */
	@WebMethod(action="http://servicios.laaraucana.cl/rolesLDAP/getRolesinApp",  operationName="getRolesinApp")
	public ResponseRolesWS getRoles_inApp(String token, RequestRolesWS request){
		ResponseRolesWS rolesAPP= new ResponseRolesWS();
		
		if(!isValidToken(token).equals("")){
			@SuppressWarnings("unchecked")
			List<String> roles= (List<String>)ProxyLDAP.getRolesUserinApp(request.getUserId(), request.getAppId());
			rolesAPP.setCodigo_respuesta(OK);
			rolesAPP.setRoles(roles);
		}else{
			rolesAPP.setCodigo_respuesta(ERROR_TOKEN);
		}
		return rolesAPP;
	}

	/* (non-Javadoc)
	 * @see cl.araucana.ldap.ws.GestionRolesAPP#getUsers_inRole(java.lang.String, cl.araucana.ldap.ws.vo.RequestUsersWS)
	 */
	@WebMethod(action="http://servicios.laaraucana.cl/rolesLDAP/getUsersinRole",  operationName="getUsersinRole")
	public ResponseUsersWS getUsers_inRole(String token, RequestUsersWS request) {
		ResponseUsersWS usersRol= new ResponseUsersWS();
		if(!isValidToken(token).equals("")){ 
			@SuppressWarnings("unchecked")
			List<String> users= (List<String>)ProxyLDAP.getUsersAppRoles(request.getAppId(), request.getRolId());
			usersRol.setUsuarios(users);
			usersRol.setCodigo_respuesta(OK);
		}else{
			usersRol.setCodigo_respuesta(ERROR_TOKEN);
		}
		return usersRol;
	}
	
	@WebMethod(exclude= true)
	private String isValidToken(String token){
		String usuario="";
		try {
			String[] data= Utiles.decodeToken(token);
			String remoteip= ((HttpServletRequest)(wsCtx.getMessageContext().get(MessageContext.SERVLET_REQUEST))).getRemoteAddr();
			
			String tokenFactory= TokenFactory.getInstance().getToken(data[1]);
			String dataTokenFacory[]= Utiles.decodeToken(tokenFactory);
			
			String fecha= dataTokenFacory[2];
			usuario=dataTokenFacory[1];
			if(fecha!=null){
				if(Utiles.validaFecha(fecha)){
					if(remoteip.equals(data[0])){
						return usuario;
					}
				}else{
					 TokenFactory.getInstance().delToken(data[0]);
				}
			}
			/*MessageContext mctx = wsCtx.getMessageContext();
			String remoteip= ((HttpServletRequest)(wsCtx.getMessageContext().get(MessageContext.SERVLET_REQUEST))).getRemoteAddr();
			String username = mctx.get("userid").toString();
			String password = mctx.get("password").toString();
			if(username.equals("WSAraucanaTGR")){
				if(password.equals("srv12345")){
					return true;
				}
			}*/
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
