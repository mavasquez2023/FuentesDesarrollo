package cl.laaraucana.silmsil.dao;

import java.util.HashMap;

import org.apache.log4j.Logger;

/**
 * Clase para manejo sentencias SQL usuando IBATIS para proceso de 
 * generación de base intermedia para LM.
 * @author usist42
 *
 */
public class ILCLM050DAO extends DaoParent{

	private static Logger logger = Logger.getLogger(ILCLM050DAO.class);
	
	/**
	 * Ejecutar CL ILCLM050.
	 * @return
	 * @throws Exception
	 */
	public static boolean callILCLM050(String periodo, String usuario) throws Exception {
		boolean confirmacion = false;
		try {
			HashMap map =  new HashMap();
			map.put("PARAM", periodo+usuario);
			getConn().queryForObject("procCargaLM", map);
			confirmacion = true;
			
		} catch (Exception e) {
			logger.error("Error callILCLM050() 2 : " + e.getMessage());
			e.printStackTrace();
		}
		return confirmacion;
	}	
}
