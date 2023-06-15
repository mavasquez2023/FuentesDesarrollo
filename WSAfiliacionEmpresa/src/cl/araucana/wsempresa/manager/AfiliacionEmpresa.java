/**
 * 
 */
package cl.araucana.wsempresa.manager;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import cl.araucana.wsempresa.vo.CredentialWS;
import cl.araucana.wsempresa.vo.RequestWS;
import cl.araucana.wsempresa.vo.ResponseWS;

import com.ibm.trl.soap.SOAPException;


/**
 * @author Claudio Lillo
 *
 */
@WebService( targetNamespace = "http://servicios.laaraucana.cl/afiliacionEmpresa")
public interface AfiliacionEmpresa {
	
	@WebMethod()
	public abstract ResponseWS getEstadoAfiliacion(@WebParam(name="credentials") @XmlElement(name="credentials", required=true) CredentialWS param, @WebParam(name="request") @XmlElement(name="request", required=true) RequestWS request) throws SOAPException;

}
