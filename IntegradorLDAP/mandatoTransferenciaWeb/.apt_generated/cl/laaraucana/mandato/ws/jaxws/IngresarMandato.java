//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.laaraucana.mandato.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "ingresarMandato", namespace = "http://servicio.laaraucana.cl/mandato")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ingresarMandato", namespace = "http://servicio.laaraucana.cl/mandato", propOrder = {
    "autenticacion",
    "cuenta"
})
public class IngresarMandato {

    @XmlElement(name = "autenticacion", namespace = "", required = true)
    private cl.laaraucana.transferencias.banco.vo.CredencialesVO autenticacion;
    @XmlElement(name = "cuenta", namespace = "", required = true)
    private cl.laaraucana.transferencias.banco.vo.CuentaRequestWSVO cuenta;

    /**
     * 
     * @return
     *     returns CredencialesVO
     */
    public cl.laaraucana.transferencias.banco.vo.CredencialesVO getAutenticacion() {
        return this.autenticacion;
    }

    /**
     * 
     * @param autenticacion
     *     the value for the autenticacion property
     */
    public void setAutenticacion(cl.laaraucana.transferencias.banco.vo.CredencialesVO autenticacion) {
        this.autenticacion = autenticacion;
    }

    /**
     * 
     * @return
     *     returns CuentaRequestWSVO
     */
    public cl.laaraucana.transferencias.banco.vo.CuentaRequestWSVO getCuenta() {
        return this.cuenta;
    }

    /**
     * 
     * @param cuenta
     *     the value for the cuenta property
     */
    public void setCuenta(cl.laaraucana.transferencias.banco.vo.CuentaRequestWSVO cuenta) {
        this.cuenta = cuenta;
    }

}
