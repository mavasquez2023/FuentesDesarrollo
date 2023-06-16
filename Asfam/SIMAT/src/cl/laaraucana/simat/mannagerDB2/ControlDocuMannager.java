package cl.laaraucana.simat.mannagerDB2;

import java.util.ArrayList;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.ControlDocuDAO;
import cl.laaraucana.simat.entidades.ControlDocuVO;

public class ControlDocuMannager {
	public ArrayList BuscarByMesInf(String mes_informacion) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		ControlDocuDAO controlDocuDAO = df.getControlDocuDAO();
		return (ArrayList) controlDocuDAO.BuscarByMesInf(mes_informacion);
	}

	public ControlDocuVO BuscarById(ControlDocuVO controlDocu) {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			ControlDocuDAO controlDocuDAO = df.getControlDocuDAO();

			ControlDocuVO control = new ControlDocuVO();
			control = controlDocuDAO.BuscarById(controlDocu);

			return control;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void Insertar(ControlDocuVO controlDocu) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		ControlDocuDAO controlDocuDAO = df.getControlDocuDAO();

		System.out.println("Mannagerdocu insert  ");

		controlDocuDAO.Insertar(controlDocu);
	}

	public void Actualizar(ControlDocuVO controlDocu) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		ControlDocuDAO controlDocuDAO = df.getControlDocuDAO();

		controlDocuDAO.Actualizar(controlDocu);
	}

	public void Eliminar(ControlDocuVO controlDocu) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		ControlDocuDAO controlDocuDAO = df.getControlDocuDAO();

		controlDocuDAO.Eliminar(controlDocu);
	}

	public ArrayList BuscarTodo() {
		// TODO Auto-generated method stub
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			ControlDocuDAO controlDocuDAO = df.getControlDocuDAO();

			ArrayList listaControlDocu = new ArrayList();
			listaControlDocu = (ArrayList) controlDocuDAO.BuscarTodo();

			return listaControlDocu;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		ControlDocuDAO controlDocuDAO = df.getControlDocuDAO();
		return (controlDocuDAO.BuscarListaAvanzar(keyFin));
	}

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		ControlDocuDAO controlDocuDAO = df.getControlDocuDAO();
		return (controlDocuDAO.BuscarListaRetroceder(keyInicio));
	}

	public ArrayList BuscarListaAvanzarEstadoDoc_SMF05(ControlDocuVO controlDocu) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		ControlDocuDAO controlDocuDAO = df.getControlDocuDAO();
		return (controlDocuDAO.BuscarListaAvanzarEstadoDoc_SMF05(controlDocu));
	}

	public ArrayList BuscarListaRetrocederEstadoDoc_SMF05(ControlDocuVO controlDocu) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		ControlDocuDAO controlDocuDAO = df.getControlDocuDAO();
		return (controlDocuDAO.BuscarListaRetrocederEstadoDoc_SMF05(controlDocu));
	}

	public ArrayList getControlDocuByEstadoDoc(ControlDocuVO controlDocu) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		ControlDocuDAO controlDocuDAO = df.getControlDocuDAO();
		return (ArrayList) controlDocuDAO.getControlDocuByEstadoDoc(controlDocu);
	}

	public ArrayList getControlDocuByNumDoc(ControlDocuVO controlDocu) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		ControlDocuDAO controlDocuDAO = df.getControlDocuDAO();
		return (ArrayList) controlDocuDAO.getControlDocuByNumDoc(controlDocu);
	}

}
