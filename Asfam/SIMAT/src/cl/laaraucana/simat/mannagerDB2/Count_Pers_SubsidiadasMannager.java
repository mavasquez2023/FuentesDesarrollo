package cl.laaraucana.simat.mannagerDB2;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.QueryCantPers_SubsidiadasDAO;
import cl.laaraucana.simat.entidades.CountVO;

public class Count_Pers_SubsidiadasMannager {

	public CountVO num_pers_subs_Pre_natal(CountVO qid) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			QueryCantPers_SubsidiadasDAO queryCantPers_SubsidiadasDAO = df.getQueryCantPers_SubsidiadasDAO();
			qid = queryCantPers_SubsidiadasDAO.num_pers_subs_Pre_natal(qid);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO num_pers_subs_Post_natal_Madre(CountVO qid) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			QueryCantPers_SubsidiadasDAO queryCantPers_SubsidiadasDAO = df.getQueryCantPers_SubsidiadasDAO();
			qid = queryCantPers_SubsidiadasDAO.num_pers_subs_Post_natal_Madre(qid);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO num_pers_subs_Post_natal_Padre(CountVO qid) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			QueryCantPers_SubsidiadasDAO queryCantPers_SubsidiadasDAO = df.getQueryCantPers_SubsidiadasDAO();
			qid = queryCantPers_SubsidiadasDAO.num_pers_subs_Post_natal_Padre(qid);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO num_pers_subs_parental_Madre(CountVO qid) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			QueryCantPers_SubsidiadasDAO queryCantPers_SubsidiadasDAO = df.getQueryCantPers_SubsidiadasDAO();
			qid = queryCantPers_SubsidiadasDAO.num_pers_subs_parental_Madre(qid);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO num_pers_subs_parental_Padre(CountVO qid) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			QueryCantPers_SubsidiadasDAO queryCantPers_SubsidiadasDAO = df.getQueryCantPers_SubsidiadasDAO();
			qid = queryCantPers_SubsidiadasDAO.num_pers_subs_parental_Padre(qid);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO num_pers_subs_Enf_menor_Madre(CountVO qid) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			QueryCantPers_SubsidiadasDAO queryCantPers_SubsidiadasDAO = df.getQueryCantPers_SubsidiadasDAO();
			qid = queryCantPers_SubsidiadasDAO.num_pers_subs_Enf_menor_Madre(qid);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return qid;
	}

	public CountVO num_pers_subs_Enf_menor_Padre(CountVO qid) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			QueryCantPers_SubsidiadasDAO queryCantPers_SubsidiadasDAO = df.getQueryCantPers_SubsidiadasDAO();
			qid = queryCantPers_SubsidiadasDAO.num_pers_subs_Enf_menor_Padre(qid);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return qid;
	}

}
