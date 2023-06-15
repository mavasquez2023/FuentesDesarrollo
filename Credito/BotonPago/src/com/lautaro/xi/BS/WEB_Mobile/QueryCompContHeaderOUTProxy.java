package com.lautaro.xi.BS.WEB_Mobile;

public class QueryCompContHeaderOUTProxy implements com.lautaro.xi.BS.WEB_Mobile.QueryCompContHeaderOUT {
  private String _endpoint = null;
  private com.lautaro.xi.BS.WEB_Mobile.QueryCompContHeaderOUT queryCompContHeaderOUT = null;
  
  public QueryCompContHeaderOUTProxy() {
    _initQueryCompContHeaderOUTProxy();
  }
  
  public QueryCompContHeaderOUTProxy(String endpoint) {
    _endpoint = endpoint;
    _initQueryCompContHeaderOUTProxy();
  }
  
  private void _initQueryCompContHeaderOUTProxy() {
    try {
      queryCompContHeaderOUT = (new com.lautaro.xi.BS.WEB_Mobile.QueryCompContHeaderOUTServiceLocator()).getHTTPS_Port();
      if (queryCompContHeaderOUT != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)queryCompContHeaderOUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)queryCompContHeaderOUT)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (queryCompContHeaderOUT != null)
      ((javax.xml.rpc.Stub)queryCompContHeaderOUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lautaro.xi.BS.WEB_Mobile.QueryCompContHeaderOUT getQueryCompContHeaderOUT() {
    if (queryCompContHeaderOUT == null)
      _initQueryCompContHeaderOUTProxy();
    return queryCompContHeaderOUT;
  }
  
  public com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeaderResponse queryCompContrHeader(com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeaderRequest queryCompactContractHeaderRequestOut) throws java.rmi.RemoteException{
    if (queryCompContHeaderOUT == null)
      _initQueryCompContHeaderOUTProxy();
    return queryCompContHeaderOUT.queryCompContrHeader(queryCompactContractHeaderRequestOut);
  }
  
  
}