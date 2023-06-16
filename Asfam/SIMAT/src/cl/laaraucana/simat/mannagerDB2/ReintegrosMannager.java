package cl.laaraucana.simat.mannagerDB2;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.ReintegrosDAO;
import cl.laaraucana.simat.entidades.ReintegrosVO;

public class ReintegrosMannager {

	public ArrayList BuscarByMesInf(String mes_informacion) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		ReintegrosDAO reintegrosDAO = df.getReintegrosDAO();
		ArrayList listaReintegros = new ArrayList();
		listaReintegros = (ArrayList) reintegrosDAO.BuscarByMesInf(mes_informacion);
		return listaReintegros;
	}

	public ArrayList getReintegrosByRutBenef(ReintegrosVO reintegros) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		ReintegrosDAO reintegrosDAO = df.getReintegrosDAO();

		System.out.println("* * * getReintegrosByRutBenef * * * ");
		ArrayList resp = new ArrayList();
		ArrayList auxLower = new ArrayList();
		ArrayList auxUpper = new ArrayList();

		String auxK = reintegros.getRun_beneficiario();
		auxK = auxK.substring((auxK.length() - 1), auxK.length());
		System.out.println("* * * auxK: [" + auxK + "] * * * ");

		if (auxK.equalsIgnoreCase("k")) {
			reintegros.setRun_beneficiario(reintegros.getRun_beneficiario().toLowerCase());
			auxLower = (ArrayList) reintegrosDAO.getReintegrosByRutBenef(reintegros);
			reintegros.setRun_beneficiario(reintegros.getRun_beneficiario().toUpperCase());
			auxUpper = (ArrayList) reintegrosDAO.getReintegrosByRutBenef(reintegros);

			resp.addAll(auxLower);
			System.out.println("* * * resp + auxLower: [" + resp.size() + "] * * * ");
			resp.addAll(auxUpper);
			System.out.println("* * * resp + auxUpper: [" + resp.size() + "] * * * ");
			LinkedHashSet lhs = new LinkedHashSet();
			lhs.addAll(resp);
			resp.clear();
			resp.addAll(lhs);
		} else {
			resp = (ArrayList) reintegrosDAO.getReintegrosByRutBenef(reintegros);
		}
		return resp;

		//return ((ArrayList) reintegrosDAO.getReintegrosByRutBenef(reintegros));
	}

	public ReintegrosVO BuscarByID(ReintegrosVO reintegros) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		ReintegrosDAO reintegrosDAO = df.getReintegrosDAO();

		System.out.println("Llego al Mannager buscarid");
		reintegros = reintegrosDAO.BuscarById(reintegros);

		return reintegros;
	}

	public void Insertar(ReintegrosVO reintegros) throws Exception {
		System.out.println("Llego al Mannager Reintegros");
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		ReintegrosDAO reintegrosDAO = df.getReintegrosDAO();

		reintegrosDAO.Insertar(reintegros);
	}

	public void eliminar(ReintegrosVO reintegros) throws Exception {
		System.out.println("Llego al Mannager Reintegros");
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		ReintegrosDAO reintegrosDAO = df.getReintegrosDAO();

		reintegrosDAO.Eliminar(reintegros);
	}

	public void actualizar(ReintegrosVO reintegros) throws Exception {
		System.out.println("Llego al Mannager Reintegros");
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		ReintegrosDAO reintegrosDAO = df.getReintegrosDAO();
		System.out.println("act mann");
		reintegrosDAO.Actualizar(reintegros);
	}

	public ArrayList BuscarTodo() throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		ReintegrosDAO reintegrosDAO = df.getReintegrosDAO();
		System.out.println("Llego al Mannager buscartodo");

		ArrayList listaReintegros = new ArrayList();
		listaReintegros = (ArrayList) reintegrosDAO.buscarTodo();

		return listaReintegros;
	}

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception {

		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		ReintegrosDAO reintegrosDAO = df.getReintegrosDAO();

		return (reintegrosDAO.BuscarListaAvanzar(keyFin));

	}

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception {

		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		ReintegrosDAO reintegrosDAO = df.getReintegrosDAO();

		return (reintegrosDAO.BuscarListaRetroceder(keyInicio));

	}
}
