package com.lautaro.xi.BS.WEB_Mobile;

public class SI_SimuladorRepro_OUTProxy implements com.lautaro.xi.BS.WEB_Mobile.SI_SimuladorRepro_OUT {
  private String _endpoint = null;
  private com.lautaro.xi.BS.WEB_Mobile.SI_SimuladorRepro_OUT sI_SimuladorRepro_OUT = null;
  
  public SI_SimuladorRepro_OUTProxy() {
    _initSI_SimuladorRepro_OUTProxy();
  }
  
  public SI_SimuladorRepro_OUTProxy(String endpoint) {
    _endpoint = endpoint;
    _initSI_SimuladorRepro_OUTProxy();
  }
  
  private void _initSI_SimuladorRepro_OUTProxy() {
    try {
      sI_SimuladorRepro_OUT = (new com.lautaro.xi.BS.WEB_Mobile.SI_SimuladorRepro_OUTServiceLocator()).getHTTPS_Port();
      if (sI_SimuladorRepro_OUT != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sI_SimuladorRepro_OUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sI_SimuladorRepro_OUT)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sI_SimuladorRepro_OUT != null)
      ((javax.xml.rpc.Stub)sI_SimuladorRepro_OUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lautaro.xi.BS.WEB_Mobile.SI_SimuladorRepro_OUT getSI_SimuladorRepro_OUT() {
    if (sI_SimuladorRepro_OUT == null)
      _initSI_SimuladorRepro_OUTProxy();
    return sI_SimuladorRepro_OUT;
  }
  
  public com.lautaro.xi.BS.WEB_Mobile.DT_SimuladorRepro_RES SI_SimuladorRepro_OUT(com.lautaro.xi.BS.WEB_Mobile.DT_SimuladorRepro_REQ MT_SimuladorRepro_REQ) throws java.rmi.RemoteException{
    if (sI_SimuladorRepro_OUT == null)
      _initSI_SimuladorRepro_OUTProxy();
    return sI_SimuladorRepro_OUT.SI_SimuladorRepro_OUT(MT_SimuladorRepro_REQ);
  }
  
  
}