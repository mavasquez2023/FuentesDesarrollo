package cl.jfactory.planillas.negocio.post.carga;

import java.sql.SQLException;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.jfactory.planillas.service.util.ConstantesUtiles;
import cl.liv.cargas.masivas.util.UtilLogCargas;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;

public class PostCargaSAPAnexo implements IPostCarga{
	
	public static SqlMapClient sqlMap = null;
	public static SqlMapClient getSqlMap(){
		if(sqlMap == null){
			sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		}
		return sqlMap;
	}
	
	public boolean execute(final HashMap session,HashMap parametros, HashMap configs) {
		SqlMapClient sqlMap = getSqlMap();
		try {
			Object retorno = sqlMap.insert("carga_SAP.update_holding_anexos",null);
			UtilLogCargas.debug("retono update_holding_anexos "+ retorno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
	}


}
