package cl.laaraucana.simat.mannagerDB2;

import java.util.ArrayList;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.PeriodoDAO;
import cl.laaraucana.simat.entidades.PeriodoVO;

public class PeriodosMannager {
	public ArrayList BuscarTodo() {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			PeriodoDAO periodoDAO = df.getPeriodoDAO();

			ArrayList listaUsuarios = new ArrayList();
			listaUsuarios = (ArrayList) periodoDAO.BuscarTodo();

			return listaUsuarios;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void Insertar(PeriodoVO periodo) throws Exception {
		System.out.println("Mannager periodoVO insertar");

		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		PeriodoDAO periodoDAO = df.getPeriodoDAO();

		periodoDAO.Insertar(periodo);
	}

	public void eliminar(PeriodoVO periodo) throws Exception {
		System.out.println("Llego al Mannager Reintegros");
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		PeriodoDAO periodoDAO = df.getPeriodoDAO();

		periodoDAO.Eliminar(periodo);
	}

	public void actualizar(PeriodoVO periodo) throws Exception {
		System.out.println("Llego al Mannager Reintegros");
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		PeriodoDAO periodoDAO = df.getPeriodoDAO();
		System.out.println("act mann");
		periodoDAO.Actualizar(periodo);
	}

	public PeriodoVO BuscarById(PeriodoVO periodo) {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			PeriodoDAO periodoDAO = df.getPeriodoDAO();

			periodo = periodoDAO.BuscarById(periodo);

			return periodo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public PeriodoVO BuscarByMes(PeriodoVO periodo) {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			PeriodoDAO periodoDAO = df.getPeriodoDAO();

			periodo = periodoDAO.BuscarByMes(periodo);

			return periodo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
