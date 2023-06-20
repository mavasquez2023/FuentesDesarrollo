/**
 * 
 */
package cl.laaraucana.licenciagestion.vo;

/**
 * @author IBM Software Factory
 *
 */
public class OficinasLicenciasVO {
private String sucursal;
private int percentil;
private String rango;
private int cantidad;


/**
 * @return the sucursal
 */
public String getSucursal() {
	return sucursal;
}
/**
 * @param sucursal the sucursal to set
 */
public void setSucursal(String sucursal) {
	this.sucursal = sucursal;
}
/**
 * @return the percentil
 */
public int getPercentil() {
	return percentil;
}
/**
 * @param percentil the percentil to set
 */
public void setPercentil(int percentil) {
	this.percentil = percentil;
}
/**
 * @return the rango
 */
public String getRango() {
	return rango;
}
/**
 * @param rango the rango to set
 */
public void setRango(String rango) {
	this.rango = rango;
}
/**
 * @return the cantidad
 */
public int getCantidad() {
	return cantidad;
}
/**
 * @param cantidad the cantidad to set
 */
public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}



}