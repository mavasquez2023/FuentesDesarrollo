package cl.laaraucana.silmsil.dao;

import java.util.HashMap;

import org.apache.log4j.Logger;

/**
 * Clase para manejo sentencias SQL usuando IBATIS para proceso de 
 * generación de base intermedia para SIL.
 * @author usist42
 *
 */
public class ILCSIL050DAO extends DaoParent {

	private static Logger logger = Logger.getLogger(ILCSIL050DAO.class);

	/**
	 * Ejecutar CL ILCSIL050 por proceso.
	 * @return
	 * @throws Exception
	 */
	public static boolean callILCSIL050(String periodo, String usuario) throws Exception {

		boolean confirmacion = false;
		try {
			HashMap map = new HashMap();
			map.put("PERIODO", periodo);
			map.put("USUARIO", usuario);
			getConn().queryForObject("procCargaSIL", map);
			confirmacion = true;

		} catch (Exception e) {
			logger.error("Error callILCSIL050() 2 : " + e.getMessage());
			e.printStackTrace();
		}
		return confirmacion;
	}
}
