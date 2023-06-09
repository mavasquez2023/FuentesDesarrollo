package cl.laaraucana.servicios.validaCliente;

public class ValidaClienteImplProxy implements cl.laaraucana.servicios.validaCliente.ValidaClienteImpl {
  private String _endpoint = null;
  private cl.laaraucana.servicios.validaCliente.ValidaClienteImpl validaClienteImpl = null;
  
  public ValidaClienteImplProxy() {
    _initValidaClienteImplProxy();
  }
  
  public ValidaClienteImplProxy(String endpoint) {
    _endpoint = endpoint;
    _initValidaClienteImplProxy();
  }
  
  private void _initValidaClienteImplProxy() {
    try {
      validaClienteImpl = (new cl.laaraucana.servicios.validaCliente.ValidaClienteServiceLocator()).getValidaClientePort();
      if (validaClienteImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)validaClienteImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)validaClienteImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (validaClienteImpl != null)
      ((javax.xml.rpc.Stub)validaClienteImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cl.laaraucana.servicios.validaCliente.ValidaClienteImpl getValidaClienteImpl() {
    if (validaClienteImpl == null)
      _initValidaClienteImplProxy();
    return validaClienteImpl;
  }
  
  public cl.laaraucana.servicios.validaCliente.Response validaCliente(cl.laaraucana.servicios.validaCliente.Request request) throws java.rmi.RemoteException, cl.laaraucana.servicios.validaCliente.SOAPException{
    if (validaClienteImpl == null)
      _initValidaClienteImplProxy();
    return validaClienteImpl.validaCliente(request);
  }
  
  
}