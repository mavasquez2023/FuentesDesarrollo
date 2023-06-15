package cl.laaraucana.simat.comun.conx;

import cl.laaraucana.simat.dao.CE1_DAO;
import cl.laaraucana.simat.dao.CE2_DAO;
import cl.laaraucana.simat.dao.CE3_DAO;
import cl.laaraucana.simat.dao.CE4_DAO;
import cl.laaraucana.simat.dao.ControlDocuDAO;
import cl.laaraucana.simat.dao.Count_Lic_AutorizadasDAO;
import cl.laaraucana.simat.dao.Count_Subs_IniciadosDAO;
import cl.laaraucana.simat.dao.DatosLicCobDAO;
import cl.laaraucana.simat.dao.DatosLicResolDAO;
import cl.laaraucana.simat.dao.DocsRevalReemDAO;
import cl.laaraucana.simat.dao.HistoricoDAO;
import cl.laaraucana.simat.dao.InformeFinancieroDAO;
import cl.laaraucana.simat.dao.LogProcesosDAO;
import cl.laaraucana.simat.dao.PaseContableDAO;
import cl.laaraucana.simat.dao.PeriodoDAO;
import cl.laaraucana.simat.dao.ProcedimientosDAO;
import cl.laaraucana.simat.dao.QueryCantPers_SubsidiadasDAO;
import cl.laaraucana.simat.dao.QueryIdDAO;
import cl.laaraucana.simat.dao.ReintegrosDAO;
import cl.laaraucana.simat.dao.SMF09_DAO;
import cl.laaraucana.simat.dao.SubsParentalDAO;
import cl.laaraucana.simat.dao.SubsPrePostNmDAO;
import cl.laaraucana.simat.dao.SubsTscVigDAO;
import cl.laaraucana.simat.dao.Sum_Dias_Subs_PagadosDAO;
import cl.laaraucana.simat.dao.Sum_InformeFinancieroDAO;
import cl.laaraucana.simat.dao.Sum_Monto_Subs_PagadosDAO;
import cl.laaraucana.simat.dao.Sum_RCP_B1P1_DAO;
import cl.laaraucana.simat.dao.Sum_RCP_B1P2_DAO;
import cl.laaraucana.simat.dao.Sum_RCP_B2P3_DAO;
import cl.laaraucana.simat.dao.TablaHomologacionDAO;
import cl.laaraucana.simat.dao.UsuariosDAO;

public abstract class DAOFactory {
	public static final int DB2 = 1;
	public static final int ORACLE = 2;
	public static final int MYSQL = 3;

	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
		case DB2:
			return new DB2DAOFactory();
		case ORACLE:
			//			return new ORLDAOFactory();
			/*
			case MYSQL:
			return new MYSQLDAOFactory();*/
		default:
			return null;
		}
	}

	public abstract ReintegrosDAO getReintegrosDAO();

	public abstract SubsParentalDAO getSubsParentalDAO();

	public abstract SubsTscVigDAO getSubsTscVigDAO();

	public abstract ControlDocuDAO getControlDocuDAO();

	public abstract DocsRevalReemDAO getDocsRevalReemDAO();

	public abstract DatosLicCobDAO getDatosLicCobDAO();

	public abstract DatosLicResolDAO getDatosLicResolDAO();

	public abstract UsuariosDAO getUsuariosDAO();

	public abstract SubsPrePostNmDAO getSubsPrePostNmDAO();

	public abstract LogProcesosDAO getLogProcesosDAO();

	public abstract TablaHomologacionDAO getTablaHomologacionDAO();

	public abstract InformeFinancieroDAO getInformeFinancieroDAO();

	public abstract PeriodoDAO getPeriodoDAO();

	public abstract QueryIdDAO getMinMaxId();

	public abstract QueryCantPers_SubsidiadasDAO getQueryCantPers_SubsidiadasDAO();

	public abstract ProcedimientosDAO getProcedimientosDAO();

	public abstract Count_Lic_AutorizadasDAO getCount_Lic_AutorizadasDAO();

	public abstract Count_Subs_IniciadosDAO getCount_Subs_IniciadosDAO();

	public abstract Sum_Dias_Subs_PagadosDAO getSum_Dias_Subs_PagadosDAO();

	public abstract Sum_Monto_Subs_PagadosDAO getSum_Monto_Subs_PagadosDAO();

	public abstract CE1_DAO getCE1_DAO();

	public abstract CE2_DAO getCE2_DAO();

	public abstract CE3_DAO getCE3_DAO();

	public abstract CE4_DAO getCE4_DAO();

	public abstract SMF09_DAO getSMF09_DAO();

	public abstract HistoricoDAO getHistoricoDAO();

	public abstract Sum_InformeFinancieroDAO getSum_InformeFinancieroDAO();

	public abstract Sum_RCP_B1P1_DAO getSum_RCP_B1P1_DAO();

	public abstract Sum_RCP_B1P2_DAO getSum_RCP_B1P2_DAO();

	public abstract Sum_RCP_B2P3_DAO getSum_RCP_B2P3_DAO();
	
	public abstract PaseContableDAO getPaseContableDAO();
}
