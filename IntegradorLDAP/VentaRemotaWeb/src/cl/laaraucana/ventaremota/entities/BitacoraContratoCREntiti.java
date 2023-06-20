package cl.laaraucana.ventaremota.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ContratoCanalesRemotos")
public class BitacoraContratoCREntiti implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "rutCliente")
	private long rutCliente;
	@Column(name = "dvCliente")
	private String dvCliente;
	@Column(name = "fechaAprobacion")
	private Date fechaAprobacion;
	@Column(name = "idChallenge")
	private String idChallenge;
	@Column(name = "ipAcceso")
	private String ipAcceso;
	@Column(name = "codigoRetorno")
	private String codigoRetorno;
	@Column(name = "resultadoValidacion")
	private String resultadoValidacion;
	/**
	 * @return the rutCliente
	 */
	public long getRutCliente() {
		return rutCliente;
	}
	/**
	 * @param rutcliente the rutCliente to set
	 */
	public void setRutCliente(long rutCliente) {
		this.rutCliente = rutCliente;
	}
	/**
	 * @return the dvCliente
	 */
	public String getDvCliente() {
		return dvCliente;
	}
	/**
	 * @param dvcliente the dvCliente to set
	 */
	public void setDvCliente(String dvCliente) {
		this.dvCliente = dvCliente;
	}
	/**
	 * @return the fechaAprobacion
	 */
	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}
	/**
	 * @param fechaAprobacion the fechaAprobacion to set
	 */
	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}
	/**
	 * @return the idChallenge
	 */
	public String getIdChallenge() {
		return idChallenge;
	}
	/**
	 * @param idChallenge the idChallenge to set
	 */
	public void setIdChallenge(String idChallenge) {
		this.idChallenge = idChallenge;
	}
	/**
	 * @return the ipAcceso
	 */
	public String getIpAcceso() {
		return ipAcceso;
	}
	/**
	 * @param ipAcceso the ipAcceso to set
	 */
	public void setIpAcceso(String ipAcceso) {
		this.ipAcceso = ipAcceso;
	}
	/**
	 * @return the codigoRetorno
	 */
	public String getCodigoRetorno() {
		return codigoRetorno;
	}
	/**
	 * @param codigoRetorno the codigoRetorno to set
	 */
	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}
	/**
	 * @return the resultadoValidacion
	 */
	public String getResultadoValidacion() {
		return resultadoValidacion;
	}
	/**
	 * @param resultadoValidacion the resultadoValidacion to set
	 */
	public void setResultadoValidacion(String resultadoValidacion) {
		this.resultadoValidacion = resultadoValidacion;
	}
	
	
}
