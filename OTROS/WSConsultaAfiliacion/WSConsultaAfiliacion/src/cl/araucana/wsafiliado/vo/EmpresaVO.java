/**
 * 
 */
package cl.araucana.wsafiliado.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Claudio Lillo
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "empresaWS",
		propOrder = { "rutEmpresa", "razonSocial", "fechaAfiliacion", "estado", "tipo" })
public class EmpresaVO {
	@XmlElement(name="RUT", required=true)
	private String rutEmpresa;
	@XmlElement(name="RAZON_SOCIAL", required=true)
	private String razonSocial;
	@XmlElement(name="FECHA_AFILIACION", required=true)
	private String fechaAfiliacion;
	@XmlElement(name="ESTADO", required=true)
	private String estado;
	@XmlElement(name="TIPO", required=true)
	private String tipo;
	/**
	 * @return the rutEmpresa
	 */
	public String getRutEmpresa() {
		return rutEmpresa;
	}
	/**
	 * @param rutEmpresa the rutEmpresa to set
	 */
	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	/**
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}
	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	/**
	 * @return the fechaAfiliacion
	 */
	public String getFechaAfiliacion() {
		return fechaAfiliacion;
	}
	/**
	 * @param fechaAfiliacion the fechaAfiliacion to set
	 */
	public void setFechaAfiliacion(String fechaAfiliacion) {
		this.fechaAfiliacion = fechaAfiliacion;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
}
