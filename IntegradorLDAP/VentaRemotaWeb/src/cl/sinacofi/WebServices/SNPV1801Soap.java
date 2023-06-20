/**
 * SNPV1801Soap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.sinacofi.WebServices;

public interface SNPV1801Soap extends java.rmi.Remote {
    public cl.sinacofi.WebServices.RespuestaSNPV1801 consulta(java.lang.String usuario, java.lang.String claveUsuario, java.lang.String rutCliente, java.lang.String canalInstitucion, java.lang.String idChallenge, cl.sinacofi.wsdl.SDN122REQ.RESPUESTAS[] desafio) throws java.rmi.RemoteException;
}
