package cl.laaraucana.simat.mannagerDB2;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.Sum_RCP_B1P2_DAO;
import cl.laaraucana.simat.entidades.SumVO;

public class Sum_RCP_B1P2_Mannager implements Sum_RCP_B1P2_DAO {

	public SumVO getSumB1P2_CCAF_EnfGraveNM(SumVO sumSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_RCP_B1P2_DAO sum_RCP_B1P2_DAO = df.getSum_RCP_B1P2_DAO();

		return sum_RCP_B1P2_DAO.getSumB1P2_CCAF_EnfGraveNM(sumSQL);
	}

	public SumVO getSumB1P2_CCAF_Parental(SumVO sumSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_RCP_B1P2_DAO sum_RCP_B1P2_DAO = df.getSum_RCP_B1P2_DAO();

		return sum_RCP_B1P2_DAO.getSumB1P2_CCAF_Parental(sumSQL);
	}

	public SumVO getSumB1P2_CCAF_postNatal(SumVO sumSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_RCP_B1P2_DAO sum_RCP_B1P2_DAO = df.getSum_RCP_B1P2_DAO();

		return sum_RCP_B1P2_DAO.getSumB1P2_CCAF_postNatal(sumSQL);
	}

	public SumVO getSumB1P2_CCAF_prenatal(SumVO sumSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_RCP_B1P2_DAO sum_RCP_B1P2_DAO = df.getSum_RCP_B1P2_DAO();

		return sum_RCP_B1P2_DAO.getSumB1P2_CCAF_prenatal(sumSQL);
	}

	public SumVO getSumB1P2_EntidadEmpleadora_EnfGraveNM(SumVO sumSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_RCP_B1P2_DAO sum_RCP_B1P2_DAO = df.getSum_RCP_B1P2_DAO();

		return sum_RCP_B1P2_DAO.getSumB1P2_EntidadEmpleadora_EnfGraveNM(sumSQL);
	}

	public SumVO getSumB1P2_EntidadEmpleadora_Parental(SumVO sumSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_RCP_B1P2_DAO sum_RCP_B1P2_DAO = df.getSum_RCP_B1P2_DAO();

		return sum_RCP_B1P2_DAO.getSumB1P2_EntidadEmpleadora_Parental(sumSQL);
	}

	public SumVO getSumB1P2_EntidadEmpleadora_postNatal(SumVO sumSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_RCP_B1P2_DAO sum_RCP_B1P2_DAO = df.getSum_RCP_B1P2_DAO();

		return sum_RCP_B1P2_DAO.getSumB1P2_EntidadEmpleadora_postNatal(sumSQL);
	}

	public SumVO getSumB1P2_EntidadEmpleadora_prenatal(SumVO sumSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_RCP_B1P2_DAO sum_RCP_B1P2_DAO = df.getSum_RCP_B1P2_DAO();

		return sum_RCP_B1P2_DAO.getSumB1P2_EntidadEmpleadora_prenatal(sumSQL);
	}

	public SumVO getSumB1P2_ISP_EnfGraveNM(SumVO sumSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_RCP_B1P2_DAO sum_RCP_B1P2_DAO = df.getSum_RCP_B1P2_DAO();

		return sum_RCP_B1P2_DAO.getSumB1P2_ISP_EnfGraveNM(sumSQL);
	}

	public SumVO getSumB1P2_ISP_Parental(SumVO sumSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_RCP_B1P2_DAO sum_RCP_B1P2_DAO = df.getSum_RCP_B1P2_DAO();

		return sum_RCP_B1P2_DAO.getSumB1P2_ISP_Parental(sumSQL);
	}

	public SumVO getSumB1P2_ISP_postNatal(SumVO sumSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_RCP_B1P2_DAO sum_RCP_B1P2_DAO = df.getSum_RCP_B1P2_DAO();

		return sum_RCP_B1P2_DAO.getSumB1P2_ISP_postNatal(sumSQL);
	}

	public SumVO getSumB1P2_ISP_prenatal(SumVO sumSQL) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		Sum_RCP_B1P2_DAO sum_RCP_B1P2_DAO = df.getSum_RCP_B1P2_DAO();

		return sum_RCP_B1P2_DAO.getSumB1P2_ISP_prenatal(sumSQL);
	}

}
