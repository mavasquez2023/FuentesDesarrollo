/**
 * UsuariosLDAPService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicios.usuariosLDAP;

public interface UsuariosLDAPService extends javax.xml.rpc.Service {
    public java.lang.String getusuariosLDAPPortAddress();

    public cl.laaraucana.servicios.usuariosLDAP.RegistrarUsuarioImpl getusuariosLDAPPort() throws javax.xml.rpc.ServiceException;

    public cl.laaraucana.servicios.usuariosLDAP.RegistrarUsuarioImpl getusuariosLDAPPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
