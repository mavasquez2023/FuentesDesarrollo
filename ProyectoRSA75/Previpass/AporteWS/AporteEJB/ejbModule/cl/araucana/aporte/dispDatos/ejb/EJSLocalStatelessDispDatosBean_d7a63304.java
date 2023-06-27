package cl.araucana.aporte.dispDatos.ejb;

import com.ibm.ejs.container.*;
import java.rmi.RemoteException;

/**
 * EJSLocalStatelessDispDatosBean_d7a63304
 */
public class EJSLocalStatelessDispDatosBean_d7a63304 extends EJSLocalWrapper implements cl.araucana.aporte.dispDatos.ejb.DispDatosLocal {
	/**
	 * EJSLocalStatelessDispDatosBean_d7a63304
	 */
	public EJSLocalStatelessDispDatosBean_d7a63304() {
		super();	}
	/**
	 * obtenerInfoDatos
	 */
	public cl.araucana.aporte.dispDatos.bo.DispDatosResultBO obtenerInfoDatos(int rut) {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		cl.araucana.aporte.dispDatos.bo.DispDatosResultBO _EJS_result = null;
		try {
			if ( container.doesJaccNeedsEJBArguments(this) )
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = new java.lang.Integer(rut);
			}
	cl.araucana.aporte.dispDatos.ejb.DispDatoBean beanRef = (cl.araucana.aporte.dispDatos.ejb.DispDatoBean)container.preInvoke(this, 0, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.obtenerInfoDatos(rut);
		}
		catch (java.rmi.RemoteException ex) {
		 	_EJS_s.setUncheckedLocalException(ex);
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedLocalException(ex);
		}

		finally {
			try {
				container.postInvoke(this, 0, _EJS_s);
			} catch ( RemoteException ex ) {
				_EJS_s.setUncheckedLocalException(ex);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
}
