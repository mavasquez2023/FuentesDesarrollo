package lme.cl.gov.lme.www;

public class WSLMEValEmpCCAFProxy implements lme.cl.gov.lme.www.WSLMEValEmpCCAF {
  private String _endpoint = null;
  private lme.cl.gov.lme.www.WSLMEValEmpCCAF wSLMEValEmpCCAF = null;
  
  public WSLMEValEmpCCAFProxy() {
    _initWSLMEValEmpCCAFProxy();
  }
  
  private void _initWSLMEValEmpCCAFProxy() {
    try {
      wSLMEValEmpCCAF = (new lme.cl.gov.lme.www.WSLMEValEmpCCAFServiceLocator()).getWSLMEValEmpCCAF();
      if (wSLMEValEmpCCAF != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSLMEValEmpCCAF)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSLMEValEmpCCAF)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSLMEValEmpCCAF != null)
      ((javax.xml.rpc.Stub)wSLMEValEmpCCAF)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public lme.cl.gov.lme.www.WSLMEValEmpCCAF getWSLMEValEmpCCAF() {
    if (wSLMEValEmpCCAF == null)
      _initWSLMEValEmpCCAFProxy();
    return wSLMEValEmpCCAF;
  }
  
  public lme.cl.gov.lme.www.LMEValEmpCCAFResponse LMEValEmpCCAF(lme.cl.gov.lme.www.LMEValEmpCCAFRequest request) throws java.rmi.RemoteException{
    if (wSLMEValEmpCCAF == null)
      _initWSLMEValEmpCCAFProxy();
    return wSLMEValEmpCCAF.LMEValEmpCCAF(request);
  }
  
  
}