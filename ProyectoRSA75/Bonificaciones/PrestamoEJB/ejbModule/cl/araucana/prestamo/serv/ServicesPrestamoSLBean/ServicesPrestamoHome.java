package cl.araucana.prestamo.serv.ServicesPrestamoSLBean;
/**
 * Home interface for Enterprise Bean: ServicesPrestamo
 */
public interface ServicesPrestamoHome extends javax.ejb.EJBHome {
	/**
	 * Creates a default instance of Session Bean: ServicesPrestamo
	 */
	public cl
		.araucana
		.prestamo
		.serv
		.ServicesPrestamoSLBean
		.ServicesPrestamo create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
}
