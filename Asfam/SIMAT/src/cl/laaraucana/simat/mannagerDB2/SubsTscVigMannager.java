package cl.laaraucana.simat.mannagerDB2;

import java.util.ArrayList;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.SubsTscVigDAO;
import cl.laaraucana.simat.entidades.SubsTscVigVO;

public class SubsTscVigMannager {

	public void Actualizar(SubsTscVigVO subsTscVig) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);

		SubsTscVigDAO subsTscVigDAO = df.getSubsTscVigDAO();

		System.out.println("Llego al Mannager eliminar");

		subsTscVigDAO.Actualizar(subsTscVig);
	}

	public void Eliminar(SubsTscVigVO subsTscVig) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);

		SubsTscVigDAO subsTscVigDAO = df.getSubsTscVigDAO();

		System.out.println("Llego al Mannager eliminar");

		subsTscVigDAO.Eliminar(subsTscVig);
	}

	public ArrayList BuscarByMes(String mesInformacion) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);

		SubsTscVigDAO subsTscVigDAO = df.getSubsTscVigDAO();

		System.out.println("Llego al Mannagerdb2 buscar");

		ArrayList listaSubsTscVig = new ArrayList();
		listaSubsTscVig = subsTscVigDAO.BuscarByMesInf(mesInformacion);
		return (listaSubsTscVig);
	}

	public void Insertar(SubsTscVigVO subsTscVig) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);

		SubsTscVigDAO subsTscVigDAO = df.getSubsTscVigDAO();

		System.out.println("Llego al Mannager insertar");

		subsTscVigDAO.Insertar(subsTscVig);

	}

	public SubsTscVigVO BuscarByID(SubsTscVigVO subsTscVig) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);

		SubsTscVigDAO subsTscVigDAO = df.getSubsTscVigDAO();

		System.out.println("Llego al Mannager buscar");
		SubsTscVigVO subsTscVigres = new SubsTscVigVO();
		subsTscVigres = subsTscVigDAO.BuscarById(subsTscVig);
		return subsTscVigres;
	}

	public ArrayList BuscarTodo() throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);

		SubsTscVigDAO subsTscVigDAO = df.getSubsTscVigDAO();

		System.out.println("Llego al Mannagerdb2 buscar");

		ArrayList listaSubsTscVig = new ArrayList();
		listaSubsTscVig = subsTscVigDAO.BuscarTodo();
		return (listaSubsTscVig);
	}
}
