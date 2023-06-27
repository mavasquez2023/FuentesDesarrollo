/**
 * 
 */
package cl.araucana.cheque.to;

/**
 * @author usist199
 *
 */
public class ParamURL {
private int folio;
private String cola;
private String dispositivo;
private String cajero;
private String ipRemota;
private String username;
/**
 * @return el cola
 */
public String getCola() {
	return cola;
}
/**
 * @param cola el cola a establecer
 */
public void setCola(String cola) {
	this.cola = cola;
}
/**
 * @return el dispositivo
 */
public String getDispositivo() {
	return dispositivo;
}
/**
 * @param dispositivo el dispositivo a establecer
 */
public void setDispositivo(String dispositivo) {
	this.dispositivo = dispositivo;
}
/**
 * @return el cajero
 */
public String getCajero() {
	return cajero;
}
/**
 * @param cajero el cajero a establecer
 */
public void setCajero(String cajero) {
	this.cajero = cajero;
}

public String getRuta(){
	return "/" + cola + "/" + dispositivo + "/" + cajero + "/";
}
/**
 * @return el ipRemota
 */
public String getIpRemota() {
	return ipRemota;
}
/**
 * @param ipRemota el ipRemota a establecer
 */
public void setIpRemota(String ipRemota) {
	this.ipRemota = ipRemota;
}
/**
 * @return el username
 */
public String getUsername() {
	return username;
}
/**
 * @param username el username a establecer
 */
public void setUsername(String username) {
	if(username==null){
		username="";
	}
	this.username = username;
}
/**
 * @return el folio
 */
public int getFolio() {
	return folio;
}
/**
 * @param folio el folio a establecer
 */
public void setFolio(int folio) {
	this.folio = folio;
}
}
