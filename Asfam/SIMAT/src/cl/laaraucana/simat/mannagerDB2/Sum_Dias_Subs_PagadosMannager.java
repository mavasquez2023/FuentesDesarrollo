package cl.laaraucana.simat.mannagerDB2;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.Sum_Dias_Subs_PagadosDAO;
import cl.laaraucana.simat.entidades.CountVO;

public class Sum_Dias_Subs_PagadosMannager implements Sum_Dias_Subs_PagadosDAO {

	public CountVO getSum_Dias_Pagados_Pre_natal(CountVO countSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_Dias_Subs_PagadosDAO sum_Dias_Subs_PagadosDAO = df.getSum_Dias_Subs_PagadosDAO();
		countSQL = sum_Dias_Subs_PagadosDAO.getSum_Dias_Pagados_Pre_natal(countSQL);
		return countSQL;
	}

	public CountVO getSum_Dias_Pagados_Post_natal_Madre(CountVO countSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_Dias_Subs_PagadosDAO sum_Dias_Subs_PagadosDAO = df.getSum_Dias_Subs_PagadosDAO();
		countSQL = sum_Dias_Subs_PagadosDAO.getSum_Dias_Pagados_Post_natal_Madre(countSQL);
		return countSQL;
	}

	public CountVO getSum_Dias_Pagados_Post_natal_Padre(CountVO countSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_Dias_Subs_PagadosDAO sum_Dias_Subs_PagadosDAO = df.getSum_Dias_Subs_PagadosDAO();
		countSQL = sum_Dias_Subs_PagadosDAO.getSum_Dias_Pagados_Post_natal_Padre(countSQL);
		return countSQL;
	}

	public CountVO getSum_Dias_Pagados_Parental_Madre(CountVO countSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_Dias_Subs_PagadosDAO sum_Dias_Subs_PagadosDAO = df.getSum_Dias_Subs_PagadosDAO();
		countSQL = sum_Dias_Subs_PagadosDAO.getSum_Dias_Pagados_Parental_Madre(countSQL);
		return countSQL;
	}

	public CountVO getSum_Dias_Pagados_Parental_Padre(CountVO countSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_Dias_Subs_PagadosDAO sum_Dias_Subs_PagadosDAO = df.getSum_Dias_Subs_PagadosDAO();
		countSQL = sum_Dias_Subs_PagadosDAO.getSum_Dias_Pagados_Parental_Padre(countSQL);
		return countSQL;
	}

	public CountVO getSum_Dias_Pagados_Enf_menor_Madre(CountVO countSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_Dias_Subs_PagadosDAO sum_Dias_Subs_PagadosDAO = df.getSum_Dias_Subs_PagadosDAO();
		countSQL = sum_Dias_Subs_PagadosDAO.getSum_Dias_Pagados_Enf_menor_Madre(countSQL);
		return countSQL;
	}

	public CountVO getSum_Dias_Pagados_Enf_menor_Padre(CountVO countSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_Dias_Subs_PagadosDAO sum_Dias_Subs_PagadosDAO = df.getSum_Dias_Subs_PagadosDAO();
		countSQL = sum_Dias_Subs_PagadosDAO.getSum_Dias_Pagados_Enf_menor_Padre(countSQL);
		return countSQL;
	}

}
