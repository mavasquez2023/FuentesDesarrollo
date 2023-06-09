package com.lautaro.xi.CRM.Legacy;

public class Os_OfertasVigentesFD_SIProxy implements com.lautaro.xi.CRM.Legacy.Os_OfertasVigentesFD_SI {
  private String _endpoint = null;
  private com.lautaro.xi.CRM.Legacy.Os_OfertasVigentesFD_SI os_OfertasVigentesFD_SI = null;
  
  public Os_OfertasVigentesFD_SIProxy() {
    _initOs_OfertasVigentesFD_SIProxy();
  }
  
  public Os_OfertasVigentesFD_SIProxy(String endpoint) {
    _endpoint = endpoint;
    _initOs_OfertasVigentesFD_SIProxy();
  }
  
  private void _initOs_OfertasVigentesFD_SIProxy() {
    try {
      os_OfertasVigentesFD_SI = (new com.lautaro.xi.CRM.Legacy.Os_OfertasVigentesFD_SIServiceLocator()).getHTTPS_Port();
      if (os_OfertasVigentesFD_SI != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)os_OfertasVigentesFD_SI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)os_OfertasVigentesFD_SI)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (os_OfertasVigentesFD_SI != null)
      ((javax.xml.rpc.Stub)os_OfertasVigentesFD_SI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lautaro.xi.CRM.Legacy.Os_OfertasVigentesFD_SI getOs_OfertasVigentesFD_SI() {
    if (os_OfertasVigentesFD_SI == null)
      _initOs_OfertasVigentesFD_SIProxy();
    return os_OfertasVigentesFD_SI;
  }
  
  public com.lautaro.xi.CRM.Legacy.OfertasVigentes_DT[] os_OfertasVigentesFD_SI(com.lautaro.xi.CRM.Legacy.OfertasVigentesFDReq_DT ofertasVigentesFDReq_MT) throws java.rmi.RemoteException{
    if (os_OfertasVigentesFD_SI == null)
      _initOs_OfertasVigentesFD_SIProxy();
    return os_OfertasVigentesFD_SI.os_OfertasVigentesFD_SI(ofertasVigentesFDReq_MT);
  }
  
  
}