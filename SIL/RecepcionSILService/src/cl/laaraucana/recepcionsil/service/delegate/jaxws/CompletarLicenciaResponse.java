//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.laaraucana.recepcionsil.service.delegate.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "completarLicenciaResponse", namespace = "http://delegate.service.recepcionsil.laaraucana.cl/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "completarLicenciaResponse", namespace = "http://delegate.service.recepcionsil.laaraucana.cl/")
public class CompletarLicenciaResponse {

    @XmlElement(name = "return", namespace = "")
    private cl.laaraucana.recepcionsil.service.vo.SalidaRecepcionSILVO _return;

    /**
     * 
     * @return
     *     returns SalidaRecepcionSILVO
     */
    public cl.laaraucana.recepcionsil.service.vo.SalidaRecepcionSILVO getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(cl.laaraucana.recepcionsil.service.vo.SalidaRecepcionSILVO _return) {
        this._return = _return;
    }

}
