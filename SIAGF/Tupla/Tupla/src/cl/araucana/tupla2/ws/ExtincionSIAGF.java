/**
 * 
 */
package cl.araucana.tupla2.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import cl.araucana.wssiagf.vo.*;

import com.ibm.trl.soap.SOAPException;


/**
 * @author Claudio Lillo
 *
 */
@WebService( targetNamespace = "http://servicios.laaraucana.cl/extincionSIAGF")
public interface ExtincionSIAGF {
	@WebMethod()
	public abstract String autenticacionWS(@WebParam(name="credentials") @XmlElement(name="credentials", required=true) CredentialWS param) throws SOAPException;
	@WebMethod()
	public abstract Boolean getStatus(@WebParam(name="token") @XmlElement(name="token", required=true) String token) throws SOAPException;
	@WebMethod()
	public abstract ResponseWS setExtingueCausantes(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="request") @XmlElement(name="request", required=true) RequestWS request) throws SOAPException;
/*	@WebMethod()
	public abstract ResponseDataWS getDatosAfiliacion(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="request") @XmlElement(name="request", required=true) RequestWS request) throws SOAPException;
*/
}
