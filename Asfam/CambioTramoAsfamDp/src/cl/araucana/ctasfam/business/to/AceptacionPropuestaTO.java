package cl.araucana.ctasfam.business.to;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class AceptacionPropuestaTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int rutEmpresa;

	private String dvRutEmpresa;

	private String razonSocial;

	private int rutEncargado;

	private String dvRutEncargado;

	private String mailEncargado;

	private String mailEncargado2;

	private String mailEncargado3;

	private String estadoProceso;

	private String formatoArchivo;

	private int cantiadadArchivos;

	private Date fechaCreacion;

	private Time horaCreacion;

	public AceptacionPropuestaTO() {
		super();
	}

	public int getCantiadadArchivos() {
		return cantiadadArchivos;
	}

	public void setCantiadadArchivos(int cantiadadArchivos) {
		this.cantiadadArchivos = cantiadadArchivos;
	}

	public String getDvRutEmpresa() {
		return dvRutEmpresa;
	}

	public void setDvRutEmpresa(String dvRutEmpresa) {
		this.dvRutEmpresa = dvRutEmpresa;
	}

	public String getDvRutEncargado() {
		return dvRutEncargado;
	}

	public void setDvRutEncargado(String dvRutEncargado) {
		this.dvRutEncargado = dvRutEncargado;
	}

	public String getEstadoProceso() {
		return estadoProceso;
	}

	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFormatoArchivo() {
		return formatoArchivo;
	}

	public void setFormatoArchivo(String formatoArchivo) {
		this.formatoArchivo = formatoArchivo;
	}

	public Time getHoraCreacion() {
		return horaCreacion;
	}

	public void setHoraCreacion(Time horaCreacion) {
		this.horaCreacion = horaCreacion;
	}

	public String getMailEncargado() {
		return mailEncargado;
	}

	public void setMailEncargado(String mailEncargado) {
		this.mailEncargado = mailEncargado;
	}

	public String getMailEncargado2() {
		return mailEncargado2;
	}

	public void setMailEncargado2(String mailEncargado2) {
		this.mailEncargado2 = mailEncargado2;
	}

	public String getMailEncargado3() {
		return mailEncargado3;
	}

	public void setMailEncargado3(String mailEncargado3) {
		this.mailEncargado3 = mailEncargado3;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public int getRutEmpresa() {
		return rutEmpresa;
	}

	public void setRutEmpresa(int rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	public int getRutEncargado() {
		return rutEncargado;
	}

	public void setRutEncargado(int rutEncargado) {
		this.rutEncargado = rutEncargado;
	}

}
