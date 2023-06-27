package cl.araucana.aporte.orqInput.ejb;

/**
 * Home interface for Enterprise Bean: OrqInputBean
 */
public interface OrqInputRemoteHome extends javax.ejb.EJBHome {

    /**
     * Creates a default instance of Session Bean: OrqInputBean
     */
    public static final String REMOTE_HOME_JNDI = "cl/araucana/aporte/orqInput/ejb/OrqInputRemoteHome";
    public cl.araucana.aporte.orqInput.ejb.OrqInputRemote create()
    throws javax.ejb.CreateException,
    java.rmi.RemoteException;
}
