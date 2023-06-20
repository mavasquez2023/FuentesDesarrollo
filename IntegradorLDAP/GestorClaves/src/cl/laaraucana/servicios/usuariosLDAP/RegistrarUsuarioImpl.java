/**
 * RegistrarUsuarioImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicios.usuariosLDAP;

public interface RegistrarUsuarioImpl extends java.rmi.Remote {
    public java.lang.String autenticacionUWS(cl.laaraucana.servicios.usuariosLDAP.CredentialWS credentials) throws java.rmi.RemoteException;
    public cl.laaraucana.servicios.usuariosLDAP.UsuariosLDAP addUsuario(java.lang.String token, cl.laaraucana.servicios.usuariosLDAP.RequestUsuarioWS datos, cl.laaraucana.servicios.usuariosLDAP.RequestNotificarWS notificar) throws java.rmi.RemoteException;
    public cl.laaraucana.servicios.usuariosLDAP.UsuariosLDAP delUsuario(java.lang.String token, java.lang.String rut) throws java.rmi.RemoteException;
    public cl.laaraucana.servicios.usuariosLDAP.UsuariosLDAP changePassword(java.lang.String token, cl.laaraucana.servicios.usuariosLDAP.RequestPasswordWS request, cl.laaraucana.servicios.usuariosLDAP.RequestNotificarWS notificar) throws java.rmi.RemoteException;
}
