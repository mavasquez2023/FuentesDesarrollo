package com.lautaro.xi.BS.WEB_Mobile;

public class SI_Cotizacion_OUTProxy implements com.lautaro.xi.BS.WEB_Mobile.SI_Cotizacion_OUT {
  private String _endpoint = null;
  private com.lautaro.xi.BS.WEB_Mobile.SI_Cotizacion_OUT sI_Cotizacion_OUT = null;
  
  public SI_Cotizacion_OUTProxy() {
    _initSI_Cotizacion_OUTProxy();
  }
  
  public SI_Cotizacion_OUTProxy(String endpoint) {
    _endpoint = endpoint;
    _initSI_Cotizacion_OUTProxy();
  }
  
  private void _initSI_Cotizacion_OUTProxy() {
    try {
      sI_Cotizacion_OUT = (new com.lautaro.xi.BS.WEB_Mobile.SI_Cotizacion_OUTServiceLocator()).getHTTPS_Port();
      if (sI_Cotizacion_OUT != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sI_Cotizacion_OUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sI_Cotizacion_OUT)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sI_Cotizacion_OUT != null)
      ((javax.xml.rpc.Stub)sI_Cotizacion_OUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lautaro.xi.BS.WEB_Mobile.SI_Cotizacion_OUT getSI_Cotizacion_OUT() {
    if (sI_Cotizacion_OUT == null)
      _initSI_Cotizacion_OUTProxy();
    return sI_Cotizacion_OUT;
  }
  
  public com.lautaro.xi.BS.WEB_Mobile.DT_Cotizacion_RES SI_Cotizacion_OUT(com.lautaro.xi.BS.WEB_Mobile.DT_Cotizacion_REQ MT_Cotizacion_REQ) throws java.rmi.RemoteException{
    if (sI_Cotizacion_OUT == null)
      _initSI_Cotizacion_OUTProxy();
    return sI_Cotizacion_OUT.SI_Cotizacion_OUT(MT_Cotizacion_REQ);
  }
  
  
}