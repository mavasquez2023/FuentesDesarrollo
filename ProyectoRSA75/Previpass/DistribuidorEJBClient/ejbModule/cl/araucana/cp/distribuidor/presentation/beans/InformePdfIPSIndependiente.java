package cl.araucana.cp.distribuidor.presentation.beans;

import java.io.Serializable;

public class InformePdfIPSIndependiente implements Serializable{
	
	private String folioRecaudador;
	private String rut;
	private String nombres;
	private String telefono;
	private String fax;
	private String direccion;
	private String numero;
	private String ciudad;
	private String comuna;
	private String region;
	private String codigoPostal;
	private String cajaCompensacion;
	private String mutual;
	private String mail;
	private String codActividad;
	private String exCajaRegimenAnterior;
	private String fechaRemuneracion;
	private int rentaImponible;
	private String diasTrabajados;
	private String nCargasSimple;
	private String nCargasInvalidez;
	private int pensionINP;
	private int fonasa;
	private int accidenteTrabajo;
	private int desahucio;
	private int totalCotizacion;
	private int reajusteInteres;
	private int multas;
	private int totalGravames;
	private int asignacionFamiliar;
	private int bonificArt19;
	private int totalRebajas;
	private int aFavorInstitucion;
	private int aFavorEmpleador;
	
	public int getAccidenteTrabajo() {
		return accidenteTrabajo;
	}
	public void setAccidenteTrabajo(int accidenteTrabajo) {
		this.accidenteTrabajo = accidenteTrabajo;
	}
	public int getAFavorEmpleador() {
		return aFavorEmpleador;
	}
	public void setAFavorEmpleador(int favorEmpleador) {
		aFavorEmpleador = favorEmpleador;
	}
	public int getAFavorInstitucion() {
		return aFavorInstitucion;
	}
	public void setAFavorInstitucion(int favorInstitucion) {
		aFavorInstitucion = favorInstitucion;
	}
	public int getAsignacionFamiliar() {
		return asignacionFamiliar;
	}
	public void setAsignacionFamiliar(int asignacionFamiliar) {
		this.asignacionFamiliar = asignacionFamiliar;
	}
	public int getBonificArt19() {
		return bonificArt19;
	}
	public void setBonificArt19(int bonificArt19) {
		this.bonificArt19 = bonificArt19;
	}
	public String getCajaCompensacion() {
		return cajaCompensacion;
	}
	public void setCajaCompensacion(String cajaCompensacion) {
		this.cajaCompensacion = cajaCompensacion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getCodActividad() {
		return codActividad;
	}
	public void setCodActividad(String codActividad) {
		this.codActividad = codActividad;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getComuna() {
		return comuna;
	}
	public void setComuna(String comuna) {
		this.comuna = comuna;
	}
	public int getDesahucio() {
		return desahucio;
	}
	public void setDesahucio(int desahucio) {
		this.desahucio = desahucio;
	}
	public String getDiasTrabajados() {
		return diasTrabajados;
	}
	public void setDiasTrabajados(String diasTrabajados) {
		this.diasTrabajados = diasTrabajados;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getExCajaRegimenAnterior() {
		return exCajaRegimenAnterior;
	}
	public void setExCajaRegimenAnterior(String exCajaRegimenAnterior) {
		this.exCajaRegimenAnterior = exCajaRegimenAnterior;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getFechaRemuneracion() {
		return fechaRemuneracion;
	}
	public void setFechaRemuneracion(String fechaRemuneracion) {
		this.fechaRemuneracion = fechaRemuneracion;
	}
	public String getFolioRecaudador() {
		return folioRecaudador;
	}
	public void setFolioRecaudador(String folioRecaudador) {
		this.folioRecaudador = folioRecaudador;
	}
	public int getFonasa() {
		return fonasa;
	}
	public void setFonasa(int fonasa) {
		this.fonasa = fonasa;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getMultas() {
		return multas;
	}
	public void setMultas(int multas) {
		this.multas = multas;
	}
	public String getMutual() {
		return mutual;
	}
	public void setMutual(String mutual) {
		this.mutual = mutual;
	}
	public String getNCargasInvalidez() {
		return nCargasInvalidez;
	}
	public void setNCargasInvalidez(String cargasInvalidez) {
		nCargasInvalidez = cargasInvalidez;
	}
	public String getNCargasSimple() {
		return nCargasSimple;
	}
	public void setNCargasSimple(String cargasSimple) {
		nCargasSimple = cargasSimple;
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
	public int getPensionINP() {
		return pensionINP;
	}
	public void setPensionINP(int pensionINP) {
		this.pensionINP = pensionINP;
	}
	public int getReajusteInteres() {
		return reajusteInteres;
	}
	public void setReajusteInteres(int reajusteInteres) {
		this.reajusteInteres = reajusteInteres;
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
	public int getTotalCotizacion() {
		return totalCotizacion;
	}
	public void setTotalCotizacion(int totalCotizacion) {
		this.totalCotizacion = totalCotizacion;
	}
	public int getTotalGravames() {
		return totalGravames;
	}
	public void setTotalGravames(int totalGravames) {
		this.totalGravames = totalGravames;
	}
	public int getTotalRebajas() {
		return totalRebajas;
	}
	public void setTotalRebajas(int totalRebajas) {
		this.totalRebajas = totalRebajas;
	}
	
	
	
}
