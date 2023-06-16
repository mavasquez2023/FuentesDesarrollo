package cl.laaraucana.simat.dao;

import java.util.ArrayList;

import cl.laaraucana.simat.entidades.TablaHomologacionVO;

public interface TablaHomologacionDAO {
	public TablaHomologacionVO BuscarByIdRegistro(TablaHomologacionVO homologacion) throws Exception;

	public ArrayList buscarByClasificacion(TablaHomologacionVO homologacion) throws Exception;

	public void Insertar(TablaHomologacionVO homologacion) throws Exception;

	public void Eliminar(TablaHomologacionVO homologacion) throws Exception;

	public void Actualizar(TablaHomologacionVO homologacion) throws Exception;

	public ArrayList BuscarTodo() throws Exception;
}
