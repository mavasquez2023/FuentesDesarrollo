package cl.araucana.cp.distribuidor.business.ejbs;

import com.ibm.ejs.container.*;

/**
 * EJSRemoteStatelessDistribuidorSessionHome_b6b3452b
 */
public class EJSRemoteStatelessDistribuidorSessionHome_b6b3452b extends EJSWrapper implements cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionHome {
	/**
	 * EJSRemoteStatelessDistribuidorSessionHome_b6b3452b
	 */
	public EJSRemoteStatelessDistribuidorSessionHome_b6b3452b() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 */
	public cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSession create() throws java.rmi.RemoteException, javax.ejb.CreateException, java.io.IOException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSession _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[0];
			}
	cl.araucana.cp.distribuidor.business.ejbs.EJSStatelessDistribuidorSessionHomeBean_b6b3452b _EJS_beanRef = (cl.araucana.cp.distribuidor.business.ejbs.EJSStatelessDistribuidorSessionHomeBean_b6b3452b)container.preInvoke(this, 0, _EJS_s, _jacc_parms);
			_EJS_result = _EJS_beanRef.create();
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (javax.ejb.CreateException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.io.IOException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try {
				container.postInvoke(this, 0, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getEJBMetaData
	 */
	public javax.ejb.EJBMetaData getEJBMetaData() throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		javax.ejb.EJBMetaData _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[0];
			}
	cl.araucana.cp.distribuidor.business.ejbs.EJSStatelessDistribuidorSessionHomeBean_b6b3452b _EJS_beanRef = (cl.araucana.cp.distribuidor.business.ejbs.EJSStatelessDistribuidorSessionHomeBean_b6b3452b)container.preInvoke(this, 1, _EJS_s, _jacc_parms);
			_EJS_result = _EJS_beanRef.getEJBMetaData();
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try {
				container.postInvoke(this, 1, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getHomeHandle
	 */
	public javax.ejb.HomeHandle getHomeHandle() throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		javax.ejb.HomeHandle _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[0];
			}
	cl.araucana.cp.distribuidor.business.ejbs.EJSStatelessDistribuidorSessionHomeBean_b6b3452b _EJS_beanRef = (cl.araucana.cp.distribuidor.business.ejbs.EJSStatelessDistribuidorSessionHomeBean_b6b3452b)container.preInvoke(this, 2, _EJS_s, _jacc_parms);
			_EJS_result = _EJS_beanRef.getHomeHandle();
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try {
				container.postInvoke(this, 2, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * remove
	 */
	public void remove(java.lang.Object arg0) throws java.rmi.RemoteException, javax.ejb.RemoveException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = arg0;
			}
	cl.araucana.cp.distribuidor.business.ejbs.EJSStatelessDistribuidorSessionHomeBean_b6b3452b _EJS_beanRef = (cl.araucana.cp.distribuidor.business.ejbs.EJSStatelessDistribuidorSessionHomeBean_b6b3452b)container.preInvoke(this, 3, _EJS_s, _jacc_parms);
			_EJS_beanRef.remove(arg0);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (javax.ejb.RemoveException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try {
				container.postInvoke(this, 3, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * remove
	 */
	public void remove(javax.ejb.Handle arg0) throws java.rmi.RemoteException, javax.ejb.RemoveException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = arg0;
			}
	cl.araucana.cp.distribuidor.business.ejbs.EJSStatelessDistribuidorSessionHomeBean_b6b3452b _EJS_beanRef = (cl.araucana.cp.distribuidor.business.ejbs.EJSStatelessDistribuidorSessionHomeBean_b6b3452b)container.preInvoke(this, 4, _EJS_s, _jacc_parms);
			_EJS_beanRef.remove(arg0);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (javax.ejb.RemoveException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try {
				container.postInvoke(this, 4, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
}
