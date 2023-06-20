package cl.laaraucana.servicios.usuariosLDAP;

public class RegistrarUsuarioImplProxy implements cl.laaraucana.servicios.usuariosLDAP.RegistrarUsuarioImpl {
  private String _endpoint = null;
  private cl.laaraucana.servicios.usuariosLDAP.RegistrarUsuarioImpl registrarUsuarioImpl = null;
  
  public RegistrarUsuarioImplProxy() {
    _initRegistrarUsuarioImplProxy();
  }
  
  public RegistrarUsuarioImplProxy(String endpoint) {
    _endpoint = endpoint;
    _initRegistrarUsuarioImplProxy();
  }
  
  private void _initRegistrarUsuarioImplProxy() {
    try {
      registrarUsuarioImpl = (new cl.laaraucana.servicios.usuariosLDAP.UsuariosLDAPServiceLocator()).getusuariosLDAPPort();
      if (registrarUsuarioImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)registrarUsuarioImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)registrarUsuarioImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (registrarUsuarioImpl != null)
      ((javax.xml.rpc.Stub)registrarUsuarioImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cl.laaraucana.servicios.usuariosLDAP.RegistrarUsuarioImpl getRegistrarUsuarioImpl() {
    if (registrarUsuarioImpl == null)
      _initRegistrarUsuarioImplProxy();
    return registrarUsuarioImpl;
  }
  
  public cl.laaraucana.servicios.usuariosLDAP.UsuariosLDAP addUsuario(java.lang.String token, cl.laaraucana.servicios.usuariosLDAP.RequestUsuarioWS datos, cl.laaraucana.servicios.usuariosLDAP.RequestNotificarWS notificar) throws java.rmi.RemoteException{
    if (registrarUsuarioImpl == null)
      _initRegistrarUsuarioImplProxy();
    return registrarUsuarioImpl.addUsuario(token, datos, notificar);
  }
  
  public cl.laaraucana.servicios.usuariosLDAP.UsuariosLDAP delUsuario(java.lang.String token, java.lang.String rut) throws java.rmi.RemoteException{
    if (registrarUsuarioImpl == null)
      _initRegistrarUsuarioImplProxy();
    return registrarUsuarioImpl.delUsuario(token, rut);
  }
  
  public cl.laaraucana.servicios.usuariosLDAP.UsuariosLDAP changePassword(java.lang.String token, cl.laaraucana.servicios.usuariosLDAP.RequestPasswordWS request, cl.laaraucana.servicios.usuariosLDAP.RequestNotificarWS notificar) throws java.rmi.RemoteException{
    if (registrarUsuarioImpl == null)
      _initRegistrarUsuarioImplProxy();
    return registrarUsuarioImpl.changePassword(token, request, notificar);
  }
  
  public java.lang.String autenticacionUWS(cl.laaraucana.servicios.usuariosLDAP.CredentialWS credentials) throws java.rmi.RemoteException{
    if (registrarUsuarioImpl == null)
      _initRegistrarUsuarioImplProxy();
    return registrarUsuarioImpl.autenticacionUWS(credentials);
  }
  
  
}