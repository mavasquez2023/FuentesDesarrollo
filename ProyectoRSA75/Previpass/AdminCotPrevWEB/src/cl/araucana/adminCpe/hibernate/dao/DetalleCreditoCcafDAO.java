package cl.araucana.adminCpe.hibernate.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.DetalleCreditoCcafVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

public class DetalleCreditoCcafDAO {
	
	private static Logger log = Logger.getLogger(DetalleCreditoCcafDAO.class);
	private Session session;

	public DetalleCreditoCcafDAO(Session session)
	{
		this.session = session;
	}
	
	/**
	 * guarda Detalle Credito CCAF
	 * 
	 * @param DetalleAporteCcafVO
	 * @throws DaoException
	 */
	public void guardaDetalleCredito(DetalleCreditoCcafVO detalleCreditoCcafVO) throws DaoException
	{
		try
		{
			log.info("DetalleCreditoCcafDAO:guardaDetalleCredito");
			this.session.save(detalleCreditoCcafVO);
			this.session.flush();
		} catch (Exception ex)
		{
			log.error("ERROR DetalleCreditoCcafDAO:guardaDetalleCredito:" + ex);
			throw new DaoException("Problemas guardaDetalleCredito", ex);
		}
	}
	/**
	 * DetalleAporte
	 * 
	 * @param idCodigoBarra
	 * @return List
	 * @throws DaoException
	 */
	public List getDetalleCredito(long idCodigoBarra) throws DaoException
	{
		try
		{
			return this.session.createCriteria(DetalleCreditoCcafVO.class).add(Restrictions.eq("idCodigoBarra", new Long(idCodigoBarra))).list();
			 
		} catch (Exception ex)
		{
			log.error("Error en DetalleCreditoCcafVO.getDetalleCredito");
			throw new DaoException("Problemas getDetalleCredito", ex);
		}
	}
	
	/**
	 * Elimina Credito CCAF
	 * 
	 * @param idCodigoBarra
	 * @return 
	 * @throws DaoException
	 */
	public void eliminaCreditoCcaf(long idCodigoBarra) throws DaoException
	{
		try
		{
			List listCredito =this.getDetalleCredito(idCodigoBarra);
			
			for (Iterator itCredito = listCredito.iterator(); itCredito.hasNext();)
				this.session.delete(itCredito.next());
				this.session.flush();
			
		} catch (Exception ex)
		{
			log.error("Error en DetalleCreditoCcafVO.eliminaCreditoCcaf");
			throw new DaoException("Problemas eliminaCreditoCcaf", ex);
		}
	}
	public void eliminaCreditoCcafTodo() throws DaoException
	{
		try
		{			
				Date ini = new Date();
				
				Query query = this.session.createQuery(" DELETE FROM DetalleCreditoCcafVO ");  
														   
				int count = query.executeUpdate();
					
				Date fin = new Date();
				
					log.info("Se han eliminado  : "+ count + " registros de la tabla DetalleCreditoCcafVO :\t\tdiff:" + (fin.getTime() - ini.getTime()) + "::");
			
		} catch (Exception ex)
		{
			log.error("Error en DetalleCreditoCcafDAO.eliminaCreditoCcafTodo");
			throw new DaoException("Error en DetalleCreditoCcafDAO.eliminaCreditoCcafTodo", ex);
		}
	}
}
