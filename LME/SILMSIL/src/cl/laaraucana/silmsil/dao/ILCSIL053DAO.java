package cl.laaraucana.silmsil.dao;

import java.util.HashMap;

import org.apache.log4j.Logger;

public class ILCSIL053DAO extends DaoParent {

	private static Logger logger = Logger.getLogger(ILCSIL053DAO.class);

	/**
	 * Ejecutar CL ILCSIL053 por proceso.
	 * @return
	 * @throws Exception
	 */
	public static boolean callILCSIL053(String periodo, String usuario) throws Exception {

		boolean confirmacion = false;
		try {
			HashMap map = new HashMap();
			map.put("PERIODO", periodo);
			map.put("USUARIO", usuario);
			getConn().queryForObject("procCargarYValidarSIL", map);
			confirmacion = true;

		} catch (Exception e) {
			logger.error("Error callILCSIL053() 2 : " + e.getMessage());
			e.printStackTrace();
		}
		return confirmacion;
	}
}
