//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.laaraucana.wssinacofi.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "validaNumeroSerieResponse", namespace = "http://servicios.laaraucana.cl/validaNumeroSerie")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validaNumeroSerieResponse", namespace = "http://servicios.laaraucana.cl/validaNumeroSerie")
public class ValidaNumeroSerieResponse {

    @XmlElement(name = "return", namespace = "")
    private cl.laaraucana.wssinacofi.ws.ResponseWS _return;

    /**
     * 
     * @return
     *     returns ResponseWS
     */
    public cl.laaraucana.wssinacofi.ws.ResponseWS getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(cl.laaraucana.wssinacofi.ws.ResponseWS _return) {
        this._return = _return;
    }

}
