//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.laaraucana.benef.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "autenticacionWS", namespace = "http://servicio.laaraucana.cl/benef")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "autenticacionWS", namespace = "http://servicio.laaraucana.cl/benef")
public class AutenticacionWS {

    @XmlElement(name = "credentials", namespace = "", required = true)
    private cl.laaraucana.benef.vo.CredencialesVO credentials;

    /**
     * 
     * @return
     *     returns CredencialesVO
     */
    public cl.laaraucana.benef.vo.CredencialesVO getCredentials() {
        return this.credentials;
    }

    /**
     * 
     * @param credentials
     *     the value for the credentials property
     */
    public void setCredentials(cl.laaraucana.benef.vo.CredencialesVO credentials) {
        this.credentials = credentials;
    }

}
