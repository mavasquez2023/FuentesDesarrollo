package cl.araucana.aporte.orqOutput.ejb;

import com.ibm.ejs.container.*;

/**
 * EJSRemoteStatelessOrqOutputBean_66e4193e
 */
public class EJSRemoteStatelessOrqOutputBean_66e4193e extends EJSWrapper implements OrqOutputRemote {
	/**
	 * EJSRemoteStatelessOrqOutputBean_66e4193e
	 */
	public EJSRemoteStatelessOrqOutputBean_66e4193e() throws java.rmi.RemoteException {
		super();	}
	/**
	 * recuperacionPago
	 */
	public cl.araucana.aporte.orqOutput.bo.OrqOutputResultBO recuperacionPago(int rut, int montoCredito, int montoLeasing, int montoAporte, int periodoAporte, java.lang.String fechaRecaudacion, java.lang.String horaRecaudacion, java.lang.String usuario, int documentoPago) throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		cl.araucana.aporte.orqOutput.bo.OrqOutputResultBO _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[9];
				_jacc_parms[0] = new java.lang.Integer(rut);
				_jacc_parms[1] = new java.lang.Integer(montoCredito);
				_jacc_parms[2] = new java.lang.Integer(montoLeasing);
				_jacc_parms[3] = new java.lang.Integer(montoAporte);
				_jacc_parms[4] = new java.lang.Integer(periodoAporte);
				_jacc_parms[5] = fechaRecaudacion;
				_jacc_parms[6] = horaRecaudacion;
				_jacc_parms[7] = usuario;
				_jacc_parms[8] = new java.lang.Integer(documentoPago);
			}
	cl.araucana.aporte.orqOutput.ejb.OrqOutpuBean beanRef = (cl.araucana.aporte.orqOutput.ejb.OrqOutpuBean)container.preInvoke(this, 0, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.recuperacionPago(rut, montoCredito, montoLeasing, montoAporte, periodoAporte, fechaRecaudacion, horaRecaudacion, usuario, documentoPago);
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
