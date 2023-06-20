package cl.laaraucana.silmsil.dao;

import java.util.HashMap;

import org.apache.log4j.Logger;

public class ILCSIL051DAO extends DaoParent {

	private static Logger logger = Logger.getLogger(ILCSIL051DAO.class);

	/**
	 * Ejecutar CL ILCSIL050 por proceso.
	 * @return
	 * @throws Exception
	 */
	public static boolean callILCSIL051(String periodo, String usuario) throws Exception {

		boolean confirmacion = false;
		try {
			HashMap map = new HashMap();
			map.put("PERIODO", periodo);
			map.put("USUARIO", usuario);
			map.put("GLOSA", " ");
			getConn().queryForObject("procValidarSIL", map);
			confirmacion = true;

		} catch (Exception e) {
			logger.error("Error callILCSIL051() 2 : " + e.getMessage());
			e.printStackTrace();
		}
		return confirmacion;
	}
}
