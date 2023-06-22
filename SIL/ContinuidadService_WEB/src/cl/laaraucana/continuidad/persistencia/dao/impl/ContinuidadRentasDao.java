package cl.laaraucana.continuidad.persistencia.dao.impl;

import java.util.HashMap;
import java.util.Map;

import cl.laaraucana.config.ibatis.DaoException;
import cl.laaraucana.config.ibatis.MyClassSqlConfig;
import cl.laaraucana.continuidad.persistencia.dao.ContinuidadRentasDaoI;
import cl.laaraucana.util.Util;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ContinuidadRentasDao implements ContinuidadRentasDaoI{
	
	private SqlMapClient sqlMap;
	
	public ContinuidadRentasDao(SqlMapClient sqlMap){
		this.sqlMap = sqlMap;
	}

	@SuppressWarnings({"unchecked","rawtypes"})
	@Override
	public String consultaContinuidadRentas(String rut, String cantidadRentas) throws DaoException {
		String entrada = "";
		String salida = "";
		try {
			rut = rut.replace("-", "");
			rut = Util.rellenar(rut, "10", "0", false);
			cantidadRentas = Util.rellenar(cantidadRentas, "3", "0", false);
			salida = Util.rellenar("", "847", "", false);
			
			entrada = rut + cantidadRentas;
			
			Map parametros = new HashMap();
			parametros.put("entrada", entrada);
			parametros.put("salida", salida);
			
			//Mapear Salida
			sqlMap.queryForObject("consultaContinuidadRentas", parametros);
			
			salida = (String) parametros.get("salida");
		} catch (Exception e) {
			throw new DaoException("Error al realizar consulta", e);
		}
		return salida;
		
	}
	
	public static void main(String[] args) throws DaoException {
		ContinuidadRentasDao dao = new ContinuidadRentasDao(MyClassSqlConfig.getInstance());
		
		dao.consultaContinuidadRentas("85489849", "3");
	}
}
