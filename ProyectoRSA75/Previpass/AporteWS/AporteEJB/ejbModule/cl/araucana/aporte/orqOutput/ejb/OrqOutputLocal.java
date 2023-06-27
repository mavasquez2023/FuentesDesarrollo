package cl.araucana.aporte.orqOutput.ejb;

import cl.araucana.aporte.orqOutput.bo.OrqOutputResultBO;

/**
 * Local interface for Enterprise Bean: OrqOutputBean
 */
public interface OrqOutputLocal extends javax.ejb.EJBLocalObject {
    public static final String LOCAL_IFACE_NAME = "cl.araucana.aporte.orqOutput.ejb.OrqOutputLocal";
    public OrqOutputResultBO recuperacionPago (int rut, int montoCredito, int montoLeasing, int montoAporte, int periodoAporte, String fechaRecaudacion, String horaRecaudacion, String usuario, int documentoPago);
}
