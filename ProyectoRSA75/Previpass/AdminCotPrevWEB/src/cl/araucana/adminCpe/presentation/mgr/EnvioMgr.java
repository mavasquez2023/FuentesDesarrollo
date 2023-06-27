package cl.araucana.adminCpe.presentation.mgr;

import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.EnvioDAO;
import cl.araucana.cp.distribuidor.hibernate.beans.EnvioVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) EnvioMgr.java 1.5 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/
/**
 * @author creyes
 * @author aacu�a
 * 
 * @version 1.5
 */
public class EnvioMgr
{
	private EnvioDAO envioDAO;

	public EnvioMgr(Session session)
	{
		this.envioDAO = new EnvioDAO(session);
	}

	/**
	 * lista envio
	 * @param idNodo
	 * @return
	 * @throws DaoException
	 */
	public int getNumEnviosPorNodo(int idNodo) throws DaoException
	{
		return this.envioDAO.getNumEnviosPorNodo(idNodo);
	}
	/**
	 * ultimo envio
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public EnvioVO getEnvio(int id) throws DaoException
	{
		return this.envioDAO.getEnvio(id);
	}
}
