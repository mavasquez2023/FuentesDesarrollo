package cl.laaraucana.simat.mannagerDB2;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.HistoricoDAO;
import cl.laaraucana.simat.entidades.Control_Historico_VO;
import cl.laaraucana.simat.entidades.CountVO;

public class HistoricoMannager implements HistoricoDAO {

	public CountVO getCountPeriodos(String tabla) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		HistoricoDAO count_HistoricoDAO = df.getHistoricoDAO();

		return (count_HistoricoDAO.getCountPeriodos(tabla));
	}

	public Control_Historico_VO getMenorPeriodo(String tabla) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		HistoricoDAO count_HistoricoDAO = df.getHistoricoDAO();

		return (count_HistoricoDAO.getMenorPeriodo(tabla));
	}

	public void delPeriodo(String tabla, Control_Historico_VO chVO) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		HistoricoDAO count_HistoricoDAO = df.getHistoricoDAO();

		count_HistoricoDAO.delPeriodo(tabla, chVO);

	}

}
