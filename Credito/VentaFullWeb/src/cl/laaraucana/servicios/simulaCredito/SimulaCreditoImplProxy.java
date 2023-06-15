package cl.laaraucana.servicios.simulaCredito;

public class SimulaCreditoImplProxy implements cl.laaraucana.servicios.simulaCredito.SimulaCreditoImpl {
  private String _endpoint = null;
  private cl.laaraucana.servicios.simulaCredito.SimulaCreditoImpl simulaCreditoImpl = null;
  
  public SimulaCreditoImplProxy() {
    _initSimulaCreditoImplProxy();
  }
  
  public SimulaCreditoImplProxy(String endpoint) {
    _endpoint = endpoint;
    _initSimulaCreditoImplProxy();
  }
  
  private void _initSimulaCreditoImplProxy() {
    try {
      simulaCreditoImpl = (new cl.laaraucana.servicios.simulaCredito.SimulaCreditoServiceLocator()).getSimulaCreditoPort();
      if (simulaCreditoImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)simulaCreditoImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)simulaCreditoImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (simulaCreditoImpl != null)
      ((javax.xml.rpc.Stub)simulaCreditoImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cl.laaraucana.servicios.simulaCredito.SimulaCreditoImpl getSimulaCreditoImpl() {
    if (simulaCreditoImpl == null)
      _initSimulaCreditoImplProxy();
    return simulaCreditoImpl;
  }
  
  public cl.laaraucana.servicios.simulaCredito.Response simulaCredito(cl.laaraucana.servicios.simulaCredito.Request request) throws java.rmi.RemoteException, cl.laaraucana.servicios.simulaCredito.SOAPException{
    if (simulaCreditoImpl == null)
      _initSimulaCreditoImplProxy();
    return simulaCreditoImpl.simulaCredito(request);
  }
  
  
}