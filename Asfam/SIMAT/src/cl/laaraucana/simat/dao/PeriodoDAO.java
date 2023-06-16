package cl.laaraucana.simat.dao;

import java.util.ArrayList;

import cl.laaraucana.simat.entidades.PeriodoVO;

public interface PeriodoDAO {
	public PeriodoVO BuscarById(PeriodoVO periodo) throws Exception;

	public void Insertar(PeriodoVO periodo) throws Exception;

	public void Eliminar(PeriodoVO periodo) throws Exception;

	public void Actualizar(PeriodoVO periodo) throws Exception;

	public ArrayList BuscarTodo() throws Exception;

	public PeriodoVO BuscarByMes(PeriodoVO periodo) throws Exception;
}
