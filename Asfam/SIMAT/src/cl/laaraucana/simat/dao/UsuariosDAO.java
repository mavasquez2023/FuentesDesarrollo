package cl.laaraucana.simat.dao;

import java.util.ArrayList;

import cl.laaraucana.simat.entidades.UsuariosVO;

public interface UsuariosDAO {

	public UsuariosVO BuscarByName(String nombre) throws Exception;

	public String Insertar(UsuariosVO usuario) throws Exception;

	public void Eliminar(UsuariosVO usuario) throws Exception;

	public ArrayList BuscarTodo() throws Exception;

	public void Actualizar(UsuariosVO usuario) throws Exception;

	public void actualizarUsuariosUltimaConeccion(UsuariosVO usuario) throws Exception;

	public UsuariosVO BuscarByIdUnico(UsuariosVO usuarios) throws Exception;
}
