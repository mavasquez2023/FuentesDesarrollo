package cl.laaraucana.simat.mannagerDB2;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.SMF09_DAO;
import cl.laaraucana.simat.entidades.SMF09_VO;

public class SMF09_Mannager implements SMF09_DAO {
	public void delEstadoPeriodoByProceso(SMF09_VO smf09) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		SMF09_DAO smf09_DAO = df.getSMF09_DAO();
		smf09_DAO.delEstadoPeriodoByProceso(smf09);

	}

	public SMF09_VO getEstadoPeriodoByProceso(SMF09_VO smf09) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		SMF09_DAO smf09_DAO = df.getSMF09_DAO();
		smf09 = smf09_DAO.getEstadoPeriodoByProceso(smf09);
		return smf09;
	}

	public void setEstadoPeriodoByProceso(SMF09_VO smf09) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		SMF09_DAO smf09_DAO = df.getSMF09_DAO();
		smf09_DAO.setEstadoPeriodoByProceso(smf09);

	}

	public void upEstadoPeriodoByProceso(SMF09_VO smf09) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		SMF09_DAO smf09_DAO = df.getSMF09_DAO();
		smf09_DAO.upEstadoPeriodoByProceso(smf09);

	}
}