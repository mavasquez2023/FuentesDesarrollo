package cl.laaraucana.capaservicios.util;

import java.util.HashMap;
import cl.laaraucana.capaservicios.database.dao.ParametrosDAO;
import cl.laaraucana.capaservicios.xml.DatosComunas;

public class Constantes {
	
	// Códigos de respuesta servicios web
	public static final String COD_RESPUESTA_SUCCESS = "3";
	public static final String COD_RESPUESTA_ERROR = "5";
	public static final String COD_RESPUESTA_VACIO = "1";
	
	//Sistema de origen STL
	public static String ID_SISTEMA_ORIGEN;
	public static String USER_SISTEMA_ORIGEN; 
	public static String PASS_SISTEMA_ORIGEN;
	public static String NOMBRE_SISTEMA_ORIGEN; 
	public static String URL_NOTIFICACION; 
	public static String FLG_ENVIA_MENSAJE;
	public static String MONEDA_SISTEMA_ORIGEN;
	public static String KEY_SISTEMA_ORIGEN;
	public static String XML_ENCODING;
	
	//Mensajes simulacion
	public static String MSJ_SIM_CUOTA_NO_CUMPLE;
	public static String MSJ_SIM_MONTO_NO_CUMPLE;
	
	// Servicio web solicitud SMS
	public static String USER_SOL_SMS;
	public static String PASS_SOL_SMS;
	public static String EP_SOL_SMS;
	public static String COD_SERV_SMS;
	
	public static String SMS_MENSAJETXT;
	public static int SMS_MAX_INTENTOS;
	public static int SMS_TIEMPO_VIDA_CODSEG;
	public static String SMS_MAX_INTENTES_MSJ; 
	
	//Transferencias electronicas STL
	public static String MSG_SMS_ERROR;
	public static String COD_EST_REALIZADA;
	public static String COD_EST_RECHAZADA;
	
	//Actualización de comprobante de tesorería
	public static String TIPO_PAGO_TES_ERROR;
	
	//Curse en SAP
	public static String BUSINESS_AREA;
	public static String PAYMENT_METHOD;
	
	//Parámetros de simulación
	public static String MONTO_MIN_GIRO;
	public static String NRO_MAX_CUO; 
	public static String NRO_MIN_CUO;
	
	//Parámetros de envio de email
	public static String MAIL_SESSION;
	public static String ASUNTO_EMAIL_EXITO;
	public static String NRO_CALL_CENTER;	
	public static String NOMB_COMP_GIRO_EMAIL;
	public static String NOMB_CERT_AMORT_EMAIL;
	public static String NOMB_CERT_COB_DESG_EMAIL;

	//Estados de transferencia
	public static String GIRO_EN_PROCESO = "EN_PROCESO";
	//public static String CREDITO_COLOCADO = "CRED_COLOCADO";
	public static String ERROR_COLOCACION = "ERROR_COL";
	
	public static String SOL_STL_ENVIADA = "SOLICITUD_STL";
	public static String SOL_STL_ERROR = "ERROR_STL";
	
	//Estados posteriores a STL
	public static String EVENTO_CURSE ="TREASURY";
	public static String EVENTO_CURSE_ERROR ="ERROR_TREAS";
	public static String EVENTO_ERROR_NOT_TEF ="ERROR_NOT_TEF";
	public static String EVENTO_RESP_STL ="RESPUESTA_STL";
	public static String EVENTO_COLOCACION ="GIRO";
	
	//Ruta archivos de reportes e imágenes
	public static String RUTA_BASE = Configuraciones.getConfig("ruta.base");
	public static String RUTA_REPORTES = RUTA_BASE + Configuraciones.getConfig("ruta.reportes");
	public static String RUTA_IMG = RUTA_BASE + Configuraciones.getConfig("ruta.img");
	
	
	//Parámetros obtenidos desde Xml
	public static HashMap<String, String> REGIONES = null;
	public static HashMap<String, DatosComunas> COMUNAS = null;
	
	public static void getInstancia() throws Exception{
		cargarParametros();
	}
	
	private static void cargarParametros() throws Exception{
		ParametrosDAO paramDao = new ParametrosDAO();
			HashMap<String, String> parametros = paramDao.getParametros();
			// Sistema de origen STL
			ID_SISTEMA_ORIGEN = parametros.get("idSistemaOrigen");
			USER_SISTEMA_ORIGEN = parametros.get("userSistemaOrigen");
			PASS_SISTEMA_ORIGEN = parametros.get("passSistemaOrigen");
			KEY_SISTEMA_ORIGEN = parametros.get("keySistemaOrigen");
			NOMBRE_SISTEMA_ORIGEN = parametros.get("nombreSistemaOrigen"); 
			URL_NOTIFICACION = parametros.get("urlNotificacion"); 
			FLG_ENVIA_MENSAJE = parametros.get("flgEnviaMensaje");
			MONEDA_SISTEMA_ORIGEN = parametros.get("monedaSistemaOrigen");
			XML_ENCODING = parametros.get("xmlEncoding");
			
			// Servicio web solicitud SMS
			
			//USER_SOL_SMS = parametros.get("userSolSMS");
			//PASS_SOL_SMS= parametros.get("passUserSMS");
			EP_SOL_SMS= parametros.get("epSolSMS");
			COD_SERV_SMS = parametros.get("codServSms");
			
			SMS_MENSAJETXT = parametros.get("mensajeSolSMS");
			SMS_MAX_INTENTOS = Integer.parseInt(parametros.get("maxIntentosSolSMS"));
			SMS_MAX_INTENTES_MSJ = parametros.get("msjMaxIntentosSolSMS");
			try {
				SMS_TIEMPO_VIDA_CODSEG = Integer.parseInt(parametros.get("tiempoValidezCodSeg"));
			} catch (Exception e) {
				//Si no esta definido, por defecto es 5
				SMS_TIEMPO_VIDA_CODSEG = 5;
			}
			
			//Transferencia
			MSG_SMS_ERROR = parametros.get("msgSMSError");
			COD_EST_REALIZADA = parametros.get("estadoRealizada");
			COD_EST_RECHAZADA = parametros.get("estadoRechazada");
			TIPO_PAGO_TES_ERROR = parametros.get("tipoPagoTesError");
			
			//Curse SAP
			PAYMENT_METHOD = parametros.get("cursePaymentMethod");
			BUSINESS_AREA = parametros.get("businessArea");
			
			//Simulación
			MONTO_MIN_GIRO = parametros.get("montoMinGiro");
			NRO_MAX_CUO = parametros.get("nroMaxCuotas");
			NRO_MIN_CUO =  parametros.get("nroMinCuotas");
			
			//Envío de email
			MAIL_SESSION = parametros.get("mailSession");
			ASUNTO_EMAIL_EXITO = parametros.get("asuntoEmailExito");
			NRO_CALL_CENTER = parametros.get("nroCallCenter");
			
			NOMB_COMP_GIRO_EMAIL= parametros.get("nombCompGiroEmail");
			NOMB_CERT_AMORT_EMAIL= parametros.get("nombCertAmortEmail");
			NOMB_CERT_COB_DESG_EMAIL= parametros.get("nombCertCobDesgEmail");
			
			MSJ_SIM_CUOTA_NO_CUMPLE = parametros.get("montoCuotaNoAprobadoMsj");;
			MSJ_SIM_MONTO_NO_CUMPLE = parametros.get("montoAprobadoInsufMsj");;
	}
	
/***
	 * Retorna los codigos de area de chile
	 * @return
	 * @throws Exception 
	 *//*
	public static void getRegionesComunas() throws Exception{
		HashMap <String,String> regiones = new LinkedHashMap<String, String>();
		HashMap<String, DatosComunas> comunas = new LinkedHashMap<String, DatosComunas>();
		byte[] bites = null;
		String ruta = Configuraciones.getConfig("xml.regiones");
		bites = UtilLeerArchivo.readFileToBites(ruta);
		if(bites != null){
			try {
				JAXBContext context = JAXBContext.newInstance(cl.laaraucana.capaservicios.xml.Regiones.class);					  
				Unmarshaller um;
				um = context.createUnmarshaller();
				Regiones regionesObj = (Regiones) um.unmarshal(new StreamSource(new ByteArrayInputStream(bites)));
				
				for (int i = 0; i < regionesObj.getRegion().size(); i++) {
					Region region = regionesObj.getRegion().get(i);
					regiones.put(region.getIdRegion(), region.getNombreRegion());
					
					for (Comuna comuna : region.getComuna()) {
						DatosComunas datosCom = new DatosComunas();
						datosCom.setIdRegion(region.getIdRegion());
						datosCom.setNombreComuna(comuna.getNombreComuna());
						comunas.put(comuna.getIdComuna(), datosCom);
					}
				}
				Constantes.REGIONES = regiones;
				Constantes.COMUNAS = comunas;
			} catch (Exception e) {
				throw new Exception("Error al leer archivo de regiones");
			}
		}else{
			throw new Exception("Error al leer archivo de regiones");
		}
	}*/
}
