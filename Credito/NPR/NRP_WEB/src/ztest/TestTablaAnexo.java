package ztest;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.jfactory.planillas.negocio.post.carga.PostCargaSINACAFF;
import cl.jfactory.planillas.service.util.ConstantesUtiles;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.comun.utiles.UtilesComunes;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;

public class TestTablaAnexo {
	public static void main(String[] args) throws SQLException {

		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		UtilesComunes.variablesEstaticas.put("sys.YearMonth", "201811");
		new PostCargaSINACAFF().prepararTablaAnexos(sqlMap, new MiHashMap());
		
	}
}
