package cl.laaraucana.servicios.extincionSIAGF;

public class ExtincionSIAGFImplProxy implements cl.laaraucana.servicios.extincionSIAGF.ExtincionSIAGFImpl {
  private String _endpoint = null;
  private cl.laaraucana.servicios.extincionSIAGF.ExtincionSIAGFImpl extincionSIAGFImpl = null;
  
  public ExtincionSIAGFImplProxy() {
    _initExtincionSIAGFImplProxy();
  }
  
  public ExtincionSIAGFImplProxy(String endpoint) {
    _endpoint = endpoint;
    _initExtincionSIAGFImplProxy();
  }
  
  private void _initExtincionSIAGFImplProxy() {
    try {
      extincionSIAGFImpl = (new cl.laaraucana.servicios.extincionSIAGF.ExtincionSIAGFServiceLocator()).getExtincionSIAGFPort();
      if (extincionSIAGFImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)extincionSIAGFImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)extincionSIAGFImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (extincionSIAGFImpl != null)
      ((javax.xml.rpc.Stub)extincionSIAGFImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cl.laaraucana.servicios.extincionSIAGF.ExtincionSIAGFImpl getExtincionSIAGFImpl() {
    if (extincionSIAGFImpl == null)
      _initExtincionSIAGFImplProxy();
    return extincionSIAGFImpl;
  }
  
  public java.lang.Boolean getStatus(java.lang.String token) throws java.rmi.RemoteException{
    if (extincionSIAGFImpl == null)
      _initExtincionSIAGFImplProxy();
    return extincionSIAGFImpl.getStatus(token);
  }
  
  public java.lang.String autenticacionWS(cl.laaraucana.servicios.extincionSIAGF.CredentialWSTGR credentials) throws java.rmi.RemoteException{
    if (extincionSIAGFImpl == null)
      _initExtincionSIAGFImplProxy();
    return extincionSIAGFImpl.autenticacionWS(credentials);
  }
  
  public cl.laaraucana.servicios.extincionSIAGF.ResponseWS extingueCausantes(java.lang.String token, cl.laaraucana.servicios.extincionSIAGF.DataWS[] request) throws java.rmi.RemoteException{
    if (extincionSIAGFImpl == null)
      _initExtincionSIAGFImplProxy();
    return extincionSIAGFImpl.extingueCausantes(token, request);
  }
  
  
}