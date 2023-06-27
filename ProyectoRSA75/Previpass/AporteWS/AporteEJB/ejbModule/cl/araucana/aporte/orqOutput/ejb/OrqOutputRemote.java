package cl.araucana.aporte.orqOutput.ejb;

import cl.araucana.aporte.orqOutput.bo.OrqOutputResultBO;

/**
 * Remote interface for Enterprise Bean: OrqOutputBean
 */
public interface OrqOutputRemote extends javax.ejb.EJBObject {
    public OrqOutputResultBO recuperacionPago (int rut, int montoCredito, int montoLeasing, int montoAporte, int periodoAporte, String fechaRecaudacion, String horaRecaudacion, String usuario, int documentoPago) throws java.rmi.RemoteException;
}
