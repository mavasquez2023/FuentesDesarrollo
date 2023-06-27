package cl.araucana.distribuidor.business.ejbs;

public class DistribuidorSessionBeanUtil {

	private static Object lookupHome(java.util.Hashtable environment,
			String jndiName, Class narrowTo)
			throws javax.naming.NamingException {
		// Obtain initial context
		javax.naming.InitialContext initialContext = new javax.naming.InitialContext(
				environment);
		try {
			Object objRef = initialContext.lookup(jndiName);
			// only narrow if necessary
			if (java.rmi.Remote.class.isAssignableFrom(narrowTo))
				return javax.rmi.PortableRemoteObject.narrow(objRef, narrowTo);
			else
				return objRef;
		} finally {
			initialContext.close();
		}
	}

	public static cl.araucana.distribuidor.business.ejbs.DistribuidorSessionHome getHome()
			throws javax.naming.NamingException {

		return (cl.araucana.distribuidor.business.ejbs.DistribuidorSessionHome) lookupHome(
				null,
				cl.araucana.distribuidor.business.ejbs.DistribuidorSessionHome.COMP_NAME,
				cl.araucana.distribuidor.business.ejbs.DistribuidorSessionHome.class);

	}

	public static cl.araucana.distribuidor.business.ejbs.DistribuidorSessionHome getHome(
			java.util.Hashtable environment)
			throws javax.naming.NamingException {

		return (cl.araucana.distribuidor.business.ejbs.DistribuidorSessionHome) lookupHome(
				environment,
				cl.araucana.distribuidor.business.ejbs.DistribuidorSessionHome.COMP_NAME,
				cl.araucana.distribuidor.business.ejbs.DistribuidorSessionHome.class);

	}

}