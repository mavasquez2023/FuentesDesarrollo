package cl.araucana.cp.hibernate.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.DetalleLeasingCcafVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.webServices.aporte.orqInput.service.vo.LeasingDetalleVO;
import cl.araucana.cp.webServices.aporte.orqInput.service.vo.OrqInputResultVO;

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

			this.session.save(detalleLeasingCcafVO);
			this.session.flush();
			
			log.info("Se guardan los detalles leasing correctamente codigo barra : "  +detalleLeasingCcafVO.getIdCodigoBarra() + " idLeasing : " +detalleLeasingCcafVO.getIdLeasing());
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
				
				log.info("Se eliminan los registros detalles leasing  para el codigo barra " + idCodigoBarra);
		} catch (Exception ex)
		{
			log.error("Error en DetalleCreditoCcafVO.eliminLeasingCcaf");
			throw new DaoException("Problemas eliminLeasingCcaf", ex);
		}
	}
	public void guardaLeasingCCAFParseado (OrqInputResultVO inputResultVO, long codigoBarraNew) throws DaoException{
		
		LeasingDetalleVO[] leasingDetalleVOs = inputResultVO.getLeasingBO().getLeasingDetalle();
		DetalleLeasingCcafVO detalleLeasingCcafVO = null;
		
		this.eliminLeasingCcaf(codigoBarraNew);
		if(leasingDetalleVOs != null){
			for(int i =0 ; leasingDetalleVOs.length > i ; i++){
			
				detalleLeasingCcafVO = new DetalleLeasingCcafVO();
				detalleLeasingCcafVO.setCodigoOficina(leasingDetalleVOs[i].getCodigoOficina());
				detalleLeasingCcafVO.setFechaVencimiento(leasingDetalleVOs[i].getFechaVencimiento());
				detalleLeasingCcafVO.setIdCodigoBarra(codigoBarraNew);
				detalleLeasingCcafVO.setIdLeasing(i+1);
				detalleLeasingCcafVO.setMonto(leasingDetalleVOs[i].getMonto());
				detalleLeasingCcafVO.setMontoUF(leasingDetalleVOs[i].getMontoUF());
				
				this.guardaDetalleLeasing(detalleLeasingCcafVO);
			}
		}
}
}