package cl.laaraucana.simat.mannagerDB2;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.CE1_DAO;
import cl.laaraucana.simat.entidades.CountVO;

public class CE1_Mannager implements CE1_DAO {

	public CountVO getCE1_Num_Dias_Lic_Autorizados_Enf_menor(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE1_DAO ce1 = df.getCE1_DAO();
			countSQL = ce1.getCE1_Num_Dias_Lic_Autorizados_Enf_menor(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE1_Num_Dias_Lic_Autorizados_Post_natal(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE1_DAO ce1 = df.getCE1_DAO();
			countSQL = ce1.getCE1_Num_Dias_Lic_Autorizados_Post_natal(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE1_Num_Dias_Lic_Autorizados_Pre_natal(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE1_DAO ce1 = df.getCE1_DAO();
			countSQL = ce1.getCE1_Num_Dias_Lic_Autorizados_Pre_natal(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE1_Num_Dias_Lic_Rechazados_Enf_menor(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE1_DAO ce1 = df.getCE1_DAO();
			countSQL = ce1.getCE1_Num_Dias_Lic_Rechazados_Enf_menor(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE1_Num_Dias_Lic_Rechazados_Post_natal(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE1_DAO ce1 = df.getCE1_DAO();
			countSQL = ce1.getCE1_Num_Dias_Lic_Rechazados_Post_natal(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE1_Num_Dias_Lic_Rechazados_Pre_natal(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE1_DAO ce1 = df.getCE1_DAO();
			countSQL = ce1.getCE1_Num_Dias_Lic_Rechazados_Pre_natal(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE1_Num_Dias_Lic_Reconsiderados_Enf_menor(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE1_DAO ce1 = df.getCE1_DAO();
			countSQL = ce1.getCE1_Num_Dias_Lic_Reconsiderados_Enf_menor(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE1_Num_Dias_Lic_Reconsiderados_Post_natal(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE1_DAO ce1 = df.getCE1_DAO();
			countSQL = ce1.getCE1_Num_Dias_Lic_Reconsiderados_Post_natal(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE1_Num_Dias_Lic_Reconsiderados_Pre_natal(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE1_DAO ce1 = df.getCE1_DAO();
			countSQL = ce1.getCE1_Num_Dias_Lic_Reconsiderados_Pre_natal(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE1_Num_Lic_Aut_Modificadas_Enf_menor(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE1_DAO ce1 = df.getCE1_DAO();
			countSQL = ce1.getCE1_Num_Lic_Aut_Modificadas_Enf_menor(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE1_Num_Lic_Aut_Modificadas_Post_natal(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE1_DAO ce1 = df.getCE1_DAO();
			countSQL = ce1.getCE1_Num_Lic_Aut_Modificadas_Post_natal(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE1_Num_Lic_Aut_Modificadas_Pre_natal(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE1_DAO ce1 = df.getCE1_DAO();
			countSQL = ce1.getCE1_Num_Lic_Aut_Modificadas_Pre_natal(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE1_Num_Lic_Aut_Reconsideradas_Post_natal(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE1_DAO ce1 = df.getCE1_DAO();
			countSQL = ce1.getCE1_Num_Lic_Aut_Reconsideradas_Post_natal(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE1_Num_Lic_Aut_Reconsideradas_Pre_natal(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE1_DAO ce1 = df.getCE1_DAO();
			countSQL = ce1.getCE1_Num_Lic_Aut_Reconsideradas_Pre_natal(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE1_Num_Lic_Aut_SinModificar_Enf_menor(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE1_DAO ce1 = df.getCE1_DAO();
			countSQL = ce1.getCE1_Num_Lic_Aut_SinModificar_Enf_menor(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE1_Num_Lic_Aut_SinModificar_Post_natal(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE1_DAO ce1 = df.getCE1_DAO();
			countSQL = ce1.getCE1_Num_Lic_Aut_SinModificar_Post_natal(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE1_Num_Lic_Aut_SinModificar_Pre_natal(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE1_DAO ce1 = df.getCE1_DAO();
			countSQL = ce1.getCE1_Num_Lic_Aut_SinModificar_Pre_natal(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE1_Num_Lic_Rechazadas_Enf_menor(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE1_DAO ce1 = df.getCE1_DAO();
			countSQL = ce1.getCE1_Num_Lic_Rechazadas_Enf_menor(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE1_Num_Lic_Rechazadas_Post_natal(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE1_DAO ce1 = df.getCE1_DAO();
			countSQL = ce1.getCE1_Num_Lic_Rechazadas_Post_natal(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE1_Num_Lic_Rechazadas_Pre_natal(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE1_DAO ce1 = df.getCE1_DAO();
			countSQL = ce1.getCE1_Num_Lic_Rechazadas_Pre_natal(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE1_Num_Lic_Reconsideradas_Enf_menor(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE1_DAO ce1 = df.getCE1_DAO();
			countSQL = ce1.getCE1_Num_Lic_Reconsideradas_Enf_menor(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

}
