/*
 * @(#) UtilsRendicionBES.java    1.0 06-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */

package cl.araucana.spl.actions.admin.rendicion.bes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
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
import cl.araucana.spl.beans.ControlRendicionBES;
import cl.araucana.spl.beans.Convenio;
import cl.araucana.spl.beans.DetalleRendicion;
import cl.araucana.spl.beans.DetalleRendicionBES;
import cl.araucana.spl.beans.EncabezadoRendicionBES;
import cl.araucana.spl.beans.MedioPago;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.RendicionBES;
import cl.araucana.spl.beans.ResumenDetalleRendicionBES;
import cl.araucana.spl.beans.TransaccionBes;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.exceptions.RendicionException;
import cl.araucana.spl.forms.admin.rendicion.ImportarRendicionForm;
import cl.araucana.spl.mgr.MedioPagoManager;
import cl.araucana.spl.mgr.PagoBesManager;
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
 *            <TD> 06-08-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Jorge Landeros <BR> jlandero@schema.cl </TD>
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
 * @author Lorge Landeros (jlandero@schema.cl)
 *
 * @version 1.0
 */

public class UtilsRendicionBES extends RendicionBase {
	//Constantes locales
	private static Logger logger = Logger.getLogger(UtilsRendicionBES.class);

	private static final ResourceBundle resourceRendicion = ResourceBundle.getBundle("cl.araucana.spl.resources.RendicionBES");
	
	private static final String SEPARADOR_CAMPOS = "|";
	private static final String SEPARADOR_SIZE = "-";

	private static final String REND_ENCABEZADO_DESC = "BES_ENCABEZADO_DESC";
	private static final String REND_ENCABEZADO_POS = "BES_ENCABEZADO_POS";
	private static final String REND_DETALLE_DESC = "BES_DETALLE_DESC";
	private static final String REND_DETALLE_POS = "BES_DETALLE_POS";
	private static final String REND_RESUMEN_DETALLE_DESC = "BES_DETALLE_RESUMEN_DESC";
	private static final String REND_RESUMEN_DETALLE_POS = "BES_DETALLE_RESUMEN_POS";
	private static final String REND_CONTROL_DESC = "BES_CONTROL_DESC";
	private static final String REND_CONTROL_POS = "BES_CONTROL_POS";
	private static final String BES_COD_RES_DET = "BES_COD_RES_DET";
	private static final String BES_COD_DET = "BES_COD_DET";
	
	private static final String ENCABEZADO_KEY = "ENCABEZADO";
	private static final String DETALLES_KEY = "DETALLE";	
	private static final String CONTROL_KEY = "CONTROL";
	
	private static Integer TOTAL_REGISTROS_RENDICION = new Integer(0);
	
	private static List LIST_ERROR_IMPORTACION = new ArrayList();
	private static List LIST_ERROR_IMPORTACION_DETALLE = new ArrayList();
	private static List LIST_ERROR_IMPORTACION_DETALLE_RESUMEN = new ArrayList();
		
	
	/**
	 * Contructor
	 */
	public UtilsRendicionBES() {		
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
			
			//Archivo se debe procesar en Orden: 1)Lineas de detalle, 2)Control del detalle, 3)Encabezado
			
			//1) LINEAS DETALLE			
			List listaLineas = procesaLineas(mapaRendicion);
			mapaResult.put(BEANS_DETALLES_RENDICION, listaLineas);
			
			//2) CONTROL
			ControlRendicionBES controlRendicionBES = procesaControl(mapaRendicion);			
			
			//3) Encabezado
			EncabezadoRendicionBES encabezadoRendicionBES = procesaEncabezado(mapaRendicion);				
			
			RendicionBES rendicionBES = new RendicionBES();
			rendicionBES.setEncabezadoRendicionBES(encabezadoRendicionBES);
			rendicionBES.setControlRendicionBES(controlRendicionBES);
			
			mapaResult.put(BEAN_RENDICION, rendicionBES);
			
			
			
			
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
		            String tipoLinea = linea.substring(0,2);
		            String tipoLineaControl = resourceRendicion.getString("REND_TIPOLINEA_CONTROL_COD").trim();
		            String tipoLineaEncabezado = resourceRendicion.getString("REND_TIPOLINEA_ENCABEZADO_COD").trim();
		            
		            
		            if (tipoLineaControl.equalsIgnoreCase(tipoLinea)) {
		            	//lectura de linea control
		            	mapa.put(CONTROL_KEY, linea);
		            } else if (tipoLineaEncabezado.equalsIgnoreCase(tipoLinea)) { 
		            	//lectura de lineas encabezado
		            	mapa.put(ENCABEZADO_KEY, linea);
		            }else{
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
		List listaDetallesPago = new ArrayList();
		Map mapa = new HashMap();
		Map mapaResumen = new HashMap();
		mapa.put(MGR_RENDICION, (RendicionManager) mapaRendicion.get(MGR_RENDICION));
		mapaResumen.put(MGR_RENDICION, (RendicionManager) mapaRendicion.get(MGR_RENDICION));
		int totalRegistrosDetalle = 0;
		
		try {
			String valorKeyDesc = resourceRendicion.getString(REND_DETALLE_DESC);
			String valorKeyPos = resourceRendicion.getString(REND_DETALLE_POS);
			
			Vector vecDesc = Utiles.setVector(valorKeyDesc, SEPARADOR_CAMPOS);
			Vector vecSize = Utiles.setVector(valorKeyPos, SEPARADOR_CAMPOS);
			
			String valorKeyDescResumen = resourceRendicion.getString(REND_RESUMEN_DETALLE_DESC);
			String valorKeyPosResumen = resourceRendicion.getString(REND_RESUMEN_DETALLE_POS);
			String codResumenDetalle = resourceRendicion.getString(BES_COD_RES_DET);
			String codDetalle = resourceRendicion.getString(BES_COD_DET);
			
			Vector vecDescResumen = Utiles.setVector(valorKeyDescResumen, SEPARADOR_CAMPOS);
			Vector vecSizeResumen = Utiles.setVector(valorKeyPosResumen, SEPARADOR_CAMPOS);	
			
			ResumenDetalleRendicionBES resumenDetalleRendicionBES = new ResumenDetalleRendicionBES();
			
			long separador = 1;
			
	        List listaDetalles = (ArrayList) mapaRendicion.get(DETALLES_KEY);
	        for (int i = 0; i < listaDetalles.size(); i++) {
	        	String linea = (String) listaDetalles.get(i);
	        	
		        	if (linea.substring(0, 3).equals(codDetalle)){
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
		        		
		        	}else if (linea.substring(0, 3).equals(codResumenDetalle)){
		        		for (int j = 0; j < vecSizeResumen.size(); j++) {
							String nombreCampo = (String) vecDescResumen.elementAt(j);
							String tamagno = (String) vecSizeResumen.elementAt(j);
												
							Vector pos = Utiles.setVector(tamagno, SEPARADOR_SIZE);
							int posIni = Integer.parseInt((String)pos.elementAt(0));
							int posFin = Integer.parseInt((String)pos.elementAt(1));
							
							String valorCampoLinea = linea.substring(posIni, posFin);
							valorCampoLinea = valorCampoLinea!=null?valorCampoLinea.trim():valorCampoLinea;
							
							logger.info("La columna: " + nombreCampo + ", de posicion:" + tamagno +" tiene el valor: " + valorCampoLinea);
						
							mapaResumen.put(nombreCampo, valorCampoLinea);
						}
		        		
		        	}
				
				/*
				idDetalleRend; idRendicion; idConvenio; idPago; codError;
				*/
				DetalleRendicionBES detalleRendicionBES = new DetalleRendicionBES();
				
				if (linea.substring(0, 3).equals(codDetalle)){
					//Validaciones Detalle Pago
					detalleRendicionBES = validarRendicionDetalle(detalleRendicionBES, mapa);
					detalleRendicionBES.setIdRepresentativo(separador);
					listaDetallesPago.add(detalleRendicionBES);
					
					
				}else if (linea.substring(0, 3).equals(codResumenDetalle)){
					//Validaciones Resumen
					resumenDetalleRendicionBES = validarRendicionResumenDetalle(resumenDetalleRendicionBES, mapaResumen);
					resumenDetalleRendicionBES.setDetalleRendicionBES((DetalleRendicionBES[])listaDetallesPago.toArray( new DetalleRendicionBES[listaDetallesPago.size()] ));
					listaLineas.add(resumenDetalleRendicionBES);
					totalRegistrosDetalle = totalRegistrosDetalle + listaDetallesPago.size();
					listaDetallesPago = new ArrayList();
					resumenDetalleRendicionBES = new ResumenDetalleRendicionBES();
					separador ++;
				}
			}
			//Acumular los registros de la rendicion
			TOTAL_REGISTROS_RENDICION = new Integer(totalRegistrosDetalle);
	        
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
	private ControlRendicionBES procesaControl(Map mapaRendicion) throws IOException, ParseException {
		ControlRendicionBES rendicionBES = new ControlRendicionBES(); 
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
	        rendicionBES = validarRendicionControl(rendicionBES, mapa);
	        
	    } finally {
	    	
	    }
		return rendicionBES;
	}
	
	/**
	 * Procesa la linea de control almacenada.
	 * @param mapaRendicion
	 * @return
	 * @throws IOException
	 */
	private EncabezadoRendicionBES procesaEncabezado(Map mapaRendicion) throws IOException, ParseException {
		EncabezadoRendicionBES encabezadoRendicionBES = new EncabezadoRendicionBES(); 
		Map mapa = new HashMap();
		String valorKeyDesc = resourceRendicion.getString(REND_ENCABEZADO_DESC);
		String valorKeySize = resourceRendicion.getString(REND_ENCABEZADO_POS);		
		try {
	        String linea = (String) mapaRendicion.get(ENCABEZADO_KEY);
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
	        encabezadoRendicionBES = validarRendicionEncabezado(encabezadoRendicionBES, mapa);
	        
	    } finally {
	    	
	    }
		return encabezadoRendicionBES;
	}
	
	/**
	 * Valida los tipos de datos del Control de RendicionBES
	 * @param rendicionBES
	 * @param mapa
	 * @return
	 */
	private ControlRendicionBES validarRendicionControl(ControlRendicionBES controlRendicionBES,Map mapa) throws ParseException {

        String tipoRegistro = (String)mapa.get("TIP_REG");
        String totalRegistros = (String)mapa.get("REG_TOTAL");
        String montoTotal = (String)mapa.get("MTO_TOTAL");
        String registrosAceptados = (String)mapa.get("REG_ACEPT");
        String montoAceptado = (String)mapa.get("MTO_ACEPT");
        String numMultipago = (String)mapa.get("NUM_MULTIPAG");
        String numTransacciones = (String)mapa.get("NUM_TXS");
        String filler = (String)mapa.get("FILLER");
        
        Integer validaTotalRegistros = valInteger.validate(totalRegistros);
        BigDecimal validaMontoTotal = valBigDecimal.validate(montoTotal);
        Integer validaRegistrosAceptados = valInteger.validate(registrosAceptados);
        BigDecimal validaMontoAceptado = valBigDecimal.validate(montoAceptado);
        Integer validaNumMultipago = valInteger.validate(numMultipago);
        Integer validaNumTransacciones = valInteger.validate(numTransacciones);
                
        
        if (validaTotalRegistros==null) { //No es numero
        	LIST_ERROR_IMPORTACION.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, CONTROL_KEY,
					"Total Registros", totalRegistros));
        }
        if (validaMontoTotal==null) { //No es numero
        	LIST_ERROR_IMPORTACION.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, CONTROL_KEY,
					"Monto Total", montoTotal));
        }
        if (validaRegistrosAceptados == null) {
        	LIST_ERROR_IMPORTACION.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, CONTROL_KEY,
					"Registros Aceptados", registrosAceptados));
        }
        if (validaMontoAceptado == null) {
        	LIST_ERROR_IMPORTACION.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, CONTROL_KEY,
					"Monto Aceptado", montoAceptado));
        }
        if (validaNumMultipago == null) {
        	LIST_ERROR_IMPORTACION.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, CONTROL_KEY,
					"Numero Multipago", numMultipago));
        }
        if (validaNumTransacciones == null) {
        	LIST_ERROR_IMPORTACION.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, CONTROL_KEY,
					"Numero Transacciones", numTransacciones));
        }
        
        //Validacion contenido
		if (totalRegistros!=null) {
			//if (validaTotalRegistros.compareTo(TOTAL_REGISTROS_RENDICION)!=0) {
			if (validaNumTransacciones.compareTo(TOTAL_REGISTROS_RENDICION)!=0) {
				logger.info("Numero de registros no coincide");
	        	LIST_ERROR_IMPORTACION.add(getMensajeError(Constants.RENDIC_ERROR_IMP_REGISTROS_TOTALES,
						CONTROL_KEY, validaNumTransacciones.toString(), TOTAL_REGISTROS_RENDICION.toString()));
			}
		}
        
		//SETEOS	
		controlRendicionBES.setFiller(filler);
		controlRendicionBES.setMontoAceptado(validaMontoAceptado != null ? validaMontoAceptado : new BigDecimal(0));
		controlRendicionBES.setMontoTotal(validaMontoTotal != null ? validaMontoTotal : new BigDecimal(0));
		controlRendicionBES.setNumMultipago(validaNumMultipago != null ? validaNumMultipago.longValue() : 0);
		controlRendicionBES.setNumTransacciones(validaNumTransacciones != null ? validaNumTransacciones.longValue() : 0);
		controlRendicionBES.setRegistrosAceptados(validaRegistrosAceptados != null ? validaRegistrosAceptados.intValue() : 0);
		controlRendicionBES.setTipoRegistro(tipoRegistro);
		controlRendicionBES.setTotalRegistros(validaTotalRegistros != null ? validaTotalRegistros.intValue() : 0);
		
		controlRendicionBES.setListErrorImportacion(LIST_ERROR_IMPORTACION);
        
        return controlRendicionBES;
	}
	
	/**
	 * Valida los tipos de datos del Control de RendicionBES
	 * @param rendicionBES
	 * @param mapa
	 * @return
	 */
	private EncabezadoRendicionBES validarRendicionEncabezado(EncabezadoRendicionBES encabezadoRendicionBES,Map mapa) throws ParseException {

        String tipoReg = (String)mapa.get("TIP_REG");
        String fecRend = (String)mapa.get("FEC_REND");
        String banco = (String)mapa.get("BANCO");
        String rutConcentrador = (String)mapa.get("RUT_CONCEN");
        String digRutConcentrador = (String)mapa.get("DIG_CONCEN");
        String glosa = (String)mapa.get("GLO_SERVIC");
        String filler = (String)mapa.get("FILLER");
        
        Integer validaFecRend = valInteger.validate(fecRend);
        Integer validaBanco = valInteger.validate(banco);
        Integer validaRut = valInteger.validate(rutConcentrador);
        
                
        
        if (validaFecRend==null) { //No es numero
        	LIST_ERROR_IMPORTACION.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, ENCABEZADO_KEY,
					"Fecha Rendicion", fecRend));
        }
        if (validaBanco==null) { //No es numero
        	LIST_ERROR_IMPORTACION.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, ENCABEZADO_KEY,
					"Banco", banco));
        }
        if (validaRut == null) {
        	LIST_ERROR_IMPORTACION.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, ENCABEZADO_KEY,
					"Rut Concentrador", rutConcentrador));
        }
        
		//SETEOS	
        encabezadoRendicionBES.setBanco(validaBanco != null ? validaBanco.intValue() : 0);
        encabezadoRendicionBES.setDigConcentrador(digRutConcentrador);
        encabezadoRendicionBES.setFechaRendicion(validaFecRend==null?Nulls.DATE:Utiles.getFechaParse("yyyyMMdd", fecRend));
        encabezadoRendicionBES.setFiller(filler);
        encabezadoRendicionBES.setGlosaServicio(glosa);
        encabezadoRendicionBES.setRutConcentrador(validaRut != null ? validaRut.longValue() : 0);
        encabezadoRendicionBES.setTipoReg(tipoReg);
		
		encabezadoRendicionBES.setListErrorImportacion(LIST_ERROR_IMPORTACION);
        
        return encabezadoRendicionBES;
	}

	
	/**
	 * Valida los pagos del Detalle de la DetalleRendicionPagosBES
	 * @param detalleRendicionBES
	 * @param mapa
	 * @return
	 */
	private DetalleRendicionBES validarRendicionDetalle(DetalleRendicionBES detalleRendicionBES,Map mapa) throws ParseException {
		LIST_ERROR_IMPORTACION_DETALLE = new ArrayList();
		RendicionManager mgrRendicion = (RendicionManager) mapa.get(MGR_RENDICION);
		
		
		
		String tipReg      = (String) mapa.get("TIP_REG");
		String subtipo     = (String) mapa.get("SUBTIPO");
		String rutCompra   = (String) mapa.get("RUT_COMPRA");
		String digCompra   = (String) mapa.get("DIG_COMPRA");
		String id          = (String) mapa.get("ID");
		String gloMultipag = (String) mapa.get("GLO_MULTIPAG");
		String fecVen      = (String) mapa.get("FEC_VEN");
		String estadoPag   = (String) mapa.get("ESTADO_PAG");
		String txBanco     = (String) mapa.get("TX_BANCO");
		String fecOpe      = (String) mapa.get("FEC_OPE");
		String horOpe      = (String) mapa.get("HOR_OPE");
		String fecConta    = (String) mapa.get("FEC_CONTA");
		String rutCuenta   = (String) mapa.get("RUT_CUENTA");
		String digCuenta   = (String) mapa.get("DIG_CUENTA");
		String convenio    = (String) mapa.get("CONVENIO");
		String gloPago     = (String) mapa.get("GLO_PAGO");
		String codPago     = (String) mapa.get("COD_PAGO");
		String mtoPagoDet  = (String) mapa.get("MTO_PAGO_DET");
		String resBatch    = (String) mapa.get("RES_BATCH");
		String alerta      = (String) mapa.get("ALERTA");
		String filler      = (String) mapa.get("FILLER");
				
		Integer validaSubtipo = valInteger.validate(subtipo);
		Integer validaRutCompra = valInteger.validate(rutCompra);
		Integer validaFecVen = valInteger.validate(fecVen);
		Integer validaTxBanco = valInteger.validate(txBanco);
		Integer validaFecOpe = valInteger.validate(fecOpe);
		Integer validaHorOpe = valInteger.validate(horOpe);
		Integer validaFecConta = valInteger.validate(fecConta);
		Integer validaRutCuenta = valInteger.validate(rutCuenta);
		Integer validaConvenio = valInteger.validate(convenio);
		BigDecimal validaMontoPago = valBigDecimal.validate(mtoPagoDet);
		Integer validaAlerta = valInteger.validate(alerta);
		BigDecimal validaId = valBigDecimal.validate(id);
		
		PagoBesManager  pagoBesManager = new PagoBesManager();
		TransaccionBes trxBD = pagoBesManager.getTransaccionByCodigoIdTrx(id);
		
		//Validar que el pago no haya sido rendido con anterioridad
		if (id!=null){
			
			DetalleRendicionBES detalleRendicionBESAux = mgrRendicion.getDetalleRendicionBesByPagoId(trxBD.getPago().getId());
			if (detalleRendicionBESAux!=null) {
				LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_YA_RENDIDO, 
						"", codPago.toString()));
			}
		}
		
		
		if (validaSubtipo == null){
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Subtipo", subtipo));
		}
		if (validaRutCompra == null){
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Rut Compra", rutCompra));
		}
		if (validaFecVen == null){
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Fecha Vencimiento", fecVen));
		}
		if (validaTxBanco == null){
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Transaccion Banco", txBanco));
		}
		if (validaFecOpe == null){
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Fecha Operacion", fecOpe));
		}
		if (validaHorOpe == null){
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Hora Operacion", horOpe));
		}
		if (validaFecConta == null){
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Fecha Contable", fecConta));
		}
		if (validaRutCuenta == null){
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Rut Cuenta", rutCuenta));
		}
		if (validaConvenio == null){
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Convenio", convenio));
		}
		if (validaMontoPago == null){
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Monto Pago", mtoPagoDet));
		}
		if (validaAlerta == null){
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Alerta", alerta));
		}
		if (validaId == null){
			LIST_ERROR_IMPORTACION_DETALLE.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Id pago", codPago));
		}
		
		// En este banco No se valida contenido de la informacion
		
		//SETEOS
		
		detalleRendicionBES.setCodigoPago(trxBD.getPago().getId() != null ? trxBD.getPago().getId():new BigDecimal(0));
		
		detalleRendicionBES.setSubTipo(validaSubtipo != null ? validaSubtipo.intValue():0);
		detalleRendicionBES.setRutCompra(validaRutCompra != null ? validaRutCompra.longValue():0);
		
		Date fechaVen = new Date();
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		if (validaFecVen != null){
			cal.setTime(Utiles.getFechaParse("yyyyMMdd", fecVen));
			fechaVen = Utiles.getFechaParse("yyyyMMdd", fecVen);
			if (cal.get(Calendar.YEAR) < 2000 && cal.get(Calendar.MONTH) > 0 && cal.get(Calendar.DAY_OF_MONTH) > 0){
				cal = null;
				fechaVen = Nulls.DATE;
			}
			
		}
		
		detalleRendicionBES.setFechaVencimiento(fechaVen);
		detalleRendicionBES.setTxBanco(validaTxBanco != null ? validaTxBanco.longValue():0);
		detalleRendicionBES.setFechaOperacion(validaFecOpe==null ||validaHorOpe == null ?Nulls.DATE:Utiles.getFechaParse("yyyyMMdd hh:mm:ss", fecOpe +" "+horOpe.substring(0,2)+":"+horOpe.substring(2,4)+":"+horOpe.substring(4,6)));
		detalleRendicionBES.setFechaContable(validaFecConta==null?Nulls.DATE:Utiles.getFechaParse("yyyyMMdd", fecConta));
		detalleRendicionBES.setRutCuenta(validaRutCuenta != null ? validaRutCuenta.longValue():0);
		detalleRendicionBES.setConvenio(validaConvenio != null ? validaConvenio.longValue():0);
		detalleRendicionBES.setMontoPago(validaConvenio != null ? validaMontoPago:new BigDecimal(0));
		detalleRendicionBES.setAlerta(validaAlerta != null ? validaAlerta.intValue():0);
		
		
		detalleRendicionBES.setTipoReg(tipReg);
		detalleRendicionBES.setDigRutCompra(digCompra);
		detalleRendicionBES.setGlosaMultipago(gloMultipag);
		detalleRendicionBES.setEstadoPago(estadoPag);
		detalleRendicionBES.setDigRutCuenta(digCuenta);
		detalleRendicionBES.setGlosaPago(gloPago);
		detalleRendicionBES.setIdentificador(id);
		detalleRendicionBES.setRespuestaBatch(resBatch);
		detalleRendicionBES.setFiller(filler);
		
		detalleRendicionBES.setListErrorImportacion(LIST_ERROR_IMPORTACION_DETALLE);
		
        return detalleRendicionBES;
	}
	
	/**
	 * Valida los pagos del Detalle de la DetalleRendicionPagosBES
	 * @param detalleRendicionBES
	 * @param mapa
	 * @return
	 */
	private ResumenDetalleRendicionBES validarRendicionResumenDetalle(ResumenDetalleRendicionBES resumenDetalleRendicionBES,Map mapa) throws ParseException {
		LIST_ERROR_IMPORTACION_DETALLE_RESUMEN = new ArrayList();
		
		String tipoReg = (String)mapa.get("TIP_REG");
		String subtipo = (String)mapa.get("SUBTIPO");
		String txBanco = (String)mapa.get("TX_BANCO");
		String numPagos = (String)mapa.get("NUM_PAGOS");
		String mtoPagos = (String)mapa.get("MTO_PAGOS");
		String filler = (String)mapa.get("FILLER");
		
		Integer validaSubtipo = valInteger.validate(subtipo);
		Integer validaTxBanco = valInteger.validate(txBanco);
		Integer validaNumPagos = valInteger.validate(numPagos);
		BigDecimal validaMtoPago = valBigDecimal.validate(mtoPagos);
		
		if (validaSubtipo == null){
			LIST_ERROR_IMPORTACION_DETALLE_RESUMEN.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Subtipo", subtipo));
		}
		if (validaTxBanco == null){
			LIST_ERROR_IMPORTACION_DETALLE_RESUMEN.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Transaccion Banco", txBanco));
		}
		if (validaNumPagos == null){
			LIST_ERROR_IMPORTACION_DETALLE_RESUMEN.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Numero de Pagos", numPagos));
		}
		if (validaMtoPago == null){
			LIST_ERROR_IMPORTACION_DETALLE_RESUMEN.add(getMensajeError(Constants.RENDIC_ERROR_IMP_VALIDACION, 
					DETALLES_KEY, "Monto del Pago", mtoPagos));
		}
		
		//SETEOS
		resumenDetalleRendicionBES.setResumenTipoRegistro(tipoReg);
		resumenDetalleRendicionBES.setResumenFiller(filler);
		resumenDetalleRendicionBES.setResumenMontoPago(validaMtoPago != null ? validaMtoPago : new BigDecimal(0));
		resumenDetalleRendicionBES.setResumenNumPagos(validaNumPagos != null ? validaNumPagos.intValue() : 0);
		resumenDetalleRendicionBES.setResumenSubTipo(validaSubtipo != null ? validaSubtipo.intValue() : 0);
		resumenDetalleRendicionBES.setResumenTxBanco(validaTxBanco != null ? validaTxBanco.longValue() : 0);
		
		resumenDetalleRendicionBES.setListErrorImportacion(LIST_ERROR_IMPORTACION_DETALLE);
		
        return resumenDetalleRendicionBES;
	}

	/**
	 * Metodo de inicio de la logica de procesamiento de rendicion BES
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
			RendicionBES rendicionBES = (RendicionBES) mapaRendicion.get(BEAN_RENDICION);
			
			if (rendicionBES!=null) {
				List listaErrorImportacionCabecera = rendicionBES.getListErrorImportacion();
				listaErrorImportacionCabecera = rendicionBES.getControlRendicionBES().getListErrorImportacion();
				listaErrorImportacionCabecera = rendicionBES.getEncabezadoRendicionBES().getListErrorImportacion();
				
				if (listaErrorImportacionCabecera.size()==0) {
					//CONCILIAR
					logger.info("Sin errores de importacion, se debe conciliar");
					
					Map mapaConciliacion = conciliarPagos(mapaRendicion, mgrPago);

					Integer importados = (Integer) mapaConciliacion.get(NRO_IMPORTADOS);
					Integer noImportados = (Integer) mapaConciliacion.get(NRO_NO_IMPORTADOS);
					Integer consistentes = (Integer) mapaConciliacion.get(NRO_CONSISTENTES);
					Integer inconsistentes = (Integer) mapaConciliacion.get(NRO_INCONSISTENTES);								
					Integer inconsistenteDetalleRendicion = (Integer) mapaConciliacion.get(NRO_INCONSISTENTES_DETS_RENDICION);								

					List listaInconsistentes = (ArrayList) mapaConciliacion.get(LISTA_INCONSISTENTES);//
					List listaInconsistentesPagos = (ArrayList) mapaConciliacion.get(LISTA_INCONSISTENTES_PAGOS);//
					List listaNoImportados = (ArrayList) mapaConciliacion.get(LISTA_NO_IMPORTADOS);
					List listaRendicionesOK = (ArrayList) mapaConciliacion.get(LISTA_RENDICIONES_OK);//
					
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
					rendicionBES.setChecksum(checksum);
					rendicionBES.setFchCaptura(fechaContable);
					
					List todos = (ArrayList) mapaRendicion.get(BEANS_DETALLES_RENDICION);//
					List todos2 = (ArrayList) mapaRendicion.get(BEANS_DETALLES_RENDICION);//
										
					List unos = new ArrayList();//
					List listaLineasInconsistentes = new ArrayList();//
					List listaLineasInconsistentesPagos = new ArrayList();//
					List listaLineasRendicionesOK = new ArrayList();//
					
					
					for (int x = 0 ; x < todos.size() ; x++){
						ResumenDetalleRendicionBES resumenDetalleRendicionBES3 = (ResumenDetalleRendicionBES) todos2.get(x);
						ResumenDetalleRendicionBES resumenDetalleRendicionBES2 = new ResumenDetalleRendicionBES();	
						resumenDetalleRendicionBES2.setCodError(resumenDetalleRendicionBES3.getCodError());
						resumenDetalleRendicionBES2.setIdRendicion(resumenDetalleRendicionBES3.getIdRendicion());
						resumenDetalleRendicionBES2.setIdResDetalleRend(resumenDetalleRendicionBES3.getIdResDetalleRend());
						resumenDetalleRendicionBES2.setListErrorImportacion(resumenDetalleRendicionBES3.getListErrorImportacion());
						resumenDetalleRendicionBES2.setListErrorInconsistente(resumenDetalleRendicionBES3.getListErrorInconsistente());
						resumenDetalleRendicionBES2.setResumenFiller(resumenDetalleRendicionBES3.getResumenFiller());
						resumenDetalleRendicionBES2.setResumenMontoPago(resumenDetalleRendicionBES3.getResumenMontoPago());
						resumenDetalleRendicionBES2.setResumenNumPagos(resumenDetalleRendicionBES3.getResumenNumPagos());
						resumenDetalleRendicionBES2.setResumenSubTipo(resumenDetalleRendicionBES3.getResumenSubTipo());
						resumenDetalleRendicionBES2.setResumenTipoRegistro(resumenDetalleRendicionBES3.getResumenTipoRegistro());
						resumenDetalleRendicionBES2.setResumenTxBanco(resumenDetalleRendicionBES3.getResumenTxBanco());				
					for (int i = 0; i < listaInconsistentes.size(); i++) {
							DetalleRendicionBES detalleRendicionBES = (DetalleRendicionBES) listaInconsistentes.get(i);
							
							if (detalleRendicionBES.getIdRepresentativo() == x+1){
								unos.add(detalleRendicionBES);
							}
						}
						if (unos.size() != 0){
							resumenDetalleRendicionBES2.setDetalleRendicionBES((DetalleRendicionBES[])unos.toArray( new DetalleRendicionBES[unos.size()] ));
							listaLineasInconsistentes.add(resumenDetalleRendicionBES2);
							unos = new ArrayList();
						}
					}
					session.setAttribute(LISTA_INCONSISTENTES, listaLineasInconsistentes);

					unos = new ArrayList();//
					for (int x = 0 ; x < todos.size() ; x++){
					ResumenDetalleRendicionBES resumenDetalleRendicionBES3 = (ResumenDetalleRendicionBES) todos2.get(x);
					ResumenDetalleRendicionBES resumenDetalleRendicionBES2 = new ResumenDetalleRendicionBES();	
					resumenDetalleRendicionBES2.setCodError(resumenDetalleRendicionBES3.getCodError());
					resumenDetalleRendicionBES2.setIdRendicion(resumenDetalleRendicionBES3.getIdRendicion());
					resumenDetalleRendicionBES2.setIdResDetalleRend(resumenDetalleRendicionBES3.getIdResDetalleRend());
					resumenDetalleRendicionBES2.setListErrorImportacion(resumenDetalleRendicionBES3.getListErrorImportacion());
					resumenDetalleRendicionBES2.setListErrorInconsistente(resumenDetalleRendicionBES3.getListErrorInconsistente());
					resumenDetalleRendicionBES2.setResumenFiller(resumenDetalleRendicionBES3.getResumenFiller());
					resumenDetalleRendicionBES2.setResumenMontoPago(resumenDetalleRendicionBES3.getResumenMontoPago());
					resumenDetalleRendicionBES2.setResumenNumPagos(resumenDetalleRendicionBES3.getResumenNumPagos());
					resumenDetalleRendicionBES2.setResumenSubTipo(resumenDetalleRendicionBES3.getResumenSubTipo());
					resumenDetalleRendicionBES2.setResumenTipoRegistro(resumenDetalleRendicionBES3.getResumenTipoRegistro());
					resumenDetalleRendicionBES2.setResumenTxBanco(resumenDetalleRendicionBES3.getResumenTxBanco());
						for (int i = 0; i < listaInconsistentesPagos.size(); i++) {
							Pago detalleRendicionBES = (Pago) listaInconsistentesPagos.get(i);
							
							if (detalleRendicionBES.getId().intValue() == x+1){
								unos.add(detalleRendicionBES);
							}
						}
						if (unos.size() != 0){
							resumenDetalleRendicionBES2.setDetalleRendicionBES((DetalleRendicionBES[])unos.toArray( new DetalleRendicionBES[unos.size()] ));
							listaLineasInconsistentesPagos.add(resumenDetalleRendicionBES2);
							unos = new ArrayList();
						}
					}
					
					List unos1 = new ArrayList();//
					session.setAttribute(LISTA_INCONSISTENTES_PAGOS, listaLineasInconsistentesPagos);
					for (int x = 0 ; x < todos2.size() ; x++){
					ResumenDetalleRendicionBES resumenDetalleRendicionBES3 = (ResumenDetalleRendicionBES) todos2.get(x);
					ResumenDetalleRendicionBES resumenDetalleRendicionBES2 = new ResumenDetalleRendicionBES();	
					resumenDetalleRendicionBES2.setCodError(resumenDetalleRendicionBES3.getCodError());
					resumenDetalleRendicionBES2.setIdRendicion(resumenDetalleRendicionBES3.getIdRendicion());
					resumenDetalleRendicionBES2.setIdResDetalleRend(resumenDetalleRendicionBES3.getIdResDetalleRend());
					resumenDetalleRendicionBES2.setListErrorImportacion(resumenDetalleRendicionBES3.getListErrorImportacion());
					resumenDetalleRendicionBES2.setListErrorInconsistente(resumenDetalleRendicionBES3.getListErrorInconsistente());
					resumenDetalleRendicionBES2.setResumenFiller(resumenDetalleRendicionBES3.getResumenFiller());
					resumenDetalleRendicionBES2.setResumenMontoPago(resumenDetalleRendicionBES3.getResumenMontoPago());
					resumenDetalleRendicionBES2.setResumenNumPagos(resumenDetalleRendicionBES3.getResumenNumPagos());
					resumenDetalleRendicionBES2.setResumenSubTipo(resumenDetalleRendicionBES3.getResumenSubTipo());
					resumenDetalleRendicionBES2.setResumenTipoRegistro(resumenDetalleRendicionBES3.getResumenTipoRegistro());
					resumenDetalleRendicionBES2.setResumenTxBanco(resumenDetalleRendicionBES3.getResumenTxBanco());
						for (int i = 0; i < listaRendicionesOK.size(); i++) {
							DetalleRendicionBES detalleRendicionBES = (DetalleRendicionBES) listaRendicionesOK.get(i);
							
							if (detalleRendicionBES.getIdRepresentativo() == x+1){
								unos1.add(detalleRendicionBES);
							}
						}
						
						
						if (unos1.size() != 0){
							resumenDetalleRendicionBES2.setDetalleRendicionBES((DetalleRendicionBES[])unos1.toArray( new DetalleRendicionBES[unos1.size()] ));
							listaLineasRendicionesOK.add(resumenDetalleRendicionBES2);
							unos1 = new ArrayList();
						}
					}
					session.setAttribute(LISTA_RENDICIONES_OK, listaLineasRendicionesOK);
					
													
					//Guardo en la session
					session.setAttribute(BEAN_RENDICION, rendicionBES);
					session.setAttribute(CODIGO_MEDIO_PAGO, codBanco);
					
				} else {
					//Cabecera con errores, informar al usuario
					logger.info("Existe errores de importacion, no se concilia");
					ActionTools.addMessage(request, new ActionMessage("rendicion.archivo.importacion.ErrorCabeceraControl"));
					frm.setFlagErrorCabeceraControl("1");
					
					frm.setListaNoImportados((ArrayList) rendicionBES.getListErrorImportacion());
					frm.setListaNoImportados((ArrayList) rendicionBES.getControlRendicionBES().getListErrorImportacion());
					frm.setListaNoImportados((ArrayList) rendicionBES.getEncabezadoRendicionBES().getListErrorImportacion());
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
		logger.info("Entre a conciliarPagos BES");
		
		Map mapa = new HashMap();
		Renderer renderer = new Renderer();
		int importados = 0;
		int noImportados = 0;
		int consistentes = 0;
		int inconsistentes = 0;
		int inconsistenteDetalleRendicion = 0; //Inconsistencia en un detalle del archivo, estos pagos se deben sumar a lo que se debe importar
		int inconsistentePago = 0; //Inconsistencia en el Pago de la base de datos
		
		ResumenDetalleRendicionBES aux = new ResumenDetalleRendicionBES();

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
		List auxResumen = new ArrayList();
		String codErrorAux = new String("");
		
		try {
			
			//ANALIZAR LAS TRANSACCIONES DE LA RENDICION
			List lineasRendicion = (ArrayList) mapaRendicion.get(BEANS_DETALLES_RENDICION);
			for (int i = 0; i < lineasRendicion.size(); i++) {
				ResumenDetalleRendicionBES resumenDetalleRendicionBES = (ResumenDetalleRendicionBES) lineasRendicion.get(i);

				if (logger.isDebugEnabled())
					logger.debug("Analizando resumenDetalleRendicionBES: " + resumenDetalleRendicionBES);
				
				for (int j = 0 ; j < resumenDetalleRendicionBES.getDetalleRendicionBES().length ; j++ ){
					
					BigDecimal idPago = resumenDetalleRendicionBES.getDetalleRendicionBES()[j].getCodigoPago();
					BigDecimal montoRendicion = resumenDetalleRendicionBES.getDetalleRendicionBES()[j].getMontoPago();
					idPago = idPago!=null?idPago:new BigDecimal("-1");
					idConvenio = (convenio!=null)?convenio.getId():new BigDecimal(0);
					logger.info("idPago rendicion: " + idPago);
					
					//ANALIZAR EL PAGO
					logger.info("Buscar pago con idPago: " + idPago + " e idConvenio: " + idConvenio);
					Pago pago = mgrPago.getPagoByIdPagoIdConvenio(idPago, idConvenio);
					
					if (pago!=null) { //PAGO-CONVENIO EXISTE
						logger.info("Pago-Convenio Existe... /idPago: " + idPago + " /idConvenio: " + idConvenio);
						
						//ANALIZAR SI SON CANDIDATOS A NO IMPORTADOS
						if (resumenDetalleRendicionBES.getDetalleRendicionBES()[j].getListErrorImportacion().size()>0) {
							logger.info("detalleRendicionBES no importada...");
							
							noImportados++;
							listaNoImportados.add(resumenDetalleRendicionBES.getDetalleRendicionBES()[j]);
							
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
								if (resumenDetalleRendicionBES.getDetalleRendicionBES()[j].getEstadoPago().equals("OK_")){
									resumenDetalleRendicionBES.getDetalleRendicionBES()[j].setAlerta(1);
									resumenDetalleRendicionBES.getDetalleRendicionBES()[j].setRespuestaBatch("OK_");
								}else if (resumenDetalleRendicionBES.getDetalleRendicionBES()[j].getEstadoPago().equals("NOK")){
									resumenDetalleRendicionBES.getDetalleRendicionBES()[j].setAlerta(2);
									resumenDetalleRendicionBES.getDetalleRendicionBES()[j].setRespuestaBatch("NOK");
								}
								listaRendicionesOK.add(resumenDetalleRendicionBES.getDetalleRendicionBES()[j]);							
							}
							
							if (!pagadoOk) { //PAGO INCONSISTENTE, no aparece pagado
								logger.info("Pago inconsistente... no aparece pagado");
								
								if (resumenDetalleRendicionBES.getDetalleRendicionBES()[j].getEstadoPago().equals("OK_")){
									resumenDetalleRendicionBES.getDetalleRendicionBES()[j].setAlerta(7);
									resumenDetalleRendicionBES.getDetalleRendicionBES()[j].setRespuestaBatch("OK_");
								}else if (resumenDetalleRendicionBES.getDetalleRendicionBES()[j].getEstadoPago().equals("NOK")){
									resumenDetalleRendicionBES.getDetalleRendicionBES()[j].setAlerta(8);
									resumenDetalleRendicionBES.getDetalleRendicionBES()[j].setRespuestaBatch("NOK");
								}
								
								codErrorAux = Constants.RENDIC_ERROR_INC_NO_PAGADO;
								setCodErrorDetalle(resumenDetalleRendicionBES.getDetalleRendicionBES()[j].getListErrorImportacion(), codErrorAux);
								
								listaInconsisAux.add(getMensajeError(codErrorAux, ""));
							}
							if (!montoOk) { //PAGO INCONSISTENTE, monto no corresponde
								logger.info("Pago inconsistente... monto no corresponde");
								codErrorAux = Constants.RENDIC_ERROR_INC_MONTO_DISTINTO;
								setCodErrorDetalle((resumenDetalleRendicionBES.getDetalleRendicionBES()[j].getListErrorImportacion()), codErrorAux);
								
								if (resumenDetalleRendicionBES.getDetalleRendicionBES()[j].getEstadoPago().equals("OK_")){
									resumenDetalleRendicionBES.getDetalleRendicionBES()[j].setAlerta(1);
									resumenDetalleRendicionBES.getDetalleRendicionBES()[j].setRespuestaBatch("OK_");
								}else if (resumenDetalleRendicionBES.getDetalleRendicionBES()[j].getEstadoPago().equals("NOK")){
									resumenDetalleRendicionBES.getDetalleRendicionBES()[j].setAlerta(2);
									resumenDetalleRendicionBES.getDetalleRendicionBES()[j].setRespuestaBatch("NOK");
								}
								
								listaInconsisAux.add(getMensajeError(codErrorAux, "", 
										renderer.formatMoney(montoRendicion), renderer.formatMoney(montoPago)));
							}
							
							//Guardar los codError detectados en el campo del bean
							resumenDetalleRendicionBES.getDetalleRendicionBES()[j].setCodError(getCodErrorDetalle((resumenDetalleRendicionBES.getDetalleRendicionBES()[j].getListErrorImportacion())));
							
							//Valido doble (si pago o monto inconsistente)
							if (!pagadoOk || !montoOk) {
								logger.info("Pago inconsistente... se lleva a la lista correspondiente.");
								
								inconsistenteDetalleRendicion++;
								resumenDetalleRendicionBES.getDetalleRendicionBES()[j].setListErrorInconsistente(listaInconsisAux);
								listaInconsistentes.add(resumenDetalleRendicionBES.getDetalleRendicionBES()[j]);
							}
						}
						
						//Guardar los ids de pagos para consulta de pagos no rendidos.
						setListaIdsPagos(idPago, listaIdsPagos);
						
					} else {
						//PAGO-CONVENIO NO EXISTE, NO IMPORTAR
						logger.info("Pago-Convenio No Existe, no importar... /idPago: " + idPago + " /idConvenio: " + idConvenio);
						
						noImportados++;
						if (resumenDetalleRendicionBES.getDetalleRendicionBES()[j].getEstadoPago().equals("OK_")){
							resumenDetalleRendicionBES.getDetalleRendicionBES()[j].setAlerta(4);
							resumenDetalleRendicionBES.getDetalleRendicionBES()[j].setRespuestaBatch("OK_");
						}else if (resumenDetalleRendicionBES.getDetalleRendicionBES()[j].getEstadoPago().equals("NOK")){
							resumenDetalleRendicionBES.getDetalleRendicionBES()[j].setAlerta(5);
							resumenDetalleRendicionBES.getDetalleRendicionBES()[j].setRespuestaBatch("NOK");
						}
						
						
						resumenDetalleRendicionBES.getListErrorImportacion().add(getMensajeError(Constants.RENDIC_ERROR_IMP_RENDIDO_NO_PAGADO,
												"", idPago.toString(), codConvenio));
						listaNoImportados.add(resumenDetalleRendicionBES.getDetalleRendicionBES()[j]);
					}
					
				}
				aux = resumenDetalleRendicionBES;
				auxResumen.add(aux);
			}
			
			//BUSCAR POR FCH CONTABLE PAGOS NO RENDIDOS (CANDIDATOS A INCONSISTENTES)
			RendicionBES rendicionBES = (RendicionBES) mapaRendicion.get(BEAN_RENDICION);
			fechaContable = rendicionBES.getEncabezadoRendicionBES().getFechaRendicion();
			
			List resumen = (ArrayList) mapaRendicion.get(BEANS_DETALLES_RENDICION);
					
			//Guardar las fechas contables para consulta de pagos no rendidos
			for (Iterator iter = resumen.iterator(); iter.hasNext();) {
				ResumenDetalleRendicionBES element = (ResumenDetalleRendicionBES) iter.next();				
				for (int i = 0 ; i < element.getDetalleRendicionBES().length ; i++){					
					setListaFechasContables( element.getDetalleRendicionBES()[i].getFechaContable(), listaFechasContables);
				}
			}
			
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
			logger.info("Sali de conciliarPagos BES");			
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
	 * Traspaso de bean detalleRendicionBES a bean generico
	 * @param listaRendiciones
	 * @return
	 */
	private List getDetalleRendicion(List listaRendiciones) {
		List result = new ArrayList();
		for (Iterator iter = listaRendiciones.iterator(); iter.hasNext();) {
			
			DetalleRendicionBES element = (DetalleRendicionBES) iter.next();
			DetalleRendicion detalleRendicion = new DetalleRendicion();
			
			detalleRendicion.setIdPago(element.getCodigoPago()!=null?element.getCodigoPago().toString():"0");
			detalleRendicion.setDetalle("");
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
			DetalleRendicionBES element = (DetalleRendicionBES) iter.next();
			i++;
				
				if (logger.isDebugEnabled())
					logger.debug("Rendicion consistente (OK) a importar, nro " + i + ":" + element);
				
		}
		
		for (Iterator iter = listaInconsistentes.iterator(); iter.hasNext();) {
			DetalleRendicionBES element = (DetalleRendicionBES) iter.next();
			i++;
			
			if (logger.isDebugEnabled())
				logger.debug("Rendicion inconsistente a importar, nro " + i + ":" + element);
			
		}
	}
	
}