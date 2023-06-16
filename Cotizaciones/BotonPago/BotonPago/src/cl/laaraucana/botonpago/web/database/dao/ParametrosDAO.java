package cl.laaraucana.botonpago.web.database.dao;

import java.util.HashMap;

import org.apache.log4j.Logger;

/**
 * clase que realiza la ejecucion de la sentencia ibatis para obtener la lista de tabla parametro
 * **/
public class ParametrosDAO extends DaoParent {
	/**
	 * obtiene todos los parametros de la tabla T_PARAMETRO para inicialisarlo en las constantes de la aplicacion
	 * @return HashMap
	 * @throws Exception
	 */

	private Logger logger = Logger.getLogger(this.getClass());

	@SuppressWarnings("unchecked")
	public HashMap<String, String> getParametros() throws Exception {
		//		SqlMapClient sqlMap = getConn();
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			map = (HashMap<String, String>) getConn().queryForMap("getParametros", null, "key", "value");
			//ImprimeUtil.imprimirHash(map);

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("" + e.getMessage());
			throw new Exception("error al obtener lista parametros");
		}
		return (map);
	}//end getParametros
}//end class ParametrosDAO
