package com.lautaro.xi.BS.WEB_Mobile;

public class SI_SimAcuPago_OUTProxy implements com.lautaro.xi.BS.WEB_Mobile.SI_SimAcuPago_OUT {
  private String _endpoint = null;
  private com.lautaro.xi.BS.WEB_Mobile.SI_SimAcuPago_OUT sI_SimAcuPago_OUT = null;
  
  public SI_SimAcuPago_OUTProxy() {
    _initSI_SimAcuPago_OUTProxy();
  }
  
  public SI_SimAcuPago_OUTProxy(String endpoint) {
    _endpoint = endpoint;
    _initSI_SimAcuPago_OUTProxy();
  }
  
  private void _initSI_SimAcuPago_OUTProxy() {
    try {
      sI_SimAcuPago_OUT = (new com.lautaro.xi.BS.WEB_Mobile.SI_SimAcuPago_OUTServiceLocator()).getHTTPS_Port();
      if (sI_SimAcuPago_OUT != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sI_SimAcuPago_OUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sI_SimAcuPago_OUT)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sI_SimAcuPago_OUT != null)
      ((javax.xml.rpc.Stub)sI_SimAcuPago_OUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lautaro.xi.BS.WEB_Mobile.SI_SimAcuPago_OUT getSI_SimAcuPago_OUT() {
    if (sI_SimAcuPago_OUT == null)
      _initSI_SimAcuPago_OUTProxy();
    return sI_SimAcuPago_OUT;
  }
  
  public com.lautaro.xi.BS.WEB_Mobile.DT_SimAcuPago_RES SI_SimAcuPago_OUT(com.lautaro.xi.BS.WEB_Mobile.DT_SimAcuPago_REQ MT_SimAcuPago_REQ) throws java.rmi.RemoteException{
    if (sI_SimAcuPago_OUT == null)
      _initSI_SimAcuPago_OUTProxy();
    return sI_SimAcuPago_OUT.SI_SimAcuPago_OUT(MT_SimAcuPago_REQ);
  }
  
  
}