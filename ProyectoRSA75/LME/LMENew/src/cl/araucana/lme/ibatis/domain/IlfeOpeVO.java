/*
 * Created on 14-10-2011
 *
 */
package cl.araucana.lme.ibatis.domain;

/**
 * @author j-factory
 *
 */
public class IlfeOpeVO extends IlfeBaseVO{

	protected static final long serialVersionUID = -636008845156153717L;
	protected Integer stsOpe; //STSOPE
	protected String nomOpe; //NOMOPE

	protected String numLicencia; //NUMLIC
	protected String digLicencia; //DIGLIC

	protected String fechaEstado; //FECHAEST
	protected String horaEstado; //HORAEST

	//	/**
	//	 * @return Returns the fechaConsulta.
	//	 */
	//	public String getFechaConsulta() {
	//		return fechaConsulta;
	//	}
	//	/**
	//	 * @param fechaConsulta The fechaConsulta to set.
	//	 */
	//	public void setFechaConsulta(String fechaConsulta) {
	//		this.fechaConsulta = fechaConsulta;
	//	}
	//	/**
	//	 * @return Returns the hora.
	//	 */
	//	public String getHora() {
	//		return hora;
	//	}
	//	/**
	//	 * @param hora The hora to set.
	//	 */
	//	public void setHora(String hora) {
	//		this.hora = hora;
	//	}

	/**
	 * @return Returns the nomOpe.
	 */
	public String getNomOpe() {
		return nomOpe;
	}

	/**
	 * @param nomOpe The nomOpe to set.
	 */
	public void setNomOpe(String nomOpe) {
		this.nomOpe = nomOpe;
	}

	/**
	 * @return Returns the stsOpe.
	 */
	public Integer getStsOpe() {
		return stsOpe;
	}

	/**
	 * @param stsOpe The stsOpe to set.
	 */
	public void setStsOpe(Integer stsOpe) {
		this.stsOpe = stsOpe;
	}

	/**
	 * @return Returns the digLicencia.
	 */
	public String getDigLicencia() {
		return digLicencia;
	}

	/**
	 * @param digLicencia The digLicencia to set.
	 */
	public void setDigLicencia(String digLicencia) {
		this.digLicencia = digLicencia;
	}

	/**
	 * @return Returns the numLicencia.
	 */
	public String getNumLicencia() {
		return numLicencia;
	}

	/**
	 * @param numLicencia The numLicencia to set.
	 */
	public void setNumLicencia(String numLicencia) {
		this.numLicencia = numLicencia;
	}

	/**
	 * @return the fechaEstado
	 */
	public String getFechaEstado() {
		return fechaEstado;
	}

	/**
	 * @return the horaEstado
	 */
	public String getHoraEstado() {
		return horaEstado;
	}

	/**
	 * @param fechaEstado the fechaEstado to set
	 */
	public void setFechaEstado(String fechaEstado) {
		this.fechaEstado = fechaEstado;
	}

	/**
	 * @param horaEstado the horaEstado to set
	 */
	public void setHoraEstado(String horaEstado) {
		this.horaEstado = horaEstado;
	}
	
}