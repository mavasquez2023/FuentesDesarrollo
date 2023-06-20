package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.HashMap;
import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cl.laaraucana.rendicionpagonomina.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ProductoEntity;

import com.ibatis.sqlmap.client.SqlMapClient;


public class ProductoDaoImpl implements ProductoDao {

	private static final Logger logger = Logger.getLogger(ProductoDaoImpl.class);


	@Override
	public List<ProductoEntity> consultaProductos() throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<ProductoEntity> queryForList = sqlMap.queryForList("transferencia.consultaProductos", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<ProductoEntity> consultaProductosByConvenioCargaManual(int convenio) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<ProductoEntity> queryForList = sqlMap.queryForList("transferencia.consultaProductosByConvenioCargaManual", convenio);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public HashMap<String, Object> consultaProducto(Integer idConvenio, String idProducto) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {
			HashMap<String, String> params= new HashMap<String, String>();
			params.put("IdCodConv", String.valueOf(idConvenio));
			params.put("idProducto", idProducto);
			HashMap<String, Object> producto = (HashMap<String, Object>)sqlMap.queryForObject("transferencia.consultaProductosByConvenio", params);
			return producto;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<HashMap<String, Object>> consultaProductosByConvenioAndCargaManual(Integer idConvenio, String cargaManual) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {
			HashMap<String, Object> pars = new HashMap<String, Object>();
			pars.put("idConvenio",idConvenio);
			pars.put("cargaManual",cargaManual);
			List<HashMap<String, Object>> queryForList = sqlMap.queryForList("transferencia.consultaProductosByConvenioAndCargaManual", pars);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public HashMap<String, Object> consultaProducto(Integer idConvenio)
			throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {
			HashMap<String, Object> producto = (HashMap<String, Object>)sqlMap.queryForObject("transferencia.consultaProductosByIdConvenio", idConvenio);
			return producto;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}


}
