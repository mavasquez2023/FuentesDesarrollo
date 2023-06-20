package cl.laaraucana.tarjetatam.ibatis.vo;

import java.util.Date;


public class CuentaVo {

	private long idmandato;
	private long rutafi;
	private String dvafi;
	private String nomafi;
	private String celular;
	private String telefono;
	private String email;
	private int codbanco;
	private String numcuenta;
	private int codtipocuenta;
	private int tipoprod;
	private int codcanal;
	private Date fechaCreacion;
	private Date fechaVigencia;
	private Date fechaTermino;
	private String estado;

	public long getIdmandato() {
		return idmandato;
	}

	public void setIdmandato(long idmandato) {
		this.idmandato = idmandato;
	}

	public long getRutafi() {
		return rutafi;
	}

	public void setRutafi(long rutafi) {
		this.rutafi = rutafi;
	}

	public String getDvafi() {
		return dvafi;
	}

	public void setDvafi(String dvafi) {
		this.dvafi = dvafi;
	}

	public String getNomafi() {
		return nomafi;
	}

	public void setNomafi(String nomafi) {
		this.nomafi = nomafi;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCodbanco() {
		return codbanco;
	}

	public void setCodbanco(int codbanco) {
		this.codbanco = codbanco;
	}

	public String getNumcuenta() {
		return numcuenta;
	}

	public void setNumcuenta(String numcuenta) {
		this.numcuenta = numcuenta;
	}

	public int getCodtipocuenta() {
		return codtipocuenta;
	}

	public void setCodtipocuenta(int codtipocuenta) {
		this.codtipocuenta = codtipocuenta;
	}

	public int getTipoprod() {
		return tipoprod;
	}

	public void setTipoprod(int tipoprod) {
		this.tipoprod = tipoprod;
	}

	public int getCodcanal() {
		return codcanal;
	}

	public void setCodcanal(int codcanal) {
		this.codcanal = codcanal;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaVigencia() {
		return fechaVigencia;
	}

	public void setFechaVigencia(Date fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	public Date getFechaTermino() {
		return fechaTermino;
	}

	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
