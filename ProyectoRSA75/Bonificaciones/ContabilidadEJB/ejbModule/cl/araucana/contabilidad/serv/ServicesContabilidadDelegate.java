package cl.araucana.contabilidad.serv;

import javax.naming.InitialContext;

import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;
import cl.araucana.contabilidad.model.Asiento;
import cl.araucana.contabilidad.serv.ServicesContabilidadSLBean.ServicesContabilidad;
import cl.araucana.contabilidad.serv.ServicesContabilidadSLBean.ServicesContabilidadHome;

import com.schema.patterns.BusinessDelegate;
import com.schema.util.FileSettings;
import com.schema.util.GeneralException;


/**
 * @author asepulveda
 * Business Delegate para Servicios de Contabilidad
 */
public class ServicesContabilidadDelegate extends BusinessDelegate {
	// Home y Remote del EJB
	private final static Class homeClass=ServicesContabilidadHome.class;
	private ServicesContabilidad services=null;
	
	/**
	 * Constructor del BDelegate. Establece la conexion con el EJB
	 * @throws GeneralException
	 */
	public ServicesContabilidadDelegate() throws GeneralException {
		// Nombre JNDI del EJB
		String jndi = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
		              "/application-settings/ejbs/contabilidad/contabilidad-services");
		              
		try {
			InitialContext ic=new InitialContext();
			services = (ServicesContabilidad)super.getServiceBean(ic, jndi,homeClass);
		} catch (Exception e) {
			throw new GeneralException(this,e);
		}
	}
	
	/**
	 * Crea un Asiento contable en el esquema de Bienestar
	 * con sus líneas de detalle
	 * @param asiento
	 * @param lineas
	 * @throws Exception
	 * @throws BusinessException
	 */
	public long creaAsientoContableBienestar(Asiento asiento) throws Exception, BusinessException {
		return services.creaAsientoContableBienestar(asiento);
	}
		
}
