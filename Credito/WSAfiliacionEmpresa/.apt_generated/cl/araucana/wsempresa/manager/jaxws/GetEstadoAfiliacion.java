//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.araucana.wsempresa.manager.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getAfiliacionEmpresa", namespace = "http://servicios.laaraucana.cl/afiliacionEmpresa")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAfiliacionEmpresa", namespace = "http://servicios.laaraucana.cl/afiliacionEmpresa", propOrder = {
    "credentials",
    "request"
})
public class GetEstadoAfiliacion {

    @XmlElement(name = "credentials", namespace = "", required = true)
    private cl.araucana.wsempresa.vo.CredentialWS credentials;
    @XmlElement(name = "request", namespace = "", required = true)
    private cl.araucana.wsempresa.vo.RequestWS request;

    /**
     * 
     * @return
     *     returns CredentialWS
     */
    public cl.araucana.wsempresa.vo.CredentialWS getCredentials() {
        return this.credentials;
    }

    /**
     * 
     * @param credentials
     *     the value for the credentials property
     */
    public void setCredentials(cl.araucana.wsempresa.vo.CredentialWS credentials) {
        this.credentials = credentials;
    }

    /**
     * 
     * @return
     *     returns RequestWS
     */
    public cl.araucana.wsempresa.vo.RequestWS getRequest() {
        return this.request;
    }

    /**
     * 
     * @param request
     *     the value for the request property
     */
    public void setRequest(cl.araucana.wsempresa.vo.RequestWS request) {
        this.request = request;
    }

}
