package cl.laaraucana.simat.mannagerDB2;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.DatosLicCobDAO;
import cl.laaraucana.simat.entidades.DatosLicCobVO;

public class DatosLicCobMannager {
	public ArrayList BuscarByMesInf(String mes_informacion) {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			DatosLicCobDAO datolLicCobDAO = df.getDatosLicCobDAO();

			ArrayList listaDatosLicCob = new ArrayList();
			listaDatosLicCob = (ArrayList) datolLicCobDAO.BuscarByMesInf(mes_informacion);

			return listaDatosLicCob;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void Insertar(DatosLicCobVO datosLicCob) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DatosLicCobDAO datosLicCobDAO = df.getDatosLicCobDAO();
		System.out.println("Llego al Mannager");
		datosLicCobDAO.Insertar(datosLicCob);
	}

	public void eliminar(DatosLicCobVO datosLicCob) throws Exception {
		System.out.println("Llego al Mannager Reintegros");
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DatosLicCobDAO datolLicCobDAO = df.getDatosLicCobDAO();

		datolLicCobDAO.Eliminar(datosLicCob);
	}

	public void actualizar(DatosLicCobVO datosLicCob) throws Exception {
		System.out.println("Llego al Mannager Reintegros");
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DatosLicCobDAO datolLicCobDAO = df.getDatosLicCobDAO();
		System.out.println("act mann");
		datolLicCobDAO.Actualizar(datosLicCob);
	}

	public DatosLicCobVO BuscarByID(DatosLicCobVO datosLicCob) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DatosLicCobDAO datolLicCobDAO = df.getDatosLicCobDAO();
		System.out.println("Llego al Mannager buscarid");
		datosLicCob = datolLicCobDAO.BuscarById(datosLicCob);

		return datosLicCob;
	}

	public ArrayList BuscarTodo() {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			DatosLicCobDAO datolLicCobDAO = df.getDatosLicCobDAO();

			ArrayList listaDatosLicCob = new ArrayList();
			listaDatosLicCob = (ArrayList) datolLicCobDAO.BuscarTodo();

			return listaDatosLicCob;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DatosLicCobDAO datolLicCobDAO = df.getDatosLicCobDAO();

		return (datolLicCobDAO.BuscarListaAvanzar(keyFin));

	}

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DatosLicCobDAO datolLicCobDAO = df.getDatosLicCobDAO();

		return (datolLicCobDAO.BuscarListaRetroceder(keyInicio));

	}

	public ArrayList getDatosLicCobByRutBenef(DatosLicCobVO datosLicCob) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		DatosLicCobDAO datolLicCobDAO = df.getDatosLicCobDAO();

		ArrayList resp = new ArrayList();
		ArrayList auxLower = new ArrayList();
		ArrayList auxUpper = new ArrayList();

		String auxK = datosLicCob.getRun_beneficiario();
		auxK = auxK.substring((auxK.length() - 1), auxK.length());
		System.out.println("* * * auxK: [" + auxK + "] * * * ");

		if (auxK.equalsIgnoreCase("k")) {
			datosLicCob.setRun_beneficiario(datosLicCob.getRun_beneficiario().toLowerCase());
			auxLower = (ArrayList) datolLicCobDAO.getDatosLicCobByRutBenef(datosLicCob);

			datosLicCob.setRun_beneficiario(datosLicCob.getRun_beneficiario().toUpperCase());
			auxUpper = (ArrayList) datolLicCobDAO.getDatosLicCobByRutBenef(datosLicCob);

			resp.addAll(auxLower);
			System.out.println("* * * resp + auxLower: [" + resp.size() + "] * * * ");
			resp.addAll(auxUpper);
			System.out.println("* * * resp + auxUpper: [" + resp.size() + "] * * * ");
			LinkedHashSet lhs = new LinkedHashSet();
			lhs.addAll(resp);
			resp.clear();
			resp.addAll(lhs);
		} else {
			resp = (ArrayList) datolLicCobDAO.getDatosLicCobByRutBenef(datosLicCob);
		}

		return resp;

		//return (ArrayList) datolLicCobDAO.getDatosLicCobByRutBenef(datosLicCob);
	}//end getDatosLicCobByRutBenef

}//end class
