/**
 * 
 */
package cl.araucana.wssiagf.vo;

import java.io.Serializable;
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
			name = "dataResponseWS",
			propOrder = { "rutTrabajador", "rutEmpresa", "estado" })

	public class DataResponse implements Serializable{
		
	@XmlElement(name="RUT_TRABAJADOR", required=true)
	private String rutTrabajador;

	@XmlElement(name="RUT_EMPRESA", required=true)
	private String rutEmpresa;

	@XmlElement(name="ESTADO", required=true)
	private String estado;

	public String getRutTrabajador() {
		return rutTrabajador;
	}

	public void setRutTrabajador(String rutTrabajador) {
		this.rutTrabajador = rutTrabajador;
	}

	public String getRutEmpresa() {
		return rutEmpresa;
	}

	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}



}
