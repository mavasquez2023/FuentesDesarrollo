package cl.araucana.cp.hibernate.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.AdministradorVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ApellidoCompuestoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaAvisoDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaAvisoGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaAvisoRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaAvisoREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MovtoPersonalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoCausaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

/*
 * @(#) CotizanteDao.java 1.16 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.16
 */
public class CotizanteDAO
{
	private static Logger log = Logger.getLogger(CotizanteDAO.class);
	private Session session;

	public CotizanteDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * cotizante
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @param rutTrabajador
	 * @return
	 */
	public CotizanteVO getCotizante(int idEmpresa, int idConvenio, char tipoProceso, int rutTrabajador)
	{
		try
		{
			log.debug("CotizanteDAO:getCotizante:idEmpresa" + idEmpresa + ":idConvenio:" + idConvenio + ":tipoProceso:" + tipoProceso + ":rutTrabajador:" + rutTrabajador + "::");
			CotizanteVO cotizante = (CotizanteVO) this.session.get(CotizanteVO.class, new CotizanteVO(idEmpresa, idConvenio, rutTrabajador));
			if (cotizante != null)
			{
				Class classCotizacion = null;
				if (tipoProceso == 'R')
					classCotizacion = CotizacionREVO.class;
				else if (tipoProceso == 'G')
					classCotizacion = CotizacionGRVO.class;
				else if (tipoProceso == 'A')
					classCotizacion = CotizacionRAVO.class;
				else
					classCotizacion = CotizacionDCVO.class;

				Class[] argTypes = { Integer.class, Integer.class, Integer.class };
				Object[] argValues = { new Integer(idEmpresa), new Integer(idConvenio), new Integer(rutTrabajador) };
				CotizacionVO cotizacion = (CotizacionVO) this.session.get(classCotizacion, (CotizacionVO) classCotizacion.getConstructor(argTypes).newInstance(argValues));
				cotizante.setCotizacion(cotizacion);
				return cotizante;
			}
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getCotizante", ex);
		}
		return null;
	}

	/**
	 * cotizante
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param rutTrabajador
	 * @return
	 * @throws DaoException
	 */
	public CotizanteVO getCotizante(int idEmpresa, int idConvenio, int rutTrabajador) throws DaoException
	{
		try
		{
			log.debug("CotizanteDAO:getCotizante:idEmpresa" + idEmpresa + ":idConvenio:" + idConvenio + ":rutTrabajador:" + rutTrabajador + "::");
			return (CotizanteVO) this.session.get(CotizanteVO.class, new CotizanteVO(idEmpresa, idConvenio, rutTrabajador));
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getCotizante", ex);
			throw new DaoException("CotizanteDAO:getCotizante idEmpresa: " + idEmpresa + ", idConvenio: " + idConvenio + ":rutTrabajador:" + rutTrabajador, ex);
		}
	}

	/**
	 * cotizante nomina
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @return
	 * @throws DaoException
	 */
	public List getCotizantesNomina(int idEmpresa, int idConvenio, char tipoProceso) throws DaoException
	{
		try
		{
			List result = new ArrayList();
			log.info("CotizanteDAO:getCotizantesNomina:idEmpresa" + idEmpresa + ":idConvenio:" + idConvenio + ":tipoProceso:" + tipoProceso + "::");
			Criteria crit = this.session.createCriteria(CotizacionVO.getTipoCotizacion(tipoProceso)).add(Restrictions.eq("rutEmpresa", new Integer(idEmpresa)));
			List cotizaciones = crit.add(Restrictions.eq("idConvenio", new Integer(idConvenio))).list();
			for (Iterator it = cotizaciones.iterator(); it.hasNext();)
			{
				CotizacionVO cotizacion = (CotizacionVO) it.next();
				CotizanteVO cotizante = (CotizanteVO) this.session.get(CotizanteVO.class, new CotizanteVO(idEmpresa, idConvenio, cotizacion.getIdCotizante()));
				if (cotizante != null)
				{
					cotizante.setCotizacion(cotizacion);
					result.add(cotizante);
				}
			}
			return result;
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getCotizantesNomina", ex);
			throw new DaoException("CotizanteDAO:getCotizantesNomina idEmpresa: " + idEmpresa + ", idConvenio: " + idConvenio + ":tipoProceso:" + tipoProceso, ex);
		}
	}

	/**
	 * 
	 * @param regIni
	 * @param cantidad
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @return
	 * @throws DaoException
	 */
	public List getListaCotizantes(int regIni, int cantidad, int idEmpresa, int idConvenio, char tipoProceso) throws DaoException
	{
		try
		{
			log.info("CotizanteDAO:getListaCotizantes:idEmpresa" + idEmpresa + ":idConvenio:" + idConvenio + ":tipoProceso:" + tipoProceso + "::");
			StringBuffer sb = new StringBuffer("select cot from " + CotizanteVO.class.getName() + " as cot "
					+ "WHERE cot.rutEmpresa = ? AND cot.idConvenio = ? AND cot." + CotizanteVO.getTipoTiene(tipoProceso) + " > 0 "
					+ "AND 0 = (SELECT count(aviso.idCotizPendiente) FROM " + CotizacionVO.getTipoCausaAviso("" + tipoProceso).getName() + " as aviso "
							+ " WHERE cot.rutEmpresa = aviso.rutEmpresa AND cot.idConvenio = aviso.idConvenio AND cot.idCotizante = aviso.idCotizPendiente)");

			List params = new ArrayList();
			params.add(new Integer(idEmpresa));
			params.add(new Integer(idConvenio));

			sb.append(" ORDER BY cot.idCotizante");
			Query q = this.session.createQuery(sb.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				q.setParameter(par++, iter.next());
			if (cantidad > 0)
			{
				q.setFirstResult(regIni);
				q.setMaxResults(cantidad);
			}
			return q.list();
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getListaCotizantes", ex);
			throw new DaoException("CotizanteDAO:getListaCotizantes idEmpresa: " + idEmpresa + ", idConvenio: " + idConvenio + ":tipoProceso:" + tipoProceso, ex);
		}
	}

	/**
	 * cotizacion pendiente
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @return
	 * @throws DaoException
	 */
	public List getCotizPend(int idEmpresa, int idConvenio, char tipoProceso) throws DaoException
	{
		try
		{
			// log.info("CotizanteDAO:getCotizPend:idEmpresa" + idEmpresa + ":idConvenio:" + idConvenio + ":tipoProceso:" + tipoProceso + "::");
			Criteria crit = this.session.createCriteria(CotizacionVO.getTipoCotizPendiente(tipoProceso)).add(Restrictions.eq("rutEmpresa", new Integer(idEmpresa)));
			return crit.add(Restrictions.eq("idConvenio", new Integer(idConvenio))).list();

		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getCotizPend", ex);
			throw new DaoException("CotizanteDAO:getCotizPend idEmpresa: " + idEmpresa + ", idConvenio: " + idConvenio + ":tipoProceso:" + tipoProceso, ex);
		}
	}

	/**
	 * lista cortizantes pendiente
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @param filtro
	 * @return
	 * @throws DaoException
	 */
	public List getListaCotizPend(int idEmpresa, int idConvenio, char tipoProceso, String filtro) throws DaoException
	{
		try
		{
			log.info("CotizanteDAO:getListaCotizPend:idEmpresa" + idEmpresa + ":idConvenio:" + idConvenio + ":tipoProceso:" + tipoProceso + ":filtro:" + filtro + "::");
			Criteria crit = this.session.createCriteria(CotizacionVO.getTipoCotizPendiente(tipoProceso)).add(Restrictions.eq("rutEmpresa", new Integer(idEmpresa)));
			crit = crit.add(Restrictions.eq("idConvenio", new Integer(idConvenio)));
			if (filtro != null)
				crit = crit.add(Restrictions.ilike("detalle", "%" + filtro.replaceAll(" ", "%") + "%"));
			return crit.list();
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getListaCotizPend", ex);
			throw new DaoException("CotizanteDAO:getListaCotizPend idEmpresa: " + idEmpresa + ", idConvenio: " + idConvenio + ":tipoProceso:" + tipoProceso, ex);
		}
	}

	/**
	 * cotizacion pendiente
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @param getCotizPend
	 * @return
	 * @throws DaoException
	 */
	public CotizacionPendienteVO getCotizPend(int idEmpresa, int idConvenio, char tipoProceso, int getCotizPend) throws DaoException
	{
		try
		{
			CotizacionPendienteVO cotPend = null;
			if (tipoProceso == 'R')
				cotPend = new CotizacionPendienteREVO(idEmpresa, idConvenio, getCotizPend);
			else if (tipoProceso == 'G')
				cotPend = new CotizacionPendienteGRVO(idEmpresa, idConvenio, getCotizPend);
			else if (tipoProceso == 'A')
				cotPend = new CotizacionPendienteRAVO(idEmpresa, idConvenio, getCotizPend);
			else
				cotPend = new CotizacionPendienteDCVO(idEmpresa, idConvenio, getCotizPend);

			log.debug("CotizanteDAO:getCotizPend:idEmpresa" + idEmpresa + ":idConvenio:" + idConvenio + ":tipoProceso:" + tipoProceso + ":getCotizPend:" + getCotizPend + "::");
			return (CotizacionPendienteVO) this.session.get(cotPend.getClass(), cotPend);
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getCotizPend", ex);
			throw new DaoException("CotizanteDAO:getCotizPend idEmpresa: " + idEmpresa + ", idConvenio: " + idConvenio + ":tipoProceso:" + tipoProceso, ex);
		}
	}

	/**
	 * tipos causa
	 * 
	 * @return
	 * @throws DaoException
	 */
	public HashMap getTiposCausa() throws DaoException
	{
		try
		{
			log.debug("CotizanteDAO:getTiposCausa::");
			List lista = this.session.createCriteria(TipoCausaVO.class).list();
			if (lista != null && lista.size() > 0)
			{
				HashMap result = new HashMap();
				for (Iterator it = lista.iterator(); it.hasNext();)
				{
					TipoCausaVO tc = (TipoCausaVO) it.next();
					result.put(new Integer(tc.getId()), tc);
				}
				return result;
			}
			throw new DaoException("CotizanteDAO:getTiposCausa: no se pudieron recuperar los tipos de causa");
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getTiposCausa", ex);
			throw new DaoException("CotizanteDAO:getTiposCausa:", ex);
		}
	}

	/**
	 * tipos causa
	 * 
	 * @return
	 * @throws DaoException
	 */
	public HashMap getNivelTiposCausa() throws DaoException
	{
		try
		{
			log.debug("CotizanteDAO:getTiposCausa::");
			List lista = this.session.createCriteria(TipoCausaVO.class).list();
			if (lista != null && lista.size() > 0)
			{
				HashMap result = new HashMap();
				for (Iterator it = lista.iterator(); it.hasNext();)
				{
					TipoCausaVO tc = (TipoCausaVO) it.next();
					result.put(new Integer(tc.getId()), new Integer(tc.getError()));
				}
				return result;
			}
			throw new DaoException("CotizanteDAO:getNivelTiposCausa: no se pudieron recuperar los tipos de causa");
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getNivelTiposCausa", ex);
			throw new DaoException("CotizanteDAO:getNivelTiposCausa:", ex);
		}
	}

	/**
	 * causas
	 * 
	 * @param tipoProceso
	 * @param cotPend
	 * @return
	 * @throws DaoException
	 */
	public List getCausas(char tipoProceso, CotizacionPendienteVO cotPend) throws DaoException
	{
		try
		{
			log.debug("CotizanteDAO:getCausas::");
			Class tipo = null, tipoAviso = null;
			if (tipoProceso == 'R')
			{
				tipo = CausaREVO.class;
				tipoAviso = CausaAvisoREVO.class;
			} else if (tipoProceso == 'G')
			{
				tipo = CausaGRVO.class;
				tipoAviso = CausaAvisoGRVO.class;
			} else if (tipoProceso == 'A')
			{
				tipo = CausaRAVO.class;
				tipoAviso = CausaAvisoRAVO.class;
			} else if (tipoProceso == 'D')
			{
				tipo = CausaDCVO.class;
				tipoAviso = CausaAvisoDCVO.class;
			}
			Criteria crit = this.session.createCriteria(tipo).add(Restrictions.eq("rutEmpresa", new Integer(cotPend.getRutEmpresa())));
			crit.add(Restrictions.eq("idConvenio", new Integer(cotPend.getIdConvenio()))).add(Restrictions.eq("idCotizPendiente", new Integer(cotPend.getIdCotizPendiente())));
			List result = crit.list();
			List lista = new ArrayList();
			if (result != null)
				for (Iterator it = result.iterator(); it.hasNext();)
				{
					CausaVO c = (CausaVO) it.next();
					lista.add(new Integer(c.getIdTipoCausa()));
				}

			crit = this.session.createCriteria(tipoAviso).add(Restrictions.eq("rutEmpresa", new Integer(cotPend.getRutEmpresa())));
			crit.add(Restrictions.eq("idConvenio", new Integer(cotPend.getIdConvenio()))).add(Restrictions.eq("idCotizPendiente", new Integer(cotPend.getIdCotizPendiente())));
			result = crit.list();
			if (result != null)
				for (Iterator it = result.iterator(); it.hasNext();)
				{
					CausaVO c = (CausaVO) it.next();
					lista.add(new Integer(c.getIdTipoCausa()));
				}
			return lista;
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getCausas", ex);
			throw new DaoException("CotizanteDAO:getCausas:", ex);
		}
	}

	/**
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @return
	 * @throws DaoException
	 */
	public List getCausas(int idEmpresa, int idConvenio, char tipoProceso) throws DaoException
	{
		try
		{
			log.debug("CotizanteDAO:getCausas:idEmpresa:" + idEmpresa + ":idConvenio:" + idConvenio + ":tipoProceso:" + tipoProceso + "::");
			Class tipo = null;
			if (tipoProceso == 'R')
				tipo = CausaREVO.class;
			if (tipoProceso == 'G')
				tipo = CausaGRVO.class;
			if (tipoProceso == 'A')
				tipo = CausaRAVO.class;
			if (tipoProceso == 'D')
				tipo = CausaDCVO.class;

			List lista = this.session.createCriteria(tipo).add(Restrictions.eq("rutEmpresa", new Integer(idEmpresa))).add(Restrictions.eq("idConvenio", new Integer(idConvenio))).list();
			lista = getCausasAvisos(tipoProceso, idEmpresa, idConvenio, lista);
			return lista;
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getCausas", ex);
			throw new DaoException("CotizanteDAO:getCausas:", ex);
		}
	}

	/**
	 * apvs
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param idCotizante
	 * @return
	 * @throws DaoException
	 */
	public List getApvs(int idEmpresa, int idConvenio, int idCotizante) throws DaoException
	{
		try
		{
			log.debug("CotizanteDAO:getApvs::" + idEmpresa + "::" + idConvenio + "::" + idCotizante + "::");
			Criteria crit = this.session.createCriteria(ApvVO.class).add(Restrictions.eq("rutEmpresa", new Integer(idEmpresa)))
				.add(Restrictions.eq("idConvenio", new Integer(idConvenio)));
			if (idCotizante > 0)
				crit = crit.add(Restrictions.eq("idCotizante", new Integer(idCotizante)));
			return crit.list();
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getApvs", ex);
			throw new DaoException("CotizanteDAO:getApvs:", ex);
		}
	}

	/**
	 * movimientos personas
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param idCotizante
	 * @return
	 * @throws DaoException
	 */
	public List getMovtosPers(int idEmpresa, int idConvenio, int idCotizante) throws DaoException
	{
		try
		{
			log.info("CotizanteDAO:getMovtosPers::");
			return this.session.createCriteria(MovtoPersonalVO.class).add(Restrictions.eq("rutEmpresa", new Integer(idEmpresa))).add(Restrictions.eq("idCotizante", new Integer(idCotizante))).add(
					Restrictions.eq("idConvenio", new Integer(idConvenio))).list();
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getMovtosPers", ex);
			throw new DaoException("CotizanteDAO:getMovtosPers:", ex);
		}
	}

	/**
	 * apellidos compuestos
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getApellCompuestos() throws DaoException
	{
		try
		{
			log.info("CotizanteDAO:getApellCompuestos");
			List lista = this.session.createCriteria(ApellidoCompuestoVO.class).list();
			if (lista == null)
				return new ArrayList();
			return lista;
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR CotizanteDAO:getApellCompuestos::", ex);
		}
	}

	public List getCausasAvisos(char tipoProceso, int idEmpresa, int idConvenio, List lista) throws DaoException
	{
		try
		{
			log.debug("CotizanteDAO:getCausasAvisos::");
			Class tipo = null;
			if (tipoProceso == 'R')
				tipo = CausaAvisoREVO.class;
			if (tipoProceso == 'G')
				tipo = CausaAvisoGRVO.class;
			if (tipoProceso == 'A')
				tipo = CausaAvisoRAVO.class;
			if (tipoProceso == 'D')
				tipo = CausaAvisoDCVO.class;

			Criteria crit = this.session.createCriteria(tipo).add(Restrictions.eq("rutEmpresa", new Integer(idEmpresa)));
			crit.add(Restrictions.eq("idConvenio", new Integer(idConvenio)));
			List result = crit.list();
			if (result != null)
				for (Iterator it = result.iterator(); it.hasNext();)
					lista.add((CausaVO) it.next());
			return lista;
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getCausasAvisos", ex);
			throw new DaoException("CotizanteDAO:getCausasAvisos:", ex);
		}
	}

	public List getListaCotizantesAvisos(int regIni, int cantidad, int idEmpresa, int idConvenio, char tipoProceso) throws DaoException
	{
		try
		{
			log.info("CotizanteDAO:getListaCotizantesAvisos:idEmpresa" + idEmpresa + ":idConvenio:" + idConvenio + ":tipoProceso:" + tipoProceso + "::");
			StringBuffer sb = new StringBuffer("select cot from " + CotizanteVO.class.getName() + " as cot "
					+ "WHERE cot.rutEmpresa = ? AND cot.idConvenio = ? AND cot." + CotizanteVO.getTipoTiene(tipoProceso) + " > 0 "
					+ "AND 0 < (SELECT count(aviso.idCotizPendiente) FROM " + CotizacionVO.getTipoCausaAviso("" + tipoProceso).getName() + " as aviso "
							+ " WHERE cot.rutEmpresa = aviso.rutEmpresa AND cot.idConvenio = aviso.idConvenio AND cot.idCotizante = aviso.idCotizPendiente)");
			List params = new ArrayList();
			params.add(new Integer(idEmpresa));
			params.add(new Integer(idConvenio));
			sb.append(" ORDER BY cot.idCotizante");
			Query q = this.session.createQuery(sb.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				q.setParameter(par++, iter.next());
			if (cantidad > 0)
			{
				q.setFirstResult(regIni);
				q.setMaxResults(cantidad);
			}

			return q.list();
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getListaCotizantesAvisos", ex);
			throw new DaoException("CotizanteDAO:getListaCotizantesAvisos idEmpresa: " + idEmpresa + ", idConvenio: " + idConvenio + ":tipoProceso:" + tipoProceso, ex);
		}
	}

	public List getCausasAvisos(char tipoProceso, int idEmpresa, int idConvenio, int idCotizante) throws DaoException
	{
		try
		{
			List lista = new ArrayList();
			log.info("\nCotizanteDAO:getCausasAvisos::" + idEmpresa + "::" + idConvenio + "::" + idCotizante + "::");
			Class tipo = null;
			if (tipoProceso == 'R')
				tipo = CausaAvisoREVO.class;
			if (tipoProceso == 'G')
				tipo = CausaAvisoGRVO.class;
			if (tipoProceso == 'A')
				tipo = CausaAvisoRAVO.class;
			if (tipoProceso == 'D')
				tipo = CausaAvisoDCVO.class;

			Criteria crit = this.session.createCriteria(tipo).add(Restrictions.eq("rutEmpresa", new Integer(idEmpresa)));
			crit.add(Restrictions.eq("idConvenio", new Integer(idConvenio)));
			crit.add(Restrictions.eq("idCotizPendiente", new Integer(idCotizante)));
			List result = crit.list();
			if (result != null)
			{
				for (Iterator it = result.iterator(); it.hasNext();)
					lista.add(new Integer(((CausaVO) it.next()).getIdTipoCausa()));
			}
			log.info("\nCotizanteDAO:getCausasAvisos:encontrados n avisos:" + lista.size() + "::");
			return lista;
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getCausasAvisos", ex);
			throw new DaoException("CotizanteDAO:getCausasAvisos:", ex);
		}
	}

	public List getCausasAvisosPendientes(char tipoProceso, int idEmpresa, int idConvenio, int idCotizante) throws DaoException {
		try {
			List lista = new ArrayList();
			log.info("\nCotizanteDAO:getCausasAvisosPendientes::" + idEmpresa + "::" + idConvenio + "::" + idCotizante + "::");
			Class tipo = null;
			if (tipoProceso == 'R')
				tipo = CausaREVO.class;
			if (tipoProceso == 'G')
				tipo = CausaGRVO.class;
			if (tipoProceso == 'A')
				tipo = CausaRAVO.class;
			if (tipoProceso == 'D')
				tipo = CausaDCVO.class;

			Criteria crit = this.session.createCriteria(tipo).add(Restrictions.eq("rutEmpresa", new Integer(idEmpresa)));
			crit.add(Restrictions.eq("idConvenio", new Integer(idConvenio)));
			crit.add(Restrictions.eq("idCotizPendiente", new Integer(idCotizante)));
			List result = crit.list();
			if (result != null) {
				for (Iterator it = result.iterator(); it.hasNext();)
					lista.add(new Integer(((CausaVO) it.next()).getIdTipoCausa()));
			}
			log.info("\nCotizanteDAO:getCausasAvisos:encontrados n avisos:" + lista.size() + "::");
			return lista;
		} catch (Exception ex) {
			log.error("Error en CotizanteDAO:getCausasAvisos", ex);
			throw new DaoException("CotizanteDAO:getCausasAvisos:", ex);
		}
	}


	public int getNumAprobados(int rutEmpresa, int convenio, char tipoProceso, String filtro) throws DaoException
	{
		try
		{
			StringBuffer sb = new StringBuffer("select count(cot.idCotizante) FROM " + CotizanteVO.class.getName() + " as cot "
					+ "WHERE cot.rutEmpresa = ? AND cot.idConvenio = ? AND cot." + CotizanteVO.getTipoTiene(tipoProceso) + " > 0 ");

				List params = new ArrayList();
				params.add(new Integer(rutEmpresa));
				params.add(new Integer(convenio));
				if (filtro != null)
				{
					sb.append("AND cot.idCotizante = ? ");
					params.add(new Integer(filtro));
				}
				Query q = this.session.createQuery(sb.toString());
				int par = 0;
				for (Iterator iter = params.iterator(); iter.hasNext();)
					q.setParameter(par++, iter.next());
				return ((Integer) q.list().get(0)).intValue();
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getNumAprobados", ex);
			throw new DaoException("CotizanteDAO:getNumAprobados:", ex);
		}
	}

	public int getNumAvisos(int rutEmpresa, int convenio, char tipoProceso, String filtro) throws DaoException
	{
		try
		{
			StringBuffer sb = new StringBuffer("select count(cot.idCotizante) from " + CotizanteVO.class.getName() + " as cot "
					+ "WHERE cot.rutEmpresa = ? AND cot.idConvenio = ? AND cot." + CotizanteVO.getTipoTiene(tipoProceso) + " > 0 "
					+ "AND 0 < (SELECT count(aviso.idCotizPendiente) FROM " + CotizacionVO.getTipoCausaAviso("" + tipoProceso).getName() + " as aviso "
							+ " WHERE cot.rutEmpresa = aviso.rutEmpresa AND cot.idConvenio = aviso.idConvenio AND cot.idCotizante = aviso.idCotizPendiente)");
				
			List params = new ArrayList();
			params.add(new Integer(rutEmpresa));
			params.add(new Integer(convenio));
			if (filtro != null)
			{
				sb.append("AND cot.idCotizante = ? ");
				params.add(new Integer(filtro));
			}
			Query q = this.session.createQuery(sb.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				q.setParameter(par++, iter.next());
			return ((Integer) q.list().get(0)).intValue();
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getNumAvisos", ex);
			throw new DaoException("CotizanteDAO:getNumAvisos:", ex);
		}
	}

	public int getNumPendientes(int rutEmpresa, int convenio, char tipoProceso, String filtro) throws DaoException
	{
		try
		{
			Criteria criteria = this.session.createCriteria(CotizacionVO.getTipoCotizPendiente(tipoProceso)).add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa))).add(
					Restrictions.eq("idConvenio", new Integer(convenio)));
			if (filtro != null)
				criteria = criteria.add(Restrictions.ilike("detalle", "%" + filtro + "%"));
			criteria.setProjection(Projections.rowCount());
			return ((Integer) criteria.list().get(0)).intValue();
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getNumPendientes", ex);
			throw new DaoException("CotizanteDAO:getNumPendientes:", ex);
		}
	}

	/**
	 * Idéntico comportamiento a getNumAprobadosAll(int, String, String, String), sólo se extiende su funcionalidad a
	 * filtros de búsqueda por empresa
	 * 
	 * @param idUser
	 * @param filtros
	 * @return
	 * @throws DaoException
	 */
	public int getNumAprobadosAll(int idUser, HashMap filtros) throws DaoException
	{
		try
		{
			Integer filtroRut      = (filtros.containsKey("idCotizante") ? (Integer) filtros.get("idCotizante") : null);
			String  filtroNombre   = (filtros.containsKey("nombre")      ? (String)  filtros.get("nombre")      : null);
			String  filtroApellido = (filtros.containsKey("apellidos")   ? (String)  filtros.get("apellidos")   : null);

			Integer filtroRutEmpresa  = (filtros.containsKey("rutEmpresa")  ? ((Integer) filtros.get("rutEmpresa"))  : null);
			String  filtroRazonSocial = (filtros.containsKey("razonSocial") ? ((String)  filtros.get("razonSocial")) : null);
			String  filtroConvenio    = (filtros.containsKey("convenio")    ? ((String)  filtros.get("convenio"))    : null);
			String  filtroProceso     = (filtros.containsKey("proceso")     ? ((String)  filtros.get("proceso"))     : null);

			List params = new ArrayList();
			StringBuffer sb = new StringBuffer("SELECT COUNT(cot.idCotizante) FROM cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO as cot ");

			if (filtroRazonSocial != null)
			{
				sb.append(", cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO as e ");
			}

			sb.append("WHERE cot.tieneAviso = 0 ");

			if (filtroRut != null)
			{
				sb.append("AND cot.idCotizante = ? ");
				params.add(filtroRut);
			}
			if (filtroApellido != null)
			{
				sb.append("AND CONCAT(CONCAT(RTRIM(cot.apellidoPat),' '), cot.apellidoMat) LIKE ? ");
				params.add("%" + filtroApellido + "%");
			}
			if (filtroNombre != null)
			{
				sb.append("AND cot.nombre LIKE ? ");
				params.add("%" + filtroNombre + "%");
			}

			if (filtroRutEmpresa != null)
			{
				sb.append("AND cot.rutEmpresa = ? ");
				params.add(filtroRutEmpresa);
			}
			if (filtroRazonSocial != null)
			{
				sb.append("AND cot.rutEmpresa = e.idEmpresa ");
				sb.append("AND UPPER(e.razonSocial) LIKE ? ");
				params.add("%" + filtroRazonSocial + "%");
			}
			if (filtroConvenio != null && !filtroConvenio.trim().equals("0"))
			{
				sb.append("AND cot.rutEmpresa IN (SELECT co.idEmpresa FROM ConvenioVO co WHERE co.idGrupoConvenio = ?) ");
				params.add(new Integer(filtroConvenio));
			}
			if (filtroProceso != null && !filtroProceso.trim().equals("0"))
			{
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_REMUNERACION)))
				{
					sb.append("AND cot.tieneRemu = 1 ");
				}
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_RELIQUIDACION)))
				{
					sb.append("AND cot.tieneReli = 1 ");
				}
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_GRATIFICACION)))
				{
					sb.append("AND cot.tieneGrat = 1 ");
				}
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_DEPOSITOCONVENIDO)))
				{
					sb.append("AND cot.tieneDepo = 1 ");
				}
			}

			sb.append("AND (cot.rutEmpresa in (SELECT emp.idEmpresa FROM cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO as emp WHERE emp.idAdmin = ?) "
					+ "	    OR 0 < (SELECT count(perm.idEncargado) FROM cl.araucana.cp.distribuidor.hibernate.beans.EncargadoConvenioVO as perm WHERE "
					+ "					cot.rutEmpresa = perm.idEmpresa AND cot.idConvenio = perm.idConvenio AND perm.idEncargado = ?)) ");

			params.add(new Long(idUser));
			params.add(new Integer(idUser));

			Query q = this.session.createQuery(sb.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				q.setParameter(par++, iter.next());
			return ((Integer) q.list().get(0)).intValue();
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getNumAprobadosAll", ex);
			throw new DaoException("CotizanteDAO:getNumAprobadosAll:", ex);
		}
	}


	public int getNumAprobadosAll(int idUser, String filtroRut, String filtroNombre, String filtroApellido) throws DaoException
	{
		try
		{
			List params = new ArrayList();
			StringBuffer sb = new StringBuffer("SELECT COUNT(cot.idCotizante) FROM cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO as cot WHERE cot.tieneAviso = 0 ");
			if (filtroRut != null)
			{
				sb.append("AND cot.idCotizante = ? ");
				params.add(new Integer(filtroRut));
			}
			if (filtroApellido != null)
			{
				sb.append("AND CONCAT(CONCAT(RTRIM(cot.apellidoPat),' '), cot.apellidoMat) LIKE ?  ");
				params.add("%" + filtroApellido + "%");
			}
			if (filtroNombre != null)
			{
				sb.append("AND cot.nombre LIKE ? ");
				params.add("%" + filtroNombre + "%");
			}

			sb.append("AND (cot.rutEmpresa in (SELECT emp.idEmpresa FROM cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO as emp WHERE emp.idAdmin = ?) "
					+ "	    OR 0 < (SELECT count(perm.idEncargado) FROM cl.araucana.cp.distribuidor.hibernate.beans.EncargadoConvenioVO as perm WHERE "
					+ "					cot.rutEmpresa = perm.idEmpresa AND cot.idConvenio = perm.idConvenio AND perm.idEncargado = ?)) ");

			params.add(new Long(idUser));
			params.add(new Integer(idUser));

			Query q = this.session.createQuery(sb.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				q.setParameter(par++, iter.next());
			return ((Integer) q.list().get(0)).intValue();
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getNumAprobadosAll", ex);
			throw new DaoException("CotizanteDAO:getNumAprobadosAll:", ex);
		}
	}

	/**
	 * Idéntico comportamiento a getNumAvisosAll(int, String, String, String), sólo se extiende su funcionalidad a
	 * filtros de búsqueda por empresa
	 * 
	 * @param idUser
	 * @param filtros
	 * @return
	 * @throws DaoException
	 */
	public int getNumAvisosAll(int idUser, HashMap filtros) throws DaoException
	{
		try
		{
			Integer filtroRut      = (filtros.containsKey("idCotizante") ? ((Integer) filtros.get("idCotizante")) : null);
			String  filtroNombre   = (filtros.containsKey("nombre")      ? (String)   filtros.get("nombre")       : null);
			String  filtroApellido = (filtros.containsKey("apellidos")   ? (String)   filtros.get("apellidos")    : null);

			Integer filtroRutEmpresa  = (filtros.containsKey("rutEmpresa")  ? ((Integer) filtros.get("rutEmpresa"))  : null);
			String  filtroRazonSocial = (filtros.containsKey("razonSocial") ? ((String)  filtros.get("razonSocial")) : null);
			String  filtroConvenio    = (filtros.containsKey("convenio")    ? ((String)  filtros.get("convenio"))    : null);
			String  filtroProceso     = (filtros.containsKey("proceso")     ? ((String)  filtros.get("proceso"))     : null);

			List params = new ArrayList();
			StringBuffer sb = new StringBuffer("SELECT count(cot.idCotizante) FROM cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO as cot ");

			if (filtroRazonSocial != null)
			{
				sb.append(", cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO as e ");
			}
			sb.append("WHERE cot.tieneAviso > 0 AND ");

			if (filtroRut != null)
			{
				sb.append("cot.idCotizante = ? AND ");
				params.add(filtroRut);
			}
			if (filtroApellido != null)
			{
				sb.append("CONCAT(CONCAT(RTRIM(cot.apellidoPat),' '), cot.apellidoMat) LIKE ? AND ");
				params.add("%" + filtroApellido + "%");
			}
			if (filtroNombre != null)
			{
				sb.append("cot.nombre LIKE ? AND ");
				params.add("%" + filtroNombre + "%");
			}
			
			if (filtroRutEmpresa != null)
			{
				sb.append("cot.rutEmpresa = ? AND ");
				params.add(filtroRutEmpresa);
			}
			if (filtroRazonSocial != null)
			{
				sb.append("cot.rutEmpresa = e.idEmpresa AND ");
				sb.append("UPPER(e.razonSocial) LIKE ?  AND ");
				params.add("%" + filtroRazonSocial + "%");
			}
			if (filtroConvenio != null && !filtroConvenio.trim().equals("0"))
			{
				sb.append("cot.rutEmpresa IN (SELECT co.idEmpresa FROM ConvenioVO co WHERE co.idGrupoConvenio = ?) AND ");
				params.add(new Integer(filtroConvenio));
			}
			if (filtroProceso != null && !filtroProceso.trim().equals("0"))
			{
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_REMUNERACION)))
				{
					sb.append("cot.tieneRemu = 1 AND ");
				}
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_RELIQUIDACION)))
				{
					sb.append("cot.tieneReli = 1 AND ");
				}
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_GRATIFICACION)))
				{
					sb.append("cot.tieneGrat = 1 AND ");
				}
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_DEPOSITOCONVENIDO)))
				{
					sb.append("cot.tieneDepo = 1 AND ");
				}
			}
			
			sb.append("(cot.rutEmpresa in (SELECT emp.idEmpresa FROM cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO as emp WHERE emp.idAdmin = ?) "
					+ "	    OR 0 < (SELECT count(perm.idEncargado) FROM cl.araucana.cp.distribuidor.hibernate.beans.EncargadoConvenioVO as perm WHERE "
					+ "					cot.rutEmpresa = perm.idEmpresa AND cot.idConvenio = perm.idConvenio AND perm.idEncargado = ?)) ");

			params.add(new Long(idUser));
			params.add(new Integer(idUser));

			Query q = this.session.createQuery(sb.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				q.setParameter(par++, iter.next());
			return ((Integer) q.list().get(0)).intValue();
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getNumAvisosAll", ex);
			throw new DaoException("CotizanteDAO:getNumAvisosAll:", ex);
		}
	}

	
	public int getNumAvisosAll(int idUser, String filtroRut, String filtroNombre, String filtroApellido) throws DaoException
	{
		try
		{
			/*
			 * select count (idCotizante) donde el usuario sea administrador de la empresa, o sea encargado, y donde el cotizante tenga algun aviso asociado en R, G, A o D (si tiene aviso, es porque
			 * tiene cotizacion)
			 */
			List params = new ArrayList();
			StringBuffer sb = new StringBuffer("SELECT count(cot.idCotizante) FROM cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO as cot " +
					"WHERE cot.tieneAviso > 0 AND ");
			if (filtroRut != null)
			{
				sb.append("cot.idCotizante = ? AND ");
				params.add(new Integer(filtroRut));
			}
			if (filtroApellido != null)
			{
				/*sb.append("(cot.apellidoPat LIKE ? or cot.apellidoMat LIKE ?) AND ");
				params.add("%" + filtroApellido + "%");
				params.add("%" + filtroApellido + "%");*/

				sb.append("CONCAT(CONCAT(RTRIM(cot.apellidoPat),' '), cot.apellidoMat) LIKE ? AND ");
				params.add("%" + filtroApellido + "%");
			}
			if (filtroNombre != null)
			{
				sb.append("cot.nombre LIKE ? AND ");
				params.add("%" + filtroNombre + "%");
			}

			sb.append("(cot.rutEmpresa in (SELECT emp.idEmpresa FROM cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO as emp WHERE emp.idAdmin = ?) "
					+ "	    OR 0 < (SELECT count(perm.idEncargado) FROM cl.araucana.cp.distribuidor.hibernate.beans.EncargadoConvenioVO as perm WHERE "
					+ "					cot.rutEmpresa = perm.idEmpresa AND cot.idConvenio = perm.idConvenio AND perm.idEncargado = ?)) ");

			params.add(new Long(idUser));
			params.add(new Integer(idUser));

			Query q = this.session.createQuery(sb.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				q.setParameter(par++, iter.next());
			return ((Integer) q.list().get(0)).intValue();
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getNumAvisosAll", ex);
			throw new DaoException("CotizanteDAO:getNumAvisosAll:", ex);
		}
	}

	/**
	 * Idéntico comportamiento a getNumPendientesAll(int, String, String, String, List), sólo se extiende su funcionalidad a
	 * filtros de búsqueda por empresa
	 * 
	 * @param	idUser
	 * @param 	HashMap
	 * @param	tiposNomina
	 */
	public int getNumPendientesAll(int idUser, HashMap filtros, List tiposNomina) throws DaoException
	{
		try
		{
			String filtroRut      = (filtros.containsKey("idCotizante") ? ((Integer) filtros.get("idCotizante")).toString() : null);
			String filtroNombre   = (filtros.containsKey("nombre")      ? (String)   filtros.get("nombre")                  : null);
			String filtroApellido = (filtros.containsKey("apellidos")   ? (String)   filtros.get("apellidos")               : null);

			Integer filtroRutEmpresa  = (filtros.containsKey("rutEmpresa")  ? ((Integer) filtros.get("rutEmpresa"))  : null);
			String  filtroRazonSocial = (filtros.containsKey("razonSocial") ? ((String)  filtros.get("razonSocial")) : null);
			String  filtroConvenio    = (filtros.containsKey("convenio")    ? ((String)  filtros.get("convenio"))    : null);

			int count = 0;
			for (Iterator it = tiposNomina.iterator(); it.hasNext();)
			{
				List params = new ArrayList();
				String tn = ((TipoNominaVO) it.next()).getIdTipoNomina();
				StringBuffer sb = new StringBuffer("SELECT COUNT(pend.idCotizPendiente) FROM " + CotizacionVO.getTipoCotizPendiente(tn.charAt(0)).getName() + " AS pend ");
				if(filtroRazonSocial != null)
				{
					sb.append(", cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO AS e ");
				}
				sb.append("WHERE ");

				if (filtroRut != null)
				{
					sb.append("upper(pend.detalle) LIKE ? AND ");
					params.add("%" + filtroRut + "%");
				}
				if (filtroApellido != null)
				{
					sb.append("upper(pend.detalle) LIKE ? AND ");
					params.add("%" + filtroApellido.replaceAll(" ", "%") + "%");
				}
				if (filtroNombre != null)
				{
					sb.append("upper(pend.detalle) LIKE ? AND ");
					params.add("%" + filtroNombre.replaceAll(" ", "%") + "%");
				}

				if(filtroRutEmpresa != null)
				{
					sb.append("pend.rutEmpresa = ? AND ");
					params.add(filtroRutEmpresa);
				}
				if(filtroRazonSocial != null)
				{
					sb.append("e.idEmpresa = pend.rutEmpresa AND ");
					sb.append("UPPER(e.razonSocial) LIKE ?   AND ");
					params.add("%" + filtroRazonSocial + "%");
				}
				if (filtroConvenio != null && !filtroConvenio.trim().equals("0"))
				{
					sb.append("pend.rutEmpresa IN (SELECT co.idEmpresa FROM ConvenioVO co WHERE co.idGrupoConvenio = ?) AND ");
					params.add(new Integer(filtroConvenio));
				}

				sb.append("(pend.rutEmpresa in (SELECT emp.idEmpresa FROM cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO as emp WHERE emp.idAdmin = ?) "
						+ "	    OR 0 < (SELECT count(perm.idEncargado) FROM cl.araucana.cp.distribuidor.hibernate.beans.EncargadoConvenioVO as perm WHERE "
						+ "					pend.rutEmpresa = perm.idEmpresa AND pend.idConvenio = perm.idConvenio AND perm.idEncargado = ?)) ");

				params.add(new Long(idUser));
				params.add(new Integer(idUser));

				Query q = this.session.createQuery(sb.toString());
				int par = 0;
				for (Iterator iter = params.iterator(); iter.hasNext();)
					q.setParameter(par++, iter.next());
				count += ((Integer) q.list().get(0)).intValue();
			}
			return count;
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getNumPendientes", ex);
			throw new DaoException("CotizanteDAO:getNumPendientes:", ex);
		}
	}


	public int getNumPendientesAll(int idUser, String filtroRut, String filtroNombre, String filtroApellido, List tiposNomina) throws DaoException
	{
		try
		{
			int count = 0;
			for (Iterator it = tiposNomina.iterator(); it.hasNext();)
			{
				List params = new ArrayList();			
				String tn = ((TipoNominaVO) it.next()).getIdTipoNomina();
				StringBuffer sb = new StringBuffer("SELECT COUNT(pend.idCotizPendiente) FROM " + CotizacionVO.getTipoCotizPendiente(tn.charAt(0)).getName() + 
						" as pend WHERE ");

				if (filtroRut != null)
				{
					sb.append("upper(pend.detalle) LIKE ? AND ");
					params.add("%" + filtroRut + "%");
				}
				if (filtroApellido != null)
				{
					//sb.append("pend.detalle LIKE ? AND ");
					//params.add("%" + filtroApellido + "%");
					/*String apels[] = filtroApellido.split(" ");
					String tmp = "";
					for (int i = 0; i < apels.length; i++)
						tmp += apels[i] + "%";
					if (tmp.length() > 0)
					{
						sb.append("upper(pend.detalle) LIKE ? AND ");
						params.add("%" + tmp.substring(0, tmp.length() - 1) + "%");	
					}*/
					sb.append("upper(pend.detalle) LIKE ? AND ");
					params.add("%" + filtroApellido.replaceAll(" ", "%") + "%");
				}
				if (filtroNombre != null)
				{
					sb.append("upper(pend.detalle) LIKE ? AND ");
					params.add("%" + filtroNombre.replaceAll(" ", "%") + "%");
				}
	
				sb.append("(pend.rutEmpresa in (SELECT emp.idEmpresa FROM cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO as emp WHERE emp.idAdmin = ?) "
						+ "	    OR 0 < (SELECT count(perm.idEncargado) FROM cl.araucana.cp.distribuidor.hibernate.beans.EncargadoConvenioVO as perm WHERE "
						+ "					pend.rutEmpresa = perm.idEmpresa AND pend.idConvenio = perm.idConvenio AND perm.idEncargado = ?)) ");
	
				params.add(new Long(idUser));
				params.add(new Integer(idUser));
	
				Query q = this.session.createQuery(sb.toString());
				int par = 0;
				for (Iterator iter = params.iterator(); iter.hasNext();)
					q.setParameter(par++, iter.next());
				count += ((Integer) q.list().get(0)).intValue();
			}
			return count;
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getNumPendientes", ex);
			throw new DaoException("CotizanteDAO:getNumPendientes:", ex);
		}
	}

	/**
	 * Idéntico comportamiento a getPendientesAll(int, String, String, String, List), sólo se extiende su funcionalidad a
	 * filtros de búsqueda por empresa
	 * 
	 * @param idUser
	 * @param filtros
	 * @param tiposNomina
	 * @throws DaoException
	 */
	public HashMap getPendientesAll(int idUser, HashMap filtros, List tiposNomina) throws DaoException
	{
		try
		{
			String filtroRut      = (filtros.containsKey("idCotizante") ? ((Integer) filtros.get("idCotizante")).toString() : null);
			String filtroNombre   = (filtros.containsKey("nombre")      ? (String)   filtros.get("nombre")                  : null);
			String filtroApellido = (filtros.containsKey("apellidos")   ? (String)   filtros.get("apellidos")               : null);

			Integer filtroRutEmpresa  = (filtros.containsKey("rutEmpresa")  ? ((Integer) filtros.get("rutEmpresa"))  : null);
			String  filtroRazonSocial = (filtros.containsKey("razonSocial") ? ((String)  filtros.get("razonSocial")) : null);
			String  filtroConvenio    = (filtros.containsKey("convenio")    ? ((String)  filtros.get("convenio"))    : null);
			
			HashMap result = new HashMap();
			int count = 0;
			for (Iterator it = tiposNomina.iterator(); it.hasNext();)
			{
				List params = new ArrayList();
				String tn = ((TipoNominaVO) it.next()).getIdTipoNomina();
				StringBuffer sb = new StringBuffer("SELECT pend FROM " + CotizacionVO.getTipoCotizPendiente(tn.charAt(0)).getName() + " as pend ");

				if(filtroRazonSocial != null)
				{
					sb.append(", cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO AS e ");
				}
				sb.append("WHERE ");

				if (filtroRut != null)
				{
					sb.append("upper(pend.detalle) LIKE ? AND ");
					params.add("%" + filtroRut + "%");
				}
				if (filtroApellido != null)
				{
					sb.append("upper(pend.detalle) LIKE ? AND ");
					params.add("%" + filtroApellido.replaceAll(" ", "%") + "%");
				}
				if (filtroNombre != null)
				{
					sb.append("upper(pend.detalle) LIKE ? AND ");
					params.add("%" + filtroNombre.replaceAll(" ", "%") + "%");
				}

				if(filtroRutEmpresa != null)
				{
					sb.append("pend.rutEmpresa = ? AND ");
					params.add(filtroRutEmpresa);
				}
				if(filtroRazonSocial != null)
				{
					sb.append("e.idEmpresa = pend.rutEmpresa AND ");
					sb.append("UPPER(e.razonSocial) LIKE ?   AND ");
					params.add("%" + filtroRazonSocial + "%");
				}
				if (filtroConvenio != null && !filtroConvenio.trim().equals("0"))
				{
					sb.append("pend.rutEmpresa IN (SELECT co.idEmpresa FROM ConvenioVO co WHERE co.idGrupoConvenio = ?) AND ");
					params.add(new Integer(filtroConvenio));
				}

				sb.append("(pend.rutEmpresa in (SELECT emp.idEmpresa FROM cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO as emp WHERE emp.idAdmin = ?) "
						+ "	    OR 0 < (SELECT count(perm.idEncargado) FROM cl.araucana.cp.distribuidor.hibernate.beans.EncargadoConvenioVO as perm WHERE "
						+ "					pend.rutEmpresa = perm.idEmpresa AND pend.idConvenio = perm.idConvenio AND perm.idEncargado = ?)) ");

				params.add(new Long(idUser));
				params.add(new Integer(idUser));

				Query q = this.session.createQuery(sb.toString());
				int par = 0;
				for (Iterator iter = params.iterator(); iter.hasNext();)
					q.setParameter(par++, iter.next());
				List r = q.list();
				if (r.size() > 0)
				{
					result.put(tn, r);
					count += r.size();
				}
			}
			result.put("numPendientes", new Integer(count));
			return result;
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getPendientesAll", ex);
			throw new DaoException("CotizanteDAO:getPendientesAll:", ex);
		}
	}

	public HashMap getPendientesAll(int idUser, String filtroRut, String filtroNombre, String filtroApellido, List tiposNomina) throws DaoException
	{
		try
		{
			/*
			 * select count (idCotizPendiente) donde el usuario sea administrador de la empresa, o sea encargado, y donde el cotizante pendiente tenga registro en R, G, A o D
			 */
			HashMap result = new HashMap();
			int count = 0;
			for (Iterator it = tiposNomina.iterator(); it.hasNext();)
			{
				List params = new ArrayList();
				String tn = ((TipoNominaVO) it.next()).getIdTipoNomina();
				StringBuffer sb = new StringBuffer("SELECT pend FROM " + CotizacionVO.getTipoCotizPendiente(tn.charAt(0)).getName() + " as pend WHERE ");			
				if (filtroRut != null)
				{
					sb.append("upper(pend.detalle) LIKE ? AND ");
					params.add("%" + filtroRut + "%");
				}
				if (filtroApellido != null)
				{
					/*String apels[] = filtroApellido.split(" ");
					String tmp = "";
					for (int i = 0; i < apels.length; i++)
						tmp += apels[i] + "%";
					if (tmp.length() > 0)
					{
						sb.append("upper(pend.detalle) LIKE ? AND ");
						params.add("%" + tmp.substring(0, tmp.length() - 1) + "%");	
					}*/
					sb.append("upper(pend.detalle) LIKE ? AND ");
					params.add("%" + filtroApellido.replaceAll(" ", "%") + "%");
				}
				if (filtroNombre != null)
				{
					sb.append("upper(pend.detalle) LIKE ? AND ");
					params.add("%" + filtroNombre.replaceAll(" ", "%") + "%");
				}

				sb.append("(pend.rutEmpresa in (SELECT emp.idEmpresa FROM cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO as emp WHERE emp.idAdmin = ?) "
						+ "	    OR 0 < (SELECT count(perm.idEncargado) FROM cl.araucana.cp.distribuidor.hibernate.beans.EncargadoConvenioVO as perm WHERE "
						+ "					pend.rutEmpresa = perm.idEmpresa AND pend.idConvenio = perm.idConvenio AND perm.idEncargado = ?)) ");

				params.add(new Long(idUser));
				params.add(new Integer(idUser));

				Query q = this.session.createQuery(sb.toString());
				int par = 0;
				for (Iterator iter = params.iterator(); iter.hasNext();)
					q.setParameter(par++, iter.next());
				List r = q.list();
				if (r.size() > 0)
				{
					result.put(tn, r);
					count += r.size();
				}
			}
			result.put("numPendientes", new Integer(count));
			return result;
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getPendientesAll", ex);
			throw new DaoException("CotizanteDAO:getPendientesAll:", ex);
		}
	}

	/**
	 * Idéntico comportamiento a getAllCotizantesAvisos(int, int, int, String, String, String), sólo se extiende su funcionalidad a
	 * filtros de búsqueda por empresa
	 *
	 * @param posIni
	 * @param cantidad
	 * @param idUser
	 * @param filtros
	 * @throws DaoException
	 */
	public List getAllCotizantesAvisos(int posIni, int cantidad, int idUser, HashMap filtros) throws DaoException
	{
		try
		{
			String filtroRut      = (filtros.containsKey("idCotizante") ? ((Integer) filtros.get("idCotizante")).toString() : null);
			String filtroNombre   = (filtros.containsKey("nombre")      ? (String)   filtros.get("nombre")                  : null);
			String filtroApellido = (filtros.containsKey("apellidos")   ? (String)   filtros.get("apellidos")               : null);

			String filtroRutEmpresa  = (filtros.containsKey("rutEmpresa")  ? ((Integer) filtros.get("rutEmpresa")).toString() : null);
			String filtroRazonSocial = (filtros.containsKey("razonSocial") ? ((String)  filtros.get("razonSocial"))           : null);
			String filtroConvenio    = (filtros.containsKey("convenio")    ? ((String)  filtros.get("convenio"))              : null);
			String filtroProceso     = (filtros.containsKey("proceso")     ? ((String)  filtros.get("proceso"))               : null);
			
			log.info("CotizanteDAO:getAllCotizantesAvisos:desde:" + posIni + ":cuantos:" + cantidad + "::");
			List params = new ArrayList();
			StringBuffer sb = new StringBuffer("SELECT cot.idCotizante, cot.rutEmpresa, cot.idConvenio, cot.nombre, cot.apellidoPat, cot.apellidoMat, cot.tieneRemu, cot.tieneGrat, cot.tieneReli, cot.tieneDepo ");
			sb.append("FROM cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO as cot ");
			
			if(filtroRazonSocial != null)
			{
				sb.append(", cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO AS e ");
			}

			sb.append("WHERE cot.tieneAviso > 0 AND ");
			
			if (filtroRut != null)
			{
				sb.append("cot.idCotizante = ? AND ");
				params.add(new Integer(filtroRut));
			}
			if (filtroApellido != null)
			{
				sb.append("CONCAT (CONCAT(TRIM(cot.apellidoPat),' '),cot.apellidoMat) LIKE ? AND ");
				params.add("%" + filtroApellido + "%");
			}
			if (filtroNombre != null)
			{
				sb.append("cot.nombre LIKE ? AND ");
				params.add("%" + filtroNombre + "%");
			}

			if (filtroRutEmpresa != null)
			{
				sb.append("cot.rutEmpresa = ? AND ");
				params.add(new Integer(filtroRutEmpresa));
			}
			if (filtroRazonSocial != null)
			{
				sb.append("cot.rutEmpresa = e.idEmpresa AND ");
				sb.append("UPPER(e.razonSocial) LIKE ?  AND ");
				params.add("%" + filtroRazonSocial + "%");
			}
			if (filtroConvenio != null && !filtroConvenio.trim().equals("0"))
			{
				sb.append("cot.rutEmpresa IN (SELECT co.idEmpresa FROM ConvenioVO co WHERE co.idGrupoConvenio = ?) AND ");
				params.add(new Integer(filtroConvenio));
			}
			if (filtroProceso != null && !filtroProceso.trim().equals("0"))
			{
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_REMUNERACION)))
				{
					sb.append("cot.tieneRemu = 1 AND ");
				}
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_RELIQUIDACION)))
				{
					sb.append("cot.tieneReli = 1 AND ");
				}
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_GRATIFICACION)))
				{
					sb.append("cot.tieneGrat = 1 AND ");
				}
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_DEPOSITOCONVENIDO)))
				{
					sb.append("cot.tieneDepo = 1 AND ");
				}
			}

			sb.append("(cot.rutEmpresa in (SELECT emp.idEmpresa FROM cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO as emp WHERE emp.idAdmin = ?) "
					+ "  OR 0 < (SELECT count(perm.idEncargado) FROM cl.araucana.cp.distribuidor.hibernate.beans.EncargadoConvenioVO as perm WHERE "
					+ "			cot.rutEmpresa = perm.idEmpresa AND cot.idConvenio = perm.idConvenio AND perm.idEncargado = ?)) ");

			params.add(new Long(idUser));
			params.add(new Integer(idUser));


			sb.append(" ORDER BY cot.idCotizante asc, cot.rutEmpresa asc, cot.idConvenio asc");

			Query q = this.session.createQuery(sb.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				q.setParameter(par++, iter.next());
			q.setMaxResults(cantidad);
			q.setFirstResult(posIni);
			return q.list();
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getAllCotizantesAvisos", ex);
			throw new DaoException("CotizanteDAO:getAllCotizantesAvisos:", ex);
		}
	}

	public List getAllCotizantesAvisos(int posIni, int cantidad, int idUser, String filtroRut, String filtroNombre, String filtroApellido) throws DaoException
	{
		try
		{
			log.info("CotizanteDAO:getAllCotizantesAvisos:desde:" + posIni + ":cuantos:" + cantidad + "::");
			List params = new ArrayList();
			StringBuffer sb = new StringBuffer("SELECT cot.idCotizante, cot.rutEmpresa, cot.idConvenio, cot.nombre, cot.apellidoPat, cot.apellidoMat, " +
					"tieneRemu, tieneGrat, tieneReli, tieneDepo FROM cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO as cot WHERE cot.tieneAviso > 0 AND ");
			
			if (filtroRut != null)
			{
				sb.append("cot.idCotizante = ? AND ");
				params.add(new Integer(filtroRut));
			}
			if (filtroApellido != null)
			{
				/*sb.append("(cot.apellidoPat LIKE ? or cot.apellidoMat LIKE ?) AND ");
				params.add("%" + filtroApellido + "%");
				params.add("%" + filtroApellido + "%");*/
				sb.append("CONCAT (CONCAT(TRIM(cot.apellidoPat),' '),cot.apellidoMat) LIKE ? AND ");
				params.add("%" + filtroApellido + "%");
			}
			if (filtroNombre != null)
			{
				sb.append("cot.nombre LIKE ? AND ");
				params.add("%" + filtroNombre + "%");
			}

			sb.append("  (cot.rutEmpresa in (SELECT emp.idEmpresa FROM cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO as emp WHERE emp.idAdmin = ?) "
					+ "	    OR 0 < (SELECT count(perm.idEncargado) FROM cl.araucana.cp.distribuidor.hibernate.beans.EncargadoConvenioVO as perm WHERE "
					+ "					cot.rutEmpresa = perm.idEmpresa AND cot.idConvenio = perm.idConvenio AND perm.idEncargado = ?)) ");

			params.add(new Long(idUser));
			params.add(new Integer(idUser));
			
			
			sb.append(" ORDER BY cot.idCotizante asc, cot.rutEmpresa asc, cot.idConvenio asc");

			Query q = this.session.createQuery(sb.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				q.setParameter(par++, iter.next());
			q.setMaxResults(cantidad);
			q.setFirstResult(posIni);
			return q.list();
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getAllCotizantesAvisos", ex);
			throw new DaoException("CotizanteDAO:getAllCotizantesAvisos:", ex);
		}
	}

	/**
	 * Idéntico comportamiento a getAllCotizantes(int, int, int, HashMap filtros), sólo se extiende su funcionalidad a
	 * filtros de búsqueda por empresa
	 * 
	 * @param posIni
	 * @param cantidad
	 * @param idUser
	 * @param filtros
	 * @throws DaoException
	 */
	public List getAllCotizantes(int posIni, int cantidad, int idUser, HashMap filtros) throws DaoException
	{
		try
		{
			String filtroRut      = (filtros.containsKey("idCotizante") ? ((Integer) filtros.get("idCotizante")).toString() : null);
			String filtroNombre   = (filtros.containsKey("nombre")      ? (String)   filtros.get("nombre")                  : null);
			String filtroApellido = (filtros.containsKey("apellidos")   ? (String)   filtros.get("apellidos")               : null);

			String filtroRutEmpresa  = (filtros.containsKey("rutEmpresa")  ? ((Integer) filtros.get("rutEmpresa")).toString() : null);
			String filtroRazonSocial = (filtros.containsKey("razonSocial") ? ((String)  filtros.get("razonSocial"))           : null);
			String filtroConvenio    = (filtros.containsKey("convenio")    ? ((String)  filtros.get("convenio"))              : null);
			String filtroProceso     = (filtros.containsKey("proceso")     ? ((String)  filtros.get("proceso"))               : null);

			log.info("CotizanteDAO:getAllCotizantes:desde:" + posIni + ":cuantos:" + cantidad + "::");
			List params = new ArrayList();
			StringBuffer sb = new StringBuffer("SELECT cot.idCotizante, cot.rutEmpresa, cot.idConvenio, cot.nombre, cot.apellidoPat, cot.apellidoMat, cot.tieneRemu, cot.tieneGrat, cot.tieneReli, cot.tieneDepo ");
			sb.append("FROM cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO as cot ");
			
			if(filtroRazonSocial != null)
			{
				sb.append(", cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO AS e ");
			}
			sb.append("WHERE cot.tieneAviso = 0 ");
			
			if (filtroRut != null)
			{
				sb.append("AND cot.idCotizante = ? ");
				params.add(new Integer(filtroRut));
			}
			if (filtroApellido != null)
			{
				sb.append("AND CONCAT (CONCAT(TRIM(cot.apellidoPat),' '),cot.apellidoMat) LIKE ? ");
				params.add("%" + filtroApellido + "%");
			}
			if (filtroNombre != null)
			{
				sb.append("AND cot.nombre LIKE ? ");
				params.add("%" + filtroNombre + "%");
			}

			if (filtroRutEmpresa != null)
			{
				sb.append("AND cot.rutEmpresa = ? ");
				params.add(new Integer(filtroRutEmpresa));
			}
			if (filtroRazonSocial != null)
			{
				sb.append("AND cot.rutEmpresa = e.idEmpresa ");
				sb.append("AND UPPER(e.razonSocial) LIKE ? ");
				params.add("%" + filtroRazonSocial + "%");
			}
			if (filtroConvenio != null && !filtroConvenio.trim().equals("0"))
			{
				sb.append("AND cot.rutEmpresa IN (SELECT co.idEmpresa FROM ConvenioVO co WHERE co.idGrupoConvenio = ?) ");
				params.add(new Integer(filtroConvenio));
			}
			if (filtroProceso != null && !filtroProceso.trim().equals("0"))
			{
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_REMUNERACION)))
				{
					sb.append("AND cot.tieneRemu = 1 ");
				}
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_RELIQUIDACION)))
				{
					sb.append("AND cot.tieneReli = 1 ");
				}
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_GRATIFICACION)))
				{
					sb.append("AND cot.tieneGrat = 1 ");
				}
				if (filtroProceso.equalsIgnoreCase(String.valueOf(Constants.TIPO_NOM_DEPOSITOCONVENIDO)))
				{
					sb.append("AND cot.tieneDepo = 1 ");
				}
			}
			
			sb.append("AND ( cot.rutEmpresa in (SELECT emp.idEmpresa FROM cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO as emp WHERE emp.idAdmin = ?) "
					+ "    OR 0 < (SELECT count(perm.idEncargado) FROM cl.araucana.cp.distribuidor.hibernate.beans.EncargadoConvenioVO as perm WHERE "
					+ "				cot.rutEmpresa = perm.idEmpresa AND cot.idConvenio = perm.idConvenio AND perm.idEncargado = ?)) ");

			params.add(new Long(idUser));
			params.add(new Integer(idUser));

			sb.append(" ORDER BY cot.idCotizante asc, cot.rutEmpresa asc, cot.idConvenio asc");

			Query q = this.session.createQuery(sb.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				q.setParameter(par++, iter.next());
			q.setMaxResults(cantidad);
			q.setFirstResult(posIni);
			return q.list();
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getAllCotizantes", ex);
			throw new DaoException("CotizanteDAO:getAllCotizantes:", ex);
		}
	}

	public List getAllCotizantes(int posIni, int cantidad, int idUser, String filtroRut, String filtroNombre, String filtroApellido) throws DaoException
	{
		try
		{
			log.info("CotizanteDAO:getAllCotizantes:desde:" + posIni + ":cuantos:" + cantidad + "::");
			List params = new ArrayList();
			StringBuffer sb = new StringBuffer("SELECT cot.idCotizante, cot.rutEmpresa, cot.idConvenio, cot.nombre, cot.apellidoPat, cot.apellidoMat, " +
			"tieneRemu, tieneGrat, tieneReli, tieneDepo FROM cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO as cot WHERE cot.tieneAviso = 0 ");
			
			if (filtroRut != null)
			{
				sb.append("AND cot.idCotizante = ? ");
				params.add(new Integer(filtroRut));
			}
			if (filtroApellido != null)
			{
				/*sb.append("(cot.apellidoPat LIKE ? or cot.apellidoMat LIKE ?) AND ");
				params.add("%" + filtroApellido + "%");
				params.add("%" + filtroApellido + "%");*/
				sb.append("AND CONCAT (CONCAT(TRIM(cot.apellidoPat),' '),cot.apellidoMat) LIKE ? ");
				params.add("%" + filtroApellido + "%");
			}
			if (filtroNombre != null)
			{
				sb.append("AND cot.nombre LIKE ? ");
				params.add("%" + filtroNombre + "%");
			}

			sb.append(" AND ( cot.rutEmpresa in (SELECT emp.idEmpresa FROM cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO as emp WHERE emp.idAdmin = ?) "
					+ "	    OR 0 < (SELECT count(perm.idEncargado) FROM cl.araucana.cp.distribuidor.hibernate.beans.EncargadoConvenioVO as perm WHERE "
					+ "					cot.rutEmpresa = perm.idEmpresa AND cot.idConvenio = perm.idConvenio AND perm.idEncargado = ?)) ");

			params.add(new Long(idUser));
			params.add(new Integer(idUser));

			sb.append(" ORDER BY cot.idCotizante asc, cot.rutEmpresa asc, cot.idConvenio asc");

			Query q = this.session.createQuery(sb.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				q.setParameter(par++, iter.next());
			q.setMaxResults(cantidad);
			q.setFirstResult(posIni);
			return q.list();
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getAllCotizantes", ex);
			throw new DaoException("CotizanteDAO:getAllCotizantes:", ex);
		}
	}
	
	public List getTrabajadoresPendientes(long rutEmpresa, long idConvenio, String tipoNomina) throws DaoException
	{
		//TODO metodo por confirmar
		List listado = new ArrayList();
		return listado;
	}
	
	public List getCausasVO(char tipoProceso, CotizacionPendienteVO cotPend) throws DaoException {
		try {
			log.debug("CotizanteDAO:getCausas::");
			Class tipo = null, tipoAviso = null;
			if (tipoProceso == 'R'){
				tipo = CausaREVO.class;
				tipoAviso = CausaAvisoREVO.class;
			} else if (tipoProceso == 'G') {
				tipo = CausaGRVO.class;
				tipoAviso = CausaAvisoGRVO.class;
			} else if (tipoProceso == 'A') {
				tipo = CausaRAVO.class;
				tipoAviso = CausaAvisoRAVO.class;
			} else if (tipoProceso == 'D') {
				tipo = CausaDCVO.class;
				tipoAviso = CausaAvisoDCVO.class;
			}
			Criteria crit = this.session.createCriteria(tipo).add(Restrictions.eq("rutEmpresa", new Integer(cotPend.getRutEmpresa())));
			crit.add(Restrictions.eq("idConvenio", new Integer(cotPend.getIdConvenio()))).add(Restrictions.eq("idCotizPendiente", new Integer(cotPend.getIdCotizPendiente())));
			List result = crit.list();
			List lista = new ArrayList();
			if (result != null) {
				for (Iterator it = result.iterator(); it.hasNext();) {
					CausaVO c = (CausaVO) it.next();
					lista.add(c);
				}
			}
			crit = this.session.createCriteria(tipoAviso).add(Restrictions.eq("rutEmpresa", new Integer(cotPend.getRutEmpresa())));
			crit.add(Restrictions.eq("idConvenio", new Integer(cotPend.getIdConvenio()))).add(Restrictions.eq("idCotizPendiente", new Integer(cotPend.getIdCotizPendiente())));
			result = crit.list();
			if (result != null) {
				for (Iterator it = result.iterator(); it.hasNext();) {
					CausaVO c = (CausaVO) it.next();
					lista.add(c);
				}
			}
			return lista;
		} catch (Exception ex) {
			log.error("Error en CotizanteDAO:getCausasVO", ex);
			throw new DaoException("CotizanteDAO:getCausas:", ex);
		}
	}
	
	
	/**
	 * 
	 * @param regIni
	 * @param cantidad
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @return
	 * @throws DaoException
	 */
	public String[] getTipoAdmin(int rutCotizante) throws DaoException
	{
		String[] tipoAdmin=new String[2];
		try
		{
			//log.info("CotizanteDAO:getListaCotizantes:idEmpresa" + idEmpresa + ":idConvenio:" + idConvenio + ":tipoProceso:" + tipoProceso + "::");
			StringBuffer sb = new StringBuffer(" select adm.empresa, adm.independiente, " +
											   " from "+ CotizanteVO.class.getName() + " as cot , " +
											     ConvenioVO.class.getName() +" as conv  ," +
											     EmpresaVO.class.getName() +" as emp ,"+ 
											     AdministradorVO.class.getName() +" as adm "+
											    " where cot.rutEmpresa = conv.idEmpresa "+
											    " and cot.rutEmpresa = emp.idEmpresa "+
											    " and emp.idAdmin = adm.idAdmin "+
												" and cot.idCotizante='"+rutCotizante+"'");	
		
			Query q = this.session.createQuery(sb.toString());
						
			if(q.list().size() > 0){
			
				tipoAdmin[0] =	((String) q.list().get(0)).toString();
				tipoAdmin[1] =	((String) q.list().get(1)).toString();
				
			}
			
		} catch (Exception ex)
			{
				log.error("Error en CotizanteDAO:getListaCotizantes", ex);
				throw new DaoException("CotizanteDAO:getTipoAdmin rutCotizante: " + rutCotizante, ex);
			}
	 
		return tipoAdmin;
	}
}