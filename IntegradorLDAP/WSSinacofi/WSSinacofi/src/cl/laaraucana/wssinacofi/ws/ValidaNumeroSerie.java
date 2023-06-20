/**
 * 
 */
package cl.laaraucana.wssinacofi.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import com.ibm.trl.soap.SOAPException;


/**
 * @author Claudio Lillo
 *
 */
@WebService( targetNamespace = "http://servicios.laaraucana.cl/validaNumeroSerie")
public interface ValidaNumeroSerie {
	
	@WebMethod()
	public abstract ResponseWS validaNumeroSerie(CredencialesWS param, RequestWS request) throws SOAPException;

}
