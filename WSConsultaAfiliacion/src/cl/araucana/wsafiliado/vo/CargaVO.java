/**
 * 
 */
package cl.araucana.wsafiliado.vo;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Claudio Lillo
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "cargaWS",
		propOrder = { "rutCarga", "nombreCarga", "fechaNacimientoCarga", "sexoCarga", "fechaVencimientoCarga", "parentesco", "estadoCarga" })
@XmlRootElement(name="CARGA")
public class CargaVO {
	@XmlElement(name="RUT_CARGA", required=true)
	private String rutCarga;
	@XmlElement(name="NOMBRE_TITULAR", required=true)
	private String nombreCarga;
	@XmlElement(name="FECHA_NACIMINETO_CARGA", required=true)
	private String fechaNacimientoCarga;
	@XmlElement(name="SEXO_CARGA", required=true)
	private String sexoCarga;
	@XmlElement(name="FECHA_VENCIMIENTO_CARGA", required=true)
	private String fechaVencimientoCarga;
	@XmlElement(name="PARENTESCO", required=true)
	private String parentesco;
	@XmlElement(name="ESTADO_CARGA", required=true)
	private String estadoCarga;

	/**
	 * @return the rutCarga
	 */
	public String getRutCarga() {
		return rutCarga;
	}
	/**
	 * @param rutCarga the rutCarga to set
	 */
	public void setRutCarga(String rutCarga) {
		this.rutCarga = rutCarga;
	}
	/**
	 * @return the nombreCarga
	 */
	public String getNombreCarga() {
		return nombreCarga;
	}
	/**
	 * @param nombreCarga the nombreCarga to set
	 */
	public void setNombreCarga(String nombreCarga) {
		this.nombreCarga = nombreCarga;
	}
	/**
	 * @return the fechaNacimientoCarga
	 */
	public String getFechaNacimientoCarga() {
		return fechaNacimientoCarga;
	}
	/**
	 * @param fechaNacimientoCarga the fechaNacimientoCarga to set
	 */
	public void setFechaNacimientoCarga(String fechaNacimientoCarga) {
		this.fechaNacimientoCarga = fechaNacimientoCarga;
	}
	/**
	 * @return the sexoCarga
	 */
	public String getSexoCarga() {
		return sexoCarga;
	}
	/**
	 * @param sexoCarga the sexoCarga to set
	 */
	public void setSexoCarga(String sexoCarga) {
		this.sexoCarga = sexoCarga;
	}
	/**
	 * @return the fechaVencimientoCarga
	 */
	public String getFechaVencimientoCarga() {
		return fechaVencimientoCarga;
	}
	/**
	 * @param fechaVencimientoCarga the fechaVencimientoCarga to set
	 */
	public void setFechaVencimientoCarga(String fechaVencimientoCarga) {
		this.fechaVencimientoCarga = fechaVencimientoCarga;
	}
	/**
	 * @return the parentesco
	 */
	public String getParentesco() {
		return parentesco;
	}
	/**
	 * @param parentesco the parentesco to set
	 */
	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}
	/**
	 * @return the estadoCarga
	 */
	public String getEstadoCarga() {
		return estadoCarga;
	}
	/**
	 * @param estadoCarga the estadoCarga to set
	 */
	public void setEstadoCarga(String estadoCarga) {
		this.estadoCarga = estadoCarga;
	}
	
}

