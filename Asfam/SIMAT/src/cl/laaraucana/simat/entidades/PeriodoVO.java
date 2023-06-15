package cl.laaraucana.simat.entidades;

import java.sql.Timestamp;

public class PeriodoVO {
	private int id;
	private String estado;
	private String periodo;
	private Timestamp fecha_hora;

	public PeriodoVO() {
	}

	public PeriodoVO(int id, String estado, String periodo, Timestamp fecha_hora) {
		super();
		this.id = id;
		this.estado = estado;
		this.periodo = periodo;
		this.fecha_hora = fecha_hora;
	}

	public String getEstado() {
		return estado;
	}

	public Timestamp getFecha_hora() {
		return fecha_hora;
	}

	public int getId() {
		return id;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setFecha_hora(Timestamp fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

}
