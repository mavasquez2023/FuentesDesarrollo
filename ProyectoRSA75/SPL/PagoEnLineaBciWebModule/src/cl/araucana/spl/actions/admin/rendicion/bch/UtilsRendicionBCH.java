package cl.araucana.spl.actions.admin.rendicion.bch;

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
import cl.araucana.spl.beans.DetalleRendicionBCH;
import cl.araucana.spl.beans.MedioPago;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.RendicionBCH;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.exceptions.RendicionException;
import cl.araucana.spl.forms.admin.rendicion.ImportarRendicionForm;
import cl.araucana.spl.mgr.MedioPagoManager;
import cl.araucana.spl.mgr.PagoManager;
import cl.araucana.spl.mgr.RendicionManager;
import cl.araucana.spl.util.ActionTools;
import cl.araucana.spl.util.MD5Checksum;
import cl.araucana.spl.util.Nulls;
import cl.araucana.spl.util.Renderer;
import cl.araucana.spl.util.Utiles;

public class UtilsRendicionBCH extends RendicionBase {
	//Constantes locales
	private static Logger logger = Logger.getLogger(UtilsRendicionBCH.class);

	private static final ResourceBundle resourceRendicion = ResourceBundle.getBundle("cl.araucana.spl.resources.RendicionBCH");
	
	private static final String SEPARADOR_CAMPOS = "|";
	private static final String SEPARADOR_SIZE = "-";

	private static final String REND_DETALLE_DESC = "BCH_DETALLE_DESC";
	private static final String REND_DETALLE_POS = "BCH_DETALLE_POS";
	private static final String REND_CONTROL_DESC = "BCH_CONTROL_DESC";
	private static final String REND_CONTROL_POS = "BCH_CONTROL_POS";

	private static final String DETALLES_KEY = "DETALLE";	
	private static final String CONTROL_KEY = "CONTROL";
	
	private static Integer TOTAL_REGISTROS_RENDICION = new Integer(0);
	
	private static List LIST_ERROR_IMPORTACION = new ArrayList();
	private static List LIST_ERROR_IMPORTACION_DETALLE = new ArrayList();	
		
	
	/**
	 * Contructor
	 */
	public UtilsRendicionBCH() {		
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
			
			//2) CONTROL
			RendicionBCH rendicionBCH = procesaControl(mapaRendicion);			
			mapaResult.put(BEAN_RENDICION, rendicionBCH);
			
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
			boolean encontradoControl = false;
			List listaDetalles = new ArrayList();
	        String linea;
	        while ((linea = in.readLine()) != null) {
	            logger.info("Linea: "  + linea + " - Largo: " + linea.length());

	            if (linea.length()<largoLinea) {
	            	throw new RendicionException("rendicion.archivo.linea.tamagnoMenor");
	            	
	            } else {
		            String tipoLinea = linea.substring(0,1);
		            String tipoLineaControl = resourceRendicion.getString("REND_TIPOLINEA_CONTROL_COD").trim();
		            
		            if (tipoLineaControl.equalsIgnoreCase(tipoLinea) && !encontradoControl) {
		            	//lectura de linea control
		            	mapa.put(CONTROL_KEY, linea);
		            	encontradoControl = true;
		            } else { 
		            	//lectura de lineas detalle
		            	listaDetalles.add(linea);
		            }
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
					int posIni = Integer.parseInt((String)pos.elementAt(0));
					int posFin = Integer.parseInt((String)pos.elementAt(1));
					
					String valorCampoLinea = linea.substring(posIni, posFin);
					valorCampoLinea = valorCampoLinea!=null?valorCampoLinea.trim():valorCampoLinea;
					
					logger.info("La columna: " + nombreCampo + ", de posicion:" + tamagno +" tiene el valor: " + valorCampoLinea);
				
					mapa.put(nombreCampo, valorCampoLinea);
				}
				/*
				idDetalleRend; idRendicion; idConvenio; idPago; codError;
				*/
				DetalleRendicionBCH detalleRendicionBCH = new DetalleRendicionBCH();
				
				//VALIDACIONES
				detalleRendicionBCH = validarRendicionDetalle(detalleRendicionBCH, mapa);
				
				listaLineas.add(detalleRendicionBCH);
			}
	        
			//Acumular los registros de la rendicion
			TOTAL_REGISTROS_RENDICION = new Integer(listaDetalles.size());
	        
	    } finally {
	    }
	    return listaLineas;
	}
	
	/**
	 * Procesa la linea de control almacenada.
	 * @param mapaRendicion
	 * @return
	 * @throws IOException
	 */
	private RendicionBCH procesaControl(Map mapaRendicion) throws IOException, ParseException {
		RendicionBCH rendicionBCH = new RendicionBCH(); 
		Map mapa = new HashMap();
		String valorKeyDesc = resourceRendicion.getString(REND_CONTROL_DESC);
		String valorKeySize = resourceRendicion.getString(REND_CONTROL_POS);		
		try {
	        String linea = (String) mapaRendicion.get(CONTROL_KEY);
	        if (linea != null) {
				Vector vecDesc = Utiles.setVector(valorKeyDesc, SEPARADOR_CAMPOS);
				Vector vecSize = Utiles.setVector(valorKeySize, SEPARADOR_CAMPOS);	        	
	        	
				for (int i = 0; i < vecSize.size(); i++) {
					String nombreCampo = (String) vecDesc.elementAt(i);
					String tamagno = (String) vecSize.elementAt(i);
										
					Vector pos = Utiles.setVector(tamagno, SEPARADOR_SIZE);
					int posIni = Integer.parseInt((String)pos.elementAt(0));
					int posFin = Integer.parseInt((String)pos.elementAt(1));
					
					String valorCampoLinea = linea.substring(posIni, posFin);
					valorCampoLinea = valorCampoLinea!=null?valorCampoLinea.trim():valorCampoLinea;
					
					logger.info("La columna: " + nombreCampo + ", de posicion:" + tamagno +" tiene el valor: " + valorCampoLinea);
				
					mapa.put(nombreCampo, valorCampoLinea);
				}
	        }
	        /*
			idRendicion; checksum; fchImportacion;			
	        */
	        
	        //VALIDACIONES
	        rendicionBCH = validarRendicionControl(rendicionBCH, mapa);
	        
	    } finally {
	    	
	    }
		return rendicionBCH;
	}
	
	/**
	 * Valida los tipos de datos del Control de RendicionBCH
	 * @param rendicionBCH
	 * @param mapa
	 * @return
	 */
	private RendicionBCH validarRendicionControl(RendicionBCH rendicionBCH,Map mapa) throws ParseException {

		String fechaCapturaStr = (String) mapa.get("FCH_CAPTURA"); //Fecha transaccion (es una para todos los movimientos informados)
        String nroRegistrosStr = (String) mapa.get("NRO_REGISTROS");
        
        Integer fechaCaptura = valInteger.validate(fechaCapturaStr);
        Integer nroRegistros = valInteger.validate(nroRegistrosStr);
        
        if (fechaCaptura==null) { //No es numero
        	LIST_ERROR_IMPORTACION.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, CONTROL_KEY,
					"Fecha captura", fechaCapturaStr));
        }
        if (nroRegistros==null) { //No es numero
        	LIST_ERROR_IMPORTACION.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, CONTROL_KEY,
					"Nro. registros", nroRegistrosStr));
        }
        
        //Validacion contenido
		if (nroRegistros!=null) {
			if (nroRegistros.compareTo(TOTAL_REGISTROS_RENDICION)!=0) {
				logger.info("Numero de registros no coincide");
	        	LIST_ERROR_IMPORTACION.add(getMensajeError(Constants.RENDIC_ERROR_IMP_REGISTROS_TOTALES,
						CONTROL_KEY, nroRegistros.toString(), TOTAL_REGISTROS_RENDICION.toString()));
			}
		}
        
		//SETEOS
		rendicionBCH.setFchCaptura((fechaCaptura==null)?Nulls.DATE:Utiles.getFechaParse("yyyyMMdd", fechaCapturaStr));
        rendicionBCH.setNroRegistros(getBigDecimal(nroRegistros));
        
        rendicionBCH.setListErrorImportacion(LIST_ERROR_IMPORTACION);
        
        return rendicionBCH;
	}

	
	/**
	 * Valida los tipos de datos del Detalle de la RendicionBCH
	 * @param detalleRendicionBCH
	 * @param mapa
	 * @return
	 */
	private DetalleRendicionBCH validarRendicionDetalle(DetalleRendicionBCH detalleRendicionBCH,Map mapa) throws ParseException {
		LIST_ERROR_IMPORTACION_DETALLE = new ArrayList();
		RendicionManager mgrRendicion = (RendicionManager) mapa.get(MGR_RENDICION);
		
		String codEmpresaStr = (String) mapa.get("COD_EMPRESA");
		String oficinaCapturaStr = (String) mapa.get("OFIC_CAPTURA");
		String fechaPagoStr = (String) mapa.get("FCH_PAGO"); //Fecha Contable
		String montoPagadoStr = (String) mapa.get("MONTO_PAGADO"); //Monto pagado al banco (es igual al monto informado)
		String medioPagoStr = (String) mapa.get("MEDIO_PAGO");
		String montoPagadoMonedaOrigenStr = (String) mapa.get("MONTO_PAGADO_MONEDA_ORIGEN");
		String tipoCambioStr = (String) mapa.get("VALOR_TIPO_CAMBIO");
		String montoPagadoMoraStr = (String) mapa.get("MONTO_PAGADO_MORA");
		String montoInformadoStr = (String) mapa.get("MONTO_INFORMADO");

		//Las primeras 16 posiciones de "campos agregados" contiene el idPago.
		//El idPago el banco lo informa concatenado con idConvenio
		//ejemplo: 1234500000000001 (cod conv 12345 e idPago 1)
		String camposAgregadosStr = (String) mapa.get("CAMPOS_AGREGADOS");
		String idPagoStr = getIdPagoCampoAgreg(camposAgregadosStr); 
		
		//VALIDACIONES
		Integer codEmpresa = valInteger.validate(codEmpresaStr);
		Integer oficinaCaptura = valInteger.validate(oficinaCapturaStr);
		Integer fechaPago = valInteger.validate(fechaPagoStr);
		Integer montoPagado = valInteger.validate(montoPagadoStr);
		Integer medioPago = valInteger.validate(medioPagoStr);
		BigDecimal montoPagadoMonedaOrigen = valBigDecimal.validate(montoPagadoMonedaOrigenStr);
		BigDecimal tipoCambio = valBigDecimal.validate(tipoCambioStr);
		BigDecimal montoPagadoMora = valBigDecimal.validate(montoPagadoMoraStr);
		Integer montoInformado = valInteger.validate(montoInformadoStr);		
		BigDecimal idPago = valBigDecimal.validate(idPagoStr);
		
		//Validar que el pago no haya sido rendido con anterioridad
		if (idPago!=null) {			
			DetalleRendicionBCH detalleRendicionBCHAux = mgrRendicion.getDetalleRendicionBchByPagoId(idPago);
			if (detalleRendicionBCHAux!=null) {
				LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_YA_RENDIDO, 
						"", idPago.toString()));
			}
		}
		
		//Validar tipo de datos
        if (idPago == null) // No es numero
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Id pago", idPagoStr));

		if (codEmpresa == null) // No es numero
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "C&oacute;digo empresa", codEmpresaStr));

		if (oficinaCaptura == null) // No es numero
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Oficina captura", oficinaCapturaStr));

		if (fechaPago == null) // No es numero
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Fecha pago", fechaPagoStr));

		if (montoPagado == null) // No es numero
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Monto pagado", montoPagadoStr));

		if (medioPago == null) // No es numero
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Medio pago", medioPagoStr));

		if (montoPagadoMonedaOrigen == null) // No es numero
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Monto pagado moneda origen", montoPagadoMonedaOrigenStr));

		if (tipoCambio == null) // No es numero
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Tipo cambio", tipoCambioStr));

		if (montoPagadoMora == null) // No es numero
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Monto pagado mora", montoPagadoMoraStr));

		if (montoInformado == null) // No es numero
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Monto informado", montoInformadoStr));
		
		// En este banco No se valida contenido de la informacion
		
		//SETEOS
        detalleRendicionBCH.setIdPago(idPago!=null?idPago:new BigDecimal(0));
        detalleRendicionBCH.setCodEmpresa(getBigDecimal(codEmpresa));
        detalleRendicionBCH.setOficinaCaptura(getBigDecimal(oficinaCaptura));
        detalleRendicionBCH.setFchPago(fechaPago==null?Nulls.DATE:Utiles.getFechaParse("yyyyMMdd", fechaPagoStr));
        detalleRendicionBCH.setMontoPagado(getBigDecimal(montoPagado));
        detalleRendicionBCH.setMedioPago(getBigDecimal(medioPago));
        detalleRendicionBCH.setMontoPagadoMonedaOrigen(getBigDecimal(montoPagadoMonedaOrigen, NRO_DECIMALES_MONTOS_BCH));
        detalleRendicionBCH.setTipoCambio(getBigDecimal(tipoCambio, NRO_DECIMALES_MONTOS_BCH));
        detalleRendicionBCH.setMontoMora(getBigDecimal(montoPagadoMora, NRO_DECIMALES_MONTOS_BCH));
        detalleRendicionBCH.setMontoInformado(getBigDecimal(montoInformado));
        detalleRendicionBCH.setCamposAgregados(camposAgregadosStr);
        
        detalleRendicionBCH.setListErrorImportacion(LIST_ERROR_IMPORTACION_DETALLE);
        return detalleRendicionBCH;
	}

	/**
	 * Extrae el idPago que viene concatenado con el idConvenio.
	 * @param camposAgregadosStr
	 * @return String idPago
	 */
	private static String getIdPagoCampoAgreg(String camposAgregadosStr) {
		String result = "";
		if (camposAgregadosStr!=null) {
			if (camposAgregadosStr.length()>15) {
				result = camposAgregadosStr.substring(5, 16);
			}
		}
		logger.info("getIdPagoCampoAgreg... result: " + result + " /camposAgregadosStr: " + camposAgregadosStr);
		return result;
	}
	
	/**
	 * Metodo de inicio de la logica de procesamiento de rendicion BCH
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
			RendicionBCH rendicionBCH = (RendicionBCH) mapaRendicion.get(BEAN_RENDICION);
			if (rendicionBCH!=null) {
				List listaErrorImportacionCabecera = rendicionBCH.getListErrorImportacion();
				
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
					Date fechaContable = (Date) mapaConciliacion.get(FECHA_ARCHIVO_RENDICION);
					rendicionBCH.setChecksum(checksum);
					rendicionBCH.setFchCaptura(fechaContable);
													
					//Guardo en la session
					session.setAttribute(BEAN_RENDICION, rendicionBCH);
					session.setAttribute(CODIGO_MEDIO_PAGO, codBanco);
					session.setAttribute(LISTA_INCONSISTENTES, listaInconsistentes);
					session.setAttribute(LISTA_INCONSISTENTES_PAGOS, listaInconsistentesPagos);
					session.setAttribute(LISTA_RENDICIONES_OK, listaRendicionesOK);
					
				} else {
					//Cabecera con errores, informar al usuario
					logger.info("Existe errores de importacion, no se concilia");
					ActionTools.addMessage(request, new ActionMessage("rendicion.archivo.importacion.ErrorCabeceraControl"));
					frm.setFlagErrorCabeceraControl("1");
					
					frm.setListaNoImportados((ArrayList) rendicionBCH.getListErrorImportacion());
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
		logger.info("Entre a conciliarPagos BCH");
		
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
				DetalleRendicionBCH detalleRendicionBCH = (DetalleRendicionBCH) lineasRendicion.get(i);

				if (logger.isDebugEnabled())
					logger.debug("Analizando DetalleRendicionBCH: " + detalleRendicionBCH);
			
				BigDecimal idPago = detalleRendicionBCH.getIdPago();
				BigDecimal montoRendicion = detalleRendicionBCH.getMontoPagado();
				idPago = idPago!=null?idPago:new BigDecimal("-1");
				logger.info("idPago rendicion: " + idPago);
				
				//ANALIZAR EL PAGO
				logger.info("Buscar pago con idPago: " + idPago + " e idConvenio: " + idConvenio);
				Pago pago = mgrPago.getPagoByIdPagoIdConvenio(idPago, idConvenio);
				
				if (pago!=null) { //PAGO-CONVENIO EXISTE
					logger.info("Pago-Convenio Existe... /idPago: " + idPago + " /idConvenio: " + idConvenio);
					
					//ANALIZAR SI SON CANDIDATOS A NO IMPORTADOS
					if (detalleRendicionBCH.getListErrorImportacion().size()>0) {
						logger.info("detalleRendicionBCH no importada...");
						
						noImportados++;
						listaNoImportados.add(detalleRendicionBCH);
						
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
							listaRendicionesOK.add(detalleRendicionBCH);							
						}
						
						if (!pagadoOk) { //PAGO INCONSISTENTE, no aparece pagado
							logger.info("Pago inconsistente... no aparece pagado");
							codErrorAux = Constants.RENDIC_ERROR_INC_NO_PAGADO;
							setCodErrorDetalle(detalleRendicionBCH.getListErrorImportacion(), codErrorAux);
							
							listaInconsisAux.add(getMensajeError(codErrorAux, ""));
						}
						if (!montoOk) { //PAGO INCONSISTENTE, monto no corresponde
							logger.info("Pago inconsistente... monto no corresponde");
							codErrorAux = Constants.RENDIC_ERROR_INC_MONTO_DISTINTO;
							setCodErrorDetalle(detalleRendicionBCH.getListErrorImportacion(), codErrorAux);
							
							listaInconsisAux.add(getMensajeError(codErrorAux, "", 
									renderer.formatMoney(montoRendicion), renderer.formatMoney(montoPago)));
						}
						
						//Guardar los codError detectados en el campo del bean
						detalleRendicionBCH.setCodError(getCodErrorDetalle(detalleRendicionBCH.getListErrorImportacion()));
						
						//Valido doble (si pago o monto inconsistente)
						if (!pagadoOk || !montoOk) {
							logger.info("Pago inconsistente... se lleva a la lista correspondiente.");
							
							inconsistenteDetalleRendicion++;
							detalleRendicionBCH.setListErrorInconsistente(listaInconsisAux);
							listaInconsistentes.add(detalleRendicionBCH);
						}
					}
					
					//Guardar los ids de pagos para consulta de pagos no rendidos.
					setListaIdsPagos(idPago, listaIdsPagos);
					
				} else {
					//PAGO-CONVENIO NO EXISTE, NO IMPORTAR
					logger.info("Pago-Convenio No Existe, no importar... /idPago: " + idPago + " /idConvenio: " + idConvenio);
					
					noImportados++;
					detalleRendicionBCH.getListErrorImportacion().add(getMensajeError(Constants.RENDIC_ERROR_IMP_RENDIDO_NO_PAGADO,
											"", idPago.toString(), codConvenio));
					listaNoImportados.add(detalleRendicionBCH);
				}
			}
			
			//BUSCAR POR FCH CONTABLE PAGOS NO RENDIDOS (CANDIDATOS A INCONSISTENTES)
			RendicionBCH rendicionBCH = (RendicionBCH) mapaRendicion.get(BEAN_RENDICION);
			fechaContable = rendicionBCH.getFchCaptura();			
		
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
			logger.info("Sali de conciliarPagos BCH");			
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
	 * Traspaso de bean detalleRendicionBCH a bean generico
	 * @param listaRendiciones
	 * @return
	 */
	private List getDetalleRendicion(List listaRendiciones) {
		List result = new ArrayList();
		for (Iterator iter = listaRendiciones.iterator(); iter.hasNext();) {
			DetalleRendicionBCH element = (DetalleRendicionBCH) iter.next();
			DetalleRendicion detalleRendicion = new DetalleRendicion();
			
			detalleRendicion.setIdPago(element.getIdPago()!=null?element.getIdPago().toString():"0");
			detalleRendicion.setDetalle("Cod.Empresa: "+element.getCodEmpresa().toString());
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
			DetalleRendicionBCH element = (DetalleRendicionBCH) iter.next();
			i++;
			if (logger.isDebugEnabled())
				logger.debug("Rendicion consistente (OK) a importar, nro " + i + ":" + element);
		}
		
		for (Iterator iter = listaInconsistentes.iterator(); iter.hasNext();) {
			DetalleRendicionBCH element = (DetalleRendicionBCH) iter.next();
			i++;
			if (logger.isDebugEnabled())
				logger.debug("Rendicion inconsistente a importar, nro " + i + ":" + element);
		}
	}
	
}