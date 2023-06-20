/**
 * 
 */
package cl.laaraucana.licenciascompinemp.vo;

/**
 * @author IBM Software Factory
 *
 */
public class EmpresaVO implements Comparable<String>{
private String rutEmpresa;
private String nombreEmpresa;
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
 * @return the nombreEmpresa
 */
public String getNombreEmpresa() {
	return nombreEmpresa;
}
/**
 * @param nombreEmpresa the nombreEmpresa to set
 */
public void setNombreEmpresa(String nombreEmpresa) {
	this.nombreEmpresa = nombreEmpresa;
}
@Override
public int compareTo(String rutempresa) {
	// TODO Auto-generated method stub
	return this.rutEmpresa.equals(rutempresa)?0:1;
}


}
