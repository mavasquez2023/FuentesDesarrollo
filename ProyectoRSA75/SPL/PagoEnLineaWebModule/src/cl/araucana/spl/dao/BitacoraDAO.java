package cl.araucana.spl.dao;

import java.math.BigDecimal;
import java.util.List;

import cl.araucana.spl.beans.Bitacora;

public interface BitacoraDAO {
	public void insertBitacora(Bitacora bitacora);
	public List findBitacora(BigDecimal idPago);
	public List findCountBitacoraByPagos(List ids);
}
