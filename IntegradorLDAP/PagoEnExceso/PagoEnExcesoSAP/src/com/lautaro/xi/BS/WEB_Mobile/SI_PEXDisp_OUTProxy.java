package com.lautaro.xi.BS.WEB_Mobile;

public class SI_PEXDisp_OUTProxy implements com.lautaro.xi.BS.WEB_Mobile.SI_PEXDisp_OUT {
  private String _endpoint = null;
  private com.lautaro.xi.BS.WEB_Mobile.SI_PEXDisp_OUT sI_PEXDisp_OUT = null;
  
  public SI_PEXDisp_OUTProxy() {
    _initSI_PEXDisp_OUTProxy();
  }
  
  public SI_PEXDisp_OUTProxy(String endpoint) {
    _endpoint = endpoint;
    _initSI_PEXDisp_OUTProxy();
  }
  
  private void _initSI_PEXDisp_OUTProxy() {
    try {
      sI_PEXDisp_OUT = (new com.lautaro.xi.BS.WEB_Mobile.SI_PEXDisp_OUTServiceLocator()).getHTTPS_Port();
      if (sI_PEXDisp_OUT != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sI_PEXDisp_OUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sI_PEXDisp_OUT)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sI_PEXDisp_OUT != null)
      ((javax.xml.rpc.Stub)sI_PEXDisp_OUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lautaro.xi.BS.WEB_Mobile.SI_PEXDisp_OUT getSI_PEXDisp_OUT() {
    if (sI_PEXDisp_OUT == null)
      _initSI_PEXDisp_OUTProxy();
    return sI_PEXDisp_OUT;
  }
  
  public com.lautaro.xi.BS.WEB_Mobile.DT_PEXDisp_RES SI_PEXDisp_OUT(com.lautaro.xi.BS.WEB_Mobile.DT_PEXDisp_REQ MT_PEXDisp_REQ) throws java.rmi.RemoteException{
    if (sI_PEXDisp_OUT == null)
      _initSI_PEXDisp_OUTProxy();
    return sI_PEXDisp_OUT.SI_PEXDisp_OUT(MT_PEXDisp_REQ);
  }
  
  
}