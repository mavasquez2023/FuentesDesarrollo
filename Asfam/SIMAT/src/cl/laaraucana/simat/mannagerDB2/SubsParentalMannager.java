package cl.laaraucana.simat.mannagerDB2;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.SubsParentalDAO;
import cl.laaraucana.simat.entidades.SubsParentalVO;

public class SubsParentalMannager {

	public void Actualizar(SubsParentalVO subsParental) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);

		SubsParentalDAO subsParentalDAO = df.getSubsParentalDAO();

		System.out.println("Llego al Mannager actualizar");

		subsParentalDAO.Actualizar(subsParental);
	}

	public void Eliminar(SubsParentalVO subsParental) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);

		SubsParentalDAO subsParentalDAO = df.getSubsParentalDAO();

		System.out.println("Llego al Mannager eliminar");

		subsParentalDAO.Eliminar(subsParental);
	}

	public ArrayList BuscarByMes(String mesInformacion) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		SubsParentalDAO subsParentalDAO = df.getSubsParentalDAO();
		return (subsParentalDAO.BuscarByMesInf(mesInformacion));
	}

	public void Insertar(SubsParentalVO subsParental) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);

		SubsParentalDAO subsParentalDAO = df.getSubsParentalDAO();

		System.out.println("Llego al Mannager insertardb2");

		subsParentalDAO.Insertar(subsParental);
	}

	public SubsParentalVO BuscarByID(SubsParentalVO subsParental) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);

		SubsParentalDAO subsParentalDAO = df.getSubsParentalDAO();

		System.out.println("Llego al Mannager buscarId");

		return subsParentalDAO.BuscarById(subsParental);
	}

	public ArrayList BuscarTodo() throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		SubsParentalDAO subsParentalDAO = df.getSubsParentalDAO();
		return (subsParentalDAO.BuscarTodo());
	}

	public ArrayList BuscarLista(int keyInicio, int keyFin) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		SubsParentalDAO subsParentalDAO = df.getSubsParentalDAO();

		return (subsParentalDAO.BuscarLista(keyInicio, keyFin));
	}

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		SubsParentalDAO subsParentalDAO = df.getSubsParentalDAO();

		return (subsParentalDAO.BuscarListaAvanzar(keyFin));
	}

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		SubsParentalDAO subsParentalDAO = df.getSubsParentalDAO();

		return (subsParentalDAO.BuscarListaRetroceder(keyInicio));
	}

	public ArrayList getSubsParentalByNumDoc(SubsParentalVO subsParental) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		SubsParentalDAO subsParentalDAO = df.getSubsParentalDAO();
		return (subsParentalDAO.getSubsParentalByNumDoc(subsParental));
	}

	public ArrayList getSubsParentalByRutBenef(SubsParentalVO subsParental) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		SubsParentalDAO subsParentalDAO = df.getSubsParentalDAO();

		ArrayList resp = new ArrayList();
		ArrayList auxLower = new ArrayList();
		ArrayList auxUpper = new ArrayList();

		String auxK = subsParental.getRun_beneficiario_parental();
		auxK = auxK.substring((auxK.length() - 1), auxK.length());
		System.out.println("* * * auxK: [" + auxK + "] * * * ");

		if (auxK.equalsIgnoreCase("k")) {
			subsParental.setRun_beneficiario_parental(subsParental.getRun_beneficiario_parental().toLowerCase());
			auxLower = subsParentalDAO.getSubsParentalByRutBenef(subsParental);
			subsParental.setRun_beneficiario_parental(subsParental.getRun_beneficiario_parental().toUpperCase());
			auxUpper = subsParentalDAO.getSubsParentalByRutBenef(subsParental);

			resp.addAll(auxLower);
			System.out.println("* * * resp + auxLower: [" + resp.size() + "] * * * ");
			resp.addAll(auxUpper);
			System.out.println("* * * resp + auxUpper: [" + resp.size() + "] * * * ");
			LinkedHashSet lhs = new LinkedHashSet();
			lhs.addAll(resp);
			resp.clear();
			resp.addAll(lhs);
		} else {
			resp = subsParentalDAO.getSubsParentalByRutBenef(subsParental);
		}
		return resp;
		//return(subsParentalDAO.getSubsParentalByRutBenef(subsParental));
	}//end getSubsParentalByRutBenef
}
