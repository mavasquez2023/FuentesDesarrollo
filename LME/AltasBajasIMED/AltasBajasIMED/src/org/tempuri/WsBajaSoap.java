/**
 * WsBajaSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface WsBajaSoap extends java.rmi.Remote {
    public void wmImed_SrvBaja(org.tempuri.WmImed_SrvBajaCredenciales credenciales, org.tempuri.WmImed_SrvBajaBeneficiario beneficiario, javax.xml.rpc.holders.StringHolder estado, javax.xml.rpc.holders.StringHolder mensaje, javax.xml.rpc.holders.StringHolder codigoTransaccion) throws java.rmi.RemoteException;
}
