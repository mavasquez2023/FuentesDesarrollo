package cl.laaraucana.silmsil.dao;

import java.util.HashMap;

import org.apache.log4j.Logger;

public class ILCSIL051_TDAO extends DaoParent{

	private static Logger logger = Logger.getLogger(ILCSIL051_TDAO.class);

	/**
	 * Ejecutar CL ILCSIL050 por proceso.
	 * @return
	 * @throws Exception
	 */
	public static boolean callILCSIL051_T(String periodo, String usuario,String glosa) throws Exception {
		
		boolean confirmacion = false;
		try {
			HashMap map =  new HashMap();
			map.put("PERIODO", periodo);
			map.put("USUARIO", usuario);
			map.put("GLOSA", glosa);
			getConn().queryForObject("procValidarSIL_T", map);

		} catch (Exception e) {
			logger.error("Error ejecucion callILCSIL051_T(): " + e.getMessage());
			e.printStackTrace();
		}
	return confirmacion;
	}
}
