/**
 * 
 */
package cl.araucana.wsafiliado.vo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author J-Factory (Claudio Lillo)
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "dataWS",
		propOrder = { "nombre", "estado", "segmento", "tipo", "codigo_respuesta", "observacion" })

public class ResponseDataWS implements Serializable{

	@XmlElement(name="NOMBRE", required=false)
	private String nombre;
	
	@XmlElement(name="ESTADO", required=true)
	private int estado;
	
	//@XmlElementWrapper(name="SEGMENTOS")
	@XmlElement(name="SEGMENTO", required=false)
	private SegmentoVO segmento;
	
	@XmlElement(name="TIPO", required=false)
	private String tipo;
	
	@XmlElement(name="CODIGO_RESPUESTA", required=true)
	private int codigo_respuesta;
	
	@XmlElement(name="OBSERVACION", required=false)
	private String observacion;

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}

	/**
	 * @return the segmento
	 */
	public SegmentoVO getSegmento() {
		return segmento;
	}

	/**
	 * @param segmento the segmento to set
	 */
	public void setSegmento(SegmentoVO segmento) {
		this.segmento = segmento;
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

	/**
	 * @return the codigo_respuesta
	 */
	public int getCodigo_respuesta() {
		return codigo_respuesta;
	}

	/**
	 * @param codigo_respuesta the codigo_respuesta to set
	 */
	public void setCodigo_respuesta(int codigo_respuesta) {
		this.codigo_respuesta = codigo_respuesta;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

		
}
