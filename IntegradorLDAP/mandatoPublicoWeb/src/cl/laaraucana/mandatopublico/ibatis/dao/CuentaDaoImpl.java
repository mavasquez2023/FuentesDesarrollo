package cl.laaraucana.mandatopublico.ibatis.dao;


import org.apache.log4j.Logger;
import com.ibatis.sqlmap.client.SqlMapClient;
import cl.laaraucana.mandatopublico.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.mandatopublico.ibatis.vo.RegMandatoPublicoVo;


public class CuentaDaoImpl implements CuentaDao {

	private static final Logger logger = Logger.getLogger(CuentaDaoImpl.class);


	@Override
	public void insertMandatoPublico(RegMandatoPublicoVo reg) throws Exception {
		
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			sqlMap.insert("cuentas.registroMandato", reg);

		} catch (Exception e) {
			 
			logger.error("error ", e);
			
			throw new Exception("Error inset ", e);
		}
		
	}



}
