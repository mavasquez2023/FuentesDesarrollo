package cl.araucana.cp.hibernate.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.DetalleAporteCcafVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.webServices.aporte.orqInput.service.vo.AporteDetalleVO;
import cl.araucana.cp.webServices.aporte.orqInput.service.vo.OrqInputResultVO;

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
			
			this.session.save(detalleAporteCcafVO);
			this.session.flush();
			
			log.info("Se guardan los detalles aporte correctamente codigo barra : "  +detalleAporteCcafVO.getIdCodigoBarra() + " idAporte : " +detalleAporteCcafVO.getIdAporte());
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
					
				log.info("Se eliminan los registros detalles aporte para codigo barra " + idCodigoBarra);
		} catch (Exception ex)
		{
			log.error("Error en DetalleCreditoCcafVO.eliminAporteCcaf");
			throw new DaoException("Problemas eliminAporteCcaf", ex);
		}
	}
	public void guardaAporteCCAFParseado(OrqInputResultVO inputResultVO, long codigoBarraNew) throws DaoException{
		
		AporteDetalleVO[] aporteDetalleVOs = inputResultVO.getAporteVO().getAporteDetalle();
				
		DetalleAporteCcafVO detalleAporteCcafVO = null;
		
		this.eliminAporteCcaf(codigoBarraNew);
		if(aporteDetalleVOs != null){
			for(int i = 0 ; aporteDetalleVOs.length > i ; i++){
				
				detalleAporteCcafVO=new DetalleAporteCcafVO();
				detalleAporteCcafVO.setFechaVencimiento(aporteDetalleVOs[i].getFechaVencimiento());
				detalleAporteCcafVO.setIdAporte(i+1);
				detalleAporteCcafVO.setIdCodigoBarra(codigoBarraNew);
				detalleAporteCcafVO.setInteresReajuste(aporteDetalleVOs[i].getInteresesReajuste());
				detalleAporteCcafVO.setMonto(aporteDetalleVOs[i].getMonto());
				detalleAporteCcafVO.setRentaPromedio(aporteDetalleVOs[i].getRentaPromedio());
				detalleAporteCcafVO.setMontoTotal(inputResultVO.getAporteVO().getMonto());
				
				this.guardaDetalleAporte(detalleAporteCcafVO);
			}
		}
	}
}
