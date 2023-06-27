package cl.araucana.cp.distribuidor.business.ejbs;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NodoVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

/**
 * Remote interface for cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionBean bean.
 */
public interface DistribuidorSession extends javax.ejb.EJBObject 
{
	/**
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	public StringBuffer getEnv() throws java.rmi.RemoteException;

	/**
	 * @param idPersona
	 * @return
	 * @throws DaoException
	 * @throws java.rmi.RemoteException
	 */
	public NodoVO asigna(String idPersona) throws DaoException, java.rmi.RemoteException;

	/**
	 * @param idPersona
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	public List getEstadisticas(String idPersona) throws java.rmi.RemoteException;

	/**
	 * @param idPersona
	 * @param region
	 * @throws java.rmi.RemoteException
	 */
	public void limpiaCache(String idPersona, String region) throws java.rmi.RemoteException;

	/**
	 * @param pendiente
	 * @param rutEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @param idTrabajador
	 * @param idPersona
	 * @param periodo
	 * @return
	 * @throws java.rmi.RemoteException
	 */
//	clillo 3-12-14 por Reliquidación
	//public int eliminaTrabajador(boolean pendiente, int rutEmpresa, int idConvenio, char tipoProceso, int idTrabajador, String idPersona) throws java.rmi.RemoteException;
	public int eliminaTrabajador(boolean pendiente, int rutEmpresa, int idConvenio, char tipoProceso, int idTrabajador, String idPersona, int periodo) throws java.rmi.RemoteException;
	
	//se deja original para Independiente ??
	//public int eliminaTrabajador(boolean pendiente, int rutEmpresa, int idConvenio, char tipoProceso, int idTrabajador, String idPersona) throws java.rmi.RemoteException;

	/**
	 * @param idPersona
	 * @throws java.rmi.RemoteException
	 */
	public void limpiaCache(String idPersona) throws java.rmi.RemoteException;

	/**
	 * @return
	 * @throws DaoException
	 * @throws java.rmi.RemoteException
	 */
	public boolean cargaConfiguracion() throws DaoException, java.rmi.RemoteException;

	/**
	 * @param idPersona
	 * @return
	 * @throws DaoException
	 * @throws java.rmi.RemoteException
	 */
	public boolean actualizaBalanceo(String idPersona) throws DaoException, java.rmi.RemoteException;

	/**
	 * @param idEnvio
	 * @param idPersona
	 * @return
	 * @throws DaoException
	 * @throws java.rmi.RemoteException
	 */
	public HashMap asigna(int idEnvio, String idPersona) throws DaoException, java.rmi.RemoteException;

	/**
	 * @param idCotizPend
	 * @param idPersona
	 * @param oldRut
	 * @param cotizante
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	public List valida(int idCotizPend, String idPersona, String oldRut, CotizanteVO cotizante) throws java.rmi.RemoteException;
	
	/**
	 * @param idCotizPend
	 * @param idPersona
	 * @param oldRut
	 * @param cotizante
	 * @param periodo
	 * @return
	 * @throws java.rmi.RemoteException
	 */
//	clillo 3-12-14 por Reliquidación
	public List valida(int idCotizPend, String idPersona, String oldRut, CotizanteVO cotizante, int periodo_old) throws java.rmi.RemoteException;
	
	/**
	 * @param idDescriptor
	 * @param idPersona
	 * @param mapConceptos
	 * @throws java.rmi.RemoteException
	 */
	public void valida(String idDescriptor, String idPersona, Properties mapConceptos) throws java.rmi.RemoteException;
	
	/**
	 * @param idPersona
	 * @param tipoProceso
	 * @param nominas
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	public HashMap validaCarga(String idPersona, char tipoProceso, HashMap nominas) throws java.rmi.RemoteException;
}
