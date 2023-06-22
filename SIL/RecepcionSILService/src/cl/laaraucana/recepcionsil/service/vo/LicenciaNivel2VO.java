/**
 * 
 */
package cl.laaraucana.recepcionsil.service.vo;

import java.io.Serializable;


/**
 * @author J-Factory Solutions
 * @Nivel 2
 */
public class LicenciaNivel2VO implements Serializable{

	private String nroLicencia;
	private String nroLicenciaExterno;
	private String rutAfiliado;
	private String mixtaOFormulario;
	private String estadoLicencia;
	private TipoVO tipo;
	private ReposoVO reposo;
	private ProfesionalVO profesional;
	private EmpleadorVO empleador;
	private EntidadPrevisionalVO entidadPrevisional;
	private RemuneracionesVO remuneraciones;


	/**
	 * @return the nroLicencia
	 */
	public String getNroLicencia() {
		return nroLicencia;
	}
	/**
	 * @param nroLicencia the nroLicencia to set
	 */
	public void setNroLicencia(String nroLicencia) {
		this.nroLicencia = nroLicencia;
	}
	
	/**
	 * @return the nroLicenciaExterno
	 */
	public String getNroLicenciaExterno() {
		return nroLicenciaExterno;
	}
	/**
	 * @param nroLicenciaExterno the nroLicenciaExterno to set
	 */
	public void setNroLicenciaExterno(String nroLicenciaExterno) {
		this.nroLicenciaExterno = nroLicenciaExterno;
	}
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
	 * @return the mixtaOFormulario
	 */
	public String getMixtaOFormulario() {
		return mixtaOFormulario;
	}
	/**
	 * @param mixtaOFormulario the mixtaOFormulario to set
	 */
	public void setMixtaOFormulario(String mixtaOFormulario) {
		this.mixtaOFormulario = mixtaOFormulario;
	}
	/**
	 * @return the tipo
	 */
	public TipoVO getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TipoVO tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the reposo
	 */
	public ReposoVO getReposo() {
		return reposo;
	}
	/**
	 * @param reposo the reposo to set
	 */
	public void setReposo(ReposoVO reposo) {
		this.reposo = reposo;
	}
	/**
	 * @return the profesional
	 */
	public ProfesionalVO getProfesional() {
		return profesional;
	}
	/**
	 * @param profesional the profesional to set
	 */
	public void setProfesional(ProfesionalVO profesional) {
		this.profesional = profesional;
	}
	/**
	 * @return the empleador
	 */
	public EmpleadorVO getEmpleador() {
		return empleador;
	}
	/**
	 * @param empleador the empleador to set
	 */
	public void setEmpleador(EmpleadorVO empleador) {
		this.empleador = empleador;
	}
	/**
	 * @return the entidadPrevisional
	 */
	public EntidadPrevisionalVO getEntidadPrevisional() {
		return entidadPrevisional;
	}
	/**
	 * @param entidadPrevisional the entidadPrevisional to set
	 */
	public void setEntidadPrevisional(EntidadPrevisionalVO entidadPrevisional) {
		this.entidadPrevisional = entidadPrevisional;
	}
	/**
	 * @return the remuneraciones
	 */
	public RemuneracionesVO getRemuneraciones() {
		return remuneraciones;
	}
	/**
	 * @param remuneraciones the remuneraciones to set
	 */
	public void setRemuneraciones(RemuneracionesVO remuneraciones) {
		this.remuneraciones = remuneraciones;
	}
	public String getEstadoLicencia() {
		return estadoLicencia;
	}
	public void setEstadoLicencia(String estadoLicencia) {
		this.estadoLicencia = estadoLicencia;
	}

	


}
