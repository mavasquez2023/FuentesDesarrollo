package cl.laaraucana.recepcionsil.service.vo;

import java.io.Serializable;
import java.util.List;

public class LicenciaVO implements Serializable{
	
	//OK Nivel 1
	private String fechaIngreso;
	private String oficinaIngreso;
	private String identifUsuario;
	private String rutAfiliado;
	private String dvRutAfiliado;
	private String nombreAfiliado;
	private String apellidoPaternoAfiliado;
	private String apellidoMaternoAfiliado;
	private String edadAfiliado;
	private String fechaNacTrabajador;
	private String sexoAfiliado;
	private String mixtaOFormulario;
	private String oficinaEmisora;
	private String sucursalCtaCte;
	private String nroLicencia;
	private String nroLicenciaExterna;
	private String dvNroLicenciaExterna;
	private String oficinaPago;
	private String rutEmpresa;
	private String dvRutEmpresa;
	private String diasLicencia;
	private String fechaDesde;
	private String fechaHasta;
	private String tipoLicencia;
	private String operador;
	private String color;
	private String estadoEmpresa;
	private String indValidacion;
	private String indicadorVisada;
	private String servicioSalud;
	private String fullName;
	
	//Deprecado (Mantener)
	private String fechaAfiliacion;
	private String fechaContrato;
	private String tipoReposo;
	private String formaPago;
	
	//Nuevo
	private String fechaEmision;
	private String codComunaUsoCompin;
	private String codigoInstPrevision;
	private String calidadTrabajador;
	private String fechaCreacion;
	private String horaCreacion;
	private String indiceCreacion;
	private String estadoCarga;
	private String excepcionCompin;
	private String excepcionInspTrabajo;
	private String excepcionMaternalSSB;
	private String excepcionTrabajadorNuevo;
	private String contratoDuracionIndefinido;
	private String trabajadorAfiliadoAFC;
	private String fechaRecepcionEmpleador;
	
	private List<HijoVO> hijos;

	public String getApellidoPaternoAfiliado() {
		return apellidoPaternoAfiliado;
	}

	public void setApellidoPaternoAfiliado(String apellidoPaternoAfiliado) {
		this.apellidoPaternoAfiliado = apellidoPaternoAfiliado;
	}

	public String getApellidoMaternoAfiliado() {
		return apellidoMaternoAfiliado;
	}

	public void setApellidoMaternoAfiliado(String apellidoMaternoAfiliado) {
		this.apellidoMaternoAfiliado = apellidoMaternoAfiliado;
	}

	public String getColor() {
		return color;
	}

	public String getServicioSalud() {
		return servicioSalud;
	}

	public void setServicioSalud(String servicioSalud) {
		this.servicioSalud = servicioSalud;
	}

	public String getIndicadorVisada() {
		return indicadorVisada;
	}

	public String getTipoReposo() {
		return tipoReposo;
	}

	public void setIndicadorVisada(String indicadorVisada) {
		this.indicadorVisada = indicadorVisada;
	}

	public void setTipoReposo(String tipoReposo) {
		this.tipoReposo = tipoReposo;
	}

	public String getDiasLicencia() {
		return diasLicencia;
	}

	public String getDvNroLicenciaExterna() {
		return dvNroLicenciaExterna;
	}

	public String getEdadAfiliado() {
		return edadAfiliado;
	}

	public String getEstadoEmpresa() {
		return estadoEmpresa;
	}

	public String getFechaAfiliacion() {
		return fechaAfiliacion;
	}

	public String getFechaContrato() {
		return fechaContrato;
	}

	public String getFechaDesde() {
		return fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public String getFechaNacTrabajador() {
		return fechaNacTrabajador;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public List<HijoVO> getHijos() {
		return hijos;
	}

	public String getIdentifUsuario() {
		return identifUsuario;
	}

	public String getIndValidacion() {
		return indValidacion;
	}

	public String getMixtaOFormulario() {
		return mixtaOFormulario;
	}

	public String getNombreAfiliado() {
		return nombreAfiliado;
	}
	
	public String getNroLicencia() {
		return nroLicencia;
	}
	
	public String getNroLicenciaExterna() {
		return nroLicenciaExterna;
	}

	public String getOficinaEmisora() {
		return oficinaEmisora;
	}

	public String getOficinaIngreso() {
		return oficinaIngreso;
	}

	public String getOficinaPago() {
		return oficinaPago;
	}

	public String getOperador() {
		return operador;
	}

	public String getRutAfiliado() {
		return rutAfiliado;
	}

	public String getRutEmpresa() {
		return rutEmpresa;
	}

	public String getSexoAfiliado() {
		return sexoAfiliado;
	}

	public String getSucursalCtaCte() {
		return sucursalCtaCte;
	}

	public String getTipoLicencia() {
		return tipoLicencia;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setDiasLicencia(String diasLicencia) {
		this.diasLicencia = diasLicencia;
	}

	public void setDvNroLicenciaExterna(String dvNroLicenciaExterna) {
		this.dvNroLicenciaExterna = dvNroLicenciaExterna;
	}

	public void setEdadAfiliado(String edadAfiliado) {
		this.edadAfiliado = edadAfiliado;
	}

	public void setEstadoEmpresa(String estadoEmpresa) {
		this.estadoEmpresa = estadoEmpresa;
	}

	public void setFechaAfiliacion(String fechaAfiliacion) {
		this.fechaAfiliacion = fechaAfiliacion;
	}

	public void setFechaContrato(String fechaContrato) {
		this.fechaContrato = fechaContrato;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public void setFechaNacTrabajador(String fechaNacTrabajador) {
		this.fechaNacTrabajador = fechaNacTrabajador;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public void setHijos(List<HijoVO> hijos) {
		this.hijos = hijos;
	}

	public void setIdentifUsuario(String identifUsuario) {
		this.identifUsuario = identifUsuario;
	}

	public void setIndValidacion(String indValidacion) {
		this.indValidacion = indValidacion;
	}

	public void setMixtaOFormulario(String mixtaOFormulario) {
		this.mixtaOFormulario = mixtaOFormulario;
	}

	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
	}
	
	public void setNroLicencia(String nroLicencia) {
		this.nroLicencia = nroLicencia;
	}
	
	public void setNroLicenciaExterna(String nroLicenciaExterna) {
		this.nroLicenciaExterna = nroLicenciaExterna;
	}

	public void setOficinaEmisora(String oficinaEmisora) {
		this.oficinaEmisora = oficinaEmisora;
	}

	public void setOficinaIngreso(String oficinaIngreso) {
		this.oficinaIngreso = oficinaIngreso;
	}

	public void setOficinaPago(String oficinaPago) {
		this.oficinaPago = oficinaPago;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public void setRutAfiliado(String rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}

	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	/**
	 * @return the dvRutAfiliado
	 */
	public String getDvRutAfiliado() {
		return dvRutAfiliado;
	}

	/**
	 * @param dvRutAfiliado the dvRutAfiliado to set
	 */
	public void setDvRutAfiliado(String dvRutAfiliado) {
		this.dvRutAfiliado = dvRutAfiliado;
	}

	/**
	 * @return the dvRutEmpresa
	 */
	public String getDvRutEmpresa() {
		return dvRutEmpresa;
	}

	/**
	 * @param dvRutEmpresa the dvRutEmpresa to set
	 */
	public void setDvRutEmpresa(String dvRutEmpresa) {
		this.dvRutEmpresa = dvRutEmpresa;
	}

	public void setSexoAfiliado(String sexoAfiliado) {
		this.sexoAfiliado = sexoAfiliado;
	}

	public void setSucursalCtaCte(String sucursalCtaCte) {
		this.sucursalCtaCte = sucursalCtaCte;
	}

	public void setTipoLicencia(String tipoLicencia) {
		this.tipoLicencia = tipoLicencia;
	}
	
	/**
	 * @return the fechaEmision
	 */
	public String getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	/**
	 * @return the codComunaUsoCompin
	 */
	public String getCodComunaUsoCompin() {
		return codComunaUsoCompin;
	}

	/**
	 * @param codComunaUsoCompin the codComunaUsoCompin to set
	 */
	public void setCodComunaUsoCompin(String codComunaUsoCompin) {
		this.codComunaUsoCompin = codComunaUsoCompin;
	}

	/**
	 * @return the nombreInstPrevision
	 */
	public String getCodigoInstPrevision() {
		return codigoInstPrevision;
	}

	/**
	 * @param nombreInstPrevision the nombreInstPrevision to set
	 */
	public void setCodigoInstPrevision(String codigoInstPrevision) {
		this.codigoInstPrevision = codigoInstPrevision;
	}

	/**
	 * @return the calidadTrabajador
	 */
	public String getCalidadTrabajador() {
		return calidadTrabajador;
	}

	/**
	 * @param calidadTrabajador the calidadTrabajador to set
	 */
	public void setCalidadTrabajador(String calidadTrabajador) {
		this.calidadTrabajador = calidadTrabajador;
	}

	/**
	 * @return the fechaCreacion
	 */
	public String getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the horaCreacion
	 */
	public String getHoraCreacion() {
		return horaCreacion;
	}

	/**
	 * @param horaCreacion the horaCreacion to set
	 */
	public void setHoraCreacion(String horaCreacion) {
		this.horaCreacion = horaCreacion;
	}

	/**
	 * @return the indiceCreacion
	 */
	public String getIndiceCreacion() {
		return indiceCreacion;
	}

	/**
	 * @param indiceCreacion the indiceCreacion to set
	 */
	public void setIndiceCreacion(String indiceCreacion) {
		this.indiceCreacion = indiceCreacion;
	}

	/**
	 * @return the estadoCarga
	 */
	public String getEstadoCarga() {
		return estadoCarga;
	}

	/**
	 * @param estadoCarga the estadoCarga to set
	 */
	public void setEstadoCarga(String estadoCarga) {
		this.estadoCarga = estadoCarga;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the excepcionCompin
	 */
	public String getExcepcionCompin() {
		return excepcionCompin;
	}

	/**
	 * @param excepcionCompin the excepcionCompin to set
	 */
	public void setExcepcionCompin(String excepcionCompin) {
		this.excepcionCompin = excepcionCompin;
	}

	/**
	 * @return the excepcionInspTrabajo
	 */
	public String getExcepcionInspTrabajo() {
		return excepcionInspTrabajo;
	}

	/**
	 * @param excepcionInspTrabajo the excepcionInspTrabajo to set
	 */
	public void setExcepcionInspTrabajo(String excepcionInspTrabajo) {
		this.excepcionInspTrabajo = excepcionInspTrabajo;
	}

	/**
	 * @return the excepcionMaternalSSB
	 */
	public String getExcepcionMaternalSSB() {
		return excepcionMaternalSSB;
	}

	/**
	 * @param excepcionMaternalSSB the excepcionMaternalSSB to set
	 */
	public void setExcepcionMaternalSSB(String excepcionMaternalSSB) {
		this.excepcionMaternalSSB = excepcionMaternalSSB;
	}

	/**
	 * @return the excepcionTrabajadorNuevo
	 */
	public String getExcepcionTrabajadorNuevo() {
		return excepcionTrabajadorNuevo;
	}

	/**
	 * @param excepcionTrabajadorNuevo the excepcionTrabajadorNuevo to set
	 */
	public void setExcepcionTrabajadorNuevo(String excepcionTrabajadorNuevo) {
		this.excepcionTrabajadorNuevo = excepcionTrabajadorNuevo;
	}

	/**
	 * @return the contratoDuracionIndefinido
	 */
	public String getContratoDuracionIndefinido() {
		return contratoDuracionIndefinido;
	}

	/**
	 * @param contratoDuracionIndefinido the contratoDuracionIndefinido to set
	 */
	public void setContratoDuracionIndefinido(String contratoDuracionIndefinido) {
		this.contratoDuracionIndefinido = contratoDuracionIndefinido;
	}

	/**
	 * @return the trabajadorAfiliadoAFC
	 */
	public String getTrabajadorAfiliadoAFC() {
		return trabajadorAfiliadoAFC;
	}

	/**
	 * @param trabajadorAfiliadoAFC the trabajadorAfiliadoAFC to set
	 */
	public void setTrabajadorAfiliadoAFC(String trabajadorAfiliadoAFC) {
		this.trabajadorAfiliadoAFC = trabajadorAfiliadoAFC;
	}
	
	/**
	 * @return the fechaRecepcionEmpleador
	 */
	public String getFechaRecepcionEmpleador() {
		return fechaRecepcionEmpleador;
	}

	/**
	 * @param fechaRecepcionEmpleador the fechaRecepcionEmpleador to set
	 */
	public void setFechaRecepcionEmpleador(String fechaRecepcionEmpleador) {
		this.fechaRecepcionEmpleador = fechaRecepcionEmpleador;
	}
}
