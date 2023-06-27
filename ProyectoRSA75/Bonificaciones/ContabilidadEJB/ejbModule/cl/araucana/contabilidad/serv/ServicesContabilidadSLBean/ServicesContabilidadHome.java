package cl.araucana.contabilidad.serv.ServicesContabilidadSLBean;
/**
 * Home interface for Enterprise Bean: ServicesContabilidad
 */
public interface ServicesContabilidadHome extends javax.ejb.EJBHome {
	/**
	 * Creates a default instance of Session Bean: ServicesContabilidad
	 */
	public cl
		.araucana
		.contabilidad
		.serv
		.ServicesContabilidadSLBean
		.ServicesContabilidad create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
}
