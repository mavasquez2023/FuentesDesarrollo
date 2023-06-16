/**
 * 
 */
package cl.araucana.fonasa.dao.VO;

/**
 * @author IBM Software Factory
 *
 */
public class BitacoraVO {
private int totalValidados;
private int estadono72;
private int timeout;
private String observacion;
private String usuario;
private String nombreArchivo;
private String fecha;
private String horaInicio;
private String horaTermino;
public int getTotalValidados() {
	return totalValidados;
}
public void setTotalValidados(int totalValidados) {
	this.totalValidados = totalValidados;
}
public int getEstadono72() {
	return estadono72;
}
public void setEstadono72(int estadono72) {
	this.estadono72 = estadono72;
}
public int getTimeout() {
	return timeout;
}
public void setTimeout(int timeout) {
	this.timeout = timeout;
}
public String getObservacion() {
	return observacion;
}
public void setObservacion(String observacion) {
	this.observacion = observacion;
}
public String getUsuario() {
	return usuario;
}
public void setUsuario(String usuario) {
	this.usuario = usuario;
}
public String getNombreArchivo() {
	return nombreArchivo;
}
public void setNombreArchivo(String nombreArchivo) {
	this.nombreArchivo = nombreArchivo;
}
public String getFecha() {
	return fecha;
}
public void setFecha(String fecha) {
	this.fecha = fecha;
}
public String getHoraInicio() {
	return horaInicio;
}
public void setHoraInicio(String horaInicio) {
	this.horaInicio = horaInicio;
}
public String getHoraTermino() {
	return horaTermino;
}
public void setHoraTermino(String horaTermino) {
	this.horaTermino = horaTermino;
}


}
