package cl.araucana.cp.webServices.aporte.orqInput.service;

public class OrqInputServiceImplProxy implements cl.araucana.cp.webServices.aporte.orqInput.service.OrqInputServiceImpl {
  private String _endpoint = null;
  private cl.araucana.cp.webServices.aporte.orqInput.service.OrqInputServiceImpl orqInputServiceImpl = null;
  
  public OrqInputServiceImplProxy() {
    _initOrqInputServiceImplProxy();
  }
  
  private void _initOrqInputServiceImplProxy() {
    try {
      orqInputServiceImpl = (new cl.araucana.cp.webServices.aporte.orqInput.service.OrqInputServiceImplServiceLocator()).getOrqInputServiceImpl();
      if (orqInputServiceImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)orqInputServiceImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)orqInputServiceImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (orqInputServiceImpl != null)
      ((javax.xml.rpc.Stub)orqInputServiceImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cl.araucana.cp.webServices.aporte.orqInput.service.OrqInputServiceImpl getOrqInputServiceImpl() {
    if (orqInputServiceImpl == null)
      _initOrqInputServiceImplProxy();
    return orqInputServiceImpl;
  }
  
  public cl.araucana.cp.webServices.aporte.orqInput.service.vo.OrqInputResultVO obtenerInfoPago(int rut) throws java.rmi.RemoteException{
    if (orqInputServiceImpl == null)
      _initOrqInputServiceImplProxy();
    return orqInputServiceImpl.obtenerInfoPago(rut);
  }
  
  public cl.araucana.cp.webServices.aporte.orqInput.service.vo.OrqInputResultVO obtenerInfoPagoRemote(int rut) throws java.rmi.RemoteException{
    if (orqInputServiceImpl == null)
      _initOrqInputServiceImplProxy();
    return orqInputServiceImpl.obtenerInfoPagoRemote(rut);
  }
  
  
}