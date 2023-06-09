package cl.laaraucana.servicios.validaCredito;

public class ValidaCreditoImplProxy implements cl.laaraucana.servicios.validaCredito.ValidaCreditoImpl {
  private String _endpoint = null;
  private cl.laaraucana.servicios.validaCredito.ValidaCreditoImpl validaCreditoImpl = null;
  
  public ValidaCreditoImplProxy() {
    _initValidaCreditoImplProxy();
  }
  
  public ValidaCreditoImplProxy(String endpoint) {
    _endpoint = endpoint;
    _initValidaCreditoImplProxy();
  }
  
  private void _initValidaCreditoImplProxy() {
    try {
      validaCreditoImpl = (new cl.laaraucana.servicios.validaCredito.ValidaCreditoServiceLocator()).getValidaCreditoPort();
      if (validaCreditoImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)validaCreditoImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)validaCreditoImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (validaCreditoImpl != null)
      ((javax.xml.rpc.Stub)validaCreditoImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cl.laaraucana.servicios.validaCredito.ValidaCreditoImpl getValidaCreditoImpl() {
    if (validaCreditoImpl == null)
      _initValidaCreditoImplProxy();
    return validaCreditoImpl;
  }
  
  public cl.laaraucana.servicios.validaCredito.Response validaCredito(cl.laaraucana.servicios.validaCredito.Request request) throws java.rmi.RemoteException, cl.laaraucana.servicios.validaCredito.SOAPException{
    if (validaCreditoImpl == null)
      _initValidaCreditoImplProxy();
    return validaCreditoImpl.validaCredito(request);
  }
  
  
}