package com.lautaro.xi.BS.WEB_Mobile;

public class SI_FeriadosAnuales_OUTProxy implements com.lautaro.xi.BS.WEB_Mobile.SI_FeriadosAnuales_OUT {
  private String _endpoint = null;
  private com.lautaro.xi.BS.WEB_Mobile.SI_FeriadosAnuales_OUT sI_FeriadosAnuales_OUT = null;
  
  public SI_FeriadosAnuales_OUTProxy() {
    _initSI_FeriadosAnuales_OUTProxy();
  }
  
  public SI_FeriadosAnuales_OUTProxy(String endpoint) {
    _endpoint = endpoint;
    _initSI_FeriadosAnuales_OUTProxy();
  }
  
  private void _initSI_FeriadosAnuales_OUTProxy() {
    try {
      sI_FeriadosAnuales_OUT = (new com.lautaro.xi.BS.WEB_Mobile.SI_FeriadosAnuales_OUTServiceLocator()).getHTTPS_Port();
      if (sI_FeriadosAnuales_OUT != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sI_FeriadosAnuales_OUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sI_FeriadosAnuales_OUT)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sI_FeriadosAnuales_OUT != null)
      ((javax.xml.rpc.Stub)sI_FeriadosAnuales_OUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lautaro.xi.BS.WEB_Mobile.SI_FeriadosAnuales_OUT getSI_FeriadosAnuales_OUT() {
    if (sI_FeriadosAnuales_OUT == null)
      _initSI_FeriadosAnuales_OUTProxy();
    return sI_FeriadosAnuales_OUT;
  }
  
  public com.lautaro.xi.BS.WEB_Mobile.DT_FeriadosAnuales_RESFERIADOS[] SI_FeriadosAnuales_OUT(com.lautaro.xi.BS.WEB_Mobile.DT_FeriadosAnuales_REQ MT_FeriadosAnuales_REQ) throws java.rmi.RemoteException{
    if (sI_FeriadosAnuales_OUT == null)
      _initSI_FeriadosAnuales_OUTProxy();
    return sI_FeriadosAnuales_OUT.SI_FeriadosAnuales_OUT(MT_FeriadosAnuales_REQ);
  }
  
  
}