package cl.araucana.spl.mgr;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.Estado;
import cl.araucana.spl.beans.FiltroInconsistencias;
import cl.araucana.spl.beans.Inconsistencia;
import cl.araucana.spl.beans.MedioPago;
import cl.araucana.spl.beans.ResumenInconsistencias;
import cl.araucana.spl.dao.BitacoraDAO;
import cl.araucana.spl.dao.EstadoDAO;
import cl.araucana.spl.dao.MedioPagoDAO;
import cl.araucana.spl.dao.PagoDAO;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.util.Utiles;

import com.bh.paginacion.DataPage;
import com.ibatis.dao.client.DaoManager;

public class InconsistenciasManager {
	private static final Logger logger = Logger.getLogger(InconsistenciasManager.class);

	private EstadoDAO estadoDAO;
	private PagoDAO pagoDAO;
	private BitacoraDAO bitacoraDAO;
	private MedioPagoDAO medioPagoDAO;
	
	public InconsistenciasManager() {
		DaoManager mgr = DaoConfig.getDaoManager();
		estadoDAO = (EstadoDAO) mgr.getDao(EstadoDAO.class);
		pagoDAO = (PagoDAO) mgr.getDao(PagoDAO.class);
		bitacoraDAO = (BitacoraDAO) mgr.getDao(BitacoraDAO.class);
		medioPagoDAO = (MedioPagoDAO) mgr.getDao(MedioPagoDAO.class);
	}
	
	public List getEstados() {
		return estadoDAO.getEstados();
	}
	
	/**
	 * Retorna los estados que se pueden modificar.
	 * @return Lista de objetos Estado {@link Estado}
	 */
	public List getEstadosModificables() {
		List result = new ArrayList();
		List estados = estadoDAO.getEstados();
		for (Iterator iter = estados.iterator(); iter.hasNext();) {
			Estado estado = (Estado) iter.next();
			BigDecimal idEstado = estado.getId();
			if (Constants.ESTADO_PAGO_INCONSISTENTE.equals(idEstado)
				|| Constants.ESTADO_PAGO_CORREGIDO.equals(idEstado)) {
				result.add(estado);
			}
		}
		return result;
	}
	
	/**
	 * Consulta el estado por su id.
	 * @param idEstado
	 * @return
	 */
	public Estado getEstadoById(BigDecimal idEstado) {
		return estadoDAO.getEstadoById(idEstado);
	}

	public DataPage getPaginaInconsistencias(FiltroInconsistencias filtro) throws PagoEnLineaException {
		if (logger.isDebugEnabled()) {
			logger.debug("Buscando paginado por filtro: " + filtro);
		}
		FiltroInconsistencias filtroWorker = new FiltroInconsistencias();
		Utiles.copyProperties(filtro, filtroWorker);

		if (logger.isDebugEnabled()) {
			logger.debug("Buscando previos segun " + filtroWorker);
		}
		List previosInvertidos = pagoDAO.findInconsistenciasPaginaPreviaInvertida(filtroWorker);
		long offsetPrevio = filtro.getOffset();
		if (previosInvertidos.size() > 0) {
			Inconsistencia in = (Inconsistencia) previosInvertidos.get(previosInvertidos.size() - 1);
			offsetPrevio = in.getIdPago().longValue();
		}

		filtroWorker.setLimit(filtroWorker.getLimit()+1);
		if (logger.isDebugEnabled()) {
			logger.debug("Buscando pagina de inconsistencias segun " + filtroWorker);
		}
		List pagos = pagoDAO.findInconsistenciasPaginadas(filtroWorker);
		if (logger.isDebugEnabled()) {
			logger.debug("Registros encontrados: " + pagos.size());
		}
		
		
		long offsetSiguiente = 0;
		if (pagos.size() == filtroWorker.getLimit()) {
			Inconsistencia siguiente = (Inconsistencia) pagos.remove(pagos.size()-1);
			offsetSiguiente = siguiente.getIdPago().longValue();
		}

		if (pagos.size() > 0) {
			setCantidadesBitacora(pagos);
		}
		
		DataPage page = new DataPage(
			pagos,
			offsetPrevio < filtro.getOffset(),
			offsetSiguiente > filtro.getOffset(),
			offsetPrevio, offsetSiguiente);
		
		return page;
	}


	public ResumenInconsistencias getResumenInconsistencias(FiltroInconsistencias filtro) {
		logger.debug("Recuperando resumen");
		ResumenInconsistencias resumen = new ResumenInconsistencias();

		BigDecimal montoPago = pagoDAO.getSumMontoPagoByFiltro(filtro);
		if (montoPago == null) { montoPago = new BigDecimal(0); }
		resumen.setMontoPago(montoPago);
		
		BigDecimal montoBanco = pagoDAO.getSumMontoBancoByFiltro(filtro);
		if (montoBanco == null) { montoBanco = new BigDecimal(0); }
		resumen.setMontoBanco(montoBanco);
		
		BigDecimal montoDescuadre = montoBanco.subtract(montoPago);
		resumen.setMontoDescuadre(montoDescuadre);

		BigDecimal cantidadPago = pagoDAO.getCountPagoByFiltro(filtro);
		if (cantidadPago == null) { cantidadPago = new BigDecimal(0); }
		resumen.setCantidadPago(cantidadPago);
		
		BigDecimal cantidadBanco = pagoDAO.getCountBancoByFiltro(filtro);
		if (cantidadBanco == null) { cantidadBanco = new BigDecimal(0); }
		resumen.setCantidadBanco(cantidadBanco);
		
		BigDecimal cantidadDescuadre = cantidadBanco.subtract(cantidadPago);
		resumen.setCantidadDescuadre(cantidadDescuadre);
		
		MedioPago medioPago = medioPagoDAO.getMedioPagoById(filtro.getBanco());
		resumen.setMedioPago(medioPago);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Monto pago: " + montoPago);
			logger.debug("Monto banco: " + montoBanco);
			logger.debug("montoDescuadre: " + montoDescuadre);
			logger.debug("cantidadPago: " + cantidadPago);
			logger.debug("cantidadBanco: " + cantidadBanco);
			logger.debug("cantidadDescuadre: " + cantidadDescuadre);
			logger.debug("Mediopago: " + medioPago);
		}
		
		return resumen;
	}

	private void setCantidadesBitacora(List pagos) {
		List counts = bitacoraDAO.findCountBitacoraByPagos(getIds(pagos));
		HashMap hcounts = new HashMap();
		for (Iterator it = counts.iterator(); it.hasNext(); ) {
			HashMap el = (HashMap) it.next();
			BigDecimal id = (BigDecimal)el.get("ID");
			Integer cantidad = (Integer) el.get("CAMBIOS");
			hcounts.put(new BigDecimal(id.intValue()), cantidad);
		}
		for (Iterator it = pagos.iterator(); it.hasNext(); ) {
			Inconsistencia i = (Inconsistencia) it.next();
			Integer cantidad = (Integer) hcounts.get(i.getIdPago());
			i.setCantidadCambios(cantidad != null ? cantidad : new Integer(0));
		}
	}

	private List getIds(List pagos) {
		List ids = new ArrayList(pagos.size());
		for (Iterator it = pagos.iterator(); it.hasNext(); ) {
			Inconsistencia i = (Inconsistencia) it.next();
			ids.add(i.getIdPago());
		}
		return ids;
	}

}
