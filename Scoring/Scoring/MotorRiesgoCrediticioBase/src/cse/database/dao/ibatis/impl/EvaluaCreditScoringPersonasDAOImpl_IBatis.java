package cse.database.dao.ibatis.impl;

import java.sql.SQLException;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;

import cse.database.dao.EvaluaCreditScoringPersonasDAO;


public class EvaluaCreditScoringPersonasDAOImpl_IBatis implements EvaluaCreditScoringPersonasDAO {
	
	private SqlMapClient sqlMapClient;

	public EvaluaCreditScoringPersonasDAOImpl_IBatis(SqlMapClient sqlMap) {
		super();
		this.sqlMapClient = sqlMap;
	}

	public int execute(String idSolicitud) throws SQLException {
		try {
			sqlMapClient.startTransaction();
			HashMap params = new HashMap();
			params.put("idSolicitud", idSolicitud);
			params.put("returnStatus", new Integer(99));
			Object casa = sqlMapClient.queryForObject("MotorCreditScoring_dbo_StoredProcedures.evaluaCreditScoringPersonas", params);
			sqlMapClient.commitTransaction();
			System.out.println(casa);
			Object object = params.get("returnStatus");
			if (object instanceof Integer){
				Integer dato = (Integer)object;
				return dato.intValue();			
			}	
			return -1;
		} finally{
			sqlMapClient.endTransaction();
		}
		
	}
}
