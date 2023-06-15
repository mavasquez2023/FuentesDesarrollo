package cl.araucana.ctasfam.presentation.struts.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PeticionProcesamientoDto {
	
	private Integer idPeticionProcesamiento;
	private Integer periodo;
	private Integer rutEmpresa;
	private Integer oficina;
	private Integer sucursal;
	private String estado;
	private String rutaArchivo;
	private String usuario;
	private Date fechaSubida;
	private Integer totalRegInformados;
	private Date fechaProcesamiento;
	private Integer totalRegProcesados;
	private Integer cantidadIntentos;
	private String origen;
	private Date fechaUltimoCambio;
	private String usuarioUltimoCambio;
	private Integer totalRegNoDeclarados;
	
	public PeticionProcesamientoDto(){}
	
	public PeticionProcesamientoDto(Integer idPeticionProcesamiento, Integer periodo,
			Integer rutEmpresa, Integer oficina, Integer sucursal,
			String estado, String rutaArchivo, String usuario,
			Date fechaSubida, Integer totalRegInformados,
			Date fechaProcesamiento, Integer totalRegProcesados,
			Integer cantidadIntentos, String origen, Date fechaUltimoCambio,
			String usuarioUltimoCambio, Integer totalRegNoDeclarados) {
		super();
		this.idPeticionProcesamiento = idPeticionProcesamiento;
		this.periodo = periodo;
		this.rutEmpresa = rutEmpresa;
		this.oficina = oficina;
		this.sucursal = sucursal;
		this.estado = estado;
		this.rutaArchivo = rutaArchivo;
		this.usuario = usuario;
		this.fechaSubida = fechaSubida;
		this.totalRegInformados = totalRegInformados;
		this.fechaProcesamiento = fechaProcesamiento;
		this.totalRegProcesados = totalRegProcesados;
		this.cantidadIntentos = cantidadIntentos;
		this.origen = origen;
		this.fechaUltimoCambio = fechaUltimoCambio;
		this.usuarioUltimoCambio = usuarioUltimoCambio;
		this.totalRegNoDeclarados = totalRegNoDeclarados;
		
	}
	public Integer getIdPeticionProcesamiento() {
		return idPeticionProcesamiento;
	}
	public void setIdPeticionProcesamiento(Integer idPeticionProcesamiento) {
		this.idPeticionProcesamiento = idPeticionProcesamiento;
	}
	public Integer getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}
	public Integer getRutEmpresa() {
		return rutEmpresa;
	}
	public void setRutEmpresa(Integer rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	public Integer getOficina() {
		return oficina;
	}
	public void setOficina(Integer oficina) {
		this.oficina = oficina;
	}
	public Integer getSucursal() {
		return sucursal;
	}
	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getRutaArchivo() {
		return rutaArchivo;
	}
	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getFechaSubida() {
		return fechaSubida;
	}
	public void setFechaSubida(Date fechaSubida) {
		this.fechaSubida = fechaSubida;
	}
	public Integer getTotalRegInformados() {
		return totalRegInformados;
	}
	
	public void setTotalRegInformados(Integer totalRegInformados) {
		this.totalRegInformados = totalRegInformados;
	}
	public Date getFechaProcesamiento() {
		return fechaProcesamiento;
	}
	public void setFechaProcesamiento(Date fechaProcesamiento) {
		this.fechaProcesamiento = fechaProcesamiento;
	}
	public Integer getTotalRegProcesados() {
		return totalRegProcesados;
	}
	public void setTotalRegProcesados(Integer totalRegProcesados) {
		this.totalRegProcesados = totalRegProcesados;
	}
	public Integer getCantidadIntentos() {
		return cantidadIntentos;
	}
	public void setCantidadIntentos(Integer cantidadIntentos) {
		this.cantidadIntentos = cantidadIntentos;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public Date getFechaUltimoCambio() {
		return fechaUltimoCambio;
	}

	public void setFechaUltimoCambio(Date fechaUltimoCambio) {
		this.fechaUltimoCambio = fechaUltimoCambio;
	}

	public String getUsuarioUltimoCambio() {
		return usuarioUltimoCambio;
	}

	public void setUsuarioUltimoCambio(String usuarioUltimoCambio) {
		this.usuarioUltimoCambio = usuarioUltimoCambio;
	}
	
	public String getFechaEnvioStr(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return (fechaSubida!=null)?sdf.format(fechaSubida):"";
	}
	public String getFechaProcesamientoStr(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return (fechaProcesamiento!=null)?sdf.format(fechaProcesamiento):"";
	}
	public String getTotalRegProcesadosStr() {
		return ((totalRegProcesados!=null)?String.valueOf(totalRegProcesados):"0");
	}

	public Integer getTotalRegNoDeclarados() {
		return totalRegNoDeclarados;
	}

	public void setTotalRegNoDeclarados(Integer totalRegNoDeclarados) {
		this.totalRegNoDeclarados = totalRegNoDeclarados;
	}
	
}
