package cl.laaraucana.servicios.rolesLDAP;

public class GestionRolesAPPImplProxy implements cl.laaraucana.servicios.rolesLDAP.GestionRolesAPPImpl {
  private String _endpoint = null;
  private cl.laaraucana.servicios.rolesLDAP.GestionRolesAPPImpl gestionRolesAPPImpl = null;
  
  public GestionRolesAPPImplProxy() {
    _initGestionRolesAPPImplProxy();
  }
  
  public GestionRolesAPPImplProxy(String endpoint) {
    _endpoint = endpoint;
    _initGestionRolesAPPImplProxy();
  }
  
  private void _initGestionRolesAPPImplProxy() {
    try {
      gestionRolesAPPImpl = (new cl.laaraucana.servicios.rolesLDAP.RolesLDAPServiceLocator()).getrolesLDAPPort();
      if (gestionRolesAPPImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)gestionRolesAPPImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)gestionRolesAPPImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (gestionRolesAPPImpl != null)
      ((javax.xml.rpc.Stub)gestionRolesAPPImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cl.laaraucana.servicios.rolesLDAP.GestionRolesAPPImpl getGestionRolesAPPImpl() {
    if (gestionRolesAPPImpl == null)
      _initGestionRolesAPPImplProxy();
    return gestionRolesAPPImpl;
  }
  
  public java.lang.String autenticacionWS(cl.laaraucana.servicios.rolesLDAP.CredentialWSTGR credentials) throws java.rmi.RemoteException{
    if (gestionRolesAPPImpl == null)
      _initGestionRolesAPPImplProxy();
    return gestionRolesAPPImpl.autenticacionWS(credentials);
  }
  
  public cl.laaraucana.servicios.rolesLDAP.RolesAPP getRolesinApp(java.lang.String arg0, cl.laaraucana.servicios.rolesLDAP.RequestRolesWS arg1) throws java.rmi.RemoteException{
    if (gestionRolesAPPImpl == null)
      _initGestionRolesAPPImplProxy();
    return gestionRolesAPPImpl.getRolesinApp(arg0, arg1);
  }
  
  public cl.laaraucana.servicios.rolesLDAP.UsersRol getUsersinRole(java.lang.String arg0, cl.laaraucana.servicios.rolesLDAP.RequestUsersWS arg1) throws java.rmi.RemoteException{
    if (gestionRolesAPPImpl == null)
      _initGestionRolesAPPImplProxy();
    return gestionRolesAPPImpl.getUsersinRole(arg0, arg1);
  }
  
  
}