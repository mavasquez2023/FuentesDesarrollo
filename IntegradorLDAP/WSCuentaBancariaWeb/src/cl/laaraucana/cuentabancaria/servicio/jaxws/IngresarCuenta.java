//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.laaraucana.cuentabancaria.servicio.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "ingresarCuenta", namespace = "http://servicio.laaraucana.cl/cuentabancaria")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ingresarCuenta", namespace = "http://servicio.laaraucana.cl/cuentabancaria", propOrder = {
    "token",
    "cuenta"
})
public class IngresarCuenta {

    @XmlElement(name = "token", namespace = "", required = true)
    private String token;
    @XmlElement(name = "cuenta", namespace = "", required = true)
    private cl.laaraucana.cuentabancaria.vo.CuentaRequestWSVO cuenta;

    /**
     * 
     * @return
     *     returns String
     */
    public String getToken() {
        return this.token;
    }

    /**
     * 
     * @param token
     *     the value for the token property
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 
     * @return
     *     returns CuentaRequestWSVO
     */
    public cl.laaraucana.cuentabancaria.vo.CuentaRequestWSVO getCuenta() {
        return this.cuenta;
    }

    /**
     * 
     * @param cuenta
     *     the value for the cuenta property
     */
    public void setCuenta(cl.laaraucana.cuentabancaria.vo.CuentaRequestWSVO cuenta) {
        this.cuenta = cuenta;
    }

}
