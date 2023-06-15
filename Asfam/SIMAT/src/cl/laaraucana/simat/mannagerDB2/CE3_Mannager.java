package cl.laaraucana.simat.mannagerDB2;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.CE3_DAO;
import cl.laaraucana.simat.entidades.CountVO;

public class CE3_Mannager implements CE3_DAO {

	public CountVO getCE3_Sum_Dias_Pagados_Enf_menor(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE3_DAO ce3 = df.getCE3_DAO();
			countSQL = ce3.getCE3_Sum_Dias_Pagados_Enf_menor(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE3_Sum_Dias_Pagados_Parental(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE3_DAO ce3 = df.getCE3_DAO();
			countSQL = ce3.getCE3_Sum_Dias_Pagados_Parental(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE3_Sum_Dias_Pagados_Post_natal(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE3_DAO ce3 = df.getCE3_DAO();
			countSQL = ce3.getCE3_Sum_Dias_Pagados_Post_natal(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE3_Sum_Dias_Pagados_Pre_natal(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE3_DAO ce3 = df.getCE3_DAO();
			countSQL = ce3.getCE3_Sum_Dias_Pagados_Pre_natal(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

}
