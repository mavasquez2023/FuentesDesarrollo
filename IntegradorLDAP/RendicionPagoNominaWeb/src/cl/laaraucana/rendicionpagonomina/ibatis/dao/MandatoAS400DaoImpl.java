package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cl.laaraucana.rendicionpagonomina.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.MandatoAS400Vo;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.RechazoVo;

import com.ibatis.sqlmap.client.SqlMapClient;


@Repository
public class MandatoAS400DaoImpl implements MandatoAS400Dao {

	private static final Logger logger = Logger.getLogger(MandatoAS400DaoImpl.class);
	

	@SuppressWarnings("unchecked")
	public List<MandatoAS400Vo> consultaMandatos(int rut) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<MandatoAS400Vo> queryForList = sqlMap.queryForList("mandatos.consultaMandato", rut);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public void insertMandatoRechazado(RechazoVo rechazo) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		sqlMap.insert("mandatos.insertMandatoRechazado", rechazo);
		
	}

	@Override
	public int deleteMandatoRechazado(long rutafi) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		return sqlMap.delete("mandatos.deleteMandatoRechazado", rutafi);
		
	}
	
}
