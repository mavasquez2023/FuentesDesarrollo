package com.lautaro.xi.CRM.WEB_Mobile;

public class SI_CampAcuPagoCastigo_OUTProxy implements com.lautaro.xi.CRM.WEB_Mobile.SI_CampAcuPagoCastigo_OUT {
  private String _endpoint = null;
  private com.lautaro.xi.CRM.WEB_Mobile.SI_CampAcuPagoCastigo_OUT sI_CampAcuPagoCastigo_OUT = null;
  
  public SI_CampAcuPagoCastigo_OUTProxy() {
    _initSI_CampAcuPagoCastigo_OUTProxy();
  }
  
  public SI_CampAcuPagoCastigo_OUTProxy(String endpoint) {
    _endpoint = endpoint;
    _initSI_CampAcuPagoCastigo_OUTProxy();
  }
  
  private void _initSI_CampAcuPagoCastigo_OUTProxy() {
    try {
      sI_CampAcuPagoCastigo_OUT = (new com.lautaro.xi.CRM.WEB_Mobile.SI_CampAcuPagoCastigo_OUTServiceLocator()).getHTTPS_Port();
      if (sI_CampAcuPagoCastigo_OUT != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sI_CampAcuPagoCastigo_OUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sI_CampAcuPagoCastigo_OUT)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sI_CampAcuPagoCastigo_OUT != null)
      ((javax.xml.rpc.Stub)sI_CampAcuPagoCastigo_OUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lautaro.xi.CRM.WEB_Mobile.SI_CampAcuPagoCastigo_OUT getSI_CampAcuPagoCastigo_OUT() {
    if (sI_CampAcuPagoCastigo_OUT == null)
      _initSI_CampAcuPagoCastigo_OUTProxy();
    return sI_CampAcuPagoCastigo_OUT;
  }
  
  public com.lautaro.xi.CRM.WEB_Mobile.DT_CampAcuPagoCastigo_RES SI_CampAcuPagoCastigo_OUT(com.lautaro.xi.CRM.WEB_Mobile.DT_CampAcuPagoCastigo_REQ MT_CampAcuPagoCastigo_REQ) throws java.rmi.RemoteException{
    if (sI_CampAcuPagoCastigo_OUT == null)
      _initSI_CampAcuPagoCastigo_OUTProxy();
    return sI_CampAcuPagoCastigo_OUT.SI_CampAcuPagoCastigo_OUT(MT_CampAcuPagoCastigo_REQ);
  }
  
  
}