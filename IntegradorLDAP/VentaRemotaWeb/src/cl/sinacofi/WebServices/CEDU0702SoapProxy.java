package cl.sinacofi.WebServices;

public class CEDU0702SoapProxy implements cl.sinacofi.WebServices.CEDU0702Soap {
  private String _endpoint = null;
  private cl.sinacofi.WebServices.CEDU0702Soap cEDU0702Soap = null;
  
  public CEDU0702SoapProxy() {
    _initCEDU0702SoapProxy();
  }
  
  public CEDU0702SoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initCEDU0702SoapProxy();
  }
  
  private void _initCEDU0702SoapProxy() {
    try {
      cEDU0702Soap = (new cl.sinacofi.WebServices.CEDU0702Locator()).getCEDU0702Soap();
      if (cEDU0702Soap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)cEDU0702Soap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)cEDU0702Soap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (cEDU0702Soap != null)
      ((javax.xml.rpc.Stub)cEDU0702Soap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cl.sinacofi.WebServices.CEDU0702Soap getCEDU0702Soap() {
    if (cEDU0702Soap == null)
      _initCEDU0702SoapProxy();
    return cEDU0702Soap;
  }
  
  public cl.sinacofi.WebServices.RespuestaCEDU0702 consulta(java.lang.String usuario, java.lang.String claveUsuario, java.lang.String rutCliente, java.lang.String numeroSerie, java.lang.String canalInstitucion) throws java.rmi.RemoteException{
    if (cEDU0702Soap == null)
      _initCEDU0702SoapProxy();
    return cEDU0702Soap.consulta(usuario, claveUsuario, rutCliente, numeroSerie, canalInstitucion);
  }
  
  
}