/**
 * 
 */
package cl.araucana.wsempresa.vo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Claudio Lillo
 *
 */
@XmlType(
		name = "responseWS",
		propOrder = { "estado", "razonSocial", "glosaError"})
public class ResponseWS implements Serializable{


private int estado;

private String razonSocial;

private String glosaError;
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
 * @return the glosaError
 */
public String getGlosaError() {
	return glosaError;
}
/**
 * @param glosaError the glosaError to set
 */
public void setGlosaError(String glosaError) {
	this.glosaError = glosaError;
}


}
