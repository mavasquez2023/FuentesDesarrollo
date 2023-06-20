/**
 * 
 */
package cl.araucana.bonomarzo.vo;

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
		name = "responseWS",
		propOrder = { "respuesta", "id_consulta", "rut_cliente", "trabajador"})
public class ResponseWS implements Serializable{
	@XmlElement(name="RESPUESTA", required=true)
	private int respuesta;
	@XmlElement(name="ID_CONSULTA", required=true)
	private String id_consulta;
	@XmlElement(name="RUT_CLIENTE", required=true)
	private String rut_cliente;
	@XmlElement(name="TRABAJADOR", required=true)
	private TrabajadorVO trabajador;
	/**
	 * @return the respuesta
	 */
	public int getRespuesta() {
		return respuesta;
	}
	/**
	 * @param respuesta the respuesta to set
	 */
	public void setRespuesta(int respuesta) {
		this.respuesta = respuesta;
	}
	/**
	 * @return the id_consulta
	 */
	public String getId_consulta() {
		return id_consulta;
	}
	/**
	 * @param id_consulta the id_consulta to set
	 */
	public void setId_consulta(String id_consulta) {
		this.id_consulta = id_consulta;
	}
	/**
	 * @return the rut_cliente
	 */
	public String getRut_cliente() {
		return rut_cliente;
	}
	/**
	 * @param rut_cliente the rut_cliente to set
	 */
	public void setRut_cliente(String rut_cliente) {
		this.rut_cliente = rut_cliente;
	}
	/**
	 * @return the trabajador
	 */
	public TrabajadorVO getTrabajador() {
		return trabajador;
	}
	/**
	 * @param trabajador the trabajador to set
	 */
	public void setTrabajador(TrabajadorVO trabajador) {
		this.trabajador = trabajador;
	}

	
}
