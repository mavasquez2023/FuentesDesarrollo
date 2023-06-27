package cl.araucana.cp.hibernate.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.EntidadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapaCodigoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapaNominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoConceptoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) MapeosDao.java 1.16 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.16
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
	 * borra mapeos
	 * @param idMapaCod
	 * @param tipo
	 * @throws Exception
	 */
	public void borraMapeos(int idMapaCod, Class tipo) throws Exception 
	{	
		log.info("MapeosDAO:borraMapeos idMapaCod:" + idMapaCod + ":tipo:" + tipo.getName() + "::");
		
		List lista = this.session.createCriteria(tipo)
			.add(Restrictions.eq("idMapaCod", new Integer(idMapaCod)))
			.list();
		MapeoVO mapeo;
		for (Iterator it = lista.iterator(); it.hasNext();) 
		{
			mapeo = (MapeoVO) it.next();
			this.session.delete(mapeo);
		}
	}
	/**
	 * guarda mapeos
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
		} catch(Exception ex)
		{
			log.error("MapeosDAO:guardaMapeos error: " + ex.getCause().toString());
			throw new DaoException("Problemas en MapeosDAO.guardaMapeos", ex);
		}
	}
	/**
	 * mapeos
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
			
			List listaMapeos = this.session.createCriteria(tipo)
				.add(Restrictions.eq("idMapaCod", new Integer(idMapaCod)))
				.addOrder(Order.asc("id")).addOrder(Order.asc("codigo"))
				.list();

			MapeoVO mapeo;
			for (Iterator it = listaMapeos.iterator(); it.hasNext();) 
			{
				mapeo = (MapeoVO) it.next();
				mapeo.setEntidad((EntidadVO) this.session.load(tipoEntidad, new Integer(mapeo.getId())));
			}

			return listaMapeos;
		} catch(Exception ex) 
		{
			log.error("MapeosDAO:getMapeo error: " + ex.getCause());
			throw new DaoException("Problemas en MapeosDAO.getMapeo", ex);
		}
	}

	/**
	 * mapeos
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
			
			List listaMapeos = this.session.createCriteria(tipo)
				.add(Restrictions.eq("idMapaCod", new Integer(idMapaCod)))
				.add(Restrictions.ne("id", new Integer(idExcluir)))
				.addOrder(Order.asc("id")).addOrder(Order.asc("codigo"))
				.list();

			MapeoVO mapeo;
			for (Iterator it = listaMapeos.iterator(); it.hasNext();) {
				mapeo = (MapeoVO) it.next();
				mapeo.setEntidad((EntidadVO) this.session.load(tipoEntidad, new Integer(mapeo.getId())));
			}

			return listaMapeos;
		} catch(Exception ex) {
			log.error("MapeosDAO:getMapeo error: " + ex.getCause());
			throw new DaoException("Problemas en MapeosDAO.getMapeo", ex);
		}
	}
	/**
	 * mapa nomina 
	 * @param idMapaNom
	 * @return
	 * @throws DaoException
	 */
	public MapaNominaVO getMapaNomina(int idMapaNom) throws DaoException {
		
		try {
			log.info("MapeosDAO:getMapaNomina** idMapaNom: " + idMapaNom);

			return (MapaNominaVO) this.session.load(MapaNominaVO.class, new Integer(idMapaNom));
		} catch(Exception ex) {
			log.error("MapeosDAO:getMapaNomina error: " + ex);
			throw new DaoException("Problemas en MapeosDAO.getMapaNomina", ex);
		}
	}
	/**
	 * guarda mapa nomina
	 * @param mapaNom
	 * @throws DaoException
	 */
	public void guardarMapaNomina(MapaNominaVO mapaNom) throws DaoException 
	{
		try 
		{
			log.info("MapeosDAO:guardarMapaNomina::");
			this.session.save(mapaNom);
		} catch(Exception ex) {
			log.error("MapeosDAO:guardarMapaNomina error: " + ex);
			throw new DaoException("Problemas en MapeosDAO.guardarMapaNomina", ex);
		}
	}

	/**
	 * mapa codigo
	 * @param idMapaCod
	 * @return
	 * @throws DaoException
	 */
	public MapaCodigoVO getMapaCodigo(int idMapaCod) throws DaoException {
		try {
			log.info("MapeosDAO:getMapaCodigo::");

			return (MapaCodigoVO) this.session.load(MapaCodigoVO.class, new Integer(idMapaCod));
		} catch(Exception ex) {
			log.error("Error en MapeosDAO:getMapaCodigo: " + ex);
			throw new DaoException("Problemas en MapeosDAO.getMapaCodigo", ex);
		}
	}

	/**
	 * Obtiene un Mapeo de Concepto particular de acuerdo a su llave
	 * 
	 * @param tipoNomina
	 * @param idMapaCod
	 * @param idConcepto
	 * @return
	 * @throws DaoException
	 */
	public MapeoConceptoVO getMapeoConcepto(String tipoNomina, int idMapaNom, int idConcepto) throws DaoException{
		try {
			log.info("MapeosDAO:getMapeoConcepto::");
			return (MapeoConceptoVO) this.session.createCriteria(MapeoConceptoVO.class)
												 .add(Restrictions.eq("tipoProceso", tipoNomina))
												 .add(Restrictions.eq("idConcepto", new Integer(idConcepto)))
												 .add(Restrictions.eq("idMapa", new Integer(idMapaNom)))
												 .list()
												 .get(0);
		} catch(Exception ex) {
			log.error("Error en MapeosDAO:getMapeoConcepto: " + ex);
			throw new DaoException("Problemas en MapeosDAO.getMapeoConcepto", ex);
		}
	}

	/**
	 * Devuelve la cantidad máxima de conceptos. Para el caso de Nominas con Caracter de Separador.
	 * @param idMapa
	 * @param tipoProceso
	 * @return
	 */
	public int getCantidadDeConceptos(int idMapa, char tipoProceso) {
		int contador = 0;

		log.info("MapeosDAO:getCantidadDeConceptos: idMapa: " + "tipoProceso: " + tipoProceso + "::");
		contador = ((Integer) this.session.createCriteria(MapeoConceptoVO.class)
										  .add(Restrictions.eq("idMapa", new Integer(idMapa)))
										  .add(Restrictions.eq("tipoProceso", String.valueOf(tipoProceso)))
										  .setProjection(Projections.max("posicion"))
										  .list()
										  .get(0)).intValue();
		return contador;
	}
}
