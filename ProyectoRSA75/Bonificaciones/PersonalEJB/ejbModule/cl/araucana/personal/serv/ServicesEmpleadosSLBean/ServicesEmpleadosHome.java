package cl.araucana.personal.serv.ServicesEmpleadosSLBean;
/**
 * Home interface for Enterprise Bean: ServicesEmpleados
 */
public interface ServicesEmpleadosHome extends javax.ejb.EJBHome {
	/**
	 * Creates a default instance of Session Bean: ServicesEmpleados
	 */
	public cl
		.araucana
		.personal
		.serv
		.ServicesEmpleadosSLBean
		.ServicesEmpleados create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
}
