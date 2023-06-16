package cl.laaraucana.simat.comun.conx;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

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
import cl.laaraucana.simat.dao.db2.DB2ControlDocuDAO;
import cl.laaraucana.simat.dao.db2.DB2Count_Lic_autorizadasDAO;
import cl.laaraucana.simat.dao.db2.DB2Count_Subs_iniciadosDAO;
import cl.laaraucana.simat.dao.db2.DB2DatosLicCobDAO;
import cl.laaraucana.simat.dao.db2.DB2DatosLicResolDAO;
import cl.laaraucana.simat.dao.db2.DB2DocsRevalReemDAO;
import cl.laaraucana.simat.dao.db2.DB2InformeFinancieroDAO;
import cl.laaraucana.simat.dao.db2.DB2LogProcesosDAO;
import cl.laaraucana.simat.dao.db2.DB2PaseContableDAO;
import cl.laaraucana.simat.dao.db2.DB2PeriodoDAO;
import cl.laaraucana.simat.dao.db2.DB2ProcedimientoDAO;
import cl.laaraucana.simat.dao.db2.DB2QueryCantPers_SubsidiadasDAO;
import cl.laaraucana.simat.dao.db2.DB2QueryIdDAO;
import cl.laaraucana.simat.dao.db2.DB2ReintegrosDAO;
import cl.laaraucana.simat.dao.db2.DB2SubsParentalDAO;
import cl.laaraucana.simat.dao.db2.DB2SubsPrePostNmDAO;
import cl.laaraucana.simat.dao.db2.DB2SubsTscVigDAO;
import cl.laaraucana.simat.dao.db2.DB2Sum_Dias_Subs_PagadosDAO;
import cl.laaraucana.simat.dao.db2.DB2Sum_InformeFinancieroDAO;
import cl.laaraucana.simat.dao.db2.DB2Sum_Monto_Subs_PagadosDAO;
import cl.laaraucana.simat.dao.db2.DB2Sum_RCP_B1P1_DAO;
import cl.laaraucana.simat.dao.db2.DB2Sum_RCP_B1P2_DAO;
import cl.laaraucana.simat.dao.db2.DB2Sum_RCP_B2P3_DAO;
import cl.laaraucana.simat.dao.db2.DB2TablaHomologacionDAO;
import cl.laaraucana.simat.dao.db2.DB2UsuariosDAO;
import cl.laaraucana.simat.dao.db2.DB2_CE1_DAO;
import cl.laaraucana.simat.dao.db2.DB2_CE2_DAO;
import cl.laaraucana.simat.dao.db2.DB2_CE3_DAO;
import cl.laaraucana.simat.dao.db2.DB2_CE4_DAO;
import cl.laaraucana.simat.dao.db2.DB2_HistoricoDAO;
import cl.laaraucana.simat.dao.db2.DB2_SMF09_DAO;

public class DB2DAOFactory extends DAOFactory {
	private static SqlMapClient sqlMap = null;

	public static Connection getConnection() throws SQLException {
		ServiceLocator sl = ServiceLocator.getInstance();
		DataSource ds = (DataSource) sl.getService(ServiceLocator.DB2_DATA_SOURCE);
		return ds.getConnection();
		//Conexion conx = new Conexion();
		//return conx.getConnection();
	}
	
	public static SqlMapClient getDB2Connection() throws Exception{
		if(sqlMap==null){
			conectar();
		}
		return sqlMap;
	}
	
	
	private static void conectar() throws Exception  {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		}catch (IOException e){
			throw new Exception("Error al leer archivo de configuración de Ibatis: ", e);
		}catch (Exception e) {
			throw new Exception("Error al conectarse al datasource: ", e);
		}
	}

	public ReintegrosDAO getReintegrosDAO() {
		return new DB2ReintegrosDAO();
	}

	public ControlDocuDAO getControlDocuDAO() {
		return new DB2ControlDocuDAO();
	}

	public DatosLicCobDAO getDatosLicCobDAO() {
		return new DB2DatosLicCobDAO();
	}

	public DatosLicResolDAO getDatosLicResolDAO() {
		return new DB2DatosLicResolDAO();
	}

	public DocsRevalReemDAO getDocsRevalReemDAO() {
		return new DB2DocsRevalReemDAO();
	}

	public UsuariosDAO getUsuariosDAO() {
		return new DB2UsuariosDAO();
	}

	public LogProcesosDAO getLogProcesosDAO() {
		return new DB2LogProcesosDAO();
	}

	public TablaHomologacionDAO getTablaHomologacionDAO() {
		return new DB2TablaHomologacionDAO();
	}

	public SubsPrePostNmDAO getSubsPrePostNmDAO() {
		return new DB2SubsPrePostNmDAO();
	}

	public SubsParentalDAO getSubsParentalDAO() {
		return new DB2SubsParentalDAO();
	}

	public SubsTscVigDAO getSubsTscVigDAO() {
		return new DB2SubsTscVigDAO();
	}

	public InformeFinancieroDAO getInformeFinancieroDAO() {
		return new DB2InformeFinancieroDAO();
	}

	public PeriodoDAO getPeriodoDAO() {
		return new DB2PeriodoDAO();
	}

	public QueryIdDAO getMinMaxId() {
		return new DB2QueryIdDAO();
	}

	public ProcedimientosDAO getProcedimientosDAO() {
		return new DB2ProcedimientoDAO();
	}

	public QueryCantPers_SubsidiadasDAO getQueryCantPers_SubsidiadasDAO() {
		return new DB2QueryCantPers_SubsidiadasDAO();
	}

	public Count_Lic_AutorizadasDAO getCount_Lic_AutorizadasDAO() {
		return new DB2Count_Lic_autorizadasDAO();
	}

	public Count_Subs_IniciadosDAO getCount_Subs_IniciadosDAO() {
		return new DB2Count_Subs_iniciadosDAO();
	}

	public Sum_Dias_Subs_PagadosDAO getSum_Dias_Subs_PagadosDAO() {

		return new DB2Sum_Dias_Subs_PagadosDAO();
	}

	public Sum_Monto_Subs_PagadosDAO getSum_Monto_Subs_PagadosDAO() {
		return new DB2Sum_Monto_Subs_PagadosDAO();
	}

	public CE1_DAO getCE1_DAO() {
		return new DB2_CE1_DAO();
	}

	public CE2_DAO getCE2_DAO() {
		return new DB2_CE2_DAO();
	}

	public CE3_DAO getCE3_DAO() {
		return new DB2_CE3_DAO();
	}

	public CE4_DAO getCE4_DAO() {
		return new DB2_CE4_DAO();
	}

	public SMF09_DAO getSMF09_DAO() {
		return new DB2_SMF09_DAO();
	}

	public HistoricoDAO getHistoricoDAO() {
		return new DB2_HistoricoDAO();
	}

	public Sum_InformeFinancieroDAO getSum_InformeFinancieroDAO() {
		return new DB2Sum_InformeFinancieroDAO();
	}

	public Sum_RCP_B1P1_DAO getSum_RCP_B1P1_DAO() {
		return new DB2Sum_RCP_B1P1_DAO();
	}

	public Sum_RCP_B1P2_DAO getSum_RCP_B1P2_DAO() {
		return new DB2Sum_RCP_B1P2_DAO();
	}

	public Sum_RCP_B2P3_DAO getSum_RCP_B2P3_DAO() {
		return new DB2Sum_RCP_B2P3_DAO();
	}

	public DB2PaseContableDAO getPaseContableDAO() {
		return new DB2PaseContableDAO();
	}

}
