package ztest;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.jfactory.planillas.service.util.ConstantesUtiles;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;

public class TestIbatis {
	public static void main(String[] args) {

		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			sqlMap.queryForList("carga_SAP.mi_test");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
