/**
 * ComercioSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package cl.araucana.spl.cliente.webServices.bbva;

public interface ComercioSoap extends java.rmi.Remote {
    public java.lang.String solicitarAcceso(int comercioID, java.lang.String clave) throws java.rmi.RemoteException;
    public java.lang.String enviarTransaccion(int comercioID, java.lang.String llave, java.lang.String transaccion, int monto, int cantidadPagos, java.lang.String pagos) throws java.rmi.RemoteException;
}
