//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.araucana.ldap.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getUsersinRoleResponse", namespace = "http://servicios.laaraucana.cl/rolesLDAP")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUsersinRoleResponse", namespace = "http://servicios.laaraucana.cl/rolesLDAP")
public class GetUsers_inRoleResponse {

    @XmlElement(name = "return", namespace = "")
    private cl.araucana.ldap.ws.vo.ResponseUsersWS _return;

    /**
     * 
     * @return
     *     returns ResponseUsersWS
     */
    public cl.araucana.ldap.ws.vo.ResponseUsersWS getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(cl.araucana.ldap.ws.vo.ResponseUsersWS _return) {
        this._return = _return;
    }

}
