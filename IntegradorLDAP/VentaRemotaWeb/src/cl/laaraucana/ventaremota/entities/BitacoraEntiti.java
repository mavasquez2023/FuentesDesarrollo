package cl.laaraucana.ventaremota.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bitacoraVetaRemota")
public class BitacoraEntiti implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idBita")
	private long idBita;
	@Column(name = "rutcliente")
	private long rutcliente;
	@Column(name = "dvcliente")
	private String dvcliente;
	@Column(name = "numeroOferta")
	private long numeroOferta;
	@Column(name = "folioCredito")
	private String folioCredito;
	@Column(name = "IdAprobRech")
	private String IdAprobRech;
	@Column(name = "fechaAprobRech")
	private Date fechaAprobRech;
	@Column(name = "estadoOfertaCrm")
	private String estadoOfertaCrm;
	@Column(name = "numeroOfertaCrm")
	private String numeroOfertaCrm;
	@Column(name = "idChallenge")
	private String idChallenge;
	@Column(name = "ipAcceso")
	private String ipAcceso;
	@Column(name = "codigoRetorno")
	private String codigoRetorno;
	@Column(name = "resultadoValidacion")
	private String resultadoValidacion;
	
	
	public long getIdBita() {
		return idBita;
	}

	public void setIdBita(long idBita) {
		this.idBita = idBita;
	}

	public long getRutcliente() {
		return rutcliente;
	}

	public void setRutcliente(long rutcliente) {
		this.rutcliente = rutcliente;
	}

	public String getDvcliente() {
		return dvcliente;
	}

	public void setDvcliente(String dvcliente) {
		this.dvcliente = dvcliente;
	}

	public long getNumeroOferta() {
		return numeroOferta;
	}

	public void setNumeroOferta(long numeroOferta) {
		this.numeroOferta = numeroOferta;
	}

	public String getFolioCredito() {
		return folioCredito;
	}

	public void setFolioCredito(String folioCredito) {
		this.folioCredito = folioCredito;
	}

	public String getIdAprobRech() {
		return IdAprobRech;
	}

	public void setIdAprobRech(String idAprobRech) {
		IdAprobRech = idAprobRech;
	}

	public Date getFechaAprobRech() {
		return fechaAprobRech;
	}

	public void setFechaAprobRech(Date fechaAprobRech) {
		this.fechaAprobRech = fechaAprobRech;
	}

	public String getEstadoOfertaCrm() {
		return estadoOfertaCrm;
	}

	public void setEstadoOfertaCrm(String estadoOfertaCrm) {
		this.estadoOfertaCrm = estadoOfertaCrm;
	}

	public String getNumeroOfertaCrm() {
		return numeroOfertaCrm;
	}

	public void setNumeroOfertaCrm(String numeroOfertaCrm) {
		this.numeroOfertaCrm = numeroOfertaCrm;
	}

	public String getIdChallenge() {
		return idChallenge;
	}

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
