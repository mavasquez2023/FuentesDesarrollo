package cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean;

import com.ibm.ejs.container.*;

/**
 * EJSRemoteStatelessServicesAutoconsulta_6348947c
 */
public class EJSRemoteStatelessServicesAutoconsulta_6348947c extends EJSWrapper implements ServicesAutoconsulta {
	/**
	 * EJSRemoteStatelessServicesAutoconsulta_6348947c
	 */
	public EJSRemoteStatelessServicesAutoconsulta_6348947c() throws java.rmi.RemoteException {
		super();	}
	/**
	 * usuarioPuedeConsultarPorAfiliado
	 */
	public boolean usuarioPuedeConsultarPorAfiliado(long rutEncargado, long rutEmpresa, cl.araucana.autoconsulta.vo.AfiliadoVO afiliado) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		boolean _EJS_result = false;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[3];
				_jacc_parms[0] = new java.lang.Long(rutEncargado);
				_jacc_parms[1] = new java.lang.Long(rutEmpresa);
				_jacc_parms[2] = afiliado;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 0, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.usuarioPuedeConsultarPorAfiliado(rutEncargado, rutEmpresa, afiliado);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
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
	 * getDatosEmpleado
	 */
	public cl.araucana.autoconsulta.vo.AfiliadoVO getDatosEmpleado(long rutAfiliado, long rutEmpresa) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		cl.araucana.autoconsulta.vo.AfiliadoVO _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = new java.lang.Long(rutAfiliado);
				_jacc_parms[1] = new java.lang.Long(rutEmpresa);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 1, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getDatosEmpleado(rutAfiliado, rutEmpresa);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
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
	 * getCartolaAhorro
	 */
	public cl.araucana.autoconsulta.vo.CartolaAhorroVO getCartolaAhorro(cl.araucana.autoconsulta.vo.UsuarioVO usrVo, java.lang.String cuentaAhorro) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		cl.araucana.autoconsulta.vo.CartolaAhorroVO _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = usrVo;
				_jacc_parms[1] = cuentaAhorro;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 2, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getCartolaAhorro(usrVo, cuentaAhorro);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
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
	 * getCartolaAhorro
	 */
	public cl.araucana.autoconsulta.vo.CartolaAhorroVO getCartolaAhorro(long rut, java.lang.String cuentaAhorro, java.lang.String dispositivo) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		cl.araucana.autoconsulta.vo.CartolaAhorroVO _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[3];
				_jacc_parms[0] = new java.lang.Long(rut);
				_jacc_parms[1] = cuentaAhorro;
				_jacc_parms[2] = dispositivo;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 3, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getCartolaAhorro(rut, cuentaAhorro, dispositivo);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
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
	 * getCertificadoAsignacionFamiliarByRut
	 */
	public cl.araucana.autoconsulta.vo.CertificadoAsignacionFamiliarVO getCertificadoAsignacionFamiliarByRut(long rutAfiliado, long rutEmpleador, int tipoConsulta, java.lang.String nombreCertificado, java.lang.String rutCertificado) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		cl.araucana.autoconsulta.vo.CertificadoAsignacionFamiliarVO _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[5];
				_jacc_parms[0] = new java.lang.Long(rutAfiliado);
				_jacc_parms[1] = new java.lang.Long(rutEmpleador);
				_jacc_parms[2] = new java.lang.Integer(tipoConsulta);
				_jacc_parms[3] = nombreCertificado;
				_jacc_parms[4] = rutCertificado;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 4, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getCertificadoAsignacionFamiliarByRut(rutAfiliado, rutEmpleador, tipoConsulta, nombreCertificado, rutCertificado);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
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
	 * getCertificadoDeudaVigenteByRut
	 */
	public cl.araucana.autoconsulta.vo.CertificadoDeudaVigenteVO getCertificadoDeudaVigenteByRut(cl.araucana.autoconsulta.vo.UsuarioVO usrVo, java.lang.String nombreConsulta, java.lang.String rutConsulta) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		cl.araucana.autoconsulta.vo.CertificadoDeudaVigenteVO _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[3];
				_jacc_parms[0] = usrVo;
				_jacc_parms[1] = nombreConsulta;
				_jacc_parms[2] = rutConsulta;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 5, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getCertificadoDeudaVigenteByRut(usrVo, nombreConsulta, rutConsulta);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
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
	 * getCertificadoDeudaVigenteByRut
	 */
	public cl.araucana.autoconsulta.vo.CertificadoDeudaVigenteVO getCertificadoDeudaVigenteByRut(long rut, java.lang.String dispositivo, java.lang.String nombreConsulta, java.lang.String rutConsulta) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		cl.araucana.autoconsulta.vo.CertificadoDeudaVigenteVO _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[4];
				_jacc_parms[0] = new java.lang.Long(rut);
				_jacc_parms[1] = dispositivo;
				_jacc_parms[2] = nombreConsulta;
				_jacc_parms[3] = rutConsulta;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 6, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getCertificadoDeudaVigenteByRut(rut, dispositivo, nombreConsulta, rutConsulta);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
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
	 * getCertificadoLicenciasMedicas
	 */
	public cl.araucana.autoconsulta.vo.CertificadoLicenciasMedicasVO getCertificadoLicenciasMedicas(cl.araucana.autoconsulta.vo.UsuarioVO usrVo, java.lang.String nombreConsulta, java.lang.String rutConsulta) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		cl.araucana.autoconsulta.vo.CertificadoLicenciasMedicasVO _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[3];
				_jacc_parms[0] = usrVo;
				_jacc_parms[1] = nombreConsulta;
				_jacc_parms[2] = rutConsulta;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 7, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getCertificadoLicenciasMedicas(usrVo, nombreConsulta, rutConsulta);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
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
	 * getCertificadoLicenciasMedicas
	 */
	public cl.araucana.autoconsulta.vo.CertificadoLicenciasMedicasVO getCertificadoLicenciasMedicas(long rut, java.lang.String dispositivo, java.lang.String nombreConsulta, java.lang.String rutConsulta) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		cl.araucana.autoconsulta.vo.CertificadoLicenciasMedicasVO _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[4];
				_jacc_parms[0] = new java.lang.Long(rut);
				_jacc_parms[1] = dispositivo;
				_jacc_parms[2] = nombreConsulta;
				_jacc_parms[3] = rutConsulta;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 8, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getCertificadoLicenciasMedicas(rut, dispositivo, nombreConsulta, rutConsulta);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
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
	 * getCuentasAhorroByRut
	 */
	public cl.araucana.autoconsulta.vo.CuentasAhorroRutVO getCuentasAhorroByRut(long rut) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		cl.araucana.autoconsulta.vo.CuentasAhorroRutVO _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = new java.lang.Long(rut);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 9, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getCuentasAhorroByRut(rut);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
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
	 * getDatosValidacionCertificado
	 */
	public cl.araucana.autoconsulta.vo.DatosValidacionVO getDatosValidacionCertificado(long id) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		cl.araucana.autoconsulta.vo.DatosValidacionVO _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = new java.lang.Long(id);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 10, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getDatosValidacionCertificado(id);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
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
		return _EJS_result;
	}
	/**
	 * getDeudaIntercaja
	 */
	public cl.araucana.autoconsulta.vo.DeudaIntercajaVO getDeudaIntercaja(long rutAfiliado) throws java.lang.Exception, cl.araucana.common.BusinessException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		cl.araucana.autoconsulta.vo.DeudaIntercajaVO _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = new java.lang.Long(rutAfiliado);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 11, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getDeudaIntercaja(rutAfiliado);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
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
		return _EJS_result;
	}
	/**
	 * getCliente
	 */
	public cl.araucana.autoconsulta.vo.EmpresaACargoVO getCliente(long rut) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		cl.araucana.autoconsulta.vo.EmpresaACargoVO _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = new java.lang.Long(rut);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 12, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getCliente(rut);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
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
		return _EJS_result;
	}
	/**
	 * getBitacoraLicenciaMedica
	 */
	public cl.araucana.autoconsulta.vo.LicenciaMedicaVO getBitacoraLicenciaMedica(long rutAfiliado, long nroLicencia, java.lang.String fechaHasta) throws java.rmi.RemoteException, java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		cl.araucana.autoconsulta.vo.LicenciaMedicaVO _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[3];
				_jacc_parms[0] = new java.lang.Long(rutAfiliado);
				_jacc_parms[1] = new java.lang.Long(nroLicencia);
				_jacc_parms[2] = fechaHasta;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 13, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getBitacoraLicenciaMedica(rutAfiliado, nroLicencia, fechaHasta);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 13, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getAutenticacion
	 */
	public cl.araucana.autoconsulta.vo.UsuarioVO getAutenticacion(long rut) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		cl.araucana.autoconsulta.vo.UsuarioVO _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = new java.lang.Long(rut);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 14, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getAutenticacion(rut);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 14, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getAutenticacion
	 */
	public cl.araucana.autoconsulta.vo.UsuarioVO getAutenticacion(long rut, int password) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		cl.araucana.autoconsulta.vo.UsuarioVO _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = new java.lang.Long(rut);
				_jacc_parms[1] = new java.lang.Integer(password);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 15, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getAutenticacion(rut, password);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 15, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getDatosUsuario
	 */
	public cl.araucana.autoconsulta.vo.UsuarioVO getDatosUsuario(long rutUsuario) throws java.lang.Exception, cl.araucana.common.BusinessException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		cl.araucana.autoconsulta.vo.UsuarioVO _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = new java.lang.Long(rutUsuario);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 16, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getDatosUsuario(rutUsuario);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 16, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getTieneCargasByRut
	 */
	public int getTieneCargasByRut(long rutAfiliado, long rutEmpleador) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		int _EJS_result = 0;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = new java.lang.Long(rutAfiliado);
				_jacc_parms[1] = new java.lang.Long(rutEmpleador);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 17, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getTieneCargasByRut(rutAfiliado, rutEmpleador);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 17, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * datosComplClienteSimulacion
	 */
	public java.util.Collection datosComplClienteSimulacion(long rutCliente) throws java.lang.Exception, java.sql.SQLException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = new java.lang.Long(rutCliente);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 18, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.datosComplClienteSimulacion(rutCliente);
		}
		catch (java.sql.SQLException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 18, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * datosEmpresaAfiliadoSimulacion
	 */
	public java.util.Collection datosEmpresaAfiliadoSimulacion(long rutCliente) throws java.lang.Exception, java.sql.SQLException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = new java.lang.Long(rutCliente);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 19, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.datosEmpresaAfiliadoSimulacion(rutCliente);
		}
		catch (java.sql.SQLException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 19, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getChequesEmpresa
	 */
	public java.util.Collection getChequesEmpresa(cl.araucana.autoconsulta.vo.UsuarioVO usrVo) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = usrVo;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 20, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getChequesEmpresa(usrVo);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 20, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getChequesEmpresa
	 */
	public java.util.Collection getChequesEmpresa(long rut, java.lang.String dispositivo) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = new java.lang.Long(rut);
				_jacc_parms[1] = dispositivo;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 21, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getChequesEmpresa(rut, dispositivo);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 21, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getConsultaLicenciasMedicas
	 */
	public java.util.Collection getConsultaLicenciasMedicas(cl.araucana.autoconsulta.vo.UsuarioVO usrVo) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = usrVo;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 22, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getConsultaLicenciasMedicas(usrVo);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 22, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getConsultaLicenciasMedicas
	 */
	public java.util.Collection getConsultaLicenciasMedicas(long rut, java.lang.String dispositivo) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = new java.lang.Long(rut);
				_jacc_parms[1] = dispositivo;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 23, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getConsultaLicenciasMedicas(rut, dispositivo);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 23, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getCreditosByRut
	 */
	public java.util.Collection getCreditosByRut(cl.araucana.autoconsulta.vo.UsuarioVO usrVo) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = usrVo;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 24, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getCreditosByRut(usrVo);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 24, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getCreditosByRut
	 */
	public java.util.Collection getCreditosByRut(long rut, java.lang.String dispositivo) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = new java.lang.Long(rut);
				_jacc_parms[1] = dispositivo;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 25, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getCreditosByRut(rut, dispositivo);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 25, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getDatosEmpresa
	 */
	public java.util.Collection getDatosEmpresa(long rutEmpresa) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = new java.lang.Long(rutEmpresa);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 26, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getDatosEmpresa(rutEmpresa);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 26, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getDatosEmpresaPublica
	 */
	public java.util.Collection getDatosEmpresaPublica(long rutEmpresaPublica) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = new java.lang.Long(rutEmpresaPublica);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 27, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getDatosEmpresaPublica(rutEmpresaPublica);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 27, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getDatosTrabajadorLiquidaciones
	 */
	public java.util.Collection getDatosTrabajadorLiquidaciones(long rut) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = new java.lang.Long(rut);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 28, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getDatosTrabajadorLiquidaciones(rut);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 28, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getEmpleadoresByEmpleado
	 */
	public java.util.Collection getEmpleadoresByEmpleado(cl.araucana.autoconsulta.vo.UsuarioVO usrVo) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = usrVo;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 29, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getEmpleadoresByEmpleado(usrVo);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 29, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getEmpleadoresByEmpleado
	 */
	public java.util.Collection getEmpleadoresByEmpleado(long rut, java.lang.String dispositivo) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = new java.lang.Long(rut);
				_jacc_parms[1] = dispositivo;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 30, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getEmpleadoresByEmpleado(rut, dispositivo);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 30, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getEmpresaACargo
	 */
	public java.util.Collection getEmpresaACargo(long rut) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = new java.lang.Long(rut);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 31, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getEmpresaACargo(rut);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 31, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getEncargados
	 */
	public java.util.Collection getEncargados(long rut) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = new java.lang.Long(rut);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 32, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getEncargados(rut);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 32, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getLiquidacionReembolsosByRut
	 */
	public java.util.Collection getLiquidacionReembolsosByRut(cl.araucana.autoconsulta.vo.UsuarioVO usrVo) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = usrVo;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 33, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getLiquidacionReembolsosByRut(usrVo);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 33, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getLiquidacionReembolsosByRut
	 */
	public java.util.Collection getLiquidacionReembolsosByRut(long rut) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = new java.lang.Long(rut);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 34, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getLiquidacionReembolsosByRut(rut);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 34, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getListaInstitucionesPrevicionales
	 */
	public java.util.Collection getListaInstitucionesPrevicionales() throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[0];
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 35, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getListaInstitucionesPrevicionales();
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 35, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getListaObservaciones
	 */
	public java.util.Collection getListaObservaciones() throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[0];
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 36, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getListaObservaciones();
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 36, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getListaOficinasPago
	 */
	public java.util.Collection getListaOficinasPago() throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[0];
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 37, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getListaOficinasPago();
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 37, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getListaOficinasSucursalesByEmpresaEncargado
	 */
	public java.util.Collection getListaOficinasSucursalesByEmpresaEncargado(long rutEncargado, long rutEmpresa) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = new java.lang.Long(rutEncargado);
				_jacc_parms[1] = new java.lang.Long(rutEmpresa);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 38, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getListaOficinasSucursalesByEmpresaEncargado(rutEncargado, rutEmpresa);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 38, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getMovimientosLiquidacion
	 */
	public java.util.Collection getMovimientosLiquidacion(cl.araucana.autoconsulta.vo.UsuarioVO usrVo, java.lang.String nroliq) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = usrVo;
				_jacc_parms[1] = nroliq;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 39, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getMovimientosLiquidacion(usrVo, nroliq);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 39, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getMovimientosLiquidacion
	 */
	public java.util.Collection getMovimientosLiquidacion(long rut, java.lang.String nroliq) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = new java.lang.Long(rut);
				_jacc_parms[1] = nroliq;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 40, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getMovimientosLiquidacion(rut, nroliq);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 40, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getNumeroConvenio
	 */
	public java.util.Collection getNumeroConvenio(long rut) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = new java.lang.Long(rut);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 41, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getNumeroConvenio(rut);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 41, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getPublicidad
	 */
	public java.util.Collection getPublicidad() throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[0];
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 42, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getPublicidad();
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 42, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getResumenMensualByEmpresa
	 */
	public java.util.Collection getResumenMensualByEmpresa(long rutEmp, long nroConvenio, java.lang.String periodo) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[3];
				_jacc_parms[0] = new java.lang.Long(rutEmp);
				_jacc_parms[1] = new java.lang.Long(nroConvenio);
				_jacc_parms[2] = periodo;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 43, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getResumenMensualByEmpresa(rutEmp, nroConvenio, periodo);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 43, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getTip
	 */
	public java.util.Collection getTip(long agno, long mes, int tipFija, int tipValor) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[4];
				_jacc_parms[0] = new java.lang.Long(agno);
				_jacc_parms[1] = new java.lang.Long(mes);
				_jacc_parms[2] = new java.lang.Integer(tipFija);
				_jacc_parms[3] = new java.lang.Integer(tipValor);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 44, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getTip(agno, mes, tipFija, tipValor);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 44, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getUltimaEmpresaHistorica
	 */
	public java.util.Collection getUltimaEmpresaHistorica(long rut) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = new java.lang.Long(rut);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 45, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getUltimaEmpresaHistorica(rut);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 45, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * getUsuarios
	 */
	public java.util.Collection getUsuarios() throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[0];
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 46, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getUsuarios();
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 46, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * listaObservacionesCompin
	 */
	public java.util.Collection listaObservacionesCompin(long rutAfiliado, long numLicencia) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = new java.lang.Long(rutAfiliado);
				_jacc_parms[1] = new java.lang.Long(numLicencia);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 47, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.listaObservacionesCompin(rutAfiliado, numLicencia);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 47, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * periodosPrestCompl
	 */
	public java.util.Collection periodosPrestCompl(long rutEmpresa) throws java.lang.Exception, cl.araucana.common.BusinessException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = new java.lang.Long(rutEmpresa);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 48, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.periodosPrestCompl(rutEmpresa);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 48, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * sexoAfiliadoBaseComun
	 */
	public java.util.Collection sexoAfiliadoBaseComun(long rutAfiliado) throws java.lang.Exception, cl.araucana.common.BusinessException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.util.Collection _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = new java.lang.Long(rutAfiliado);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 49, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.sexoAfiliadoBaseComun(rutAfiliado);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 49, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * montoCreditoPreaprobado
	 */
	public long montoCreditoPreaprobado(long rutCliente) throws java.lang.Exception, java.sql.SQLException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		long _EJS_result = 0;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = new java.lang.Long(rutCliente);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 50, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.montoCreditoPreaprobado(rutCliente);
		}
		catch (java.sql.SQLException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 50, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * bloquearClave
	 */
	public void bloquearClave(long rut) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = new java.lang.Long(rut);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 51, _EJS_s, _jacc_parms);
			beanRef.bloquearClave(rut);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 51, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * deleteEncargado
	 */
	public void deleteEncargado(cl.araucana.autoconsulta.vo.EmpresaACargoVO vo, java.lang.String usuarioModificador) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = vo;
				_jacc_parms[1] = usuarioModificador;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 52, _EJS_s, _jacc_parms);
			beanRef.deleteEncargado(vo, usuarioModificador);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 52, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * insertarActividad
	 */
	public void insertarActividad(cl.araucana.autoconsulta.vo.UsuarioVO usrVo, int funcion) throws java.lang.Exception, java.sql.SQLException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = usrVo;
				_jacc_parms[1] = new java.lang.Integer(funcion);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 53, _EJS_s, _jacc_parms);
			beanRef.insertarActividad(usrVo, funcion);
		}
		catch (java.sql.SQLException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 53, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * modificarClavePersonal
	 */
	public void modificarClavePersonal(long rut, int password) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = new java.lang.Long(rut);
				_jacc_parms[1] = new java.lang.Integer(password);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 54, _EJS_s, _jacc_parms);
			beanRef.modificarClavePersonal(rut, password);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 54, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * putPublicidad
	 */
	public void putPublicidad(java.lang.String texto) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = texto;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 55, _EJS_s, _jacc_parms);
			beanRef.putPublicidad(texto);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 55, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * registrarEncargado
	 */
	public void registrarEncargado(cl.araucana.autoconsulta.vo.EmpresaACargoVO empresaACargoVO, java.lang.String[] indiceOficinasSeleccionadas, java.lang.String usuarioModificador) throws java.lang.Exception, cl.araucana.common.BusinessException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[3];
				_jacc_parms[0] = empresaACargoVO;
				_jacc_parms[1] = indiceOficinasSeleccionadas;
				_jacc_parms[2] = usuarioModificador;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 56, _EJS_s, _jacc_parms);
			beanRef.registrarEncargado(empresaACargoVO, indiceOficinasSeleccionadas, usuarioModificador);
		}
		catch (cl.araucana.common.BusinessException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 56, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * setRegistroSimulacionCredito
	 */
	public void setRegistroSimulacionCredito(long rutCliente, java.lang.String dvCliente, long remuneracionLiq, long montoSolicitado, int numeroCuotas, java.util.Date fechaNac, java.util.Date fechaIngEmp, boolean seguroDegravamen, boolean seguroVida, boolean seguroCesantia, long montoPrepactado, java.lang.String nombresCliente, long telefono, java.lang.String correoElectronico, long rutEmpresa, java.lang.String nombreEmpresa) throws java.lang.Exception, java.sql.SQLException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[16];
				_jacc_parms[0] = new java.lang.Long(rutCliente);
				_jacc_parms[1] = dvCliente;
				_jacc_parms[2] = new java.lang.Long(remuneracionLiq);
				_jacc_parms[3] = new java.lang.Long(montoSolicitado);
				_jacc_parms[4] = new java.lang.Integer(numeroCuotas);
				_jacc_parms[5] = fechaNac;
				_jacc_parms[6] = fechaIngEmp;
				_jacc_parms[7] = new java.lang.Boolean(seguroDegravamen);
				_jacc_parms[8] = new java.lang.Boolean(seguroVida);
				_jacc_parms[9] = new java.lang.Boolean(seguroCesantia);
				_jacc_parms[10] = new java.lang.Long(montoPrepactado);
				_jacc_parms[11] = nombresCliente;
				_jacc_parms[12] = new java.lang.Long(telefono);
				_jacc_parms[13] = correoElectronico;
				_jacc_parms[14] = new java.lang.Long(rutEmpresa);
				_jacc_parms[15] = nombreEmpresa;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 57, _EJS_s, _jacc_parms);
			beanRef.setRegistroSimulacionCredito(rutCliente, dvCliente, remuneracionLiq, montoSolicitado, numeroCuotas, fechaNac, fechaIngEmp, seguroDegravamen, seguroVida, seguroCesantia, montoPrepactado, nombresCliente, telefono, correoElectronico, rutEmpresa, nombreEmpresa);
		}
		catch (java.sql.SQLException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 57, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * setSimulacionEstadistica
	 */
	public void setSimulacionEstadistica(cl.araucana.autoconsulta.vo.UsuarioVO usrVo) throws java.lang.Exception, java.sql.SQLException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = usrVo;
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 58, _EJS_s, _jacc_parms);
			beanRef.setSimulacionEstadistica(usrVo);
		}
		catch (java.sql.SQLException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 58, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * setSimulacionEstadistica
	 */
	public void setSimulacionEstadistica(java.lang.String ipSimulacion, int funcion) throws java.lang.Exception, java.sql.SQLException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = ipSimulacion;
				_jacc_parms[1] = new java.lang.Integer(funcion);
			}
	cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean beanRef = (cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaBean)container.preInvoke(this, 59, _EJS_s, _jacc_parms);
			beanRef.setSimulacionEstadistica(ipSimulacion, funcion);
		}
		catch (java.sql.SQLException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 59, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
}
