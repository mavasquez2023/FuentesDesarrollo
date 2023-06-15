package com.lautaro.xi.CRM.WEB_Mobile;

public class QueryBPStatusRolOUTProxy implements com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRolOUT {
  private String _endpoint = null;
  private com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRolOUT queryBPStatusRolOUT = null;
  
  public QueryBPStatusRolOUTProxy() {
    _initQueryBPStatusRolOUTProxy();
  }
  
  public QueryBPStatusRolOUTProxy(String endpoint) {
    _endpoint = endpoint;
    _initQueryBPStatusRolOUTProxy();
  }
  
  private void _initQueryBPStatusRolOUTProxy() {
    try {
      queryBPStatusRolOUT = (new com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRolOUTServiceLocator()).getQueryBPStatusRolOUTPort();
      if (queryBPStatusRolOUT != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)queryBPStatusRolOUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)queryBPStatusRolOUT)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (queryBPStatusRolOUT != null)
      ((javax.xml.rpc.Stub)queryBPStatusRolOUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRolOUT getQueryBPStatusRolOUT() {
    if (queryBPStatusRolOUT == null)
      _initQueryBPStatusRolOUTProxy();
    return queryBPStatusRolOUT;
  }
  
  public com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRol[] queryBPStatusRolOUT(com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRolReq queryBPStatusRolReqOUT) throws java.rmi.RemoteException{
    if (queryBPStatusRolOUT == null)
      _initQueryBPStatusRolOUTProxy();
    return queryBPStatusRolOUT.queryBPStatusRolOUT(queryBPStatusRolReqOUT);
  }
  
  
}