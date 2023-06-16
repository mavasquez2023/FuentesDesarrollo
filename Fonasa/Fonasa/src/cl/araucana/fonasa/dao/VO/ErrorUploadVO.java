/**
 * 
 */
package cl.araucana.fonasa.dao.VO;

/**
 * @author IBM Software Factory
 *
 */
public class ErrorUploadVO {
private int numerolinea;
private String numeroFormulario;
private String observacion;
public int getNumerolinea() {
	return numerolinea;
}
public void setNumerolinea(int numerolinea) {
	this.numerolinea = numerolinea;
}
public String getNumeroFormulario() {
	return numeroFormulario;
}
public void setNumeroFormulario(String numeroFormulario) {
	this.numeroFormulario = numeroFormulario;
}
public String getObservacion() {
	return observacion;
}
public void setObservacion(String observacion) {
	this.observacion = observacion;
}



}
