package cl.laaraucana.simat.mannagerDB2;

import java.util.ArrayList;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.LogProcesosDAO;
import cl.laaraucana.simat.entidades.LogProcesosVO;

public class LogProcesosMannager {
	public ArrayList BuscarByIdRegistro(String id_registro) {
		ArrayList listaProcesos = new ArrayList();
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);

			LogProcesosDAO logDAO = df.getLogProcesosDAO();

			listaProcesos = (ArrayList) logDAO.BuscarByIdRegistro(id_registro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaProcesos;
	}

	public void Insertar(LogProcesosVO log) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		LogProcesosDAO logDAO = df.getLogProcesosDAO();

		logDAO.Insertar(log);
	}

	public void eliminar(LogProcesosVO log) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		LogProcesosDAO logDAO = df.getLogProcesosDAO();

		logDAO.Eliminar(log);
	}

	public void actualizar(LogProcesosVO log) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		LogProcesosDAO logDAO = df.getLogProcesosDAO();
		logDAO.Actualizar(log);
	}

	public LogProcesosVO BuscarByID(LogProcesosVO log) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		LogProcesosDAO logDAO = df.getLogProcesosDAO();
		log = logDAO.BuscarById(log);

		return log;
	}

	public ArrayList BuscarByTable(String tabla) throws Exception {
		ArrayList listaProcesos = new ArrayList();
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			LogProcesosDAO logDAO = df.getLogProcesosDAO();

			listaProcesos = (ArrayList) logDAO.BuscarByTable(tabla);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaProcesos;
	}

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception {

		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		LogProcesosDAO logDAO = df.getLogProcesosDAO();

		return (logDAO.BuscarListaAvanzar(keyFin));

	}

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception {

		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		LogProcesosDAO logDAO = df.getLogProcesosDAO();

		return (logDAO.BuscarListaRetroceder(keyInicio));

	}

}
