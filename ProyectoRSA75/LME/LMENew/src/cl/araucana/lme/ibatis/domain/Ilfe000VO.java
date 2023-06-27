/*
 * Created on 14-10-2011
 *
 */
package cl.araucana.lme.ibatis.domain;

/**
 * @author jcea
 *
 */
public class Ilfe000VO extends IlfeBaseVO {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1316517568635898247L;
	protected String fechaConsulta; //	FECCON
	protected String hora; //HORCON
	protected Integer numLicencia; //NUMLIC
	protected String digLicencia; //DIGLIC
	private String marca; //MARCA
	private String marcons;
	private String horaEstado;
	private String fechaEstado;

	public String getHoraEstado() {
		return horaEstado;
	}

	public void setHoraEstado(String horaEstado) {
		this.horaEstado = horaEstado;
	}

	public String getFechaEstado() {
		return fechaEstado;
	}

	public void setFechaEstado(String fechaEstado) {
		this.fechaEstado = fechaEstado;
	}

	public String getMarcons() {
		return marcons;
	}

	public void setMarcons(String marcons) {
		this.marcons = marcons;
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
	 * @return Returns the fechaConsulta.
	 */
	public String getFechaConsulta() {
		return fechaConsulta;
	}

	/**
	 * @param fechaConsulta The fechaConsulta to set.
	 */
	public void setFechaConsulta(String fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	/**
	 * @return Returns the hora.
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * @param hora The hora to set.
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * @return Returns the marca.
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca The marca to set.
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return Returns the numLicencia.
	 */
	public Integer getNumLicencia() {
		return numLicencia;
	}

	/**
	 * @param numLicencia The numLicencia to set.
	 */
	public void setNumLicencia(Integer numLicencia) {
		this.numLicencia = numLicencia;
	}

	public IlfeOpeVO toIlfeOpeVO() {
		IlfeOpeVO o = new IlfeOpeVO();
		o.setCodOpe(this.codOpe);
		//		o.setNumImpre(this.numImpre);
		//		o.setNumLicencia(this.numLicencia);
		o.setDigLicencia(this.digLicencia);
		o.setCodCcaf(this.codCcaf);
		o.setPwdCcaf(this.pwdCcaf);
		o.setIdeOpe(this.ideOpe);

		return o;
	}
}
