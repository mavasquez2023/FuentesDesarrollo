/**
 * 
 */
package cl.araucana.ldap.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import cl.araucana.ldap.ws.vo.CredentialWS;
import cl.araucana.ldap.ws.vo.RequestRolesWS;
import cl.araucana.ldap.ws.vo.RequestUsersWS;
import cl.araucana.ldap.ws.vo.ResponseRolesWS;
import cl.araucana.ldap.ws.vo.ResponseUsersWS;

/**
 * @author J-Factory
 *
 */
@WebService( targetNamespace = "http://servicios.laaraucana.cl/rolesLDAP")
public interface GestionRolesAPP {
	@WebMethod()
	public abstract String autenticacionWS( @WebParam(name="credentials") @XmlElement(name="credentials", required=true) CredentialWS param);
	@WebMethod()
	public abstract ResponseRolesWS getRoles_inApp(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="request") @XmlElement(name="request", required=true) RequestRolesWS request);
	@WebMethod()
	public abstract ResponseUsersWS getUsers_inRole(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="request") @XmlElement(name="request", required=true) RequestUsersWS request);

}
