package cl.araucana.cp.distribuidor.business.ejbs;

import com.ibm.ejs.container.*;

/**
 * EJSStatelessDistribuidorSessionHomeBean_b6b3452b
 */
public class EJSStatelessDistribuidorSessionHomeBean_b6b3452b extends EJSHome {
	static final long serialVersionUID = 61;
	/**
	 * EJSStatelessDistribuidorSessionHomeBean_b6b3452b
	 */
	public EJSStatelessDistribuidorSessionHomeBean_b6b3452b() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 */
	public cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSession create() throws java.rmi.RemoteException, javax.ejb.CreateException, java.io.IOException {
BeanO beanO = null;
cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSession result = null;
boolean createFailed = false;
try {
	result = (cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSession) super.createWrapper(null);
}
catch (javax.ejb.CreateException ex) {
	createFailed = true;
	throw ex;
} catch (java.rmi.RemoteException ex) {
	createFailed = true;
	throw ex;
} catch (Throwable ex) {
	createFailed = true;
	throw new CreateFailureException(ex);
} finally {
	if (createFailed) {
		super.createFailure(beanO);
	}
}
return result;	}
}
