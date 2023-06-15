/**
 * 
 */
package cl.laaraucana.simulacion.VO;

import java.util.List;

/**
 * @author IBM Software Factory
 *
 */
public class TipoAfiliadoCRM {
private List<String> tipoAfiliado;
private List<String> anexos;
private String nombre;
private String fechaNacimiento;
/**
 * @return the tipoAfiliado
 */
public List<String> getTipoAfiliado() {
	return tipoAfiliado;
}
/**
 * @param tipoAfiliado the tipoAfiliado to set
 */
public void setTipoAfiliado(List<String> tipoAfiliado) {
	this.tipoAfiliado = tipoAfiliado;
}
/**
 * @return the anexos
 */
public List<String> getAnexos() {
	return anexos;
}
/**
 * @param anexos the anexos to set
 */
public void setAnexos(List<String> anexos) {
	this.anexos = anexos;
}
/**
 * @return the nombre
 */
public String getNombre() {
	return nombre;
}
/**
 * @param nombre the nombre to set
 */
public void setNombre(String nombre) {
	this.nombre = nombre;
}
/**
 * @return the fechaNacimiento
 */
public String getFechaNacimiento() {
	return fechaNacimiento;
}
/**
 * @param fechaNacimiento the fechaNacimiento to set
 */
public void setFechaNacimiento(String fechaNacimiento) {
	this.fechaNacimiento = fechaNacimiento;
}


}
