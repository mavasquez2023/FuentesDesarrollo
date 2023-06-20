package cl.laaraucana.silmsil.dao;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapSession;

public class ILCLM052DAO extends DaoParent {

	private static Logger logger = Logger.getLogger(ILCLM052DAO.class);
	
	/**
	 * Ejecutar CL ILCLM052.
	 * @return
	 * @throws Exception
	 */
	public static boolean callILCLM052(String periodo, String usuario) throws Exception {
		
		boolean confirmacion = false;
		try {
			HashMap map =  new HashMap();
			map.put("PERIODO", periodo);
			map.put("USUARIO", usuario);
			SqlMapSession session= getConn().openSession();
			session.queryForObject("procGenerarPlanoLM", map);
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
