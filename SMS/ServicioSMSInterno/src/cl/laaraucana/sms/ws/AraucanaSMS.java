/**
 * AraucanaSMS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.sms.ws;

public interface AraucanaSMS extends java.rmi.Remote {
    public cl.laaraucana.sms.ws.MessageOutput sendSMS(cl.laaraucana.sms.ws.MessageInput arg0) throws java.rmi.RemoteException;
    public cl.laaraucana.sms.ws.StatusSMSOutput statusSMS(cl.laaraucana.sms.ws.StatusSMSInput arg0) throws java.rmi.RemoteException;
}
