package cl.araucana.distribuidor.business.ejbs;

/**
 * Home interface for cl.araucana.distribuidor.business.ejbs.DistribuidorSessionBean bean.
 */
public interface DistribuidorSessionHome extends javax.ejb.EJBHome {
	public static final String COMP_NAME = "java:comp/env/ejb/DistribuidorSessionBean";

	public static final String JNDI_NAME = "ejb/cl/araucana/distribuidor/business/ejbs/DistribuidorSessionHome";

	/* Default create */
	public cl.araucana.distribuidor.business.ejbs.DistribuidorSession create()
			throws java.rmi.RemoteException, javax.ejb.CreateException;

}
