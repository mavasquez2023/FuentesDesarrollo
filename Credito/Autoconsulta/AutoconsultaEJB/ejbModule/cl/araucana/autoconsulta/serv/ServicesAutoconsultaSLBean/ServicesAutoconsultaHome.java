package cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean;
/**
 * Home interface for Enterprise Bean: ServicesAutoconsulta
 */
public interface ServicesAutoconsultaHome extends javax.ejb.EJBHome {
	/**
	 * Creates a default instance of Session Bean: ServicesAutoconsulta
	 */
	public cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean
		.ServicesAutoconsulta create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
}
