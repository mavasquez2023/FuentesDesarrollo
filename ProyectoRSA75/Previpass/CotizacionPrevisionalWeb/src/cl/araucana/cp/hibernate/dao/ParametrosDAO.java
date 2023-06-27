package cl.araucana.cp.hibernate.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.hibernate.beans.AvisosVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CalendarioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ParametroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RelacionTipoCausaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoCausaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

/*
 * @(#) ParametrosDao.java 1.14 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.14
 */
public class ParametrosDAO
{
	private static Logger log = Logger.getLogger(ParametrosDAO.class);
	Session session;

	public ParametrosDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * parametros spl
	 * 
	 * @return
	 * @throws DaoException
	 */
	public ParametrosHash getParametrosHash() throws DaoException
	{
		try
		{
			ParametrosHash param = new ParametrosHash();
			List result = this.session.createCriteria(ParametroVO.class).list();
			if (result != null && result.size() > 0)
			{
				for (Iterator it = result.iterator(); it.hasNext();)
				{
					ParametroVO p = (ParametroVO) it.next();
					param.add("" + p.getId(), p.getValor().trim());
					log.info("param:" + p.getId() + "::"  + p.getValor().trim() + "::");
				}
			} else
				throw new DaoException("ERROR ParametrosDAO:getParametrosHash:no se encontro ningun parametro::");

			return param;
		} catch (Exception ex)
		{
			log.error("ERROR ParametrosDAO:getParametrosHash");
			throw new DaoException("ERROR ParametrosDAO:getParametrosHash:", ex);
		}
	}

	/**
	 * parametros spl
	 * 
	 * @param listaParams
	 * @return
	 * @throws DaoException
	 */
	public ParametrosHash getParametrosHash(List listaParams) throws DaoException
	{
		try
		{
			log.debug("ParametrosDAO:getParametrosHash");
			ParametrosHash params = new ParametrosHash();
			List result = this.session.createCriteria(ParametroVO.class).add(Restrictions.in("id", listaParams)).list();
			if (result.size() != listaParams.size())
				throw new DaoException("ERROR ParametrosDAO:getParametrosHash:parametros no encontrados por lista:buscados:" + listaParams.size() + ":encontrados:" + result.size() + "::");

			for (Iterator it = result.iterator(); it.hasNext();)
			{
				ParametroVO param = (ParametroVO)it.next();
				params.add("" + param.getId(), param.getValor().trim());
			}

			return params;
		} catch (Exception ex)
		{
			log.error("ERROR ParametrosDAO:getParametrosHash");
			throw new DaoException("ERROR ParametrosDAO:getParametrosHash:", ex);
		}
	}

	/**
	 * parametro
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public ParametroVO getParametro(int id) throws DaoException
	{
		try
		{
			ParametroVO param = (ParametroVO) this.session.get(ParametroVO.class, new Integer(id));
			if (param != null)
				return param;
			throw new DaoException("ERROR ParametrosDAO:getParametro:parametro no encontrado:" + id + "::");
		} catch (Exception ex)
		{
			log.error("ERROR ParametrosDAO:getParametro:" + id + "::");
			throw new DaoException("ERROR ParametrosDAO:getParametro:", ex);
		}
	}

	/**
	 * aviso
	 * 
	 * @return
	 * @throws DaoException
	 */
	public AvisosVO getAviso() throws DaoException
	{
		try
		{
			List result = this.session.createCriteria(AvisosVO.class).add(Restrictions.eq("estado", new Integer(Constants.NIVEL_VAL_AVISO))).list();
			if (result.size() > 0)
			{
				AvisosVO a = (AvisosVO) result.get(0);
				a.setContenido(a.getContenido().trim());
				a.setLink(a.getLink().trim());
				a.setTitulo(a.getTitulo().trim());
				return a;
			}
			return null;
		} catch (Exception ex)
		{
			log.error("ERROR ParametrosDAO:getAviso::");
			throw new DaoException("ERROR ParametrosDAO:getAviso:", ex);
		}
	}

	/**
	 * calendario
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getCalendario() throws DaoException
	{
		try
		{
			return this.session.createCriteria(CalendarioVO.class).addOrder(Order.asc("idCal")).list();
		} catch (Exception ex)
		{
			log.error("ERROR ParametrosDAO:getCalendario::");
			throw new DaoException("ERROR ParametrosDAO:getCalendario:", ex);
		}
	}

	public HashMap getTiposCausasWarn() throws DaoException
	{
		try
		{
			HashMap result = new HashMap();
			List lista = this.session.createCriteria(TipoCausaVO.class).add(Restrictions.eq("error", new Integer(Constants.NIVEL_VAL_AVISO))).list();

			for (Iterator it = lista.iterator(); it.hasNext();)
			{
				TipoCausaVO tc = (TipoCausaVO) it.next();
				result.put("" + tc.getId(), tc);
			}
			return result;
		} catch (Exception e)
		{
			log.error("\n\nERROR getTiposCausasWarn::", e);
			throw new DaoException("ERROR ValidacionDAO:getTiposCausasWarn::", e);
		}
	}

	public List getRelacionTipoCausa() throws DaoException
	{
		try
		{
			return this.session.createCriteria(RelacionTipoCausaVO.class).list();
		} catch (Exception ex)
		{
			log.error("ERROR ParametrosDAO:getRelacionTipoCausa::");
			throw new DaoException("ERROR ParametrosDAO:getRelacionTipoCausa:", ex);
		}
	}
}
