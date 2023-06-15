/**
 * 
 */
package cl.araucana.wsempresa.manager;

import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

import cl.araucana.wsempresa.ibatis.dao.AfiliacionDAO;
import cl.araucana.wsempresa.vo.ResponseWS;




/**
 * @author J-Factory
 *
 */
public class ConsultaAfiliadoSistemas {
	private static Logger log = Logger.getLogger(ConsultaAfiliadoSistemas.class);
	public static ResponseWS obtenerEstadoAfiliacion(int rutEmpresa) throws SQLException {
		ResponseWS respuesta= new ResponseWS();
		log.info("Consultando DB2, RUT: " + rutEmpresa);	
		Map<String, String> response=AfiliacionDAO.obtenerEstadoAfiliacionDB2(rutEmpresa);
		respuesta.setEstado(Integer.parseInt(response.get("ESTADO")));
		respuesta.setRazonSocial(response.get("RAZON_SOCIAL"));
		respuesta.setGlosaError(response.get("GLOSA_ERROR"));
		return respuesta;
	}
	
}
