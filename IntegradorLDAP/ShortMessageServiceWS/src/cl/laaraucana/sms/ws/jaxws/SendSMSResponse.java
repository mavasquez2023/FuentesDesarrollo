
package cl.laaraucana.sms.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "sendSMSResponse", namespace = "http://ws.sms.laaraucana.cl/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendSMSResponse", namespace = "http://ws.sms.laaraucana.cl/")
public class SendSMSResponse {

    @XmlElement(name = "return", namespace = "")
    private cl.laaraucana.sms.domain.exchange.MessageOutput _return;

    /**
     * 
     * @return
     *     returns MessageOutput
     */
    public cl.laaraucana.sms.domain.exchange.MessageOutput getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(cl.laaraucana.sms.domain.exchange.MessageOutput _return) {
        this._return = _return;
    }

}
