package cl.laaraucana.botonpago.web.database.dao;

import java.util.HashMap;

import org.apache.log4j.Logger;

import cl.laaraucana.botonpago.web.cobol.vo.EntradaSalidaRecuperaVO;

import com.ibatis.sqlmap.client.SqlMapClient;

public class RecuperacionAs400DAO extends DaoParent {
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * ejecuta el procedimiento almacenado llamado RECUPERA_CREDITO
	 * @param params
	 * @return EntradaSalidaRecuperaVO
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public EntradaSalidaRecuperaVO proRecupera(HashMap map) throws Exception {
		EntradaSalidaRecuperaVO entradaSalida = new EntradaSalidaRecuperaVO();

		SqlMapClient sqlMap = getConn();

		try {

			sqlMap.queryForObject("procTest", map);

			entradaSalida.setOfipro((String) map.get("OFIPRO"));
			entradaSalida.setCrefol((String) map.get("CREFOL"));
			entradaSalida.setMonpag((String) map.get("MONPAG"));
			entradaSalida.setTipocr((String) map.get("TIPOCR"));
			entradaSalida.setFoltes((String) map.get("FOLTES"));
			entradaSalida.setCoderr((String) map.get("CODERR"));

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("" + e.getMessage());
			throw new Exception("Error al recuperar el folio " + entradaSalida.getOfipro() + entradaSalida.getCrefol());
		}
		return entradaSalida;
	}//end CallproLibroBanco

}
