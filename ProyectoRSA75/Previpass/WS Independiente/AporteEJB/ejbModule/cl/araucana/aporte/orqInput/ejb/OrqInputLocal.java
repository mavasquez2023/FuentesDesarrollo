package cl.araucana.aporte.orqInput.ejb;

import cl.araucana.aporte.orqInput.bo.OrqInputResultBO;

/**
 * Local interface for Enterprise Bean: OrqInputBean
 */
public interface OrqInputLocal extends javax.ejb.EJBLocalObject {
    public static final String LOCAL_IFACE_NAME = "cl.araucana.aporte.orqInput.ejb.OrqInputLocal";
    public OrqInputResultBO obtenerInfoPago (int rut);

}
