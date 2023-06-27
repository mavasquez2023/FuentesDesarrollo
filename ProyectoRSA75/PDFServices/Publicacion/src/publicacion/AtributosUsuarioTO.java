/**
 * 
 */
package publicacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Usist24
 *
 */

public class AtributosUsuarioTO {
// Coleccion de objetos del tipo EmpresasAutorizadasTO {rut, razonSocial}
Collection empresas;
//Colección de empresas ordenadas por razón Social
Collection sortEmpresasByRazon;
Collection convenios;
Collection sucursales;
//Lista de Rut empresas sin dv separadas por espacio
String listaEmpresas;
//Lista de convenios separadas por espacio
String listaConvenios;
//Lista de sucursales separadas por espacio
String listaSucursales;
String tipoUsuario;
String tipoEntidad;
String usuario;
String holding;
String iniCotiza;
/**
 * @return el convenios
 */
public Collection getConvenios() {
	return convenios;
}
/**
 * @param convenios el convenios a establecer
 */
public void setConvenios(Collection convenios) {
	Collections.sort((List)convenios);
	this.convenios = convenios;
}
/**
 * @return el empresas
 */
public Collection getEmpresas() {
	return empresas;
}
/**
 * @param empresas el empresas a establecer
 */
public void setEmpresas(Collection empresas) {
	this.empresas = empresas;
	setSortEmpresasByRazon(new ArrayList(empresas));
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
	this.holding = holding;
}
/**
 * @return el iniCotiza
 */
public String getIniCotiza() {
	return iniCotiza;
}
/**
 * @param iniCotiza el iniCotiza a establecer
 */
public void setIniCotiza(String iniCotiza) {
	this.iniCotiza = iniCotiza;
}
/**
 * @return el sucursales
 */
public Collection getSucursales() {
	return sucursales;
}
/**
 * @param sucursales el sucursales a establecer
 */
public void setSucursales(Collection sucursales) {
	Collections.sort((List)sucursales);
	this.sucursales = sucursales;
}
/**
 * @return el tipoEntidad
 */
public String getTipoEntidad() {
	return tipoEntidad;
}
/**
 * @param tipoEntidad el tipoEntidad a establecer
 */
public void setTipoEntidad(String tipoEntidad) {
	this.tipoEntidad = tipoEntidad;
}
/**
 * @return el tipoUsuario
 */
public String getTipoUsuario() {
	return tipoUsuario;
}
/**
 * @param tipoUsuario el tipoUsuario a establecer
 */
public void setTipoUsuario(String tipoUsuario) {
	this.tipoUsuario = tipoUsuario;
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
	this.usuario = usuario;
}
/**
 * @return el listaConvenios
 */
public String getListaConvenios() {
	return listaConvenios;
}
/**
 * @param listaConvenios el listaConvenios a establecer
 */
public void setListaConvenios(String listaConvenios) {
	this.listaConvenios= listaConvenios;
}
/**
 * @return el listaEmpresas
 */
public String getListaEmpresas() {
	return listaEmpresas;
}
/**
 * @param listaEmpresas el listaEmpresas a establecer
 */
public void setListaEmpresas(String listaEmpresas) {
	this.listaEmpresas= listaEmpresas;
}
/**
 * @return el listaSucursales
 */
public String getListaSucursales() {
	return listaSucursales;
}
/**
 * @param listaSucursales el listaSucursales a establecer
 */
public void setListaSucursales(String listaSucursales) {
	this.listaSucursales= listaSucursales;
}
/**
 * @return el sortEmpresasByRazon
 */
public Collection getSortEmpresasByRazon() {
	return sortEmpresasByRazon;
}
/**
 * @param sortEmpresasByRazon el sortEmpresasByRazon a establecer
 */
public void setSortEmpresasByRazon(Collection sortEmpresasByRazon) {
	Collections.sort((List)sortEmpresasByRazon, new CustomComparator());
	this.sortEmpresasByRazon = sortEmpresasByRazon;
}

/**
 * @param listaConveniosDomino el listaConveniosDomino a establecer
 */
public void setListaConveniosDomino(String listaConveniosDomino) {
	String[] convenios = listaConveniosDomino.split("\n");
	String allConvenios="";
	List listConvenios= new ArrayList();
	for (int i = 0; i < convenios.length; i++) {
		String conv_emp= convenios[i].trim();
		listConvenios.add(conv_emp);
		allConvenios+= conv_emp + " ";
	}
	setListaConvenios(allConvenios.trim());
	setConvenios(listConvenios);
}

/**
 * @param listaEmpresasDomino el listaEmpresasDomino a establecer
 */
public void setListaEmpresasDomino(String listaEmpresasDomino) {
	String[] ruts = listaEmpresasDomino.split("\n");
	//ruts = this.invertir(ruts);
	String allRuts="";
	//Se rescatan las empresas autorizadas
	List listEmpresas= new ArrayList();
	for (int i = 0; i < ruts.length; i++) {
		EmpresasAutorizadasTO empresa= new EmpresasAutorizadasTO();
		String[] rut_razon = ruts[i].split(":");
		String rut= rut_razon[0].trim();
		String razonsocial= rut_razon[1].trim();
		empresa.setRut(rut);
		empresa.setRazonSocial(razonsocial);
		listEmpresas.add(empresa);
		allRuts+= empresa.getRutint() + " ";
	}
	allRuts= allRuts.substring(0, allRuts.length()-1);
	setListaEmpresas(allRuts);
	setEmpresas(listEmpresas);
}

/**
 * @param listaSucursalesDomino el listaSucursalesDomino a establecer
 */
public void setListaSucursalesDomino(String listaSucursalesDomino) {
	listaSucursalesDomino= listaSucursalesDomino.replaceAll(";", "");
	String[] sucursales = listaSucursalesDomino.split(",");
	String allSucursales="";
	List listSucursales= new ArrayList();
	for (int i = 0; i < sucursales.length; i++) {
		String suc_emp= sucursales[i].trim();
		listSucursales.add(suc_emp);
		allSucursales+= suc_emp + " ";
	}
	setListaSucursales(allSucursales.trim());
	setSucursales(listSucursales);
}

}
