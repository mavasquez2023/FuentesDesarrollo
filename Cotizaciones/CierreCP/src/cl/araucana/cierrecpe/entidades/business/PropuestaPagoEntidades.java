/*
 * Creado el 12-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.entidades.business;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.business.Constants;
import cl.araucana.cierrecpe.business.Parametros;
import cl.araucana.cierrecpe.dao.CPDAO;
import cl.araucana.cierrecpe.dao.TesoDAO;
import cl.araucana.cierrecpe.entidades.dao.ComprobanteCPDAO;
import cl.araucana.cierrecpe.entidades.dao.PropuestaPagoDAO;
import cl.araucana.cierrecpe.entidades.dao.TesoreriaDAO;
import cl.araucana.cierrecpe.entidades.dao.TipoDetalleCPDAO;
import cl.araucana.cierrecpe.entidades.to.ComprobanteCPTO;
import cl.araucana.cierrecpe.entidades.to.FormasPagoTO;
import cl.araucana.cierrecpe.entidades.to.PropuestaPagoTO;
import cl.araucana.cierrecpe.entidades.to.TipoDetalleTO;
import cl.araucana.cierrecpe.to.ParametrosCPTO;
import cl.araucana.core.util.logging.LogManager;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class PropuestaPagoEntidades {
private CPDAO cpDAO=null;
private TesoDAO tesoDAO=null;
private int ErrorCode=-1;
private static Logger logger = LogManager.getLogger();
private String mensajeError="";
private long montoTotalPropuesta=0;
//private Context ctx;

	public PropuestaPagoEntidades(boolean autoCommit) throws SQLException{
		cpDAO= new CPDAO();
		cpDAO.setAutoCommit(autoCommit);
	}
		
	/**
	 * 
	 */
	public boolean crearPropuestaPago(int periodo, int cierre, String formapago, String deposito, String origen){
		PropuestaPagoTO cheque=null;
		Set codigoBarras=null;
		int ninsert=0;
		try {
			logger.info("Generando Propuesta periodo: " + periodo + ", cierre: " + ", forma pago: " + formapago);
	 		//se resactan los distintos tipo de seccion y detalles
	 		TipoDetalleCPDAO  detalleDAO= new TipoDetalleCPDAO(cpDAO.getConnection());
	 		Collection list_tipoDetalles= (List) detalleDAO.select(null);
	 		logger.info("Cantidad de Tipo_Detalle definido en tabla:" + list_tipoDetalles.size());
	 		codigoBarras= new TreeSet();
	 		for (Iterator iterDetalle = list_tipoDetalles.iterator(); iterDetalle.hasNext();) {
				TipoDetalleTO elem_detalleTO = (TipoDetalleTO) iterDetalle.next();
				//se rescatan todos los comprobantes asociados a cada TipoDetalleTO 
				ComprobanteCPDAO comprobanteDAO= new ComprobanteCPDAO(cpDAO.getConnection());
				logger.fine("Seleccionando comprobantes para seccion=" + elem_detalleTO.getTipoSeccion() + ", detalle=" + elem_detalleTO.getTipoDetalle());
				Collection listComprobantes= (List)comprobanteDAO.select(elem_detalleTO, formapago, elem_detalleTO.getTipoNomina());
				logger.fine("Número de comprobantes asociados= "+  listComprobantes.size());

				if (listComprobantes.size()>0 || elem_detalleTO.getTipoSeccion()== Constants.REMU_CCAF){
					//se crea objeto "ChequeEntidadTO" para acumular monto en cheque asociado a entidad
					logger.info("Generando cheque...");
					cheque= new PropuestaPagoTO();
					cheque.setPeriodo(periodo);
					cheque.setCierre(cierre);
					cheque.setTipoSeccion(elem_detalleTO.getTipoSeccion());
					cheque.setTipoDetalle(elem_detalleTO.getTipoDetalle());
					cheque.setTipoNomina(elem_detalleTO.getTipoNomina());
					cheque.setRut(elem_detalleTO.getRutEntidad());
					cheque.setConceptoTesoreria(elem_detalleTO.getConceptoEgreso());
					cheque.setEstado('G');
					cheque.setOrigen(origen);
					if(elem_detalleTO.getRutEntidad().getNumber()==70016160){
						cheque.setDeposito(Constants.DEPOSITO_TRANSFERENCIA);
					}else{
						cheque.setDeposito(deposito);
					}
					List list_detalleCheque= new ArrayList();
					//Se itera sobre todos los comprobantes obtenidos para acumular MONTO de cada uno.
					for (Iterator iter = listComprobantes.iterator(); iter.hasNext();) {
						ComprobanteCPTO comprobanteTO = (ComprobanteCPTO) iter.next();
						logger.fine("Procesando comprobante :" + comprobanteTO.getCodigoBarra() + ", monto= $" + comprobanteTO.getMonto());
						//Se acumula en objeto cheque el valor del monto del detalle
						cheque.addMontoTotal(comprobanteTO.getMonto());
						//Se incorporan datos al comprobante asociado
						comprobanteTO.setChequeEntidadTO(cheque);
						//folioComprobantes.add(new Integer(comprobanteTO.getFolioIngreso()));
						codigoBarras.add(new Long(comprobanteTO.getCodigoBarra()));
						list_detalleCheque.add(comprobanteTO);
					}
					cheque.setDetalle(list_detalleCheque);
					logger.info("Insertando Propuesta de Pago, monto=" + cheque.getMontoTotal());
					this.montoTotalPropuesta+= cheque.getMontoTotal();
					ninsert+= insertarPropuestaPago(cheque);
				}
			}
	 		if (codigoBarras.size()>0){
				ComprobanteCPDAO comprobanteDAO= new ComprobanteCPDAO(cpDAO.getConnection());
				logger.info("Actualizando cierre comprobantes...");
				comprobanteDAO.update(codigoBarras, cierre);
				ErrorCode= 0;
			}
			return true;
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			setMensajeError(e.getMessage());
			ErrorCode = 1;
			e.printStackTrace();
			return false;
		}
		
	 }
	
	/**
	 * Cargar a la base de datos los objetos de tipo ChequeEntidadTO creados.
	 */
	private int insertarPropuestaPago(PropuestaPagoTO chequeTO){
		int ninsert=0;
		try {
			PropuestaPagoDAO propuestaDAO= new PropuestaPagoDAO(cpDAO.getConnection());
			chequeTO.setFolioEgreso(propuestaDAO.getNumFolio());
			logger.fine("Folio asignado=" + chequeTO.getFolioEgreso());
			ninsert= propuestaDAO.insert(chequeTO);
			ErrorCode= 0;
		} catch (Exception e) {
			logger.severe("Error mensaje=" + e.getMessage());
			ErrorCode = 1;
			e.printStackTrace();
		}
		return ninsert;
	}
	
	/**
	 * Cargar a la base de datos los objetos de tipo ChequeEntidadTO creados.
	 */
	public boolean insertarResumenProcesoCierre(int periodo, int cierre){
		int ninsert=0;
		try {
			PropuestaPagoDAO propuestaDAO= new PropuestaPagoDAO(cpDAO.getConnection());
			logger.fine("Insertando Resumen periodo=" + periodo + ", cierre=" + cierre);
			ninsert=propuestaDAO.insertResumenCierre(periodo, cierre);
			if(ninsert==0){
				return false;
			}
			propuestaDAO.updateComprobantesTGR(periodo, cierre);
			//No se utiliza AIA en 2013
			//propuestaDAO.updateArchivosImpresion(periodo, cierre);
			propuestaDAO.updateCentralizacion(periodo, cierre);
			ErrorCode= 0;
			return true;
		} catch (Exception e) {
			logger.severe("Error mensaje=" + e.getMessage());
			ErrorCode = 1;
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Cargar a la base de datos los objetos de tipo ChequeEntidadTO creados.
	 * @throws SQLException
	 */
	public void eliminarPropuestaPago(int periodo, int cierre){
		try {
			//Borrando propuesta para periodo y cierre espicificado
			PropuestaPagoDAO propuestaDAO= new PropuestaPagoDAO(cpDAO.getConnection());
			logger.info("Eliminando propuesta anterior, periodo:" + periodo + ", cierre:" + cierre);
			propuestaDAO.delete(periodo, cierre);
			//Desmarcando comprobantes en CP
			ComprobanteCPDAO comprobanteDAO= new ComprobanteCPDAO(cpDAO.getConnection());
			comprobanteDAO.updateCierre(0, cierre);
			ErrorCode= 0;
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			ErrorCode = 1;
			e.printStackTrace();
		}
	}
	
	public Collection verPropuestaPago(int periodo, int cierre, String order) {
		PropuestaPagoDAO propuestaDAO;
		try {
			logger.finer("Se solicita Ver Propuesta Pago, periodo: " + periodo + ", cierre: " + cierre);
			propuestaDAO= new PropuestaPagoDAO(cpDAO.getConnection());
			if(order.equals("DEPOSITO")){
				return (List)propuestaDAO.selectbyDeposito(periodo, cierre);
			}else{
				return (List)propuestaDAO.select(periodo, cierre);
			}
			
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public Collection verSeccionesPeriodo(int periodo) {
		PropuestaPagoDAO propuestaDAO;
		try {
			logger.finer("Se solicita Ver Secciones periodo: " + periodo);
			propuestaDAO= new PropuestaPagoDAO(cpDAO.getConnection());
			return (List)propuestaDAO.selectSecciones(periodo);
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public Collection getListCierres(int periodo){
		try {
			PropuestaPagoDAO propuestaDAO;
			propuestaDAO= new PropuestaPagoDAO(cpDAO.getConnection());
			return (List)propuestaDAO.selectListCierres(periodo);
		}
		catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public int getMaxCierre(int periodo){
		try {
			PropuestaPagoDAO propuestaDAO;
			propuestaDAO= new PropuestaPagoDAO(cpDAO.getConnection());
			return propuestaDAO.selectMaxCierre(periodo);
		}
		catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public Integer getCierrePendiente(int periodo){
		try {
			PropuestaPagoDAO propuestaDAO;
			propuestaDAO= new PropuestaPagoDAO(cpDAO.getConnection());
			return new Integer(propuestaDAO.selectCierrePendiente(periodo));
		}
		catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public Collection getListPeriodos(){
		try {
			PropuestaPagoDAO propuestaDAO;
			propuestaDAO= new PropuestaPagoDAO(cpDAO.getConnection());
			return (List)propuestaDAO.selectListPeriodos();
		}
		catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	//Rescata Los comprobantes asociados a Forma de Pago que incluye Cuadraturas contra Tesorería
	public Collection getListFormasPago(String prefijoCodigoBarra){
		try {
			tesoDAO= new TesoDAO();
			ComprobanteCPDAO comprobanteDAO= new ComprobanteCPDAO(cpDAO.getConnection());
			TesoreriaDAO tesoreriaDAO= new TesoreriaDAO(tesoDAO.getConnection());
			ParametrosCPTO paramTO= Parametros.getInstance().getParam();

			Collection formaspago= comprobanteDAO.selectNumComprobantes();
			//Se busca la mínima fecha de comprobantes emitidos en CP y la fecha actual para buscar en Tesorería
			logger.info("Obteniendo las min y max fechas de los comprobantes del cierre");
			
			//clillo 07-08-2017 se obtiene fecha de apertura desde Parámetros
			//Date fechamin=comprobanteDAO.selectMinPagadoCierre(1, 2);
			java.util.Date fechautil= paramTO.getFechaApertura().getDate();
			Date fechamin= new java.sql.Date(fechautil.getTime());
			
			System.out.println("fecha mínima:" + fechamin);
			logger.info("fecha mínima:" + fechamin);
			Date fechamax=comprobanteDAO.selectMaxPagado();
			System.out.println("fecha max:" + fechamax);
			logger.info("fecha max:" + fechamax);
			
			logger.fine("Obteniendo los comprbantes desde Tesorería");
			Collection comprobantesTesoreia= tesoreriaDAO.selectComprobantesCuadraturaTesoreria(prefijoCodigoBarra, fechamin, fechamax);
			System.out.println("nro. comprobantes en tesoreria:" +  comprobantesTesoreia.size()); 
			//Separando comprobantes Empresa e Independiente
			List compEmpresas= new ArrayList();
			List compIndependientes= new ArrayList();

			for (Iterator compTesoIter = comprobantesTesoreia.iterator(); compTesoIter.hasNext();) {
				ComprobanteCPTO compTO = (ComprobanteCPTO) compTesoIter.next();
				String tipo= comprobanteDAO.selectTipoEmpresa(compTO.getRutEmpresa().getNumber());
				if(tipo!=null){
					if(tipo.equals(Constants.TIPO_CLIENTE_EMPRESA)){
						compEmpresas.add(compTO);
					}else if(tipo.equals(Constants.TIPO_CLIENTE_INDEPENDIENTE)){
						compIndependientes.add(compTO);
					}
				}
			}
			
			logger.fine("Obteniendo los comprobantes asociados a las formas de pago");
			//Se obtienen los comprobantes asociados a las Formas de Pago
			for (Iterator iter = formaspago.iterator(); iter.hasNext();) {
				//long totalTesoreria=0;
				FormasPagoTO formaTO = (FormasPagoTO) iter.next();
				/*
				//De los comprobantes encontrados en Tesorería se eliminan los que no son de la misma Forma de Pago y no están en el Cierre 0
				logger.fine("Filtrando los comprobantes obtenidos desde Tesorería");
				List comprobantesFiltradosTesoreria= new ArrayList();
				for (Iterator iter2 = comprobantesTesoreia.iterator(); iter2.hasNext();) {
					ComprobanteCPTO compTO = (ComprobanteCPTO) iter2.next();
					if(comprobanteDAO.selectIsComprobanteValido(formaTO.getFormaPago(), compTO.getFolioIngreso())){
						comprobantesFiltradosTesoreria.add(compTO);
						System.out.println("comprobante válido:" + compTO.getCodigoBarra() + ", folio:" + compTO.getFolioIngreso());
						//Se obtiene el valor total de Tesoreria de los comprobantes filtrados
						totalTesoreria+= compTO.getMonto();
					}
				}*/
				
				//Se asigna el Total de Tesorería obtenido luego de filtrar
				logger.fine("Se asigna el Total de Tesorería obtenido a la Forma de Pago");
				String filtroForma="";
				if(formaTO.getFormaPago()== Constants.FORMAPAGO_SPL_EMPRESA || formaTO.getFormaPago()== Constants.FORMAPAGO_MIXTA_EMPRESA){
					filtroForma= Constants.FORMAPAGO_SPL_EMPRESA + ", " + Constants.FORMAPAGO_MIXTA_EMPRESA;
				}else if(formaTO.getFormaPago()== Constants.FORMAPAGO_SPL_INDEPENDIENTE || formaTO.getFormaPago()== Constants.FORMAPAGO_MIXTA_INDEPENDIENTE){
					filtroForma=Constants.FORMAPAGO_SPL_INDEPENDIENTE + ", " + Constants.FORMAPAGO_MIXTA_INDEPENDIENTE;
				}else{
					filtroForma=String.valueOf(formaTO.getFormaPago());
				}
				
				//Se rescatan todos los comprobantes de CP de la Forma de Pago X
				logger.fine("Se rescatan todos los comprobantes de CP de la Forma de Pago X");
				Collection comprobantesCP= comprobanteDAO.selectComprobantesCuadraturaCP(filtroForma, true);
				
				//Se compara la lista de comprobantes obtenida de CP versus Tesorería
				logger.fine("Se compara la lista de comprobantes obtenida de CP versus Tesorería");
				Collection diferencias= null;
				if(formaTO.getFormaPago()== Constants.FORMAPAGO_SPL_EMPRESA || formaTO.getFormaPago()== Constants.FORMAPAGO_MIXTA_EMPRESA){
					diferencias= comparaComprobantesCPTesoreia(comprobantesCP, compEmpresas);
				}else if(formaTO.getFormaPago()== Constants.FORMAPAGO_SPL_INDEPENDIENTE || formaTO.getFormaPago()== Constants.FORMAPAGO_MIXTA_INDEPENDIENTE){
					diferencias= comparaComprobantesCPTesoreia(comprobantesCP, compIndependientes);
				}else{
					diferencias= new ArrayList();
				}
				
				//Se vuelve a obtener el Numero de Comprobantes para la visualización de detalle por Forma de Pago
//				Se rescatan todos los comprobantes de CP de la Forma de Pago X
				logger.fine("Se rescatan todos los comprobantes de CP de la Forma de Pago X");
				comprobantesCP= comprobanteDAO.selectComprobantesCuadraturaCP(String.valueOf(formaTO.getFormaPago()), false);
				
				logger.fine("Comparación finalizada.");
				formaTO.setNumDescuadraturas(diferencias.size());
				formaTO.setDescuadraturas(diferencias);
				formaTO.setComprobantesCP(comprobantesCP);
			}
			 return (List) formaspago;
		}
		catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return null;
		}
		finally{
			if(tesoDAO!=null){
				tesoDAO.closeConnectionDAO();
			}
		}
	}
	
	public Collection comparaComprobantesCPTesoreia(Collection lista1, Collection lista2){
		List diferencias= new ArrayList();
		for (Iterator iterCP = lista1.iterator(); iterCP.hasNext();) {
			ComprobanteCPTO compCPTO = (ComprobanteCPTO) iterCP.next();
			boolean encontrado= false;
			for (Iterator iterTE = lista2.iterator(); iterTE.hasNext();) {
				ComprobanteCPTO compTETO = (ComprobanteCPTO) iterTE.next();
				if(compCPTO.getFolioIngreso()== compTETO.getFolioIngreso()){
					if(compCPTO.getCodigoBarra()== compTETO.getCodigoBarra()){
						if(compCPTO.getMonto()== compTETO.getMonto()){
							encontrado= true;
							break;
						}
					}
				}
			}
			if(!encontrado){
				diferencias.add(compCPTO);
			}
		}
		for (Iterator iterTE = lista2.iterator(); iterTE.hasNext();) {
			ComprobanteCPTO compTETO = (ComprobanteCPTO) iterTE.next();
			boolean encontrado= false;
			for (Iterator iterCP = lista1.iterator(); iterCP.hasNext();) {
				ComprobanteCPTO compCPTO = (ComprobanteCPTO) iterCP.next();
				if(compCPTO.getFolioIngreso()== compTETO.getFolioIngreso()){
					if(compCPTO.getCodigoBarra()== compTETO.getCodigoBarra()){
						if(compCPTO.getMonto()== compTETO.getMonto()){
							encontrado= true;
							break;
						}
					}
				}
			}
			if(!encontrado){
				diferencias.add(compTETO);
			}
		}
		return diferencias;
	}
	
	public Collection verDetallePropuesta(int folio) {
		PropuestaPagoDAO propuestaDAO;
		try {
			logger.finer("Se solicita Ver Detalle Propuesta folio egreso: " + folio);
			propuestaDAO= new PropuestaPagoDAO(cpDAO.getConnection());
			return (List)propuestaDAO.selectDetalle(folio);
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public Collection generarEstadisticasPago() {
		PropuestaPagoDAO propuestaDAO;
		try {
			logger.finer("Se solicita Ver Estadísticas Propuestas de Pago");
			propuestaDAO= new PropuestaPagoDAO(cpDAO.getConnection());
			return (List)propuestaDAO.selectEstadistica();
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public Collection getObjPropuestaPago(int periodo, int cierre) throws SQLException{
		PropuestaPagoDAO propuestaDAO;
		List chequesPropuestos=null;
			propuestaDAO= new PropuestaPagoDAO(cpDAO.getConnection());
			chequesPropuestos= (List)propuestaDAO.select(periodo, cierre);
			for (Iterator iter = chequesPropuestos.iterator(); iter.hasNext();) {
				PropuestaPagoTO cheque = (PropuestaPagoTO) iter.next();
				Collection detalle= (List) propuestaDAO.selectDetalle(cheque.getFolioEgreso());
				cheque.setDetalle(detalle);
			}
		return chequesPropuestos;
	}
	
	public void cambiarEstadoPropuestaPago(Collection list_cheques, char estado) throws SQLException {
		PropuestaPagoDAO propuestaDAO= new PropuestaPagoDAO(cpDAO.getConnection());
		for (Iterator itercheque = list_cheques.iterator(); itercheque.hasNext();) {
			PropuestaPagoTO chequeTO = (PropuestaPagoTO) itercheque.next();
			propuestaDAO.updateEstado(chequeTO, estado);
		}	
			
	}
	
	public boolean isConPlanillas(int periodo, int cierre) {
		PropuestaPagoDAO propuestaDAO;
		try {
			logger.finer("Se solicita verificar si se ha generado planillas para, periodo: " + periodo + ", cierre: " + cierre);
			propuestaDAO= new PropuestaPagoDAO(cpDAO.getConnection());
			return propuestaDAO.selectIsConPlanillas(periodo, cierre);
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	 
	 public void commit() throws SQLException{
	 	switch (ErrorCode) {
		case 0:
			logger.info("PropustaCheques, haciendo commit de la transaccion");
			cpDAO.commit();
			break;
		case 1:
			logger.warning("PropustaCheques, haciendo rollback de la transaccion");
			cpDAO.rollback();
			break;
		default:
			break;
		}
	 	
	 }
	 
	 public void close() throws SQLException{
		 	
		 	cpDAO.closeConnectionDAO();
		 }

	/**
	 * @return el mensajeError
	 */
	public String getMensajeError() {
		return mensajeError;
	}

	/**
	 * @param mensajeError el mensajeError a establecer
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	/**
	 * @return el montoTotalPropuesta
	 */
	public long getMontoTotalPropuesta() {
		return montoTotalPropuesta;
	}

	/**
	 * @param montoTotalPropuesta el montoTotalPropuesta a establecer
	 */
	public void setMontoTotalPropuesta(long montoTotalPropuesta) {
		this.montoTotalPropuesta = montoTotalPropuesta;
	}
}
