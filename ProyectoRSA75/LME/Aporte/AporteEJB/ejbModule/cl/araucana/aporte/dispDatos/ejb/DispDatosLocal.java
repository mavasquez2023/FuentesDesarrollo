package cl.araucana.aporte.dispDatos.ejb;

import cl.araucana.aporte.dispDatos.bo.DispDatosResultBO;

/**
 * Local interface for Enterprise Bean: DispDatosBean
 */
public interface DispDatosLocal extends javax.ejb.EJBLocalObject {	
    public static final String LOCAL_IFACE_NAME = "cl.araucana.aporte.dispDatos.ejb.DispDatosLocal";
    public DispDatosResultBO obtenerInfoDatos (int rut);
}
