package cl.araucana.adminCpe.hibernate.dao;

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

import cl.araucana.adminCpe.presentation.mgr.EmpresaMgr;
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
import cl.araucana.cp.distribuidor.hibernate.beans.NominaREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoCausaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.validadorSis.model.bo.dvo.ContigenciaDVO;

/*
 * @(#) CotizanteDao.java 1.9 10/06/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */

/**
 * @author jdelgado
 * @author cchamblas
 * 
 * @version 1.9
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
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @param filtro
	 * @return
	 * @throws DaoException
	 */
	public List getListaCotizPend(int idEmpresa, int idConvenio, char tipoProceso) throws DaoException
	{
		try
		{
			Class tipo = null;
			if (tipoProceso == 'R')
				tipo = CotizacionPendienteREVO.class;
			else if (tipoProceso == 'G')
				tipo = CotizacionPendienteGRVO.class;
			else if (tipoProceso == 'A')
				tipo = CotizacionPendienteRAVO.class;
			else if (tipoProceso == 'D')
				tipo = CotizacionPendienteDCVO.class;

			// log.info("CotizanteDAO:getListaCotizPend:idEmpresa" + idEmpresa + ":idConvenio:" + idConvenio + ":tipoProceso:" + tipoProceso + "::");
			Criteria crit = this.session.createCriteria(tipo).add(Restrictions.eq("rutEmpresa", new Integer(idEmpresa)));
			crit = crit.add(Restrictions.eq("idConvenio", new Integer(idConvenio)));
			return crit.list();
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getListaCotizPend", ex);
			throw new DaoException("CotizanteDAO:getListaCotizPend idEmpresa: " + idEmpresa + ", idConvenio: " + idConvenio + ":tipoProceso:" + tipoProceso, ex);
		}
	}

	/**
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
			log.info("CotizanteDAO:getCotizante:idEmpresa" + idEmpresa + ":idConvenio:" + idConvenio + ":tipoProceso:" + tipoProceso + ":rutTrabajador:" + rutTrabajador + "::");
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

			log.info("CotizanteDAO:getCotizPend:idEmpresa" + idEmpresa + ":idConvenio:" + idConvenio + ":tipoProceso:" + tipoProceso + ":getCotizPend:" + getCotizPend + "::");
			return (CotizacionPendienteVO) this.session.get(cotPend.getClass(), cotPend);
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getCotizPend", ex);
			throw new DaoException("CotizanteDAO:getCotizPend idEmpresa: " + idEmpresa + ", idConvenio: " + idConvenio + ":tipoProceso:" + tipoProceso, ex);
		}
	}

	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public HashMap getTiposCausa() throws DaoException
	{
		try
		{
			log.info("CotizanteDAO:getTiposCausa::");
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

	/**
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
			log.info("CotizanteDAO:getCausas::");
			Class tipo = null;
			if (tipoProceso == 'R')
				tipo = CausaREVO.class;
			if (tipoProceso == 'G')
				tipo = CausaGRVO.class;
			if (tipoProceso == 'A')
				tipo = CausaRAVO.class;
			if (tipoProceso == 'D')
				tipo = CausaDCVO.class;

			Criteria crit = this.session.createCriteria(tipo).add(Restrictions.eq("rutEmpresa", new Integer(cotPend.getRutEmpresa())));
			crit.add(Restrictions.eq("idConvenio", new Integer(cotPend.getIdConvenio()))).add(Restrictions.eq("idCotizPendiente", new Integer(cotPend.getIdCotizPendiente())));
			List result = crit.list();
			List lista = new ArrayList();
			if (result != null)
			{
				for (Iterator it = result.iterator(); it.hasNext();)
					lista.add(new Integer(((CausaVO) it.next()).getIdTipoCausa()));
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
	public List getCotizantesNomina(int idEmpresa, int idConvenio, char tipoProceso) throws DaoException
	{
		try
		{
			List result = new ArrayList();
			Class tipo = null;
			if (tipoProceso == 'R')
				tipo = CotizacionREVO.class;
			else if (tipoProceso == 'G')
				tipo = CotizacionGRVO.class;
			else if (tipoProceso == 'A')
				tipo = CotizacionRAVO.class;
			else
				tipo = CotizacionDCVO.class;

			log.info("CotizanteDAO:getCotizantesNomina:idEmpresa" + idEmpresa + ":idConvenio:" + idConvenio + ":tipoProceso:" + tipoProceso + "::" + tipo.getName() + "::");
			Criteria crit = this.session.createCriteria(tipo).add(Restrictions.eq("rutEmpresa", new Integer(idEmpresa)));
			List cotizaciones = crit.add(Restrictions.eq("idConvenio", new Integer(idConvenio))).list();
			for (Iterator it = cotizaciones.iterator(); it.hasNext();)
			{
				CotizacionVO cotizacion = (CotizacionVO) it.next();
				CotizanteVO cotizante = (CotizanteVO) this.session.get(CotizanteVO.class, new CotizanteVO(idEmpresa, idConvenio, cotizacion.getIdCotizante()));
				if (cotizante == null)
					log.info("\n\n\ncotiz null:");
				else
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
	 * Retorna un lista de causas de error
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
			List retorno = new ArrayList();
			log.debug("CotizanteDAO:getCausas:idEmpresa:" + idEmpresa + ":idConvenio:" + idConvenio + ":tipoProceso:" + tipoProceso + "::");
			Class tipo = null;
			Class tipoAviso = null;
			if (tipoProceso == 'R')
			{
				tipo = CausaREVO.class;
				tipoAviso = CausaAvisoREVO.class;
			}
			if (tipoProceso == 'G')
			{
				tipo = CausaGRVO.class;
				tipoAviso = CausaAvisoGRVO.class;
			}
			if (tipoProceso == 'A')
			{
				tipo = CausaRAVO.class;
				tipoAviso = CausaAvisoRAVO.class;
			}
			if (tipoProceso == 'D')
			{
				tipo = CausaDCVO.class;
				tipoAviso = CausaAvisoDCVO.class;
			}

			retorno = this.session.createCriteria(tipo).add(Restrictions.eq("rutEmpresa", new Integer(idEmpresa))).add(Restrictions.eq("idConvenio", new Integer(idConvenio))).list();
			retorno.addAll(this.session.createCriteria(tipoAviso).add(Restrictions.eq("rutEmpresa", new Integer(idEmpresa))).add(Restrictions.eq("idConvenio", new Integer(idConvenio))).list());
			return retorno;
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getCausas", ex);
			throw new DaoException("CotizanteDAO:getCausas:", ex);
		}
	}

	/**
	 * Retorna un HashMap con el id de la causa y su nivel de error
	 * 
	 * @return
	 * @throws DaoException
	 */
	public HashMap getNivelTiposCausa() throws DaoException
	{
		try
		{
			log.info("CotizanteDAO:getTiposCausa::");
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

	public List getCausasAvisos(char tipoProceso, int idEmpresa, int idConvenio, int idCotizante) throws DaoException
	{
		try
		{
			List lista = new ArrayList();
			log.info("CotizanteDAO:getCausasAvisos::");
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
			return lista;
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getCausasAvisos", ex);
			throw new DaoException("CotizanteDAO:getCausasAvisos:", ex);
		}
	}

	public int getNumAprobados(int rutEmpresa, int convenio, char tipoProceso, CotizanteVO cotizante) throws DaoException
	{
		try
		{
			StringBuffer sb = new StringBuffer("select count(cot.idCotizante) FROM " + CotizanteVO.class.getName() + " as cot "
				+ "WHERE cot.rutEmpresa = ? AND cot.idConvenio = ? AND cot." + CotizanteVO.getTipoTiene(tipoProceso) + " > 0 ");

			List params = new ArrayList();
			params.add(new Integer(rutEmpresa));
			params.add(new Integer(convenio));
			if (cotizante != null && cotizante.getIdCotizante() > 0)
			{
				sb.append("AND cot.idCotizante = ? ");
				params.add(new Integer(cotizante.getIdCotizante()));
			}
			if (cotizante != null && !cotizante.getApellidoPat().equals(""))
			{
				/*sb.append("AND (cot.apellidoPat LIKE ? or cot.apellidoMat LIKE ?) ");
				params.add("%" + cotizante.getApellidoPat() + "%");
				params.add("%" + cotizante.getApellidoPat() + "%");*/
				sb.append("AND CONCAT(CONCAT(RTRIM(cot.apellidoPat),' '), cot.apellidoMat) LIKE ?  ");
				params.add("%" + cotizante.getApellidoPat() + "%");
			}
			if (cotizante != null && !cotizante.getNombre().equals(""))
			{
				sb.append("AND cot.nombre LIKE ? ");
				params.add("%" + cotizante.getNombre() + "%");
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

	public int getNumAvisos(int rutEmpresa, int convenio, char tipoProceso, CotizanteVO cotizante) throws DaoException
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
			if (cotizante != null && cotizante.getIdCotizante() > 0)
			{
				sb.append("AND cot.idCotizante = ? ");
				params.add(new Integer(cotizante.getIdCotizante()));
			}
			if (cotizante != null && !cotizante.getApellidoPat().equals(""))
			{
				/*sb.append("AND (cot.apellidoPat LIKE ? or cot.apellidoMat LIKE ?)");
				params.add("%" + cotizante.getApellidoPat() + "%");
				params.add("%" + cotizante.getApellidoPat() + "%");*/
				sb.append("AND CONCAT(CONCAT(RTRIM(cot.apellidoPat),' '), cot.apellidoMat) LIKE ?  ");
				params.add("%" + cotizante.getApellidoPat() + "%");
			}
			if (cotizante != null && !cotizante.getNombre().equals(""))
			{
				sb.append("AND cot.nombre LIKE ? ");
				params.add("%" + cotizante.getNombre() + "%");
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

	public int getNumPendientes(int rutEmpresa, int convenio, char tipoProceso, CotizanteVO cotizante) throws DaoException
	{
		try
		{
			Criteria criteria = this.session.createCriteria(CotizacionVO.getTipoCotizPendiente(tipoProceso)).add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa))).add(
					Restrictions.eq("idConvenio", new Integer(convenio)));
			if (cotizante != null && cotizante.getIdCotizante() > 0)
				criteria = criteria.add(Restrictions.like("detalle", "%" + cotizante.getIdCotizante() + "%"));
			if (cotizante != null && !cotizante.getApellidoPat().equals(""))
				criteria = criteria.add(Restrictions.ilike("detalle", "%" + cotizante.getApellidoPat().replaceAll(" ", "%") + "%"));
			if (cotizante != null && !cotizante.getNombre().equals(""))
				criteria = criteria.add(Restrictions.ilike("detalle", "%" + cotizante.getNombre().replaceAll(" ", "%") + "%"));
			criteria.setProjection(Projections.rowCount());
			return ((Integer) criteria.list().get(0)).intValue();
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getNumPendientes", ex);
			throw new DaoException("CotizanteDAO:getNumPendientes:", ex);
		}
	}

	public List getListaCotizPend(int rutEmpresa, int convenio, char tipoProceso, CotizanteVO cotizante) throws DaoException
	{
		try
		{
			Criteria criteria = this.session.createCriteria(CotizacionVO.getTipoCotizPendiente(tipoProceso)).add(Restrictions.eq("rutEmpresa", new Integer(rutEmpresa))).add(
					Restrictions.eq("idConvenio", new Integer(convenio)));
			if (cotizante != null && cotizante.getIdCotizante() > 0)
				criteria = criteria.add(Restrictions.like("detalle", "%" + cotizante.getIdCotizante() + "%"));
			if (cotizante != null && !cotizante.getApellidoPat().equals(""))
			{
				criteria = criteria.add(Restrictions.ilike("detalle", "%" + cotizante.getApellidoPat().replaceAll(" ", "%") + "%"));
			}
			if (cotizante != null && !cotizante.getNombre().equals(""))
				criteria = criteria.add(Restrictions.ilike("detalle", "%" + cotizante.getNombre().replaceAll(" ", "%") + "%"));
			return criteria.list();
		} catch (Exception ex)
		{
			log.error("Error en CotizanteDAO:getNumPendientes", ex);
			throw new DaoException("CotizanteDAO:getNumPendientes:", ex);
		}
	}

	public List getListaCotizantesAvisos(int regIni, int cantidad, int idEmpresa, int idConvenio, char tipoProceso, CotizanteVO cotizante) throws DaoException
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
			if (cotizante != null && cotizante.getIdCotizante() > 0)
			{
				sb.append("AND cot.idCotizante = ? ");
				params.add(new Integer(cotizante.getIdCotizante()));
			}
			if (cotizante != null && !cotizante.getApellidoPat().equals(""))
			{
				/*sb.append("AND (cot.apellidoPat LIKE ? or cot.apellidoMat LIKE ?) ");
				params.add("%" + cotizante.getApellidoPat() + "%");
				params.add("%" + cotizante.getApellidoPat() + "%");*/

				sb.append("AND CONCAT(CONCAT(RTRIM(cot.apellidoPat),' '), cot.apellidoMat) LIKE ?  ");
				params.add("%" + cotizante.getApellidoPat() + "%");
			}
			if (cotizante != null && !cotizante.getNombre().equals(""))
			{
				sb.append("AND cot.nombre LIKE ? ");
				params.add("%" + cotizante.getNombre() + "%");
			}
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

	public List getListaCotizantes(int regIni, int cantidad, int rutEmpresa, int convenio, char tipoProceso, CotizanteVO cotizante) throws DaoException
	{
		try
		{
			StringBuffer sb = new StringBuffer("select cot from " + CotizanteVO.class.getName() + " as cot "
					+ "WHERE cot.rutEmpresa = ? AND cot.idConvenio = ? AND cot." + CotizanteVO.getTipoTiene(tipoProceso) + " > 0 "
					+ "AND 0 = (SELECT count(aviso.idCotizPendiente) FROM " + CotizacionVO.getTipoCausaAviso("" + tipoProceso).getName() + " as aviso "
							+ " WHERE cot.rutEmpresa = aviso.rutEmpresa AND cot.idConvenio = aviso.idConvenio AND cot.idCotizante = aviso.idCotizPendiente)");

			List params = new ArrayList();
			params.add(new Integer(rutEmpresa));
			params.add(new Integer(convenio));
			if (cotizante != null && cotizante.getIdCotizante() > 0)
			{
				sb.append("AND cot.idCotizante = ? ");
				params.add(new Integer(cotizante.getIdCotizante()));
			}
			if (cotizante != null && !cotizante.getApellidoPat().equals(""))
			{
				/*sb.append("AND (cot.apellidoPat LIKE ? or cot.apellidoMat LIKE ?) ");
				params.add("%" + cotizante.getApellidoPat() + "%");
				params.add("%" + cotizante.getApellidoPat() + "%");*/

				sb.append("AND CONCAT(CONCAT(RTRIM(cot.apellidoPat),' '), cot.apellidoMat) LIKE ?  ");
				params.add("%" + cotizante.getApellidoPat() + "%");
			}
			if (cotizante != null && !cotizante.getNombre().equals(""))
			{
				sb.append("AND cot.nombre LIKE ? ");
				params.add("%" + cotizante.getNombre() + "%");
			}
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
			throw new DaoException("CotizanteDAO:getListaCotizantes:", ex);
		}
	}
	/**
	 * 
	 * @param idEmpresa
	 * @param rutTrabajador
	 * @return
	 * @throws DaoException 
	 */
	public CotizanteVO getCotizante(int idEmpresa, int rutTrabajador) throws DaoException
	{
		try
		{
			Criteria crit = this.session.createCriteria(CotizanteVO.class).add(Restrictions.eq("rutEmpresa", new Integer(idEmpresa)))
							.add(Restrictions.eq("idConvenio", new Integer(rutTrabajador)));
			//if(crit.list().size() == 0 )
					//throw new Exception("No se obtuvo cotizante para el rut trabajador: " + rutTrabajador  + " rut empresa: " +idEmpresa);
			
				return null;//(CotizanteVO)crit.list().get(0);
		} catch (Exception ex)
		{
			log.error("ERROR CotizanteDAO:getCotizante(" + idEmpresa + "," +rutTrabajador + ")");
			throw new DaoException("Problemas en CotizanteDAO:getCotizante", ex);
		}
	}
}

