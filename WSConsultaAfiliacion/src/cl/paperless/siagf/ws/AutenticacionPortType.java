/**
 * AutenticacionPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package cl.paperless.siagf.ws;

public interface AutenticacionPortType extends java.rmi.Remote {
	public java.lang.String login(java.lang.Integer codigoEntidad, java.lang.String loginUsuario, java.lang.String claveUsuario) throws java.rmi.RemoteException;
}
