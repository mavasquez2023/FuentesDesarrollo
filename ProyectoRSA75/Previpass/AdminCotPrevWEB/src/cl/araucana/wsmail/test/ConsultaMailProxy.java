package cl.araucana.wsmail.test;

public class ConsultaMailProxy implements cl.araucana.wsmail.test.ConsultaMail {
  private String _endpoint = null;
  private cl.araucana.wsmail.test.ConsultaMail consultaMail = null;
  
  public ConsultaMailProxy() {
    _initConsultaMailProxy();
  }
  
  private void _initConsultaMailProxy() {
    try {
      consultaMail = (new cl.araucana.wsmail.test.ConsultaMailServiceLocator()).getConsultaMail();
      if (consultaMail != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)consultaMail)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)consultaMail)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (consultaMail != null)
      ((javax.xml.rpc.Stub)consultaMail)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cl.araucana.wsmail.test.ConsultaMail getConsultaMail() {
    if (consultaMail == null)
      _initConsultaMailProxy();
    return consultaMail;
  }
  
  public java.lang.String getMailAdmin(java.lang.String rutEmpresa) throws java.rmi.RemoteException{
    if (consultaMail == null)
      _initConsultaMailProxy();
    return consultaMail.getMailAdmin(rutEmpresa);
  }
  
  
}