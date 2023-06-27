package cl.araucana.aporte.dispDatos.ejb;

import com.ibm.ejs.container.*;

/**
 * EJSStatelessDispDatosBeanHomeBean_d7a63304
 */
public class EJSStatelessDispDatosBeanHomeBean_d7a63304 extends EJSHome {
	static final long serialVersionUID = 61;
	/**
	 * EJSStatelessDispDatosBeanHomeBean_d7a63304
	 */
	public EJSStatelessDispDatosBeanHomeBean_d7a63304() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 */
	public cl.araucana.aporte.dispDatos.ejb.DispDatosRemote create() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
cl.araucana.aporte.dispDatos.ejb.DispDatosRemote result = null;
boolean createFailed = false;
try {
	result = (cl.araucana.aporte.dispDatos.ejb.DispDatosRemote) super.createWrapper(null);
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
	public cl.araucana.aporte.dispDatos.ejb.DispDatosLocal create_Local() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
cl.araucana.aporte.dispDatos.ejb.DispDatosLocal result = null;
boolean createFailed = false;
boolean preCreateFlag = false;
try {
	result = (cl.araucana.aporte.dispDatos.ejb.DispDatosLocal) super.createWrapper_Local(null);
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
