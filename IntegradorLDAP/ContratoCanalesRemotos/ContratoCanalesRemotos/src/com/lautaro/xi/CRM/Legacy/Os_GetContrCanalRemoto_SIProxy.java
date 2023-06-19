package com.lautaro.xi.CRM.Legacy;

public class Os_GetContrCanalRemoto_SIProxy implements com.lautaro.xi.CRM.Legacy.Os_GetContrCanalRemoto_SI {
  private String _endpoint = null;
  private com.lautaro.xi.CRM.Legacy.Os_GetContrCanalRemoto_SI os_GetContrCanalRemoto_SI = null;
  
  public Os_GetContrCanalRemoto_SIProxy() {
    _initOs_GetContrCanalRemoto_SIProxy();
  }
  
  public Os_GetContrCanalRemoto_SIProxy(String endpoint) {
    _endpoint = endpoint;
    _initOs_GetContrCanalRemoto_SIProxy();
  }
  
  private void _initOs_GetContrCanalRemoto_SIProxy() {
    try {
      os_GetContrCanalRemoto_SI = (new com.lautaro.xi.CRM.Legacy.Os_GetContrCanalRemoto_SIServiceLocator()).getos_GetContrCanalRemoto_SIPort();
      if (os_GetContrCanalRemoto_SI != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)os_GetContrCanalRemoto_SI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)os_GetContrCanalRemoto_SI)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (os_GetContrCanalRemoto_SI != null)
      ((javax.xml.rpc.Stub)os_GetContrCanalRemoto_SI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lautaro.xi.CRM.Legacy.Os_GetContrCanalRemoto_SI getOs_GetContrCanalRemoto_SI() {
    if (os_GetContrCanalRemoto_SI == null)
      _initOs_GetContrCanalRemoto_SIProxy();
    return os_GetContrCanalRemoto_SI;
  }
  
  public functions.rfc.sap.document.sap_com.ZRFC_GET_CONTR_CANAL_REMOTOResponse os_GetContrCanalRemoto_SI(functions.rfc.sap.document.sap_com.ZRFC_GET_CONTR_CANAL_REMOTO parameters) throws java.rmi.RemoteException{
    if (os_GetContrCanalRemoto_SI == null)
      _initOs_GetContrCanalRemoto_SIProxy();
    return os_GetContrCanalRemoto_SI.os_GetContrCanalRemoto_SI(parameters);
  }
  
  
}