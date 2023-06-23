package cl.araucana.spl.dao;

import java.math.BigDecimal;

import cl.araucana.spl.beans.DetalleRendicionBCH;
import cl.araucana.spl.beans.RendicionBCH;

public interface RendicionBchDAO {
	public BigDecimal countRendicionByChecksum(String checksum);
	public DetalleRendicionBCH getDetalleRendicionBchByPagoId(BigDecimal idPago);
	public BigDecimal insertRendicion(RendicionBCH rendicionBCH);
	public void insertDetalleRendicion(DetalleRendicionBCH detalleRendicionBCH);
	
}
