//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package cl.laaraucana.satelites.integracion.ConsultaCreditosPorRutEnAs400;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "ConsultaCreditoPorRutEnAs400", targetNamespace = "http://delegate.toAS.legados.integracion.laaraucana.cl/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ConsultaCreditoPorRutEnAs400 {


    /**
     * 
     * @param arg0
     * @return
     *     returns cl.laaraucana.satelites.integracion.ConsultaCreditosPorRutEnAs400.ConsultaCreditosPorRutSalidaVO
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "consultaCreditosPorRutEnAs400", targetNamespace = "http://delegate.toAS.legados.integracion.laaraucana.cl/", className = "cl.laaraucana.satelites.integracion.ConsultaCreditosPorRutEnAs400.ConsultaCreditosPorRutEnAs400")
    @ResponseWrapper(localName = "consultaCreditosPorRutEnAs400Response", targetNamespace = "http://delegate.toAS.legados.integracion.laaraucana.cl/", className = "cl.laaraucana.satelites.integracion.ConsultaCreditosPorRutEnAs400.ConsultaCreditosPorRutEnAs400Response")
    @Action(input = "http://delegate.toAS.legados.integracion.laaraucana.cl/ConsultaCreditoPorRutEnAs400/consultaCreditosPorRutEnAs400Request", output = "http://delegate.toAS.legados.integracion.laaraucana.cl/ConsultaCreditoPorRutEnAs400/consultaCreditosPorRutEnAs400Response")
    public ConsultaCreditosPorRutSalidaVO consultaCreditosPorRutEnAs400(
        @WebParam(name = "arg0", targetNamespace = "")
        ConsultaCreditosPorRutEntradaVO arg0);

}
