package cl.laaraucana.saldoafavor.vo;

import java.util.Date;

public class RegistroVo {

	private long id;
	private int rutConsultado;
	private String dvConsultado;
	private Date fechaConsulta;
	private Date horaConsulta;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the rutConsultado
	 */
	public int getRutConsultado() {
		return rutConsultado;
	}
	/**
	 * @param rutConsultado the rutConsultado to set
	 */
	public void setRutConsultado(int rutConsultado) {
		this.rutConsultado = rutConsultado;
	}
	/**
	 * @return the dvConsultado
	 */
	public String getDvConsultado() {
		return dvConsultado;
	}
	/**
	 * @param dvConsultado the dvConsultado to set
	 */
	public void setDvConsultado(String dvConsultado) {
		this.dvConsultado = dvConsultado;
	}
	/**
	 * @return the fechaConsulta
	 */
	public Date getFechaConsulta() {
		return fechaConsulta;
	}
	/**
	 * @param fechaConsulta the fechaConsulta to set
	 */
	public void setFechaConsulta(Date fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}
	/**
	 * @return the horaConsulta
	 */
	public Date getHoraConsulta() {
		return horaConsulta;
	}
	/**
	 * @param horaConsulta the horaConsulta to set
	 */
	public void setHoraConsulta(Date horaConsulta) {
		this.horaConsulta = horaConsulta;
	}

}
