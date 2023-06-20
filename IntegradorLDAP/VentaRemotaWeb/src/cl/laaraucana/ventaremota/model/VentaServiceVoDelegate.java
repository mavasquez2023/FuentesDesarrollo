package cl.laaraucana.ventaremota.model;

import java.io.Serializable;
import javax.jws.WebService;
import javax.jws.WebParam;


@WebService (targetNamespace="http://model.ventaremota.laaraucana.cl/", serviceName="VentaServiceVoService", portName="VentaServiceVoPort")
public class VentaServiceVoDelegate{

    cl.laaraucana.ventaremota.model.VentaServiceVo _ventaServiceVo = null;

    public String getSalida () {
        return _ventaServiceVo.getSalida();
    }

    public void setSalida (@WebParam(name="salida") String salida) {
        _ventaServiceVo.setSalida(salida);
    }

    public String getCodigo () {
        return _ventaServiceVo.getCodigo();
    }

    public void setCodigo (@WebParam(name="codigo") String codigo) {
        _ventaServiceVo.setCodigo(codigo);
    }

    public VentaServiceVoDelegate() {
        _ventaServiceVo = new cl.laaraucana.ventaremota.model.VentaServiceVo(); }

}