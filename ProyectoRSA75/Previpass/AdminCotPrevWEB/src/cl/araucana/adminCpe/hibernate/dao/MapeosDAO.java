package cl.araucana.adminCpe.hibernate.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapaCodigoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapaNominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

/*
 * @(#) MapeosDao.java 1.10 10/06/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */

/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.10
 */
public class MapeosDAO
{
	private static Logger log = Logger.getLogger(MapeosDAO.class);
	private Session session;

	public MapeosDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * eliminar mapeos
	 * 
	 * @param idMapaCod
	 * @param tipo
	 * @throws Exception
	 */
	public void borraMapeos(int idMapaCod, Class tipo) throws Exception
	{
		log.info("MapeosDAO:borraMapeos");

		List lista = this.session.createCriteria(tipo).add(Restrictions.eq("idMapaCod", new Integer(idMapaCod))).list();
		MapeoVO mapeo;
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			mapeo = (MapeoVO) it.next();
			this.session.delete(mapeo);
		}
	}

	/**
	 * guardar mapeos
	 * 
	 * @param idMapaCod
	 * @param listaGuardar
	 * @param tipo
	 * @throws DaoException
	 */
	public void guardaMapeos(int idMapaCod, List listaGuardar, Class tipo) throws DaoException
	{
		try
		{
			log.info("MapeosDAO:guardaMapeo");
			this.borraMapeos(idMapaCod, tipo);
			this.session.flush();

			MapeoVO mapeo;
			for (Iterator it = listaGuardar.iterator(); it.hasNext();)
			{
				mapeo = (MapeoVO) it.next();
				this.session.save(mapeo);
			}
		} catch (Exception ex)
		{
			log.error("MapeosDAO:guardaMapeos error: " + ex.getCause().toString());
			throw new DaoException("Problemas en MapeosDAO.guardaMapeos", ex);
		}
	}

	/**
	 * guardar mapeos
	 * 
	 * @param mapeo
	 * @throws DaoException
	 */
	public void saveMapeo(MapeoVO mapeo) throws DaoException
	{
		try
		{
			// log.info("MapeosDAO:saveMapeo:IdMapaCod:" + mapeo.getIdMapaCod() + ":id:" + mapeo.getId() + ":Codigo:" + mapeo.getCodigo() + "::");
			this.session.save(mapeo);
		} catch (Exception ex)
		{
			log.error("MapeosDAO:saveMapeo error:***");
			throw new DaoException("Problemas en MapeosDAO.saveMapeo", ex);
		}
	}

	/**
	 * listar mapeos
	 * 
	 * @param idMapaCod
	 * @param tipo
	 * @param tipoEntidad
	 * @return
	 * @throws DaoException
	 */
	public List getMapeos(int idMapaCod, Class tipo, Class tipoEntidad) throws DaoException
	{
		try
		{
			log.info("MapeosDAO:getMapeos idMapaCod: " + idMapaCod + ", tipo: " + tipo.getName() + ", tipoEntidad: " + tipoEntidad.getName() + "::");
			List listaMapeos;
			if (idMapaCod != -1)
			{
				listaMapeos = this.session.createCriteria(tipo).add(Restrictions.eq("idMapaCod", new Integer(idMapaCod))).addOrder(Order.asc("id")).addOrder(Order.asc("codigo")).list();
			} else
			{
				listaMapeos = this.session.createCriteria(tipo).addOrder(Order.asc("id")).addOrder(Order.asc("codigo")).list();
			}

			MapeoVO mapeo;
			for (Iterator it = listaMapeos.iterator(); it.hasNext();)
			{
				mapeo = (MapeoVO) it.next();
				mapeo.setEntidad((EntidadVO) this.session.load(tipoEntidad, new Integer(mapeo.getId())));
			}

			return listaMapeos;
		} catch (Exception ex)
		{
			log.error("MapeosDAO:getMapeo error: " + ex.getCause());
			throw new DaoException("Problemas en MapeosDAO.getMapeo", ex);
		}
	}

	/**
	 * listar mapeos
	 * 
	 * @param idMapaCod
	 * @param tipo
	 * @param tipoEntidad
	 * @param idExcluir
	 * @return
	 * @throws DaoException
	 */
	public List getMapeos(int idMapaCod, Class tipo, Class tipoEntidad, int idExcluir) throws DaoException
	{

		try
		{
			log.info("MapeosDAO:getMapeos idMapaCod: " + idMapaCod + ", tipo: " + tipo.getName() + ", tipoEntidad: " + tipoEntidad.getName() + ":idExcluir:" + idExcluir + "::");

			List listaMapeos;
			if (idMapaCod != -1)
			{
				listaMapeos = this.session.createCriteria(tipo).add(Restrictions.eq("idMapaCod", new Integer(idMapaCod))).add(Restrictions.ne("id", new Integer(idExcluir))).addOrder(Order.asc("id"))
						.addOrder(Order.asc("codigo")).list();
			} else
				listaMapeos = this.session.createCriteria(tipo).add(Restrictions.ne("id", new Integer(idExcluir))).addOrder(Order.asc("id")).addOrder(Order.asc("codigo")).list();

			MapeoVO mapeo;
			for (Iterator it = listaMapeos.iterator(); it.hasNext();)
			{
				mapeo = (MapeoVO) it.next();
				mapeo.setEntidad((EntidadVO) this.session.load(tipoEntidad, new Integer(mapeo.getId())));
			}

			return listaMapeos;
		} catch (Exception ex)
		{
			log.error("MapeosDAO:getMapeo error: " + ex.getCause());
			throw new DaoException("Problemas en MapeosDAO.getMapeo", ex);
		}
	}

	/**
	 * mapa nomina
	 * 
	 * @param idMapaNom
	 * @return
	 * @throws DaoException
	 */
	public MapaNominaVO getMapaNomina(int idMapaNom) throws DaoException
	{
		try
		{
			log.info("MapeosDAO:getMapaNomina** idMapaNom: " + idMapaNom);

			return (MapaNominaVO) this.session.load(MapaNominaVO.class, new Integer(idMapaNom));
		} catch (Exception ex)
		{
			log.error("MapeosDAO:getMapaNomina error: " + ex);
			throw new DaoException("Problemas en MapeosDAO.getMapaNomina", ex);
		}
	}

	/**
	 * guardar mapa nomina
	 * 
	 * @param mapaNom
	 * @return
	 * @throws DaoException
	 */
	public Integer guardarMapaNomina(MapaNominaVO mapaNom) throws DaoException
	{

		try
		{
			List l = this.session.createCriteria(MapaNominaVO.class).setProjection(Projections.max("idMapaNom")).list();
			int oldId = ((Integer) l.get(0)).intValue();

			mapaNom.setIdMapaNom(oldId + 1);
			log.info("MapeosDAO:guardarMapaNomina::");

			return (Integer) this.session.save(mapaNom);
		} catch (Exception ex)
		{
			log.error("MapeosDAO:guardarMapaNomina error: " + ex);
			throw new DaoException("Problemas en MapeosDAO.guardarMapaNomina", ex);
		}
	}

	/**
	 * guardar mapa nomina alternativa
	 * 
	 * @param mapaNom
	 * @param editar
	 * @return
	 * @throws DaoException
	 */
	public Integer guardarMapaNominaAlternativa(MapaNominaVO mapaNom, boolean editar) throws DaoException
	{

		try
		{
			List l = this.session.createCriteria(MapaNominaVO.class).setProjection(Projections.max("idMapaNom")).list();
			int oldId = ((Integer) l.get(0)).intValue();
			if (!editar)
				mapaNom.setIdMapaNom(oldId + 1);
			log.info("MapeosDAO:guardarMapaNomina::");

			return (Integer) this.session.save(mapaNom);
		} catch (Exception ex)
		{
			log.error("MapeosDAO:guardarMapaNomina error: " + ex);
			throw new DaoException("Problemas en MapeosDAO.guardarMapaNomina", ex);
		}
	}

	/**
	 * mapa codigo
	 * 
	 * @param idMapaCod
	 * @return
	 * @throws DaoException
	 */
	public MapaCodigoVO getMapaCodigo(int idMapaCod) throws DaoException
	{
		try
		{
			log.info("MapeosDAO:getMapaCodigo::");

			return (MapaCodigoVO) this.session.load(MapaCodigoVO.class, new Integer(idMapaCod));
		} catch (Exception ex)
		{
			log.error("Error en MapeosDAO:getMapaCodigo: " + ex);
			throw new DaoException("Problemas en MapeosDAO.getMapaCodigo", ex);
		}
	}

	/**
	 * guarda mapa codigo
	 * 
	 * @param mapaCod
	 * @return
	 * @throws DaoException
	 */
	public Integer saveMapaCodigo(MapaCodigoVO mapaCod) throws DaoException
	{
		try
		{
			List l = this.session.createCriteria(MapaCodigoVO.class).setProjection(Projections.max("idMapaCodigo")).list();
			int oldId = ((Integer) l.get(0)).intValue();
			mapaCod.setIdMapaCodigo(oldId + 1);
			log.info("MapeosDAO:saveMapaCodigo::" + mapaCod.toString());
			Integer id = (Integer) this.session.save(mapaCod);
			return id;
		} catch (Exception ex)
		{
			log.error("Error en MapeosDAO:saveMapaCodigo: " + ex);
			throw new DaoException("Problemas en MapeosDAO.saveMapaCodigo", ex);
		}
	}

	/**
	 * guarda mapeo concepto
	 * 
	 * @param mapaCod
	 * @throws DaoException
	 */
	public void saveMapeoConcepto(MapeoConceptoVO mapaCod) throws DaoException
	{
		try
		{
			// log.info("MapeosDAO:saveMapeoConcepto::" + mapaCod.toString());
			this.session.save(mapaCod);
		} catch (Exception ex)
		{
			log.error("Error en MapeosDAO:saveMapeoConcepto: " + ex);
			throw new DaoException("Problemas en MapeosDAO.saveMapeoConcepto ", ex);
		}
	}

	/**
	 * Actualiza el tipo de separador y el caracter separador para un grupo de convenio
	 * 
	 * @param tipoNomina
	 * @param idMapaCod
	 * @param caracterSeparador
	 * @throws DaoException
	 */
	public void actualizaMapeoConcepto(char tipoNomina, int idMapaCod, int tipoSeparador, Character caracterSeparador) throws DaoException
	{
		try
		{
			Query query = this.session.createQuery("UPDATE " + MapeoConceptoVO.class.getName() + " map " +
													  "SET map.caracterSeparador = ? " +
													    ", map.tipoSeparador = ? " +
													"WHERE map.idMapa = ? " + 
												      "AND map.tipoProceso = ?");
			Character charSeparador = tipoSeparador == Constants.TIPO_SEPARADOR_CARACTER ?  caracterSeparador : null;
			query.setParameter(0, charSeparador, Hibernate.CHARACTER);
			query.setInteger(1, tipoSeparador);
			query.setInteger(2, idMapaCod);
			query.setCharacter(3, tipoNomina);
			query.executeUpdate();
		} catch (Exception ex)
		{
			log.error("Error en MapeosDAO:actualizaMapeoConcepto: " + ex);
			throw new DaoException("Problemas en MapeosDAO.actualizaMapeoConcepto ", ex);
		}
	}
}
