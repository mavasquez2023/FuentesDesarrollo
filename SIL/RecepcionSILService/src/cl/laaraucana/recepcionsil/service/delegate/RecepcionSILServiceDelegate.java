package cl.laaraucana.recepcionsil.service.delegate;


import cl.laaraucana.recepcionsil.service.vo.EntradaRecepcionSILVO;
import cl.laaraucana.recepcionsil.service.vo.LicenciaNivel2VO;
import cl.laaraucana.recepcionsil.service.vo.SalidaRecepcionSILVO;


import javax.jws.WebService;


@WebService (targetNamespace="http://delegate.service.recepcionsil.laaraucana.cl/", serviceName="RecepcionSILService", portName="RecepcionSILServicePort", wsdlLocation="WEB-INF/wsdl/RecepcionSILService.wsdl")
public class RecepcionSILServiceDelegate{

    cl.laaraucana.recepcionsil.service.RecepcionSILService _recepcionSILService = null;

    public SalidaRecepcionSILVO ingresoLicencia (EntradaRecepcionSILVO entrada) {
        return _recepcionSILService.ingresoLicencia(entrada);
    }
    
    public SalidaRecepcionSILVO completarLicencia (LicenciaNivel2VO entrada) {
        return _recepcionSILService.completarLicencia(entrada);
    }
    
    public RecepcionSILServiceDelegate() {
        _recepcionSILService = new cl.laaraucana.recepcionsil.service.RecepcionSILService(); }

}