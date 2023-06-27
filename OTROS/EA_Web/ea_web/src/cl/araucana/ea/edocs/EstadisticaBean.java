package cl.araucana.ea.edocs;

import java.sql.Date;
import java.sql.Time;

public class EstadisticaBean {
	private int id = 0;
	private int periodo = 0;
	private int rutUsuario = 0;
	private int rutEmpresa = 0;
	private String nombreEmpresa = "";
	private String codigoOficina = "";
	private String codigoNomina = "";
	private String IP = "";
	private Date fechaCreacion = null;
	private Time horaCreacion = null;
	private String formato = "";
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public String getCodigoNomina() {
		return codigoNomina;
	}
	public void setCodigoNomina(String codigoNomina) {
		this.codigoNomina = codigoNomina;
	}
	public String getCodigoOficina() {
		return codigoOficina;
	}
	public void setCodigoOficina(String codigoOficina) {
		this.codigoOficina = codigoOficina;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Time getHoraCreacion() {
		return horaCreacion;
	}
	public void setHoraCreacion(Time horaCreacion) {
		this.horaCreacion = horaCreacion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String ip) {
		IP = ip;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public int getPeriodo() {
		return periodo;
	}
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	public int getRutEmpresa() {
		return rutEmpresa;
	}
	public void setRutEmpresa(int rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	public int getRutUsuario() {
		return rutUsuario;
	}
	public void setRutUsuario(int rutUsuario) {
		this.rutUsuario = rutUsuario;
	}
	
}
