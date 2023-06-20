/**
 * CEDU0702Soap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.sinacofi.WebServices;

public interface CEDU0702Soap extends java.rmi.Remote {
    public cl.sinacofi.WebServices.RespuestaCEDU0702 consulta(java.lang.String usuario, java.lang.String claveUsuario, java.lang.String rutCliente, java.lang.String numeroSerie, java.lang.String canalInstitucion) throws java.rmi.RemoteException;
}
