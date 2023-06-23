package cl.araucana.aporte.dispDatos.ejb;

/**
 * Home interface for Enterprise Bean: DispDatosBean
 */
public interface DispDatosRemoteHome extends javax.ejb.EJBHome {

    /**
     * Creates a default instance of Session Bean: DispDatosBean
     */
    public static final String REMOTE_HOME_JNDI = "cl/araucana/aporte/dispDatos/ejb/DispDatosRemoteHome";
    public cl.araucana.aporte.dispDatos.ejb.DispDatosRemote create()
    throws javax.ejb.CreateException,
    java.rmi.RemoteException;
}
