/**
 * 
 */
package cl.laaraucana.reportesil.dao.vo;

/**
 * @author IBM Software Factory
 *
 */
public class FormularioCalculoSILVO {
	private ResumenLicenciaVO cabeceraLicencia;
	private RemuneracionesBCSIL remuneraciones;
	private MontoSubsidioDiarioVO montoDiario;
	private TopeSubsidioDiarioVO topeDiario;
	private int baseCalculo;
	/**
	 * @return the cabeceraLicencia
	 */
	public ResumenLicenciaVO getCabeceraLicencia() {
		return cabeceraLicencia;
	}
	/**
	 * @param cabeceraLicencia the cabeceraLicencia to set
	 */
	public void setCabeceraLicencia(ResumenLicenciaVO cabeceraLicencia) {
		this.cabeceraLicencia = cabeceraLicencia;
	}
	/**
	 * @return the remuneraciones
	 */
	public RemuneracionesBCSIL getRemuneraciones() {
		return remuneraciones;
	}
	/**
	 * @param remuneraciones the remuneraciones to set
	 */
	public void setRemuneraciones(RemuneracionesBCSIL remuneraciones) {
		this.remuneraciones = remuneraciones;
	}
	/**
	 * @return the montoDiario
	 */
	public MontoSubsidioDiarioVO getMontoDiario() {
		return montoDiario;
	}
	/**
	 * @param montoDiario the montoDiario to set
	 */
	public void setMontoDiario(MontoSubsidioDiarioVO montoDiario) {
		this.montoDiario = montoDiario;
	}
	/**
	 * @return the topeDiario
	 */
	public TopeSubsidioDiarioVO getTopeDiario() {
		return topeDiario;
	}
	/**
	 * @param topeDiario the topeDiario to set
	 */
	public void setTopeDiario(TopeSubsidioDiarioVO topeDiario) {
		this.topeDiario = topeDiario;
	}
	/**
	 * @return the baseCalculo
	 */
	public int getBaseCalculo() {
		return baseCalculo;
	}
	/**
	 * @param baseCalculo the baseCalculo to set
	 */
	public void setBaseCalculo(int baseCalculo) {
		this.baseCalculo = baseCalculo;
	}

	
}
