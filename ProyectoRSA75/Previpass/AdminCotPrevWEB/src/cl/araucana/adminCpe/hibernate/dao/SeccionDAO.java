package cl.araucana.adminCpe.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


import cl.araucana.cp.distribuidor.hibernate.beans.DetalleSeccionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SeccionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) SeccionDao.java 1.2 10/06/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/

/**
 * @author jdelgado
 * 
 * @version 1.2
 */
public class SeccionDAO
{
	private static Logger log = Logger.getLogger(SeccionDAO.class);
	private Session session;
	private boolean loggear = true;

	public SeccionDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * 
	 * @param idCodigoBarra
	 * @return seccion
	 * @throws DaoException
	 */
	public List getSecciones(long idCodigoBarra) throws DaoException
	{
		try
		{
			Class tipo = SeccionVO.class;
			if (this.loggear) log.info("SeccionDAO:getSecciones:");
			return this.session.createCriteria(tipo).add(Restrictions.eq("idCodigoBarra", new Long(idCodigoBarra))).list();
		} catch (Exception ex)
		{
			log.error("Error en SeccionDAO.getSecciones");
			throw new DaoException("SeccionDAO:getSecciones:", ex);
		}
	}
	public List getDetalleSecciones(int tipoProceso) throws DaoException
	{
		try
		{
			Class tipo = DetalleSeccionVO.class;
			if (this.loggear) log.info("SeccionDAO:getDetalleSecciones:");
			this.session.flush();
			return this.session.createCriteria(tipo).add(Restrictions.eq("tipoPago", new Integer(tipoProceso))).list();
		} catch (Exception ex)
		{
			log.error("Error en SeccionDAO.getDetalleSecciones");
			throw new DaoException("SeccionDAO:getDetalleSecciones:", ex);
		}
	}
	public List getDetalleTipo(long idCodigoBarra, char tipo) throws DaoException
	{
		try
		{
			if (this.loggear) log.info("SeccionDAO:getDetalleTipo:");
			this.session.flush();
			List lista = new ArrayList();
			if(tipo == 'R'){
				lista =  this.session.createCriteria(DetalleSeccionVO.class)
					.add(Restrictions.eq("idCodigoBarra", new Long(idCodigoBarra)))
					.add(Restrictions.between("idTipoSeccion", new Integer(1), new Integer(19)))
					.list();
			}
			if(tipo == 'A'){
				lista =  this.session.createCriteria(DetalleSeccionVO.class)
					.add(Restrictions.eq("idCodigoBarra", new Long(idCodigoBarra)))
					.add(Restrictions.between("idTipoSeccion", new Integer(20), new Integer(39)))
					.list();
			}
			if(tipo == 'G'){
				lista =  this.session.createCriteria(DetalleSeccionVO.class)
					.add(Restrictions.eq("idCodigoBarra", new Long(idCodigoBarra)))
					.add(Restrictions.between("idTipoSeccion", new Integer(40), new Integer(59)))
					.list();
			}
			if(tipo == 'D'){
				lista =  this.session.createCriteria(DetalleSeccionVO.class)
					.add(Restrictions.eq("idCodigoBarra", new Long(idCodigoBarra)))
					.add(Restrictions.between("idTipoSeccion", new Integer(60), new Integer(79)))
					.list();
			}			
			return lista;
		} catch (Exception ex)
		{
			log.error("Error en SeccionDAO.getDetalleTipo");
			throw new DaoException("SeccionDAO:getDetalleTipo:", ex);
		}
	}
	public DetalleSeccionVO getDetalle(long idCodigoBarra, int tipo, int idDetalleSeccion ) throws DaoException
	{
		try
		{
			this.session.flush();
			return (DetalleSeccionVO)this.session.createCriteria(DetalleSeccionVO.class)
					.add(Restrictions.eq("idCodigoBarra", new Long(idCodigoBarra)))
					.add(Restrictions.eq("idTipoSeccion", new Integer(tipo)))
					.add(Restrictions.eq("idDetalleSeccion", new Integer(idDetalleSeccion)))
					.uniqueResult();
			
			
		} catch (Exception ex)
		{
			log.error("Error en SeccionDAO.getDetalleTipo");
			throw new DaoException("SeccionDAO:getDetalleTipo:", ex);
		}
	}
}
