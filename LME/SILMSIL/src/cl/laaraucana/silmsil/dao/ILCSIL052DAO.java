package cl.laaraucana.silmsil.dao;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapSession;

public class ILCSIL052DAO extends DaoParent {

	private static Logger logger = Logger.getLogger(ILCSIL052DAO.class);

	/**
	 * Ejecutar CL ILCLSIL052.
	 * @return
	 * @throws Exception
	 */
	public static boolean callILCSIL052(String periodo, String usuario) throws Exception {

		boolean confirmacion = false;
		try {
			HashMap map = new HashMap();
			map.put("PERIODO", periodo);
			map.put("USUARIO", usuario);
			logger.info("Llego a llamar al CL ILCSIL052");
			SqlMapSession session= getConn().openSession();
			session.queryForObject("procGenerarPlanoSIL", map);
			session.close();
			confirmacion = true;

		} catch (Exception e) {
			logger.error("Error callILCLM052() 2 : " + e.getMessage());
			e.printStackTrace();
		}finally{
			getConn().endTransaction();
		}
		return confirmacion;
	}
}
