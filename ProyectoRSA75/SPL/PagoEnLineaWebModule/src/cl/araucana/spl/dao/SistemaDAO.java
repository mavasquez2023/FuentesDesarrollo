package cl.araucana.spl.dao;

import java.math.BigDecimal;
import java.util.List;

import cl.araucana.spl.beans.Sistema;

public interface SistemaDAO {
	public Sistema findSistemaOrigenByCodigo(String origen);
	public Sistema findSistemaOrigenByIdPago(BigDecimal idPago);
	public List findSistemas();
}
