//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.araucana.ldap.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "autenticacionUWS", namespace = "http://servicios.laaraucana.cl/usuariosLDAP")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "autenticacionUWS", namespace = "http://servicios.laaraucana.cl/usuariosLDAP")
public class AutenticacionUWS {

    @XmlElement(name = "credentials", namespace = "", required = true)
    private cl.araucana.ldap.ws.vo.CredentialWS credentials;

    /**
     * 
     * @return
     *     returns CredentialWS
     */
    public cl.araucana.ldap.ws.vo.CredentialWS getCredentials() {
        return this.credentials;
    }

    /**
     * 
     * @param credentials
     *     the value for the credentials property
     */
    public void setCredentials(cl.araucana.ldap.ws.vo.CredentialWS credentials) {
        this.credentials = credentials;
    }

}
