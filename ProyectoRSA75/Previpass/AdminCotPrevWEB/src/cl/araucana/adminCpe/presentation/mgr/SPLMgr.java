package cl.araucana.adminCpe.presentation.mgr;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.SplDAO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) SPLMgr.java 1.3 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/
/**
 * @author malvarez
 * @author jdelgado
 * 
 * @version 1.3
 */
public class SPLMgr 
{
	private static Logger logger = Logger.getLogger(SPLMgr.class);
	private SplDAO splDAO;
	
	public SPLMgr(Session session) 
	{
		this.splDAO = new SplDAO(session);
	}
	
	/**
	 * Retorna los bancos activos
	 * @return
	 * @throws DaoException
	 * @author malvarez
	 */
	public List getMediosPagoActivos() throws DaoException 
	{
		List lista = this.splDAO.getMediosPagoActivos();
		logger.debug("Nro de bancos activos en SPL: " + lista.size());
		return lista;
	}
	/**
	 * lista montos spl convenio
	 * @param idConvenio
	 * @return
	 * @throws DaoException
	 */
	public List getMontosSplConvenio(int idConvenio) throws DaoException 
	{
		List lista = this.splDAO.getMontosSplConvenio(idConvenio);
		return lista;
	}
	/**
	 * lista montos spl convenio
	 * @return
	 * @throws DaoException
	 */
	public List getMontosSplConvenio() throws DaoException 
	{
		List lista = this.splDAO.getMontosSplConvenio();
		return lista;
	}
	
	/**
	 * estado pago
	 * @param idEstado
	 * @return
	 * @throws DaoException
	 */
	public String getEstadoPago(int idEstado) throws DaoException 
	{
		return this.splDAO.getEstadoPago(idEstado);
	}
}
