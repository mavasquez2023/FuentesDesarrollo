/**
 * 
 */
package cl.araucana.cotcarserv.dao.VO;

/**
 * @author IBM Software Factory
 *
 */
public class ErrorUploadVO {
private int numerolinea;
private String rutTrabajador;
private String obervacion;
/**
 * @return the numerolinea
 */
public int getNumerolinea() {
	return numerolinea;
}
/**
 * @param numerolinea the numerolinea to set
 */
public void setNumerolinea(int numerolinea) {
	this.numerolinea = numerolinea;
}
/**
 * @return the rutTrabajador
 */
public String getRutTrabajador() {
	return rutTrabajador;
}
/**
 * @param rutTrabajador the rutTrabajador to set
 */
public void setRutTrabajador(String rutTrabajador) {
	this.rutTrabajador = rutTrabajador;
}
/**
 * @return the obervacion
 */
public String getObervacion() {
	return obervacion;
}
/**
 * @param obervacion the obervacion to set
 */
public void setObervacion(String obervacion) {
	this.obervacion = obervacion;
}


}
