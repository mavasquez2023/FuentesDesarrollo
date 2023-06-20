
package cl.laaraucana.sms.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "statusSMS", namespace = "http://ws.sms.laaraucana.cl/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "statusSMS", namespace = "http://ws.sms.laaraucana.cl/")
public class StatusSMS {

    @XmlElement(name = "arg0", namespace = "")
    private cl.laaraucana.sms.domain.exchange.StatusSMSInput arg0;

    /**
     * 
     * @return
     *     returns StatusSMSInput
     */
    public cl.laaraucana.sms.domain.exchange.StatusSMSInput getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(cl.laaraucana.sms.domain.exchange.StatusSMSInput arg0) {
        this.arg0 = arg0;
    }

}
