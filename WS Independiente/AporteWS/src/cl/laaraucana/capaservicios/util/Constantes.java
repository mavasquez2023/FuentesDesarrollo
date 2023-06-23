package cl.laaraucana.capaservicios.util;


public class Constantes {
	
	// Códigos de respuesta servicios web
	public static final String COD_RESPUESTA_SUCCESS = "3";
	public static final String COD_RESPUESTA_ERROR = "5";
	public static final String COD_RESPUESTA_VACIO = "1";
	
	//Curse en SAP
	public static String BUSINESS_AREA;
	public static String PAYMENT_METHOD;
	
	//Parámetros de envio de email
	public static String MAIL_SESSION;
	public static String ASUNTO_EMAIL_EXITO;
	public static String NRO_CALL_CENTER;	
	public static String NOMB_COMP_GIRO_EMAIL;
	public static String NOMB_CERT_AMORT_EMAIL;
	public static String NOMB_CERT_COB_DESG_EMAIL;
	
	//Ruta archivos de reportes e imágenes
	public static String RUTA_BASE = Configuraciones.getConfig("ruta.base");
	public static String RUTA_REPORTES = RUTA_BASE + Configuraciones.getConfig("ruta.reportes");
	public static String RUTA_IMG = RUTA_BASE + Configuraciones.getConfig("ruta.img");
	
}
