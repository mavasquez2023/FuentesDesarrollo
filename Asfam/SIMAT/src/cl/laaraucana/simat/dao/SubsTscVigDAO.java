package cl.laaraucana.simat.dao;

import java.util.ArrayList;

import cl.laaraucana.simat.entidades.SubsTscVigVO;

public interface SubsTscVigDAO {
	public ArrayList BuscarByMesInf(String mes_informacion) throws Exception;

	public void Insertar(SubsTscVigVO subs) throws Exception;

	public void Eliminar(SubsTscVigVO subs) throws Exception;

	public void Actualizar(SubsTscVigVO subs) throws Exception;

	public SubsTscVigVO BuscarById(SubsTscVigVO idSubsTscVig) throws Exception;

	public ArrayList BuscarTodo() throws Exception;
}
