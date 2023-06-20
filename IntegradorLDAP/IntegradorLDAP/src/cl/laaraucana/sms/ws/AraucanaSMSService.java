/**
 * AraucanaSMSService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.sms.ws;

public interface AraucanaSMSService extends javax.xml.rpc.Service {
    public java.lang.String getAraucanaSMSPortAddress();

    public cl.laaraucana.sms.ws.AraucanaSMS getAraucanaSMSPort() throws javax.xml.rpc.ServiceException;

    public cl.laaraucana.sms.ws.AraucanaSMS getAraucanaSMSPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
