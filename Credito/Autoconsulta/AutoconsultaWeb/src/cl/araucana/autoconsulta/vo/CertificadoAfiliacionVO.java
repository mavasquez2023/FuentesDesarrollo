package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.Date;

public class CertificadoAfiliacionVO implements Serializable 
{

	public static final String STD_ACTIVA="A";
	public static final String STD_INACTIVA="I";
	
	private long id=0;
	private long rut=0;
	private String fullNombreAfiliado=null;
	private Date fechaAfiliacion=null;
	private Date fechaEmision=null;
	
	public String getFullNombreAfiliado() {
		return fullNombreAfiliado;
	}
	public void setFullNombreAfiliado(String fullNombreAfiliado) {
		this.fullNombreAfiliado = fullNombreAfiliado;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getRut() {
		return rut;
	}
	public void setRut(long rut) {
		this.rut = rut;
	}
	public Date getFechaAfiliacion() {
		return fechaAfiliacion;
	}
	public void setFechaAfiliacion(Date fechaAfiliacion) {
		this.fechaAfiliacion = fechaAfiliacion;
	}	
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}	
	
}
