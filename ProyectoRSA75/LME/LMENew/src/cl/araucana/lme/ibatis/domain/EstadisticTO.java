/**
 * 
 */
package cl.araucana.lme.ibatis.domain;

/**
 * @author usist199
 *
 */
public class EstadisticTO {
private String fechaEvento;
private String horaEvento;
private Integer codOperador;
private Integer total;
private Integer devolucion;
private Integer validacion;
private Integer zonaC;
private String dateEvento;
private String timeEvento;

/**
 * @return el codOperador
 */
public Integer getCodOperador() {
	return codOperador;
}
/**
 * @param codOperador el codOperador a establecer
 */
public void setCodOperador(Integer codOperador) {
	this.codOperador = codOperador;
}
/**
 * @return el devolucion
 */
public Integer getDevolucion() {
	return devolucion;
}
/**
 * @param devolucion el devolucion a establecer
 */
public void setDevolucion(Integer devolucion) {
	this.devolucion = devolucion;
}
/**
 * @return el fechaEvento
 */
public String getFechaEvento() {
	return fechaEvento;
}
/**
 * @param fechaEvento el fechaEvento a establecer
 */
public void setFechaEvento(String fechaEvento) {
	this.fechaEvento = fechaEvento;
}
/**
 * @return el horaEvento
 */
public String getHoraEvento() {
	return horaEvento;
}
/**
 * @param horaEvento el horaEvento a establecer
 */
public void setHoraEvento(String horaEvento) {
	this.horaEvento = horaEvento;
}
/**
 * @return el total
 */
public Integer getTotal() {
	return total;
}
/**
 * @param total el total a establecer
 */
public void setTotal(Integer total) {
	this.total = total;
}
/**
 * @return el validacion
 */
public Integer getValidacion() {
	return validacion;
}
/**
 * @param validacion el validacion a establecer
 */
public void setValidacion(Integer validacion) {
	this.validacion = validacion;
}
/**
 * @return el zonaC
 */
public Integer getZonaC() {
	return zonaC;
}
/**
 * @param zonaC el zonaC a establecer
 */
public void setZonaC(Integer zonaC) {
	this.zonaC = zonaC;
}
/**
 * @return el dateEvento
 */
public String getDateEvento() {
	return dateEvento;
}
/**
 * @param dateEvento el dateEvento a establecer
 */
public void setDateEvento(String dateEvento) {
	this.dateEvento = dateEvento;
}
/**
 * @return el timeEvento
 */
public String getTimeEvento() {
	return timeEvento;
}
/**
 * @param timeEvento el timeEvento a establecer
 */
public void setTimeEvento(String timeEvento) {
	this.timeEvento = timeEvento;
}


}
