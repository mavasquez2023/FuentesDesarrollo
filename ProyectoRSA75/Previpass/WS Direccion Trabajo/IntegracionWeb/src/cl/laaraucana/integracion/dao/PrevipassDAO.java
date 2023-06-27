package cl.laaraucana.integracion.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import cl.laaraucana.integracion.util.Constantes;
import com.ibatis.sqlmap.client.SqlMapClient;

public class PrevipassDAO {

	private static String mensajeError=null;
	private static Log log = LogFactory.getLog(PrevipassDAO.class);
	
	public static String obtenerDestinatariosCorreo(){
		
		setMensajeError(null);
		String destinatarios="";
		SqlMapClient sqlMap = SqlMapInstanceExtranet.getInstance().getSqlMap();
		
		try{
			destinatarios = sqlMap.queryForObject("integracionNS.obtenerCorreos", Constantes.getInstancia().idparametrocp ).toString();
		}
		catch(Exception e){
			log.fatal("Ha ocurrido un Error al momento de Obtener el registro \n"+e);
			setMensajeError("Error Conectando a la Base de Datos con el jndi jdbc/integracion, mensaje:" + e.getMessage());
		}
		
		return destinatarios;
	}

	/**
	 * @return el mensajeError
	 */
	public static String getMensajeError() {
		return mensajeError;
	}

	/**
	 * @param mensajeError el mensajeError a establecer
	 */
	public static void setMensajeError(String mensajeError) {
		PrevipassDAO.mensajeError = mensajeError;
	}

	
	
	
	
	
	
}
