/**
 * 
 */
package cl.araucana.wsafiliado.vo;

import java.util.List;

/**
 * @author Claudio Lillo
 *
 */
public class DatosEntradaVO {
private int rutemp;
private int anio;
private String mes;
private List<Integer> ruttra;
/**
 * @return the rutemp
 */
public int getRutemp() {
	return rutemp;
}
/**
 * @param rutemp the rutemp to set
 */
public void setRutemp(int rutemp) {
	this.rutemp = rutemp;
}
/**
 * @return the anio
 */
public int getAnio() {
	return anio;
}
/**
 * @param anio the anio to set
 */
public void setAnio(int anio) {
	this.anio = anio;
}
/**
 * @return the mes
 */
public String getMes() {
	return mes;
}
/**
 * @param mes the mes to set
 */
public void setMes(String mes) {
	this.mes = mes;
}
/**
 * @return the ruttra
 */
public List<Integer> getRuttra() {
	return ruttra;
}
/**
 * @param ruttra the ruttra to set
 */
public void setRuttra(List<Integer> ruttra) {
	this.ruttra = ruttra;
}



}
