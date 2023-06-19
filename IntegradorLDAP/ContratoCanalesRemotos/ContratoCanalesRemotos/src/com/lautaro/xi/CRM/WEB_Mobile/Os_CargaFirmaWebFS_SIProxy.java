package com.lautaro.xi.CRM.WEB_Mobile;

public class Os_CargaFirmaWebFS_SIProxy implements com.lautaro.xi.CRM.WEB_Mobile.Os_CargaFirmaWebFS_SI {
  private String _endpoint = null;
  private com.lautaro.xi.CRM.WEB_Mobile.Os_CargaFirmaWebFS_SI os_CargaFirmaWebFS_SI = null;
  
  public Os_CargaFirmaWebFS_SIProxy() {
    _initOs_CargaFirmaWebFS_SIProxy();
  }
  
  public Os_CargaFirmaWebFS_SIProxy(String endpoint) {
    _endpoint = endpoint;
    _initOs_CargaFirmaWebFS_SIProxy();
  }
  
  private void _initOs_CargaFirmaWebFS_SIProxy() {
    try {
      os_CargaFirmaWebFS_SI = (new com.lautaro.xi.CRM.WEB_Mobile.Os_CargaFirmaWebFS_SIServiceLocator()).getos_CargaFirmaWebFS_SIPort();
      if (os_CargaFirmaWebFS_SI != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)os_CargaFirmaWebFS_SI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)os_CargaFirmaWebFS_SI)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (os_CargaFirmaWebFS_SI != null)
      ((javax.xml.rpc.Stub)os_CargaFirmaWebFS_SI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lautaro.xi.CRM.WEB_Mobile.Os_CargaFirmaWebFS_SI getOs_CargaFirmaWebFS_SI() {
    if (os_CargaFirmaWebFS_SI == null)
      _initOs_CargaFirmaWebFS_SIProxy();
    return os_CargaFirmaWebFS_SI;
  }
  
  public com.lautaro.xi.CRM.WEB_Mobile.Oa_CargaFirmaWebFS_DT os_CargaFirmaWebFS_SI(com.lautaro.xi.CRM.WEB_Mobile.Ia_CargaFirmaWebFS_DT is_CargaFirmaWebFS_MT) throws java.rmi.RemoteException{
    if (os_CargaFirmaWebFS_SI == null)
      _initOs_CargaFirmaWebFS_SIProxy();
    return os_CargaFirmaWebFS_SI.os_CargaFirmaWebFS_SI(is_CargaFirmaWebFS_MT);
  }
  
  
}