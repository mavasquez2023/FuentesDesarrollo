//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.laaraucana.simulacion.webservices.autogenerado.consultadatosafiliacion;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "ConsultaDatosAfiliacionAs400", targetNamespace = "http://delegate.toAS.legados.integracion.laaraucana.cl/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ConsultaDatosAfiliacionAs400 {


    /**
     * 
     * @param arg0
     * @return
     *     returns cl.laaraucana.integracion.legados.toas.delegate.ConsultaDatosAfiliacionAs400SalidaVO
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "consultaDatosAfiliacionAs400", targetNamespace = "http://delegate.toAS.legados.integracion.laaraucana.cl/", className = "cl.laaraucana.integracion.legados.toas.delegate.ConsultaDatosAfiliacionAs400_Type")
    @ResponseWrapper(localName = "consultaDatosAfiliacionAs400Response", targetNamespace = "http://delegate.toAS.legados.integracion.laaraucana.cl/", className = "cl.laaraucana.integracion.legados.toas.delegate.ConsultaDatosAfiliacionAs400Response")
    @Action(input = "http://delegate.toAS.legados.integracion.laaraucana.cl/ConsultaDatosAfiliacionAs400/consultaDatosAfiliacionAs400Request", output = "http://delegate.toAS.legados.integracion.laaraucana.cl/ConsultaDatosAfiliacionAs400/consultaDatosAfiliacionAs400Response")
    public ConsultaDatosAfiliacionAs400SalidaVO consultaDatosAfiliacionAs400(
        @WebParam(name = "arg0", targetNamespace = "")
        ConsultaDatosAfiliacionAs400EntradaVO arg0);

}
