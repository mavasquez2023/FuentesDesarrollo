/**
 * 
 */
package cl.araucana.wssiagf.vo;

import java.io.Serializable;
import java.util.List;

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
		name = "responseWS")

	public class ResponseWS implements Serializable{
	
	@XmlElement(name="CODIGO", required=true)
	private int codigo;
	
	@XmlElement(name="DESCRIPCION", required=true)
	private String descripcion;
		
	@XmlElement(name="RESPUESTA", required=false)
	private List<DataResponse> data;
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<DataResponse> getData() {
		return data;
	}

	public void setData(List<DataResponse> data) {
		this.data = data;
	}

	

}
