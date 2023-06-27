package cl.araucana.aporte.orqInput.ejb;

import com.ibm.ejs.container.*;

/**
 * EJSRemoteStatelessOrqInputBean_b79450f3
 */
public class EJSRemoteStatelessOrqInputBean_b79450f3 extends EJSWrapper implements OrqInputRemote {
	/**
	 * EJSRemoteStatelessOrqInputBean_b79450f3
	 */
	public EJSRemoteStatelessOrqInputBean_b79450f3() throws java.rmi.RemoteException {
		super();	}
	/**
	 * obtenerInfoPago
	 */
	public cl.araucana.aporte.orqInput.bo.OrqInputResultBO obtenerInfoPago(int rut) throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		cl.araucana.aporte.orqInput.bo.OrqInputResultBO _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = new java.lang.Integer(rut);
			}
	cl.araucana.aporte.orqInput.ejb.OrqInpuBean beanRef = (cl.araucana.aporte.orqInput.ejb.OrqInpuBean)container.preInvoke(this, 0, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.obtenerInfoPago(rut);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 0, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
}
