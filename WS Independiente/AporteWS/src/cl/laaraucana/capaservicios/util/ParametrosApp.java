package cl.laaraucana.capaservicios.util;

import java.util.HashMap;

import cl.laaraucana.config.Config;

public class ParametrosApp {
	public static ParametrosApp instancia;
	
	
	//Codigos de respuesta para servicios web
	public static final String COD_EMPTY = "1";
	public static final String COD_SUCCESS = "3";
	public static final String COD_ERROR = "5";

	//Estados de nomina de rendicion
	public static String COD_NOMINA_ING = "1";
	public static String COD_NOMINA_TES = "2";
	public static String COD_NOMINA_LIBRO = "3";
	public static String COD_NOMINA_SAP = "4";
	//Estados detalle de rendicion
	public static String COD_NOMINA_REC = "1";
	public static String COD_NOMINA_ERROR_REC = "-1";
		
	//Parametros de recuperacion SAP
	public static String TIPO_MONEDA="CLP";
	public static String CONTAB_TYPE_SAP;
	public static String ES_CREDITO_SAP;
	public static String PI_TYPE="O";
	public static String OFICINA_PAGO="001";//Oficina depago por defecto
	
	//Envio de email de error
	public static String SESION_EMAIL="mailSession/pagoNoAfiUnired";
	public static String EMAIL_NOTIF="mvasquezm@laaraucana.cl";
	
	public static String FILTRO_EMPLANILLADO;
	
}
