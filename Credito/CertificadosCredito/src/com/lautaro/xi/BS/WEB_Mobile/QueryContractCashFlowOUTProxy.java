package com.lautaro.xi.BS.WEB_Mobile;

public class QueryContractCashFlowOUTProxy implements com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowOUT {
  private String _endpoint = null;
  private com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowOUT queryContractCashFlowOUT = null;
  
  public QueryContractCashFlowOUTProxy() {
    _initQueryContractCashFlowOUTProxy();
  }
  
  public QueryContractCashFlowOUTProxy(String endpoint) {
    _endpoint = endpoint;
    _initQueryContractCashFlowOUTProxy();
  }
  
  private void _initQueryContractCashFlowOUTProxy() {
    try {
      queryContractCashFlowOUT = (new com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowOUTServiceLocator()).getHTTPS_Port();
      if (queryContractCashFlowOUT != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)queryContractCashFlowOUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)queryContractCashFlowOUT)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (queryContractCashFlowOUT != null)
      ((javax.xml.rpc.Stub)queryContractCashFlowOUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowOUT getQueryContractCashFlowOUT() {
    if (queryContractCashFlowOUT == null)
      _initQueryContractCashFlowOUTProxy();
    return queryContractCashFlowOUT;
  }
  
  public com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowResponse queryContractCashFlow(com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowRequest queryContractCashFlowRequestOut) throws java.rmi.RemoteException{
    if (queryContractCashFlowOUT == null)
      _initQueryContractCashFlowOUTProxy();
    return queryContractCashFlowOUT.queryContractCashFlow(queryContractCashFlowRequestOut);
  }
  
  
}