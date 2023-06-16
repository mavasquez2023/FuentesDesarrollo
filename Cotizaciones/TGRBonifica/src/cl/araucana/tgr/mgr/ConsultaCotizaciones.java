/**
 * 
 */
package cl.araucana.tgr.mgr;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import com.ibm.trl.soap.SOAPException;

import cl.araucana.tgr.vo.CredentialWSTGR;
import cl.araucana.tgr.vo.RequestWSTGR;
import cl.araucana.tgr.vo.RequestsWSTGR;
import cl.araucana.tgr.vo.ResponseWSTGR;

/**
 * @author Claudio Lillo
 *
 */
@WebService( targetNamespace = "http://servicios.laaraucana.cl/bonificaTGR")
@HandlerChain(file = "/etc/serversoaphandler.xml")
public interface ConsultaCotizaciones {
	@WebMethod()
	public String autenticacionWS(@WebParam(name="credentials") @XmlElement(name="credentials", required=true) CredentialWSTGR param) throws SOAPException;
	@WebMethod()
	public abstract Boolean getStatus(@WebParam(name="token") @XmlElement(name="token", required=true) String token) throws SOAPException;
	@WebMethod()
	public abstract ResponseWSTGR getCotizacionesByTrabajador(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="request") @XmlElement(name="request", required=true) RequestWSTGR request) throws SOAPException;
	@WebMethod()
	public abstract ResponseWSTGR getCotizacionesByTrabajadores(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="request") @XmlElement(name="request", required=true) RequestsWSTGR request) throws SOAPException;
	
}
