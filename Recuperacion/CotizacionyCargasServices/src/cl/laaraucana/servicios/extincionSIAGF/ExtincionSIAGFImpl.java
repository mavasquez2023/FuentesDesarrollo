/**
 * ExtincionSIAGFImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicios.extincionSIAGF;

public interface ExtincionSIAGFImpl extends java.rmi.Remote {
    public java.lang.Boolean getStatus(java.lang.String token) throws java.rmi.RemoteException;
    public java.lang.String autenticacionWS(cl.laaraucana.servicios.extincionSIAGF.CredentialWSTGR credentials) throws java.rmi.RemoteException;
    public cl.laaraucana.servicios.extincionSIAGF.ResponseWS extingueCausantes(java.lang.String token, cl.laaraucana.servicios.extincionSIAGF.DataWS[] request) throws java.rmi.RemoteException;
}
