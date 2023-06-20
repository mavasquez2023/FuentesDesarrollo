package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.List;

import cl.laaraucana.rendicionpagonomina.ibatis.vo.EstadoEntity;

public interface EstadoDao {

	public List<EstadoEntity> consultaEstadosActivos() throws Exception;
	
	public List<EstadoEntity> consultaEstadosNomina() throws Exception;
	
	public List<EstadoEntity> consultaEstadosPago() throws Exception;
	
}
