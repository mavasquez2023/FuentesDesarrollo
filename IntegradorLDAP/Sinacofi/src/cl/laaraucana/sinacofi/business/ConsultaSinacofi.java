/**
 * 
 */
package cl.laaraucana.sinacofi.business;

import org.apache.log4j.Logger;

import cl.laaraucana.sinacofi.clientesws.model.ConstantesRespuestasWS;
import cl.laaraucana.sinacofi.clientesws.vo.SalidaSinacofiVO;

/**
 * @author IBM Software Factory
 *
 */
public class ConsultaSinacofi {
	protected Logger logger = Logger.getLogger(this.getClass());
	private String url;
	private String usuario;
	private String clave;
	
	public ConsultaSinacofi(String url, String usuario, String clave){
		this.url= url;
		this.usuario=usuario;
		this.clave= clave;
	}
	
	public String consultaSinacofi(String rut, String serie){
		logger.info("Consultando Sinacofi, Rut:" + rut + ", serie=" + serie);
		//Consulta Sinacofi
		String mensaje="";
		SalidaSinacofiVO salida2= UtilInfoAfiliado.consultaSinacofi(url, usuario, clave, rut, serie);
		if(salida2!=null && salida2.getCodigoError()==ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS){
			logger.info("Respuesta Sinacofi, codigo retorno= " + salida2.getCodigoRetorno());
			if(salida2.getCodigoRetorno().equals("10000")){
				logger.info("Cedula Vigente=" + salida2.getCedulaVigente() + ".");
				if(salida2.getCedulaVigente().equals("NO")){
					logger.info("Respuesta a Cédula NO Vigente");
					mensaje= "cedula_error";
				}else{
					logger.info("Respuesta a Cédula Vigente");
					//Exito en ambas validaciones
					mensaje="cedula_vigente";
				}
			}else{
				if(salida2.getCodigoRetorno().equals("10001")){
					mensaje="Error en parámetros de entrada";
				}else if(salida2.getCodigoRetorno().equals("10002")){
					mensaje="Error interno del servicio";
				}else if(salida2.getCodigoRetorno().equals("10003")){
					mensaje="Error en la autenticación del usuario";
				}else if(salida2.getCodigoRetorno().equals("10004")){
					mensaje="Error de permiso";
				}else if(salida2.getCodigoRetorno().equals("10005")){
					mensaje="RUT no válido";
				}
			}
		}else{
			logger.info("Servicio Sinacofi Error");
			//Si servicio SINACOFI no responde se notifica error
			mensaje="servicio_error";

		}
		return mensaje;
	}
}
