package cl.araucana.adminCpe.hibernate.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.DetalleAporteCcafVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

public class DetalleAporteCcafDAO {
	
	private static Logger log = Logger.getLogger(DetalleAporteCcafDAO.class);
	private Session session;

	public DetalleAporteCcafDAO(Session session)
	{
		this.session = session;
	}
	
	/**
	 * guarda Detalle Aporte CCAF
	 * 
	 * @param DetalleAporteCcafVO
	 * @throws DaoException
	 */
	public void guardaDetalleAporte(DetalleAporteCcafVO detalleAporteCcafVO) throws DaoException
	{
		try
		{
			log.info("DetalleAporteCcafDAO:guardaDetalleAporte");
			this.session.save(detalleAporteCcafVO);
			this.session.flush();
		} catch (Exception ex)
		{
			log.error("ERROR DetalleAporteCcafDAO:guardaDetalleAporte:" + ex);
			throw new DaoException("Problemas guardaDetalleAporte", ex);
		}
	}
	/**
	 * DetalleAporte
	 * 
	 * @param idCodigoBarra
	 * @return List
	 * @throws DaoException
	 */
	public List getDetalleAporte(long idCodigoBarra) throws DaoException
	{
		try
		{
			return this.session.createCriteria(DetalleAporteCcafVO.class).add(Restrictions.eq("idCodigoBarra", new Long(idCodigoBarra))).list();
			 
		} catch (Exception ex)
		{
			log.error("Error en DetalleAporteCcafDAO.getDetalleAporte");
			throw new DaoException("Problemas getDetalleAporte", ex);
		}
	}
	/**
	 * Elimina Aporte CCAF
	 * 
	 * @param idCodigoBarra
	 * @return 
	 * @throws DaoException
	 */
	public void eliminAporteCcaf(long idCodigoBarra) throws DaoException
	{
		try
		{
			List listAporte = this.getDetalleAporte(idCodigoBarra);
			
			for (Iterator itAporte = listAporte.iterator(); itAporte.hasNext();)
				this.session.delete(itAporte.next());
				this.session.flush();
					
		} catch (Exception ex)
		{
			log.error("Error en DetalleCreditoCcafVO.eliminAporteCcaf");
			throw new DaoException("Problemas eliminAporteCcaf", ex);
		}
	}
	public void eliminAporteCcafTodo() throws DaoException
	{
		try
		{			
				Date ini = new Date();
				
				Query query = this.session.createQuery(" DELETE FROM DetalleAporteCcafVO ");  
														   
				int count = query.executeUpdate();
					
				Date fin = new Date();
				
					log.info("Se han eliminado  : "+ count + " registros de la tabla DetalleAporteCcafVO :\t\tdiff:" + (fin.getTime() - ini.getTime()) + "::");
			
		} catch (Exception ex)
		{
			log.error("Error en DetalleAporteCcafDAO.eliminAporteCcafTodo");
			throw new DaoException("Error en DetalleAporteCcafDAO.eliminAporteCcafTodo", ex);
		}
	}
}
