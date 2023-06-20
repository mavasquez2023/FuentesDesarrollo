package cl.laaraucana.pagoenexceso.delegate;

public class ConsultaPagoEnExcesoDelegateProxy implements cl.laaraucana.pagoenexceso.delegate.ConsultaPagoEnExcesoDelegate {
  private String _endpoint = null;
  private cl.laaraucana.pagoenexceso.delegate.ConsultaPagoEnExcesoDelegate consultaPagoEnExcesoDelegate = null;
  
  public ConsultaPagoEnExcesoDelegateProxy() {
    _initConsultaPagoEnExcesoDelegateProxy();
  }
  
  public ConsultaPagoEnExcesoDelegateProxy(String endpoint) {
    _endpoint = endpoint;
    _initConsultaPagoEnExcesoDelegateProxy();
  }
  
  private void _initConsultaPagoEnExcesoDelegateProxy() {
    try {
      consultaPagoEnExcesoDelegate = (new cl.laaraucana.pagoenexceso.delegate.ConsultaPagoEnExcesoServiceLocator()).getConsultaPagoEnExcesoPort();
      if (consultaPagoEnExcesoDelegate != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)consultaPagoEnExcesoDelegate)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)consultaPagoEnExcesoDelegate)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (consultaPagoEnExcesoDelegate != null)
      ((javax.xml.rpc.Stub)consultaPagoEnExcesoDelegate)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cl.laaraucana.pagoenexceso.delegate.ConsultaPagoEnExcesoDelegate getConsultaPagoEnExcesoDelegate() {
    if (consultaPagoEnExcesoDelegate == null)
      _initConsultaPagoEnExcesoDelegateProxy();
    return consultaPagoEnExcesoDelegate;
  }
  
  public cl.laaraucana.pagoenexceso.delegate.ConsultaPagoEnExcesoOut consultarPagoEnExceso(java.lang.String rut) throws java.rmi.RemoteException{
    if (consultaPagoEnExcesoDelegate == null)
      _initConsultaPagoEnExcesoDelegateProxy();
    return consultaPagoEnExcesoDelegate.consultarPagoEnExceso(rut);
  }
  
  
}