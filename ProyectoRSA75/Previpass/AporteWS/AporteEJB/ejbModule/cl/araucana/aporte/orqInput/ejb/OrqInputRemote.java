package cl.araucana.aporte.orqInput.ejb;

import cl.araucana.aporte.orqInput.bo.OrqInputResultBO;

/**
 * Remote interface for Enterprise Bean: OrqInputBean
 */
public interface OrqInputRemote extends javax.ejb.EJBObject {
    public OrqInputResultBO obtenerInfoPago (int rut)throws java.rmi.RemoteException;
}
