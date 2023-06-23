package cl.araucana.spl.dao;

import java.math.BigDecimal;
import java.util.List;

import cl.araucana.spl.beans.Estado;

public interface EstadoDAO {
	public List getEstados();
	public Estado getEstadoById(BigDecimal idEstado);
}
