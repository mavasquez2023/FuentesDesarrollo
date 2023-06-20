package cl.laaraucana.transferencias.ibatis.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapClient;
import cl.laaraucana.transferencias.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.transferencias.ibatis.vo.BancoVo;
import cl.laaraucana.transferencias.ibatis.vo.MandatoAS400Vo;
import cl.laaraucana.transferencias.ibatis.vo.TipoCuentaVo;

@Repository
public class MandatoAS400DaoImpl implements MandatoAS400Dao {

	private static final Logger logger = Logger.getLogger(MandatoAS400DaoImpl.class);
	
	@Override
	public BancoVo findBanckById(int codigo) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			BancoVo queryForObject = (BancoVo) sqlMap.queryForObject("mandatos.consultaBancos", codigo);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
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
	

	@SuppressWarnings("unchecked")
	@Override
	public List<BancoVo> getBanco() throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<BancoVo> queryForList = sqlMap.queryForList("mandatos.consultaBancos", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoCuentaVo> getTipoCuenta() throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<TipoCuentaVo> queryForList = sqlMap.queryForList("mandatos.consultaTipocuenta", null);
			return queryForList;
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
	
	@Override
	public void insertMandato(int rut, MandatoAS400Vo mandatos) throws Exception {
		// TODO Auto-generated method stub
		SqlMapClient sqlMap = null;

		try {

			sqlMap = MyClassSqlConfig.getSqlMapInstance();

		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			sqlMap.startTransaction();

			@SuppressWarnings("unchecked")
			List<MandatoAS400Vo> lista = sqlMap.queryForList("mandatos.consultaMandato", rut);
			
			if (lista.size() > 0) {
				logger.info("Revocando Mandato vigente RUT:" + rut);
				sqlMap.delete("mandatos.deleteByRutCuenta", rut);
				
				for (MandatoAS400Vo mandatoAS400Vo : lista) {
					mandatoAS400Vo.setObservaciones("");
					mandatoAS400Vo.setEstado("0");
					sqlMap.insert("mandatos.insertMandatoRev", mandatoAS400Vo);
				}
			}
			List<MandatoAS400Vo> listarecha = sqlMap.queryForList("mandatos.consultaMandatoRechazado", rut);
			if(listarecha.size()>0){
				sqlMap.update("mandatos.updateMandatoRechazado", rut);
			}
			sqlMap.insert("mandatos.insertMandato", mandatos);

			sqlMap.commitTransaction();
			sqlMap.endTransaction();
		} catch (Exception e) {

			logger.error("Error ", e);
			sqlMap.endTransaction();
			throw new Exception();
		}
		
	}
	
	@Override
	public boolean deleteMandato(int rut) throws Exception {
		// TODO Auto-generated method stub
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}
		try {

			sqlMap.startTransaction();

			@SuppressWarnings("unchecked")
			List<MandatoAS400Vo> lista = sqlMap.queryForList("mandatos.consultaMandato", rut);
			
			if (lista.size() > 0) {
				
				sqlMap.delete("mandatos.deleteByRutCuenta", rut);
				
				for (MandatoAS400Vo mandatoAS400Vo : lista) {
					mandatoAS400Vo.setObservaciones("");
					mandatoAS400Vo.setEstado("0");
					sqlMap.insert("mandatos.insertMandatoRev", mandatoAS400Vo);
				}
			}

			sqlMap.commitTransaction();
			sqlMap.endTransaction();
			return true;
		} catch (Exception e) {

			logger.error("Error ", e);
			sqlMap.endTransaction();
			return false;
		}
	}
	
	@Override
	public boolean deleteMandato(HashMap<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}
		try {

			sqlMap.startTransaction();

			@SuppressWarnings("unchecked")
			List<MandatoAS400Vo> lista = sqlMap.queryForList("mandatos.consultaMandato", params.get("RUT").toString());
			
			if (lista.size() > 0) {
				
				sqlMap.delete("mandatos.deleteByRutCuenta", params.get("RUT").toString());
				
				for (MandatoAS400Vo mandatoAS400Vo : lista) {
					mandatoAS400Vo.setObservaciones(params.get("MENSAJE").toString());
					mandatoAS400Vo.setEstado("1");
					sqlMap.insert("mandatos.insertMandatoRev", mandatoAS400Vo);
				}
			}

			sqlMap.commitTransaction();
			sqlMap.endTransaction();
			return true;
		} catch (Exception e) {

			logger.error("Error ", e);
			sqlMap.endTransaction();
			return false;
		}
	}
	
	@Override
	public boolean deleteMandatoById(long id) throws Exception {
		// TODO Auto-generated method stub
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}
		try {

			sqlMap.startTransaction();

			@SuppressWarnings("unchecked")
			List<MandatoAS400Vo> lista = sqlMap.queryForList("mandatos.consultaMandatoById", id);
			
			if (lista.size() > 0) {
				
				sqlMap.delete("mandatos.deleteByIdMandato", id);
				
				for (MandatoAS400Vo mandatoAS400Vo : lista) {
					mandatoAS400Vo.setObservaciones("");
					mandatoAS400Vo.setEstado("0");
					sqlMap.insert("mandatos.insertMandatoRev", mandatoAS400Vo);
				}
			}

			sqlMap.commitTransaction();
			sqlMap.endTransaction();
			return true;
		} catch (Exception e) {

			logger.error("Error ", e);
			sqlMap.endTransaction();
			return false;
		}
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
	
	@Override
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
