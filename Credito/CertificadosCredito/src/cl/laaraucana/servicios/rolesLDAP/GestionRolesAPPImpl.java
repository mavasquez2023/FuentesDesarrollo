/**
 * GestionRolesAPPImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicios.rolesLDAP;

public interface GestionRolesAPPImpl extends java.rmi.Remote {
    public java.lang.String autenticacionWS(cl.laaraucana.servicios.rolesLDAP.CredentialWSTGR credentials) throws java.rmi.RemoteException;
    public cl.laaraucana.servicios.rolesLDAP.RolesAPP getRolesinApp(java.lang.String arg0, cl.laaraucana.servicios.rolesLDAP.RequestRolesWS arg1) throws java.rmi.RemoteException;
    public cl.laaraucana.servicios.rolesLDAP.UsersRol getUsersinRole(java.lang.String arg0, cl.laaraucana.servicios.rolesLDAP.RequestUsersWS arg1) throws java.rmi.RemoteException;
}
