/**
 * 
 */
package cl.araucana.wsafiliado.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Claudio Lillo
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "titularWS",
		propOrder = { "rutTitular", "nombreTitular", "fechaNacimiento", "sexo", "codigo_titular", "empleador" })
@XmlRootElement(name="TITULAR")
public class TitularVO {
	@XmlElement(name="RUT_TITULAR", required=true)
	private String rutTitular;
	@XmlElement(name="NOMBRE_TITULAR", required=true)
	private String nombreTitular;
	@XmlElement(name="FECHA_NACIMINETO", required=true)
	private String fechaNacimiento;
	@XmlElement(name="SEXO", required=true)
	private String sexo;
	@XmlElementWrapper(name="EMPRESAS_ENTIDADES")
	@XmlElement(name="EMPRESA_ENTIDAD", required=true)
	private List<EmpresaVO> empleador;
	@XmlElement(name="CODIGO_TITULAR", required=true)
	private int codigo_titular;
	/**
	 * @return the rutTitular
	 */
	public String getRutTitular() {
		return rutTitular;
	}
	/**
	 * @param rutTitular the rutTitular to set
	 */
	public void setRutTitular(String rutTitular) {
		this.rutTitular = rutTitular;
	}
	/**
	 * @return the nombreTitular
	 */
	public String getNombreTitular() {
		return nombreTitular;
	}
	/**
	 * @param nombreTitular the nombreTitular to set
	 */
	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}
	/**
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}
	/**
	 * @param sexo the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	/**
	 * @return the empleador
	 */
	public List<EmpresaVO> getEmpleador() {
		return empleador;
	}
	/**
	 * @param empleador the empleador to set
	 */
	public void setEmpleador(List<EmpresaVO> empleador) {
		this.empleador = empleador;
	}
	/**
	 * @return the codigo_titular
	 */
	public int getCodigo_titular() {
		return codigo_titular;
	}
	/**
	 * @param codigo_titular the codigo_titular to set
	 */
	public void setCodigo_titular(int codigo_titular) {
		this.codigo_titular = codigo_titular;
	}

	
	
	}
