package cl.laaraucana.simat.mannagerDB2;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.CE4_DAO;
import cl.laaraucana.simat.entidades.CountVO;

public class CE4_Mannager implements CE4_DAO {

	public CountVO getCE4_NumSubs_JornadaCompleta_Iniciados_Parental(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE4_DAO ce4 = df.getCE4_DAO();
			countSQL = ce4.getCE4_NumSubs_JornadaCompleta_Iniciados_Parental(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE4_NumSubs_JornadaCompleta_Pagados_Parental(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE4_DAO ce4 = df.getCE4_DAO();
			countSQL = ce4.getCE4_NumSubs_JornadaCompleta_Pagados_Parental(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE4_NumSubs_JornadaCompleta_Traspasados_Parental(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE4_DAO ce4 = df.getCE4_DAO();
			countSQL = ce4.getCE4_NumSubs_JornadaCompleta_Traspasados_Parental(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE4_NumSubs_JornadaParcial_Iniciados_Parental(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE4_DAO ce4 = df.getCE4_DAO();
			countSQL = ce4.getCE4_NumSubs_JornadaParcial_Iniciados_Parental(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE4_NumSubs_JornadaParcial_Pagados_Parental(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE4_DAO ce4 = df.getCE4_DAO();
			countSQL = ce4.getCE4_NumSubs_JornadaParcial_Pagados_Parental(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getCE4_NumSubs_JornadaParcial_Traspasados_Parental(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			CE4_DAO ce4 = df.getCE4_DAO();
			countSQL = ce4.getCE4_NumSubs_JornadaParcial_Traspasados_Parental(countSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

}
