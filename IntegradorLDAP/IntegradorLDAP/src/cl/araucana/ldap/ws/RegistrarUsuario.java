/**
 * 
 */
package cl.araucana.ldap.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import cl.araucana.ldap.ws.vo.CredentialWS;
import cl.araucana.ldap.ws.vo.NotificarWS;
import cl.araucana.ldap.ws.vo.RequestPasswordWS;
import cl.araucana.ldap.ws.vo.RequestUsuarioWS;
import cl.araucana.ldap.ws.vo.ResponseUsuarioWS;

/**
 * @author J-Factory
 *
 */
@WebService( targetNamespace = "http://servicios.laaraucana.cl/usuariosLDAP")
public interface RegistrarUsuario {
	@WebMethod()
	public abstract String autenticacionUWS( @WebParam(name="credentials") @XmlElement(name="credentials", required=true) CredentialWS param);
	@WebMethod()
	public abstract ResponseUsuarioWS addUsuario(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="request") @XmlElement(name="request", required=true) RequestUsuarioWS request, @WebParam(name="notificar") @XmlElement(name="notificar", required=true) NotificarWS notificar);
	@WebMethod()
	public abstract ResponseUsuarioWS delUsuario(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="rut") @XmlElement(name="rut", required=true) String rut);
	@WebMethod()
	public abstract ResponseUsuarioWS changePassword(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="request") @XmlElement(name="request", required=true) RequestPasswordWS request, @WebParam(name="notificar") @XmlElement(name="notificar", required=true) NotificarWS notificar);
	
}
