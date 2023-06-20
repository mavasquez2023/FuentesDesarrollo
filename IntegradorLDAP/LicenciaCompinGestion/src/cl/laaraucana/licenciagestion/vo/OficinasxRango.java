/**
 * 
 */
package cl.laaraucana.licenciagestion.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author IBM Software Factory
 *
 */
public class OficinasxRango {
private int percentil;
private String rango;
private int totalLicencias;
private List<OficinaViaIngreso> listaOficinas;
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
 * @return the totalLicencias
 */
public int getTotalLicencias() {
	return totalLicencias;
}
/**
 * @param totalLicencias the totalLicencias to set
 */
public void setTotalLicencias(int totalLicencias) {
	this.totalLicencias = totalLicencias;
}
/**
 * @return the listaOficinas
 */
public List<OficinaViaIngreso> getListaOficinas() {
	return listaOficinas;
}
/**
 * @param listaOficinas the listaOficinas to set
 */
public void setListaOficinas(List<OficinaViaIngreso> listaOficinas) {
	this.listaOficinas = listaOficinas;
}

public void addTotal(int monto){
	this.totalLicencias= this.totalLicencias + monto;
}

public void addSucursal( OficinaViaIngreso oficina){
	if(this.listaOficinas==null){
		listaOficinas= new ArrayList<OficinaViaIngreso>();
	}
	this.listaOficinas.add(oficina);
}

}
