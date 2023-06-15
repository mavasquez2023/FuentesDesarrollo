package com.lautaro.xi.BS.WEB_Mobile;

public class EarlyPayoffSimulationOUT2Proxy implements com.lautaro.xi.BS.WEB_Mobile.EarlyPayoffSimulationOUT2 {
  private String _endpoint = null;
  private com.lautaro.xi.BS.WEB_Mobile.EarlyPayoffSimulationOUT2 earlyPayoffSimulationOUT2 = null;
  
  public EarlyPayoffSimulationOUT2Proxy() {
    _initEarlyPayoffSimulationOUT2Proxy();
  }
  
  public EarlyPayoffSimulationOUT2Proxy(String endpoint) {
    _endpoint = endpoint;
    _initEarlyPayoffSimulationOUT2Proxy();
  }
  
  private void _initEarlyPayoffSimulationOUT2Proxy() {
    try {
      earlyPayoffSimulationOUT2 = (new com.lautaro.xi.BS.WEB_Mobile.EarlyPayoffSimulationOUT2ServiceLocator()).getHTTPS_Port();
      if (earlyPayoffSimulationOUT2 != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)earlyPayoffSimulationOUT2)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)earlyPayoffSimulationOUT2)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (earlyPayoffSimulationOUT2 != null)
      ((javax.xml.rpc.Stub)earlyPayoffSimulationOUT2)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lautaro.xi.BS.WEB_Mobile.EarlyPayoffSimulationOUT2 getEarlyPayoffSimulationOUT2() {
    if (earlyPayoffSimulationOUT2 == null)
      _initEarlyPayoffSimulationOUT2Proxy();
    return earlyPayoffSimulationOUT2;
  }
  
  public com.lautaro.xi.BS.WEB_Mobile.EarlyPayoffContractResponse2 earlyPayoffSimulation(com.lautaro.xi.BS.WEB_Mobile.EarlyPayoffContractRequest2 earlyPayoffSimulationRequestOut2) throws java.rmi.RemoteException{
    if (earlyPayoffSimulationOUT2 == null)
      _initEarlyPayoffSimulationOUT2Proxy();
    return earlyPayoffSimulationOUT2.earlyPayoffSimulation(earlyPayoffSimulationRequestOut2);
  }
  
  
}