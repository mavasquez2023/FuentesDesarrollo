package cl.araucana.spl.base;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.log4j.Logger;

import cl.araucana.spl.beans.Mensaje;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.exceptions.RendicionException;
import cl.araucana.spl.mgr.PagoManager;
import cl.araucana.spl.mgr.RendicionManager;


/**
 * Clase padre rendiciones.
 * @author malvarez
 *
 */
public abstract class RendicionBase {
	private static final Logger logger = Logger.getLogger(RendicionBase.class);
	
	public static final ResourceBundle resourceApp = ResourceBundle.getBundle("cl.araucana.spl.resources.ApplicationResources");
	public static final IntegerValidator valInteger = IntegerValidator.getInstance();
	public static final BigDecimalValidator valBigDecimal = BigDecimalValidator.getInstance();
	
	public static final String CODIGO_CONVENIO = "CODIGO_CONVENIO";

	public static final String BEAN_RENDICION = "BEAN_RENDICION";
	public static final String BEAN_RENDICION_ENCABEZADO = "BEAN_RENDICION_ENCABEZADO";
	public static final String BEANS_DETALLES_RENDICION = "BEANS_DETALLES_RENDICION";
	
	//Constantes Actions
	public static final String LISTA_RENDICIONES_OK = "LISTA_RENDICIONES_OK";
	public static final String LISTA_NO_IMPORTADOS = "LISTA_NO_IMPORTADOS";
	public static final String LISTA_INCONSISTENTES = "LISTA_INCONSISTENTES";
	public static final String LISTA_INCONSISTENTES_PAGOS = "LISTA_INCONSISTENTES_PAGOS";
	public static final String NRO_INCONSISTENTES = "NRO_INCONSISTENTES";
	public static final String NRO_INCONSISTENTES_DETS_RENDICION = "NRO_INCONSISTENTES_DETS_RENDICION";
	public static final String NRO_CONSISTENTES = "NRO_CONSISTENTES";
	public static final String NRO_NO_IMPORTADOS = "NRO_NO_IMPORTADOS";
	public static final String NRO_IMPORTADOS = "NRO_IMPORTADOS";	
	public static final String CODIGO_MEDIO_PAGO = "CODIGO_MEDIO_PAGO";	
	public static final String FECHA_ARCHIVO_RENDICION = "FECHA_ARCHIVO_RENDICION";	
	public static final String BEAN_CONVENIO = "BEAN_CONVENIO";
	public static final String MGR_RENDICION = "MGR_RENDICION";
	public static final String FORM_IMPORTAR_RENDICION = "FORM_IMPORTAR_RENDICION";
	public static final String MGR_PAGO = "MGR_PAGO";
	public static final String MEDIO_PAGO = "MEDIO_PAGO";
	public static final String FORM_FILE = "FORM_FILE";
	public static final String NOMBRE_ARCHIVO = "NOMBRE_ARCHIVO";	
	
	//Otras
	public static final int NRO_DECIMALES_MONTOS_BCH = 4;
	public static final int NRO_DECIMALES_MONTOS_BSA = 3;
	public static final int NRO_DECIMALES_MONTOS_BIT = 3;
	
	protected abstract String initProcesarRendicion(Map mapaParametros,
			HttpServletRequest request, HttpSession session) throws Exception;

	protected abstract Map procesarArchivoRendicion(InputStream is,
			String codigoConvenio, RendicionManager mgrRendicion) throws RendicionException;

	protected abstract Map conciliarPagos(Map mapaRendicion, PagoManager mgrPago)
			throws PagoEnLineaException, ParseException;
	
	/**
	 * Entrega un BigDecimal valido.
	 * @param numero
	 * @return
	 */
	public BigDecimal getBigDecimal(Integer numero) {
		return new BigDecimal(numero==null?"0":numero.toString());
	}

	/**
	 * Retorna un BigDecimal formateado en decimales.
	 * @param numero
	 * @param nroDecimales, si es cero o negativo o se entregan decimales.
	 * @return
	 */
	public static BigDecimal getBigDecimal(BigDecimal numero, int nroDecimales) throws ArithmeticException {
		nroDecimales = (nroDecimales<0?0:nroDecimales);
		numero = (numero==null?new BigDecimal(0):numero);
		BigDecimal result = new BigDecimal(new BigInteger(numero.toString()), nroDecimales);
		return result;
	}
	
//	public static void main(String[] args) {
//		String s = "1197650000";
//		BigDecimal numero = new BigDecimal(s);
//		BigDecimal n2 = getBigDecimal(numero, 4);
//		System.out.println(numero);
//		System.out.println( "aqui: " +n2);
//
//		BigDecimal n3 = new BigDecimal(new BigInteger(s), 4);
//		BigDecimal n4 = n3.divide(new BigDecimal(10000), BigDecimal.ROUND_UNNECESSARY);
//		System.out.println(n3);
//		System.out.println(n4);
//	}
	
	/**
	 * Retorna un objeto mensaje segun los parametros entregados.
	 * @param codError
	 * @param tipo
	 * @return
	 */
	public Mensaje getMensajeError(String codError, String tipo) {
		Mensaje mensaje = new Mensaje();
		mensaje.setTipo(tipo);
		mensaje.setCodigo(codError);
		
		String keyDesc = getKeyDescrip(codError);
		mensaje.setDescripcion(resourceApp.getString(keyDesc));
		return mensaje;
	}

	/**
	 * Retorna un objeto mensaje segun los parametros entregados.
	 * @param codError
	 * @param tipo
	 * @param valor1
	 * @return
	 */
	public Mensaje getMensajeError(String codError, String tipo, String valor1) {
		Mensaje mensaje = new Mensaje();
		mensaje.setTipo(tipo);
		mensaje.setCodigo(codError);
		
		String keyDesc = getKeyDescrip(codError);
		mensaje.setDescripcion(getStrMessageFormat(resourceApp.getString(keyDesc), valor1));
		return mensaje;
	}

	/**
	 * Retorna un objeto mensaje segun los parametros entregados.
	 * @param codError
	 * @param tipo
	 * @param valor1
	 * @param valor2
	 * @return
	 */
	public Mensaje getMensajeError(String codError, String tipo, String valor1, String valor2) {
		Mensaje mensaje = new Mensaje();
		mensaje.setTipo(tipo);
		mensaje.setCodigo(codError);
		
		String keyDesc = getKeyDescrip(codError);
		mensaje.setDescripcion(getStrMessageFormat(resourceApp.getString(keyDesc), valor1, valor2));
		return mensaje;
	}
	
	
	public Mensaje getMensajeErrorKeyResources(String key, String tipo, String valor1, String valor2) {
		Mensaje mensaje = new Mensaje();
		mensaje.setTipo(tipo);
		mensaje.setCodigo(key);
		
		mensaje.setDescripcion(getStrMessageFormat(resourceApp.getString(key), valor1, valor2));
		return mensaje;
	}	
	
	
	/**
	 * Retorna la keyDescripcion del archivo properties que le corresponde al codigo de error. 
	 * @param codError
	 * @return
	 */
	private static String getKeyDescrip(String codError) {
		String result = "";
		if (codError.equalsIgnoreCase(Constants.RENDIC_ERROR_IMP_VALIDACION)) {
			result = Constants.RENDIC_ERROR_IMP_VALIDACION_N;
		} else if (codError.equalsIgnoreCase(Constants.RENDIC_ERROR_IMP_RENDIDO_NO_PAGADO)) {
			result = Constants.RENDIC_ERROR_IMP_RENDIDO_NO_PAGADO_N;
		} else if (codError.equalsIgnoreCase(Constants.RENDIC_ERROR_IMP_PREDETERMINADO)) {
			result = Constants.RENDIC_ERROR_IMP_PREDETERMINADO_N;
		} else if (codError.equalsIgnoreCase(Constants.RENDIC_ERROR_IMP_NRO_CONVENIO)) {
			result = Constants.RENDIC_ERROR_IMP_NRO_CONVENIO_N;
		} else if (codError.equalsIgnoreCase(Constants.RENDIC_ERROR_IMP_MONTO_TOTAL)) {
			result = Constants.RENDIC_ERROR_IMP_MONTO_TOTAL_N;
		} else if (codError.equalsIgnoreCase(Constants.RENDIC_ERROR_IMP_REGISTROS_TOTALES)) {
			result = Constants.RENDIC_ERROR_IMP_REGISTROS_TOTALES_N;
		} else if (codError.equalsIgnoreCase(Constants.RENDIC_ERROR_IMP_YA_RENDIDO)) {
			result = Constants.RENDIC_ERROR_IMP_YA_RENDIDO_N;
		} else if (codError.equalsIgnoreCase(Constants.RENDIC_ERROR_INC_NO_PAGADO)) {
			result = Constants.RENDIC_ERROR_INC_NO_PAGADO_N;			
		} else if (codError.equalsIgnoreCase(Constants.RENDIC_ERROR_INC_PAGADO_DISTINTO)) {
			result = Constants.RENDIC_ERROR_INC_PAGADO_DISTINTO_N;
		} else if (codError.equalsIgnoreCase(Constants.RENDIC_ERROR_INC_MONTO_DISTINTO)) {
			result = Constants.RENDIC_ERROR_INC_MONTO_DISTINTO_N;
		} else if (codError.equalsIgnoreCase(Constants.RENDIC_ERROR_INC_PAGADO_NO_RENDIDO)) {
			result = Constants.RENDIC_ERROR_INC_PAGADO_NO_RENDIDO_N;
		}
		
		return result;
	}
	
	
	/**
	 * Retorna una descripcion formateada.
	 * @param mensaje
	 * @param valor1
	 * @param valor2
	 * @return
	 */
	private static String getStrMessageFormat(String mensaje, String valor1, String valor2){
		Object args[] = { String.valueOf(valor1), String.valueOf(valor2) };
		return MessageFormat.format(mensaje, args);
	}
	
	/**
	 * Retorna una descripcion formateada
	 * @param mensaje
	 * @param valor1
	 * @return
	 */
	private static String getStrMessageFormat(String mensaje, String valor1){
		Object args[] = { String.valueOf(valor1) };
		return MessageFormat.format(mensaje, args);
	}
	
	
	/**
	 * Guarda los ids de los pagos encontrados en la base de datos.
	 * @param idPago
	 * @param listaIdPagos
	 */
	public void setListaIdsPagos(BigDecimal idPago, List listaIdPagos) {
		if (!listaIdPagos.contains(idPago)) {
			listaIdPagos.add(idPago);	
		}
	}
	
	/**
	 * Traspasa los pagosNoRendidos a otro vector, seteando ademas un mensaje.
	 * @param listPagosNoRendidos
	 * @param listaInconsistentesPagos
	 */
	public void setListaPagosNoRendidos(List listPagosNoRendidos, List listaInconsistentesPagos) {
		for (Iterator iter = listPagosNoRendidos.iterator(); iter.hasNext();) {
			Pago element = (Pago) iter.next();
			String idPago = element.getId().toString();
			element.setMensaje(getMensajeError(Constants.RENDIC_ERROR_INC_PAGADO_NO_RENDIDO, "", idPago));
			listaInconsistentesPagos.add(element);
		}
	}
	
	/**
	 * Acumula en una lista los cod error detectados en el detalle de la rendicion 
	 * @param listaCodError
	 * @param codError
	 */
	public void setCodErrorDetalle(List listaCodError, String codError) {
		logger.info("Entre a setCodErrorDetalle... codError: " + codError);
		if (!listaCodError.contains(codError)) {
			listaCodError.add(codError);
		}
	}
	
	/**
	 * Devuelve los cod error separados por coma.
	 * @param listaCodError
	 * @return
	 */
	public String getCodErrorDetalle(List listaCodError) {
		logger.info("Entre a getCodErrorDetalle");
		if (listaCodError == null || listaCodError.size() == 0)
			return "0";
		
		logger.info("listaCodError>0");
		StringBuffer sb = new StringBuffer("");
		boolean segundo = false;
		for (Iterator iter = listaCodError.iterator(); iter.hasNext();) {
			String element = (String) iter.next();
			if (segundo)
				sb.append(",");
			sb.append(element);
			segundo = true;
		}
		logger.info("Sali de getCodErrorDetalle..." + sb.toString());
		return sb.toString();
	}
	
	
}
