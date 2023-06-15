package cl.laaraucana.simat.mannagerDB2;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.Count_Subs_IniciadosDAO;
import cl.laaraucana.simat.entidades.CountVO;

public class Count_Subs_IniciadosMannager implements Count_Subs_IniciadosDAO {

	public CountVO getNum_subs_Iniciados_Pre_natal(CountVO countSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Count_Subs_IniciadosDAO count_Subs_IniciadosDAO = df.getCount_Subs_IniciadosDAO();
		countSQL = count_Subs_IniciadosDAO.getNum_subs_Iniciados_Pre_natal(countSQL);
		return countSQL;
	}

	public CountVO getNum_subs_Iniciados_Post_natal_Madre(CountVO countSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Count_Subs_IniciadosDAO count_Subs_IniciadosDAO = df.getCount_Subs_IniciadosDAO();
		countSQL = count_Subs_IniciadosDAO.getNum_subs_Iniciados_Post_natal_Madre(countSQL);
		return countSQL;
	}

	public CountVO getNum_subs_Iniciados_Post_natal_Padre(CountVO countSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Count_Subs_IniciadosDAO count_Subs_IniciadosDAO = df.getCount_Subs_IniciadosDAO();
		countSQL = count_Subs_IniciadosDAO.getNum_subs_Iniciados_Post_natal_Padre(countSQL);
		return countSQL;
	}

	public CountVO getNum_subs_Iniciados_Parental_Madre(CountVO countSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Count_Subs_IniciadosDAO count_Subs_IniciadosDAO = df.getCount_Subs_IniciadosDAO();
		countSQL = count_Subs_IniciadosDAO.getNum_subs_Iniciados_Parental_Madre(countSQL);
		return countSQL;
	}

	public CountVO getNum_subs_Iniciados_Parental_Padre(CountVO countSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Count_Subs_IniciadosDAO count_Subs_IniciadosDAO = df.getCount_Subs_IniciadosDAO();
		countSQL = count_Subs_IniciadosDAO.getNum_subs_Iniciados_Parental_Padre(countSQL);
		return countSQL;
	}

	public CountVO getNum_subs_Iniciados_Enf_menor_Madre(CountVO countSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Count_Subs_IniciadosDAO count_Subs_IniciadosDAO = df.getCount_Subs_IniciadosDAO();
		countSQL = count_Subs_IniciadosDAO.getNum_subs_Iniciados_Enf_menor_Madre(countSQL);
		return countSQL;
	}

	public CountVO getNum_subs_Iniciados_Enf_menor_Padre(CountVO countSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Count_Subs_IniciadosDAO count_Subs_IniciadosDAO = df.getCount_Subs_IniciadosDAO();
		countSQL = count_Subs_IniciadosDAO.getNum_subs_Iniciados_Enf_menor_Padre(countSQL);
		return countSQL;
	}

}
