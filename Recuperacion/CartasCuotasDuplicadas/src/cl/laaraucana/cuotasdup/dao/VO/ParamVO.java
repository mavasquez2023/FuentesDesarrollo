/**
 * 
 */
package cl.laaraucana.cuotasdup.dao.VO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author IBM Software Factory
 *
 */
public class ParamVO {
private String rutEmpresa;
private String dvRutEmpresa;
private String razonSocial;
private String oficina;
private String nombreOficina;
private String sucursal;
private List<Integer> listRutemp;

public ParamVO() {

}

public ParamVO(String rutEmpresa, String razonSocial, String codigoOficina, String codigoSucursal) {
	List<Integer> listaRut= new ArrayList<Integer>();
	if(rutEmpresa!=null){
		String[] ruts= rutEmpresa.split(" ");
		for (int i = 0; i < ruts.length; i++) {
			listaRut.add(Integer.parseInt(ruts[i].trim()));
		}
	}else{
		listaRut=null;
	}

	this.listRutemp= listaRut;
	if (razonSocial!=null){
		razonSocial= "%"+razonSocial+"%";
	}
	this.razonSocial= razonSocial;
	this.oficina= codigoOficina;
	this.sucursal= codigoSucursal;
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
 * @return the dvRutEmpresa
 */
public String getDvRutEmpresa() {
	return dvRutEmpresa;
}

/**
 * @param dvRutEmpresa the dvRutEmpresa to set
 */
public void setDvRutEmpresa(String dvRutEmpresa) {
	this.dvRutEmpresa = dvRutEmpresa;
}

/**
 * @return the razonSocial
 */
public String getRazonSocial() {
	return razonSocial;
}

/**
 * @param razonSocial the razonSocial to set
 */
public void setRazonSocial(String razonSocial) {
	this.razonSocial = razonSocial;
}

/**
 * @return the oficina
 */
public String getOficina() {
	return oficina;
}

/**
 * @param oficina the oficina to set
 */
public void setOficina(String oficina) {
	this.oficina = oficina;
}

/**
 * @return the nombreOficina
 */
public String getNombreOficina() {
	return nombreOficina;
}

/**
 * @param nombreOficina the nombreOficina to set
 */
public void setNombreOficina(String nombreOficina) {
	this.nombreOficina = nombreOficina;
}

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
 * @return the listRutemp
 */
public List<Integer> getListRutemp() {
	return listRutemp;
}

/**
 * @param listRutemp the listRutemp to set
 */
public void setListRutemp(List<Integer> listRutemp) {
	this.listRutemp = listRutemp;
}


}