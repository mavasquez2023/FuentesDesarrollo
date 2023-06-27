package cl.araucana.cp.distribuidor.business.ejbs;

import com.ibm.ejs.container.*;

/**
 * EJSRemoteStatelessDistribuidorSession_b6b3452b
 */
public class EJSRemoteStatelessDistribuidorSession_b6b3452b extends EJSWrapper implements DistribuidorSession {
	/**
	 * EJSRemoteStatelessDistribuidorSession_b6b3452b
	 */
	public EJSRemoteStatelessDistribuidorSession_b6b3452b() throws java.rmi.RemoteException {
		super();	}
	/**
	 * actualizaBalanceo
	 */
	public boolean actualizaBalanceo(java.lang.String idPersona) throws cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		boolean _EJS_result = false;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = idPersona;
			}
	cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean beanRef = (cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean)container.preInvoke(this, 0, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.actualizaBalanceo(idPersona);
		}
		catch (cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
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
	/**
	 * cargaConfiguracion
	 */
	public boolean cargaConfiguracion() throws cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		boolean _EJS_result = false;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[0];
			}
	cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean beanRef = (cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean)container.preInvoke(this, 1, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.cargaConfiguracion();
		}
		catch (cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
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
				container.postInvoke(this, 1, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * asigna
	 */
	public cl.araucana.cp.distribuidor.hibernate.beans.NodoVO asigna(java.lang.String idPersona) throws cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		cl.araucana.cp.distribuidor.hibernate.beans.NodoVO _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = idPersona;
			}
	cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean beanRef = (cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean)container.preInvoke(this, 2, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.asigna(idPersona);
		}
		catch (cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
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
				container.postInvoke(this, 2, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * eliminaTrabajador
	 */
	public int eliminaTrabajador(boolean pendiente, int rutEmpresa, int idConvenio, char tipoProceso, int idTrabajador, java.lang.String idPersona, int periodo) throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		int _EJS_result = 0;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[7];
				_jacc_parms[0] = new java.lang.Boolean(pendiente);
				_jacc_parms[1] = new java.lang.Integer(rutEmpresa);
				_jacc_parms[2] = new java.lang.Integer(idConvenio);
				_jacc_parms[3] = new java.lang.Character(tipoProceso);
				_jacc_parms[4] = new java.lang.Integer(idTrabajador);
				_jacc_parms[5] = idPersona;
				_jacc_parms[6] = new java.lang.Integer(periodo);
			}
	cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean beanRef = (cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean)container.preInvoke(this, 3, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.eliminaTrabajador(pendiente, rutEmpresa, idConvenio, tipoProceso, idTrabajador, idPersona, periodo);
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
				container.postInvoke(this, 3, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getEnv
	 */
	public java.lang.StringBuffer getEnv() throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.lang.StringBuffer _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[0];
			}
	cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean beanRef = (cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean)container.preInvoke(this, 4, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getEnv();
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
				container.postInvoke(this, 4, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * asigna
	 */
	public java.util.HashMap asigna(int idEnvio, java.lang.String idPersona) throws cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.HashMap _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = new java.lang.Integer(idEnvio);
				_jacc_parms[1] = idPersona;
			}
	cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean beanRef = (cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean)container.preInvoke(this, 5, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.asigna(idEnvio, idPersona);
		}
		catch (cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
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
				container.postInvoke(this, 5, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * validaCarga
	 */
	public java.util.HashMap validaCarga(java.lang.String idPersona, char tipoProceso, java.util.HashMap nominas) throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.HashMap _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[3];
				_jacc_parms[0] = idPersona;
				_jacc_parms[1] = new java.lang.Character(tipoProceso);
				_jacc_parms[2] = nominas;
			}
	cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean beanRef = (cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean)container.preInvoke(this, 6, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.validaCarga(idPersona, tipoProceso, nominas);
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
				container.postInvoke(this, 6, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getEstadisticas
	 */
	public java.util.List getEstadisticas(java.lang.String idPersona) throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.List _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = idPersona;
			}
	cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean beanRef = (cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean)container.preInvoke(this, 7, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getEstadisticas(idPersona);
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
				container.postInvoke(this, 7, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * valida
	 */
	public java.util.List valida(int idCotizPend, java.lang.String idPersona, java.lang.String oldRut, cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO cotizante) throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.List _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[4];
				_jacc_parms[0] = new java.lang.Integer(idCotizPend);
				_jacc_parms[1] = idPersona;
				_jacc_parms[2] = oldRut;
				_jacc_parms[3] = cotizante;
			}
	cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean beanRef = (cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean)container.preInvoke(this, 8, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.valida(idCotizPend, idPersona, oldRut, cotizante);
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
				container.postInvoke(this, 8, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * valida
	 */
	public java.util.List valida(int idCotizPend, java.lang.String idPersona, java.lang.String oldRut, cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO cotizante, int periodo_old) throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.List _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[5];
				_jacc_parms[0] = new java.lang.Integer(idCotizPend);
				_jacc_parms[1] = idPersona;
				_jacc_parms[2] = oldRut;
				_jacc_parms[3] = cotizante;
				_jacc_parms[4] = new java.lang.Integer(periodo_old);
			}
	cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean beanRef = (cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean)container.preInvoke(this, 9, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.valida(idCotizPend, idPersona, oldRut, cotizante, periodo_old);
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
				container.postInvoke(this, 9, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * limpiaCache
	 */
	public void limpiaCache(java.lang.String idPersona) throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = idPersona;
			}
	cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean beanRef = (cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean)container.preInvoke(this, 10, _EJS_s, _jacc_parms);
			beanRef.limpiaCache(idPersona);
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
				container.postInvoke(this, 10, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * limpiaCache
	 */
	public void limpiaCache(java.lang.String idPersona, java.lang.String region) throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = idPersona;
				_jacc_parms[1] = region;
			}
	cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean beanRef = (cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean)container.preInvoke(this, 11, _EJS_s, _jacc_parms);
			beanRef.limpiaCache(idPersona, region);
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
				container.postInvoke(this, 11, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * valida
	 */
	public void valida(java.lang.String idDescriptor, java.lang.String idPersona, java.util.Properties mapConceptos) throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[3];
				_jacc_parms[0] = idDescriptor;
				_jacc_parms[1] = idPersona;
				_jacc_parms[2] = mapConceptos;
			}
	cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean beanRef = (cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean)container.preInvoke(this, 12, _EJS_s, _jacc_parms);
			beanRef.valida(idDescriptor, idPersona, mapConceptos);
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
				container.postInvoke(this, 12, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
}
