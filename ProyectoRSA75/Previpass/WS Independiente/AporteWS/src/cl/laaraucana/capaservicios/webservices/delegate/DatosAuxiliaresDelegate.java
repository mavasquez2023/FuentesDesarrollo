package cl.laaraucana.capaservicios.webservices.delegate;

import cl.laaraucana.capaservicios.manager.DatosAuxiliaresMGR;
import cl.laaraucana.capaservicios.webservices.vo.DatosAuxiliares.ListaCodigosSTLOut;
import javax.jws.WebService;
import javax.jws.WebParam;


@WebService (targetNamespace="http://delegate.webservices.capaservicios.laaraucana.cl/", serviceName="DatosAuxiliaresService", portName="DatosAuxiliaresPort", wsdlLocation="WEB-INF/wsdl/DatosAuxiliaresService.wsdl")
public class DatosAuxiliaresDelegate{

    cl.laaraucana.capaservicios.webservices.service.DatosAuxiliares _datosAuxiliares = null;

    public ListaCodigosSTLOut getListaBancos () {
        return _datosAuxiliares.getListaBancos();
    }

    public ListaCodigosSTLOut getTiposCuenta () {
        return _datosAuxiliares.getTiposCuenta();
    }

    public ListaCodigosSTLOut getComunasProvRegiones (@WebParam(name="tipoEjecucion") String tipoEjecucion) {
        return _datosAuxiliares.getComunasProvRegiones(tipoEjecucion);
    }

    public DatosAuxiliaresDelegate() {
        _datosAuxiliares = new cl.laaraucana.capaservicios.webservices.service.DatosAuxiliares(); }

}