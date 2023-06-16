package cl.laaraucana.simat.dao;

import java.util.ArrayList;

import cl.laaraucana.simat.entidades.ReintegrosVO;

public interface ReintegrosDAO {
	public ArrayList BuscarByMesInf(String mes_informacion) throws Exception;

	public void Insertar(ReintegrosVO reintegros) throws Exception;

	public void Eliminar(ReintegrosVO reintegros) throws Exception;

	public void Actualizar(ReintegrosVO reintegros) throws Exception;

	public ReintegrosVO BuscarById(ReintegrosVO reintegros) throws Exception;

	public ArrayList buscarTodo() throws Exception;

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception;

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception;

	public ArrayList getReintegrosByRutBenef(ReintegrosVO reintegros) throws Exception;
}
