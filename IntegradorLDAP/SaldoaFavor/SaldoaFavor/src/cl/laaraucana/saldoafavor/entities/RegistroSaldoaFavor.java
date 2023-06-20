package cl.laaraucana.saldoafavor.entities;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "RegistroSaldoaFavor")
public class RegistroSaldoaFavor implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "rutConsultado")
	private String rut;
	@Column(name = "dvConsultado")
	private String dvConsultado;
	@Column(name = "fechaConsulta")
	private Date fechaConsulta;
	@Column(name = "horaConsulta")
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
	public String getRut() {
		return rut;
	}
	/**
	 * @param rutConsultado the rut to set
	 */
	public void setRut(String rut) {
		this.rut = rut;
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
