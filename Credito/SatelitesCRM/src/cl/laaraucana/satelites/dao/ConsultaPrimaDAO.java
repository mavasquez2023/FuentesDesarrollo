package cl.laaraucana.satelites.dao;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.laaraucana.satelites.dao.VO.ConsultaPrimaVO;
import cl.laaraucana.satelites.dao.VO.RespPrimaVO;
import cl.laaraucana.satelites.ibatis.MyClassSqlConfig;

public class ConsultaPrimaDAO {


	public static double consultaPrima(ConsultaPrimaVO consulta) throws Exception {
		double prima = 0;
		SqlMapClient sqlMap = null;
		RespPrimaVO rprima = new RespPrimaVO();
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
		 rprima =  (RespPrimaVO) sqlMap.queryForObject("consultaPrima", consulta);		 
		 prima=rprima.getPrima();
		} catch (Exception e) {
			throw new Exception("Error al realizar la consulta prima");
		}
		
		return prima;
	}

	
/*	public static void main(String[] args) throws Exception {
		ConsultaPrimaVO consulta = new ConsultaPrimaVO();
		consulta.setOfipro(Integer.parseInt("041"));
		consulta.setCuonumDesde(26);
		consulta.setCuonumHasta(27);
		consulta.setCrefol(Integer.parseInt("000306417"));
		
		double resp = (Double) consultaPrima(consulta);
		
		System.out.println("consulta prima: " + resp);
		
	}*/
}
