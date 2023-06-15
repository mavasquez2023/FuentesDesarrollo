/**
 * RolesLDAPService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicios.rolesLDAP;

public interface RolesLDAPService extends javax.xml.rpc.Service {
    public java.lang.String getrolesLDAPPortAddress();

    public cl.laaraucana.servicios.rolesLDAP.GestionRolesAPPImpl getrolesLDAPPort() throws javax.xml.rpc.ServiceException;

    public cl.laaraucana.servicios.rolesLDAP.GestionRolesAPPImpl getrolesLDAPPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
