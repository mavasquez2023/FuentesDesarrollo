package cl.araucana.spl.dao;

import java.math.BigDecimal;

import cl.araucana.spl.beans.DetalleRendicionBSA;
import cl.araucana.spl.beans.RendicionBSA;

public interface RendicionBsaDAO {
	public BigDecimal countRendicionByChecksum(String checksum);
	public DetalleRendicionBSA getDetalleRendicionBsaByPagoId(BigDecimal idPago);
	public BigDecimal insertRendicion(RendicionBSA rendicionBSA);
	public void insertDetalleRendicion(DetalleRendicionBSA detalleRendicionBSA);

}
