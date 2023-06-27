package cl.araucana.adminCpe.presentation.mgr;

import java.util.List;

import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.NodoDAO;
import cl.araucana.cp.distribuidor.hibernate.beans.NodoVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) NodoMgr.java 1.2 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/
/**
 * @author creyes
 * @author cchamblas
 * 
 * @version 1.2
 */
public class NodoMgr
{
	private NodoDAO nodoDAO;

	public NodoMgr(Session session)
	{
		this.nodoDAO= new NodoDAO(session);
	}

	/**
	 * lista nodos
	 * @return
	 * @throws DaoException
	 */
	public List getListaNodos() throws DaoException
	{
		return this.nodoDAO.getListaNodos();
	}

	/**
	 * nodo
	 * @param idNodo
	 * @return
	 * @throws DaoException
	 */
	public NodoVO getNodo(int idNodo) throws DaoException
	{
		return this.nodoDAO.getNodo(idNodo);
	}
	/**
	 * nodo
	 * @param nodo
	 * @throws DaoException
	 */
	public int saveNodo(NodoVO nodo) throws DaoException
	{    	
    	return this.nodoDAO.saveNodo(nodo);
	}
	/**
	 * elimina nodo
	 * @param idNodo
	 * @throws DaoException
	 */
	public void eliminaNodo(int idNodo) throws DaoException
	{
		this.nodoDAO.eliminaNodo(idNodo);
	}
	

}
