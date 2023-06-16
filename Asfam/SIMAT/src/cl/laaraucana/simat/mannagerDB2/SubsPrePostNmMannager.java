package cl.laaraucana.simat.mannagerDB2;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.SubsPrePostNmDAO;
import cl.laaraucana.simat.entidades.SubsPrePostNMVO;

public class SubsPrePostNmMannager {
	public ArrayList BuscarByMesInf(String mes_informacion) {
		ArrayList listaSubs = new ArrayList();
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			SubsPrePostNmDAO subsPrePostNmDAO = df.getSubsPrePostNmDAO();

			System.out.println("MANN mes = " + mes_informacion);

			listaSubs = (ArrayList) subsPrePostNmDAO.BuscarByMesInf(mes_informacion);

			System.out.println("llego mannager lista " + listaSubs.size());

			return listaSubs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaSubs;
	}

	public SubsPrePostNMVO BuscarByID(SubsPrePostNMVO subsPrePostNM) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		SubsPrePostNmDAO subsPrePostNMVODAO = df.getSubsPrePostNmDAO();
		System.out.println("Llego al Mannager buscarid");

		subsPrePostNM = subsPrePostNMVODAO.BuscarById(subsPrePostNM);

		return subsPrePostNM;
	}

	public void Insertar(SubsPrePostNMVO subs) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		SubsPrePostNmDAO subsPrePostNmDAO = df.getSubsPrePostNmDAO();

		subsPrePostNmDAO.Insertar(subs);
	}

	public void eliminar(SubsPrePostNMVO subs) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		SubsPrePostNmDAO subsPrePostNmDAO = df.getSubsPrePostNmDAO();

		subsPrePostNmDAO.Eliminar(subs);
	}

	public void actualizar(SubsPrePostNMVO subsPrePostNM) throws Exception {
		System.out.println("Llego al Mannager SubsPrePostNMVO");
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		SubsPrePostNmDAO subsPrePostNMDAO = df.getSubsPrePostNmDAO();

		subsPrePostNMDAO.Actualizar(subsPrePostNM);
	}

	public ArrayList BuscarTodo() {
		ArrayList listaSubs = new ArrayList();
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			SubsPrePostNmDAO subsPrePostNmDAO = df.getSubsPrePostNmDAO();

			listaSubs = (ArrayList) subsPrePostNmDAO.BuscarTodo();

			System.out.println("llego mannager lista " + listaSubs.size());

			return listaSubs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaSubs;
	}

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception {

		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		SubsPrePostNmDAO subsPrePostNmDAO = df.getSubsPrePostNmDAO();

		return (subsPrePostNmDAO.BuscarListaAvanzar(keyFin));

	}

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		SubsPrePostNmDAO subsPrePostNmDAO = df.getSubsPrePostNmDAO();

		return (subsPrePostNmDAO.BuscarListaRetroceder(keyInicio));

	}

	public ArrayList getSubsPrePostNMByNumDoc(SubsPrePostNMVO subsPrePostNM) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		SubsPrePostNmDAO subsPrePostNmDAO = df.getSubsPrePostNmDAO();

		return (subsPrePostNmDAO.getSubsPrePostNMByNumDoc(subsPrePostNM));
	}

	public ArrayList getSubsPrePostNMByRutBenef(SubsPrePostNMVO subsPrePostNM) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		SubsPrePostNmDAO subsPrePostNmDAO = df.getSubsPrePostNmDAO();

		ArrayList resp = new ArrayList();
		ArrayList auxLower = new ArrayList();
		ArrayList auxUpper = new ArrayList();

		String auxK = subsPrePostNM.getRun_beneficiario();
		auxK = auxK.substring((auxK.length() - 1), auxK.length());
		System.out.println("* * * auxK: [" + auxK + "] * * * ");

		if (auxK.equalsIgnoreCase("k")) {
			subsPrePostNM.setRun_beneficiario(subsPrePostNM.getRun_beneficiario().toLowerCase());
			auxLower = subsPrePostNmDAO.getSubsPrePostNMByRutBenef(subsPrePostNM);
			subsPrePostNM.setRun_beneficiario(subsPrePostNM.getRun_beneficiario().toUpperCase());
			auxUpper = subsPrePostNmDAO.getSubsPrePostNMByRutBenef(subsPrePostNM);

			resp.addAll(auxLower);
			System.out.println("* * * resp + auxLower: [" + resp.size() + "] * * * ");
			resp.addAll(auxUpper);
			System.out.println("* * * resp + auxUpper: [" + resp.size() + "] * * * ");
			LinkedHashSet lhs = new LinkedHashSet();
			lhs.addAll(resp);
			resp.clear();
			resp.addAll(lhs);
		} else {
			resp = subsPrePostNmDAO.getSubsPrePostNMByRutBenef(subsPrePostNM);
		}
		return resp;
		//return (subsPrePostNmDAO.getSubsPrePostNMByRutBenef(subsPrePostNM));
	}//end getSubsPrePostNMByRutBenef

}//end manager
