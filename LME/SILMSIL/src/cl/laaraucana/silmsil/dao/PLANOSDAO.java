package cl.laaraucana.silmsil.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import cl.laaraucana.silmsil.vo.ILFLM052VO;
import cl.laaraucana.silmsil.vo.ILFSIL052VO;

import com.ibatis.sqlmap.client.SqlMapSession;

public class PLANOSDAO extends DaoParent {

	private static Logger logger = Logger.getLogger(PLANOSDAO.class);

	/**
	 * Método para obtener datos para la creación de archivo plano SIL.
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<ILFSIL052VO> obtenerDatosPlanoSIL() throws Exception {

		ArrayList<ILFSIL052VO> silList = null;
		try {
			SqlMapSession session = getConn().openSession();
			silList = (ArrayList<ILFSIL052VO>) session.queryForList("generarPlanoSil", null);
			session.close();

			logger.info("Tamaño lista ilfsil052 : " + silList.size());
		} catch (Exception e) {
			logger.error("Error al obtenerDatosPlanoSIL" + e.getMessage());
			e.printStackTrace();
		} finally {
			getConn().endTransaction();
		}
		return silList;
	}

	public static int limpiaIlfsil052() throws Exception {
		int n = 0;
		try {
			SqlMapSession session = getConn().openSession();
			n = session.delete("limpiaIlfsil052", null);
			session.close();

		} catch (Exception e) {
			logger.error("Error limpiar tabla ILFSIL052" + e.getMessage());
			e.printStackTrace();
		} finally {
			getConn().endTransaction();
		}
		return n;
	}

	/**
	 * Método para obtener datos para la creación de archivo plano LM.
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<ILFLM052VO> obtenerDatosPlanoLM() throws Exception {

		ArrayList<ILFLM052VO> lmList = null;
		try {
			SqlMapSession session = getConn().openSession();
			lmList = (ArrayList<ILFLM052VO>) session.queryForList("generarPlanoLm", null);
			session.close();
			logger.info("Tamaño lista ilflm052 : " + lmList.size());

		} catch (Exception e) {
			logger.error("Error al obtenerDatosPlanoLM" + e.getMessage());
			e.printStackTrace();
		} finally {
			getConn().endTransaction();
		}
		return lmList;
	}

	public static int limpiaIlflm052() throws Exception {
		int n = 0;
		try {
			SqlMapSession session = getConn().openSession();
			n = session.delete("limpiaIlflm052", null);
			session.close();

		} catch (Exception e) {
			logger.error("Error limpiar tabla ILFLM052" + e.getMessage());
			e.printStackTrace();
		} finally {
			getConn().endTransaction();
		}
		return n;
	}

	/**
	 * Método para obtener datos de cuadro estadisitico.
	 * @return
	 * @throws Exception
	 */
	public static ArrayList obtenerEstadistico_LM(String periodo) {

		ArrayList ceLM_List = null;
		try {
			HashMap ceMap = new HashMap();
			ceMap.put("periodo", periodo);
			ceLM_List = (ArrayList) getConn().queryForList("generarPlanoEstadistico_LM", ceMap);

		} catch (Exception e) {
			logger.error("Error query obtenerEstadisticoSILMSIL" + e.getMessage());
			e.printStackTrace();
		}
		return ceLM_List;
	}//END obtenerEstadistico_LM()

	/**
	 * Método para obtener datos de cuadro estadisitico.
	 * @return
	 * @throws Exception
	 */
	public static ArrayList obtenerEstadistico_SIL(String periodo) {

		ArrayList ceSIL_List = null;
		try {
			HashMap ceMap = new HashMap();
			ceMap.put("periodo", periodo);
			ceSIL_List = (ArrayList) getConn().queryForList("generarPlanoEstadistico_SIL", ceMap);

		} catch (Exception e) {
			logger.error("Error query obtenerEstadisticoSILMSIL" + e.getMessage());
			e.printStackTrace();
		}
		return ceSIL_List;
	}//END obtenerEstadistico_SIL()

}
