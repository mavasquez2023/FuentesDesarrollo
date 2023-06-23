package cl.araucana.spl.actions.admin.rendicion.bsa;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import cl.araucana.spl.base.Constants;
import cl.araucana.spl.base.RendicionBase;
import cl.araucana.spl.beans.Convenio;
import cl.araucana.spl.beans.DetalleRendicion;
import cl.araucana.spl.beans.DetalleRendicionBSA;
import cl.araucana.spl.beans.MedioPago;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.RendicionBSA;
import cl.araucana.spl.beans.xmlbsa.ArchivoRendicion;
import cl.araucana.spl.beans.xmlbsa.DetallePagos;
import cl.araucana.spl.beans.xmlbsa.TotalizadorPagos;
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

public class UtilsRendicionBSA extends RendicionBase {
	//Constantes locales
	private static Logger logger = Logger.getLogger(UtilsRendicionBSA.class);
		
	private static final String DETALLES_KEY = "DETALLE";	
	private static final String CONTROL_KEY = "CONTROL";
	
	private static Integer TOTAL_MONTO_RENDICION = new Integer(0);
	private static Integer TOTAL_REGISTROS_RENDICION = new Integer(0);
	
	private static List LIST_ERROR_IMPORTACION = new ArrayList();
	private static List LIST_ERROR_IMPORTACION_DETALLE = new ArrayList();	
	
	/**
	 * Contructor
	 */
	public UtilsRendicionBSA() {
		TOTAL_MONTO_RENDICION = new Integer(0);
		TOTAL_REGISTROS_RENDICION = new Integer(0);
		LIST_ERROR_IMPORTACION = new ArrayList();
	}
	
	/**
	 * Busca los errores de importacion de una rendicion.
	 * Setea en un Map el bean de Rendicion y los detalles de las rendiciones.
	 * @param pathCompArchivo
	 * @return
	 * @throws IOException
	 * @throws SAXException 
	 */
	protected Map procesarArchivoRendicion(InputStream is, String codigoConvenio, RendicionManager mgrRendicion) 
		throws RendicionException {
		Map mapaResult = new HashMap();
		logger.info("Estoy en procesarArchivoRendicion");
		
		try {
			Map mapaRendicion = leerArchivoRendicionXml(is);
			mapaRendicion.put(CODIGO_CONVENIO, codigoConvenio);
			mapaRendicion.put(MGR_RENDICION, mgrRendicion);
			
			//Archivo se debe procesar en Orden: 1)Lineas, 2)Control
			
			//1) LINEAS DETALLE
			List listaLineas = procesaLineas(mapaRendicion);
			mapaResult.put(BEANS_DETALLES_RENDICION, listaLineas);
			
			//2) CONTROL
			RendicionBSA rendicionBSA = procesaControl(mapaRendicion);			
			mapaResult.put(BEAN_RENDICION, rendicionBSA);
			
	    } 
		catch (SAXException e) {
			logger.error("Archivo de rendicion no es valido");
			throw new RendicionException("rendicion.archivo.general", e);
		} catch (IOException e) {
			throw new RendicionException("rendicion.archivo.general", e);
		} catch (ParserConfigurationException e) {
			throw new RendicionException("rendicion.archivo.general", e);
		} catch (ParseException e) {
			throw new RendicionException("rendicion.archivo.general", e);
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
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	private Map leerArchivoRendicionXml(InputStream is) throws IOException, RendicionException, 
		SAXException, ParserConfigurationException {
		Map mapa = new HashMap();
		UtilsDomBSA utilsDom = new UtilsDomBSA();
		try {
			String xml = Utiles.getString(is);
			if (logger.isDebugEnabled())
				logger.debug("XML transformado de InputStream a String: " + xml);
			
			xml = utilsDom.normalizarString(xml);
			logger.info("XML despues de normalizar: " + xml);
			
			Document doc = utilsDom.getDocument(xml);
			ArchivoRendicion archivoRendicion = utilsDom.umArchivoRendicion(doc.getDocumentElement());
			
			mapa.put(CONTROL_KEY, archivoRendicion.getTotalizador());
			mapa.put(DETALLES_KEY, archivoRendicion.getDetalles());
			
	    } finally {
	    	
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
	        List listaDetalles = (ArrayList) mapaRendicion.get(DETALLES_KEY);
	        for (int i = 0; i < listaDetalles.size(); i++) {
	        	DetallePagos detalle = (DetallePagos) listaDetalles.get(i);
	        	logger.info("DetallePagos rendicion... DescProducto (idPago): " + detalle.getDescProducto());
	        	
	        	if (logger.isDebugEnabled())
	        		logger.debug("DetallePago XML: " + detalle);
	        	
	        	mapa.put("DETALLE_BSA", detalle);
				/*
				idDetalleRend; idRendicion; idConvenio; idPago; codError;
				*/
				DetalleRendicionBSA detalleRendicionBSA = new DetalleRendicionBSA();
				
				//VALIDACIONES
				detalleRendicionBSA = validarRendicionDetalle(detalleRendicionBSA, mapa);
				
				listaLineas.add(detalleRendicionBSA);
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
	private RendicionBSA procesaControl(Map mapaRendicion) throws IOException, ParseException {
		RendicionBSA rendicionBSA = new RendicionBSA(); 
		Map mapa = new HashMap();
		try {
			TotalizadorPagos totalizadorPagos = (TotalizadorPagos) mapaRendicion.get(CONTROL_KEY);
			logger.info("TotalizadorPagos XML :" + totalizadorPagos);
			
	        mapa.put("CONTROL_BSA", totalizadorPagos);
	        /*
			idRendicion; checksum; fchImportacion;			
	        */
	        //VALIDACIONES
	        rendicionBSA = validarRendicionControl(rendicionBSA, mapa);
	        
	    } finally {
	    	
	    }
		return rendicionBSA;
	}
	
	/**
	 * Valida los tipos de datos del Control de RendicionBCH
	 * @param rendicionBSA
	 * @param mapa
	 * @return
	 */
	private RendicionBSA validarRendicionControl(RendicionBSA rendicionBSA,Map mapa) throws ParseException {
		
		TotalizadorPagos control = (TotalizadorPagos) mapa.get("CONTROL_BSA");
		Renderer renderer = new Renderer();
		
		String montoTotalStr = control.getMontoTotal();
        String nroPagosStr = control.getNumeroPagos();
        
        Integer montoTotal = valInteger.validate(montoTotalStr);
        Integer nroPagos = valInteger.validate(nroPagosStr);
        
        if (montoTotal == null) { // No es numero
			LIST_ERROR_IMPORTACION.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					CONTROL_KEY, "Monto total", montoTotalStr));
		}
		if (nroPagos == null) { // No es numero
			LIST_ERROR_IMPORTACION.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					CONTROL_KEY, "Nro. pagos", nroPagosStr));
		}
        
        //Validacion contenido
		if (montoTotal!=null) {
			if (montoTotal.compareTo(TOTAL_MONTO_RENDICION)!=0) {
				logger.info("Monto total no coincide");
				BigDecimal montoTotalAux = new BigDecimal(montoTotal.doubleValue());
				BigDecimal totalMontoRendicionAux = new BigDecimal(TOTAL_MONTO_RENDICION.doubleValue());
				
	        	LIST_ERROR_IMPORTACION.add(getMensajeError(Constants.RENDIC_ERROR_IMP_MONTO_TOTAL, CONTROL_KEY,
						renderer.formatMoney(montoTotalAux), renderer.formatMoney(totalMontoRendicionAux)));
			}
		}
		if (nroPagos!=null) {
			if (nroPagos.compareTo(TOTAL_REGISTROS_RENDICION)!=0) {
				logger.info("Numero de registros no coincide");

				LIST_ERROR_IMPORTACION.add(getMensajeError(Constants.RENDIC_ERROR_IMP_REGISTROS_TOTALES,
						CONTROL_KEY, nroPagos.toString(), TOTAL_REGISTROS_RENDICION.toString()));
			}
		}
        
		//SETEOS
		rendicionBSA.setMontoTotal(getBigDecimal(montoTotal));
        rendicionBSA.setNroPagos(getBigDecimal(nroPagos));
        
        rendicionBSA.setListErrorImportacion(LIST_ERROR_IMPORTACION);
        
        return rendicionBSA;
	}

	
	/**
	 * Valida los tipos de datos del Detalle de la RendicionBSA
	 * @param detalleRendicionBSA
	 * @param mapa
	 * @return
	 */
	private DetalleRendicionBSA validarRendicionDetalle(DetalleRendicionBSA detalleRendicionBSA,Map mapa) throws ParseException {
		LIST_ERROR_IMPORTACION_DETALLE = new ArrayList();
		DetallePagos detalleBSA = (DetallePagos) mapa.get("DETALLE_BSA"); 
		RendicionManager mgrRendicion = (RendicionManager) mapa.get(MGR_RENDICION);
		
		String idCarroStr = detalleBSA.getIdCarro();
		String idConvenioStr = detalleBSA.getIdConvenio();
		String numeroProductoStr = detalleBSA.getNumeroProducto();
		String numeroClienteStr = detalleBSA.getNumeroCliente();
		String fechaExpiracionProductoStr = detalleBSA.getExpiracionProducto();
		String descProductoStr = detalleBSA.getDescProducto(); //aqui viene numero del idPago
		String montoProductoStr = detalleBSA.getMontoProducto();
		String fechaHoraOperacionStr = detalleBSA.getFechaHoraOperacion();
		//idAtributo y numeroOperacion NO APLICAN segun el archivo formato del banco.
		
		//VALIDACIONES
		BigDecimal idPago = valBigDecimal.validate(descProductoStr);
		BigDecimal idConvenio = valBigDecimal.validate(idConvenioStr);
		Integer numeroProducto = valInteger.validate(numeroProductoStr);
		BigDecimal montoProducto = valBigDecimal.validate(montoProductoStr);
		
		//Validar que el pago no haya sido rendido con anterioridad
		if (idPago!=null) {
			DetalleRendicionBSA detalleRendicionBSAAux = mgrRendicion.getDetalleRendicionBsaByPagoId(idPago);
			if (detalleRendicionBSAAux!=null) {
				LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_YA_RENDIDO, 
						"", idPago.toString()));
			}
		}
		
		//Validar tipo de datos
        if (idPago == null) // No es numero
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Id pago", descProductoStr));

		if (idConvenio == null) // No es numero
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Id convenio", idConvenioStr));

		if (numeroProducto == null) // No es numero
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Nro producto", numeroProductoStr));

		if (montoProducto == null) // No es numero
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Monto producto", montoProductoStr));
        
        Date fechaExpiracionProd = fechaExpiracionProductoStr == null ? Nulls.DATE
				: Utiles.getFechaParse("dd/MM/yyyy", fechaExpiracionProductoStr);
		Date fechaHoraOperacion = fechaHoraOperacionStr == null ? Nulls.DATE
				: Utiles.getFechaParse("dd/MM/yyyyHH:mm:ss", fechaHoraOperacionStr);
        
        //En este banco No se valida contenido predeterminado de la informacion
        
		//Acumular el monto de la trx
		if (montoProducto!=null) {
			TOTAL_MONTO_RENDICION = new Integer(TOTAL_MONTO_RENDICION.intValue() + montoProducto.intValue());
		}
        		
		//SETEOS
        detalleRendicionBSA.setIdPago(idPago!=null?idPago:new BigDecimal(0));
        detalleRendicionBSA.setIdCarro(idCarroStr);
        detalleRendicionBSA.setIdConvenio(idConvenio);
        detalleRendicionBSA.setNroProducto(getBigDecimal(numeroProducto));
        detalleRendicionBSA.setNroCliente(numeroClienteStr);
        detalleRendicionBSA.setFchExpiracion(fechaExpiracionProd);
        detalleRendicionBSA.setDescProducto(descProductoStr);
        detalleRendicionBSA.setMontoProducto(montoProducto);
        detalleRendicionBSA.setFchHoraOperacion(fechaHoraOperacion);
        
        detalleRendicionBSA.setListErrorImportacion(LIST_ERROR_IMPORTACION_DETALLE);
        return detalleRendicionBSA;
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
			RendicionBSA rendicionBSA = (RendicionBSA) mapaRendicion.get(BEAN_RENDICION);
			if (rendicionBSA!=null) {
				List listaErrorImportacionCabecera = rendicionBSA.getListErrorImportacion();
				
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
					rendicionBSA.setChecksum(checksum);
					
					//En el BSA la fecha contable viene en el detalle
					/*Date fechaContable = (Date) mapaConciliacion.get(FECHA_ARCHIVO_RENDICION);
					rendicionBSA.setFchCaptura(fechaContable);*/
													
					//Guardo en la session
					session.setAttribute(BEAN_RENDICION, rendicionBSA);
					session.setAttribute(CODIGO_MEDIO_PAGO, codBanco);
					session.setAttribute(LISTA_INCONSISTENTES, listaInconsistentes);
					session.setAttribute(LISTA_INCONSISTENTES_PAGOS, listaInconsistentesPagos);
					session.setAttribute(LISTA_RENDICIONES_OK, listaRendicionesOK);
					
				} else {
					//Cabecera con errores, informar al usuario
					logger.info("Existe errores de importacion, no se concilia");
					ActionTools.addMessage(request, new ActionMessage("rendicion.archivo.importacion.ErrorCabeceraControl"));
					frm.setFlagErrorCabeceraControl("1");
					
					frm.setListaNoImportados((ArrayList) rendicionBSA.getListErrorImportacion());
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
		logger.info("Entre a conciliarPagos BSA");
		
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
				DetalleRendicionBSA detalleRendicionBSA = (DetalleRendicionBSA) lineasRendicion.get(i);

				if (logger.isDebugEnabled())
					logger.debug("Analizando DetalleRendicionBSA: " + detalleRendicionBSA);
				
				BigDecimal idPago = detalleRendicionBSA.getIdPago();
				BigDecimal montoRendicion = detalleRendicionBSA.getMontoProducto();
				idPago = idPago!=null?idPago:new BigDecimal("-1");
				logger.info("idPago rendicion: " + idPago);
				
				//ANALIZAR EL PAGO
				logger.info("Buscar pago con idPago: " + idPago + " e idConvenio: " + idConvenio);
				Pago pago = mgrPago.getPagoByIdPagoIdConvenio(idPago, idConvenio);
				
				if (pago!=null) { //PAGO-CONVENIO EXISTE
					logger.info("Pago-Convenio Existe... /idPago: " + idPago + " /idConvenio: " + idConvenio);
					
					//ANALIZAR SI SON CANDIDATOS A NO IMPORTADOS
					if (detalleRendicionBSA.getListErrorImportacion().size()>0) {
						logger.info("detalleRendicionBSA no importada...");
						
						noImportados++;
						listaNoImportados.add(detalleRendicionBSA);
						
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
							listaRendicionesOK.add(detalleRendicionBSA);
						}
						
						if (!pagadoOk) { //PAGO INCONSISTENTE, no aparece pagado
							logger.info("Pago inconsistente... no aparece pagado");
							codErrorAux = Constants.RENDIC_ERROR_INC_NO_PAGADO;
							setCodErrorDetalle(detalleRendicionBSA.getListErrorImportacion(), codErrorAux);
							
							listaInconsisAux.add(getMensajeError(codErrorAux, ""));
						}
						if (!montoOk) { //PAGO INCONSISTENTE, monto no corresponde
							logger.info("Pago inconsistente... monto no corresponde");
							codErrorAux = Constants.RENDIC_ERROR_INC_MONTO_DISTINTO;
							setCodErrorDetalle(detalleRendicionBSA.getListErrorImportacion(), codErrorAux);
							
							listaInconsisAux.add(getMensajeError(codErrorAux, "", 
									renderer.formatMoney(montoRendicion), renderer.formatMoney(montoPago)));
						}
						
						//Guardar los codError detectados en el campo del bean
						detalleRendicionBSA.setCodError(getCodErrorDetalle(detalleRendicionBSA.getListErrorImportacion()));
						
						//Valido doble (si pago o monto inconsistente)
						if (!pagadoOk || !montoOk) {
							logger.info("Pago inconsistente... se lleva a la lista correspondiente.");
							
							inconsistenteDetalleRendicion++;
							detalleRendicionBSA.setListErrorInconsistente(listaInconsisAux);
							listaInconsistentes.add(detalleRendicionBSA);
						}
					}
					
					//Guardar las fechas contables para consulta de pagos no rendidos
					setListaFechasContables(detalleRendicionBSA, listaFechasContables);
					
					//Guardar los ids de pagos para consulta de pagos no rendidos.
					setListaIdsPagos(idPago, listaIdsPagos);
					
					
				} else {
					//PAGO-CONVENIO NO EXISTE, NO IMPORTAR
					logger.info("Pago-Convenio No Existe, no importar... /idPago: " + idPago + " /idConvenio: " + idConvenio);
					
					noImportados++;
					detalleRendicionBSA.getListErrorImportacion().add(getMensajeError(Constants.RENDIC_ERROR_IMP_RENDIDO_NO_PAGADO,
											"", idPago.toString(), codConvenio));
					listaNoImportados.add(detalleRendicionBSA);
				}
			}
			
			//BUSCAR POR FCH CONTABLE PAGOS NO RENDIDOS (CANDIDATOS A INCONSISTENTES)
			//En BSA la fecha contable viene en el detalle, por lo que tenemos una lista de fechas (es posible)
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
			logger.info("Sali de conciliarPagos BSA");
		}
		return mapa;		
	}
	
	/**
	 * Guardar las fechas contables de los detalles.
	 * Se valida no repetir el valor de una fecha.
	 * @param detalleRendicionBSA
	 * @param fechasContables
	 */
	private void setListaFechasContables(DetalleRendicionBSA detalleRendicionBSA, List fechasContables) {
		Renderer renderer = new Renderer();
		Date fchHoraOperacion = detalleRendicionBSA.getFchHoraOperacion(); 
		
		logger.info("En setListaFechasContables, fecha a agregar: " + fchHoraOperacion);
		
		//Guardar las fechas contables de cada pago encontrado
		String fechaContableAuxStr = renderer.formatDateForDb(fchHoraOperacion);
		if (!fechasContables.contains(fechaContableAuxStr)) {
			fechasContables.add(fechaContableAuxStr);
			logger.info("Fecha agregada");
		} else {
			logger.info("Fecha ya esta en el vector");
		}
	}
	
	/**
	 * Traspaso de bean detalleRendicionBCH a bean generico
	 * @param listaRendiciones
	 * @return
	 */
	private List getDetalleRendicion(List listaRendiciones) {
		List result = new ArrayList();
		for (Iterator iter = listaRendiciones.iterator(); iter.hasNext();) {
			DetalleRendicionBSA element = (DetalleRendicionBSA) iter.next();
			DetalleRendicion detalleRendicion = new DetalleRendicion();
			
			detalleRendicion.setIdPago(element.getIdPago()!=null?element.getIdPago().toString():"0");
			detalleRendicion.setDetalle("Cod.Cliente: "+element.getNroCliente());
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
			DetalleRendicionBSA element = (DetalleRendicionBSA) iter.next();
			i++;
			if (logger.isDebugEnabled())
				logger.debug("Rendicion consistente (OK) a importar, nro " + i + ":" + element);				
		}
		
		for (Iterator iter = listaInconsistentes.iterator(); iter.hasNext();) {
			DetalleRendicionBSA element = (DetalleRendicionBSA) iter.next();
			i++;
			if (logger.isDebugEnabled())				
				logger.debug("Rendicion inconsistente a importar, nro " + i + ":" + element);
		}
	}		

}