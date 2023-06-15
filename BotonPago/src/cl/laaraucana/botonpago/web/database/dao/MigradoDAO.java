package cl.laaraucana.botonpago.web.database.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import cl.laaraucana.botonpago.web.database.ibatis.domain.Crf5006;

import com.ibatis.sqlmap.client.SqlMapClient;

public class MigradoDAO extends DaoParent {

	protected Logger logger = Logger.getLogger(this.getClass());

	public Boolean isMigradoByRut(String rut, String dv) throws Exception {
		Boolean migrado = null;
		SqlMapClient sqlMap = getConn();
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("rut", rut);
			map.put("dv", dv);
			@SuppressWarnings("unchecked")
			ArrayList<Crf5006> crf5006 = (ArrayList<Crf5006>) sqlMap.queryForList("selectMigrado", map);
			if (crf5006 == null || crf5006.size() == 0) {
				migrado = false;
			} else {
				migrado = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("" + e);
			logger.error("Error al consultar a la base de datos CRDTA/Crf5006 cause: " + e);
		}
		return migrado;
	}
}
