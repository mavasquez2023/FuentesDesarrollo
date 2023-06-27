package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;


public class ArchivoCargaFamiliarVO implements Serializable 
{
	private static final long serialVersionUID = 4548024932185313541L;
	
	private int id;
	
	private Date fecha;
	
	private Timestamp hora;
	
	private int idCaja;
	
	private String estado;

	public ArchivoCargaFamiliarVO()
	{
		super();
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Timestamp getHora() {
		return hora;
	}

	public void setHora(Timestamp hora) {
		this.hora = hora;
	}

	public int getIdCaja() {
		return idCaja;
	}

	public void setIdCaja(int idCaja) {
		this.idCaja = idCaja;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
}