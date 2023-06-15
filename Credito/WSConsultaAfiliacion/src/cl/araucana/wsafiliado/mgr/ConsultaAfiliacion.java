/**
 * 
 */
package cl.araucana.wsafiliado.mgr;

import java.sql.SQLException;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import com.ibm.trl.soap.SOAPException;

import cl.araucana.wsafiliado.vo.CredentialWS;
import cl.araucana.wsafiliado.vo.RequestWS;
import cl.araucana.wsafiliado.vo.ResponseDataWS;
import cl.araucana.wsafiliado.vo.ResponseWS;

/**
 * @author Claudio Lillo
 *
 */
@WebService( targetNamespace = "http://servicios.laaraucana.cl/estadoAfiliacion")
@HandlerChain(file = "/etc/serversoaphandler.xml")
public interface ConsultaAfiliacion {
	@WebMethod()
	public abstract String autenticacionWS(@WebParam(name="credentials") @XmlElement(name="credentials", required=true) CredentialWS param) throws SOAPException, SQLException;
	@WebMethod()
	public abstract Boolean getStatus(@WebParam(name="token") @XmlElement(name="token", required=true) String token) throws SOAPException;
	@WebMethod()
	public abstract ResponseWS getEstadoAfiliacion(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="request") @XmlElement(name="request", required=true) RequestWS request) throws SOAPException;
	@WebMethod()
	public abstract ResponseWS isAfiliadoTitular(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="request") @XmlElement(name="request", required=true) RequestWS request) throws SOAPException;
	@WebMethod()
	public abstract ResponseDataWS getDatosAfiliacion(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="request") @XmlElement(name="request", required=true) RequestWS request) throws SOAPException;

}
