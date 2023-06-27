package cl.araucana.bienestar.bonificaciones.serv;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import cl.araucana.bienestar.bonificaciones.common.Constants;
import cl.araucana.bienestar.bonificaciones.model.Cobertura;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCoberturasSLBean.ServicesCoberturas;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCoberturasSLBean.ServicesCoberturasHome;
import cl.araucana.bienestar.bonificaciones.vo.AgrupacionCobertura;
import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;

import com.schema.patterns.BusinessDelegate;
import com.schema.util.FileSettings;
import com.schema.util.GeneralException;

/**
 * @author asepulveda
 * Business Delegate para Servicios de Coberturas de Bienestar
 */
public class ServicesCoberturasDelegate extends BusinessDelegate {
	// Home y Remote del EJB
	private final static Class homeClass=ServicesCoberturasHome.class;
	private ServicesCoberturas services=null;
	
	Logger logger = Logger.getLogger(ServicesCoberturasDelegate.class);
	
	/**
	 * Constructor del BDelegate. Establece la conexion con el EJB
	 * @throws GeneralException
	 */
	public ServicesCoberturasDelegate() throws GeneralException {
		// Nombre JNDI del EJB
		String jndi = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
		              "/application-settings/ejbs/bonificaciones/coberturas-services");
		              
		try {
			InitialContext ic=new InitialContext();
			services = (ServicesCoberturas)super.getServiceBean(ic, jndi,homeClass);
		} catch (Exception e) {
			throw new GeneralException(this,e);
		}
	}
	
	
	
	/** 
	 * Obtiene la lista de Coberturas (Bonificaciones) de tipo adicional existentes
	 * @return ArrayList de Cobertura
	 * @throws Exception
	 */
	public ArrayList getListaCoberturasAdicionales() throws RemoteException, Exception, BusinessException {
		return services.getListaCoberturasAdicionales();
	}
	
	/** 
	 * Obtiene la lista de Coberturas (Bonificaciones) de tipo especial existentes
	 * @return ArrayList de Cobertura
	 * @throws Exception
	 */
	public ArrayList getListaCoberturasEspeciales() throws RemoteException, Exception, BusinessException {
		return services.getListaCoberturasEspeciales();
	}

	
	
	/** 
	 * Obtiene la lista de Coberturas (Bonificaciones) de tipo DIRECTA existentes (REQ 5088)
	 * @return ArrayList de Cobertura
	 * @throws Exception
	 */
	public ArrayList getListaCoberturasDirectas() throws RemoteException, Exception, BusinessException {
		return services.getListaCoberturasDirectas();
	}
	/** 
	 * Obtiene la lista de Coberturas (Bonificaciones) existentes
	 * @param Cobertura
	 * @return ArrayList de Cobertura
	 * @throws Exception
	 */
	public ArrayList getCoberturas(Cobertura cobertura) throws RemoteException, Exception, BusinessException {
		return services.getCoberturas(cobertura);
	}
	
	/** 
	 * Obtiene la lista de Coberturas que no esten en la lista de productos de un convenio
	 * @param codigo Convenio 
	 * @param Cobertura
	 * @return ArrayList de Cobertura
	 * @throws Exception
	 */
	public ArrayList getComplementoProductos(long codigoConvenio) throws RemoteException, Exception, BusinessException {
		return services.getComplementoProductos(codigoConvenio);
	}
	
	/** 
	 * Obtiene una Cobertura (Bonificacion) existente
	 * @param codigo de cobertura
	 * @return Cobertura
	 * @throws Exception
	 */
	public Cobertura getCobertura(long codigoCobertura) throws RemoteException, Exception, BusinessException {
		return services.getCobertura(codigoCobertura);
	}
	
	/**
	 * Crea una nueva cobertura en Bienestar
	 * @param Cobertura
	 */
	public void registraCobertura(Cobertura cobertura) throws RemoteException, BusinessException,Exception {
		services.registraCobertura(cobertura);
	}
	
	/**
	 * Actualiza una cobertura en Bienestar
	 * @param Cobertura
	 */
	public void actualizaCobertura(Cobertura cobertura) throws RemoteException, BusinessException,Exception {
		services.actualizaCobertura(cobertura);
	}
	
	/**
	 * Activa una nueva cobertura en Bienestar
	 * Valida que la cobertura tenga rangos definidos para poder activarla
	 * @param Cobertura
	 */
	public void activaCobertura(Cobertura cobertura) throws RemoteException, Exception, BusinessException{
		services.activaCobertura(cobertura);
	}
		
	/**
	 * Elimina una cobertura en Bienestar
	 * @param Cobertura
	 */
	public void eliminaCobertura(Cobertura cobertura) throws RemoteException, BusinessException, Exception{
		services.eliminaCobertura(cobertura);
	}
	
	/**
	 * Actualiza los rangos Vigentes de una cobertura
	 * @param Cobertura
	 */
	public void actualizaRangosVigentesCobertura(Cobertura cobertura) throws RemoteException, BusinessException, Exception {
		services.actualizaRangosCobertura(cobertura, Constants.RANGO_VIGENTE);
	}
	
	/**
	 * Actualiza los rangos Futuros de una cobertura 
	 * @param Cobertura
	 */
	public void actualizaRangosFuturosCobertura(Cobertura cobertura) throws RemoteException, BusinessException, Exception {
		services.actualizaRangosCobertura(cobertura, Constants.RANGO_FUTURO);
	}
	
	/** 
	 * Obtiene todos los Rangos de una Cobertura (Bonificacion) existentes
	 * Obtiene el rango vigente si existe, los rangos historicos y el rango futuro si existe
	 * @param Cobertura
	 * @return Cobertura con los rangos
	 * @throws Exception
	 */
	public Cobertura getAllRangosCobertura(Cobertura cobertura) throws RemoteException, Exception, BusinessException {
		return services.getAllRangosCobertura(cobertura);
	}
	
	/** 
	 * Obtiene el Rango que se encuentra vigente en la fecha pasada en los parámetros
	 * @param Cobertura
	 * @param Date fecha
	 * @return Cobertura con VigenciaRango rangoVigente si tiene y null si no tiene
	 * @throws Exception
	 */
	public Cobertura getRangoCoberturaVigenteByFecha(Cobertura cobertura, Date fecha) throws RemoteException, Exception, BusinessException {
		return services.getRangoCoberturaVigenteByFecha(cobertura, fecha);
	}
	
	/** 
	 * Busca si la cobertura pasada como parámetro
	 * tiene la definición de rangos en otra cobertura,
	 * en caso de ser así, significa que ambas coberturas
	 * utilizan los mismos aportes de bienestar. Si n o tiene devuelve cero
	 * @param long codigoCobertura 
	 * @return AgrupacionCobertura
	 * 	!= null si existe la relación
	 *  = null si no existe la relación
	 * @throws Exception
	 */
	public AgrupacionCobertura getRelacionAgrupacionCobertura(long codigoCobertura) throws RemoteException, Exception, BusinessException {
		return services.getRelacionAgrupacionCobertura(codigoCobertura);
	}
	
	/** 
	 * Busca todas la relaciones que posee una cobertura maestra
	 * @param long codigoCoberturaMaestra 
	 * @return ArrayList de AgrupacionCobertura
	 * @throws Exception
	 */
	public ArrayList getRelacionAgrupacionCoberturaByCoberturaMaestra(long codigoCobertura) throws RemoteException, Exception, BusinessException {
		return services.getRelacionAgrupacionCoberturaByCoberturaMaestra(codigoCobertura);	
	}
	
	/** 
	 * Busca todas la relaciones que posee una cobertura maestra y devuelve
	 * un ArrayList de Cobertura
	 * @param long codigoCoberturaMaestra 
	 * @return ArrayList de Cobertura
	 * @throws Exception
	 */
	public ArrayList getCoberturasByCoberturaMaestra(long codigoCobertura) throws Exception, BusinessException {
		return services.getCoberturasByCoberturaMaestra(codigoCobertura);	
	}	
			
}
