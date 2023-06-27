package cl.araucana.spl.dao;

import java.math.BigDecimal;

import cl.araucana.spl.beans.DetalleRendicionBCI;
import cl.araucana.spl.beans.RendicionBCI;

public interface RendicionBciDAO {
	public BigDecimal countRendicionByChecksum(String checksum);
	public BigDecimal insertRendicion(RendicionBCI rendicionBCI);
	public void insertDetalleRendicion(DetalleRendicionBCI detalleRendicionBCI);
	public DetalleRendicionBCI getDetalleRendicionBciByPagoId(BigDecimal idPago);
}
