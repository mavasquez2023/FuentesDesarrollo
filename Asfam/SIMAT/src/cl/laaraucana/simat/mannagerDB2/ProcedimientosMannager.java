package cl.laaraucana.simat.mannagerDB2;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.ProcedimientosDAO;

public class ProcedimientosMannager implements ProcedimientosDAO {
	public void callProcedureValidar(String periodo) throws Exception {

		System.out.println("Entro al mannger callProcedure Validar");

		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		ProcedimientosDAO procedimientosDAO = df.getProcedimientosDAO();
		procedimientosDAO.callProcedureValidar(periodo);

	}

	public void callProcedureCargaArchivosDB2(String periodo) throws Exception {
		System.out.println("Entro al mannger callProcedure generar ARchivos");

		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		ProcedimientosDAO procedimientosDAO = df.getProcedimientosDAO();
		procedimientosDAO.callProcedureCargaArchivosDB2(periodo);

	}

	public void callProcedureRespaldoHistorico(String periodo, String tabla) throws Exception {
		System.out.println("Entro al mannger callProcedure Respaldo historico");

		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		ProcedimientosDAO procedimientosDAO = df.getProcedimientosDAO();
		procedimientosDAO.callProcedureRespaldoHistorico(periodo, tabla);

	}

	public void callProcedureDistribucion(String periodo) throws Exception {
		System.out.println("Entro al mannger callProcedure Distribucion");

		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		ProcedimientosDAO procedimientosDAO = df.getProcedimientosDAO();
		procedimientosDAO.callProcedureDistribucion(periodo);

	}

	//	public void callProcedureFechaPorDefecto(String tabla) throws Exception {
	//		System.out.println("Entro al mannger callProcedure callProcedureFechaPorDefecto");
	//		
	//		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
	//		ProcedimientosDAO procedimientosDAO = df.getProcedimientosDAO();
	//		//procedimientosDAO.callProcedureFechaPorDefecto(tabla);		
	//	}

}
