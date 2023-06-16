package cl.laaraucana.simat.mannagerDB2;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.CE2_DAO;
import cl.laaraucana.simat.entidades.CountVO;

public class CE2_Mannager implements CE2_DAO {

	public CountVO getCE2_Num_subs_Iniciados_Enf_menor(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE2_DAO ce2 = df.getCE2_DAO();
			countSQL = ce2.getCE2_Num_subs_Iniciados_Enf_menor(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE2_Num_subs_Iniciados_Parental(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE2_DAO ce2 = df.getCE2_DAO();
			countSQL = ce2.getCE2_Num_subs_Iniciados_Parental(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE2_Num_subs_Iniciados_Post_natal(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE2_DAO ce2 = df.getCE2_DAO();
			countSQL = ce2.getCE2_Num_subs_Iniciados_Post_natal(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE2_Num_subs_Iniciados_Pre_natal(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE2_DAO ce2 = df.getCE2_DAO();
			countSQL = ce2.getCE2_Num_subs_Iniciados_Pre_natal(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

}
