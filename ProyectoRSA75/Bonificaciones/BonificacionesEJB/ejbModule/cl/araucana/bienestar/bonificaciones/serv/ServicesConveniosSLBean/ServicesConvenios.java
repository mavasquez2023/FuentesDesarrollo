package cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosSLBean;

import java.rmi.RemoteException;
import java.util.ArrayList;

import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.model.Producto;
import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.model.Talonario;
import cl.araucana.bienestar.bonificaciones.model.Vale;
import cl.araucana.bienestar.bonificaciones.vo.TalonarioVO;
import cl.araucana.common.BusinessException;

/**
 * Remote interface for Enterprise Bean: ServicesSocios
 */
public interface ServicesConvenios extends javax.ejb.EJBObject {
	
	/** 
	 * Obtiene la lista de Convenios existentes
	 * @param Convenio
	 * @return ArrayList de Convenio
	 * @throws Exception
	 */
	public ArrayList getConvenios(Convenio convenio) throws Exception, BusinessException;
	
	/**
	 * Obtiene la lista de Convenios relacionados con una cobertura
	 * @param codigo de Cobertura
	 * @return ArrayList de Convenio
	 * @throws Exception
	 */
	public ArrayList getConvenios( long codigoCobertura) throws Exception, BusinessException;
	
	/** 
	 * Obtiene un Convenio existente
	 * @param codigo de Convenio
	 * @return Convenio
	 * @throws Exception
	 */
	public Convenio getConvenio(long codigoConvenio) throws Exception, BusinessException;

	/** 
	 * Obtiene la lista de Talonarios existentes
	 * @param codigo convenio
	 * @throws Exception
	 */
	public Convenio getTalonarios(Convenio convenio, Talonario talonarioFiltro) throws Exception, BusinessException;
	
	/** 
	 * Obtiene la lista de Talonarios disponibles
	 * @param talonario, Objeto alonario paRa opciones de filtro
	 * @return ArrayList de Talonario
	 * @throws Exception
	 */
	public ArrayList getTalonariosDisponibles(TalonarioVO talonarioVO) throws Exception, BusinessException;
	
	/**
	 * Registra un nuevo Talonario asociado a un Convenio en Bienestar
	 * @param convenio: el Objeto Convenio y talonario: el Objeto Talonario
	 */
	public void registraTalonario(Convenio convenio, Talonario talonario) throws Exception, RemoteException,BusinessException;
	
	/**
	 * Actualiza la informacion de un talonario
	 * @param convenio: el Objeto Convenio y talonario: el Objeto Talonario
	 */
	public void actualizaTalonario(Convenio convenio, Talonario talonario) throws BusinessException, Exception;
	
	/**
	 * Cambia el estado de un talonario a Eliminado
	 * @param convenio: el Objeto Convenio y talonario: el Objeto Talonario
	 */
	public void eliminaTalonario(Convenio convenio, Talonario talonario) throws Exception, RemoteException,BusinessException;

	
	/**
	 * Crea un nuevo Convenio en Bienestar
	 * @param convenio: el Objeto Convenio
	 */
	public void registraConvenio(Convenio convenio) throws RemoteException,BusinessException,Exception;
	
	/**
	 * Modifica un Convenio en Bienestar
	 * @param convenio: el Objeto Convenio
	 */
	public void actualizaConvenio(Convenio convenio) throws Exception, RemoteException,BusinessException;

	/**
	 * Activa un Convenio en Bienestar
	 * @param convenio: el Objeto Convenio
	 */
	public void activaConvenio(Convenio convenio) throws BusinessException,Exception;
	
	/**
	 * Desactiva un Convenio en Bienestar
	 * @param convenio: el Objeto Convenio
	 */
	public void desactivaConvenio(Convenio convenio) throws BusinessException,Exception; 
		
	/** 
	 * Obtiene la lista de Productos asociados a un convenio
	 * @param producto Producto
	 * @return ArrayList de Producto
	 * @throws Exception
	 */
	public Convenio getProductos(long casoId, Convenio convenio,Producto productoFiltro) throws Exception, BusinessException;
	
	/** 
	 * Obtiene la lista de Productos activos asociados a un convenio
	 * @param long codigoConvenio,Producto productoFiltro
	 * @return ArrayList de Producto
	 * @throws Exception
	 */
	public ArrayList getProductosByConvenio(long codigoConvenio,Producto productoFiltro)   throws Exception, BusinessException;
	
	/** 
	 * Obtiene un Producto asociado a un convenio
	 * @param codigo de producto y codigo de convenio
	 * @return producto
	 * @throws Exception
	 */
	public Producto getProducto(long codProducto, long codConvenio) throws Exception, BusinessException;
	
	/**
	 * Crea un nuevo Producto asociado a un Convenio en Bienestar
	 * @param producto: el Objeto Producto
	 */
	public void registraProducto(Producto producto,long codigoConvenio) throws Exception, RemoteException,BusinessException ;
	
	/**
	 * Actualiza un Producto asociado a un Convenio en Bienestar
	 * @param producto: el Objeto Producto y codigo de convenio
	 */
	public void actualizaProducto(Producto producto, long codigoConvenio) throws Exception, RemoteException,BusinessException ;
	
	/**
	 * Elimina un Producto asociado a un Convenio en Bienestar
	 * @param producto: el Objeto Producto y codigo de convenio
	 */
	public void eliminaProducto(Producto producto, long codigoConvenio) throws Exception, RemoteException,BusinessException ;
	
	/** 
	 * Obtiene los Rangos de un Producto existente
	 * @param codigo convenio y codigo de producto
	 * @return ArrayList de Rangos
	 * @throws Exception
	 */
	public Producto getRangosProducto(long codigoConvenio, Producto producto) throws Exception, BusinessException;
	
	/**
	 * Actualiza un rango asociado a un Producto
	 * @param producto: el Objeto Producto
	 */
	public void actualizaRangoProducto(Producto producto,long codigoConvenio) throws Exception, RemoteException,BusinessException;
	
	/**
	 * Obtiene información de un Vale
	 * @param long codigo de vale
	 * @return Vale
	 */ 

	public Vale getVale(long codigoVale) throws Exception,BusinessException;
	
	
	/**
	 * Obtiene los vales asociados a un Convenio
	 * @param
	 * @return
	 */ 

	public ArrayList getValesByConvenio(long codigoConvenio) throws Exception, BusinessException;
	
	/** 
	 * Obtiene los vales mo Usados asociados a un Rut de Socio
	 * @param socio Objeto Socio
	 * @return ArrayList de Vale
	 * @throws Exception
	 */
	public ArrayList getValesNoUsadosByRut(String rutSocio,long codigoConvenio) throws Exception, BusinessException;
	
	/** 
	 * Obtiene informacion del ultimo vale disponible
	 * @param codigo de convenio y codigo de talonario
	 * @return TalonarioVO
	 * @throws Exception
	 */
	public Vale getValeDisponible(long codigoTalonario) throws BusinessException, Exception ;
	
	/** 
	 * Obtiene los vales asociados a un Rut de Socio
	 * @param socio Objeto Socio
	 * @return ArrayList de Vale
	 * @throws Exception
	 */
	public Socio getValesByRut(Socio socio) throws Exception, BusinessException;

	/**
	 * Asocia un Vale con un Socio
	 * @param vale, Objeto Vale y rut de Socio
	 */
	public void asociaValeSocio(Socio socio,Vale vale) throws Exception, RemoteException, BusinessException;
	
	/**
	 * Actualiza la informacion de un Vale
	 * @param vale, Objeto Vale y rut de Socio
	 */
	public void anulaVale(Socio socio, Vale vale) throws Exception, RemoteException, BusinessException;
	
	public ArrayList getConveniosRe( Convenio convenio) throws Exception, BusinessException;
}

