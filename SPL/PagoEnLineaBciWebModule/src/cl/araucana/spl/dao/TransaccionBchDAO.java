package cl.araucana.spl.dao;

import java.math.BigDecimal;

import cl.araucana.spl.beans.DetalleTrxBCH;
import cl.araucana.spl.beans.TransaccionBChile;

public interface TransaccionBchDAO {
	//Transacciones Bco Chile:
	public void insertTrxBChile(TransaccionBChile trx);
	public TransaccionBChile findTransaccionBchByCodigoIdTrx(String codigoIdTrx);
	public void updateNotificacionBChile(TransaccionBChile trx);
	public void updateTrxBChile(TransaccionBChile trx);
	public DetalleTrxBCH findDetalleTrxBchByIdTrx(BigDecimal idTrx);
}
