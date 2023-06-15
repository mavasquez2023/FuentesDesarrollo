package com.lautaro.xi.BS.WEB_Mobile;

public class QueryContractHeaderOUTProxy implements com.lautaro.xi.BS.WEB_Mobile.QueryContractHeaderOUT {
  private String _endpoint = null;
  private com.lautaro.xi.BS.WEB_Mobile.QueryContractHeaderOUT queryContractHeaderOUT = null;
  
  public QueryContractHeaderOUTProxy() {
    _initQueryContractHeaderOUTProxy();
  }
  
  public QueryContractHeaderOUTProxy(String endpoint) {
    _endpoint = endpoint;
    _initQueryContractHeaderOUTProxy();
  }
  
  private void _initQueryContractHeaderOUTProxy() {
    try {
      queryContractHeaderOUT = (new com.lautaro.xi.BS.WEB_Mobile.QueryContractHeaderOUTServiceLocator()).getHTTPS_Port();
      if (queryContractHeaderOUT != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)queryContractHeaderOUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)queryContractHeaderOUT)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (queryContractHeaderOUT != null)
      ((javax.xml.rpc.Stub)queryContractHeaderOUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lautaro.xi.BS.WEB_Mobile.QueryContractHeaderOUT getQueryContractHeaderOUT() {
    if (queryContractHeaderOUT == null)
      _initQueryContractHeaderOUTProxy();
    return queryContractHeaderOUT;
  }
  
  public com.lautaro.xi.BS.WEB_Mobile.QueryContractHeaderResponse queryContractHeader(com.lautaro.xi.BS.WEB_Mobile.QueryContractHeaderRequest queryContractHeaderRequestOut) throws java.rmi.RemoteException{
    if (queryContractHeaderOUT == null)
      _initQueryContractHeaderOUTProxy();
    return queryContractHeaderOUT.queryContractHeader(queryContractHeaderRequestOut);
  }
  
  
}