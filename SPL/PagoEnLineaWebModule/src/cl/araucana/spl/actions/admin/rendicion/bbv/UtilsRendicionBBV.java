package cl.araucana.spl.actions.admin.rendicion.bbv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import cl.araucana.spl.base.Constants;
import cl.araucana.spl.base.RendicionBase;
import cl.araucana.spl.beans.Convenio;
import cl.araucana.spl.beans.DetalleRendicion;
import cl.araucana.spl.beans.DetalleRendicionBBV;
import cl.araucana.spl.beans.MedioPago;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.RendicionBBV;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.exceptions.RendicionException;
import cl.araucana.spl.forms.admin.rendicion.ImportarRendicionForm;
import cl.araucana.spl.mgr.MedioPagoManager;
import cl.araucana.spl.mgr.PagoManager;
import cl.araucana.spl.mgr.RendicionManager;
import cl.araucana.spl.util.ActionTools;
import cl.araucana.spl.util.MD5Checksum;
import cl.araucana.spl.util.Renderer;
import cl.araucana.spl.util.Utiles;

public class UtilsRendicionBBV extends RendicionBase {
	//Constantes locales
	private static Logger logger = Logger.getLogger(UtilsRendicionBBV.class);

	private static final ResourceBundle resourceRendicion = ResourceBundle.getBundle("cl.araucana.spl.resources.RendicionBBV");
	
	private static final String SEPARADOR_CAMPOS = "|";
	private static final String SEPARADOR_SIZE = "-";

	private static final String REND_DETALLE_DESC = "BBV_DETALLE_DESC";
	private static final String REND_DETALLE_POS = "BBV_DETALLE_POS";
	
	private static final String DETALLES_KEY = "DETALLE";	
	
	private static Integer TOTAL_REGISTROS_RENDICION = new Integer(0);
	
	private static List LIST_ERROR_IMPORTACION = new ArrayList();
	private static List LIST_ERROR_IMPORTACION_DETALLE = new ArrayList();	
		
	
	/**
	 * Contructor
	 */
	public UtilsRendicionBBV() {		
		TOTAL_REGISTROS_RENDICION = new Integer(0);
		LIST_ERROR_IMPORTACION = new ArrayList();
	}
	
	/**
	 * Busca los errores de importacion de una rendicion.
	 * Setea en un Map el bean de Rendicion y los detalles de las rendiciones.
	 * @param pathCompArchivo
	 * @return
	 * @throws IOException
	 */
	protected Map procesarArchivoRendicion(InputStream is, String codigoConvenio, RendicionManager mgrRendicion) 
		throws RendicionException {
		logger.info("Estoy en procesarArchivoRendicion");
		
		Map mapaResult = new HashMap();
		try {
			Map mapaRendicion = leerArchivoRendicionTxt(is);
			mapaRendicion.put(CODIGO_CONVENIO, codigoConvenio);
			mapaRendicion.put(MGR_RENDICION, mgrRendicion);
			
			//Archivo se debe procesar en Orden: 1)Lineas, 2)Control
			
			//1) LINEAS DETALLE
			List listaLineas = procesaLineas(mapaRendicion);
			mapaResult.put(BEANS_DETALLES_RENDICION, listaLineas);
			
			//sumar los valore para obtener el total
			//numero de pago y fecha de importacion
			
			////2) CONTROL
			RendicionBBV rendicionBBV = new RendicionBBV();
			rendicionBBV.setNroRegistros(new BigDecimal(TOTAL_REGISTROS_RENDICION.intValue()));
			rendicionBBV.setFchImportacion(new Date());
			rendicionBBV.setMontoTotal(this.sumaTotalDetalleRendiciones(listaLineas));
			mapaResult.put(BEAN_RENDICION, rendicionBBV);
			
	    } catch (IOException e) {
			logger.error("Error en procesarArchivoRendicion");
			throw new RendicionException("Problemas en procesamiento archivo rendicion", e);
		} 
		catch (ParseException e) {
			logger.error("Error en procesarArchivoRendicion");
			throw new RendicionException("Problemas en parse archivo rendicion", e);
		} finally {
			logger.info("Sali de procesarArchivoRendicion");
	    }
		return mapaResult;
	}
	
	/**
	 * Lee el archivo de rendicion, separando los detalles y el control.
	 * @param pathCompArchivo
	 * @return
	 * @throws IOException
	 */
	private Map leerArchivoRendicionTxt(InputStream is) throws IOException, RendicionException {
		Map mapa = new HashMap();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		int largoLinea = new Integer(resourceRendicion.getString("REND_NRO_CARACTERES_LINEA")).intValue();
		try {
			List listaDetalles = new ArrayList();
	        String linea;
	        while ((linea = in.readLine()) != null) {
	            logger.info("Linea: "  + linea + " - Largo: " + linea.length());

	            if (linea.length()<largoLinea) {
	            	throw new RendicionException("rendicion.archivo.linea.tamagnoMenor");
	            	
	            } else {
		            listaDetalles.add(linea);
		           
	            }
		        
	        }
	        mapa.put(DETALLES_KEY, listaDetalles);
	    } finally {
	    	if (in!=null)
	    		in.close();
	    }
	
		return mapa;
	}
	
	/**
	 * Procesa las lineas de detalles almacenadas.
	 * @param mapaRendicion
	 * @return
	 */
	private List procesaLineas(Map mapaRendicion) throws ParseException {
		List listaLineas = new ArrayList();
		Map mapa = new HashMap();
		mapa.put(MGR_RENDICION, (RendicionManager) mapaRendicion.get(MGR_RENDICION));
		
		try {
			String valorKeyDesc = resourceRendicion.getString(REND_DETALLE_DESC);
			String valorKeyPos = resourceRendicion.getString(REND_DETALLE_POS);
			
			Vector vecDesc = Utiles.setVector(valorKeyDesc, SEPARADOR_CAMPOS);
			Vector vecSize = Utiles.setVector(valorKeyPos, SEPARADOR_CAMPOS);	
			
	        List listaDetalles = (ArrayList) mapaRendicion.get(DETALLES_KEY);
	        for (int i = 0; i < listaDetalles.size(); i++) {
	        	String linea = (String) listaDetalles.get(i);
	        	
				for (int j = 0; j < vecSize.size(); j++) {
					String nombreCampo = (String) vecDesc.elementAt(j);
					String tamagno = (String) vecSize.elementAt(j);
										
					Vector pos = Utiles.setVector(tamagno, SEPARADOR_SIZE);
					int posIni = Integer.parseInt(((String)pos.elementAt(0)).trim());
					int posFin = Integer.parseInt(((String)pos.elementAt(1)).trim());
					
					String valorCampoLinea = linea.substring(posIni, posFin);
					valorCampoLinea = valorCampoLinea!=null?valorCampoLinea.trim():valorCampoLinea;
					
					logger.info("La columna: " + nombreCampo + ", de posicion:" + tamagno +" tiene el valor: " + valorCampoLinea);
				
					mapa.put(nombreCampo, valorCampoLinea);
				}
				/*
				idDetalleRend; idRendicion; idConvenio; idPago; codError;
				*/
				DetalleRendicionBBV detalleRendicionBBV = new DetalleRendicionBBV();
				
				//VALIDACIONES
				detalleRendicionBBV = validarRendicionDetalle(detalleRendicionBBV, mapa);
				
				listaLineas.add(detalleRendicionBBV);
			}
	        
			//Acumular los registros de la rendicion
			TOTAL_REGISTROS_RENDICION = new Integer(listaDetalles.size());
	        
	    } finally {
	    }
	    return listaLineas;
	}
		
	/**
	 * Valida los tipos de datos del Detalle de la RendicionBBV
	 * @param detalleRendicionBBV
	 * @param mapa
	 * @return
	 */
	private DetalleRendicionBBV validarRendicionDetalle(DetalleRendicionBBV detalleRendicionBBV,Map mapa) throws ParseException {
		LIST_ERROR_IMPORTACION_DETALLE = new ArrayList();
		RendicionManager mgrRendicion = (RendicionManager) mapa.get(MGR_RENDICION);
		
		String codTransBanco = (String) mapa.get("COD_TRANS_BANCO");
		String codTransComercio = (String) mapa.get("COD_TRANS_COMERCIO");
		String numTransaccion = (String) mapa.get("NUM_TRANSACCION");
		String idCliente = (String) mapa.get("ID_CLIENTE");
		String idDocumento = (String) mapa.get("ID_DOCUMENTO");
		String rutCliente = (String) mapa.get("RUT_CLINETE");
		String montoPago = (String) mapa.get("MONTO_PAGO");
		String fechaPago = (String) mapa.get("FECHA_PAGO");
		String horaPago = (String) mapa.get("HORA_PAGO");
		String fechaRecaudacion = (String) mapa.get("FECHA_RECAUDACION");
		
		
		//VALIDACIONES
		BigDecimal numTransaccionB = valBigDecimal.validate(numTransaccion);
		BigDecimal montoPagoB = valBigDecimal.validate(montoPago);
		
		BigDecimal fechaPagoB = valBigDecimal.validate(fechaPago);
		BigDecimal horaPagoB = valBigDecimal.validate(horaPago);
		BigDecimal fechaRecaudacionB = valBigDecimal.validate(fechaRecaudacion);
		

		//Validar que el pago no haya sido rendido con anterioridad
		if (numTransaccionB!=null) {			
			DetalleRendicionBBV detalleRendicionBBVAxu = mgrRendicion.getDetalleRendicionBbvByPagoId(numTransaccionB);
			if (detalleRendicionBBVAxu!=null) {
				LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_YA_RENDIDO, 
						"", numTransaccionB.toString()));
			}
		}
		
		//Validar tipo de datos
        if (numTransaccionB == null)
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Id pago", numTransaccion));

		if (codTransBanco == null)
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Codigo Transaccion Bnaco", codTransBanco));

		if (codTransComercio == null)
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Codigo Transaccion Comercio", codTransComercio));

		if (idCliente == null)
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Id Cliente", idCliente));

		if (idDocumento == null)
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Id Documento", idDocumento));

		if (rutCliente == null)
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Rut Cliente", rutCliente));

		if (montoPago == null)
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Monto Pago", montoPago));

		if (fechaPago == null)
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Fecha Pago", fechaPago));

		if (horaPago == null)
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Hora Pago", horaPago));

		if (fechaRecaudacion == null)
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Fecha Recaudacion", fechaRecaudacion));
		
		try{
			Utiles.getFechaParse("yyyyMMdd", fechaPago);	
		}catch (Exception e) {
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Fecha Pago", fechaPago));
		}
		try{
			Utiles.getFechaParse("HHmmss", horaPago);	
		}catch (Exception e) {
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Hora Pago", horaPago));
		}
		try{
			Utiles.getFechaParse("yyyyMMdd", fechaRecaudacion);	
		}catch (Exception e) {
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Fecha Pago", fechaPago));
		}
		//SETEOS
		detalleRendicionBBV.setIdPago(numTransaccionB);
		detalleRendicionBBV.setCodTransBanco(codTransBanco);
		detalleRendicionBBV.setCodTransComercio(codTransComercio);
		detalleRendicionBBV.setNumTransaccion(numTransaccionB);
		detalleRendicionBBV.setIdCliente(idCliente);
		detalleRendicionBBV.setIdDocumento(idDocumento);
		detalleRendicionBBV.setRutClientePagador(rutCliente);
		detalleRendicionBBV.setMontoPago(montoPagoB);
		detalleRendicionBBV.setFechaPago(fechaPagoB);
		detalleRendicionBBV.setHoraPago(horaPagoB);
		detalleRendicionBBV.setFechaRendicion(fechaRecaudacionB);
		
        detalleRendicionBBV.setListErrorImportacion(LIST_ERROR_IMPORTACION_DETALLE);
        return detalleRendicionBBV;
	}

	/**
	 * Metodo de inicio de la logica de procesamiento de rendicion BBV
	 * @param mapaParametros
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public String initProcesarRendicion(Map mapaParametros, HttpServletRequest request, HttpSession session) throws Exception {
		logger.info("Entre a initProcesarRendicion");
		
		String target = new String("preview");
		String archivoNombre = (String) mapaParametros.get(NOMBRE_ARCHIVO);
		FormFile rendicionFile = (FormFile) mapaParametros.get(FORM_FILE);
		MedioPago medioPago = (MedioPago) mapaParametros.get(MEDIO_PAGO);
		RendicionManager mgrRendicion = (RendicionManager) mapaParametros.get(MGR_RENDICION);
		PagoManager mgrPago = (PagoManager) mapaParametros.get(MGR_PAGO);
		ImportarRendicionForm frm = (ImportarRendicionForm) mapaParametros.get(FORM_IMPORTAR_RENDICION);

		String codBanco = medioPago!=null?medioPago.getCodigo():"";

		//En esta rendicion no se valida el nombre del archivo.

		//Calcular checksum
		String checksum = MD5Checksum.getMD5Checksum(rendicionFile.getFileData());
		logger.info("checksum archivo:" + checksum);
		
		//Consultar segun banco, si existe la rendicion
		boolean existe = mgrRendicion.existeRendicionBancoByChecksum(codBanco, checksum).booleanValue();
		
		if (!existe) {
			//Validacion General
			logger.info("Rendicion No existe... se debe procesar.");
			
			Convenio convenio = new MedioPagoManager().getConvenio(codBanco);
			String codigoConvenioBanco = convenio!=null?convenio.getCodigo():""; 
			
			//PROCESAR ARCHIVO
			Map mapaRendicion = procesarArchivoRendicion(rendicionFile.getInputStream(), codigoConvenioBanco, mgrRendicion);
			mapaRendicion.put(NOMBRE_ARCHIVO, archivoNombre);
			mapaRendicion.put(BEAN_CONVENIO, convenio);
			
			//Verificar errores en cabecera y control
			RendicionBBV rendicionBBV  = (RendicionBBV) mapaRendicion.get(BEAN_RENDICION);
			if (rendicionBBV!=null) {
					List listaErrorImportacionCabecera = rendicionBBV.getListErrorImportacion();
				
				if (listaErrorImportacionCabecera.size()==0) {
					//CONCILIAR
					logger.info("Sin errores de importacion, se debe conciliar");
					
					Map mapaConciliacion = conciliarPagos(mapaRendicion, mgrPago);

					Integer importados = (Integer) mapaConciliacion.get(NRO_IMPORTADOS);
					Integer noImportados = (Integer) mapaConciliacion.get(NRO_NO_IMPORTADOS);
					Integer consistentes = (Integer) mapaConciliacion.get(NRO_CONSISTENTES);
					Integer inconsistentes = (Integer) mapaConciliacion.get(NRO_INCONSISTENTES);								
					Integer inconsistenteDetalleRendicion = (Integer) mapaConciliacion.get(NRO_INCONSISTENTES_DETS_RENDICION);								

					List listaInconsistentes = (ArrayList) mapaConciliacion.get(LISTA_INCONSISTENTES);
					List listaInconsistentesPagos = (ArrayList) mapaConciliacion.get(LISTA_INCONSISTENTES_PAGOS);
					List listaNoImportados = (ArrayList) mapaConciliacion.get(LISTA_NO_IMPORTADOS);
					List listaRendicionesOK = (ArrayList) mapaConciliacion.get(LISTA_RENDICIONES_OK);
					
					//Mostrar en log rendiciones OK
					showRendicionesAImportar(listaRendicionesOK, listaInconsistentes);
					
					//Sets form
					frm.setImportados(new Integer(importados.intValue()+inconsistenteDetalleRendicion.intValue()));
					frm.setNoImportados(noImportados);
					frm.setConsistentes(consistentes);
					frm.setInconsistentes(inconsistentes);
					frm.setTotalDetallesRendicion(new Integer(frm.getImportados().intValue()+ frm.getNoImportados().intValue()));
													
					frm.setListaInconsistentes(getDetalleRendicion(listaInconsistentes));
					frm.setListaInconsistentesPagos(listaInconsistentesPagos);
					frm.setListaNoImportados(getDetalleRendicion(listaNoImportados));
					
					//Sets de datos que no vienen en el archivo de rendicion
					rendicionBBV.setChecksum(checksum);
													
					//Guardo en la session
					session.setAttribute(BEAN_RENDICION, rendicionBBV);
					session.setAttribute(CODIGO_MEDIO_PAGO, codBanco);
					session.setAttribute(LISTA_INCONSISTENTES, listaInconsistentes);
					session.setAttribute(LISTA_INCONSISTENTES_PAGOS, listaInconsistentesPagos);
					session.setAttribute(LISTA_RENDICIONES_OK, listaRendicionesOK);
					
				} else {
					//Cabecera con errores, informar al usuario
					logger.info("Existe errores de importacion, no se concilia");
					ActionTools.addMessage(request, new ActionMessage("rendicion.archivo.importacion.ErrorCabeceraControl"));
					frm.setFlagErrorCabeceraControl("1");
					
					frm.setListaNoImportados((ArrayList) rendicionBBV.getListErrorImportacion());
				}
			}
			
			//Sets form obligatorios
			frm.setMedioPago(medioPago);
			
		} else {
			logger.info("Rendicion ya existe");
			
			ActionTools.addMessage(request, new ActionMessage("rendicion.archivo.existe"));
			target = "inicio";
		}

		logger.info("Sali de initProcesarRendicion, target: " + target);
		return target;
	}
	
	
	
	/**
	 * Ejecuta la conciliacion de los pagos a importar.
	 * @param mapaRendicion
	 * @param mgrPago
	 * @return
	 * @throws PagoEnLineaException
	 */
	protected Map conciliarPagos(Map mapaRendicion, PagoManager mgrPago) throws PagoEnLineaException, ParseException {
		logger.info("Entre a conciliarPagos BBV");
		
		Map mapa = new HashMap();
		Renderer renderer = new Renderer();
		int importados = 0;
		int noImportados = 0;
		int consistentes = 0;
		int inconsistentes = 0;
		int inconsistenteDetalleRendicion = 0; //Inconsistencia en un detalle del archivo, estos pagos se deben sumar a lo que se debe importar
		int inconsistentePago = 0; //Inconsistencia en el Pago de la base de datos

		List listaNoImportados = new ArrayList();
		List listaInconsistentes = new ArrayList();
		List listaInconsistentesPagos = new ArrayList();
		List listaRendicionesOK = new ArrayList();
		Date fechaContable = new Date();
		Convenio convenio = (Convenio) mapaRendicion.get(BEAN_CONVENIO);
		BigDecimal idConvenio = (convenio!=null)?convenio.getId():new BigDecimal(0);
		String codConvenio = (convenio!=null)?convenio.getCodigo():"";
		List listaFechasContables = new ArrayList(); 
		List listaIdsPagos = new ArrayList();
		String codErrorAux = new String("");
		
		try {
			
			//ANALIZAR LAS TRANSACCIONES DE LA RENDICION
			List lineasRendicion = (ArrayList) mapaRendicion.get(BEANS_DETALLES_RENDICION);
			for (int i = 0; i < lineasRendicion.size(); i++) {
				DetalleRendicionBBV detalleRendicionBBV = (DetalleRendicionBBV) lineasRendicion.get(i);

				if (logger.isDebugEnabled())
					logger.debug("Analizando DetalleRendicionBBV: " + detalleRendicionBBV);
			
				BigDecimal idPago = detalleRendicionBBV.getIdPago();
				BigDecimal montoRendicion = detalleRendicionBBV.getMontoPago();
				idPago = idPago!=null?idPago:new BigDecimal("-1");
				logger.info("idPago rendicion: " + idPago);
				
				//ANALIZAR EL PAGO
				logger.info("Buscar pago con idPago: " + idPago + " e idConvenio: " + idConvenio);
				Pago pago = mgrPago.getPagoByIdPagoIdConvenio(idPago, idConvenio);
				
				if (pago!=null) { //PAGO-CONVENIO EXISTE
					logger.info("Pago-Convenio Existe... /idPago: " + idPago + " /idConvenio: " + idConvenio);
					
					//ANALIZAR SI SON CANDIDATOS A NO IMPORTADOS
					if (detalleRendicionBBV.getListErrorImportacion().size()>0) {
						logger.info("detalleRendicionBBV no importada...");
						
						noImportados++;
						listaNoImportados.add(detalleRendicionBBV);
						
					} else {
						//ANALIZAR EL PAGO
						logger.info("Analizar el pago...");
						
						BigDecimal montoPago = pago.getMonto();
						int pagoPagado = (pago.getPagado()!=null)?pago.getPagado().intValue():Constants.PAGO_INICIAL.intValue();
						
						boolean pagadoOk=false;
						boolean montoOk=false;
						
						List listaInconsisAux = new ArrayList();
						if (montoPago.compareTo(montoRendicion)==0) //Si son iguales
							montoOk=true;
						if (pagoPagado==Constants.PAGO_PAGADO.intValue())
							pagadoOk=true;
						
						if (pagadoOk && montoOk) {
							//PAGO OK
							logger.info("Pago OK");
							importados++;
							consistentes++;
							listaRendicionesOK.add(detalleRendicionBBV);							
						}
						
						if (!pagadoOk) { //PAGO INCONSISTENTE, no aparece pagado
							logger.info("Pago inconsistente... no aparece pagado");
							codErrorAux = Constants.RENDIC_ERROR_INC_NO_PAGADO;
							setCodErrorDetalle(detalleRendicionBBV.getListErrorImportacion(), codErrorAux);
							
							listaInconsisAux.add(getMensajeError(codErrorAux, ""));
						}
						if (!montoOk) { //PAGO INCONSISTENTE, monto no corresponde
							logger.info("Pago inconsistente... monto no corresponde");
							codErrorAux = Constants.RENDIC_ERROR_INC_MONTO_DISTINTO;
							setCodErrorDetalle(detalleRendicionBBV.getListErrorImportacion(), codErrorAux);
							
							listaInconsisAux.add(getMensajeError(codErrorAux, "", 
									renderer.formatMoney(montoRendicion), renderer.formatMoney(montoPago)));
						}
						
						//Guardar los codError detectados en el campo del bean
						detalleRendicionBBV.setCodError(getCodErrorDetalle(detalleRendicionBBV.getListErrorImportacion()));
						
						//Valido doble (si pago o monto inconsistente)
						if (!pagadoOk || !montoOk) {
							logger.info("Pago inconsistente... se lleva a la lista correspondiente.");
							
							inconsistenteDetalleRendicion++;
							detalleRendicionBBV.setListErrorInconsistente(listaInconsisAux);
							listaInconsistentes.add(detalleRendicionBBV);
						}
					}
					
					//Guardar los ids de pagos para consulta de pagos no rendidos.
					setListaIdsPagos(idPago, listaIdsPagos);
					
				} else {
					//PAGO-CONVENIO NO EXISTE, NO IMPORTAR
					logger.info("Pago-Convenio No Existe, no importar... /idPago: " + idPago + " /idConvenio: " + idConvenio);
					
					noImportados++;
					detalleRendicionBBV.getListErrorImportacion().add(getMensajeError(Constants.RENDIC_ERROR_IMP_RENDIDO_NO_PAGADO,
											"", idPago.toString(), codConvenio));
					listaNoImportados.add(detalleRendicionBBV);
				}
			}
			
			//Guardar las fechas contables para consulta de pagos no rendidos
			setListaFechasContables(fechaContable, listaFechasContables);
			
			List listPagosNoRendidos = mgrPago.getPagosNoRendidos(listaFechasContables, listaIdsPagos, idConvenio);
			logger.info("Nro pagos_NO_Rendidos encontrados: " + listPagosNoRendidos.size());
			setListaPagosNoRendidos(listPagosNoRendidos, listaInconsistentesPagos);
			inconsistentePago = listaInconsistentesPagos.size();
			
			//Sumo las inconsistencias
			inconsistentes = inconsistenteDetalleRendicion + inconsistentePago;			
			
		} finally {
			mapa.put(NRO_IMPORTADOS, new Integer(importados));
			mapa.put(NRO_NO_IMPORTADOS, new Integer(noImportados));
			mapa.put(NRO_CONSISTENTES, new Integer(consistentes));
			mapa.put(NRO_INCONSISTENTES, new Integer(inconsistentes));
			mapa.put(NRO_INCONSISTENTES_DETS_RENDICION, new Integer(inconsistenteDetalleRendicion));
			mapa.put(LISTA_INCONSISTENTES, listaInconsistentes);
			mapa.put(LISTA_INCONSISTENTES_PAGOS, listaInconsistentesPagos);
			mapa.put(LISTA_NO_IMPORTADOS, listaNoImportados);
			mapa.put(LISTA_RENDICIONES_OK, listaRendicionesOK);
			mapa.put(FECHA_ARCHIVO_RENDICION, fechaContable);
			
			logger.info("Mapa resultante de conciliarPagos: " + Utiles.getString(mapa));
			logger.info("Sali de conciliarPagos BBV");			
		}
		return mapa;		
	}
	
	
	/**
	 * Guarda la fecha contable en un vector
	 * @param fechaContable
	 * @param fechasContables
	 */
	private void setListaFechasContables(Date fechaContable, List fechasContables) {
		Renderer renderer = new Renderer();
		logger.info("En setListaFechasContables, fecha a agregar: " + fechaContable);
		
		//Guardar las fechas contables de cada pago encontrado
		String fechaContableAuxStr = renderer.formatDateForDb(fechaContable);
		fechasContables.add(fechaContableAuxStr);
		logger.info("Fecha agregada");
	}		
	
	/**
	 * Traspaso de bean detalleRendicionBBV a bean generico
	 * @param listaRendiciones
	 * @return
	 */
	private List getDetalleRendicion(List listaRendiciones) {
		List result = new ArrayList();
		for (Iterator iter = listaRendiciones.iterator(); iter.hasNext();) {
			DetalleRendicionBBV element = (DetalleRendicionBBV) iter.next();
			DetalleRendicion detalleRendicion = new DetalleRendicion();
			
			detalleRendicion.setIdPago(element.getIdPago()!=null?element.getIdPago().toString():"0");
			detalleRendicion.setListErrorImportacion(element.getListErrorImportacion());
			detalleRendicion.setListErrorInconsistente(element.getListErrorInconsistente());
			
			result.add(detalleRendicion);
		}
		return result;
	}
	
	
	/**
	 * Metodo de pruebas. Muestra en log las rendiciones Ok e inconsistentes a importar    
	 * @param listaRendicionesOK
	 * @param listaInconsistentes
	 */
	public void showRendicionesAImportar(List listaRendicionesOK, List listaInconsistentes) {
		int i=0;
		for (Iterator iter = listaRendicionesOK.iterator(); iter.hasNext();) {
			DetalleRendicionBBV element = (DetalleRendicionBBV) iter.next();
			i++;
			if (logger.isDebugEnabled())
				logger.debug("Rendicion consistente (OK) a importar, nro " + i + ":" + element);
		}
		
		for (Iterator iter = listaInconsistentes.iterator(); iter.hasNext();) {
			DetalleRendicionBBV element = (DetalleRendicionBBV) iter.next();
			i++;
			if (logger.isDebugEnabled())
				logger.debug("Rendicion inconsistente a importar, nro " + i + ":" + element);
		}
	}
	
	/**
	 * Suma todos los montos del detalle rendicion    
	 * @param ArrayList detalleRendiciones
	 * @return BigDecimal montoTotal
	 */
	public BigDecimal sumaTotalDetalleRendiciones(List detalleRendiciones){
		
		long montoTotal = 0;
		for(Iterator it = detalleRendiciones.iterator() ; it.hasNext();){
			 DetalleRendicionBBV detalleRendicionBBV = (DetalleRendicionBBV) it.next();
			 
			 montoTotal = montoTotal + detalleRendicionBBV.getMontoPago().longValue();
		}
		
		return new BigDecimal(montoTotal);
		
	}
	
}