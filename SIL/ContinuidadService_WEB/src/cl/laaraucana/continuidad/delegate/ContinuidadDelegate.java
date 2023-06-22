package cl.laaraucana.continuidad.delegate;

import cl.laaraucana.continuidad.manager.ContinuidadMgr;
import cl.laaraucana.continuidad.service.vo.EntradaContRentas;
import cl.laaraucana.continuidad.service.vo.SalidaContRentas;
import javax.jws.WebService;
import javax.jws.WebParam;


@WebService (targetNamespace="http://delegate.continuidad.laaraucana.cl/", serviceName="ContinuidadService", portName="ContinuidadPort", wsdlLocation="WEB-INF/wsdl/ContinuidadService.wsdl")
public class ContinuidadDelegate{

    cl.laaraucana.continuidad.service.Continuidad _continuidad = null;

    public SalidaContRentas continuidad (@WebParam(name="entrada") EntradaContRentas entrada) {
        return _continuidad.continuidad(entrada);
    }

    public ContinuidadDelegate() {
        _continuidad = new cl.laaraucana.continuidad.service.Continuidad(); }

}