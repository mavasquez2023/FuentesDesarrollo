/**
 * ServicioSMSInterno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.araucana.ws;

public interface ServicioSMSInterno extends java.rmi.Remote {
    public boolean enviarSMS(java.lang.String telefono, java.lang.String mensaje, java.lang.String servicio, java.lang.String cod_negocio) throws java.rmi.RemoteException;
}
