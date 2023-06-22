package cl.laaraucana.simulacion.ibatis.dao;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.laaraucana.simulacion.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.simulacion.ibatis.vo.SolicitudCotizacionVO;

public class RegistrarSolicitudDAO {
	
	public static void registrarSolicitudCotizacion(SolicitudCotizacionVO solicitud) throws Exception{

		SqlMapClient sqlMap = null;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		}catch(Exception e){
			throw new Exception("Error al conectarse al datasource");
		}
		try{
			sqlMap.insert("registrarSolicitudCotizacion", solicitud);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al registrar la solicitud de cotizacion");
		}
	}
	

}
