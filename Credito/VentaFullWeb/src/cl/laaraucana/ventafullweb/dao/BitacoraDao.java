package cl.laaraucana.ventafullweb.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.laaraucana.ventafullweb.dao.config.SqlMapLocator;
import cl.laaraucana.ventafullweb.dto.BitacoraEvaluadorAISDto;
import cl.laaraucana.ventafullweb.dto.BitacoraGenesysDto;
import cl.laaraucana.ventafullweb.dto.BitacoraSeguimientoDto;
import cl.laaraucana.ventafullweb.dto.BitacoraSimulacionDto;

public class BitacoraDao {


	public void insertBitacoraSimulacion(BitacoraSimulacionDto data) throws SQLException{
		SqlMapClient sqlMap = SqlMapLocator.getInstance();
		sqlMap.insert("bitacora.insertBitacoraSimulacion", data);

	}
	
	public void insertBitacoraGenesys(BitacoraGenesysDto data) throws SQLException{
		SqlMapClient sqlMap = SqlMapLocator.getInstance();
		sqlMap.insert("bitacora.insertBitacoraGenesys", data);

	}
	
	public void updateBitacoraGenesys(BitacoraGenesysDto data) throws SQLException{
		SqlMapClient sqlMap = SqlMapLocator.getInstance();
		sqlMap.insert("bitacora.updateBitacoraGenesys", data);

	}
	
	public void insertBitacoraAIS(BitacoraEvaluadorAISDto data) throws SQLException{
		SqlMapClient sqlMap = SqlMapLocator.getInstance();
		sqlMap.insert("bitacora.insertBitacoraAIS", data);

	}
	
	public void insertBitacoraSeguimiento(BitacoraSeguimientoDto data) throws SQLException{
		SqlMapClient sqlMap = SqlMapLocator.getInstance();
		sqlMap.insert("bitacora.insertBitacoraSeguimiento", data);

	}
}
