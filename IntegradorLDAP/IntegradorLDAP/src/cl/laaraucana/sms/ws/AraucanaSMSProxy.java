package cl.laaraucana.sms.ws;

public class AraucanaSMSProxy implements cl.laaraucana.sms.ws.AraucanaSMS {
  private String _endpoint = null;
  private cl.laaraucana.sms.ws.AraucanaSMS araucanaSMS = null;
  
  public AraucanaSMSProxy() {
    _initAraucanaSMSProxy();
  }
  
  public AraucanaSMSProxy(String endpoint) {
    _endpoint = endpoint;
    _initAraucanaSMSProxy();
  }
  
  private void _initAraucanaSMSProxy() {
    try {
      araucanaSMS = (new cl.laaraucana.sms.ws.AraucanaSMSServiceLocator()).getAraucanaSMSPort();
      if (araucanaSMS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)araucanaSMS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)araucanaSMS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (araucanaSMS != null)
      ((javax.xml.rpc.Stub)araucanaSMS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cl.laaraucana.sms.ws.AraucanaSMS getAraucanaSMS() {
    if (araucanaSMS == null)
      _initAraucanaSMSProxy();
    return araucanaSMS;
  }
  
  public cl.laaraucana.sms.ws.MessageOutput sendSMS(cl.laaraucana.sms.ws.MessageInput arg0) throws java.rmi.RemoteException{
    if (araucanaSMS == null)
      _initAraucanaSMSProxy();
    return araucanaSMS.sendSMS(arg0);
  }
  
  public cl.laaraucana.sms.ws.StatusSMSOutput statusSMS(cl.laaraucana.sms.ws.StatusSMSInput arg0) throws java.rmi.RemoteException{
    if (araucanaSMS == null)
      _initAraucanaSMSProxy();
    return araucanaSMS.statusSMS(arg0);
  }
  
  
}