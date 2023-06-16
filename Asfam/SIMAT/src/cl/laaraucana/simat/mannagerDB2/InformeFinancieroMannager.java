package cl.laaraucana.simat.mannagerDB2;

import java.util.ArrayList;
import java.util.HashMap;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.InformeFinancieroDAO;
import cl.laaraucana.simat.entidades.InformeFinancieroVO;

public class InformeFinancieroMannager implements InformeFinancieroDAO {

	public ArrayList BuscarByPeriodo(String periodo) {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			InformeFinancieroDAO informeDAO = df.getInformeFinancieroDAO();

			ArrayList listaInforme = new ArrayList();
			listaInforme = (ArrayList) informeDAO.BuscarByPeriodo(periodo);

			return listaInforme;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void Insertar(InformeFinancieroVO informeFinanciero) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		InformeFinancieroDAO informeFinDAO = df.getInformeFinancieroDAO();

		informeFinDAO.Insertar(informeFinanciero);
	}

	public void Eliminar(InformeFinancieroVO informeFinanciero) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		InformeFinancieroDAO informeDAO = df.getInformeFinancieroDAO();

		informeDAO.Eliminar(informeFinanciero);
	}

	public ArrayList getTodoInformeFinanciero() throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		InformeFinancieroDAO informeDAO = df.getInformeFinancieroDAO();
		return (ArrayList) informeDAO.getTodoInformeFinanciero();
	}

	public HashMap<String, Long> getResultadoPlanos() throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		InformeFinancieroDAO informeDAO = df.getInformeFinancieroDAO();
		return informeDAO.getResultadoPlanos();
	}
}
