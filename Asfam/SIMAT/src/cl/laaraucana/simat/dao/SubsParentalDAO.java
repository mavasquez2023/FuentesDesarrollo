package cl.laaraucana.simat.dao;

import java.util.ArrayList;

import cl.laaraucana.simat.entidades.SubsParentalVO;

public interface SubsParentalDAO {
	public ArrayList BuscarByMesInf(String mes_informacion) throws Exception;

	public void Insertar(SubsParentalVO subs) throws Exception;

	public void Eliminar(SubsParentalVO subsParental) throws Exception;

	public void Actualizar(SubsParentalVO subs) throws Exception;

	public SubsParentalVO BuscarById(SubsParentalVO subsParental) throws Exception;

	public ArrayList BuscarTodo() throws Exception;

	public ArrayList BuscarLista(int keyInicio, int keyFin) throws Exception;

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception;

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception;

	public ArrayList getSubsParentalByRutBenef(SubsParentalVO subsParental) throws Exception;

	public ArrayList getSubsParentalByNumDoc(SubsParentalVO subsParental) throws Exception;
}
