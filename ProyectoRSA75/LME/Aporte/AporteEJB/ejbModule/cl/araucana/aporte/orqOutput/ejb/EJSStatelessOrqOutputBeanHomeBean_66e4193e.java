package cl.araucana.aporte.orqOutput.ejb;

import com.ibm.ejs.container.*;

/**
 * EJSStatelessOrqOutputBeanHomeBean_66e4193e
 */
public class EJSStatelessOrqOutputBeanHomeBean_66e4193e extends EJSHome {
	static final long serialVersionUID = 61;
	/**
	 * EJSStatelessOrqOutputBeanHomeBean_66e4193e
	 */
	public EJSStatelessOrqOutputBeanHomeBean_66e4193e() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 */
	public cl.araucana.aporte.orqOutput.ejb.OrqOutputRemote create() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
cl.araucana.aporte.orqOutput.ejb.OrqOutputRemote result = null;
boolean createFailed = false;
try {
	result = (cl.araucana.aporte.orqOutput.ejb.OrqOutputRemote) super.createWrapper(null);
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
	public cl.araucana.aporte.orqOutput.ejb.OrqOutputLocal create_Local() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
cl.araucana.aporte.orqOutput.ejb.OrqOutputLocal result = null;
boolean createFailed = false;
boolean preCreateFlag = false;
try {
	result = (cl.araucana.aporte.orqOutput.ejb.OrqOutputLocal) super.createWrapper_Local(null);
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
