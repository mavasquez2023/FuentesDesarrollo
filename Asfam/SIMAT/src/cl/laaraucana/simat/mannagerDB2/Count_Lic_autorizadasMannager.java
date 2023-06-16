package cl.laaraucana.simat.mannagerDB2;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.Count_Lic_AutorizadasDAO;
import cl.laaraucana.simat.entidades.CountVO;

public class Count_Lic_autorizadasMannager {

	public CountVO getNum_Lic_Autorizadas_Pre_natal(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			Count_Lic_AutorizadasDAO count_Lic_Autorizadas = df.getCount_Lic_AutorizadasDAO();
			countSQL = count_Lic_Autorizadas.getNum_Lic_Autorizadas_Pre_natal(countSQL);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getNum_Lic_Autorizadas_Post_natal_Madre(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			Count_Lic_AutorizadasDAO count_Lic_Autorizadas = df.getCount_Lic_AutorizadasDAO();
			countSQL = count_Lic_Autorizadas.getNum_Lic_Autorizadas_Post_natal_Madre(countSQL);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getNum_Lic_Autorizadas_Post_natal_Padre(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			Count_Lic_AutorizadasDAO count_Lic_Autorizadas = df.getCount_Lic_AutorizadasDAO();
			countSQL = count_Lic_Autorizadas.getNum_Lic_Autorizadas_Post_natal_Padre(countSQL);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	/*

	public CountVO num_pers_subs_parental_Madre(CountVO qid) throws Exception {
			try
			{			
				DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
				QueryCantPers_SubsidiadasDAO queryCantPers_SubsidiadasDAO=df.getQueryCantPers_SubsidiadasDAO();
				qid=queryCantPers_SubsidiadasDAO.num_pers_subs_parental_Madre(qid);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				return qid;
		}
		public CountVO num_pers_subs_parental_Padre(CountVO qid) throws Exception {
			try
			{			
				DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
				QueryCantPers_SubsidiadasDAO queryCantPers_SubsidiadasDAO=df.getQueryCantPers_SubsidiadasDAO();
				qid=queryCantPers_SubsidiadasDAO.num_pers_subs_parental_Padre(qid);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				return qid;
		}

		 * */

	public CountVO getNum_Lic_Autorizadas_Enf_menor_Madre(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			Count_Lic_AutorizadasDAO count_Lic_Autorizadas = df.getCount_Lic_AutorizadasDAO();
			countSQL = count_Lic_Autorizadas.getNum_Lic_Autorizadas_Enf_menor_Madre(countSQL);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

	public CountVO getNum_Lic_Autorizadas_Enf_menor_Padre(CountVO countSQL) throws Exception {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			Count_Lic_AutorizadasDAO count_Lic_Autorizadas = df.getCount_Lic_AutorizadasDAO();
			countSQL = count_Lic_Autorizadas.getNum_Lic_Autorizadas_Enf_menor_Padre(countSQL);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countSQL;
	}

}
