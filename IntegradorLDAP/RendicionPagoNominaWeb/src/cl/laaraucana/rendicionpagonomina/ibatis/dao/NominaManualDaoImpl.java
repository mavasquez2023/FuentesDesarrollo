package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.List;


import org.apache.log4j.Logger;

import cl.laaraucana.rendicionpagonomina.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ConvenioEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.DetalleCargaPagoManualVo;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ResumenCargaPagoManualVo;

import com.ibatis.sqlmap.client.SqlMapClient;



public class NominaManualDaoImpl implements NominaManualDao {
	
	private static final Logger logger = Logger.getLogger(NominaManualDaoImpl.class);
	
	@Override
	public void insertCabecera(ResumenCargaPagoManualVo cabecera)
			throws Exception {
		
			SqlMapClient sqlMap = null;

			try {

				sqlMap = MyClassSqlConfig.getSqlMapInstance();

			} catch (Exception e) {
				throw new Exception("Error al conectar al Datasource");
			}

			try {

				sqlMap.startTransaction();

				sqlMap.insert("mandatos.insertMandatoRev", cabecera);
				
				for(DetalleCargaPagoManualVo detalle : cabecera.getListaNomina()){
					sqlMap.insert("mandatos.insertMandatoRev", detalle);
				}

				sqlMap.commitTransaction();
				sqlMap.endTransaction();
			} catch (Exception e) {

				logger.error("Error ", e);
				sqlMap.endTransaction();
				throw new Exception();
			}
		
	}

	@Override
	public void insertDetalle(DetalleCargaPagoManualVo detalle)
			throws Exception {
		// TODO Auto-generated method stub
		
	}




}
