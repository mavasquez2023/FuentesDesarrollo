//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.laaraucana.ventaremota.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "informarCreditoResponse", namespace = "http://servicio.laaraucana.cl/ingresarVenta")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "informarCreditoResponse", namespace = "http://servicio.laaraucana.cl/ingresarVenta")
public class InformarCreditoResponse {

    @XmlElement(name = "return", namespace = "")
    private cl.laaraucana.ventaremota.model.VentaServiceVo _return;

    /**
     * 
     * @return
     *     returns VentaServiceVo
     */
    public cl.laaraucana.ventaremota.model.VentaServiceVo getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(cl.laaraucana.ventaremota.model.VentaServiceVo _return) {
        this._return = _return;
    }

}