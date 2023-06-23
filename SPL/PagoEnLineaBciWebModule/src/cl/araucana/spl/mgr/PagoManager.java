package cl.araucana.spl.mgr;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.Bitacora;
import cl.araucana.spl.beans.Convenio;
import cl.araucana.spl.beans.DetallePago;
import cl.araucana.spl.beans.Estado;
import cl.araucana.spl.beans.FiltroRendicion;
import cl.araucana.spl.beans.Folio;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.WrapperXmlMedioPago;
import cl.araucana.spl.dao.BitacoraDAO;
import cl.araucana.spl.dao.PagoDAO;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.util.FechaContable;
import cl.araucana.spl.util.Nulls;
import cl.araucana.spl.util.Renderer;
import cl.araucana.spl.util.Utiles;

import com.ibatis.dao.client.DaoManager;

public class PagoManager {
	private final Logger logger = Logger.getLogger(PagoManager.class); 
	
	private PagoDAO pagoDAO;
	private BitacoraDAO bitacoraDAO;
	private SistemaManager sistemaManager;
	private MedioPagoManager medioManager;
	
	public static final ResourceBundle resourceApp = ResourceBundle.getBundle("cl.araucana.spl.resources.ApplicationResources");
	
	public PagoManager() {
		DaoManager daoManager = DaoConfig.getDaoManager();
		pagoDAO = (PagoDAO)daoManager.getDao(PagoDAO.class);
		bitacoraDAO = (BitacoraDAO) daoManager.getDao(BitacoraDAO.class);
		sistemaManager = new SistemaManager();
		medioManager = new MedioPagoManager();
	}

	public Pago setPago(WrapperXmlMedioPago wxml, String banco, String origen) throws PagoEnLineaException {
		Pago pago = new Pago();
		
		pago.setConvenio(medioManager.getConvenio(banco));
		
		pago.setEstado(new Estado(Constants.ESTADO_PAGO_SIN_CUADRAR));

		Date fecha = new Date(System.currentTimeMillis());
		pago.setFechaInicio(fecha);
		
		FechaContable contable = new FechaContable();
		pago.setFechaContable(contable.getFechaContable(fecha));

		pago.setGlosa(wxml.getGlosa());
		pago.setMonto(wxml.getMontoTotal());
		if (Nulls.isNotNull(wxml.getPagador())) {
			pago.setPagador(wxml.getPagador());
		}
		pago.setSistema(sistemaManager.getSistemaByCodigo(origen));
		pago.setUrlRetornoOrigen(wxml.getUrlRetorno());
		pago.setUrlNotificacionOrigen(wxml.getUrlNotificacion());
		setDetallePago(wxml, pago);
		
		return pago;
	}

	private void setDetallePago(WrapperXmlMedioPago wxml, Pago pago) {
		for (Iterator it = wxml.getFoliosBeans().iterator(); it.hasNext(); ) {
			Folio folio = (Folio) it.next();

			DetallePago detalle = new DetallePago();
			detalle.setFolio(folio.getNumero());
			detalle.setDescripcion(folio.getDetalle());
			detalle.setMonto(folio.getMonto());
			detalle.setPago(pago);
			pago.getDetalles().add(detalle);
		}
	}

	
	/**
	 * Busca un pago segun su id
	 * @param idPago
	 * @author malvarez
	 * @since 1.0 / 01-04-2008
	 */	
	public Pago getPagoById(BigDecimal idPago) {
		Pago pago = pagoDAO.findPagoById(idPago);
		return pago;
	}
	
	/**
	 * Devuelve los pagosNoRendidos segun un filtro de busqueda.
	 * @param listaFechasContables
	 * @param listaIdsPagos
	 * @param idConvenio
	 * @return
	 */
	public List getPagosNoRendidos(List listaFechasContables, List listaIdsPagos, BigDecimal idConvenio) {
		String joinedFecha = Utiles.join(",", "'", listaFechasContables);
		Date fechaDummy = Nulls.DATE;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaDummy);
		calendar.add(Calendar.DATE, 1);
		fechaDummy = calendar.getTime();
		
		Renderer renderer = new Renderer();
		joinedFecha = "".equals(joinedFecha)?"'"+ renderer.formatDateForDb(fechaDummy) + "'":joinedFecha;
		logger.info("getPagosNoRendidos, joinedFecha string: " + joinedFecha);
		
		String joinedIds = Utiles.join(",", listaIdsPagos);
		joinedIds = "".equals(joinedIds)?"0":joinedIds;
		logger.info("getPagosNoRendidos, joinedIds string: " + joinedIds);
		
		FiltroRendicion filtroRendicion = new FiltroRendicion();
		filtroRendicion.setIdConvenio(idConvenio);
		filtroRendicion.setFechasContables(joinedFecha);
		filtroRendicion.setIdsPagos(joinedIds);
		
		List lista = pagoDAO.findPagosNoRendidos(filtroRendicion);
		
		logger.info("Lista de Pagos No Rendidos: " + lista);
		return lista;
	}
	
	
	/**
	 * Busca un pago segun el id y el convenio
	 * @param idPago
	 * @param idConvenio
	 * @return Objecto Pago
	 */
	public Pago getPagoByIdPagoIdConvenio(BigDecimal idPago, BigDecimal idConvenio) {
		Convenio convenio = new Convenio();
		convenio.setId(idConvenio);
		
		Pago pago = new Pago();
		pago.setId(idPago);
		pago.setConvenio(convenio);
		
		logger.info("En getPagoByIdPagoIdConvenio... idPago: " + idPago + " /idConvenio: " + idConvenio);
		
		Pago result = pagoDAO.findPagoByIdPagoIdConvenio(pago);
		return result;
	}	
	
	/**
	 * Actualizacion del pago segun una rendicion.
	 * @param pago
	 */
	public void updatePagoTrxRendida(Pago pago) {
		pagoDAO.updatePagoTrxRendida(pago);
	}
	
	/**
	 * Actualiza el estado de un pago segun el idPago.
	 * @param idPago
	 * @param idEstado
	 */
	public void updatePagoBitacora(BigDecimal idPago, BigDecimal idEstado, Integer pagado) {
		Pago pago = new Pago();
		Estado estado = new Estado();
		
		estado.setId(idEstado);
		pago.setId(idPago);
		pago.setEstado(estado);
		pago.setPagado(pagado);
		
		pagoDAO.updatePagoBitacora(pago);
	}

	public List getFoliosByPago(BigDecimal idPago) {
		List folios = pagoDAO.findFoliosByPago(idPago);
		return folios;
	}
	
	/**
	 * Consulta los eventos de un pago.
	 * @param idPago
	 * @return
	 */
	public List getBitacora(BigDecimal idPago) {
		List eventos = bitacoraDAO.findBitacora(idPago);
		return eventos;
	}	
	
	/**
	 * Insert de una bitacora.
	 * @param bitacora
	 */
	public void insertBitacora(Bitacora bitacora) {
		bitacoraDAO.insertBitacora(bitacora);
	}
	

	/**
	 * Procesa la inconsistencia (ingreso de bitacora y update del pago). 
	 * @param idPago
	 * @param idEstado
	 * @param pagado
	 * @param observacion
	 * @param usuario
	 */
	public void procesarInconsistencia(BigDecimal idPago, BigDecimal idEstado, Integer pagado, 
			String observacion, String usuario) {
		
		Pago pago = getPagoById(idPago);
		
		//Insert bitacora
		Bitacora bitacora = new Bitacora();
		bitacora.setPago(pago);
		bitacora.setEstadoAnterior(pago.getEstado());
		bitacora.setPagadoAnterior(pago.getPagado());
		bitacora.setFechaIngreso(Utiles.getFechaActual());
		bitacora.setObservacion(observacion);
		bitacora.setUsuario(usuario);
		insertBitacora(bitacora);

		//Update pago
		updatePagoBitacora(idPago, idEstado, pagado);
	}
	
	/**
	 * Entrega la lista de objetos de la columna pago.pagado 
	 * @return Lista
	 */
	public List getListaPagado() {
		List lista = new ArrayList();
		
		/*estado.setId(new BigDecimal(Constants.PAGO_INICIAL.intValue()));
		estado.setDescripcion(resourceApp.getString(Constants.PAGO_PAGADO_AUX + Constants.PAGO_INICIAL));
		lista.add(estado);*/
		lista.add(getPagado(Constants.PAGO_NO_PAGADO));
		lista.add(getPagado(Constants.PAGO_PAGADO));
		return lista;		
	}
	
	/**
	 * Entrega un objeto que representa el valor de pagado 
	 * @param pagadoId
	 * @return
	 */
	public Estado getPagado(Integer pagadoId) {
		Estado estado = new Estado();
		estado.setId(new BigDecimal(pagadoId.intValue()));
		estado.setDescripcion(resourceApp.getString(Constants.PAGO_PAGADO_AUX + pagadoId));
		return estado;
	}
	
	
	public void removePagado(List listaPagados, Integer pagado) {
		BigDecimal pagadoAux = new BigDecimal(pagado.longValue());
		for (Iterator iter = listaPagados.iterator(); iter.hasNext();) {
			Estado element = (Estado) iter.next();
			if (element.getId().compareTo(pagadoAux)==0) {
				listaPagados.remove(element);
				break;
			}
		}
	}
	
	
}