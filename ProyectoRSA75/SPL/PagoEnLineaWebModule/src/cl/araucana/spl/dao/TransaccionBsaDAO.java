package cl.araucana.spl.dao;

import java.math.BigDecimal;

import cl.araucana.spl.beans.DetalleTrxBSA;
import cl.araucana.spl.beans.TransaccionBsa;

public interface TransaccionBsaDAO {
	//Transacciones Bco Santander:
	public void insertTrx(TransaccionBsa trx);
	public TransaccionBsa findTransaccionByCodigoIdTrx(String codigoIdTrx);
	public void updateNotificacion(TransaccionBsa trx);
	public void updateTrx(TransaccionBsa trx);
	public DetalleTrxBSA findDetalleTrxByIdTrx(BigDecimal idTrx);
}
