//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.araucana.bonomarzo.mgr.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getBonoTrabajadorResponse", namespace = "http://servicios.laaraucana.cl/bonomarzo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBonoTrabajadorResponse", namespace = "http://servicios.laaraucana.cl/bonomarzo")
public class GetBonoTrabajadorResponse {

    @XmlElement(name = "return", namespace = "")
    private cl.araucana.bonomarzo.vo.ResponseWS _return;

    /**
     * 
     * @return
     *     returns ResponseWS
     */
    public cl.araucana.bonomarzo.vo.ResponseWS getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(cl.araucana.bonomarzo.vo.ResponseWS _return) {
        this._return = _return;
    }

}
