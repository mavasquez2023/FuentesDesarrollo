package cl.laaraucana.muvu.ibatis.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;


public class AfiliadoSuraVo {
	private static final Logger logger = Logger.getLogger(AfiliadoSuraVo.class);
	private Integer poliza;
	private Integer grupos;
	private Date fechaIncorporacion;
	private Integer tipoAsegurado;
	private Long rut;
	private String dv;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Date fechaNacimiento;
	private Long rutCarga;
	private String dvCarga;
	private String nombresCarga;
	private String apellidoPaternoCarga;
	private String apellidoMaternoCarga;
	private String fechaNacimientoCarga;
	private String moneda;
	private String renta;
	private String tipoCuenta;
	private String numeroCuenta;
	private String codBanco;
	private String celular;
	private String correoElectronico;
	private String codModulo;
	private String continuidadCobertura;
	private Date fechaAlta;
	private String sexo;
	private String estatura;
	private String peso;
	private Integer rol;
	private Date fechaExclusion;

	public AfiliadoSuraVo() {
		// valores fijos: requieren solamente get, no set.
		poliza = 3333;
		grupos = 0;
		tipoAsegurado = 2;
		nombresCarga = "";
		apellidoPaternoCarga = "";
		apellidoMaternoCarga = "";
		fechaNacimientoCarga = "";
		moneda = "";
		renta = "";
		tipoCuenta = "";
		numeroCuenta = "";
		codBanco = "";
		codModulo = "";
		continuidadCobertura = "";
		estatura = "";
		peso = "";
		rol = 22;
		celular = "";
	}

	public String getParsedVigenciaInicialTitular() {
		String dateString = null;
		SimpleDateFormat sdfr = new SimpleDateFormat("yyyyMMdd");
		try {
			dateString = sdfr.format(fechaIncorporacion);
		} catch (Exception ex) {
			logger.error("AfiliadoMuvuVo : error parseando fechaIncorporacion = " + ex);
		}
		return dateString;
	}
	
	public String getParsedFechaNacimiento() {
		String dateString = null;
		SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dateString = sdfr.format(fechaNacimiento);
		} catch (Exception ex) {
			logger.error("AfiliadoMuvuVo : error parseando fechaNacimiento = " + ex);
		}
		return dateString;
	}
	
	public String getParsedFechaExclusion() {
		String dateString = null;
		SimpleDateFormat sdfr = new SimpleDateFormat("yyyyMMdd");
		try {
			dateString = sdfr.format(fechaExclusion);
		} catch (Exception ex) {
			logger.error("AfiliadoMuvuVo : error parseando fechaExclusion = " + ex);
		}
		return dateString;
	}
	
	public Long getRut() {
		return rut;
	}

	public Date getFechaIncorporacion() {
		return fechaIncorporacion;
	}

	public void setFechaIncorporacion(Date fechaIncorporacion) {
		this.fechaIncorporacion = fechaIncorporacion;
	}

	public void setRut(Long rut) {
		this.rut = rut;
	}

	public String getDv() {
		return dv;
	}

	public void setDv(String dv) {
		this.dv = dv;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCelular() {
		return celular;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getFechaExclusion() {
		return fechaExclusion;
	}

	public void setFechaExclusion(Date fechaExclusion) {
		this.fechaExclusion = fechaExclusion;
	}
	//Getter para valores fijos no nulos.
	public Integer getPoliza() {
		return poliza;
	}

	public Integer getGrupos() {
		return grupos;
	}

	public Integer getTipoAsegurado() {
		return tipoAsegurado;
	}

	public Long getRutCarga() {
		return rutCarga;
	}

	public String getDvCarga() {
		return dvCarga;
	}

	public String getNombresCarga() {
		return nombresCarga;
	}

	public String getApellidoPaternoCarga() {
		return apellidoPaternoCarga;
	}

	public String getApellidoMaternoCarga() {
		return apellidoMaternoCarga;
	}

	public String getFechaNacimientoCarga() {
		return fechaNacimientoCarga;
	}

	public String getMoneda() {
		return moneda;
	}

	public String getRenta() {
		return renta;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public String getCodBanco() {
		return codBanco;
	}

	public String getContinuidadCobertura() {
		return continuidadCobertura;
	}

	public String getEstatura() {
		return estatura;
	}

	public String getPeso() {
		return peso;
	}

	public String getCodModulo() {
		return codModulo;
	}

	public Integer getRol() {
		return rol;
	}

	
}
