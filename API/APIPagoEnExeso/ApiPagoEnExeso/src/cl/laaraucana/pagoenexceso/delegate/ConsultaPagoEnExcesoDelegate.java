/*package cl.laaraucana.pagoenexceso.delegate;

import cl.laaraucana.pagoenexceso.manager.PagoEnExcesoMGR;
import cl.laaraucana.pagoenexceso.persistencia.vo.ConsultaPagoEnExcesoOut;
import javax.jws.WebService;
import javax.jws.WebParam;


@WebService (targetNamespace="http://delegate.pagoenexceso.laaraucana.cl/", serviceName="ConsultaPagoEnExcesoService", portName="ConsultaPagoEnExcesoPort", wsdlLocation="WEB-INF/wsdl/ConsultaPagoEnExcesoService.wsdl")
public class ConsultaPagoEnExcesoDelegate{

    cl.laaraucana.pagoenexceso.service.ConsultaPagoEnExceso _consultaPagoEnExceso = null;

    public ConsultaPagoEnExcesoOut consultarPagoEnExceso (@WebParam(name="rut") String rut) {
        return _consultaPagoEnExceso.consultarPagoEnExceso(rut);
    }

    public ConsultaPagoEnExcesoDelegate() {
        _consultaPagoEnExceso = new cl.laaraucana.pagoenexceso.service.ConsultaPagoEnExceso(); }

}*/