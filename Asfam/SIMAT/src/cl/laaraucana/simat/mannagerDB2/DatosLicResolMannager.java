package cl.laaraucana.simat.mannagerDB2;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.DatosLicResolDAO;
import cl.laaraucana.simat.entidades.DatosLicResolVO;

public class DatosLicResolMannager {
	public ArrayList BuscarByMesInf(String mes_informacion) {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			DatosLicResolDAO datosLicResolDAO = df.getDatosLicResolDAO();

			ArrayList listaDatosLicResol = new ArrayList();
			listaDatosLicResol = (ArrayList) datosLicResolDAO.BuscarByMesInf(mes_informacion);

			return listaDatosLicResol;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void Insertar(DatosLicResolVO datosLicResol) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DatosLicResolDAO datosLicResolDAO = df.getDatosLicResolDAO();

		System.out.println("Llego al Mannager");

		datosLicResolDAO.Insertar(datosLicResol);
	}

	public void eliminar(DatosLicResolVO datosLicResol) throws Exception {
		System.out.println("Llego al Mannager Reintegros");
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DatosLicResolDAO datosLicResolDAO = df.getDatosLicResolDAO();

		datosLicResolDAO.Eliminar(datosLicResol);
	}

	public void actualizar(DatosLicResolVO datosLicResol) throws Exception {
		System.out.println("Llego al Mannager Reintegros");
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DatosLicResolDAO datosLicResolDAO = df.getDatosLicResolDAO();
		System.out.println("act mann");
		datosLicResolDAO.Actualizar(datosLicResol);
	}

	public DatosLicResolVO BuscarByID(DatosLicResolVO datosLicResol) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DatosLicResolDAO datosLicResolDAO = df.getDatosLicResolDAO();
		System.out.println("Llego al Mannager buscarid");
		datosLicResol = datosLicResolDAO.BuscarById(datosLicResol);

		return datosLicResol;
	}

	public ArrayList BuscarTodo() {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			DatosLicResolDAO datosLicResolDAO = df.getDatosLicResolDAO();

			ArrayList listaDatosLicResol = new ArrayList();
			listaDatosLicResol = (ArrayList) datosLicResolDAO.BuscarTodo();

			return listaDatosLicResol;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DatosLicResolDAO datosLicResolDAO = df.getDatosLicResolDAO();

		return (datosLicResolDAO.BuscarListaAvanzar(keyFin));

	}

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DatosLicResolDAO datosLicResolDAO = df.getDatosLicResolDAO();

		return (datosLicResolDAO.BuscarListaRetroceder(keyInicio));

	}

	public ArrayList getDatosLicResolByRutBenef(DatosLicResolVO datosLicResol) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DatosLicResolDAO datosLicResolDAO = df.getDatosLicResolDAO();
		System.out.println("* * * getDatosLicResolByRutBenef * * * ");

		ArrayList resp = new ArrayList();
		ArrayList auxLower = new ArrayList();
		ArrayList auxUpper = new ArrayList();

		String auxK = datosLicResol.getRun_beneficiario();
		auxK = auxK.substring((auxK.length() - 1), auxK.length());
		System.out.println("* * * auxK: [" + auxK + "] * * * ");

		if (auxK.equalsIgnoreCase("k")) {
			datosLicResol.setRun_beneficiario(datosLicResol.getRun_beneficiario().toLowerCase());
			auxLower = (ArrayList) datosLicResolDAO.getDatosLicResolByRutBenef(datosLicResol);
			System.out.println("* * * auxLower: [" + auxLower.size() + "] * * * ");

			datosLicResol.setRun_beneficiario(datosLicResol.getRun_beneficiario().toUpperCase());
			auxUpper = (ArrayList) datosLicResolDAO.getDatosLicResolByRutBenef(datosLicResol);
			System.out.println("* * * auxUpper: [" + auxUpper.size() + "] * * * ");

			resp.addAll(auxLower);
			System.out.println("* * * resp + auxLower: [" + resp.size() + "] * * * ");
			resp.addAll(auxUpper);
			System.out.println("* * * resp + auxUpper: [" + resp.size() + "] * * * ");
			LinkedHashSet lhs = new LinkedHashSet();
			lhs.addAll(resp);
			resp.clear();
			resp.addAll(lhs);
		} else {
			resp = (ArrayList) datosLicResolDAO.getDatosLicResolByRutBenef(datosLicResol);
		}
		return resp;
		//return (ArrayList) datosLicResolDAO.getDatosLicResolByRutBenef(datosLicResol);
	}
}
