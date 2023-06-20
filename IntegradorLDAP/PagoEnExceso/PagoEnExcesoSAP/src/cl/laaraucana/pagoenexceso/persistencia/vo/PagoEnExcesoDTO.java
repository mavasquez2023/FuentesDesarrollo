package cl.laaraucana.pagoenexceso.persistencia.vo;

import java.util.Date;

public class PagoEnExcesoDTO {
	private Date fechaCreacionDate;
	private String fechaCreacion;
	private int monto;
	private String rut;
	private int tipo;
	private String nombreCompleto;

	public int getTipo() {
		return this.tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public PagoEnExcesoDTO() {
		this.fechaCreacionDate = null;
		this.monto = 0;
		this.rut = "";
	}

	public PagoEnExcesoDTO(Date fecha, int monto, String rut, int tipo) {
		this.fechaCreacionDate = fecha;
		this.monto = monto;
		this.rut = rut;
		this.tipo = tipo;
	}

	public PagoEnExcesoDTO(String fecha, int monto, String rut, int tipo) {
		this.fechaCreacion = fecha;
		this.monto = monto;
		this.rut = rut;
		this.tipo = tipo;
	}

	public PagoEnExcesoDTO(Date fecha, String rut, int monto) {
		this.fechaCreacionDate = fecha;
		this.monto = monto;
		this.rut = rut;

	}
	
	public Date getFechaCreacionDate() {
		return fechaCreacionDate;
	}

	public void setFechaCreacionDate(Date fechaCreacionDate) {
		this.fechaCreacionDate = fechaCreacionDate;
	}
	
	public int getMonto() {
		return this.monto;
	}

	public String getRut() {
		return this.rut;
	}

	public void setMonto(int i) {
		this.monto = i;
	}

	public void setRut(String string) {
		this.rut = string;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}



	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

}
