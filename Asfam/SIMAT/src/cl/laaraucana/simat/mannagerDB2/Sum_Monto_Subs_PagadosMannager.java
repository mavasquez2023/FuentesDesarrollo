package cl.laaraucana.simat.mannagerDB2;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.Sum_Monto_Subs_PagadosDAO;
import cl.laaraucana.simat.entidades.SumVO;

public class Sum_Monto_Subs_PagadosMannager implements Sum_Monto_Subs_PagadosDAO {

	public SumVO getSum_Monto_SubsPagado_Enf_menor_Madre(SumVO countSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_Monto_Subs_PagadosDAO sum_Monto_Subs_PagadosDAO = df.getSum_Monto_Subs_PagadosDAO();
		countSQL = sum_Monto_Subs_PagadosDAO.getSum_Monto_SubsPagado_Enf_menor_Madre(countSQL);
		return countSQL;
	}

	public SumVO getSum_Monto_SubsPagado_Enf_menor_Padre(SumVO countSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_Monto_Subs_PagadosDAO sum_Monto_Subs_PagadosDAO = df.getSum_Monto_Subs_PagadosDAO();
		countSQL = sum_Monto_Subs_PagadosDAO.getSum_Monto_SubsPagado_Enf_menor_Padre(countSQL);
		return countSQL;
	}

	public SumVO getSum_Monto_SubsPagado_Parental_Madre(SumVO countSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_Monto_Subs_PagadosDAO sum_Monto_Subs_PagadosDAO = df.getSum_Monto_Subs_PagadosDAO();
		countSQL = sum_Monto_Subs_PagadosDAO.getSum_Monto_SubsPagado_Parental_Madre(countSQL);
		return countSQL;
	}

	public SumVO getSum_Monto_SubsPagado_Parental_Padre(SumVO countSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_Monto_Subs_PagadosDAO sum_Monto_Subs_PagadosDAO = df.getSum_Monto_Subs_PagadosDAO();
		countSQL = sum_Monto_Subs_PagadosDAO.getSum_Monto_SubsPagado_Parental_Padre(countSQL);
		return countSQL;
	}

	public SumVO getSum_Monto_SubsPagado_Post_natal_Madre(SumVO countSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_Monto_Subs_PagadosDAO sum_Monto_Subs_PagadosDAO = df.getSum_Monto_Subs_PagadosDAO();
		countSQL = sum_Monto_Subs_PagadosDAO.getSum_Monto_SubsPagado_Post_natal_Madre(countSQL);
		return countSQL;
	}

	public SumVO getSum_Monto_SubsPagado_Post_natal_Padre(SumVO countSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_Monto_Subs_PagadosDAO sum_Monto_Subs_PagadosDAO = df.getSum_Monto_Subs_PagadosDAO();
		countSQL = sum_Monto_Subs_PagadosDAO.getSum_Monto_SubsPagado_Post_natal_Padre(countSQL);
		return countSQL;
	}

	public SumVO getSum_Monto_SubsPagado_Pre_natal(SumVO countSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_Monto_Subs_PagadosDAO sum_Monto_Subs_PagadosDAO = df.getSum_Monto_Subs_PagadosDAO();
		countSQL = sum_Monto_Subs_PagadosDAO.getSum_Monto_SubsPagado_Pre_natal(countSQL);
		return countSQL;
	}

}
