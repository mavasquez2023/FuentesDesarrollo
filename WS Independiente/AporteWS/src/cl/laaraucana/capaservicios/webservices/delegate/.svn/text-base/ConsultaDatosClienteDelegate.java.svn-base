package cl.laaraucana.capaservicios.webservices.delegate;

import cl.laaraucana.capaservicios.manager.ConsultaDatosClienteMGR;
import cl.laaraucana.capaservicios.webservices.vo.ConsultaDatosCliente.ActDatosClienteVO;
import cl.laaraucana.capaservicios.webservices.vo.ConsultaDatosCliente.ActualizarDatosClienteOut;
import cl.laaraucana.capaservicios.webservices.vo.ConsultaDatosCliente.ConsultaDatosClienteIn;
import cl.laaraucana.capaservicios.webservices.vo.ConsultaDatosCliente.ConsultaDatosClienteOut;
import javax.jws.WebService;
import javax.jws.WebParam;


@WebService (targetNamespace="http://delegate.webservices.capaservicios.laaraucana.cl/", serviceName="ConsultaDatosClienteService", portName="ConsultaDatosClientePort", wsdlLocation="WEB-INF/wsdl/ConsultaDatosClienteService.wsdl")
public class ConsultaDatosClienteDelegate{

    cl.laaraucana.capaservicios.webservices.service.ConsultaDatosCliente _consultaDatosCliente = null;

    public ConsultaDatosClienteOut obtenerDatosCliente (@WebParam(name="entrada") ConsultaDatosClienteIn entrada) {
        return _consultaDatosCliente.obtenerDatosCliente(entrada);
    }

    public ActualizarDatosClienteOut actualizarDatosCliente (@WebParam(name="entrada") ActDatosClienteVO entrada) {
        return _consultaDatosCliente.actualizarDatosCliente(entrada);
    }

    public ConsultaDatosClienteDelegate() {
        _consultaDatosCliente = new cl.laaraucana.capaservicios.webservices.service.ConsultaDatosCliente(); }

}