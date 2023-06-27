package cl.araucana.aporte.dispDatos.ejb;

import cl.araucana.aporte.dispDatos.bo.DispDatosResultBO;

/**
 * Remote interface for Enterprise Bean: DispDatosBean
 */
public interface DispDatosRemote extends javax.ejb.EJBObject {
    public DispDatosResultBO obtenerInfoDatos (int rut)throws java.rmi.RemoteException;
}
