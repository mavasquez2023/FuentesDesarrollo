/**
 * OrqOutputServiceImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package cl.araucana.aporte.orqOutput.cliente.service;

public interface OrqOutputServiceImpl extends java.rmi.Remote {
    public cl.araucana.aporte.orqOutput.cliente.service.vo.OrqOutputResultVO recuperacionPago(int rut, int montoCredito, int montoLeasing, int montoAporte, int periodoAporte, java.lang.String fechaRecaudacion, java.lang.String horaRecaudacion, java.lang.String usuario, int documentoPago) throws java.rmi.RemoteException;
    public cl.araucana.aporte.orqOutput.cliente.service.vo.OrqOutputResultVO recuperacionPagoRemote(int rut, int montoCredito, int montoLeasing, int montoAporte, int periodoAporte, java.lang.String fechaRecaudacion, java.lang.String horaRecaudacion, java.lang.String usuario, int documentoPago) throws java.rmi.RemoteException;
}
