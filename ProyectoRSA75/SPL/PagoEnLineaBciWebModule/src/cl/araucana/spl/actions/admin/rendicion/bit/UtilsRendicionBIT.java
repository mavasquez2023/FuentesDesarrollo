package cl.araucana.spl.actions.admin.rendicion.bit;

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
import cl.araucana.spl.beans.DetalleRendicionBIT;
import cl.araucana.spl.beans.MedioPago;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.RendicionBIT;
import cl.araucana.spl.beans.xmlbit.ArchivoRendicion;
import cl.araucana.spl.beans.xmlbit.DetallePagos;
import cl.araucana.spl.beans.xmlbit.TotalizadorPagos;
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

/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 13-01-2014 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Gonzalo Mallea Lorca <BR> gmallea@schema.cl </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Gonzalo Mallea Lorca (gmallea@schema.cl)
 *
 * @version 1.0
 */

public class UtilsRendicionBIT extends RendicionBase{
	
		//Constantes locales
		private static Logger logger = Logger.getLogger(UtilsRendicionBIT.class);
			
		private static final String DETALLES_KEY = "DETALLE";	
		private static final String CONTROL_KEY = "CONTROL";
		
		private static Integer TOTAL_MONTO_RENDICION = new Integer(0);
		private static Integer TOTAL_REGISTROS_RENDICION = new Integer(0);
		
		private static List LIST_ERROR_IMPORTACION = new ArrayList();
		private static List LIST_ERROR_IMPORTACION_DETALLE = new ArrayList();	
		
		/**
		 * Contructor
		 */
		public UtilsRendicionBIT() {
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
				RendicionBIT rendicionBIT = procesaControl(mapaRendicion);			
				mapaResult.put(BEAN_RENDICION, rendicionBIT);
				
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
			UtilsDomBIT utilsDom = new UtilsDomBIT();
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
		        	
		        	mapa.put("DETALLE_BIT", detalle);
					/*
					idDetalleRend; idRendicion; idConvenio; idPago; codError;
					*/
					DetalleRendicionBIT detalleRendicionBIT = new DetalleRendicionBIT();
					
					//VALIDACIONES
					detalleRendicionBIT = validarRendicionDetalle(detalleRendicionBIT, mapa);
					
					listaLineas.add(detalleRendicionBIT);
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
		private RendicionBIT procesaControl(Map mapaRendicion) throws IOException, ParseException {
			RendicionBIT rendicionBIT = new RendicionBIT(); 
			Map mapa = new HashMap();
			try {
				TotalizadorPagos totalizadorPagos = (TotalizadorPagos) mapaRendicion.get(CONTROL_KEY);
				logger.info("TotalizadorPagos XML :" + totalizadorPagos);
				
		        mapa.put("CONTROL_BIT", totalizadorPagos);
		        /*
				idRendicion; checksum; fchImportacion;			
		        */
		        //VALIDACIONES
		        rendicionBIT = validarRendicionControl(rendicionBIT, mapa);
		        
		    } finally {
		    	
		    }
			return rendicionBIT;
		}
		
		/**
		 * Valida los tipos de datos del Control de RendicionBIT
		 * @param rendicionBIT
		 * @param mapa
		 * @return
		 */
		private RendicionBIT validarRendicionControl(RendicionBIT rendicionBIT,Map mapa) throws ParseException {
			
			TotalizadorPagos control = (TotalizadorPagos) mapa.get("CONTROL_BIT");
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
			rendicionBIT.setMontoTotal(getBigDecimal(montoTotal));
			rendicionBIT.setNroPagos(getBigDecimal(nroPagos));
	        
			rendicionBIT.setListErrorImportacion(LIST_ERROR_IMPORTACION);
	        
	        return rendicionBIT;
		}

		
		/**
		 * Valida los tipos de datos del Detalle de la RendicionBIT
		 * @param detalleRendicionBIT
		 * @param mapa
		 * @return
		 */
		private DetalleRendicionBIT validarRendicionDetalle(DetalleRendicionBIT detalleRendicionBIT,Map mapa) throws ParseException {
			LIST_ERROR_IMPORTACION_DETALLE = new ArrayList();
			DetallePagos detalleBIT = (DetallePagos) mapa.get("DETALLE_BIT"); 
			RendicionManager mgrRendicion = (RendicionManager) mapa.get(MGR_RENDICION);
			
			
			String idConvenioStr = detalleBIT.getIdConvenio();
			String numeroProductoStr = detalleBIT.getNumeroProducto();
			String numeroClienteStr = detalleBIT.getNumeroCliente();
			String fechaExpiracionProductoStr = detalleBIT.getExpiracionProducto();
			String descProductoStr = detalleBIT.getDescProducto();
			String montoProductoStr = detalleBIT.getMontoProducto();
			String fechaOperacionStr = detalleBIT.getFechaOperacion();

			
			//VALIDACIONES
			BigDecimal idPago = valBigDecimal.validate(descProductoStr);
			BigDecimal idConvenio = valBigDecimal.validate(idConvenioStr);
			Integer numeroProducto = valInteger.validate(numeroProductoStr);
			BigDecimal montoProducto = valBigDecimal.validate(montoProductoStr);
			BigDecimal numeroCliente = valBigDecimal.validate(numeroClienteStr);
			
			//Validar que el pago no haya sido rendido con anterioridad
			if (idPago!=null) {
				DetalleRendicionBIT detalleRendicionBITAux = mgrRendicion.getDetalleRendicionBitByPagoId(idPago);
				if (detalleRendicionBITAux!=null) {
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
			Date fechaOperacion = fechaOperacionStr == null ? Nulls.DATE
					: Utiles.getFechaParse("dd/MM/yyyyHH:mm:ss", fechaOperacionStr);
	        
	        //En este banco No se valida contenido predeterminado de la informacion
	        
			//Acumular el monto de la trx
			if (montoProducto!=null) {
				TOTAL_MONTO_RENDICION = new Integer(TOTAL_MONTO_RENDICION.intValue() + montoProducto.intValue());
			}
	        		
			//SETEOS
	        detalleRendicionBIT.setIdPago(idPago!=null?idPago:new BigDecimal(0));
	        detalleRendicionBIT.setIdConvenio(idConvenio);
	        detalleRendicionBIT.setNumProducto(getBigDecimal(numeroProducto));
	        detalleRendicionBIT.setNumCliente(numeroCliente);
	        detalleRendicionBIT.setFchExpiracion(fechaExpiracionProd);
	        detalleRendicionBIT.setDescripcionProducto(descProductoStr);
	        detalleRendicionBIT.setMontoProducto(montoProducto);
	        detalleRendicionBIT.setFechaOperacion(fechaOperacion);
	       
	        detalleRendicionBIT.setListErrorImportacion(LIST_ERROR_IMPORTACION_DETALLE);
	        return detalleRendicionBIT;
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
				RendicionBIT rendicionBIT = (RendicionBIT) mapaRendicion.get(BEAN_RENDICION);
				if (rendicionBIT!=null) {
					List listaErrorImportacionCabecera = rendicionBIT.getListErrorImportacion();
					
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
						rendicionBIT.setChecksum(checksum);
						
						//En el BIT la fecha contable viene en el detalle
						/*Date fechaContable = (Date) mapaConciliacion.get(FECHA_ARCHIVO_RENDICION);
						rendicionBIT.setFchCaptura(fechaContable);*/
														
						//Guardo en la session
						session.setAttribute(BEAN_RENDICION, rendicionBIT);
						session.setAttribute(CODIGO_MEDIO_PAGO, codBanco);
						session.setAttribute(LISTA_INCONSISTENTES, listaInconsistentes);
						session.setAttribute(LISTA_INCONSISTENTES_PAGOS, listaInconsistentesPagos);
						session.setAttribute(LISTA_RENDICIONES_OK, listaRendicionesOK);
						
					} else {
						//Cabecera con errores, informar al usuario
						logger.info("Existe errores de importacion, no se concilia");
						ActionTools.addMessage(request, new ActionMessage("rendicion.archivo.importacion.ErrorCabeceraControl"));
						frm.setFlagErrorCabeceraControl("1");
						
						frm.setListaNoImportados((ArrayList) rendicionBIT.getListErrorImportacion());
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
			logger.info("Entre a conciliarPagos BIT");
			
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
					DetalleRendicionBIT detalleRendicionBIT = (DetalleRendicionBIT) lineasRendicion.get(i);

					if (logger.isDebugEnabled())
						logger.debug("Analizando DetalleRendicionBIT: " + detalleRendicionBIT);
					
					BigDecimal idPago = detalleRendicionBIT.getIdPago();
					BigDecimal montoRendicion = detalleRendicionBIT.getMontoProducto();
					idPago = idPago!=null?idPago:new BigDecimal("-1");
					logger.info("idPago rendicion: " + idPago);
					
					//ANALIZAR EL PAGO
					logger.info("Buscar pago con idPago: " + idPago + " e idConvenio: " + idConvenio);
					Pago pago = mgrPago.getPagoByIdPagoIdConvenio(idPago, idConvenio);
					
					if (pago!=null) { //PAGO-CONVENIO EXISTE
						logger.info("Pago-Convenio Existe... /idPago: " + idPago + " /idConvenio: " + idConvenio);
						
						//ANALIZAR SI SON CANDIDATOS A NO IMPORTADOS
						if (detalleRendicionBIT.getListErrorImportacion().size()>0) {
							logger.info("detalleRendicionBIT no importada...");
							
							noImportados++;
							listaNoImportados.add(detalleRendicionBIT);
							
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
								listaRendicionesOK.add(detalleRendicionBIT);
							}
							
							if (!pagadoOk) { //PAGO INCONSISTENTE, no aparece pagado
								logger.info("Pago inconsistente... no aparece pagado");
								codErrorAux = Constants.RENDIC_ERROR_INC_NO_PAGADO;
								setCodErrorDetalle(detalleRendicionBIT.getListErrorImportacion(), codErrorAux);
								
								listaInconsisAux.add(getMensajeError(codErrorAux, ""));
							}
							if (!montoOk) { //PAGO INCONSISTENTE, monto no corresponde
								logger.info("Pago inconsistente... monto no corresponde");
								codErrorAux = Constants.RENDIC_ERROR_INC_MONTO_DISTINTO;
								setCodErrorDetalle(detalleRendicionBIT.getListErrorImportacion(), codErrorAux);
								
								listaInconsisAux.add(getMensajeError(codErrorAux, "", 
										renderer.formatMoney(montoRendicion), renderer.formatMoney(montoPago)));
							}
							
							//Guardar los codError detectados en el campo del bean
							detalleRendicionBIT.setCodError(getCodErrorDetalle(detalleRendicionBIT.getListErrorImportacion()));
							
							//Valido doble (si pago o monto inconsistente)
							if (!pagadoOk || !montoOk) {
								logger.info("Pago inconsistente... se lleva a la lista correspondiente.");
								
								inconsistenteDetalleRendicion++;
								detalleRendicionBIT.setListErrorInconsistente(listaInconsisAux);
								listaInconsistentes.add(detalleRendicionBIT);
							}
						}
						
						//Guardar las fechas contables para consulta de pagos no rendidos
						setListaFechasContables(detalleRendicionBIT, listaFechasContables);
						
						//Guardar los ids de pagos para consulta de pagos no rendidos.
						setListaIdsPagos(idPago, listaIdsPagos);
						
						
					} else {
						//PAGO-CONVENIO NO EXISTE, NO IMPORTAR
						logger.info("Pago-Convenio No Existe, no importar... /idPago: " + idPago + " /idConvenio: " + idConvenio);
						
						noImportados++;
						detalleRendicionBIT.getListErrorImportacion().add(getMensajeError(Constants.RENDIC_ERROR_IMP_RENDIDO_NO_PAGADO,
												"", idPago.toString(), codConvenio));
						listaNoImportados.add(detalleRendicionBIT);
					}
				}
				
				//BUSCAR POR FCH CONTABLE PAGOS NO RENDIDOS (CANDIDATOS A INCONSISTENTES)
				//En BIT la fecha contable viene en el detalle, por lo que tenemos una lista de fechas (es posible)
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
				logger.info("Sali de conciliarPagos BIT");
			}
			return mapa;		
		}
		
		/**
		 * Guardar las fechas contables de los detalles.
		 * Se valida no repetir el valor de una fecha.
		 * @param detalleRendicionBIT
		 * @param fechasContables
		 */
		private void setListaFechasContables(DetalleRendicionBIT detalleRendicionBIT, List fechasContables) {
			Renderer renderer = new Renderer();
			Date fchHoraOperacion = detalleRendicionBIT.getFechaOperacion(); 
			
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
				DetalleRendicionBIT element = (DetalleRendicionBIT) iter.next();
				DetalleRendicion detalleRendicion = new DetalleRendicion();
				
				detalleRendicion.setIdPago(element.getIdPago()!=null?element.getIdPago().toString():"0");
				detalleRendicion.setDetalle(element.getDescripcionProducto());
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
				DetalleRendicionBIT element = (DetalleRendicionBIT) iter.next();
				i++;
				if (logger.isDebugEnabled())
					logger.debug("Rendicion consistente (OK) a importar, nro " + i + ":" + element);				
			}
			
			for (Iterator iter = listaInconsistentes.iterator(); iter.hasNext();) {
				DetalleRendicionBIT element = (DetalleRendicionBIT) iter.next();
				i++;
				if (logger.isDebugEnabled())				
					logger.debug("Rendicion inconsistente a importar, nro " + i + ":" + element);
			}
		}		

	}