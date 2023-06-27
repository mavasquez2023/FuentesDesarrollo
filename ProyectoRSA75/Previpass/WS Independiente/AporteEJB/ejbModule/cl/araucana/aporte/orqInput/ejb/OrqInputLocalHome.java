package cl.araucana.aporte.orqInput.ejb;

/**
 * Local Home interface for Enterprise Bean: OrqInputBean
 */
public interface OrqInputLocalHome extends javax.ejb.EJBLocalHome {

    public static final String LOCALHOME_JNDI = "local:ejb/cl/araucana/aporte/orqInput/ejb/OrqInputRemoteHome";

    /**
     * Creates a default instance of Session Bean: OrqInputBean
     */
    public cl.araucana.aporte.orqInput.ejb.OrqInputLocal create()
    throws javax.ejb.CreateException;
}
