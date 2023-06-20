package cl.laaraucana.pagoenexceso.persistencia.vo;

import java.util.Date;

public class PagoEnExceso {
	private Date  fecha;
	private String  monto;

	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
