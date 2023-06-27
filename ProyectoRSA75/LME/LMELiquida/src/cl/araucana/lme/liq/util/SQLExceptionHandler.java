package cl.araucana.lme.liq.util;

import java.sql.SQLException;


import cl.araucana.util.email.Email;

public class SQLExceptionHandler{

	private static String connErrorSQLStates = ConfiguracionesLiq.getConfig("conn.error.sql.states");
	private static String[] sqlStates = connErrorSQLStates.split(",");
	
	private static int cantErroresAntesNotif = Integer.parseInt(ConfiguracionesLiq.getConfig("conn.error.qty"));//Cantidad de errores de conexion que se deben dar antes de notificar
	private static int erroresCount = 0;
	private static boolean notificadoEmail = false;//Para evitar multiples envios de correo
	
	
	public static void reiniciarContadorErrores(){
		erroresCount = 0;
		notificadoEmail = false;
	}
	
	/**
	 * Identifica errores de SQLException que corresponden a errores de conexion
	 * @param e
	 */
	public static void handleSQLException(SQLException e){
		if(e!=null && e.getSQLState()!=null){
			for (int i = 0; i < sqlStates.length; i++) {
				if(e.getSQLState().equals(sqlStates[i])){
					erroresCount++;
					String cause = "SQLState: " + e.getSQLState() + " ";
					cause += (e.getMessage() == null) ? "" : e.getMessage().toString();
					//Verifica si ya fue notificado el error en este ciclo
					if(!notificadoEmail && erroresCount>=cantErroresAntesNotif){
						//Si no hubo problemas al enviar el correo, marca como notificado
						if(enviarCorreoNotificacion(cause)){
							notificadoEmail = true;
						}
					}
					//Termina la ejecución de los procesos siguientes
					//LmeCiclo.setContinuarCiclo(false);
					return;
				}
			}
		}
	}
	
	/**
	 * Envia notificación sin validar si ya habia sido notificado
	 * @param e
	 */
	public static void handleSQLExceptionNoValidate(SQLException e){
		if(e!=null && e.getSQLState()!=null){
			for (int i = 0; i < sqlStates.length; i++) {
				if(e.getSQLState().equals(sqlStates[i])){
					String cause = "SQLState: " + e.getSQLState() + " ";
					cause += (e.getMessage() == null) ? "" : e.getMessage().toString();
					enviarCorreoNotificacion(cause);
					//Termina la ejecución de los procesos siguientes
					return;
				}
			}
		}
	}
	
	/**
	 * Envia correo utilizando parámetros definidos en la BD
	 * @param mensajeError
	 */
	private static boolean enviarCorreoNotificacion(String mensajeError){
		boolean res = false;
		String mailSession = BdConstantsLiq.getInstance().MAIL_SESSION;
		String to = BdConstantsLiq.getInstance().DESTINO_CORREO_NOTIF;
		String subject = BdConstantsLiq.getInstance().ASUNTO_CORREO_NOTIF;
		String bodyContent = BdConstantsLiq.getInstance().CUERPO_CORREO_NOTIF;
		bodyContent = bodyContent.replaceAll("<DETALLE_ERROR/>", mensajeError);
		
		try {
			Email emailUtil = new Email(mailSession);
			emailUtil.sendEmail(to, subject, bodyContent);
			res = true;
		} catch (Exception e) {
			//No realiza nada
			e.printStackTrace();
		}
		return res;
	}
}
