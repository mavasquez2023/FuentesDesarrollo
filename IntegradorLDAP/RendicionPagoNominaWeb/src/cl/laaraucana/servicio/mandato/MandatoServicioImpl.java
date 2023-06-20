/**
 * MandatoServicioImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicio.mandato;

public interface MandatoServicioImpl extends java.rmi.Remote {
    public cl.laaraucana.servicio.mandato.RespuestaInsertMandato ingresarMandato(cl.laaraucana.servicio.mandato.AUTENTICACION autenticacion, cl.laaraucana.servicio.mandato.CuentaBanco cuenta) throws java.rmi.RemoteException;
    public cl.laaraucana.servicio.mandato.CuentaVigente consultarMandato(cl.laaraucana.servicio.mandato.AUTENTICACION autenticacion, cl.laaraucana.servicio.mandato.CONSULTAR cliente) throws java.rmi.RemoteException;
    public cl.laaraucana.servicio.mandato.RespuestaInsertMandato revocarMandato(cl.laaraucana.servicio.mandato.AUTENTICACION autenticacion, cl.laaraucana.servicio.mandato.REVOCAR cliente) throws java.rmi.RemoteException;
}
