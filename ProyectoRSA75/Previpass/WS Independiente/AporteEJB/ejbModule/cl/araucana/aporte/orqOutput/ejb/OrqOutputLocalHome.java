package cl.araucana.aporte.orqOutput.ejb;

/**
 * Local Home interface for Enterprise Bean: OrqOutputBean
 */
public interface OrqOutputLocalHome extends javax.ejb.EJBLocalHome {

    public static final String LOCALHOME_JNDI = "local:ejb/cl/araucana/aporte/orqOutput/ejb/OrqOutputRemoteHome";
    /**
     * Creates a default instance of Session Bean: OrqOutputBean
     */
    public cl.araucana.aporte.orqOutput.ejb.OrqOutputLocal create()
    throws javax.ejb.CreateException;
}
