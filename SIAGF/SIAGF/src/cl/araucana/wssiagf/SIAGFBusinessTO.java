

package cl.araucana.wssiagf;


import java.io.Serializable;
import java.util.List;


public class SIAGFBusinessTO implements Serializable {

	private String fechaEmision;

	private int tipoCausante;
	private char sexoCausante;
	private String rutCausante;
	private String nombreCausante;
	private String fechaNacimientoCausante;
	private int regionCausante;
	private int comunaCausante;

	private int tipoBeneficiario;
	private String rutBeneficiario;
	private String nombreBeneficiario;
	private int regionBeneficiario;
	private int comunaBeneficiario;
	private int ingresoPromedio;

	private String rutEmpleador;
	private String nombreEmpleador;
	private int actividadEconomicaEmpleador;
	private int regionEmpleador;
	private int comunaEmpleador;

	private int codigoEntidadAdministradora;

	private int idTipoBeneficio;
	private String fechaReconocimientoCausante;
	private String fechaPagoBeneficio;
	private int montoUnitarioBeneficio;
	private int puntajeFichaProtSocial;
	private int tramoASFAM;
	private String fechaExtincionCausante;
	private int causaExtincionCausante;
	
	public SIAGFBusinessTO() {
	}

	public void setFechaEmision(String fecha) {
		this.fechaEmision = fecha.trim();
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setTipoCausante(int tipo) {
		this.tipoCausante = tipo;
	}

	public int getTipoCausante() {
		return tipoCausante;
	}

	public void setSexoCausante(char sexo) {
		this.sexoCausante = sexo;
	}

	public char getSexoCausante() {
		return sexoCausante;
	}

	public void setRutCausante(String rut) {
		this.rutCausante = rut.trim();
	}

	public String getRutCausante() {
		return rutCausante;
	}

	public void setNombreCausante(String nombre) {
		this.nombreCausante = nombre.trim();
	}

	public String getNombreCausante() {
		return nombreCausante;
	}

	public void setFechaNacimientoCausante(String fecha) {
		this.fechaNacimientoCausante = fecha.trim();
	}

	public String getFechaNacimientoCausante() {
		return fechaNacimientoCausante;
	}

	public void setRegionCausante(int region) {
		this.regionCausante = region;
	}

	public int getRegionCausante() {
		return regionCausante;
	}

	public void setComunaCausante(int comuna) {
		this.comunaCausante = comuna;
	}

	public int getComunaCausante() {
		return comunaCausante;
	}

	public void setTipoBeneficiario(int tipo) {
		this.tipoBeneficiario = tipo;
	}

	public int getTipoBeneficiario() {
		return tipoBeneficiario;
	}

	public void setRutBeneficiario(String rut) {
		this.rutBeneficiario = rut.trim();
	}

	public String getRutBeneficiario() {
		return rutBeneficiario;
	}

	public void setNombreBeneficiario(String nombre) {
		this.nombreBeneficiario = nombre.trim();
	}

	public String getNombreBeneficiario() {
		return nombreBeneficiario;
	}

	public void setRegionBeneficiario(int region) {
		this.regionBeneficiario = region;
	}

	public int getRegionBeneficiario() {
		return regionBeneficiario;
	}

	public void setComunaBeneficiario(int comuna) {
		this.comunaBeneficiario = comuna;
	}

	public int getComunaBeneficiario() {
		return comunaBeneficiario;
	}

	public void setIngresoPromedio(int ingreso) {
		this.ingresoPromedio = ingreso;
	}

	public int getIngresoPromedio() {
		return ingresoPromedio;
	}

	public void setRutEmpleador(String rut) {
		this.rutEmpleador = rut.trim();
	}

	public String getRutEmpleador() {
		return rutEmpleador;
	}

	public void setNombreEmpleador(String nombre) {
		this.nombreEmpleador = nombre.trim();
	}

	public String getNombreEmpleador() {
		return nombreEmpleador;
	}

	public void setActividadEconomicaEmpleador(int actividadEconomica) {
		this.actividadEconomicaEmpleador = actividadEconomica;
	}

	public int getActividadEconomicaEmpleador() {
		return actividadEconomicaEmpleador;
	}

	public void setRegionEmpleador(int region) {
		this.regionEmpleador = region;
	}

	public int getRegionEmpleador() {
		return regionEmpleador;
	}

	public void setComunaEmpleador(int comuna) {
		this.comunaEmpleador = comuna;
	}

	public int getComunaEmpleador() {
		return comunaEmpleador;
	}

	public void setCodigoEntidadAdministradora(int codigoEntidad) {
		this.codigoEntidadAdministradora = codigoEntidad;
	}

	public int getCodigoEntidadAdministradora() {
		return codigoEntidadAdministradora;
	}

	public void setIdTipoBeneficio(int idTipoBeneficio) {
		this.idTipoBeneficio = idTipoBeneficio;
	}

	public int getIdTipoBeneficio() {
		return idTipoBeneficio;
	}

	public void setFechaReconocimientoCausante(String fecha) {
		this.fechaReconocimientoCausante = fecha.trim();
	}

	public String getFechaReconocimientoCausante() {
		return fechaReconocimientoCausante;
	}

	public void setFechaPagoBeneficio(String fecha) {
		this.fechaPagoBeneficio = fecha.trim();
	}

	public String getFechaPagoBeneficio() {
		return fechaPagoBeneficio;
	}

	public void setMontoUnitarioBeneficio(int monto) {
		this.montoUnitarioBeneficio = monto;
	}

	public int getMontoUnitarioBeneficio() {
		return montoUnitarioBeneficio;
	}

	public void setPuntajeFichaProtSocial(int puntaje) {
		this.puntajeFichaProtSocial = puntaje;
	}

	public int getPuntajeFichaProtSocial() {
		return puntajeFichaProtSocial;
	}

	public void setTramoASFAM(int tramo) {
		this.tramoASFAM = tramo;
	}

	public int getTramoASFAM() {
		return tramoASFAM;
	}

	public void setFechaExtincionCausante(String fecha) {
		this.fechaExtincionCausante = fecha.trim();
	}

	public String getFechaExtincionCausante() {
		return fechaExtincionCausante;
	}

	public void setCausaExtincionCausante(int causa) {
		this.causaExtincionCausante = causa;
	}

	public int getCausaExtincionCausante() {
		return causaExtincionCausante;
	}

}
