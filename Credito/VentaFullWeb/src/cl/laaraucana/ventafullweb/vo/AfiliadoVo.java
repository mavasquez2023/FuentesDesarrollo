package cl.laaraucana.ventafullweb.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class AfiliadoVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String rutAfiliado;
	private String nombreAfiliado;
	private int afiliadoVigente;
	private int campagnaVigente;
	private String fechaVigencia;
	private int montoCampagna;
	private String segmento;
	private String rutEmpresa;
	private String razonSocial;
	private int idCampagna;
	private String inscripcionPension;
	private int licenciaMedicaActiva;
	private int afiliadoFallecido;
	private String sucursal;
	private int licenciaMedicaIsapre;
	private int creditosVigentes;
	private int insolvenciaInterna;
	private int marcaDesafiliacion;
	private int deudaCRF;
	private int deudaCRM;
	private String serieCedula;
	private String celular;
	private int afiliadoFuncionario;
	private int montoSimulacion;
	private int cuotas;
	private String seguroCesantia;
	private String seguroDesgravamen;
	private int tipoSeguro;
	private int montoCuota;
	private String tasaInteresMensual;
	private String cae;
	private int costoTotalCredito;
	private int impuesto;
	private int gastoNotaria;
	private int valorMensualSeguroCesantia;
	private int valorMensualSeguroDesgravamen;
	private String pagoPrimeraCuota;
	private String captcha;
	private String tasaInteresAnual;
	private int costoTotalCesantia;
	private int costoTotalDesgravamen;
	private List<DetalleCuotasVo> detalleCuotas;
	private String autorizar;
	private String declaracionJurada;
	private String correo;
	private String telefono;
	private String fechaNacimiento;
	private String estadoCivil;
	private String tipoContrato;
	private String direccion;
	private String dpto;
	private String numeroDireccion;
	private String villaPoblacion;
	private String codigoRegion;
	private String codigoProvincia;
	private String codigoComuna;
	private String bancoNumeroCuenta;
	private String bancoTipoCuenta;
	private String bancoCodigoBanco;
	private String nacionalidad;
	private String superaVecesRenta;
	private String superaMaxEndeudNorm;
	private String rentaPromedio;
		
	/**
	 * @return the rutAfiliado
	 */
	public String getRutAfiliado() {
		return rutAfiliado;
	}
	/**
	 * @param rutAfiliado the rutAfiliado to set
	 */
	public void setRutAfiliado(String rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}
	/**
	 * @return the nombreAfiliado
	 */
	public String getNombreAfiliado() {
		return nombreAfiliado;
	}
	/**
	 * @param nombreAfiliado the nombreAfiliado to set
	 */
	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
	}
	/**
	 * @return the afiliadoVigente
	 */
	public int getAfiliadoVigente() {
		return afiliadoVigente;
	}
	/**
	 * @param afiliadoVigente the afiliadoVigente to set
	 */
	public void setAfiliadoVigente(int afiliadoVigente) {
		this.afiliadoVigente = afiliadoVigente;
	}
	/**
	 * @return the campagnaVigente
	 */
	public int getCampagnaVigente() {
		return campagnaVigente;
	}
	/**
	 * @param campagnaVigente the campagnaVigente to set
	 */
	public void setCampagnaVigente(int campagnaVigente) {
		this.campagnaVigente = campagnaVigente;
	}
	/**
	 * @return the fechaVigencia
	 */
	public String getFechaVigencia() {
		return fechaVigencia;
	}
	/**
	 * @param fechaVigencia the fechaVigencia to set
	 */
	public void setFechaVigencia(String fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}
	/**
	 * @return the montoCampagna
	 */
	public int getMontoCampagna() {
		return montoCampagna;
	}
	/**
	 * @param montoCampagna the montoCampagna to set
	 */
	public void setMontoCampagna(int montoCampagna) {
		this.montoCampagna = montoCampagna;
	}
	/**
	 * @return the segmento
	 */
	public String getSegmento() {
		return segmento;
	}
	/**
	 * @param segmento the segmento to set
	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}
	/**
	 * @return the rutEmpresa
	 */
	public String getRutEmpresa() {
		return rutEmpresa;
	}
	/**
	 * @param rutEmpresa the rutEmpresa to set
	 */
	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	/**
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}
	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	/**
	 * @return the idCampagna
	 */
	public int getIdCampagna() {
		return idCampagna;
	}
	/**
	 * @param idCampagna the idCampagna to set
	 */
	public void setIdCampagna(int idCampagna) {
		this.idCampagna = idCampagna;
	}
	/**
	 * @return the inscripcionPension
	 */
	public String getInscripcionPension() {
		return inscripcionPension;
	}
	/**
	 * @param inscripcionPension the inscripcionPension to set
	 */
	public void setInscripcionPension(String inscripcionPension) {
		this.inscripcionPension = inscripcionPension;
	}
	/**
	 * @return the licenciaMedicaActiva
	 */
	public int getLicenciaMedicaActiva() {
		return licenciaMedicaActiva;
	}
	/**
	 * @param licenciaMedicaActiva the licenciaMedicaActiva to set
	 */
	public void setLicenciaMedicaActiva(int licenciaMedicaActiva) {
		this.licenciaMedicaActiva = licenciaMedicaActiva;
	}
	/**
	 * @return the afiliadoFallecido
	 */
	public int getAfiliadoFallecido() {
		return afiliadoFallecido;
	}
	/**
	 * @param afiliadoFallecido the afiliadoFallecido to set
	 */
	public void setAfiliadoFallecido(int afiliadoFallecido) {
		this.afiliadoFallecido = afiliadoFallecido;
	}
	/**
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}
	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	/**
	 * @return the licenciaMedicaIsapre
	 */
	public int getLicenciaMedicaIsapre() {
		return licenciaMedicaIsapre;
	}
	/**
	 * @param licenciaMedicaIsapre the licenciaMedicaIsapre to set
	 */
	public void setLicenciaMedicaIsapre(int licenciaMedicaIsapre) {
		this.licenciaMedicaIsapre = licenciaMedicaIsapre;
	}
	/**
	 * @return the creditosVigentes
	 */
	public int getCreditosVigentes() {
		return creditosVigentes;
	}
	/**
	 * @param creditosVigentes the creditosVigentes to set
	 */
	public void setCreditosVigentes(int creditosVigentes) {
		this.creditosVigentes = creditosVigentes;
	}
	/**
	 * @return the insolvenciaInterna
	 */
	public int getInsolvenciaInterna() {
		return insolvenciaInterna;
	}
	/**
	 * @param insolvenciaInterna the insolvenciaInterna to set
	 */
	public void setInsolvenciaInterna(int insolvenciaInterna) {
		this.insolvenciaInterna = insolvenciaInterna;
	}
	/**
	 * @return the marcaDesafiliacion
	 */
	public int getMarcaDesafiliacion() {
		return marcaDesafiliacion;
	}
	/**
	 * @param marcaDesafiliacion the marcaDesafiliacion to set
	 */
	public void setMarcaDesafiliacion(int marcaDesafiliacion) {
		this.marcaDesafiliacion = marcaDesafiliacion;
	}
	/**
	 * @return the deudaCRF
	 */
	public int getDeudaCRF() {
		return deudaCRF;
	}
	/**
	 * @param deudaCRF the deudaCRF to set
	 */
	public void setDeudaCRF(int deudaCRF) {
		this.deudaCRF = deudaCRF;
	}
	/**
	 * @return the deudaCRM
	 */
	public int getDeudaCRM() {
		return deudaCRM;
	}
	/**
	 * @param deudaCRM the deudaCRM to set
	 */
	public void setDeudaCRM(int deudaCRM) {
		this.deudaCRM = deudaCRM;
	}
	/**
	 * @return the serieCedula
	 */
	public String getSerieCedula() {
		return serieCedula;
	}
	/**
	 * @param serieCedula the serieCedula to set
	 */
	public void setSerieCedula(String serieCedula) {
		this.serieCedula = serieCedula;
	}
	/**
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}
	/**
	 * @param celular the celular to set
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}
	/**
	 * @return the afiliadoFuncionario
	 */
	public int getAfiliadoFuncionario() {
		return afiliadoFuncionario;
	}
	/**
	 * @param afiliadoFuncionario the afiliadoFuncionario to set
	 */
	public void setAfiliadoFuncionario(int afiliadoFuncionario) {
		this.afiliadoFuncionario = afiliadoFuncionario;
	}
	/**
	 * @return the montoSimulacion
	 */
	public int getMontoSimulacion() {
		return montoSimulacion;
	}
	/**
	 * @param montoSimulacion the montoSimulacion to set
	 */
	public void setMontoSimulacion(int montoSimulacion) {
		this.montoSimulacion = montoSimulacion;
	}
	/**
	 * @return the cuotas
	 */
	public int getCuotas() {
		return cuotas;
	}
	/**
	 * @param cuotas the cuotas to set
	 */
	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}
	/**
	 * @return the seguroCesantia
	 */
	public String getSeguroCesantia() {
		return seguroCesantia;
	}
	/**
	 * @param seguroCesantia the seguroCesantia to set
	 */
	public void setSeguroCesantia(String seguroCesantia) {
		this.seguroCesantia = seguroCesantia;
	}
	/**
	 * @return the seguroDesgravamen
	 */
	public String getSeguroDesgravamen() {
		return seguroDesgravamen;
	}
	/**
	 * @param seguroDesgravamen the seguroDesgravamen to set
	 */
	public void setSeguroDesgravamen(String seguroDesgravamen) {
		this.seguroDesgravamen = seguroDesgravamen;
	}
	/**
	 * @return the tipoSeguro
	 */
	public int getTipoSeguro() {
		return tipoSeguro;
	}
	/**
	 * @param tipoSeguro the tipoSeguro to set
	 */
	public void setTipoSeguro(int tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}
	/**
	 * @return the montoCuota
	 */
	public int getMontoCuota() {
		return montoCuota;
	}
	/**
	 * @param montoCuota the montoCuota to set
	 */
	public void setMontoCuota(int montoCuota) {
		this.montoCuota = montoCuota;
	}
	/**
	 * @return the tasaInteresMensual
	 */
	public String getTasaInteresMensual() {
		return tasaInteresMensual;
	}
	/**
	 * @param tasaInteresMensual the tasaInteresMensual to set
	 */
	public void setTasaInteresMensual(String tasaInteresMensual) {
		this.tasaInteresMensual = tasaInteresMensual;
	}
	/**
	 * @return the cae
	 */
	public String getCae() {
		return cae;
	}
	/**
	 * @param cae the cae to set
	 */
	public void setCae(String cae) {
		this.cae = cae;
	}
	/**
	 * @return the costoTotalCredito
	 */
	public int getCostoTotalCredito() {
		return costoTotalCredito;
	}
	/**
	 * @param costoTotalCredito the costoTotalCredito to set
	 */
	public void setCostoTotalCredito(int costoTotalCredito) {
		this.costoTotalCredito = costoTotalCredito;
	}
	/**
	 * @return the impuesto
	 */
	public int getImpuesto() {
		return impuesto;
	}
	/**
	 * @param impuesto the impuesto to set
	 */
	public void setImpuesto(int impuesto) {
		this.impuesto = impuesto;
	}
	/**
	 * @return the gastoNotaria
	 */
	public int getGastoNotaria() {
		return gastoNotaria;
	}
	/**
	 * @param gastoNotaria the gastoNotaria to set
	 */
	public void setGastoNotaria(int gastoNotaria) {
		this.gastoNotaria = gastoNotaria;
	}
	/**
	 * @return the valorMensualSeguroCesantia
	 */
	public int getValorMensualSeguroCesantia() {
		return valorMensualSeguroCesantia;
	}
	/**
	 * @param valorMensualSeguroCesantia the valorMensualSeguroCesantia to set
	 */
	public void setValorMensualSeguroCesantia(int valorMensualSeguroCesantia) {
		this.valorMensualSeguroCesantia = valorMensualSeguroCesantia;
	}
	/**
	 * @return the valorMensualSeguroDesgravamen
	 */
	public int getValorMensualSeguroDesgravamen() {
		return valorMensualSeguroDesgravamen;
	}
	/**
	 * @param valorMensualSeguroDesgravamen the valorMensualSeguroDesgravamen to set
	 */
	public void setValorMensualSeguroDesgravamen(int valorMensualSeguroDesgravamen) {
		this.valorMensualSeguroDesgravamen = valorMensualSeguroDesgravamen;
	}
	/**
	 * @return the pagoPrimeraCuota
	 */
	public String getPagoPrimeraCuota() {
		return pagoPrimeraCuota;
	}
	/**
	 * @param pagoPrimeraCuota the pagoPrimeraCuota to set
	 */
	public void setPagoPrimeraCuota(String pagoPrimeraCuota) {
		this.pagoPrimeraCuota = pagoPrimeraCuota;
	}
	/**
	 * @return the captcha
	 */
	public String getCaptcha() {
		return captcha;
	}
	/**
	 * @param captcha the captcha to set
	 */
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	/**
	 * @return the tasaInteresAnual
	 */
	public String getTasaInteresAnual() {
		return tasaInteresAnual;
	}
	/**
	 * @param tasaInteresAnual the tasaInteresAnual to set
	 */
	public void setTasaInteresAnual(String tasaInteresAnual) {
		this.tasaInteresAnual = tasaInteresAnual;
	}
	/**
	 * @return the costoTotalCesantia
	 */
	public int getCostoTotalCesantia() {
		return costoTotalCesantia;
	}
	/**
	 * @param costoTotalCesantia the costoTotalCesantia to set
	 */
	public void setCostoTotalCesantia(int costoTotalCesantia) {
		this.costoTotalCesantia = costoTotalCesantia;
	}
	/**
	 * @return the costoTotalDesgravamen
	 */
	public int getCostoTotalDesgravamen() {
		return costoTotalDesgravamen;
	}
	/**
	 * @param costoTotalDesgravamen the costoTotalDesgravamen to set
	 */
	public void setCostoTotalDesgravamen(int costoTotalDesgravamen) {
		this.costoTotalDesgravamen = costoTotalDesgravamen;
	}
	/**
	 * @return the detalleCuotas
	 */
	public List<DetalleCuotasVo> getDetalleCuotas() {
		return detalleCuotas;
	}
	/**
	 * @param detalleCuotas the detalleCuotas to set
	 */
	public void setDetalleCuotas(List<DetalleCuotasVo> detalleCuotas) {
		this.detalleCuotas = detalleCuotas;
	}
	/**
	 * @return the autorizar
	 */
	public String getAutorizar() {
		return autorizar;
	}
	/**
	 * @param autorizar the autorizar to set
	 */
	public void setAutorizar(String autorizar) {
		this.autorizar = autorizar;
	}
	public String getDeclaracionJurada() {
		return declaracionJurada;
	}
	public void setDeclaracionJurada(String declaracionJurada) {
		this.declaracionJurada = declaracionJurada;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getTipoContrato() {
		return tipoContrato;
	}
	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}	
	public String getNumeroDireccion() {
		return numeroDireccion;
	}
	public void setNumeroDireccion(String numeroDireccion) {
		this.numeroDireccion = numeroDireccion;
	}
	public String getDpto() {
		return dpto;
	}
	public void setDpto(String dpto) {
		this.dpto = dpto;
	}
	public String getVillaPoblacion() {
		return villaPoblacion;
	}
	public void setVillaPoblacion(String villaPoblacion) {
		this.villaPoblacion = villaPoblacion;
	}
	public String getCodigoRegion() {
		return codigoRegion;
	}
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	public String getCodigoProvincia() {
		return codigoProvincia;
	}
	public void setCodigoProvincia(String codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}
	public String getCodigoComuna() {
		return codigoComuna;
	}
	public void setCodigoComuna(String codigoComuna) {
		this.codigoComuna = codigoComuna;
	}
	/**
	 * @return the bancoNumeroCuenta
	 */
	public String getBancoNumeroCuenta() {
		return bancoNumeroCuenta;
	}
	/**
	 * @param bancoNumeroCuenta the bancoNumeroCuenta to set
	 */
	public void setBancoNumeroCuenta(String bancoNumeroCuenta) {
		this.bancoNumeroCuenta = bancoNumeroCuenta;
	}
	/**
	 * @return the bancoTipoCuenta
	 */
	public String getBancoTipoCuenta() {
		return bancoTipoCuenta;
	}
	/**
	 * @param bancoTipoCuenta the bancoTipoCuenta to set
	 */
	public void setBancoTipoCuenta(String bancoTipoCuenta) {
		this.bancoTipoCuenta = bancoTipoCuenta;
	}
	/**
	 * @return the bancoCodigoBanco
	 */
	public String getBancoCodigoBanco() {
		return bancoCodigoBanco;
	}
	/**
	 * @param bancoCodigoBanco the bancoCodigoBanco to set
	 */
	public void setBancoCodigoBanco(String bancoCodigoBanco) {
		this.bancoCodigoBanco = bancoCodigoBanco;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	/**
	 * @return the superaVecesRenta
	 */
	public String getSuperaVecesRenta() {
		return superaVecesRenta;
	}
	/**
	 * @param superaVecesRenta the superaVecesRenta to set
	 */
	public void setSuperaVecesRenta(String superaVecesRenta) {
		this.superaVecesRenta = superaVecesRenta;
	}
	/**
	 * @return the superaMaxEndeudNorm
	 */
	public String getSuperaMaxEndeudNorm() {
		return superaMaxEndeudNorm;
	}
	/**
	 * @param superaMaxEndeudNorm the superaMaxEndeudNorm to set
	 */
	public void setSuperaMaxEndeudNorm(String superaMaxEndeudNorm) {
		this.superaMaxEndeudNorm = superaMaxEndeudNorm;
	}
	public String getRentaPromedio() {
		return rentaPromedio;
	}
	public void setRentaPromedio(String rentaPromedio) {
		this.rentaPromedio = rentaPromedio;
	}
	
	
	}
