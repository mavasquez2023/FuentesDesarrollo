package cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean;

import com.ibm.ejs.container.*;

/**
 * EJSStatelessServicesAutoconsultaHomeBean_6348947c
 */
public class EJSStatelessServicesAutoconsultaHomeBean_6348947c extends EJSHome {
	static final long serialVersionUID = 61;
	/**
	 * EJSStatelessServicesAutoconsultaHomeBean_6348947c
	 */
	public EJSStatelessServicesAutoconsultaHomeBean_6348947c() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 */
	public cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsulta create() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsulta result = null;
boolean createFailed = false;
try {
	result = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsulta) super.createWrapper(null);
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
