package WsLMEInet;

public class WsLMEInetPortTypeProxy implements WsLMEInet.WsLMEInetPortType {
  private String _endpoint = null;
  private WsLMEInet.WsLMEInetPortType wsLMEInetPortType = null;
  
  public WsLMEInetPortTypeProxy() {
    _initWsLMEInetPortTypeProxy();
  }
  
  private void _initWsLMEInetPortTypeProxy() {
    try {
      wsLMEInetPortType = (new WsLMEInet.IMEvenLccLocator()).getWsLMEInetPort();
      if (wsLMEInetPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wsLMEInetPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wsLMEInetPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wsLMEInetPortType != null)
      ((javax.xml.rpc.Stub)wsLMEInetPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public WsLMEInet.WsLMEInetPortType getWsLMEInetPortType() {
    if (wsLMEInetPortType == null)
      _initWsLMEInetPortTypeProxy();
    return wsLMEInetPortType;
  }
  
  public WsLMEInet.LMEEvenLccResponse LMEEvenLcc(WsLMEInet.LMEEvenLcc LMEEvenLcc) throws java.rmi.RemoteException{
    if (wsLMEInetPortType == null)
      _initWsLMEInetPortTypeProxy();
    return wsLMEInetPortType.LMEEvenLcc(LMEEvenLcc);
  }
  
  
}