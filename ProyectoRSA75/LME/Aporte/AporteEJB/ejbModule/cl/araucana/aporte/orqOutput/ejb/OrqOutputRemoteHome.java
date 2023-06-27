package cl.araucana.aporte.orqOutput.ejb;

/**
 * Home interface for Enterprise Bean: OrqOutputBean
 */
public interface OrqOutputRemoteHome extends javax.ejb.EJBHome {

    /**
     * Creates a default instance of Session Bean: OrqOutputBean
     */
    public static final String REMOTE_HOME_JNDI = "cl/araucana/aporte/orqOutput/ejb/OrqOutputRemoteHome";
    public cl.araucana.aporte.orqOutput.ejb.OrqOutputRemote create()
    throws javax.ejb.CreateException,
    java.rmi.RemoteException;
}
