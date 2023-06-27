package cl.laaraucana.integracion.impl;

public class IntegracionDTProxy implements cl.laaraucana.integracion.impl.IntegracionDT {
  private String _endpoint = null;
  private cl.laaraucana.integracion.impl.IntegracionDT integracionDT = null;
  
  public IntegracionDTProxy() {
    _initIntegracionDTProxy();
  }
  
  private void _initIntegracionDTProxy() {
    try {
      integracionDT = (new cl.laaraucana.integracion.impl.IntegracionDTServiceLocator()).getIntegracionDT();
      if (integracionDT != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)integracionDT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)integracionDT)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (integracionDT != null)
      ((javax.xml.rpc.Stub)integracionDT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cl.laaraucana.integracion.impl.IntegracionDT getIntegracionDT() {
    if (integracionDT == null)
      _initIntegracionDTProxy();
    return integracionDT;
  }
  
  public java.lang.String integracionDT(java.lang.String entrada) throws java.rmi.RemoteException{
    if (integracionDT == null)
      _initIntegracionDTProxy();
    return integracionDT.integracionDT(entrada);
  }
  
  
}