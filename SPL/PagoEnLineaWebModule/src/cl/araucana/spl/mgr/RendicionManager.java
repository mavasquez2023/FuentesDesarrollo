package cl.araucana.spl.mgr;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.Convenio;
import cl.araucana.spl.beans.DetalleRendicionBBV;
import cl.araucana.spl.beans.DetalleRendicionBCH;
import cl.araucana.spl.beans.DetalleRendicionBCI;
import cl.araucana.spl.beans.DetalleRendicionBES;
import cl.araucana.spl.beans.DetalleRendicionBIT;
import cl.araucana.spl.beans.DetalleRendicionBSA;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.RendicionBBV;
import cl.araucana.spl.beans.RendicionBCH;
import cl.araucana.spl.beans.RendicionBCI;
import cl.araucana.spl.beans.RendicionBES;
import cl.araucana.spl.beans.RendicionBIT;
import cl.araucana.spl.beans.RendicionBSA;
import cl.araucana.spl.beans.ResumenDetalleRendicionBES;
import cl.araucana.spl.dao.RendicionBbvDAO;
import cl.araucana.spl.dao.RendicionBchDAO;
import cl.araucana.spl.dao.RendicionBciDAO;
import cl.araucana.spl.dao.RendicionBesDAO;
import cl.araucana.spl.dao.RendicionBitDAO;
import cl.araucana.spl.dao.RendicionBsaDAO;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.util.Utiles;

import com.ibatis.dao.client.DaoManager;

public class RendicionManager {
	private static final Logger logger = Logger.getLogger(RendicionManager.class);
	
	private RendicionBchDAO daoRendBCH;
	private RendicionBciDAO daoRendBCI;
	private RendicionBsaDAO daoRendBSA;
	private RendicionBesDAO daoRendBES;
	private RendicionBitDAO daoRendBIT;
	private RendicionBbvDAO daoRendBBV;
	private PagoManager mgrPago;
	
	//Constructor
	public RendicionManager() {
		DaoManager mgr = DaoConfig.getDaoManager();
		daoRendBCH = (RendicionBchDAO)mgr.getDao(RendicionBchDAO.class);
		daoRendBCI = (RendicionBciDAO)mgr.getDao(RendicionBciDAO.class);
		daoRendBSA = (RendicionBsaDAO)mgr.getDao(RendicionBsaDAO.class);
		daoRendBES = (RendicionBesDAO)mgr.getDao(RendicionBesDAO.class);
		daoRendBIT = (RendicionBitDAO)mgr.getDao(RendicionBitDAO.class);
		daoRendBBV = (RendicionBbvDAO)mgr.getDao(RendicionBbvDAO.class);
		mgrPago = new PagoManager();
	}
	
	/**
	 * Cuenta ocurrencias de una rendicion BCI segun el checksum.
	 * @param codigo, checksum MD5
	 * @return {@link BigDecimal} contador
	 */
	public BigDecimal countRendicionBciByChecksum(String codigo) {
		BigDecimal contador = daoRendBCI.countRendicionByChecksum(codigo);
		return (contador==null)?new BigDecimal(0):contador;
	}
	
	/**
	 * Cuenta ocurrencias de una rendicion BSA segun el checksum.
	 * @param codigo, checksum MD5
	 * @return {@link BigDecimal} contador
	 */
	public BigDecimal countRendicionBsaByChecksum(String codigo) {
		BigDecimal contador = daoRendBSA.countRendicionByChecksum(codigo);
		return (contador==null)?new BigDecimal(0):contador;
	}

	/**
	 * Cuenta ocurrencias de una rendicion BCH segun el checksum.
	 * @param codigo, checksum MD5
	 * @return {@link BigDecimal} contador
	 */
	public BigDecimal countRendicionBchByChecksum(String codigo) {
		BigDecimal contador = daoRendBCH.countRendicionByChecksum(codigo);
		return (contador==null)?new BigDecimal(0):contador;
	}

	/**
	 * Cuenta ocurrencias de una rendicion BES segun el checksum.
	 * @param codigo, checksum MD5
	 * @return {@link BigDecimal} contador
	 */
	public BigDecimal countRendicionBesByChecksum(String codigo) {
		BigDecimal contador = daoRendBES.countRendicionByChecksum(codigo);
		return (contador==null)?new BigDecimal(0):contador;
	}
	
	/**
	 * Cuenta ocurrencias de una rendicion BIT segun el checksum.
	 * @param codigo, checksum MD5
	 * @return {@link BigDecimal} contador
	 */
	public BigDecimal countRendicionBitByChecksum(String codigo) {
		BigDecimal contador = daoRendBIT.countRendicionByChecksum(codigo);
		return (contador==null)?new BigDecimal(0):contador;
	}
	
	/**
	 * Cuenta ocurrencias de una rendicion BBV segun el checksum.
	 * @param codigo, checksum MD5
	 * @return {@link BigDecimal} contador
	 */
	public BigDecimal countRendicionBbvByChecksum(String codigo) {
		BigDecimal contador = daoRendBBV.countRendicionByChecksum(codigo);
		return (contador==null)?new BigDecimal(0):contador;
	}
	
	/**
	 * Dice si la rendicion ya existe una rendicion para el banco X con el mismo checksum.
	 * @param codigoBanco
	 * @param checksum
	 * @return {@link Boolean}, existencia de la rendicion
	 */
	public Boolean existeRendicionBancoByChecksum(String codigoBanco, String checksum) {
		boolean existe = false;
		BigDecimal contador =  new BigDecimal(0);
		if (Constants.MEDIO_CODIGO_BCI.equals(codigoBanco) || 
			Constants.MEDIO_CODIGO_TBC.equals(codigoBanco)) {
			contador = countRendicionBciByChecksum(checksum);
			
		} else if (Constants.MEDIO_CODIGO_BCH.equals(codigoBanco)) {
			contador = countRendicionBchByChecksum(checksum);
			
		} else if (Constants.MEDIO_CODIGO_BSA.equals(codigoBanco)) {
			contador = countRendicionBsaByChecksum(checksum);
		} else if (Constants.MEDIO_CODIGO_BES.equals(codigoBanco)) {
			contador = countRendicionBesByChecksum(checksum);
		
		} else if (Constants.MEDIO_CODIGO_BIT.equals(codigoBanco)) {
			contador = countRendicionBitByChecksum(checksum);
	
		} else if (Constants.MEDIO_CODIGO_BBV.equals(codigoBanco)) {
			contador = countRendicionBbvByChecksum(checksum);
		}
		
		int compare = new BigDecimal(0).compareTo(contador);
		if (compare==-1) { //Contador es mayor
			existe = true;
		}
		logger.info("CodigoBanco: " + codigoBanco + " / Checksum: " + checksum + " / Contador ocurrencias: " + contador);
		logger.info("Existe Rendicion: " + existe);

		return new Boolean(existe);
	}
	
	//****************************
	//METODOS BCO.CRED. E INVERS.
	//****************************
	
	/**
	 * Ingresa un registro en la tabla RENDICBCI
	 * @param rendicionBCI
	 * @return
	 */
	public BigDecimal insertRendicionBCI(RendicionBCI rendicionBCI) {
		BigDecimal id = daoRendBCI.insertRendicion(rendicionBCI);
		return id;
	}
	
	/**
	 * Ingresa un registro en la tabla DETRENDBCI
	 * @param detalleRendicionBCI
	 */
	public void insertDetalleRendicionBCI(DetalleRendicionBCI detalleRendicionBCI) {
		daoRendBCI.insertDetalleRendicion(detalleRendicionBCI);
	}
	
	/**
	 * Consulta un detalle de rendicion BCI segun un idPago.
	 * @param idPago
	 * @return Objeto Detalle {@link DetalleRendicionBCI}.
	 */
	public DetalleRendicionBCI getDetalleRendicionBciByPagoId(BigDecimal idPago) {
		return daoRendBCI.getDetalleRendicionBciByPagoId(idPago);
	}
	
	/**
	 * Importacion del archivo de rendicion del banco BCI.
	 * @param rendicionBCI
	 * @param idConvenio
	 * @param listaInconsistentes
	 * @param listaInconsistentesPagos
	 * @param listaRendicionesOK
	 */
	public void importarRendicionBCI(RendicionBCI rendicionBCI, BigDecimal idConvenio, 
			List listaInconsistentes, List listaInconsistentesPagos, List listaRendicionesOK) throws Exception {
		
		//Sets de datos faltantes (no seteados en preview y que no vienen en la rendicion)
		rendicionBCI.setFchImportacion(Utiles.getFechaActual());
		
		//Insert rendicion BCI (info de cabecera y control)
		BigDecimal idRendicionBCI = insertRendicionBCI(rendicionBCI);
		logger.info("Insert de rendicionBCI ok, el id es: " + idRendicionBCI);

		//Procesar detalles rendiciones OK
		logger.info("Antes de procesar rendiciones OK");
		ingresarDetallesRendicionBCI(listaRendicionesOK, idConvenio, idRendicionBCI, Constants.ESTADO_PAGO_CONSISTENTE);
		
		//Procesar detalles rendiciones inconsistentes (pagado y no rendido)
		logger.info("Antes de procesar trx pagadas y no rendidas");
		updatePagosInconsistentes(listaInconsistentesPagos);

		//Procesar pago rendiciones inconsistentes
		logger.info("Antes de procesar rendiciones inconsistentes");
		ingresarDetallesRendicionBCI(listaInconsistentes, idConvenio, idRendicionBCI, Constants.ESTADO_PAGO_INCONSISTENTE);
	}
	
	
	/**
	 * Ingresa una lista de detalles rendicion BCI.
	 * @param listaRendiciones
	 * @param idConvenio
	 * @param idRendicionBCI
	 * @param estadoId
	 */
	public void ingresarDetallesRendicionBCI(List listaRendiciones, BigDecimal idConvenio, 
			BigDecimal idRendicionBCI, BigDecimal estadoId) throws Exception {
		
		for (Iterator iter = listaRendiciones.iterator(); iter.hasNext();) {
			DetalleRendicionBCI detalleRendicionBCI = (DetalleRendicionBCI) iter.next();
			detalleRendicionBCI.setIdRendicion(idRendicionBCI);
			
			BigDecimal idPago = detalleRendicionBCI.getIdPago(); 
			BigDecimal montoRendicion = detalleRendicionBCI.getTotal();
		
			//Sets de datos que no vienen en el archivo de rendicion
			detalleRendicionBCI.setIdConvenio(idConvenio);
			//detalleRendicionBCI.setCodError("0");
			
			//Insert detalle rendicion
			insertDetalleRendicionBCI(detalleRendicionBCI);
			
			//Update trx
			Pago pago = mgrPago.getPagoById(idPago);
			pago.getEstado().setId(estadoId);
			pago.setMontoRendicion(montoRendicion);
			
			mgrPago.updatePagoTrxRendida(pago);
		}
	}	

	//****************************
	//METODOS BANCO CHILE
	//****************************
	
	/**
	 * Ingresa un registro en la tabla RENDICBCH
	 * @param rendicionBCH
	 * @return
	 */
	public BigDecimal insertRendicionBCH(RendicionBCH rendicionBCH) {
		BigDecimal id = daoRendBCH.insertRendicion(rendicionBCH);
		return id;
	}

	/**
	 * Ingresa un registro en la tabla DETRENDBCH
	 * @param detalleRendicionBCH
	 */
	public void insertDetalleRendicionBCH(DetalleRendicionBCH detalleRendicionBCH) {
		daoRendBCH.insertDetalleRendicion(detalleRendicionBCH);
	}
	
	/**
	 * Consulta un detalle de rendicion BCH segun un idPago.
	 * @param idPago
	 * @return Objeto Detalle {@link DetalleRendicionBCH}.
	 */
	public DetalleRendicionBCH getDetalleRendicionBchByPagoId(BigDecimal idPago) {
		return daoRendBCH.getDetalleRendicionBchByPagoId(idPago);
	}

	/**
	 * Ingresa una lista de detalles rendicion BCH.
	 * @param listaRendiciones
	 * @param idConvenio
	 * @param idRendicionBCH
	 * @param estadoId
	 * @since 09-06-2008: Desde detalle se recupera la fecha contable y se actualiza el campo en la tabla pago.
	 * 					Fecha trx no se actualiza porque ya fue informada en la notificacion del pago.
	 */
	public void ingresarDetallesRendicionBCH(List listaRendiciones, BigDecimal idConvenio, 
			BigDecimal idRendicionBCH, BigDecimal estadoId) throws Exception {
		
		for (Iterator iter = listaRendiciones.iterator(); iter.hasNext();) {
			DetalleRendicionBCH detalleRendicionBCH = (DetalleRendicionBCH) iter.next();
			detalleRendicionBCH.setIdRendicion(idRendicionBCH);
			
			BigDecimal idPago = detalleRendicionBCH.getIdPago(); 
			BigDecimal montoRendicion = detalleRendicionBCH.getMontoPagado();
			Date fechaContable = detalleRendicionBCH.getFchPago();
		
			//Sets de datos que no vienen en el archivo de rendicion
			detalleRendicionBCH.setIdConvenio(idConvenio);
			//detalleRendicionBCH.setCodError("0");
			
			//Insert detalle rendicion
			insertDetalleRendicionBCH(detalleRendicionBCH);
			
			//Update trx
			Pago pago = mgrPago.getPagoById(idPago);
			pago.getEstado().setId(estadoId);
			pago.setMontoRendicion(montoRendicion);
			pago.setFechaContable(fechaContable);
			
			mgrPago.updatePagoTrxRendida(pago);
		}
	}
	

	/**
	 * Importacion del archivo de rendicion del banco BCH a la base de datos.
	 * @param rendicionBCH
	 * @param idConvenio
	 * @param listaInconsistentes
	 * @param listaInconsistentesPagos
	 * @param listaRendicionesOK
	 */
	public void importarRendicionBCH(RendicionBCH rendicionBCH, BigDecimal idConvenio, 
			List listaInconsistentes, List listaInconsistentesPagos, List listaRendicionesOK) throws Exception {
		
		//Sets de datos faltantes (no seteados en preview y que no vienen en la rendicion)
		rendicionBCH.setFchImportacion(Utiles.getFechaActual());
		
		//Insert rendicion BCH (info control)
		BigDecimal idRendicionBCH = insertRendicionBCH(rendicionBCH);
		logger.info("Insert de rendicionBCH ok, el id es: " + idRendicionBCH);

		//Procesar detalles rendiciones OK
		logger.info("Antes de procesar rendiciones OK");
		ingresarDetallesRendicionBCH(listaRendicionesOK, idConvenio, idRendicionBCH, Constants.ESTADO_PAGO_CONSISTENTE);
		
		//Procesar detalles rendiciones inconsistentes (pagado y no rendido)
		logger.info("Antes de procesar trx pagadas y no rendidas");
		updatePagosInconsistentes(listaInconsistentesPagos);

		//Procesar pago rendiciones inconsistentes
		logger.info("Antes de procesar rendiciones inconsistentes");
		ingresarDetallesRendicionBCH(listaInconsistentes, idConvenio, idRendicionBCH, Constants.ESTADO_PAGO_INCONSISTENTE);
	}	
	
	//****************************
	//METODOS BANCO SANTANDER
	//****************************	

	/**
	 * Consulta un detalle de rendicion BSA segun un idPago.
	 * @param idPago
	 * @return Objeto Detalle {@link DetalleRendicionBSA}.
	 */
	public DetalleRendicionBSA getDetalleRendicionBsaByPagoId(BigDecimal idPago) {
		return daoRendBSA.getDetalleRendicionBsaByPagoId(idPago);
	}
	
	/**
	 * Ingresa un registro en la tabla RENDICBSA
	 * @param rendicionBSA
	 * @return
	 */
	public BigDecimal insertRendicionBSA(RendicionBSA rendicionBSA) {
		BigDecimal id = daoRendBSA.insertRendicion(rendicionBSA);
		return id;
	}
	
	/**
	 * Ingresa un registro en la tabla DETRENDBSA
	 * @param detalleRendicionBSA
	 */
	public void insertDetalleRendicionBSA(DetalleRendicionBSA detalleRendicionBSA) {
		daoRendBSA.insertDetalleRendicion(detalleRendicionBSA);
	}

	/**
	 * Importacion del archivo de rendicion del banco BSA a la base de datos.
	 * @param rendicionBSA
	 * @param idConvenio
	 * @param listaInconsistentes
	 * @param listaInconsistentesPagos
	 * @param listaRendicionesOK
	 */
	public void importarRendicionBSA(RendicionBSA rendicionBSA, BigDecimal idConvenio, 
			List listaInconsistentes, List listaInconsistentesPagos, List listaRendicionesOK) throws Exception {
		
		//Sets de datos faltantes (no seteados en preview y que no vienen en la rendicion)
		rendicionBSA.setFchImportacion(Utiles.getFechaActual());
		
		//Insert rendicion BCH (info control)
		BigDecimal idRendicionBSA = insertRendicionBSA(rendicionBSA);
		logger.info("Insert de rendicionBSA ok, el id es: " + idRendicionBSA);

		//Procesar detalles rendiciones OK
		logger.info("Antes de procesar rendiciones OK");
		ingresarDetallesRendicionBSA(listaRendicionesOK, idConvenio, idRendicionBSA, Constants.ESTADO_PAGO_CONSISTENTE);
		
		//Procesar detalles rendiciones inconsistentes (pagado y no rendido)
		logger.info("Antes de procesar trx pagadas y no rendidas");
		updatePagosInconsistentes(listaInconsistentesPagos);

		//Procesar pago rendiciones inconsistentes
		logger.info("Antes de procesar rendiciones inconsistentes");
		ingresarDetallesRendicionBSA(listaInconsistentes, idConvenio, idRendicionBSA, Constants.ESTADO_PAGO_INCONSISTENTE);
	}

	/**
	 * Ingresa una lista de detalles rendicion BSA.
	 * @param listaRendiciones
	 * @param idConvenio
	 * @param idRendicionBSA
	 * @param estadoId
	 */
	public void ingresarDetallesRendicionBSA(List listaRendiciones, BigDecimal idConvenio, 
			BigDecimal idRendicionBSA, BigDecimal estadoId) throws Exception {
		
		for (Iterator iter = listaRendiciones.iterator(); iter.hasNext();) {
			DetalleRendicionBSA detalleRendicionBSA = (DetalleRendicionBSA) iter.next();
			detalleRendicionBSA.setIdRendicion(idRendicionBSA);
			
			BigDecimal idPago = detalleRendicionBSA.getIdPago(); 
			BigDecimal montoRendicion = detalleRendicionBSA.getMontoProducto();
			Date fechaContable = detalleRendicionBSA.getFchHoraOperacion();
		
			//Sets de datos que no vienen en el archivo de rendicion
			detalleRendicionBSA.setIdConvenio(idConvenio);
			//detalleRendicionBSA.setCodError("0");
			
			//Insert detalle rendicion
			insertDetalleRendicionBSA(detalleRendicionBSA);
			
			//Update trx
			Pago pago = mgrPago.getPagoById(idPago);
			pago.getEstado().setId(estadoId);
			pago.setMontoRendicion(montoRendicion);
			pago.setFechaContable(fechaContable);
			
			mgrPago.updatePagoTrxRendida(pago);
		}
	}
		

	//****************************
	//METODOS BANCO ESTADO
	//****************************
	
	/**
	 * Ingresa un registro en la tabla RENDICBES
	 * @param rendicionBES
	 * @return
	 */
	public BigDecimal insertRendicionBES(RendicionBES rendicionBES) {
		BigDecimal id = daoRendBES.insertRendicion(rendicionBES);
		return id;
	}

	/**
	 * Ingresa un registro en la tabla RDRENDBES
	 * @param resumenDetalleRendicionBES
	 */
	public BigDecimal insertResumenDetalleRendicionBES(ResumenDetalleRendicionBES resumenDetalleRendicionBES) {
		BigDecimal id = daoRendBES.insertResumenDetalleRendicion(resumenDetalleRendicionBES);
		return id;
	}	
	
	/**
	 * Ingresa un registro en la tabla DETRENDBES
	 * @param detalleRendicionBES
	 */
	public void insertDetalleRendicionBES(DetalleRendicionBES detalleRendicionBES) {
		daoRendBES.insertDetalleRendicion(detalleRendicionBES);
	}
	
	/**
	 * Consulta un detalle de rendicion BES segun un idPago.
	 * @param idPago
	 * @return Objeto Detalle {@link ResumenDetalleRendicionBES}.
	 */
	public DetalleRendicionBES getDetalleRendicionBesByPagoId(BigDecimal idPago) {
		return daoRendBES.getDetalleRendicionBesByPagoId(idPago);
	}
	
	/**
	 * Consulta un detalle de rendicion BIT segun un idPago.
	 * @param idPago
	 * @return Objeto Detalle.
	 */
	public DetalleRendicionBIT getDetalleRendicionBitByPagoId(BigDecimal idPago) {
		return daoRendBIT.getDetalleRendicionBitByPagoId(idPago);
	}

	/**
	 * Ingresa una lista de detalles rendicion BES.
	 * @param listaRendiciones
	 * @param idConvenio
	 * @param idRendicionBES
	 * @param estadoId
	 * @since 09-06-2008: Desde detalle se recupera la fecha contable y se actualiza el campo en la tabla pago.
	 * 					Fecha trx no se actualiza porque ya fue informada en la notificacion del pago.
	 */
	public void ingresarDetallesRendicionBES(DetalleRendicionBES detalleRendicionBES, BigDecimal idConvenio, 
			BigDecimal idResumenDetalleBES, BigDecimal estadoId) throws Exception {

			detalleRendicionBES.setIdResDetalleRend(idResumenDetalleBES);
			
			BigDecimal idPago = detalleRendicionBES.getCodigoPago(); 
			BigDecimal montoRendicion = detalleRendicionBES.getMontoPago();
			Date fechaContable = detalleRendicionBES.getFechaOperacion();
			
			if (detalleRendicionBES.getCodError() == null || detalleRendicionBES.getCodError().equals("")){
				detalleRendicionBES.setCodError("0");
			}
		
			//Sets de datos que no vienen en el archivo de rendicion
			//TODO verificar esta informacion
			detalleRendicionBES.setConvenio(idConvenio.longValue());
			
			//Insert detalle rendicion
			insertDetalleRendicionBES(detalleRendicionBES);
			
			//Update trx
			Pago pago = mgrPago.getPagoById(idPago);
			pago.getEstado().setId(estadoId);
			pago.setMontoRendicion(montoRendicion);
			pago.setFechaContable(fechaContable);
			
			mgrPago.updatePagoTrxRendida(pago);
	}
	
	/**
	 * Ingresa una resumen de detalles rendicion BES.
	 * @param listaRendiciones
	 * @param idConvenio
	 * @param idRendicionBES
	 * @param estadoId
	 * @since 09-06-2008: Desde detalle se recupera la fecha contable y se actualiza el campo en la tabla pago.
	 * 					Fecha trx no se actualiza porque ya fue informada en la notificacion del pago.
	 */
	public void ingresarResumenDetallesRendicionBES(List listaRendiciones, BigDecimal idConvenio, 
			BigDecimal idRendicionBES, BigDecimal estadoId) throws Exception {
		
		for (Iterator iter = listaRendiciones.iterator(); iter.hasNext();) {
			ResumenDetalleRendicionBES resumenDetalleRendicionBES = (ResumenDetalleRendicionBES) iter.next();
			resumenDetalleRendicionBES.setIdRendicion(idRendicionBES);
			resumenDetalleRendicionBES.setCodError("0");
			
			//Insert detalle rendicion
			BigDecimal idResumenDetalleBES = insertResumenDetalleRendicionBES(resumenDetalleRendicionBES);
			for (int i = 0 ; i < resumenDetalleRendicionBES.getDetalleRendicionBES().length ; i++){
					ingresarDetallesRendicionBES(resumenDetalleRendicionBES.getDetalleRendicionBES()[i], idConvenio, idResumenDetalleBES, estadoId);	
			}
			
			logger.info("Antes de procesar rendiciones OK");
			
		}
	}
	

	/**
	 * Importacion del archivo de rendicion del banco BIT a la base de datos.
	 * @param rendicionBES
	 * @param idConvenio
	 * @param listaInconsistentes
	 * @param listaInconsistentesPagos
	 * @param listaRendicionesOK
	 */
	public void importarRendicionBES(RendicionBES rendicionBES, BigDecimal idConvenio, 
			List listaInconsistentes, List listaInconsistentesPagos, List listaRendicionesOK) throws Exception {
		
		//Sets de datos faltantes (no seteados en preview y que no vienen en la rendicion)
		rendicionBES.setFchImportacion(Utiles.getFechaActual());
		
		//Insert rendicion BES (info control, detalle)
		BigDecimal idRendicionBES = insertRendicionBES(rendicionBES);
		logger.info("Insert de rendicionBES ok, el id es: " + idRendicionBES);

		//Procesar detalles rendiciones OK
		logger.info("Antes de procesar rendiciones OK");
		ingresarResumenDetallesRendicionBES(listaRendicionesOK, idConvenio, idRendicionBES, Constants.ESTADO_PAGO_CONSISTENTE);
				
		//Procesar detalles rendiciones inconsistentes (pagado y no rendido)
		logger.info("Antes de procesar trx pagadas y no rendidas");
		updatePagosInconsistentes(listaInconsistentesPagos);

		//Procesar pago rendiciones inconsistentes
		logger.info("Antes de procesar rendiciones inconsistentes");
		ingresarResumenDetallesRendicionBES(listaInconsistentes, idConvenio, idRendicionBES, Constants.ESTADO_PAGO_INCONSISTENTE);
	}
	
	//****************************
	//METODOS INGRESO DE RENDICION
	//****************************	
	
	/**
	 * Actualiza el estado de una lista de pagos inconsistentes (pagado y no rendido).
	 * @param mgrPago
	 * @param listaInconsistentesPagos
	 */
	public void updatePagosInconsistentes(List listaInconsistentesPagos) throws Exception {
		for (Iterator iter = listaInconsistentesPagos.iterator(); iter.hasNext();) {
			Pago pago = (Pago) iter.next();

			//Update trx
			pago.getEstado().setId(Constants.ESTADO_PAGO_INCONSISTENTE);
			mgrPago.updatePagoTrxRendida(pago);
		}
	}
	
	/**
	 * 
	 * @param rendicionBCI
	 * @param idConvenio
	 * @param listaInconsistentes
	 * @param listaInconsistentesPagos
	 * @param listaRendicionesOK
	 * @throws Exception
	 */
	public void importarRendicion(Object rendicion, String codMedioPago, 
			List listaInconsistentes, List listaInconsistentesPagos, List listaRendicionesOK) throws Exception {

		Convenio convenio = new MedioPagoManager().getConvenio(codMedioPago);
		BigDecimal idConvenio = convenio.getId();
		
		if (Constants.MEDIO_CODIGO_BCI.equals(codMedioPago)
			|| Constants.MEDIO_CODIGO_TBC.equals(codMedioPago)) {
			RendicionBCI rendicionBCI = (RendicionBCI) rendicion;
			importarRendicionBCI(rendicionBCI, idConvenio, listaInconsistentes, listaInconsistentesPagos, listaRendicionesOK);
			
		} else if (Constants.MEDIO_CODIGO_BCH.equals(codMedioPago)) {
			RendicionBCH rendicionBCH = (RendicionBCH) rendicion;
			importarRendicionBCH(rendicionBCH, idConvenio, listaInconsistentes, listaInconsistentesPagos, listaRendicionesOK);

		} else if (Constants.MEDIO_CODIGO_BSA.equals(codMedioPago)) {
			RendicionBSA rendicionBSA = (RendicionBSA) rendicion;
			importarRendicionBSA(rendicionBSA, idConvenio, listaInconsistentes, listaInconsistentesPagos, listaRendicionesOK);
			
		} else if (Constants.MEDIO_CODIGO_BES.equals(codMedioPago)) {
			RendicionBES rendicionBES = (RendicionBES) rendicion;
			importarRendicionBES(rendicionBES, idConvenio, listaInconsistentes, listaInconsistentesPagos, listaRendicionesOK);
			
		} else if (Constants.MEDIO_CODIGO_BIT.equals(codMedioPago)) {
			RendicionBIT rendicionBIT = (RendicionBIT) rendicion;
			importarRendicionBIT(rendicionBIT, idConvenio, listaInconsistentes, listaInconsistentesPagos, listaRendicionesOK);
			
		} else if (Constants.MEDIO_CODIGO_BBV.equals(codMedioPago)) {
			RendicionBBV rendicionBBV = (RendicionBBV) rendicion;
			importarRendicionBBV(rendicionBBV, idConvenio, listaInconsistentes, listaInconsistentesPagos, listaRendicionesOK);
			
		}
		
	}
	
	/**
	 * Importacion del archivo de rendicion del banco BIT a la base de datos.
	 * @param rendicionBIT
	 * @param idConvenio
	 * @param listaInconsistentes
	 * @param listaInconsistentesPagos
	 * @param listaRendicionesOK
	 */
	public void importarRendicionBIT(RendicionBIT rendicionBIT, BigDecimal idConvenio, 
			List listaInconsistentes, List listaInconsistentesPagos, List listaRendicionesOK) throws Exception {
		
		//Sets de datos faltantes (no seteados en preview y que no vienen en la rendicion)
		rendicionBIT.setFchImportacion(Utiles.getFechaActual());
		
		//Insert rendicion Bit (info control)
		BigDecimal idRendicionBit = insertRendicionBIT(rendicionBIT);
		logger.info("Insert de rendicionBIT ok, el id es: " + rendicionBIT);

		//Procesar detalles rendiciones OK
		logger.info("Antes de procesar rendiciones OK");
		ingresarDetallesRendicionBIT(listaRendicionesOK, idConvenio, idRendicionBit, Constants.ESTADO_PAGO_CONSISTENTE);
		
		//Procesar detalles rendiciones inconsistentes (pagado y no rendido)
		logger.info("Antes de procesar trx pagadas y no rendidas");
		updatePagosInconsistentes(listaInconsistentesPagos);

		//Procesar pago rendiciones inconsistentes
		logger.info("Antes de procesar rendiciones inconsistentes");
		ingresarDetallesRendicionBIT(listaInconsistentes, idConvenio, idRendicionBit, Constants.ESTADO_PAGO_INCONSISTENTE);
	}
	
	/**
	 * Importacion del archivo de rendicion del banco BBVA a la base de datos.
	 * @param rendicionBSA
	 * @param idConvenio
	 * @param listaInconsistentes
	 * @param listaInconsistentesPagos
	 * @param listaRendicionesOK
	 */
	public void importarRendicionBBV(RendicionBBV rendicionBBV, BigDecimal idConvenio, 
			List listaInconsistentes, List listaInconsistentesPagos, List listaRendicionesOK) throws Exception {
		
		//Sets de datos faltantes (no seteados en preview y que no vienen en la rendicion)
		//rendicionBBV.setFchImportacion(Utiles.getFechaActual());
		
		//Insert rendicion Bbv (info control)
		insertRendicionBBV(rendicionBBV);
		logger.info("Insert de rendicionBBV ok, el id es: " + rendicionBBV);

		//Procesar detalles rendiciones OK
		logger.info("Antes de procesar rendiciones OK");
		ingresarDetallesRendicionBBV(listaRendicionesOK, idConvenio, rendicionBBV.getIdRendicion(), Constants.ESTADO_PAGO_CONSISTENTE);
		
		//Procesar detalles rendiciones inconsistentes (pagado y no rendido)
		logger.info("Antes de procesar trx pagadas y no rendidas");
		updatePagosInconsistentes(listaInconsistentesPagos);

		//Procesar pago rendiciones inconsistentes
		//logger.info("Antes de procesar rendiciones inconsistentes");
		//ingresarDetallesRendicionBIT(listaInconsistentes, idConvenio, idRendicionBit, Constants.ESTADO_PAGO_INCONSISTENTE);
	}
	/**
	 * Ingresa un registro en la tabla RENDICBIT
	 * @param RendicionBIT
	 * @return
	 */
	public BigDecimal insertRendicionBIT(RendicionBIT rendicionBIT) {
		BigDecimal id = daoRendBIT.insertRendicion(rendicionBIT);
		return id;
	}
	
	/**
	 * Ingresa un registro en la tabla RENDICBBVA
	 * @param RendicionBBV
	 * @return
	 */
	public BigDecimal insertRendicionBBV(RendicionBBV rendicionBBV) {
		BigDecimal id = daoRendBBV.insertRendicion(rendicionBBV);
		return id;
	}
	/**
	 * Ingresa una lista de detalles rendicion Bit.
	 * @param listaRendiciones
	 * @param idConvenio
	 * @param idRendicionBSA
	 * @param estadoId
	 */
	public void ingresarDetallesRendicionBIT(List listaRendiciones, BigDecimal idConvenio, 
			BigDecimal idRendicionBIT, BigDecimal estadoId) throws Exception {
		
		for (Iterator iter = listaRendiciones.iterator(); iter.hasNext();) {
			DetalleRendicionBIT detalleRendicionBIT = (DetalleRendicionBIT) iter.next();
			detalleRendicionBIT.setIdRendicion(idRendicionBIT);
			
			BigDecimal idPago = detalleRendicionBIT.getIdPago(); 
			BigDecimal montoRendicion = detalleRendicionBIT.getMontoProducto();
			Date fechaContable = detalleRendicionBIT.getFechaOperacion();
		
			//Sets de datos que no vienen en el archivo de rendicion
			detalleRendicionBIT.setIdConvenio(idConvenio);
			//detalleRendicionBSA.setCodError("0");
			
			//Insert detalle rendicion
			insertDetalleRendicionBIT(detalleRendicionBIT);
			
			//Update trx
			Pago pago = mgrPago.getPagoById(idPago);
			pago.getEstado().setId(estadoId);
			pago.setMontoRendicion(montoRendicion);
			pago.setFechaContable(fechaContable);
			
			mgrPago.updatePagoTrxRendida(pago);
		}
	}
	
	/**
	 * Ingresa una lista de detalles rendicion BBV.
	 * @param listaRendiciones
	 * @param idConvenio
	 * @param idRendicionBSA
	 * @param estadoId
	 */
	public void ingresarDetallesRendicionBBV(List listaRendiciones, BigDecimal idConvenio, 
			BigDecimal idRendicionBBV, BigDecimal estadoId) throws Exception {
		
		SimpleDateFormat  dateFormat=  new SimpleDateFormat("yyyyMMdd");
		for (Iterator iter = listaRendiciones.iterator(); iter.hasNext();) {
			DetalleRendicionBBV detalleRendicionBBV = (DetalleRendicionBBV) iter.next();
			detalleRendicionBBV.setIdRendicion(idRendicionBBV);
			
			BigDecimal idPago = detalleRendicionBBV.getNumTransaccion(); 
			BigDecimal montoRendicion = detalleRendicionBBV.getMontoPago();
			Date fechaContable = dateFormat.parse(""+ detalleRendicionBBV.getFechaRendicion());
		
			//Sets de datos que no vienen en el archivo de rendicion
			detalleRendicionBBV.setIdConvenio(idConvenio);
			//detalleRendicionBSA.setCodError("0");
			
			//Insert detalle rendicion
			insertDetalleRendicionBBV(detalleRendicionBBV);
			
			//Update trx
			Pago pago = mgrPago.getPagoById(idPago);
			pago.getEstado().setId(estadoId);
			pago.setMontoRendicion(montoRendicion);
			pago.setFechaContable(fechaContable);
			
			mgrPago.updatePagoTrxRendida(pago);
		}
	}

	

	/**
	 * Ingresa un registro en la tabla DETRENDBIT
	 * @param detalleRendicionBSA
	 */
	public void insertDetalleRendicionBIT(DetalleRendicionBIT detalleRendicionBIT) {
		daoRendBIT.insertDetalleRendicion(detalleRendicionBIT);
	}

	/**
	 * Consulta un detalle de rendicion BBVA segun un idPago.
	 * @param idPago
	 * @return Objeto Detalle {@link DetalleRendicionBBV}.
	 */
	public DetalleRendicionBBV getDetalleRendicionBbvByPagoId(BigDecimal idPago) {
		return daoRendBBV.getRendicionBbvByPagoId(idPago);
	}
	
	/**
	 * Ingresa un registro en la tabla DETRENDBBV
	 * @param detalleRendicionBSA
	 */
	public void insertDetalleRendicionBBV(DetalleRendicionBBV detalleRendicionBBV) {
		daoRendBBV.insertDetalleRendicion(detalleRendicionBBV);
	}
	
}
