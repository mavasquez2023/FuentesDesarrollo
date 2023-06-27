package cl.araucana.bienestar.bonificaciones.serv.ServicesCoberturasSLBean;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import cl.araucana.bienestar.bonificaciones.model.Cobertura;
import cl.araucana.bienestar.bonificaciones.vo.AgrupacionCobertura;
import cl.araucana.common.BusinessException;

/**
 * Remote interface for Enterprise Bean: ServicesSocios
 */
public interface ServicesCoberturas extends javax.ejb.EJBObject {
	
	/** 
	 * Obtiene la lista de Coberturas (Bonificaciones) de tipo adicional existentes
	 * @return ArrayList de Cobertura
	 * @throws Exception
	 */
	public ArrayList getListaCoberturasAdicionales() throws Exception, BusinessException;
	
	/** 
	 * Obtiene la lista de Coberturas (Bonificaciones) de tipo especial existentes
	 * @return ArrayList de Cobertura
	 * @throws Exception
	 */
	public ArrayList getListaCoberturasEspeciales() throws Exception, BusinessException;
	
	/** 
	 * Obtiene la lista de Coberturas (Bonificaciones) de tipo DIRECTA existentes (REQ-5088)
	 * @return ArrayList de Cobertura
	 * @throws Exception
	 */
	public ArrayList getListaCoberturasDirectas() throws Exception, BusinessException;
	
	
	/** 
	 * Obtiene la lista de Coberturas (Bonificaciones) existentes
	 * @param Cobertura
	 * @return ArrayList de Cobertura
	 * @throws Exception
	 */
	public ArrayList getCoberturas(Cobertura cobertura) throws Exception, BusinessException;
	
	/** 
	 * Obtiene la lista de Coberturas que no esten en la lista de productos de un convenio
	 * @param codigo Convenio 
	 * @param Cobertura
	 * @return ArrayList de Cobertura
	 * @throws Exception
	 */
	public ArrayList getComplementoProductos(long codigoConvenio) throws Exception, BusinessException ;
	
	/** 
	 * Obtiene una Cobertura (Bonificacion) existente
	 * @param codigo de cobertura
	 * @return Cobertura
	 * @throws Exception
	 */
	public Cobertura getCobertura(long codigoCobertura) throws Exception, BusinessException;
	
	/**
	 * Crea una nueva cobertura en Bienestar
	 * @param Cobertura
	 */
	public void registraCobertura(Cobertura cobertura) throws RemoteException,BusinessException,Exception;
	
	/**
	 * Activa una nueva cobertura en Bienestar
	 * Valida que la cobertura tenga rangos definidos para poder activarla
	 * @param Cobertura
	 */
	public void activaCobertura(Cobertura cobertura) throws BusinessException, Exception;
		
	/**
	 * Actualiza una cobertura en Bienestar
	 * @param Cobertura
	 */
	public void actualizaCobertura(Cobertura cobertura) throws RemoteException,BusinessException,Exception;
	
	/**
	 * Elimina una cobertura en Bienestar
	 * @param Cobertura
	 */
	public void eliminaCobertura(Cobertura cobertura) throws Exception, RemoteException, BusinessException;
	
	/**
	 * Actualiza los Rangos de una cobertura en Bienestar
	 * @param Cobertura
	 * @param int estadoRango
	 */
	public void actualizaRangosCobertura(Cobertura cobertura, int estadoRango) throws Exception, BusinessException; 
	
	/** 
	 * Obtiene todos los Rangos de una Cobertura (Bonificacion) existentes
	 * Obtiene el rango vigente si existe, los rangos historicos y el rango futuro si existe
	 * @param Cobertura
	 * @return Cobertura con los rangos
	 * @throws Exception
	 */
	public Cobertura getAllRangosCobertura(Cobertura cobertura) throws Exception, BusinessException;
	
	/** 
	 * Obtiene el Rango que se encuentra vigente en la fecha pasada en los parámetros
	 * @param Cobertura
	 * @param Date fecha
	 * @return Cobertura con VigenciaRango rangoVigente si tiene y null si no tiene
	 * @throws Exception
	 */
	public Cobertura getRangoCoberturaVigenteByFecha(Cobertura cobertura, Date fecha) throws Exception, BusinessException;
	
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
	public AgrupacionCobertura getRelacionAgrupacionCobertura(long codigoCobertura) throws Exception, BusinessException;
	
	/** 
	 * Busca todas la relaciones que posee una cobertura maestra
	 * @param long codigoCoberturaMaestra 
	 * @return ArrayList de AgrupacionCobertura
	 * @throws Exception
	 */
	public ArrayList getRelacionAgrupacionCoberturaByCoberturaMaestra(long codigoCobertura) throws Exception, BusinessException;
	
	/** 
	 * Busca todas la relaciones que posee una cobertura maestra y devuelve
	 * un ArrayList de Cobertura
	 * @param long codigoCoberturaMaestra 
	 * @return ArrayList de Cobertura
	 * @throws Exception
	 */
	public ArrayList getCoberturasByCoberturaMaestra(long codigoCobertura) throws Exception, BusinessException;

}

