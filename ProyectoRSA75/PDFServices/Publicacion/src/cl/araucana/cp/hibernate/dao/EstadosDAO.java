package cl.araucana.cp.hibernate.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.hibernate.beans.EstadoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EstadoVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) EstadosDao.java 1.8 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.8
 */
public class EstadosDAO
{
	private static Logger log = Logger.getLogger(EstadosDAO.class);
	Session session;

	public EstadosDAO(Session session)
	{
		this.session = session;
	}
	/**
	 * carga estados
	 * @param tipo
	 * @return
	 * @throws DaoException
	 */
	public HashMap cargaEstados(Class tipo) throws DaoException
	{
		try
		{
			log.info("EstadosDAO:cargaEstados");
			log.info(tipo);
			List result = this.session.createCriteria(tipo).setCacheable(true).list();
			if (result != null && result.size() > 0)
			{
				HashMap resultHM = new HashMap();
				log.info("\n\n\ncargaEstados::");
				for (Iterator it = result.iterator(); it.hasNext();)
				{
					EstadoVO e = (EstadoVO) it.next();
					log.info("cargando:" + e.getId() + ":" + e.getDescripcion() + "::");
					resultHM.put("" + e.getId(), e.getDescripcion());
				}
				return resultHM;
			}
			throw new DaoException("ERROR EstadosDAO:cargaEstados: no se encontraron parametros para los estados:" + tipo.getName());
		} catch (Exception e)
		{
			log.error("EstadosDAO:cargaEstados error:" + e.getMessage() + ":" + e.getClass());
			throw new DaoException("Problemas en EstadosDAO.cargaEstados", e);
		}
	}
	/**
	 * estado
	 * @param idEstado
	 * @return
	 * @throws DaoException
	 */
	public EstadoVO getEstado(int idEstado) throws DaoException
	{

		try
		{
			return (EstadoVO) this.session.load(EstadoNominaVO.class, new Integer(idEstado));
		} catch (Exception ex)
		{
			log.error("Error en EstadosDAO.getEstado");
			throw new DaoException("Error en EstadosDAO:getEstado", ex);
		}
	}
	/**
	 * estado
	 * @return
	 * @throws DaoException
	 */
	public Collection getEstados() throws DaoException
	{
		try
		{
			return this.session.createCriteria(EstadoNominaVO.class).list();
		} catch (Exception ex)
		{
			log.error("Error en EstadosDAO.getEstados():", ex);
			throw new DaoException("Error en EstadosDAO:getEstados()", ex);
		}

	}

}
