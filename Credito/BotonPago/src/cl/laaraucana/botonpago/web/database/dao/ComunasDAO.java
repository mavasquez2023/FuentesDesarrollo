package cl.laaraucana.botonpago.web.database.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.botonpago.web.database.ibatis.domain.Afp51f1;

public class ComunasDAO extends DaoParent {
	protected Logger logger = Logger.getLogger(this.getClass());

	@SuppressWarnings("unchecked")
	public List<Afp51f1> selectAll() throws Exception {
		List<Afp51f1> datos = new ArrayList<Afp51f1>();
		//		SqlMapClient sqlMap = getConn();
		try {
			datos.addAll(getConn().queryForList("selectAll", null));

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error " + e.getMessage());
			throw new Exception("Falló al realizar la invoación al procedimiento");
		}
		//logger.debug("Salida: " + datos);

		return datos;
	}
}
