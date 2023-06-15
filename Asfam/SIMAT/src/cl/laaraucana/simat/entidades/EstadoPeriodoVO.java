package cl.laaraucana.simat.entidades;

import java.util.Date;

public class EstadoPeriodoVO {
	private int id;
	private String estado;
	private String periodo;
	private Date fecha_hora;

	public EstadoPeriodoVO() {
	}

	public EstadoPeriodoVO(int id, String estado, String periodo, Date fecha_hora) {
		super();
		this.id = id;
		this.estado = estado;
		this.periodo = periodo;
		this.fecha_hora = fecha_hora;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(Date fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

}
