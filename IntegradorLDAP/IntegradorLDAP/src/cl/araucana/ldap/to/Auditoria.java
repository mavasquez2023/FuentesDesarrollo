/**
 * 
 */
package cl.araucana.ldap.to;

import java.util.Date;

import cl.araucana.ldap.util.Utiles;

/**
 * @author IBM Software Factory
 *
 */
public class Auditoria {
private String rutEjecutivo;
private String rutUsuario;
private String rutEmpresa;
private String accion;
private String tipoEjecutivo;
private String envioSMS; 
private String envioEMAIL;
private Date fechaCreacion; 
private Date horaCreacion;
private String fecha;
private String hora;
/**
 * @return the rutEjecutivo
 */
public String getRutEjecutivo() {
	return rutEjecutivo;
}
/**
 * @param rutEjecutivo the rutEjecutivo to set
 */
public void setRutEjecutivo(String rutEjecutivo) {
	this.rutEjecutivo = rutEjecutivo;
}
/**
 * @return the rutUsuario
 */
public String getRutUsuario() {
	return rutUsuario;
}
/**
 * @param rutUsuario the rutUsuario to set
 */
public void setRutUsuario(String rutUsuario) {
	this.rutUsuario = rutUsuario;
}
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
 * @return the accion
 */
public String getAccion() {
	String nombreAccion=accion;
	if(accion.equals("CP")){
		nombreAccion="Clave Persona";
	}else if(accion.equals("RE")){
		nombreAccion="Rol Encargado Empresa";
	}else if(accion.equals("RA")){
		nombreAccion="Rol Encargado Anexo";
	}
	return nombreAccion;
}
/**
 * @param accion the accion to set
 */
public void setAccion(String accion) {
	this.accion = accion;
}
/**
 * @return the tipoEjecutivo
 */
public String getTipoEjecutivo() {
	String nombreEjecutivo=tipoEjecutivo;
	if(tipoEjecutivo.equals("EJE")){
		nombreEjecutivo="Ejecutivo";
	}else if(tipoEjecutivo.equals("ADM")){
		nombreEjecutivo="Administrador";
	}else if(tipoEjecutivo.equals("AGE")){
		nombreEjecutivo="Agente";
	}
	return nombreEjecutivo;
}
/**
 * @param tipoEjecutivo the tipoEjecutivo to set
 */
public void setTipoEjecutivo(String tipoEjecutivo) {
	this.tipoEjecutivo = tipoEjecutivo;
}
/**
 * @return the envioSMS
 */
public String getEnvioSMS() {
	return envioSMS;
}
/**
 * @param envioSMS the envioSMS to set
 */
public void setEnvioSMS(String envioSMS) {
	this.envioSMS = envioSMS;
}
/**
 * @return the envioEMAIL
 */
public String getEnvioEMAIL() {
	return envioEMAIL;
}
/**
 * @param envioEMAIL the envioEMAIL to set
 */
public void setEnvioEMAIL(String envioEMAIL) {
	this.envioEMAIL = envioEMAIL;
}
/**
 * @return the fechaCreacion
 */
public Date getFechaCreacion() {
	return fechaCreacion;
}
/**
 * @param fechaCreacion the fechaCreacion to set
 */
public void setFechaCreacion(Date fechaCreacion) {
	this.fechaCreacion = fechaCreacion;
}
/**
 * @return the horaCreacion
 */
public Date getHoraCreacion() {
	return horaCreacion;
}
/**
 * @param horaCreacion the horaCreacion to set
 */
public void setHoraCreacion(Date horaCreacion) {
	this.horaCreacion = horaCreacion;
}
/**
 * @return the fecha
 */
public String getFecha() {
	return Utiles.dateToString(this.fechaCreacion);
}
/**
 * @param fecha the fecha to set
 */
public void setFecha(String fecha) {
	this.fecha = fecha;
}
/**
 * @return the hora
 */
public String getHora() {
	return Utiles.timeToString(this.horaCreacion);
}
/**
 * @param hora the hora to set
 */
public void setHora(String hora) {
	this.hora = hora;
}


}
