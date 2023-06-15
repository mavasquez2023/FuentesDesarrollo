package cl.laaraucana.simat.dao;

import java.util.ArrayList;
import java.util.HashMap;

import cl.laaraucana.simat.entidades.InformeFinancieroVO;

public interface InformeFinancieroDAO {

	public ArrayList getTodoInformeFinanciero() throws Exception;

	public ArrayList BuscarByPeriodo(String periodo) throws Exception;

	public void Insertar(InformeFinancieroVO informeFinanciero) throws Exception;

	public void Eliminar(InformeFinancieroVO informeFinanciero) throws Exception;
	
	public HashMap getResultadoPlanos() throws Exception;
}
