/**
 * CuentaBancariaServicioImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicio.transferencias;

public interface CuentaBancariaServicioImpl extends java.rmi.Remote {
    public java.lang.String autenticacionWS(cl.laaraucana.servicio.transferencias.CredentialWS credentials) throws java.rmi.RemoteException;
    public cl.laaraucana.servicio.transferencias.LOG_RESPUESTA ingresarCuenta(java.lang.String token, cl.laaraucana.servicio.transferencias.CuentaBanco cuenta) throws java.rmi.RemoteException;
    public cl.laaraucana.servicio.transferencias.LOG_RESPUESTA revocarCuenta(java.lang.String token, cl.laaraucana.servicio.transferencias.CuentaEdit cuenta) throws java.rmi.RemoteException;
    public cl.laaraucana.servicio.transferencias.CuentasRUT consultaCuenta(java.lang.String token, cl.laaraucana.servicio.transferencias.CuentaSearch cuenta) throws java.rmi.RemoteException;
}
