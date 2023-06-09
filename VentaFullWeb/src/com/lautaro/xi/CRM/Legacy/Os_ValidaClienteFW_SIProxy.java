package com.lautaro.xi.CRM.Legacy;

public class Os_ValidaClienteFW_SIProxy implements com.lautaro.xi.CRM.Legacy.Os_ValidaClienteFW_SI {
  private String _endpoint = null;
  private com.lautaro.xi.CRM.Legacy.Os_ValidaClienteFW_SI os_ValidaClienteFW_SI = null;
  
  public Os_ValidaClienteFW_SIProxy() {
    _initOs_ValidaClienteFW_SIProxy();
  }
  
  public Os_ValidaClienteFW_SIProxy(String endpoint) {
    _endpoint = endpoint;
    _initOs_ValidaClienteFW_SIProxy();
  }
  
  private void _initOs_ValidaClienteFW_SIProxy() {
    try {
      os_ValidaClienteFW_SI = (new com.lautaro.xi.CRM.Legacy.Os_ValidaClienteFW_SIServiceLocator()).getHTTPS_Port();
      if (os_ValidaClienteFW_SI != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)os_ValidaClienteFW_SI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)os_ValidaClienteFW_SI)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (os_ValidaClienteFW_SI != null)
      ((javax.xml.rpc.Stub)os_ValidaClienteFW_SI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lautaro.xi.CRM.Legacy.Os_ValidaClienteFW_SI getOs_ValidaClienteFW_SI() {
    if (os_ValidaClienteFW_SI == null)
      _initOs_ValidaClienteFW_SIProxy();
    return os_ValidaClienteFW_SI;
  }
  
  public com.lautaro.xi.CRM.Legacy.ValidaClienteFWRes_DT os_ValidaClienteFW_SI(com.lautaro.xi.CRM.Legacy.ValidaClienteFWReq_DT validaClienteFWReqOut_MT) throws java.rmi.RemoteException{
    if (os_ValidaClienteFW_SI == null)
      _initOs_ValidaClienteFW_SIProxy();
    return os_ValidaClienteFW_SI.os_ValidaClienteFW_SI(validaClienteFWReqOut_MT);
  }
  
  
}