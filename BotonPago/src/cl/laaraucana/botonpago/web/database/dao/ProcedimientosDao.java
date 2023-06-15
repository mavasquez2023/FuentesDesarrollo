package cl.laaraucana.botonpago.web.database.dao;

import java.util.HashMap;

import org.apache.log4j.Logger;

import cl.laaraucana.botonpago.web.cobol.vo.EntradaSalidaFolioVO;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ProcedimientosDao extends DaoParent {

	private Logger logger = Logger.getLogger(this.getClass());

	@SuppressWarnings("rawtypes")
	public EntradaSalidaFolioVO proFOLIO(HashMap params) throws Exception {

		SqlMapClient sqlMap = getConn();
		EntradaSalidaFolioVO entradaSalidaFolio = new EntradaSalidaFolioVO();
		try {
			sqlMap.queryForObject("CONSULTA_FOLIO", params);
			entradaSalidaFolio.setStatus((String) params.get("status"));
			entradaSalidaFolio.setCodigo((String) params.get("codigo"));
			entradaSalidaFolio.setCodigo2((String) params.get("codigo2"));
			entradaSalidaFolio.setFolio((String) params.get("folio"));

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("" + e.getMessage());
			throw new Exception("Error al obtener el folio");
		}
		return entradaSalidaFolio;
	}//end CallproLibroBanco

}
