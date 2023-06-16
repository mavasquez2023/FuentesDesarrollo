package cl.laaraucana.simat.mannagerDB2;

import java.util.ArrayList;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.TablaHomologacionDAO;
import cl.laaraucana.simat.entidades.TablaHomologacionVO;

public class TablaHomologacionMannager {

	public TablaHomologacionVO BuscarByIdRegistro(TablaHomologacionVO homologacion) {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			TablaHomologacionDAO homologacionDAO = df.getTablaHomologacionDAO();

			System.out.println("llego al mannager" + homologacion.getId_registro());
			homologacion = (TablaHomologacionVO) homologacionDAO.BuscarByIdRegistro(homologacion);
			return homologacion;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList buscarByClasificacion(TablaHomologacionVO homologacion) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		TablaHomologacionDAO homologacionDAO = df.getTablaHomologacionDAO();
		return (ArrayList) homologacionDAO.buscarByClasificacion(homologacion);
	}

	public void Insertar(TablaHomologacionVO homologacion) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		TablaHomologacionDAO homologacionDAO = df.getTablaHomologacionDAO();
		System.out.println("llego al insertar del mannager");

		homologacionDAO.Insertar(homologacion);
	}

	public void Actualizar(TablaHomologacionVO homologacion) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		TablaHomologacionDAO homologacionDAO = df.getTablaHomologacionDAO();
		System.out.println("llego al iactualizar del mannager");

		homologacionDAO.Actualizar(homologacion);
	}

	public void Eliminar(TablaHomologacionVO homologacion) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		TablaHomologacionDAO homologacionDAO = df.getTablaHomologacionDAO();
		System.out.println("llego al eliminar del mannager");

		homologacionDAO.Eliminar(homologacion);
	}

	public ArrayList buscarTodoHomologacion() {
		ArrayList listaHomologacion = new ArrayList();
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			TablaHomologacionDAO homologacionDAO = df.getTablaHomologacionDAO();
			listaHomologacion = (ArrayList) homologacionDAO.BuscarTodo();
		} catch (Exception e) {
			listaHomologacion = null;
			e.printStackTrace();
		}
		return listaHomologacion;
	}

}//end class
