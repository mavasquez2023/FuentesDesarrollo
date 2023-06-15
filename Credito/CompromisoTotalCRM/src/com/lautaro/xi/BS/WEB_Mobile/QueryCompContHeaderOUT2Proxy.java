package com.lautaro.xi.BS.WEB_Mobile;

public class QueryCompContHeaderOUT2Proxy implements com.lautaro.xi.BS.WEB_Mobile.QueryCompContHeaderOUT2 {
  private String _endpoint = null;
  private com.lautaro.xi.BS.WEB_Mobile.QueryCompContHeaderOUT2 queryCompContHeaderOUT2 = null;
  
  public QueryCompContHeaderOUT2Proxy() {
    _initQueryCompContHeaderOUT2Proxy();
  }
  
  public QueryCompContHeaderOUT2Proxy(String endpoint) {
    _endpoint = endpoint;
    _initQueryCompContHeaderOUT2Proxy();
  }
  
  private void _initQueryCompContHeaderOUT2Proxy() {
    try {
      queryCompContHeaderOUT2 = (new com.lautaro.xi.BS.WEB_Mobile.QueryCompContHeaderOUT2ServiceLocator()).getHTTPS_Port();
      if (queryCompContHeaderOUT2 != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)queryCompContHeaderOUT2)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)queryCompContHeaderOUT2)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (queryCompContHeaderOUT2 != null)
      ((javax.xml.rpc.Stub)queryCompContHeaderOUT2)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lautaro.xi.BS.WEB_Mobile.QueryCompContHeaderOUT2 getQueryCompContHeaderOUT2() {
    if (queryCompContHeaderOUT2 == null)
      _initQueryCompContHeaderOUT2Proxy();
    return queryCompContHeaderOUT2;
  }
  
  public com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeaderResponse2 queryCompContrHeader(com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeaderRequest queryCompactContractHeaderRequestOut) throws java.rmi.RemoteException{
    if (queryCompContHeaderOUT2 == null)
      _initQueryCompContHeaderOUT2Proxy();
    return queryCompContHeaderOUT2.queryCompContrHeader(queryCompactContractHeaderRequestOut);
  }
  
  
}