
package cl.laaraucana.sms.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "statusSMSResponse", namespace = "http://ws.sms.laaraucana.cl/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "statusSMSResponse", namespace = "http://ws.sms.laaraucana.cl/")
public class StatusSMSResponse {

    @XmlElement(name = "return", namespace = "")
    private cl.laaraucana.sms.domain.exchange.StatusSMSOutput _return;

    /**
     * 
     * @return
     *     returns StatusSMSOutput
     */
    public cl.laaraucana.sms.domain.exchange.StatusSMSOutput getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(cl.laaraucana.sms.domain.exchange.StatusSMSOutput _return) {
        this._return = _return;
    }

}
