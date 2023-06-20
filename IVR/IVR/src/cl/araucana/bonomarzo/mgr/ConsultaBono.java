/**
 * 
 */
package cl.araucana.bonomarzo.mgr;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import com.ibm.trl.soap.SOAPException;

import cl.araucana.bonomarzo.vo.RequestWS;
import cl.araucana.bonomarzo.vo.ResponseWS;

/**
 * @author Claudio Lillo
 *
 */
@WebService( targetNamespace = "http://servicios.laaraucana.cl/bonomarzo")

public interface ConsultaBono {
	@WebMethod()
	public abstract ResponseWS getBonoTrabajador( @WebParam(name="request") @XmlElement(name="request", required=true) RequestWS request) throws SOAPException;
	
}
