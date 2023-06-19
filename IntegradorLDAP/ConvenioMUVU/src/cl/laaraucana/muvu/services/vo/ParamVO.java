package cl.laaraucana.muvu.services.vo;

import java.util.Date;

public class ParamVO {
	private Date fechaComienzo;
	private Date fechaTermino;
	public Date getFechaComienzo() {
		return fechaComienzo;
	}
	public void setFechaComienzo(Date fechaComienzo) {
		this.fechaComienzo = fechaComienzo;
	}
	public Date getFechaTermino() {
		return fechaTermino;
	}
	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}
}
