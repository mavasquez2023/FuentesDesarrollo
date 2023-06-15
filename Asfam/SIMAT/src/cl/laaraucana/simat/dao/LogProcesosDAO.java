package cl.laaraucana.simat.dao;

import java.util.ArrayList;

import cl.laaraucana.simat.entidades.LogProcesosVO;

public interface LogProcesosDAO {
	public ArrayList BuscarByIdRegistro(String id_registro) throws Exception;

	public ArrayList BuscarByTable(String tabla) throws Exception;

	public void Insertar(LogProcesosVO log) throws Exception;

	public void Eliminar(LogProcesosVO log) throws Exception;

	public void Actualizar(LogProcesosVO log) throws Exception;

	public LogProcesosVO BuscarById(LogProcesosVO log) throws Exception;

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception;

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception;
}
