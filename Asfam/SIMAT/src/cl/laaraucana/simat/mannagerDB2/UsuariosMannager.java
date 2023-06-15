package cl.laaraucana.simat.mannagerDB2;

import java.util.ArrayList;

import cl.laaraucana.simat.comun.conx.DAOFactory;
import cl.laaraucana.simat.dao.UsuariosDAO;
import cl.laaraucana.simat.entidades.UsuariosVO;

public class UsuariosMannager implements UsuariosDAO {

	public UsuariosVO BuscarByName(String nombre) throws Exception {

		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		UsuariosDAO usuariosDAO = df.getUsuariosDAO();
		UsuariosVO uvo = new UsuariosVO();
		uvo = (UsuariosVO) usuariosDAO.BuscarByName(nombre);
		return uvo;
	}

	public String Insertar(UsuariosVO usuarios) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		UsuariosDAO usuariosDAO = df.getUsuariosDAO();
		return (usuariosDAO.Insertar(usuarios));
	}

	public UsuariosVO BuscarByIdUnico(UsuariosVO usuarios) {
		try {
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
			UsuariosDAO usuariosDAO = df.getUsuariosDAO();

			usuarios = usuariosDAO.BuscarByIdUnico(usuarios);

			return usuarios;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void Actualizar(UsuariosVO usuario) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		UsuariosDAO usuariosDAO = df.getUsuariosDAO();
		usuariosDAO.Actualizar(usuario);

	}

	public void Eliminar(UsuariosVO usuario) throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		UsuariosDAO usuariosDAO = df.getUsuariosDAO();

		usuariosDAO.Eliminar(usuario);

	}

	public void actualizarUsuariosUltimaConeccion(UsuariosVO usuario) throws Exception {

		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		UsuariosDAO usuariosDAO = df.getUsuariosDAO();
		usuariosDAO.actualizarUsuariosUltimaConeccion(usuario);
	}

	public ArrayList BuscarTodo() throws Exception {
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.DB2);
		UsuariosDAO usuariosDAO = df.getUsuariosDAO();
		return usuariosDAO.BuscarTodo();
	}

}
