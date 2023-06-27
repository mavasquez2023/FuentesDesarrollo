/**
 * 
 */
package publicacion;

/**
 * @author Usist24
 *
 */
public class BitacoraTO {
private String periodo;
private String holding;
private String usuario;
private String tipo_documento;
private String accion;
private String ip;
private String rango;
/**
 * @return el accion
 */
public String getAccion() {
	return accion;
}
/**
 * @param accion el accion a establecer
 */
public void setAccion(String accion) {
	this.accion = accion;
}
/**
 * @return el periodo
 */
public String getPeriodo() {
	return periodo;
}
/**
 * @param periodo el periodo a establecer
 */
public void setPeriodo(String periodo) {
	this.periodo = periodo;
}
/**
 * @return el tipo_documento
 */
public String getTipo_documento() {
	return tipo_documento;
}
/**
 * @param tipo_documento el tipo_documento a establecer
 */
public void setTipo_documento(String tipo_documento) {
	if(tipo_documento.length()>30){
		tipo_documento= tipo_documento.substring(0, 30);
	}
	this.tipo_documento = tipo_documento;
}
/**
 * @return el usuario
 */
public String getUsuario() {
	return usuario;
}
/**
 * @param usuario el usuario a establecer
 */
public void setUsuario(String usuario) {
	if(usuario.length()>30){
		usuario= usuario.substring(0, 30);
	}
	this.usuario = usuario;
}
/**
 * @return el holding
 */
public String getHolding() {
	return holding;
}
/**
 * @param holding el holding a establecer
 */
public void setHolding(String holding) {
	if(holding.length()>10){
		holding= holding.substring(0, 10);
	}
	this.holding = holding;
}
/**
 * @return el ip
 */
public String getIp() {
	return ip;
}
/**
 * @param ip el ip a establecer
 */
public void setIp(String ip) {
	this.ip = ip;
}
/**
 * @return el rango
 */
public String getRango() {
	return rango;
}
/**
 * @param rango el rango a establecer
 */
public void setRango(String rango) {
	this.rango = rango;
}
}
