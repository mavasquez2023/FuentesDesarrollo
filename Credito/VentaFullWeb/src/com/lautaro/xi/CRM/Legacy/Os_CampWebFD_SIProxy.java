package com.lautaro.xi.CRM.Legacy;

public class Os_CampWebFD_SIProxy implements com.lautaro.xi.CRM.Legacy.Os_CampWebFD_SI {
  private String _endpoint = null;
  private com.lautaro.xi.CRM.Legacy.Os_CampWebFD_SI os_CampWebFD_SI = null;
  
  public Os_CampWebFD_SIProxy() {
    _initOs_CampWebFD_SIProxy();
  }
  
  public Os_CampWebFD_SIProxy(String endpoint) {
    _endpoint = endpoint;
    _initOs_CampWebFD_SIProxy();
  }
  
  private void _initOs_CampWebFD_SIProxy() {
    try {
      os_CampWebFD_SI = (new com.lautaro.xi.CRM.Legacy.Os_CampWebFD_SIServiceLocator()).getos_CampWebFD_SIPort();
      if (os_CampWebFD_SI != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)os_CampWebFD_SI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)os_CampWebFD_SI)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (os_CampWebFD_SI != null)
      ((javax.xml.rpc.Stub)os_CampWebFD_SI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lautaro.xi.CRM.Legacy.Os_CampWebFD_SI getOs_CampWebFD_SI() {
    if (os_CampWebFD_SI == null)
      _initOs_CampWebFD_SIProxy();
    return os_CampWebFD_SI;
  }
  
  public com.lautaro.xi.CRM.Legacy.CampWebFDRes_DT os_CampWebFD_SI(com.lautaro.xi.CRM.Legacy.CampWebFDReq_DT campWebFDReqOut_MT) throws java.rmi.RemoteException{
    if (os_CampWebFD_SI == null)
      _initOs_CampWebFD_SIProxy();
    return os_CampWebFD_SI.os_CampWebFD_SI(campWebFDReqOut_MT);
  }
  
  
}