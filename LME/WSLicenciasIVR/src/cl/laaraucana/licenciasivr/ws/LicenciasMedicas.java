/**
 * 
 */
package cl.laaraucana.licenciasivr.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.soap.SOAPException;



/**
 * @author Claudio Lillo
 *
 */
@WebService( targetNamespace = "http://servicios.laaraucana.cl/findLicencias")
public interface LicenciasMedicas {
	
	@WebMethod()
	public abstract ResponseWS findLicencias(RequestWS request) throws SOAPException;

}
