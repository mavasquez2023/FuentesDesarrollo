package cl.araucana.aporte.dispDatos.ejb;

/**
 * Local Home interface for Enterprise Bean: DispDatosBean
 */
public interface DispDatosLocalHome extends javax.ejb.EJBLocalHome {

    /**
     * Creates a default instance of Session Bean: DispDatosBean
     */
    public static final String LOCALHOME_JNDI = "local:ejb/cl/araucana/aporte/dispDatos/ejb/DispDatosRemoteHome";

    public cl.araucana.aporte.dispDatos.ejb.DispDatosLocal create()
    throws javax.ejb.CreateException;
}
