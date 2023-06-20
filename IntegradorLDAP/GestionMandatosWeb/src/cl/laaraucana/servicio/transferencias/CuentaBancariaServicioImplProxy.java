package cl.laaraucana.servicio.transferencias;

public class CuentaBancariaServicioImplProxy implements cl.laaraucana.servicio.transferencias.CuentaBancariaServicioImpl {
  private String _endpoint = null;
  private cl.laaraucana.servicio.transferencias.CuentaBancariaServicioImpl cuentaBancariaServicioImpl = null;
  
  public CuentaBancariaServicioImplProxy() {
    _initCuentaBancariaServicioImplProxy();
  }
  
  public CuentaBancariaServicioImplProxy(String endpoint) {
    _endpoint = endpoint;
    _initCuentaBancariaServicioImplProxy();
  }
  
  private void _initCuentaBancariaServicioImplProxy() {
    try {
      cuentaBancariaServicioImpl = (new cl.laaraucana.servicio.transferencias.CuentaBancariaServicioServiceLocator()).getCuentaBancariaServicioPort();
      if (cuentaBancariaServicioImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)cuentaBancariaServicioImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)cuentaBancariaServicioImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (cuentaBancariaServicioImpl != null)
      ((javax.xml.rpc.Stub)cuentaBancariaServicioImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cl.laaraucana.servicio.transferencias.CuentaBancariaServicioImpl getCuentaBancariaServicioImpl() {
    if (cuentaBancariaServicioImpl == null)
      _initCuentaBancariaServicioImplProxy();
    return cuentaBancariaServicioImpl;
  }
  
  public java.lang.String autenticacionWS(cl.laaraucana.servicio.transferencias.CredentialWS credentials) throws java.rmi.RemoteException{
    if (cuentaBancariaServicioImpl == null)
      _initCuentaBancariaServicioImplProxy();
    return cuentaBancariaServicioImpl.autenticacionWS(credentials);
  }
  
  public cl.laaraucana.servicio.transferencias.LOG_RESPUESTA ingresarCuenta(java.lang.String token, cl.laaraucana.servicio.transferencias.CuentaBanco cuenta) throws java.rmi.RemoteException{
    if (cuentaBancariaServicioImpl == null)
      _initCuentaBancariaServicioImplProxy();
    return cuentaBancariaServicioImpl.ingresarCuenta(token, cuenta);
  }
  
  public cl.laaraucana.servicio.transferencias.LOG_RESPUESTA revocarCuenta(java.lang.String token, cl.laaraucana.servicio.transferencias.CuentaEdit cuenta) throws java.rmi.RemoteException{
    if (cuentaBancariaServicioImpl == null)
      _initCuentaBancariaServicioImplProxy();
    return cuentaBancariaServicioImpl.revocarCuenta(token, cuenta);
  }
  
  public cl.laaraucana.servicio.transferencias.CuentasRUT consultaCuenta(java.lang.String token, cl.laaraucana.servicio.transferencias.CuentaSearch cuenta) throws java.rmi.RemoteException{
    if (cuentaBancariaServicioImpl == null)
      _initCuentaBancariaServicioImplProxy();
    return cuentaBancariaServicioImpl.consultaCuenta(token, cuenta);
  }
  
  
}