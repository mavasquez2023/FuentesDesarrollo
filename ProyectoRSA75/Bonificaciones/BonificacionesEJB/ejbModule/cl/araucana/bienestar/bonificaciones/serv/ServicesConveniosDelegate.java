package cl.araucana.bienestar.bonificaciones.serv;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.model.Producto;
import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.model.Talonario;
import cl.araucana.bienestar.bonificaciones.model.Vale;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosSLBean.ServicesConvenios;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosSLBean.ServicesConveniosHome;
import cl.araucana.bienestar.bonificaciones.vo.TalonarioVO;
import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;

import com.schema.patterns.BusinessDelegate;
import com.schema.util.FileSettings;
import com.schema.util.GeneralException;

/**
 * @author asepulveda
 * Business Delegate para Servicios de Socios de Bienestar
 */
public class ServicesConveniosDelegate extends BusinessDelegate {
	// Home y Remote del EJB
	private final static Class homeClass=ServicesConveniosHome.class;
	private ServicesConvenios services=null;
	
	Logger logger = Logger.getLogger(ServicesConveniosDelegate.class);
	
	/**
	 * Constructor del BDelegate. Establece la conexion con el EJB
	 * @throws GeneralException
	 */
	public ServicesConveniosDelegate() throws GeneralException {
		// Nombre JNDI del EJB
		String jndi = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
		              "/application-settings/ejbs/bonificaciones/convenios-services");
		              
		try {
			InitialContext ic=new InitialContext();
			services = (ServicesConvenios)super.getServiceBean(ic, jndi,homeClass);
		} catch (Exception e) {
			throw new GeneralException(this,e);
		}
	}

	/** 
	 * Obtiene la lista de Convenios existentes
	 * @param Convenio
	 * @return ArrayList de Convenio
	 * @throws Exception
	 */
	public ArrayList getConvenios(Convenio convenio) throws RemoteException, Exception, BusinessException {
		return services.getConvenios(convenio);
	}
	
	/**
	 * Obtiene la lista de Convenios relacionados con una cobertura
	 * @param codigo de Cobertura
	 * @return ArrayList de Convenio
	 * @throws Exception
	 */
	public ArrayList getConvenios(long codigoCobertura) throws RemoteException, Exception, BusinessException {
		return services.getConvenios(codigoCobertura);
	}
	
	/** 
	 * Obtiene un Convenio existente
	 * @param codigo de Convenio
	 * @return Convenio
	 * @throws Exception
	 */
	public Convenio getConvenio(long codigoConvenio) throws RemoteException, Exception, BusinessException {
		return services.getConvenio(codigoConvenio);
	}
		
	/** 
	 * Obtiene la lista de Talonarios existentes
	 * @param codigo convenio
	 * @return ArrayList de Talonario
	 * @throws Exception
	 */
	public Convenio getTalonarios(Convenio convenio, Talonario talonarioFiltro) throws RemoteException, Exception, BusinessException {
		return services.getTalonarios(convenio, talonarioFiltro);
	}
	
	/**
	 * Registra un nuevo Talonario asociado a un Convenio en Bienestar
	 * @param convenio: el Objeto Convenio y talonario: el Objeto Talonario
	 */
	public void registraTalonario(Convenio convenio, Talonario talonario) throws RemoteException, BusinessException, Exception {
		services.registraTalonario(convenio, talonario);
	}
	
	/**
	 * Actualiza la informacion de un talonario
	 * @param convenio: el Objeto Convenio y talonario: el Objeto Talonario
	 */
	public void actualizaTalonario(Convenio convenio, Talonario talonario) throws RemoteException, BusinessException, Exception {
		services.actualizaTalonario(convenio,talonario);
	}
	
	/**
	 * Cambia el estado de un talonario a Eliminado
	 * @param convenio: el Objeto Convenio y talonario: el Objeto Talonario
	 */
	public void eliminaTalonario(Convenio convenio, Talonario talonario) throws RemoteException, BusinessException, Exception {
		services.eliminaTalonario(convenio, talonario);
	}
		
	/**
	 * Crea un nuevo Convenio en Bienestar
	 * @param convenio: el Objeto Convenio
	 */
	public void registraConvenio(Convenio convenio) throws RemoteException, BusinessException,Exception {
		services.registraConvenio(convenio);
	}
	
	/**
	 * Modifica un Convenio en Bienestar
	 * @param convenio: el Objeto Convenio
	 */
	public void actualizaConvenio(Convenio convenio) throws RemoteException, BusinessException, Exception {
		services.actualizaConvenio(convenio);
	}
	
	/**
	 * Activa un Convenio en Bienestar
	 * @param convenio: el Objeto Convenio
	 */
	public void activaConvenio(Convenio convenio) throws RemoteException, BusinessException,Exception {
		services.activaConvenio(convenio);
	}
	
	/**
	 * Desactiva un Convenio en Bienestar
	 * @param convenio: el Objeto Convenio
	 */
	public void desactivaConvenio(Convenio convenio) throws RemoteException, BusinessException,Exception {
		services.desactivaConvenio(convenio);
	}
	
	/** 
	 * Obtiene la lista de Productos asociados a un convenio
	 * @param producto Producto
	 * @return ArrayList de Producto
	 * @throws Exception
	 */
	public Convenio getProductos(Convenio convenio,Producto productoFiltro) throws RemoteException, Exception, BusinessException {
		return services.getProductos(0, convenio,productoFiltro);
	}
	
	/** 
	 * Obtiene la lista de Productos activos asociados a un convenio
	 * @param long codigoConvenio,Producto productoFiltro
	 * @return ArrayList de Producto
	 * @throws Exception
	 */
	public ArrayList getProductosByConvenio(long codigoConvenio,Producto productoFiltro)  throws RemoteException, Exception, BusinessException {
		return services.getProductosByConvenio(codigoConvenio,productoFiltro);
	}
	
	/** 
	 * Obtiene la lista de Productos asociados a un convenio (de un caso)
	 * @param producto Producto
	 * @return ArrayList de Producto
	 * @throws Exception
	 */
	public Convenio getProductosCaso(long casoId,Convenio convenio,Producto productoFiltro) throws RemoteException, Exception, BusinessException {
		return services.getProductos(casoId, convenio,productoFiltro);
	}
	
	/** 
	 * Obtiene un Producto asociado a un convenio
	 * @param codigo de producto y codigo de convenio
	 * @return producto
	 * @throws Exception
	 */
	public Producto getProducto(long codProducto, long codConvenio) throws RemoteException, Exception, BusinessException {
		return services.getProducto(codProducto, codConvenio);
	}
	
	
	/**
	 * Crea un nuevo Producto asociado a un Convenio en Bienestar
	 * @param producto: el Objeto Producto
	 */
	public void registraProducto(Producto producto,long codigoConvenio) throws RemoteException, BusinessException, Exception {
		services.registraProducto(producto,codigoConvenio);
	}
	
	/**
	 * Actualiza un Producto asociado a un Convenio en Bienestar
	 * @param producto: el Objeto Producto y codigo de convenio
	 */
	public void actualizaProducto(Producto producto, long codigoConvenio) throws RemoteException, BusinessException, Exception {
		services.actualizaProducto(producto, codigoConvenio);
	}
	
	/**
	 * Elimina un Producto asociado a un Convenio en Bienestar
	 * @param producto: el Objeto Producto y codigo de convenio
	 */
	public void eliminaProducto(Producto producto, long codigoConvenio) throws RemoteException, BusinessException, Exception {
		services.eliminaProducto(producto, codigoConvenio);
	}
	
	/** 
	 * Obtiene los Rangos de un Producto existente
	 * @param codigo convenio y codigo de producto
	 * @return ArrayList de Rangos
	 * @throws Exception
	 */
	public Producto getRangosProducto(long codigoConvenio, Producto producto) throws RemoteException, Exception, BusinessException {
		return services.getRangosProducto(codigoConvenio,producto);
	}
	
	/**
	 * Actualiza un rango asociado a un Producto
	 * @param producto: el Objeto Producto
	 */
	public void actualizaRangoProducto(Producto producto,long codigoConvenio) throws RemoteException, BusinessException, Exception {
		services.actualizaRangoProducto(producto,codigoConvenio);
	}
	
	/** 
	 * Obtiene la lista de Talonarios disponibles
	 * @param talonario, Objeto alonario paRa opciones de filtro
	 * @return ArrayList de Talonario
	 * @throws Exception
	 */
	public ArrayList getTalonariosDisponibles(TalonarioVO talonarioVO) throws RemoteException, Exception, BusinessException {
		return services.getTalonariosDisponibles(talonarioVO);
	}
	
	/**
	 * Obtiene información de un Vale
	 * @param long codigo de vale
	 * @return Vale
	 */ 

	public Vale getVale(long codigoVale) throws RemoteException, Exception, BusinessException {
		return services.getVale(codigoVale);
	}
	
	/**
	 * Obtiene los vales asociados a un Convenio
	 * @param
	 * @return
	 */ 

	public ArrayList getValesByConvenio(long codigoConvenio) throws RemoteException, Exception, BusinessException {
		return services.getValesByConvenio(codigoConvenio);
	}
	
	/** 
	 * Obtiene los vales mo Usados asociados a un Rut de Socio
	 * @param socio Objeto Socio
	 * @return ArrayList de Vale
	 * @throws Exception
	 */
	public ArrayList getValesNoUsadosByRut(String rutSocio, long codigoConvenio) throws RemoteException, Exception, BusinessException {
		return services.getValesNoUsadosByRut(rutSocio,codigoConvenio);
	}
	
	/** 
	 * Obtiene informacion del ultimo vale disponible
	 * @param codigo de convenio y codigo de talonario
	 * @return TalonarioVO
	 * @throws Exception
	 */
	public Vale getValeDisponible(long codigoTalonario) throws RemoteException, BusinessException, Exception {
		return services.getValeDisponible(codigoTalonario);
	}
	
	/** 
	 * Obtiene los vales asociados a un Rut de Socio
	 * @param socio Objeto Socio
	 * @return ArrayList de Vale
	 * @throws Exception
	 */
	public Socio getValesByRut(Socio socio) throws RemoteException, Exception, BusinessException {
		return services.getValesByRut(socio);
	}
	

	
	/**
	 * Asocia un Vale con un Socio
	 * @param vale, Objeto Vale y rut de Socio
	 */
	public void asociaValeSocio(Socio socio,Vale vale) throws Exception, RemoteException, BusinessException {
		services.asociaValeSocio(socio,vale);
	}
	
	/**
	 * Actualiza la informacion de un Vale
	 * @param vale, Objeto Vale y rut de Socio
	 */
	public void anulaVale(Socio socio, Vale vale) throws RemoteException, BusinessException, Exception{
		services.anulaVale(socio,vale);
	}
	
	public ArrayList getConveniosRe(Convenio convenio) throws RemoteException, Exception, BusinessException {
			return services.getConveniosRe(convenio);
		}	
}
