package cl.araucana.leasing.serv;

import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;

import javax.naming.InitialContext;

import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;
import cl.araucana.leasing.serv.ServicesLeasingSLBean.ServicesLeasing;
import cl.araucana.leasing.serv.ServicesLeasingSLBean.ServicesLeasingHome;
import cl.araucana.leasing.vo.CuentaAhorroVO;
import cl.araucana.leasing.vo.UFVO;

import com.schema.patterns.BusinessDelegate;
import com.schema.util.FileSettings;
import com.schema.util.GeneralException;

/**
 * @author asepulveda
 * Business Delegate para Servicios de Creditos
 */
public class ServicesLeasingDelegate extends BusinessDelegate {
	// Home y Remote del EJB
	private final static Class homeClass=ServicesLeasingHome.class;
	private ServicesLeasing services=null;
	
	/**
	 * Constructor del BDelegate. Establece la conexion con el EJB
	 * @throws GeneralException
	 */
	public ServicesLeasingDelegate() throws GeneralException {
		// Nombre JNDI del EJB
		String jndi = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
		              "/application-settings/ejbs/leasing/leasing-services");
		              
		try {
			InitialContext ic=new InitialContext();
			services = (ServicesLeasing)super.getServiceBean(ic, jndi,homeClass);
		} catch (Exception e) {
			throw new GeneralException(this,e);
		}
	}
	
	/** 
	 * Obtiene información de las cuentas de ahorro que tiene el rut consultado
	 * @param long rut
	 * @return Collection 
	 */
	public Collection getListaCuentaAhorroByRut(long rut) throws Exception, BusinessException,GeneralException{
		return services.getListaCuentaAhorroByRut(rut);
		
	}
	
	/** 
	 * Obtiene el detalle de una cartola de ahorro
	 * @param CuentaAhorroVO cuenta
	 * @return Collection DetalleMovimientosCuentaAhorroVO
	 */
	public Collection getDetalleCuentaAhorroByCuentaAhorro(CuentaAhorroVO cuenta) throws Exception, BusinessException, GeneralException {
		return services.getDetalleCuentaAhorroByCuentaAhorro(cuenta);
	}

	public float getValorUF(Date fechaCalculo) throws Exception{
		Collection col = services.getValorUF(fechaCalculo);
		Iterator iter = col.iterator();
		
		float retorno = 0;
		
		while ( iter.hasNext() ){
			UFVO uf = (UFVO) iter.next();
			retorno = uf.getValor();
		}
		
		return retorno;
	}
}
