package cl.araucana.spl.beans;

import java.math.BigDecimal;
import java.util.Date;

import cl.araucana.spl.util.Nulls;

public class TransaccionBci implements Transaccion {
	private BigDecimal id;
	private Pago pago;
	private String estadoBci = Nulls.STRING;
	
	public BigDecimal getId() {
		return id;
	}

	public Pago getPago() {
		return pago;
	}
	public BigDecimal getIdPago() {
		return pago.getId();
	}

	public String getEstadoBci() {
		return estadoBci;
	}

	public void setEstadoBci(String estadoBci) {
		this.estadoBci = estadoBci;
	}
	
	public String toString() {
		return new StringBuffer("[TRX::id=").append(id).
				append(",estadoBci=").append(estadoBci)
				.append(",pago=").append(pago)
				.append("]").toString();
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	public BigDecimal getMontoTotal() {
		return getPago().getMontoTotal();
	}

	public String getCodigoBanco() {
		return getPago().getCodigoBanco();
	}

	public String getCodigoSistema() {
		return getPago().getCodigoSistema();
	}

	public int getCantidadItems() {
		return getPago().getCantidadItems();
	}

	public String getUrlCgi() {
		return getPago().getUrlCgi();
	}

	public void setPagado(Integer pagado) {
		getPago().setPagado(pagado);
	}

	public boolean existeNotificacion() {
		return Nulls.isNotNull(estadoBci);
	}

	public void setFechaTransaccion(Date fecha) {
		getPago().setFechaTransaccion(fecha);
	}
}
