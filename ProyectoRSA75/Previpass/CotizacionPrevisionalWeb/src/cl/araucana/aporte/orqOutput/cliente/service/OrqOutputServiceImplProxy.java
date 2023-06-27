package cl.araucana.aporte.orqOutput.cliente.service;

public class OrqOutputServiceImplProxy implements cl.araucana.aporte.orqOutput.cliente.service.OrqOutputServiceImpl {
  private String _endpoint = null;
  private cl.araucana.aporte.orqOutput.cliente.service.OrqOutputServiceImpl orqOutputServiceImpl = null;
  
  public OrqOutputServiceImplProxy() {
    _initOrqOutputServiceImplProxy();
  }
  
  private void _initOrqOutputServiceImplProxy() {
    try {
      orqOutputServiceImpl = (new cl.araucana.aporte.orqOutput.cliente.service.OrqOutputServiceImplServiceLocator()).getOrqOutputServiceImpl();
      if (orqOutputServiceImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)orqOutputServiceImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)orqOutputServiceImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (orqOutputServiceImpl != null)
      ((javax.xml.rpc.Stub)orqOutputServiceImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cl.araucana.aporte.orqOutput.cliente.service.OrqOutputServiceImpl getOrqOutputServiceImpl() {
    if (orqOutputServiceImpl == null)
      _initOrqOutputServiceImplProxy();
    return orqOutputServiceImpl;
  }
  
  public cl.araucana.aporte.orqOutput.cliente.service.vo.OrqOutputResultVO recuperacionPago(int rut, int montoCredito, int montoLeasing, int montoAporte, int periodoAporte, java.lang.String fechaRecaudacion, java.lang.String horaRecaudacion, java.lang.String usuario, int documentoPago) throws java.rmi.RemoteException{
    if (orqOutputServiceImpl == null)
      _initOrqOutputServiceImplProxy();
    return orqOutputServiceImpl.recuperacionPago(rut, montoCredito, montoLeasing, montoAporte, periodoAporte, fechaRecaudacion, horaRecaudacion, usuario, documentoPago);
  }
  
  public cl.araucana.aporte.orqOutput.cliente.service.vo.OrqOutputResultVO recuperacionPagoRemote(int rut, int montoCredito, int montoLeasing, int montoAporte, int periodoAporte, java.lang.String fechaRecaudacion, java.lang.String horaRecaudacion, java.lang.String usuario, int documentoPago) throws java.rmi.RemoteException{
    if (orqOutputServiceImpl == null)
      _initOrqOutputServiceImplProxy();
    return orqOutputServiceImpl.recuperacionPagoRemote(rut, montoCredito, montoLeasing, montoAporte, periodoAporte, fechaRecaudacion, horaRecaudacion, usuario, documentoPago);
  }
  
  
}