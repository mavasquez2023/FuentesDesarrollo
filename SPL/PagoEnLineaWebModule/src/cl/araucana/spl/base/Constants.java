package cl.araucana.spl.base;

import java.math.BigDecimal;

import cl.araucana.spl.util.Nulls;

public class Constants {
	
	public static final String RUT_ARAUCANA = "700161609"; //Rut sin puntos ni guion con dv incluido
	public static final String GLOSA_SERVICIO_BES = "Pago en Linea La Araucana";
	public static final int CODIGO_BANCO_BES = 12;
	public static final String XML_VERSION_BES = "<?xml version='1.0'?>";
	
	
	public static final boolean TESTING = true;
	
	// Codificación de la aplicación.
	public static final String CHARSET = "ISO-8859-1";
	
	// Codificación de xml de mensajería banco chile/santander
	public static final String CHARSET_XML = "UTF-8";
	
	
	public static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";
	public static final String JSCALENDAR_PATTERN = "%d/%m/%Y"; // Para configurar calendar.js
	public static final String DEFAULT_DATETIME_PATTERN = "dd/MM/yyyy HH:mm";
	public static final String DEFAULT_HOUR_PATTERN = "HH:mm";
	public static final String DB_DATE_PATTERN = "yyyy-MM-dd";

	////////// Parametros de interfaces con sistema de origen
	// Codificación del xml de integración con sistemas de origen
	public static final String CHARSET_XML_ENTRADA = "ISO-8859-1";

	// Para xml de entrada.
	public static final String FORMATO_FECHA_XML_ENTRADA = "yyyyMMddHHmmss";
	
	// Tiempo de expiración de peticiones desde sistema de origen
	public static final int MILISEGUNDOS_EXPIRACION_DESDE_ORIGEN = 1000*60*5; // 2 minutos.

	// Datos para cache de correlativos en xml de entrada
	public static final int LRU_CACHE_SIZE = 100;
	public static final int LRU_CACHE_TIMEOUT_MILLIS = 1000 * 60 * 5; // 5 minutos
	//
	//////////
	

	
	//Caracter para campos vacios (key de archivo resources)
	public static final String SIN_VALOR = "SIN_VALOR";
	
	public static final BigDecimal MEDIO_ACTIVO = new BigDecimal(1);
	
	// El estado devuelto por el banco que implica que se aplicó el cargo.
	public static final int BCI_ESTADO_CARGO_APLICADO = 21;
	
	// Parámetros de creación de la transaccion BChile
	//public static final String DATOS_ADICIONALES        = Nulls.STRING;

	// Códigos de retorno empleados en los mensajes (bch y bsa)
	public static final String COD_RETORNO_EXITO   = "0000";
	public static final String COD_RETORNO_RECHAZO = "0001";
	
	// Identificadores de los estados de una transacción (bch y bsa)
	public static final String TRX_NO_PAGADA = "N";
	public static final String TRX_PAGADA    = "S";	
	
	// Códigos de respuesta de la notificación
	public static final String NOTIFICACION_OK  = "OK";
	public static final String NOTIFICACION_NOK = "NOK";

	// Los medios de pago disponibles. Corresponde a MEDIO.CODIGO
	public static final String MEDIO_CODIGO_BCI = "BCI";
	public static final String MEDIO_CODIGO_TBC = "TBC";
	public static final String MEDIO_CODIGO_BSA = "BSA";
	public static final String MEDIO_CODIGO_BCH = "BCH";
	public static final String MEDIO_CODIGO_BES = "BES";
	public static final String MEDIO_CODIGO_BIT = "BIT";
	public static final String MEDIO_CODIGO_BBV = "BBV";
	
	// Valores de la columna PAGO.PAGADO.
	public static final Integer PAGO_PAGADO = new Integer(1);
	public static final Integer PAGO_NO_PAGADO = new Integer(0);
	public static final Integer PAGO_INICIAL = Nulls.INTEGER;

	// En "SalidaPago" para esperar estado de pago.
	public static final int ESTADO_PAGO_ITERACIONES_DE_ESPERA = 10;
	public static final int ESTADO_PAGO_MILISEGUNDOS_ENTRE_CONSULTAS = 5000;
	
	//Descripciones de la columna PAGO.PAGADO.
	public static final String PAGO_PAGADO_AUX = "PAGO_PAGADO_";
	
	//Rendiciones codigos de error
	public static final String RENDIC_ERROR_IMP_VALIDACION = "1";
	public static final String RENDIC_ERROR_IMP_RENDIDO_NO_PAGADO = "2";
	public static final String RENDIC_ERROR_IMP_PREDETERMINADO = "3";
	public static final String RENDIC_ERROR_IMP_NRO_CONVENIO = "4";
	public static final String RENDIC_ERROR_IMP_MONTO_TOTAL = "5";
	public static final String RENDIC_ERROR_IMP_REGISTROS_TOTALES = "6";
	public static final String RENDIC_ERROR_IMP_YA_RENDIDO = "7";
	public static final String RENDIC_ERROR_INC_NO_PAGADO = "100";
	public static final String RENDIC_ERROR_INC_PAGADO_DISTINTO = "101";
	public static final String RENDIC_ERROR_INC_MONTO_DISTINTO = "200";
	public static final String RENDIC_ERROR_INC_PAGADO_NO_RENDIDO = "300";
	
	public static final String RENDIC_ERROR_IMP_VALIDACION_N = "RENDIC_ERROR_IMP_VALIDACION";
	public static final String RENDIC_ERROR_IMP_RENDIDO_NO_PAGADO_N = "RENDIC_ERROR_IMP_RENDIDO_NO_PAGADO";
	public static final String RENDIC_ERROR_IMP_PREDETERMINADO_N = "RENDIC_ERROR_IMP_PREDETERMINADO";
	public static final String RENDIC_ERROR_IMP_NRO_CONVENIO_N = "RENDIC_ERROR_IMP_NRO_CONVENIO";
	public static final String RENDIC_ERROR_IMP_MONTO_TOTAL_N = "RENDIC_ERROR_IMP_MONTO_TOTAL";
	public static final String RENDIC_ERROR_IMP_REGISTROS_TOTALES_N = "RENDIC_ERROR_IMP_REGISTROS_TOTALES";
	public static final String RENDIC_ERROR_IMP_YA_RENDIDO_N = "RENDIC_ERROR_IMP_YA_RENDIDO";
	public static final String RENDIC_ERROR_INC_NO_PAGADO_N = "RENDIC_ERROR_INC_NO_PAGADO";
	public static final String RENDIC_ERROR_INC_PAGADO_DISTINTO_N = "RENDIC_ERROR_INC_PAGADO_DISTINTO";
	public static final String RENDIC_ERROR_INC_MONTO_DISTINTO_N = "RENDIC_ERROR_INC_MONTO_DISTINTO";
	public static final String RENDIC_ERROR_INC_PAGADO_NO_RENDIDO_N = "RENDIC_ERROR_INC_PAGADO_NO_RENDIDO";		
	
	//Rendicion BCI
	public static final String REND_BCI_FORMAPAGO_CUENTA = "C";
	public static final String REND_BCI_FORMAPAGO_TARJETA = "T";

	// Estado de pagos/cuadraturas. Antes de que se que se importe la rendicion esta siempre sin cuadrar.
	public static final BigDecimal ESTADO_PAGO_SIN_CUADRAR = new BigDecimal(1);
	public static final BigDecimal ESTADO_PAGO_CONSISTENTE = new BigDecimal(2);
	public static final BigDecimal ESTADO_PAGO_INCONSISTENTE = new BigDecimal(3);
	public static final BigDecimal ESTADO_PAGO_CORREGIDO = new BigDecimal(4);

	// Paginación en listado de inconsistencias.
	public static final int PAGE_SIZE = 20;

	/////////////////
	/////// Parametros de notificacion a sistema de origen.
	// Timeout para establecer la conexion
	public static final Long NOTIFICACION_CONNECTION_TIMEOUT = new Long(15*1000); // 15 segundos

	// Timeout para esperar data luego de establecida la conexion
	public static final Integer NOTIFICACION_DATA_TIMEOUT = new Integer(15*1000); // 15 segundos
	//
	/////////////////
	
	/** Código Sistema Bonificaciones */
	public static final String SISTEMA_BONIFICACIONES = "BonificacionesWeb";
	
	//Banco ITAU
	public static final String GLOSA_SERVICIO_ITAU = "Pago en Linea La Araucana";
	public static final String ID_PARAMETRO_SRVREC = "149";
	public static final String ID_PARAMETRO_IP_SPL_PRIVADA = "150";
	
	
	
	public static final String URL_Notificacion_Pago_BCI = "/PagoEnLineaBciWebModule/pagobci/NotificacionPago.do";
	public static final String URL_Notificacion_Pago_BCH = "/PagoEnLineaBchWebModule/pagobch/NotificacionPago.do";
	public static final String URL_Notificacion_Pago_BSA = "/PagoEnLineaBsaWebModule/pagobsa/NotificacionPago.do";
	public static final String URL_Notificacion_Pago_BSE = "/PagoEnLineaBesWebModule/pagobes/NotificacionPago.do";
	public static final String URL_Notificacion_Pago_BIT = "/PagoEnLineaBitWebModule/pagobit/NotificacionPago.do";
	public static final String URL_Notificacion_Pago_BBV = "/PagoEnLineaBbvWebModule/pagobbv/NotificacionPago.do";
	
	
	/*
	public static final String URL_Notificacion_Pago_BCI = "/PagoEnLineaWebModule/pagobci/NotificacionPago.do";
	public static final String URL_Notificacion_Pago_BCH = "/PagoEnLineaWebModule/pagobch/NotificacionPago.do";
	public static final String URL_Notificacion_Pago_BSA = "/PagoEnLineaWebModule/pagobsa/NotificacionPago.do";
	public static final String URL_Notificacion_Pago_BSE = "/PagoEnLineaWebModule/pagobes/NotificacionPago.do";
	public static final String URL_Notificacion_Pago_BIT = "/PagoEnLineaWebModule/pagobit/NotificacionPago.do";
	public static final String URL_Notificacion_Pago_BBV = "/PagoEnLineaWebModule/pagobbv/NotificacionPago.do";
	*/
}
