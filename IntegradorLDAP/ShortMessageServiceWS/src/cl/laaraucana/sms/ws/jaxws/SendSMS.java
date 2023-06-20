
package cl.laaraucana.sms.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "sendSMS", namespace = "http://ws.sms.laaraucana.cl/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendSMS", namespace = "http://ws.sms.laaraucana.cl/")
public class SendSMS {

    @XmlElement(name = "arg0", namespace = "")
    private cl.laaraucana.sms.domain.exchange.MessageInput arg0;

    /**
     * 
     * @return
     *     returns MessageInput
     */
    public cl.laaraucana.sms.domain.exchange.MessageInput getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(cl.laaraucana.sms.domain.exchange.MessageInput arg0) {
        this.arg0 = arg0;
    }

}
