package cl.araucana.cp.hibernate.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ValidacionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

/*
 * @(#) ComprobanteDao.java 1.8 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.8
 */
public class ConceptoDAO
{
	private static Logger logger = Logger.getLogger(ConceptoDAO.class);
	private Session session;

	public ConceptoDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * mapa conceptos
	 * 
	 * @param tipoNomina
	 * @return
	 * @throws DaoException
	 */
	public HashMap getMapaConceptos(String tipoNomina) throws DaoException
	{
		try
		{
			logger.debug("ConceptoDAO:getMapaConceptos: tipoNomina:" + tipoNomina + "::");

			List listaConcepto = this.session.createCriteria(ConceptoVO.class).add(Restrictions.eq("tipoProceso", tipoNomina)).list();
			HashMap mapConcepto = new HashMap();
			ConceptoVO concepto;
			for (Iterator it = listaConcepto.iterator(); it.hasNext();)
			{
				concepto = (ConceptoVO) it.next();
				mapConcepto.put(new Integer(concepto.getId()), concepto);
			}
			return mapConcepto;
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ConceptoDAO:getMapaConceptos: tipoNomina:" + tipoNomina + "::", ex);
		}
	}

	/**
	 * lista mapeos concepto
	 * 
	 * @param idMapaNom
	 * @param tipoNomina
	 * @return
	 * @throws DaoException
	 */
	public List getListaMapeosConcepto(int idMapaNom, String tipoNomina) throws DaoException
	{
		try
		{
			logger.info("ConceptoDAO:getListaMapeosConcepto: idMapaNom:" + idMapaNom + ":, tipoNomina:" + tipoNomina + "::");
			List listaMapeo = this.session.createCriteria(MapeoConceptoVO.class).add(Restrictions.eq("idMapa", new Integer(idMapaNom))).add(Restrictions.eq("tipoProceso", tipoNomina)).addOrder(
					Order.asc("idConcepto")).list();

			Map mapConcepto = this.getMapaConceptos(tipoNomina);
			MapeoConceptoVO mapeo;
			for (Iterator it = listaMapeo.iterator(); it.hasNext();)
			{
				mapeo = (MapeoConceptoVO) it.next();
				mapeo.setConcepto((ConceptoVO) mapConcepto.get(new Integer(mapeo.getIdConcepto())));
			}

			return listaMapeo;
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ConceptoDAO:getListaMapeosConcepto: idMapaNom:" + idMapaNom + ":, tipoNomina:" + tipoNomina + "::", ex);
		}
	}

	/**
	 * guarda mapeos concepto
	 * 
	 * @param lista
	 * @throws DaoException
	 */
	public void guardaMapeosConcep(List lista) throws DaoException
	{

		try
		{
			logger.info("ConceptoDAO:guardaMapeosConcep::");

			MapeoConceptoVO mapeo;
			for (Iterator it = lista.iterator(); it.hasNext();)
			{
				mapeo = (MapeoConceptoVO) it.next();
				this.session.merge(mapeo);
			}
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ConceptoDAO:guardaMapeosConcep:", ex);
		}
	}

	/**
	 * lista conceptos
	 * 
	 * @param tipoProceso
	 * @return
	 * @throws DaoException
	 */
	public List getListaConceptos(String tipoProceso) throws DaoException
	{
		try
		{
			logger.info("ConceptoDAO:getListaConceptos:" + tipoProceso);
			Criteria crit = this.session.createCriteria(ConceptoVO.class);
			return crit.add(Restrictions.eq("tipoProceso", tipoProceso)).list();
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ConceptoDAO:getListaConceptos:" + tipoProceso + ":", ex);
		}
	}

	/**
	 * lista valida apvx
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getLstValidaAPVs() throws DaoException
	{
		try
		{
			logger.info("ConceptoDAO:getLstValidaAPVs");
			Criteria crit = this.session.createCriteria(ValidacionVO.class);
			return crit.add(Restrictions.eq("ejecutarEn", "A")).list();
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR ConceptoDAO:getLstValidaAPVs:", ex);
		}
	}

	/**
	 * Determina la cantidad de conceptos que tiene un mapeos de archivos
	 * 
	 * @param idMapa
	 * @param tipoProceso
	 * @return
	 * @throws DaoException
	 */	
	public int getCantidadDeConceptos(int idMapa, char tipoProceso) throws DaoException {
		int contador = 0;

		logger.info("ConceptoDAO:getCantidadDeConceptos: idMapa: " + "tipoProceso: " + tipoProceso + "::");
		contador = ((Integer) this.session.createCriteria(MapeoConceptoVO.class)
										  .add(Restrictions.eq("idMapa", new Integer(idMapa)))
										  .add(Restrictions.eq("tipoProceso", String.valueOf(tipoProceso)))
										  .setProjection(Projections.rowCount())
										  .list()
										  .get(0)).intValue();
		return contador;
	}
}
