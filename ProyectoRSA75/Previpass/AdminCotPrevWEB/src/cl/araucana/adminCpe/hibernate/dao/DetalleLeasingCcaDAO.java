package cl.araucana.adminCpe.hibernate.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.DetalleLeasingCcafVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

public class DetalleLeasingCcaDAO {
	
	private static Logger log = Logger.getLogger(DetalleLeasingCcaDAO.class);
	private Session session;

	public DetalleLeasingCcaDAO(Session session)
	{
		this.session = session;
	}
	
	/**
	 * guarda Leasing CCAF
	 * 
	 * @param DetalleAporteCcafVO
	 * @throws DaoException
	 */
	public void guardaDetalleLeasing(DetalleLeasingCcafVO detalleLeasingCcafVO ) throws DaoException
	{
		try
		{
			log.info("DetalleLeasingCcaDAO:guardaDetalleLeasing");
			this.session.save(detalleLeasingCcafVO);
			this.session.flush();
		} catch (Exception ex)
		{
			log.error("ERROR DetalleLeasingCcaDAO:guardaDetalleLeasing:" + ex);
			throw new DaoException("Problemas guardaDetalleLeasing", ex);
		}
	}
	/**
	 * DetalleLeasing
	 * 
	 * @param idCodigoBarra
	 * @return List
	 * @throws DaoException
	 */
	public List getDetalleLeasing(long idCodigoBarra) throws DaoException
	{
		try
		{
			return this.session.createCriteria(DetalleLeasingCcafVO.class).add(Restrictions.eq("idCodigoBarra", new Long(idCodigoBarra))).list();
			 
		} catch (Exception ex)
		{
			log.error("Error en DetalleLeasingCcaDAO.guardaDetalleLeasing");
			throw new DaoException("Problemas guardaDetalleLeasing", ex);
		}
	}
	/**
	 * Elimina Leasing CCAF
	 * 
	 * @param idCodigoBarra
	 * @return 
	 * @throws DaoException
	 */
	public void eliminLeasingCcaf(long idCodigoBarra) throws DaoException
	{
		try
		{
			List listLeasing= this.getDetalleLeasing(idCodigoBarra);
			
			for (Iterator itLeasing = listLeasing.iterator(); itLeasing.hasNext();)
				this.session.delete(itLeasing.next());
				this.session.flush();
						
		} catch (Exception ex)
		{
			log.error("Error en DetalleCreditoCcafVO.eliminLeasingCcaf");
			throw new DaoException("Problemas eliminLeasingCcaf", ex);
		}
	}
	public void eliminLeasingCcafTodo() throws DaoException
	{
		try
		{			
				Date ini = new Date();
				
				Query query = this.session.createQuery(" DELETE FROM DetalleLeasingCcafVO ");  
														   
				int count = query.executeUpdate();
					
				Date fin = new Date();
				
					log.info("Se han eliminado  : "+ count + " registros de la tabla DetalleLeasingCcafVO :\t\tdiff:" + (fin.getTime() - ini.getTime()) + "::");
			
		} catch (Exception ex)
		{
			log.error("Error en DetalleLeasingCcaDAO.eliminLeasingCcafTodo");
			throw new DaoException("Error en DetalleLeasingCcaDAO.eliminLeasingCcafTodo", ex);
		}
	}
}