/**
 * 
 */
package cl.laaraucana.ventaremota.ibatis.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.laaraucana.ventaremota.ibatis.config.MyClassConfig;
import cl.laaraucana.ventaremota.ibatis.vo.PreguntaVO;
import cl.laaraucana.ventaremota.ibatis.vo.RespuestaVO;
import cl.laaraucana.ventaremota.ibatis.vo.ResultadosBitacoraVO;

/**
 * @author IBM Software Factory
 *
 */
public class PreguntasDaoImpl implements PreguntasDao {
	
	private static final Logger logger = Logger.getLogger(PreguntasDaoImpl.class);
	
	/* (non-Javadoc)
	 * @see cl.laaraucana.ventaremota.ibatis.dao.PreguntasDao#obtienePreguntas(java.util.HashMap)
	 */
	@Override
	public List<PreguntaVO> obtienePreguntas(
			HashMap<String, Integer> idPreguntas) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			@SuppressWarnings("unchecked")
			List<PreguntaVO> queryForList = (List<PreguntaVO>)sqlMap.queryForList("credito.preguntas", idPreguntas);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see cl.laaraucana.ventaremota.ibatis.dao.PreguntasDao#obtieneRespuestas(int)
	 */
	@Override
	public List<RespuestaVO> obtieneRespuestas(int idPregunta) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			@SuppressWarnings("unchecked")
			List<RespuestaVO> queryForList = (List<RespuestaVO>)sqlMap.queryForList("credito.respuestas", idPregunta);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int totalPreguntas() throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			@SuppressWarnings("unchecked")
			Integer queryForObject = (Integer)sqlMap.queryForObject("credito.totalPreguntas", null);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public void insertRespuestasBitacora(ResultadosBitacoraVO resultado)
			throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource", e);
		}

		try {
			sqlMap.insert("credito.insertResultados", resultado);

		} catch (Exception e) {

			logger.error("Error ws ", e);

			throw new Exception("Error insertRespuestasBitacora ", e);
		}
		
	}

	@Override
	public void updateIntentosRespuestas(ResultadosBitacoraVO resultado) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource", e);
		}

		try {
			sqlMap.update("credito.updateIntentosRespuestas", resultado);

		} catch (Exception e) {

			logger.error("Error ws ", e);

			throw new Exception("Error updateIntentosRespuestas ", e);
		}
		
	}

	@Override
	public int numeroIntentos(HashMap<String, Long> params) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			@SuppressWarnings("unchecked")
			Integer intentos = (Integer)sqlMap.queryForObject("credito.numeroIntentos", params);
			if(intentos!=null){
				return intentos.intValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public List<Integer> preguntasActivas() throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			@SuppressWarnings("unchecked")
			List<Integer> queryForList = (List<Integer>)sqlMap.queryForList("credito.preguntasActivas", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Integer> preguntasPersonales() throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			@SuppressWarnings("unchecked")
			List<Integer> queryForList = (List<Integer>)sqlMap.queryForList("credito.preguntasPersonales", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

}
