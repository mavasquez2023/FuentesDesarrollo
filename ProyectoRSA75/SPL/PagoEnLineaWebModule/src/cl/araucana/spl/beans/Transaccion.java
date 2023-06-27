package cl.araucana.spl.beans;

import java.math.BigDecimal;

public interface Transaccion {
	public BigDecimal getId();
	public Pago getPago();
}
