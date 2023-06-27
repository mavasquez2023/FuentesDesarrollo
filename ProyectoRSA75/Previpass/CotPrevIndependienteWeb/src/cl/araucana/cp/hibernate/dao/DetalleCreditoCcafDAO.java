package cl.araucana.cp.hibernate.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.DetalleCreditoCcafVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.webServices.aporte.orqInput.service.vo.CreditoDetalleVO;
import cl.araucana.cp.webServices.aporte.orqInput.service.vo.OrqInputResultVO;

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
			this.session.save(detalleCreditoCcafVO);
			this.session.flush();
			
			log.info("Se guardan los detalles credito correctamente codigo barra : "  +detalleCreditoCcafVO.getIdCodigoBarra() + " idCredito : " +detalleCreditoCcafVO.getIdCredito());
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
				
				log.info("Se eliminan los registros detalles credito para el codigo barra " + idCodigoBarra);
		} catch (Exception ex)
		{
			log.error("Error en DetalleCreditoCcafVO.eliminaCreditoCcaf");
			throw new DaoException("Problemas eliminaCreditoCcaf", ex);
		}
	}
	public void guardarCreditoCCAFParseado(OrqInputResultVO inputResultVO, long codigoBarraNew) throws DaoException{
		
		CreditoDetalleVO[] creditoDetalleVOs =  inputResultVO.getCreditoVO().getCreditoDetalle();
		DetalleCreditoCcafVO creditoCcafVO =null;
		
		this.eliminaCreditoCcaf(codigoBarraNew);
		if(creditoDetalleVOs != null){
			for(int i = 0 ; creditoDetalleVOs.length > i ; i++){
			
				creditoCcafVO = new DetalleCreditoCcafVO();
				creditoCcafVO.setCapital(creditoDetalleVOs[i].getCapital());
				creditoCcafVO.setCodigoOficina(creditoDetalleVOs[i].getCodigoOficina());
				creditoCcafVO.setEstadoActual(creditoDetalleVOs[i].getEstadoCouta());
				creditoCcafVO.setFechaVencimiente(creditoDetalleVOs[i].getFechaVencimiento());
				creditoCcafVO.setFolioCredito(creditoDetalleVOs[i].getFolioCredito());
				creditoCcafVO.setGravamenes(creditoDetalleVOs[i].getGravamenes());
				creditoCcafVO.setIdCodigoBarra(codigoBarraNew);
				creditoCcafVO.setIdCredito(i+1);
				creditoCcafVO.setInteres(creditoDetalleVOs[i].getIntereses());
				creditoCcafVO.setLineaCredito(creditoDetalleVOs[i].getLineaCredito());
				creditoCcafVO.setMontoAbonado(creditoDetalleVOs[i].getMontoAbonado());
				creditoCcafVO.setMontoDescuenro(creditoDetalleVOs[i].getMontoDescuento());
				creditoCcafVO.setMulta(creditoDetalleVOs[i].getMultas());
				creditoCcafVO.setNumCuata(creditoDetalleVOs[i].getNumCuota());
				creditoCcafVO.setSeguros(creditoDetalleVOs[i].getSeguros());
				creditoCcafVO.setTotalCouta(creditoDetalleVOs[i].getTotalCoutas());
				creditoCcafVO.setValorCuota(creditoDetalleVOs[i].getValorCouta());
				creditoCcafVO.setMontoTotal(inputResultVO.getCreditoVO().getMonto());
				
				this.guardaDetalleCredito(creditoCcafVO);
			}
		}
	}
}
