/**
 * Autenticacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package cl.paperless.siagf.ws;

public interface Autenticacion extends javax.xml.rpc.Service {
	public java.lang.String getAutenticacionSOAP11port_httpAddress();

	public cl.paperless.siagf.ws.AutenticacionPortType getAutenticacionSOAP11port_http() throws javax.xml.rpc.ServiceException;

	public cl.paperless.siagf.ws.AutenticacionPortType getAutenticacionSOAP11port_http(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;

	public java.lang.String getAutenticacionSOAP12port_httpAddress();

	public cl.paperless.siagf.ws.AutenticacionPortType getAutenticacionSOAP12port_http() throws javax.xml.rpc.ServiceException;

	public cl.paperless.siagf.ws.AutenticacionPortType getAutenticacionSOAP12port_http(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
