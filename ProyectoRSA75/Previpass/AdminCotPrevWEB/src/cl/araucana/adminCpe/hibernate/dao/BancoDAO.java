package cl.araucana.adminCpe.hibernate.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.BancoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CtaCteBancoVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

/**
 * @author jdelgado
 * @author aacuña
 * 
 * @version 1.4
 */
public class BancoDAO
{
	private static Logger log = Logger.getLogger(BancoDAO.class);
	private Session session;
	private boolean loggear = true;

	public BancoDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * flag indica si traer o no banco id = 0
	 * 
	 * @return lista bancos
	 * @throws DaoException
	 */
	public List getBancos(boolean flag) throws DaoException
	{
		try
		{
			if (this.loggear) log.info("BancoDAO:getBancos:");
			Criteria crit = this.session.createCriteria(BancoVO.class);
			if (!flag)
				crit = crit.add(Restrictions.ne("idBanco", new Integer(Constants.BANCO_FALSO)));
			return crit.addOrder(Order.asc("rutBanco")).list();
		} catch (Exception ex)
		{
			log.error("Error en BancoDAO.getBancos");
			throw new DaoException("BancoDAO:getBancos:", ex);
		}
	}
	/**
	 * 
	 * @param idBanco
	 * @return lista un banco
	 * @throws DaoException
	 */
	public List getBanco(int idBanco) throws DaoException
	{
		try
		{
			Class tipo = BancoVO.class;
			if (this.loggear) log.info("BancoDAO:getBancos:");
			List lista= this.session.createCriteria(tipo).add(Restrictions.eq("idBanco", new Integer(idBanco))).list();
			return lista;
		} catch (Exception ex)
		{
			log.error("Error en BancoDAO.getBancos");
			throw new DaoException("BancoDAO:getBancos:", ex);
		}
	}
	/**
	 * 
	 * @param col
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public boolean existe(String col, int id) throws DaoException
	{
		try
		{
			Class tipo = BancoVO.class;
			if (this.loggear) log.info("BancoDAO:existe:");
			List lista= this.session.createCriteria(tipo).add(Restrictions.eq(col, id+"")).list();
			if(lista.size()>0)
				return true;
			return false;
		} catch (Exception ex)
		{
			log.error("Error en BancoDAO.getBancos");
			throw new DaoException("BancoDAO:getBancos:", ex);
		}
	}
	/**
	 * 
	 * @param col
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public boolean existe(String col, String id) throws DaoException
	{
		try
		{
			Class tipo = BancoVO.class;
			if (this.loggear) log.info("BancoDAO:existe:");
			List lista= this.session.createCriteria(tipo).add(Restrictions.eq(col, new String(id))).list();
			if(lista.size()>0)
				return true;
			return false;
		} catch (Exception ex)
		{
			log.error("Error en BancoDAO.getBancos");
			throw new DaoException("BancoDAO:getBancos:", ex);
		}
	}
	/**
	 * 
	 * @param bancoVO
	 * @param tipo
	 * @throws DaoException
	 */
	public void save(BancoVO bancoVO) throws DaoException 
	{
		try 
		{
			log.info("BancoDAO:save");
			this.session.save(bancoVO);
		} catch(Exception ex) 
		{
			log.error("BancoDAO:save error: " + ex.getCause().toString());
			throw new DaoException("Problemas en BancoDAO:save", ex);
		}
	}
	/**
	 * 
	 * @param bancoVO
	 * @param tipo
	 * @throws DaoException
	 */
	public void update(BancoVO bancoVO) throws DaoException 
	{
		try 
		{
			log.info("BancoDAO:update");
			this.session.update(bancoVO);
		} catch(Exception ex) 
		{
			log.error("BancoDAO:update error: " + ex.getCause().toString());
			throw new DaoException("Problemas en BancoDAO:update", ex);
		}
	}
	/**
	 * 
	 * @param id
	 * @param tipo
	 * @throws Exception
	 */
	public void del(int id, Class tipo) throws Exception 
	{
		try
		{
			log.info("BancoDAO:del:" + id + "::");
			Criteria crit = this.session.createCriteria(CtaCteBancoVO.class)
					.add(Restrictions.eq("idBanco", new Integer(id)));
			List listaCtas = crit.list();
			for (Iterator itCta = listaCtas.iterator(); itCta.hasNext();) 
				this.session.delete(itCta.next());
			this.session.flush();

			List lista = this.session.createCriteria(tipo)
				.add(Restrictions.eq("id", new Integer(id)))
				.list();
			for (Iterator it = lista.iterator(); it.hasNext();) 
				this.session.delete(it.next());
			this.session.flush();
		} catch(Exception ex) 
		{
			log.error("BancoDAO:del error: " + ex.getCause().toString());
			throw new DaoException("Problemas en BancoDAO:del", ex);
		}
	}
	/**
	 * 
	 * @param cta
	 * @return
	 * @throws DaoException
	 */
	public boolean existeCuenta(CtaCteBancoVO cta) throws DaoException
	{
		try
		{
			if(cta == null)
				return false;

			if (this.loggear) log.info("BancoDAO:existeCuenta:");
			CtaCteBancoVO _cta = (CtaCteBancoVO)this.session.get(CtaCteBancoVO.class, cta);					
			if(_cta != null)
				return true;
			return false;
		} catch (Exception ex)
		{
			log.error("Error en BancoDAO.existeCuenta");
			throw new DaoException("BancoDAO:existeCuenta:", ex);
		}
	}
	/**
	 * 
	 * @param bancoVO
	 * @param tipo
	 * @throws DaoException
	 */
	public void saveCuenta(CtaCteBancoVO cta) throws DaoException 
	{
		try 
		{
			log.info("BancoDAO:saveCuenta");
			this.session.save(cta);
			this.session.flush();
		} catch(Exception ex) 
		{
			log.error("BancoDAO:save error: " + ex.getCause().toString());
			throw new DaoException("Problemas en BancoDAO:saveCuenta", ex);
		}
	}
}
