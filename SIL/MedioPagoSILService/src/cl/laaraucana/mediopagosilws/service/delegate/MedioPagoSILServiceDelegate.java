package cl.laaraucana.mediopagosilws.service.delegate;

import org.apache.log4j.Logger;
import cl.laaraucana.mediopagosilws.core.manager.MainManager;
import cl.laaraucana.mediopagosilws.service.vo.EntradaVO;
import cl.laaraucana.mediopagosilws.service.vo.SalidaVO;
import cl.laaraucana.mediopagosilws.utils.Constante;
import javax.jws.WebService;


@WebService (targetNamespace="http://delegate.service.mediopagosilws.laaraucana.cl/", serviceName="MedioPagoSILService", portName="MedioPagoSILServicePort", wsdlLocation="WEB-INF/wsdl/MedioPagoSILService.wsdl")
public class MedioPagoSILServiceDelegate{

    cl.laaraucana.mediopagosilws.service.MedioPagoSILService _medioPagoSILService = null;

    public SalidaVO consultaMedioPago (EntradaVO entrada) {
        return _medioPagoSILService.consultaMedioPago(entrada);
    }

    public MedioPagoSILServiceDelegate() {
        _medioPagoSILService = new cl.laaraucana.mediopagosilws.service.MedioPagoSILService(); }

}