package com.lautaro.xi.CRM.Legacy;

public class Os_CrearOfertaFD_SIProxy implements com.lautaro.xi.CRM.Legacy.Os_CrearOfertaFD_SI {
  private String _endpoint = null;
  private com.lautaro.xi.CRM.Legacy.Os_CrearOfertaFD_SI os_CrearOfertaFD_SI = null;
  
  public Os_CrearOfertaFD_SIProxy() {
    _initOs_CrearOfertaFD_SIProxy();
  }
  
  public Os_CrearOfertaFD_SIProxy(String endpoint) {
    _endpoint = endpoint;
    _initOs_CrearOfertaFD_SIProxy();
  }
  
  private void _initOs_CrearOfertaFD_SIProxy() {
    try {
      os_CrearOfertaFD_SI = (new com.lautaro.xi.CRM.Legacy.Os_CrearOfertaFD_SIServiceLocator()).getHTTPS_Port();
      if (os_CrearOfertaFD_SI != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)os_CrearOfertaFD_SI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)os_CrearOfertaFD_SI)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (os_CrearOfertaFD_SI != null)
      ((javax.xml.rpc.Stub)os_CrearOfertaFD_SI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lautaro.xi.CRM.Legacy.Os_CrearOfertaFD_SI getOs_CrearOfertaFD_SI() {
    if (os_CrearOfertaFD_SI == null)
      _initOs_CrearOfertaFD_SIProxy();
    return os_CrearOfertaFD_SI;
  }
  
  public com.lautaro.xi.CRM.Legacy.CrearOfertaFDRes_DT os_CrearOfertaFD_SI(com.lautaro.xi.CRM.Legacy.CrearOfertaFDReq_DT crearOfertaFDReq_MT) throws java.rmi.RemoteException{
    if (os_CrearOfertaFD_SI == null)
      _initOs_CrearOfertaFD_SIProxy();
    return os_CrearOfertaFD_SI.os_CrearOfertaFD_SI(crearOfertaFDReq_MT);
  }
  
  
}