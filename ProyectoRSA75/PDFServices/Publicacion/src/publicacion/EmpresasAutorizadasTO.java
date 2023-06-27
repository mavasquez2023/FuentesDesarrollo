/**
 * 
 */
package publicacion;

/**
 * @author Usist24
 *
 */
public class EmpresasAutorizadasTO {
private String rut;
private int rutint;
private String razonSocial;
public String getRazonSocial() {
	return razonSocial;
}
public void setRazonSocial(String razonSocial) {
	this.razonSocial = razonSocial;
}
public String getRut() {
	return rut;
}
public void setRut(String rut) {
	this.rut = rut;
	int pos= rut.indexOf('-');
	if(pos>-1){
		setRutint(Integer.parseInt(rut.substring(0, rut.indexOf('-'))));
	}
}
public int getRutint() {
	return rutint;
}
public void setRutint(int rutint) {
	this.rutint = rutint;
}
}
