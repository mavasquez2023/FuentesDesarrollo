package cl.araucana.adminCpe.presentation.mgr;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.BancoDAO;
import cl.araucana.cp.distribuidor.hibernate.beans.BancoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CtaCteBancoVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) BancoMgr.java 1.4 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jdelgado
 * 
 * @version 1.4
 */
public class BancoMgr
{
	private BancoDAO bancoDAO;
	
	static Logger log = Logger.getLogger(BancoMgr.class);

	public BancoMgr(Session session)
	{
		this.bancoDAO = new BancoDAO(session);
	}

	/**
	 * lista bancos
	 * @return
	 * @throws DaoException
	 */
	public List getBancos() throws DaoException
	{
		return this.bancoDAO.getBancos(true);
	}
	/**
	 * lista banco
	 * @param idBanco
	 * @return
	 * @throws DaoException
	 */
	public List getBanco(int idBanco) throws DaoException
	{
		return this.bancoDAO.getBanco(idBanco);
	}
	/**
	 * existe banco
	 * @param col
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public boolean existe(String col, int id) throws DaoException{
		return this.bancoDAO.existe(col,id);
	}
	/**
	 * existe banco
	 * @param col
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public boolean existe(String col, String id) throws DaoException{
		return this.bancoDAO.existe(col,id);
	}
	/**
	 * guarda banco
	 * @param bancoVO
	 * @param tipo
	 * @throws DaoException
	 */
	public void save(BancoVO bancoVO) throws DaoException 
	{
		this.bancoDAO.save(bancoVO);
	}
	/**
	 * actualiza banco
	 * @param bancoVO
	 * @param tipo
	 * @throws DaoException
	 */
	public void update(BancoVO bancoVO) throws DaoException 
	{
		this.bancoDAO.update(bancoVO);
	}
	/**
	 * elimina banco
	 * @param id
	 * @param tipo
	 * @throws Exception
	 */
	public void del(int id, Class tipo) throws Exception 
	{
		this.bancoDAO.del(id, tipo);
	}
	/**
	 * existe cuenta
	 * @param cta
	 * @return
	 * @throws DaoException
	 */
	public boolean existeCuenta(CtaCteBancoVO cta) throws DaoException{
		return this.bancoDAO.existeCuenta(cta);
	}
	/**
	 * guarda Cta.
	 * @param cta
	 * @throws DaoException
	 */
	public void saveCuenta(CtaCteBancoVO cta) throws DaoException 
	{
		this.bancoDAO.saveCuenta(cta);
	}

	public List getBancos(boolean flag) throws DaoException 
	{
		return this.bancoDAO.getBancos(flag);
	}
}
