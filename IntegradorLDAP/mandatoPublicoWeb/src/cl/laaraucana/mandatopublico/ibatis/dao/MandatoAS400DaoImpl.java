package cl.laaraucana.mandatopublico.ibatis.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapClient;

import cl.laaraucana.mandatopublico.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.mandatopublico.ibatis.vo.BancoVo;
import cl.laaraucana.mandatopublico.ibatis.vo.MandatoAS400Vo;
import cl.laaraucana.mandatopublico.ibatis.vo.TipoCuentaVo;


@Repository
public class MandatoAS400DaoImpl implements MandatoAS400Dao {

	private static final Logger logger = Logger.getLogger(MandatoAS400DaoImpl.class);
	
	@Override
	public TipoCuentaVo findAccountkById(int codigo) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			TipoCuentaVo queryForObject = (TipoCuentaVo) sqlMap.queryForObject("mandatos.gettipocuentabycode", codigo);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public BancoVo findBancoById(int codigo) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			BancoVo queryForObject = (BancoVo) sqlMap.queryForObject("mandatos.consultaBancosbycodigo", codigo);
			return queryForObject;
		} catch (Exception e) {

			logger.error("Error ", e);
		}
		return null;
	}

	
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

	@SuppressWarnings("unchecked")
	public List<MandatoAS400Vo> consultaMandatosDia() throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<MandatoAS400Vo> queryForList = sqlMap.queryForList("mandatos.consultaMandatosDia", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	
	

	@Override
	public List<MandatoAS400Vo> consultaMandatosRev(int rut) throws Exception {
		// TODO Auto-generated method stub
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			@SuppressWarnings("unchecked")
			List<MandatoAS400Vo> queryForList = sqlMap.queryForList("mandatos.consultaMandatoRev", rut);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public List<MandatoAS400Vo> consultaMandatosRechazados(int rut) throws Exception {
		// TODO Auto-generated method stub
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			@SuppressWarnings("unchecked")
			List<MandatoAS400Vo> queryForList = sqlMap.queryForList("mandatos.consultaMandatoRechazado", rut);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public MandatoAS400Vo consultaMandatosById(long id) throws Exception {
		// TODO Auto-generated method stub
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			MandatoAS400Vo query = (MandatoAS400Vo) sqlMap.queryForObject("mandatos.consultaMandatoById", id);
			return query;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;

	}

	@Override
	public MandatoAS400Vo consultaMandatosRevById(long id) throws Exception {
		// TODO Auto-generated method stub
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			MandatoAS400Vo query = (MandatoAS400Vo) sqlMap.queryForObject("mandatos.consultaMandatoRevById", id);
			return query;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}	
	
	public static synchronized long getNextId() throws Exception {
		// TODO Auto-generated method stub
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			Integer id = (Integer) sqlMap.queryForObject("mandatos.consultaMandatoyGetId", null);
			sqlMap.insert("mandatos.insertIdMandato", null);
			return id.intValue() + 1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return 0;
	}

}
