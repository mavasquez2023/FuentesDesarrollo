package cl.laaraucana.servicio.mandato;

public class MandatoServicioImplProxy implements cl.laaraucana.servicio.mandato.MandatoServicioImpl {
  private String _endpoint = null;
  private cl.laaraucana.servicio.mandato.MandatoServicioImpl mandatoServicioImpl = null;
  
  public MandatoServicioImplProxy() {
    _initMandatoServicioImplProxy();
  }
  
  public MandatoServicioImplProxy(String endpoint) {
    _endpoint = endpoint;
    _initMandatoServicioImplProxy();
  }
  
  private void _initMandatoServicioImplProxy() {
    try {
      mandatoServicioImpl = (new cl.laaraucana.servicio.mandato.MandatoServicioServiceLocator()).getMandatoServicioPort();
      if (mandatoServicioImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)mandatoServicioImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)mandatoServicioImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (mandatoServicioImpl != null)
      ((javax.xml.rpc.Stub)mandatoServicioImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cl.laaraucana.servicio.mandato.MandatoServicioImpl getMandatoServicioImpl() {
    if (mandatoServicioImpl == null)
      _initMandatoServicioImplProxy();
    return mandatoServicioImpl;
  }
  
  public cl.laaraucana.servicio.mandato.RespuestaInsertMandato ingresarMandato(cl.laaraucana.servicio.mandato.AUTENTICACION autenticacion, cl.laaraucana.servicio.mandato.CuentaBanco cuenta) throws java.rmi.RemoteException{
    if (mandatoServicioImpl == null)
      _initMandatoServicioImplProxy();
    return mandatoServicioImpl.ingresarMandato(autenticacion, cuenta);
  }
  
  public cl.laaraucana.servicio.mandato.CuentaVigente consultarMandato(cl.laaraucana.servicio.mandato.AUTENTICACION autenticacion, cl.laaraucana.servicio.mandato.CONSULTAR cliente) throws java.rmi.RemoteException{
    if (mandatoServicioImpl == null)
      _initMandatoServicioImplProxy();
    return mandatoServicioImpl.consultarMandato(autenticacion, cliente);
  }
  
  public cl.laaraucana.servicio.mandato.RespuestaInsertMandato revocarMandato(cl.laaraucana.servicio.mandato.AUTENTICACION autenticacion, cl.laaraucana.servicio.mandato.REVOCAR cliente) throws java.rmi.RemoteException{
    if (mandatoServicioImpl == null)
      _initMandatoServicioImplProxy();
    return mandatoServicioImpl.revocarMandato(autenticacion, cliente);
  }
  
  
}