package cl.araucana.adminCpe.presentation.mgr;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.TipoCausaDAO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoCausaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) TipoCausaMgr.java 1.4 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jdelgado
 * @author malvarez
 * 
 * @version 1.4
 */
public class TipoCausaMgr
{
	private TipoCausaDAO tipoCausaDAO;
	
	static Logger log = Logger.getLogger(TipoCausaMgr.class);

	public TipoCausaMgr(Session session)
	{
		this.tipoCausaDAO = new TipoCausaDAO(session);
	}
	/**
	 * Lista de errores
	 * @param String descripcion con la descripcion de los errores a buscar
	 * @return
	 * @throws DaoException
	 */
	public List getErrores(String descripcion) throws DaoException
	{
		return this.tipoCausaDAO.getErrores(descripcion);
	}
	/**
	 * lista errores
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public List getErrores(int id) throws DaoException
	{
		return this.tipoCausaDAO.getErrores(id);
	}
	/**
	 * guarda tipo causa
	 * @param tipoCausaVO
	 * @param tipo
	 * @throws DaoException
	 */
	public void save(TipoCausaVO tipoCausaVO) throws DaoException 
	{
		this.tipoCausaDAO.save(tipoCausaVO);
	}
	/**
	 * actualiza tipo causa
	 * @param tipoCausaVO
	 * @param tipo
	 * @throws DaoException
	 */
	public void update(TipoCausaVO tipoCausaVO) throws DaoException 
	{
		this.tipoCausaDAO.update(tipoCausaVO);
	}
	/**
	 * elimina tipo causa
	 * @param objeto
	 * @return
	 * @throws Exception
	 */
	public boolean delete(TipoCausaVO objeto) throws Exception 
	{
		return this.tipoCausaDAO.delete(objeto);
	}
	/**
	 * tipo causa
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public TipoCausaVO getTipoCausa(int id) throws Exception 
	{
		return this.tipoCausaDAO.getTipoCausa(id);
	}	
}
