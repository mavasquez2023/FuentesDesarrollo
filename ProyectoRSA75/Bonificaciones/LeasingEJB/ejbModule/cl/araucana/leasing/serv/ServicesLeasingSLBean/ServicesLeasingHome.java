package cl.araucana.leasing.serv.ServicesLeasingSLBean;
/**
 * Home interface for Enterprise Bean: ServicesLeasing
 */
public interface ServicesLeasingHome extends javax.ejb.EJBHome {
	/**
	 * Creates a default instance of Session Bean: ServicesLeasing
	 */
	public cl
		.araucana
		.leasing
		.serv
		.ServicesLeasingSLBean
		.ServicesLeasing create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
}
