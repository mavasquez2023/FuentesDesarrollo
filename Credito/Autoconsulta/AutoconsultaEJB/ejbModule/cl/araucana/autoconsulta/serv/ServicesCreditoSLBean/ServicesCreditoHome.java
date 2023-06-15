package cl.araucana.autoconsulta.serv.ServicesCreditoSLBean;
/**
 * Home interface for Enterprise Bean: ServicesCredito
 */
public interface ServicesCreditoHome extends javax.ejb.EJBHome {
	/**
	 * Creates a default instance of Session Bean: ServicesCredito
	 */
	public cl.araucana.autoconsulta.serv.ServicesCreditoSLBean
		.ServicesCredito create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
}
