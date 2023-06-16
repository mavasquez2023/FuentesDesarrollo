package cl.araucana.fonasa.utils;

import java.util.ResourceBundle;

public class ConstantsUtil {

	public static final ResourceBundle RES_CONFIGURACION = ResourceBundle.getBundle("etc.config");
	public static final ResourceBundle MAIL_PROPERTIES = ResourceBundle.getBundle("etc/mail");
	public static final String SESION_EXPIRED = "Se ha terminado la sesión";	
	public static final String WS_UNAVAILABLE = "Web Service no disponible";
	public static final String CONTENT_TYPE_PDF = "application/pdf";
	public static final String CONTENT_TYPE_JSON = "text/json";
}
