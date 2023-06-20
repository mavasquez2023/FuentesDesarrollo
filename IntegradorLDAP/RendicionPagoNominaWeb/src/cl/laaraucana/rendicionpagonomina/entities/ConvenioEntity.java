package cl.laaraucana.rendicionpagonomina.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dbo.CONVENIO", schema = "TEFDTA")
public class ConvenioEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IdCodConv")
	private String idConvenio;
	@Column(name = "DesConv")
	private String descripcionConvenio;
	@Column(name = "CodBanco")
	private String codigoBanco;
	@Column(name = "mailAd")
	private String mailAdmin;
	@Column(name = "IdConvBan")
	private long idConvenioBanco;
	@Column(name = "Plantilla")
	private long plantilla;
	@Column(name = "PlantillaEnvio")
	private long plantillaEnvio;
	@Column(name = "CtaCorriente")
	private long cuentaCorriente;
	@Column(name = "FechCreacion")
	private Date fechaCreacion;
	@Column(name = "UsuCreacion")
	private String usuarioCreacion;
	/**
	 * @return the idConvenio
	 */
	public String getIdConvenio() {
		return idConvenio;
	}
	/**
	 * @param idConvenio the idConvenio to set
	 */
	public void setIdConvenio(String idConvenio) {
		this.idConvenio = idConvenio;
	}
	/**
	 * @return the descripcionConvenio
	 */
	public String getDescripcionConvenio() {
		return descripcionConvenio;
	}
	/**
	 * @param descripcionConvenio the descripcionConvenio to set
	 */
	public void setDescripcionConvenio(String descripcionConvenio) {
		this.descripcionConvenio = descripcionConvenio;
	}
	/**
	 * @return the codigoBanco
	 */
	public String getCodigoBanco() {
		return codigoBanco;
	}
	/**
	 * @param codigoBanco the codigoBanco to set
	 */
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}
	/**
	 * @return the mailAdmin
	 */
	public String getMailAdmin() {
		return mailAdmin;
	}
	/**
	 * @param mailAdmin the mailAdmin to set
	 */
	public void setMailAdmin(String mailAdmin) {
		this.mailAdmin = mailAdmin;
	}
	/**
	 * @return the idConvenioBanco
	 */
	public long getIdConvenioBanco() {
		return idConvenioBanco;
	}
	/**
	 * @param idConvenioBanco the idConvenioBanco to set
	 */
	public void setIdConvenioBanco(long idConvenioBanco) {
		this.idConvenioBanco = idConvenioBanco;
	}
	/**
	 * @return the plantilla
	 */
	public long getPlantilla() {
		return plantilla;
	}
	/**
	 * @param plantilla the plantilla to set
	 */
	public void setPlantilla(long plantilla) {
		this.plantilla = plantilla;
	}
	/**
	 * @return the cuentaCorriente
	 */
	public long getCuentaCorriente() {
		return cuentaCorriente;
	}
	/**
	 * @param cuentaCorriente the cuentaCorriente to set
	 */
	public void setCuentaCorriente(long cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}
	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * @return the usuarioCreacion
	 */
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	/**
	 * @param usuarioCreacion the usuarioCreacion to set
	 */
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	/**
	 * @return the plantillaEnvio
	 */
	public long getPlantillaEnvio() {
		return plantillaEnvio;
	}
	/**
	 * @param plantillaEnvio the plantillaEnvio to set
	 */
	public void setPlantillaEnvio(long plantillaEnvio) {
		this.plantillaEnvio = plantillaEnvio;
	}

	
}
