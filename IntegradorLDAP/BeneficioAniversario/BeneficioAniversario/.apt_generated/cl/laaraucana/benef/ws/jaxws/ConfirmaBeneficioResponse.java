//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.laaraucana.benef.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "confirmaBeneficioResponse", namespace = "http://servicio.laaraucana.cl/benef")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "confirmaBeneficioResponse", namespace = "http://servicio.laaraucana.cl/benef")
public class ConfirmaBeneficioResponse {

    @XmlElement(name = "return", namespace = "")
    private cl.laaraucana.benef.vo.BeneficioResponseVO _return;

    /**
     * 
     * @return
     *     returns BeneficioResponseVO
     */
    public cl.laaraucana.benef.vo.BeneficioResponseVO getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(cl.laaraucana.benef.vo.BeneficioResponseVO _return) {
        this._return = _return;
    }

}
