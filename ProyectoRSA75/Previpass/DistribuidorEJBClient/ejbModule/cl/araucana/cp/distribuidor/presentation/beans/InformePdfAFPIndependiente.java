package cl.araucana.cp.distribuidor.presentation.beans;

import java.io.Serializable;

public class InformePdfAFPIndependiente implements Serializable{
	
	
	private String nombres;
	private String rut;
	private String direccion;
	private String numero;
	private String comuna;
	private String ciudad;
	private String region;
	private String mail;
	private String telefono;
	private String fechaPago;
	private String rentaMes;
	private boolean tipoAfiliadoObligatorio;
	private boolean tipoAfiliadoVoluntario;
	private String cantRentas;
	private int rentaImponible;
	private int cotizacionObligatoria;
	private int cotizacionVoluntaria;
	private int cuentaOhorroVoluntario;
	private int totalApagarFondos;
	private boolean regimenTributarioA;
	private boolean regimenTributarioB;
	private int cotizacionSalud;
	private int totalPagarAFP;
	private String folioRecaudador;
	
	public String getFolioRecaudador() {
		return folioRecaudador;
	}
	public void setFolioRecaudador(String folioRecaudador) {
		this.folioRecaudador = folioRecaudador;
	}
	public String getCantRentas() {
		return cantRentas;
	}
	public void setCantRentas(String cantRentas) {
		this.cantRentas = cantRentas;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getComuna() {
		return comuna;
	}
	public void setComuna(String comuna) {
		this.comuna = comuna;
	}
	public int getCotizacionObligatoria() {
		return cotizacionObligatoria;
	}
	public void setCotizacionObligatoria(int cotizacionObligatoria) {
		this.cotizacionObligatoria = cotizacionObligatoria;
	}
	public int getCotizacionSalud() {
		return cotizacionSalud;
	}
	public void setCotizacionSalud(int cotizacionSalud) {
		this.cotizacionSalud = cotizacionSalud;
	}
	public int getCotizacionVoluntaria() {
		return cotizacionVoluntaria;
	}
	public void setCotizacionVoluntaria(int cotizacionVoluntaria) {
		this.cotizacionVoluntaria = cotizacionVoluntaria;
	}
	public int getCuentaOhorroVoluntario() {
		return cuentaOhorroVoluntario;
	}
	public void setCuentaOhorroVoluntario(int cuentaOhorroVoluntario) {
		this.cuentaOhorroVoluntario = cuentaOhorroVoluntario;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public boolean isRegimenTributarioA() {
		return regimenTributarioA;
	}
	public void setRegimenTributarioA(boolean regimenTributarioA) {
		this.regimenTributarioA = regimenTributarioA;
	}
	public boolean isRegimenTributarioB() {
		return regimenTributarioB;
	}
	public void setRegimenTributarioB(boolean regimenTributarioB) {
		this.regimenTributarioB = regimenTributarioB;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getRentaImponible() {
		return rentaImponible;
	}
	public void setRentaImponible(int rentaImponible) {
		this.rentaImponible = rentaImponible;
	}
	public String getRentaMes() {
		return rentaMes;
	}
	public void setRentaMes(String rentaMes) {
		this.rentaMes = rentaMes;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public boolean isTipoAfiliadoObligatorio() {
		return tipoAfiliadoObligatorio;
	}
	public void setTipoAfiliadoObligatorio(boolean tipoAfiliadoObligatorio) {
		this.tipoAfiliadoObligatorio = tipoAfiliadoObligatorio;
	}
	public boolean isTipoAfiliadoVoluntario() {
		return tipoAfiliadoVoluntario;
	}
	public void setTipoAfiliadoVoluntario(boolean tipoAfiliadoVoluntario) {
		this.tipoAfiliadoVoluntario = tipoAfiliadoVoluntario;
	}
	public int getTotalApagarFondos() {
		return totalApagarFondos;
	}
	public void setTotalApagarFondos(int totalApagarFondos) {
		this.totalApagarFondos = totalApagarFondos;
	}
	public int getTotalPagarAFP() {
		return totalPagarAFP;
	}
	public void setTotalPagarAFP(int totalPagarAFP) {
		this.totalPagarAFP = totalPagarAFP;
	}
	
	
	
	

}
