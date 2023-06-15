/**
 * 
 */
package cl.araucana.wscreditosocial.mgr;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import com.ibm.trl.soap.SOAPException;
import cl.araucana.wscreditosocial.vo.RequestWS;
import cl.araucana.wscreditosocial.vo.ResponseWS;

/**
 * @author Claudio Lillo
 *
 */
@WebService( targetNamespace = "http://servicios.laaraucana.cl/simuladorCreditoSocial")
public interface SimuladorCreditoSocial {
	@WebMethod()
	public abstract ResponseWS getSimuladorCreditoSocial(@WebParam(name="request") @XmlElement(name="request", required=true) RequestWS request) throws SOAPException;
	
}
