package cl.laaraucana.botonpago.web.database.vo;

import java.sql.Date;

public class Evento {

	private int id;
	private int idTipoEvento;
	private long rut;
	private String descripcion;
	private Date creacion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdTipoEvento() {
		return idTipoEvento;
	}

	public void setIdTipoEvento(int idTipoEvento) {
		this.idTipoEvento = idTipoEvento;
	}

	public long getRut() {
		return rut;
	}

	public void setRut(long rut) {
		this.rut = rut;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getCreacion() {
		return creacion;
	}

	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}

	@Override
	public String toString() {
		return String.format("Evento[id=%d,idTipoEvento=%s,rut=%s,descripcion=%s,creacion=%s]", id, idTipoEvento, rut, descripcion, creacion);
	}
}
