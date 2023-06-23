package cl.laaraucana.capaservicios.webservices.delegate;

import javax.jws.WebService;
import javax.jws.WebParam;


@WebService (targetNamespace="http://delegate.webservices.capaservicios.laaraucana.cl/", serviceName="ConsultaCreditosEspVigentesService", portName="ConsultaCreditosEspVigentesPort", wsdlLocation="WEB-INF/wsdl/ConsultaCreditosEspVigentesService.wsdl")
public class ConsultaCreditosEspVigentesDelegate{

    cl.laaraucana.capaservicios.webservices.service.ConsultaCreditosEspVigentes _consultaCreditosEspVigentes = null;

    public Object obtenerCreditosVigEsp (@WebParam(name="rut") String rut) {
        return _consultaCreditosEspVigentes.obtenerCreditosVigentes(rut);
    }

    public ConsultaCreditosEspVigentesDelegate() {
        _consultaCreditosEspVigentes = new cl.laaraucana.capaservicios.webservices.service.ConsultaCreditosEspVigentes(); }

}