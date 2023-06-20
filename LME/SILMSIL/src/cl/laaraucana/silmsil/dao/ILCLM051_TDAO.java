package cl.laaraucana.silmsil.dao;

import java.util.HashMap;

import org.apache.log4j.Logger;

public class ILCLM051_TDAO  extends DaoParent{

	private static Logger logger = Logger.getLogger(ILCLM051_TDAO.class);

	/**
	 * Ejecutar CL ILCSIL050 por registro.
	 * @return
	 * @throws Exception
	 */
	public static boolean callILCLM051_T(String periodo, String usuario,String glosa) throws Exception {
		
		boolean confirmacion = false;
		try {
			HashMap map =  new HashMap();
			map.put("PERIODO", periodo);
			map.put("USUARIO", usuario);
			map.put("GLOSA", glosa);			
			getConn().queryForObject("procValidarLM_T", map);	

		} catch (Exception e) {
			logger.error("Error ejecucion callILCLM051_T(): " + e.getMessage());
			e.printStackTrace();
		}
	return confirmacion;
	}
}
