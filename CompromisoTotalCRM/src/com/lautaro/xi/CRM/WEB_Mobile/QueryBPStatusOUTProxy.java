package com.lautaro.xi.CRM.WEB_Mobile;

public class QueryBPStatusOUTProxy implements com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusOUT {
  private String _endpoint = null;
  private com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusOUT queryBPStatusOUT = null;
  
  public QueryBPStatusOUTProxy() {
    _initQueryBPStatusOUTProxy();
  }
  
  public QueryBPStatusOUTProxy(String endpoint) {
    _endpoint = endpoint;
    _initQueryBPStatusOUTProxy();
  }
  
  private void _initQueryBPStatusOUTProxy() {
    try {
      queryBPStatusOUT = (new com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusOUTServiceLocator()).getQueryBPStatusOUTPort();
      if (queryBPStatusOUT != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)queryBPStatusOUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)queryBPStatusOUT)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (queryBPStatusOUT != null)
      ((javax.xml.rpc.Stub)queryBPStatusOUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusOUT getQueryBPStatusOUT() {
    if (queryBPStatusOUT == null)
      _initQueryBPStatusOUTProxy();
    return queryBPStatusOUT;
  }
  
  public com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusResponse queryBPStatus(com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRut queryBPStatusRequestOut) throws java.rmi.RemoteException{
    if (queryBPStatusOUT == null)
      _initQueryBPStatusOUTProxy();
    return queryBPStatusOUT.queryBPStatus(queryBPStatusRequestOut);
  }
  
  
}