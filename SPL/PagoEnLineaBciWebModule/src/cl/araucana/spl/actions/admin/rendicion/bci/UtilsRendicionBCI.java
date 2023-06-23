package cl.araucana.spl.actions.admin.rendicion.bci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import cl.araucana.spl.base.Constants;
import cl.araucana.spl.base.RendicionBase;
import cl.araucana.spl.beans.Convenio;
import cl.araucana.spl.beans.DetalleRendicion;
import cl.araucana.spl.beans.DetalleRendicionBCI;
import cl.araucana.spl.beans.MedioPago;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.RendicionBCI;
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

public class UtilsRendicionBCI extends RendicionBase {
	//Constantes locales
	private static Logger logger = Logger.getLogger(UtilsRendicionBCI.class);

	private static final ResourceBundle resourceRendicion = ResourceBundle.getBundle("cl.araucana.spl.resources.RendicionBCI");
	
	private static final String SEPARADOR_CAMPOS = "|";
	private static final String SEPARADOR_SIZE = "-";

	private static final String REND_CABECERA_DESC = "BCI_CABECERA_DESC";
	private static final String REND_CABECERA_POS = "BCI_CABECERA_POS";
	private static final String REND_DETALLE_DESC = "BCI_DETALLE_DESC";
	private static final String REND_DETALLE_POS = "BCI_DETALLE_POS";
	private static final String REND_CONTROL_DESC = "BCI_CONTROL_DESC";
	private static final String REND_CONTROL_POS = "BCI_CONTROL_POS";

	private static final String CABECERA_KEY = "CABECERA";	
	private static final String DETALLES_KEY = "DETALLE";	
	private static final String CONTROL_KEY = "CONTROL";
	
	private static Integer TOTAL_MONTO_RENDICION = new Integer(0);
	private static Integer TOTAL_REGISTROS_RENDICION = new Integer(0);
	
	private static List LIST_ERROR_IMPORT_CABECERACONTROL = new ArrayList(); //Se utiliza para guardar descrip error de cabecera y control
	private static List LIST_ERROR_IMPORTACION_DETALLE = new ArrayList(); //Para descrip error del detalle de cada pago
	//private static List LIST_COD_ERROR_DETALLE = new ArrayList();
	
	/**
	 * Contructor
	 */
	public UtilsRendicionBCI() {
		TOTAL_MONTO_RENDICION = new Integer(0);
		TOTAL_REGISTROS_RENDICION = new Integer(0);
		LIST_ERROR_IMPORT_CABECERACONTROL = new ArrayList();
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
		Map mapaResult = new HashMap();
		logger.info("Estoy en procesarArchivoRendicion");
		
		try {
			Map mapaRendicion = leerArchivoRendicionTxt(is);
			mapaRendicion.put(CODIGO_CONVENIO, codigoConvenio);
			mapaRendicion.put(MGR_RENDICION, mgrRendicion);
			
			//Archivo se debe procesar en Orden: 1)Cabecera, 2)Lineas, 3)Control
			
			//1) CABECERA
			RendicionBCI rendicionBCI = procesaCabecera(mapaRendicion);			
			
			//2) LINEAS DETALLE
			List listaLineas = procesaLineas(mapaRendicion);			
			mapaResult.put(BEANS_DETALLES_RENDICION, listaLineas);
			
			//3) CONTROL
			rendicionBCI = procesaControl(mapaRendicion, rendicionBCI);			
			mapaResult.put(BEAN_RENDICION, rendicionBCI);
			
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
	 * Lee el archivo de rendicion, separando la cabecera, los detalles y el control.
	 * @param pathCompArchivo
	 * @return
	 * @throws IOException
	 */
	private Map leerArchivoRendicionTxt(InputStream is) throws IOException, RendicionException {
		Map mapa = new HashMap();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		int largoLinea = new Integer(resourceRendicion.getString("REND_NRO_CARACTERES_LINEA")).intValue();
		try {
			boolean encontradaCabecera = false;
			boolean encontradoControl = false;
			List listaDetalles = new ArrayList();
	        String linea;
	        while ((linea = in.readLine()) != null) {
	            logger.info("Linea: "  + linea + " - Largo: " + linea.length());
	            
	            if (linea.length()<largoLinea) {
	            	throw new RendicionException("rendicion.archivo.linea.tamagnoMenor");
	            	
	            } else {
		            String tipoLinea = linea.substring(0,1);
		            String tipoLineaCabecera = resourceRendicion.getString("REND_TIPOLINEA_CABECERA_COD").trim();
		            String tipoLineaDetalle = resourceRendicion.getString("REND_TIPOLINEA_DETALLE_COD").trim();
		            String tipoLineaControl = resourceRendicion.getString("REND_TIPOLINEA_CONTROL_COD").trim();
		            
		            if (tipoLineaCabecera.equalsIgnoreCase(tipoLinea) && !encontradaCabecera) {
		            	mapa.put(CABECERA_KEY, linea);
		            	encontradaCabecera = true;
		            }
		            
		            if (tipoLineaDetalle.equalsIgnoreCase(tipoLinea)) {
		            	listaDetalles.add(linea);
		            }
		            
		            if (tipoLineaControl.equalsIgnoreCase(tipoLinea) && !encontradoControl) {
		            	mapa.put(CONTROL_KEY, linea);
		            	encontradoControl = true;
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
	 * Procesa la linea cabecera guardada.
	 * @param mapaRendicion
	 * @return
	 * @throws IOException
	 */
	private RendicionBCI procesaCabecera(Map mapaRendicion) throws IOException {
		RendicionBCI rendicionBCI = new RendicionBCI();
		Map mapa = new HashMap();
		String valorKeyDesc = resourceRendicion.getString(REND_CABECERA_DESC);
		String valorKeySize = resourceRendicion.getString(REND_CABECERA_POS);	
		
		mapa.put(CODIGO_CONVENIO, (String) mapaRendicion.get(CODIGO_CONVENIO));
		
		try {
	        String linea = (String) mapaRendicion.get(CABECERA_KEY);
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
			idRendicion; checksum; fchImportacion; fchRendicion;
	        */
	        
	        //Validaciones
	        rendicionBCI = validarRendicionCabecera(rendicionBCI, mapa);
	        
	    } finally {
	    	
	    }
		return rendicionBCI;
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
				idDetalleRend; idRendicion; idConvenio; codError;
				*/
				DetalleRendicionBCI detalleRendicionBCI = new DetalleRendicionBCI();
				
				//VALIDACIONES
				detalleRendicionBCI = validarRendicionDetalle(detalleRendicionBCI, mapa);
				
				listaLineas.add(detalleRendicionBCI);
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
	 * @param rendicionBCI
	 * @return
	 * @throws IOException
	 */
	private RendicionBCI procesaControl(Map mapaRendicion, RendicionBCI rendicionBCI) throws IOException {
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
			idRendicion; checksum; fchImportacion; fchRendicion;			
	        */
	        
	        //VALIDACIONES
	        rendicionBCI = validarRendicionControl(rendicionBCI, mapa);
	        
	    } finally {
	    	
	    }
		return rendicionBCI;
	}
	
	
	/**
	 * Valida los tipos de datos de la Cabecera de RendicionBCI
	 * @param rendicionBCI
	 * @param mapa
	 * @return
	 */
	private RendicionBCI validarRendicionCabecera(RendicionBCI rendicionBCI,Map mapa) {
		String rutEmpresaStr = (String) mapa.get("RUT_EMPRESA");
		Integer rutEmpresa = valInteger.validate(rutEmpresaStr);
        if (rutEmpresa==null) { //No es numero
        	LIST_ERROR_IMPORT_CABECERACONTROL.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, CABECERA_KEY, "Rut empresa", rutEmpresaStr));
        }
        
        String nroConvenio = (String) mapa.get("IDENTIF_EMPRESA");
        String codigoConvenio = (String) mapa.get(CODIGO_CONVENIO);
        if (!codigoConvenio.equalsIgnoreCase(nroConvenio)) {
        	LIST_ERROR_IMPORT_CABECERACONTROL.add(getMensajeError(Constants.RENDIC_ERROR_IMP_NRO_CONVENIO, CABECERA_KEY, nroConvenio));
        }
                
		//SETEOS
        rendicionBCI.setEmpresaId(nroConvenio);
        rendicionBCI.setEmpresaRut(getBigDecimal(rutEmpresa));
        rendicionBCI.setEmpresaDv((String) mapa.get("DV_EMPRESA"));
        rendicionBCI.setEmpresaNombre((String) mapa.get("NOMBRE_EMPRESA"));
        
        rendicionBCI.setListErrorImportacion(LIST_ERROR_IMPORT_CABECERACONTROL);
        
        return rendicionBCI;
	}
	
	
	/**
	 * Valida los tipos de datos del Control de RendicionBCI
	 * @param rendicionBCI
	 * @param mapa
	 * @return
	 */
	private RendicionBCI validarRendicionControl(RendicionBCI rendicionBCI,Map mapa) {
		Renderer renderer = new Renderer();

        String totalRegistrosStr = (String) mapa.get("TOTAL_REGISTROS");
        String totalMontosStr = (String) mapa.get("TOTAL_MONTOS");
        String totalComisionesStr = (String) mapa.get("TOTAL_COM");
        String cantidadRegAceptadosStr = (String) mapa.get("CANT_REG_ACEPTADOS");
        String montoRegAceptadosStr = (String) mapa.get("MONTO_REG_ACEPTADOS");
        String cantidadRegRechazadosStr = (String) mapa.get("CANT_REG_RECHAZADOS");
        String montoRegRechazadosStr = (String) mapa.get("MONTO_REG_RECHAZADOS");
        
        Integer totalRegistros = valInteger.validate((String) mapa.get("TOTAL_REGISTROS"));
        Integer totalMontos = valInteger.validate((String) mapa.get("TOTAL_MONTOS"));
        Integer totalComisiones = valInteger.validate((String) mapa.get("TOTAL_COM"));
        Integer cantidadRegAceptados = valInteger.validate((String) mapa.get("CANT_REG_ACEPTADOS"));
        Integer montoRegAceptados = valInteger.validate((String) mapa.get("MONTO_REG_ACEPTADOS"));
        Integer cantidadRegRechazados = valInteger.validate((String) mapa.get("CANT_REG_RECHAZADOS"));
        Integer montoRegRechazados = valInteger.validate((String) mapa.get("MONTO_REG_RECHAZADOS"));
        
        if (totalRegistros==null) { //No es numero
        	LIST_ERROR_IMPORT_CABECERACONTROL.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
        			CONTROL_KEY, "Total regitros", totalRegistrosStr));
        }
        if (totalMontos==null) { //No es numero
        	LIST_ERROR_IMPORT_CABECERACONTROL.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
        			CONTROL_KEY, "Total montos", totalMontosStr));
        }
        if (totalComisiones==null) { //No es numero
        	LIST_ERROR_IMPORT_CABECERACONTROL.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
        			CONTROL_KEY, "Total comisiones", totalComisionesStr));
        }
        if (cantidadRegAceptados==null) { //No es numero
        	LIST_ERROR_IMPORT_CABECERACONTROL.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
        			CONTROL_KEY, "Cantidad reg. aceptados", cantidadRegAceptadosStr));
        }
        if (montoRegAceptados==null) { //No es numero
        	LIST_ERROR_IMPORT_CABECERACONTROL.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
        			CONTROL_KEY, "Monto reg. aceptados", montoRegAceptadosStr));
        }
        if (cantidadRegRechazados==null) { //No es numero
        	LIST_ERROR_IMPORT_CABECERACONTROL.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
        			CONTROL_KEY, "Cantidad reg. rechazados", cantidadRegRechazadosStr));
        }
        if (montoRegRechazados==null) { //No es numero
        	LIST_ERROR_IMPORT_CABECERACONTROL.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
        			CONTROL_KEY, "Monto reg. rechazados", montoRegRechazadosStr));
        }
        
        //Validacion contenido
		if (totalMontos!=null) {
			if (totalMontos.compareTo(TOTAL_MONTO_RENDICION)!=0) {
				logger.info("Monto total no coincide");
				BigDecimal totalMontoAux = new BigDecimal(totalMontos.doubleValue());
				BigDecimal totalMontoRendicionAux = new BigDecimal(TOTAL_MONTO_RENDICION.doubleValue());
	        	LIST_ERROR_IMPORT_CABECERACONTROL.add(getMensajeError(Constants.RENDIC_ERROR_IMP_MONTO_TOTAL, 
	        			CONTROL_KEY, renderer.formatMoney(totalMontoAux), renderer.formatMoney(totalMontoRendicionAux)));
			}
		}
		if (totalRegistros!=null) {
			if (totalRegistros.compareTo(TOTAL_REGISTROS_RENDICION)!=0) {
				logger.info("Numero de registros no coincide");
	        	LIST_ERROR_IMPORT_CABECERACONTROL.add(getMensajeError(Constants.RENDIC_ERROR_IMP_REGISTROS_TOTALES,
						CONTROL_KEY, totalRegistros.toString(), TOTAL_REGISTROS_RENDICION.toString()));
			}
		}
        
		//SETEOS
        rendicionBCI.setTotalRegistros(getBigDecimal(totalRegistros));
        rendicionBCI.setTotalMontos(getBigDecimal(totalMontos));
        rendicionBCI.setTotalComisiones(getBigDecimal(totalComisiones));
        rendicionBCI.setCantidadAceptados(getBigDecimal(cantidadRegAceptados));
        rendicionBCI.setMontoAceptados(getBigDecimal(montoRegAceptados));
        rendicionBCI.setCantidadRechazados(getBigDecimal(cantidadRegRechazados));
        rendicionBCI.setMontoRechazados(getBigDecimal(montoRegRechazados));
        
        rendicionBCI.setListErrorImportacion(LIST_ERROR_IMPORT_CABECERACONTROL);
        
        return rendicionBCI;
	}

	
	/**
	 * Valida los tipos de datos del Detalle de la RendicionBCI
	 * @param detalleRendicionBCI
	 * @param mapa
	 * @return
	 */
	private DetalleRendicionBCI validarRendicionDetalle(DetalleRendicionBCI detalleRendicionBCI,Map mapa) {
		LIST_ERROR_IMPORTACION_DETALLE = new ArrayList();
		
		RendicionManager mgrRendicion = (RendicionManager) mapa.get(MGR_RENDICION);
		
		String idPagoStr = (String) mapa.get("IDENTIF_TRX"); 
		String pagadorRutStr = (String) mapa.get("RUT_PAGADOR");
		String precioStr = (String) mapa.get("PRECIO");
		String cantidadStr = (String) mapa.get("CANTIDAD");
		String totalStr = (String) mapa.get("TOTAL");			
		String comisionCompraStr = (String) mapa.get("COMISION_COMPRA");
		String estadoDetalleStr = (String) mapa.get("ESTADO");
		String codRechazoStr = (String) mapa.get("COD_RECHAZO");
        
		//VALIDACIONES
		BigDecimal idPago = valBigDecimal.validate(idPagoStr);				
		Integer pagadorRut = valInteger.validate(pagadorRutStr);
		Integer precio = valInteger.validate(precioStr);
		Integer cantidad = valInteger.validate(cantidadStr);			
		Integer total = valInteger.validate(totalStr);			
		Integer comisionCompra = valInteger.validate(comisionCompraStr);
		Integer estadoDetalle = valInteger.validate(estadoDetalleStr);
		Integer codRechazo = valInteger.validate(codRechazoStr);
		
		//Validar que el pago no haya sido rendido con anterioridad
		if (idPago!=null) {
			DetalleRendicionBCI detalleRendicionBCIAux = mgrRendicion.getDetalleRendicionBciByPagoId(idPago);
			if (detalleRendicionBCIAux!=null) {				
				LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_YA_RENDIDO, 
						"", idPago.toString()));				
			}
		}
		
		//Validar tipo de datos
        if (idPago==null) { //No es numero
        	LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
        			DETALLES_KEY, "Id Pago", idPagoStr));        	
        }		
        if (pagadorRut==null) { //No es numero
        	LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
        			DETALLES_KEY, "Rut pagador", pagadorRutStr));        	
        }	
        if (precio == null) { // No es numero        	
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Precio", precioStr));			
		}
		if (cantidad == null) { // No es numero
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Cantidad", cantidadStr));			
		}
		if (total == null) { // No es numero			
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Total", totalStr));			
		}
		if (comisionCompra == null) { // No es numero
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Comision compra", comisionCompraStr));			
		}
		if (estadoDetalle == null) { // No es numero
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Estado", estadoDetalleStr));			
		}
		if (codRechazo == null) { // No es numero
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Cod. rechazo", codRechazoStr));			
		}
		
		// Validar contenido
		String formaPago = (String) mapa.get("FORMA_PAGO");
		if (!Constants.REND_BCI_FORMAPAGO_CUENTA.equalsIgnoreCase(formaPago)
				&& !Constants.REND_BCI_FORMAPAGO_TARJETA.equalsIgnoreCase(formaPago)) {
			
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_PREDETERMINADO, 
					"", "Forma de pago", formaPago));			
		}
		
		//Acumular el monto de la trx
		if (total!=null) {
			TOTAL_MONTO_RENDICION = new Integer(TOTAL_MONTO_RENDICION.intValue() + total.intValue());
		}
	
		//SETEOS
		detalleRendicionBCI.setIdPago(idPago!=null?idPago:new BigDecimal(0));				
		detalleRendicionBCI.setPagadorRut(getBigDecimal(pagadorRut));
		detalleRendicionBCI.setPagadorDv((String) mapa.get("DV_PAGADOR"));
		detalleRendicionBCI.setPagadorNombre((String) mapa.get("NOMBRE_PAGADOR"));
		detalleRendicionBCI.setIdProducto((String) mapa.get("IDENTIF_PRODUCTO"));
		detalleRendicionBCI.setPrecio(getBigDecimal(precio));
		detalleRendicionBCI.setCantidad(getBigDecimal(cantidad));
		detalleRendicionBCI.setTotal(getBigDecimal(total));
		detalleRendicionBCI.setFormaPago(formaPago);
		detalleRendicionBCI.setComisionCompra(getBigDecimal(comisionCompra));				
		detalleRendicionBCI.setEstado(getBigDecimal(estadoDetalle));
		detalleRendicionBCI.setCodRechazo(getBigDecimal(codRechazo));
        
        detalleRendicionBCI.setListErrorImportacion(LIST_ERROR_IMPORTACION_DETALLE);
        
        return detalleRendicionBCI;
	}
	
	/**
	 * Retorna la fecha del archivo de rendicion
	 * @param nombreArchivo
	 * @return String que representa la fecha
	 * @author malvarez
	 */
	private String getFechaArchivo(String nombreArchivo) {
		String result = "";
		if (nombreArchivo!=null) {
			List lista = Arrays.asList(nombreArchivo.split("-"));
			if (lista!=null) {
				String parte2 = (String) lista.get(1);
				if (parte2!=null) {
					lista = Arrays.asList(parte2.split("\\."));
					result = lista!=null?(String) lista.get(0):"";
				}				
			}
		}
		return result;
	}
	
	/**
	 * Devuelve el numero de convenio del archivo de rendicion
	 * @param nombreArchivo
	 * @return
	 */
	private String getConvenioArchivo(String nombreArchivo) {
		String result = "";
		if (nombreArchivo!=null) {
			List lista = Arrays.asList(nombreArchivo.split("\\."));
			if (lista!=null) {
				String parte2 = (String) lista.get(1);
				result = parte2!=null?parte2:"";
			}
		}
		return result;
	}
	

	/**
	 * Valida la estructura del nombre del archivo de rendicion.
	 * Se aplica expresion regular {@link Pattern}.
	 * @param nombreArchivo
	 * @return boolean.  Si coincide es true, en caso contrario false.
	 */
	private boolean validarNombre(String nombreArchivo) {
		Pattern pattern = Pattern.compile("^PE-\\d{8}\\..");
		Matcher matcher = null;
		
		matcher = pattern.matcher(nombreArchivo);
		boolean result = matcher.find(); 
		logger.info("validarNombre de archivo, result:" + result);  
		return result;
	}


	/**
	 * Metodo de inicio de la logica de procesamiento de rendicion BCI
	 * @param mapaParametros
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public String initProcesarRendicion(Map mapaParametros, HttpServletRequest request, 
			HttpSession session) throws Exception {
		
		logger.info("Entre a initProcesarRendicion");
		
		String target = new String("preview");
		String archivoNombre = (String) mapaParametros.get(NOMBRE_ARCHIVO);
		FormFile rendicionFile = (FormFile) mapaParametros.get(FORM_FILE);
		MedioPago medioPago = (MedioPago) mapaParametros.get(MEDIO_PAGO);
		RendicionManager mgrRendicion = (RendicionManager) mapaParametros.get(MGR_RENDICION);
		PagoManager mgrPago = (PagoManager) mapaParametros.get(MGR_PAGO);
		ImportarRendicionForm frm = (ImportarRendicionForm) mapaParametros.get(FORM_IMPORTAR_RENDICION);

		String codBanco = medioPago!=null?medioPago.getCodigo():"";
		
		//Validar nombre del archivo.
		boolean esNombreValido = validarNombre(archivoNombre);
		if (esNombreValido) {
			
			//Calcular checksum
			String checksum = MD5Checksum.getMD5Checksum(rendicionFile.getFileData());
			logger.info("checksum archivo:" + checksum);
			
			//Consultar segun banco, si existe la rendicion
			boolean existe = mgrRendicion.existeRendicionBancoByChecksum(codBanco, checksum).booleanValue();
			
			if (!existe) {
				//Validacion General
				logger.info("Rendicion No existe... se debe procesar.");
				
				String codigoConvenioArch = getConvenioArchivo(archivoNombre);
				Convenio convenio = new MedioPagoManager().getConvenio(codBanco);
				String codigoConvenioBanco = convenio!=null?convenio.getCodigo():""; 
				
				if (codigoConvenioArch.equalsIgnoreCase(codigoConvenioBanco)) {
					logger.info("Convenio archivo valido, codigoConvenioArch: " + codigoConvenioArch + " /codigoConvenioBanco: " + codigoConvenioBanco);
					
					//PROCESAR ARCHIVO
					Map mapaRendicion = procesarArchivoRendicion(rendicionFile.getInputStream(), codigoConvenioBanco, mgrRendicion);
					mapaRendicion.put(NOMBRE_ARCHIVO, archivoNombre);
					mapaRendicion.put(BEAN_CONVENIO, convenio);
					
					//Verificar errores en cabecera y control
					RendicionBCI rendicionBCI = (RendicionBCI) mapaRendicion.get(BEAN_RENDICION);
					if (rendicionBCI!=null) {
						List listaErrorImportacionCabecera = rendicionBCI.getListErrorImportacion();
						
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
							rendicionBCI.setChecksum(checksum);
							rendicionBCI.setFchRendicion(fechaContable);
															
							//Guardo en la session
							session.setAttribute(BEAN_RENDICION, rendicionBCI);
							session.setAttribute(CODIGO_MEDIO_PAGO, codBanco);
							session.setAttribute(LISTA_INCONSISTENTES, listaInconsistentes);
							session.setAttribute(LISTA_INCONSISTENTES_PAGOS, listaInconsistentesPagos);
							session.setAttribute(LISTA_RENDICIONES_OK, listaRendicionesOK);
							
						} else {
							//Cabecera con errores, informar al usuario
							logger.info("Existe errores de importacion, no se concilia");
							ActionTools.addMessage(request, new ActionMessage("rendicion.archivo.importacion.ErrorCabeceraControl"));
							frm.setFlagErrorCabeceraControl("1");
							
							frm.setListaNoImportados((ArrayList) rendicionBCI.getListErrorImportacion());
						}
					}
					//Sets form obligatorios
					frm.setMedioPago(medioPago);						
					
					
				} else {					
					logger.info("Convenio archivo No valido, codigoConvenioArch: " + codigoConvenioArch + " /codigoConvenioBanco: " + codigoConvenioBanco);
					
					ActionTools.addMessage(request, new ActionMessage("rendicion.archivo.convenioErroneo"));
					target = "inicio";
				}
				
			} else {
				logger.info("Rendicion ya existe");
				
				ActionTools.addMessage(request, new ActionMessage("rendicion.archivo.existe"));
				target = "inicio";
			}
			
		} else {
			logger.info("Nombre de archivo no valido");
			
			ActionTools.addMessage(request, new ActionMessage("rendicion.archivo.nombreNOK"));
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
		logger.info("Entre a conciliarPagos BCI");
		
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
				DetalleRendicionBCI detalleRendicionBCI = (DetalleRendicionBCI) lineasRendicion.get(i);
				
				if (logger.isDebugEnabled())
					logger.debug("Analizando DetalleRendicionBCI: " + detalleRendicionBCI);
				
				BigDecimal idPago = detalleRendicionBCI.getIdPago(); 
				BigDecimal montoRendicion = detalleRendicionBCI.getTotal();
				BigDecimal estadoDetalle = detalleRendicionBCI.getEstado();
				idPago = idPago!=null?idPago:new BigDecimal("-1");
				logger.info("idPago rendicion: " + idPago);
				
				//ANALIZAR EL PAGO
				logger.info("Buscar pago con idPago: " + idPago + " e idConvenio: " + idConvenio);
				Pago pago = mgrPago.getPagoByIdPagoIdConvenio(idPago, idConvenio);
				
				if (pago!=null) { //PAGO-CONVENIO EXISTE
					logger.info("Pago-Convenio Existe... /idPago: " + idPago + " /idConvenio: " + idConvenio);
					
					//ANALIZAR SI SON CANDIDATOS A NO IMPORTADOS
					if (detalleRendicionBCI.getListErrorImportacion().size()>0) {
						logger.info("detalleRendicionBCI no importada...");
						
						noImportados++;
						listaNoImportados.add(detalleRendicionBCI);
						
					} else {
						//ANALIZAR EL PAGO
						logger.info("Analizar el pago...");
						
						BigDecimal montoPago = pago.getMonto();
						Integer pagoPagado = (pago.getPagado()!=null)?pago.getPagado():Constants.PAGO_INICIAL;
						
						boolean pagadoOk=false;
						boolean montoOk=false;
						List listaInconsisAux = new ArrayList();
						
						//Chequear consistencia en montos y los estados del pago (en rendicion y pago-spl)
						montoOk = chequearMonto(montoRendicion, montoPago);
						pagadoOk = chequearPagado(pagoPagado.intValue(), estadoDetalle.intValue());
						
						if (pagadoOk && montoOk) {
							//PAGO OK
							logger.info("Pago OK");
							importados++;
							consistentes++;
							listaRendicionesOK.add(detalleRendicionBCI);							
						}
						
						if (!pagadoOk) { //PAGO INCONSISTENTE, el estado de la rendicion es distinto al del pago en spl.
							logger.info("Pago inconsistente... distinto estado de pago");
							codErrorAux = Constants.RENDIC_ERROR_INC_PAGADO_DISTINTO;
							setCodErrorDetalle(detalleRendicionBCI.getListErrorImportacion(), codErrorAux);
							
							listaInconsisAux.add(getMensajeError(codErrorAux, "", getDescEstadoRendicion(estadoDetalle),
									getDescEstadoPago(pagoPagado)));
						}
						if (!montoOk) { //PAGO INCONSISTENTE, monto no corresponde
							logger.info("Pago inconsistente... monto no corresponde");
							codErrorAux = Constants.RENDIC_ERROR_INC_MONTO_DISTINTO;
							setCodErrorDetalle(detalleRendicionBCI.getListErrorImportacion(), codErrorAux);
							
							listaInconsisAux.add(getMensajeError(codErrorAux, "", renderer.formatMoney(montoRendicion),
									renderer.formatMoney(montoPago)));
						}
						
						//Guardar los codError detectados en el campo del bean
						detalleRendicionBCI.setCodError(getCodErrorDetalle(detalleRendicionBCI.getListErrorImportacion()));
						
						//Valido doble (si pago o monto inconsistente)
						if (!pagadoOk || !montoOk) {
							logger.info("Pago inconsistente... se lleva a la lista correspondiente.");
							
							inconsistenteDetalleRendicion++;
							detalleRendicionBCI.setListErrorInconsistente(listaInconsisAux);
							listaInconsistentes.add(detalleRendicionBCI);
						}
						
					}
					
					//Guardar los ids de pagos para consulta de pagos no rendidos.
					setListaIdsPagos(idPago, listaIdsPagos);
					
				} else {
					//PAGO-CONVENIO NO EXISTE, NO IMPORTAR
					logger.info("Pago-Convenio No Existe, no importar... /idPago: " + idPago + " /idConvenio: " + idConvenio);
					
					noImportados++;
					detalleRendicionBCI.getListErrorImportacion().add(getMensajeError(Constants.RENDIC_ERROR_IMP_RENDIDO_NO_PAGADO,
											"", idPago.toString(), codConvenio));
					listaNoImportados.add(detalleRendicionBCI);
				}
			}
			
			//BUSCAR POR FCH CONTABLE PAGOS NO RENDIDOS (CANDIDATOS A INCONSISTENTES)
			String fechaCont = getFechaArchivo((String) mapaRendicion.get(NOMBRE_ARCHIVO));
			fechaContable = Utiles.getFechaParse("yyyyMMdd", fechaCont);
			logger.info("Fecha Archivo-Parseada: " + fechaContable.toString());
			
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
			logger.info("Sali de conciliarPagos BCI");
		}
		return mapa;		
	}

	/**
	 * Verifica la consistencia entre pago.pagado y detrendbci.estado
	 * @param pagoPagado
	 * @param estadoDetalle
	 * @return true: Si son consistentes, false: Si no son consitentes entre ellos.
	 */
	private boolean chequearPagado(int pagoPagado, int estadoDetalle) {
		boolean result = false;
		
		logger.info("ESTOY EN chequearPagado MODIFICADO / pagoPagado: " + pagoPagado + " Y estadoDetalle: " + estadoDetalle);
		int PAGO_PAGADO = Constants.PAGO_PAGADO.intValue();
		int PAGO_NO_PAGADO = Constants.PAGO_NO_PAGADO.intValue();
		int PAGO_INICIAL = Constants.PAGO_INICIAL.intValue();
		
		if ( (pagoPagado == PAGO_PAGADO && Utiles.estadoBciPagado(estadoDetalle) 
			|| (pagoPagado == PAGO_NO_PAGADO && !Utiles.estadoBciPagado(estadoDetalle))
			|| (pagoPagado == PAGO_INICIAL && !Utiles.estadoBciPagado(estadoDetalle)))) {
			result = true;
		}
		
		logger.info("ChequearPagado... result: " + result);
		return result;
	}
	

	/**
	 * Verifica la consistencia entre pago.monto y detrendbci.total 
	 * @param montoRendicion
	 * @param montoPago
	 * @return
	 */
	private boolean chequearMonto(BigDecimal montoRendicion, BigDecimal montoPago) {
		logger.info("En chequearMonto... montoRendicion: " + montoRendicion + " /montoPago: "+montoPago);
		boolean result = false;
		if (montoPago.compareTo(montoRendicion)==0) //Si son iguales
			result=true;
		
		logger.info("ChequearMonto result: " + result);
		return result;
	}
	
	
	private String getDescEstadoRendicion(BigDecimal estadoRend) {
		String result = "NOK";
		if (Utiles.estadoBciPagado(estadoRend.intValue())) {
			result="OK";
		}
		return result;
	}
	
	private String getDescEstadoPago(Integer pagado) {
		String result = "NOK";
		if (Constants.PAGO_PAGADO.equals(pagado))
			result="OK";
		return result;
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
	 * Traspaso de bean detalleRendicionBCI a bean generico
	 * @param listaRendiciones
	 * @return
	 */
	private List getDetalleRendicion(List listaRendiciones) {
		List result = new ArrayList();
		Renderer render = new Renderer();
		for (Iterator iter = listaRendiciones.iterator(); iter.hasNext();) {
			DetalleRendicionBCI element = (DetalleRendicionBCI) iter.next();
			DetalleRendicion detalleRendicion = new DetalleRendicion();
			
			detalleRendicion.setIdPago(element.getIdPago()!=null?element.getIdPago().toString():"0");
			detalleRendicion.setDetalle("RUT: "+render.formatRut(element.getPagadorRut().intValue(), element.getPagadorDv()));
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
			DetalleRendicionBCI element = (DetalleRendicionBCI) iter.next();
			i++;
			if (logger.isDebugEnabled())				
				logger.info("Rendicion consistente (OK) a importar, nro " + i + ":" + element);
		}
		
		for (Iterator iter = listaInconsistentes.iterator(); iter.hasNext();) {
			DetalleRendicionBCI element = (DetalleRendicionBCI) iter.next();
			i++;
			if (logger.isDebugEnabled())
				logger.info("Rendicion inconsistente a importar, nro " + i + ":" + element);				
		}
	}

}