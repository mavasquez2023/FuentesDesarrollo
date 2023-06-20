package cl.laaraucana.diferimientoEspecial.sinacofi;

public class CEDU0102SoapProxy implements cl.laaraucana.diferimientoEspecial.sinacofi.CEDU0102Soap {
  private String _endpoint = null;
  private cl.laaraucana.diferimientoEspecial.sinacofi.CEDU0102Soap cEDU0102Soap = null;
  
  public CEDU0102SoapProxy() {
    _initCEDU0102SoapProxy();
  }
  
  public CEDU0102SoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initCEDU0102SoapProxy();
  }
  
  private void _initCEDU0102SoapProxy() {
    try {
      cEDU0102Soap = (new cl.laaraucana.diferimientoEspecial.sinacofi.CEDU0102Locator()).getCEDU0102Soap();
      if (cEDU0102Soap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)cEDU0102Soap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)cEDU0102Soap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (cEDU0102Soap != null)
      ((javax.xml.rpc.Stub)cEDU0102Soap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cl.laaraucana.diferimientoEspecial.sinacofi.CEDU0102Soap getCEDU0102Soap() {
    if (cEDU0102Soap == null)
      _initCEDU0102SoapProxy();
    return cEDU0102Soap;
  }
  
  public cl.laaraucana.diferimientoEspecial.sinacofi.RespuestaCEDU0102 consulta(java.lang.String usuario, java.lang.String clave, java.lang.String rut, java.lang.String serie) throws java.rmi.RemoteException{
    if (cEDU0102Soap == null)
      _initCEDU0102SoapProxy();
    return cEDU0102Soap.consulta(usuario, clave, rut, serie);
  }
  
  
}