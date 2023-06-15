package com.lautaro.xi.BS.WEB_Mobile;

public class SI_SimuladorSocial_OUTProxy implements com.lautaro.xi.BS.WEB_Mobile.SI_SimuladorSocial_OUT {
  private String _endpoint = null;
  private com.lautaro.xi.BS.WEB_Mobile.SI_SimuladorSocial_OUT sI_SimuladorSocial_OUT = null;
  
  public SI_SimuladorSocial_OUTProxy() {
    _initSI_SimuladorSocial_OUTProxy();
  }
  
  public SI_SimuladorSocial_OUTProxy(String endpoint) {
    _endpoint = endpoint;
    _initSI_SimuladorSocial_OUTProxy();
  }
  
  private void _initSI_SimuladorSocial_OUTProxy() {
    try {
      sI_SimuladorSocial_OUT = (new com.lautaro.xi.BS.WEB_Mobile.SI_SimuladorSocial_OUTServiceLocator()).getHTTPS_Port();
      if (sI_SimuladorSocial_OUT != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sI_SimuladorSocial_OUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sI_SimuladorSocial_OUT)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sI_SimuladorSocial_OUT != null)
      ((javax.xml.rpc.Stub)sI_SimuladorSocial_OUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lautaro.xi.BS.WEB_Mobile.SI_SimuladorSocial_OUT getSI_SimuladorSocial_OUT() {
    if (sI_SimuladorSocial_OUT == null)
      _initSI_SimuladorSocial_OUTProxy();
    return sI_SimuladorSocial_OUT;
  }
  
  public com.lautaro.xi.BS.WEB_Mobile.DT_SimuladorSocial_RES SI_SimuladorSocial_OUT(com.lautaro.xi.BS.WEB_Mobile.DT_SimuladorSocial_REQ MT_SimuladorSocial_REQ) throws java.rmi.RemoteException{
    if (sI_SimuladorSocial_OUT == null)
      _initSI_SimuladorSocial_OUTProxy();
    return sI_SimuladorSocial_OUT.SI_SimuladorSocial_OUT(MT_SimuladorSocial_REQ);
  }
  
  
}