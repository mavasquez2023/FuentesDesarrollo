//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.laaraucana.pagoenexceso.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "consultarPagoEnExceso", namespace = "http://service.pagoenexceso.laaraucana.cl/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarPagoEnExceso", namespace = "http://service.pagoenexceso.laaraucana.cl/")
public class ConsultarPagoEnExceso {

    @XmlElement(name = "rut", namespace = "")
    private String rut;

    /**
     * 
     * @return
     *     returns String
     */
    public String getRut() {
        return this.rut;
    }

    /**
     * 
     * @param rut
     *     the value for the rut property
     */
    public void setRut(String rut) {
        this.rut = rut;
    }

}
