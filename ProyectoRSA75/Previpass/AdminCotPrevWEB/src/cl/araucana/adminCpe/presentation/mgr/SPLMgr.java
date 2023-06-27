package cl.araucana.adminCpe.presentation.mgr;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.SplDAO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) SPLMgr.java 1.3 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
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
