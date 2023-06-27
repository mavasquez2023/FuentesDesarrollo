package cl.araucana.aporte.orqInput.ejb;

import com.ibm.ejs.container.*;

/**
 * EJSStatelessOrqInputBeanHomeBean_b79450f3
 */
public class EJSStatelessOrqInputBeanHomeBean_b79450f3 extends EJSHome {
	static final long serialVersionUID = 61;
	/**
	 * EJSStatelessOrqInputBeanHomeBean_b79450f3
	 */
	public EJSStatelessOrqInputBeanHomeBean_b79450f3() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 */
	public cl.araucana.aporte.orqInput.ejb.OrqInputRemote create() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
cl.araucana.aporte.orqInput.ejb.OrqInputRemote result = null;
boolean createFailed = false;
try {
	result = (cl.araucana.aporte.orqInput.ejb.OrqInputRemote) super.createWrapper(null);
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
	/**
	 * create_Local
	 */
	public cl.araucana.aporte.orqInput.ejb.OrqInputLocal create_Local() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
cl.araucana.aporte.orqInput.ejb.OrqInputLocal result = null;
boolean createFailed = false;
boolean preCreateFlag = false;
try {
	result = (cl.araucana.aporte.orqInput.ejb.OrqInputLocal) super.createWrapper_Local(null);
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
