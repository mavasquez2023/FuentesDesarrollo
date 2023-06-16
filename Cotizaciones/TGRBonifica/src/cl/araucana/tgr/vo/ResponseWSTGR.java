/**
 * 
 */
package cl.araucana.tgr.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Claudio Lillo
 *
 */
public class ResponseWSTGR implements Serializable{
private EmpleadorVO empleador;
private List<TrabajadorVO> trabajador;

/**
 * @return the list_trabajador
 */
public List<TrabajadorVO> getTrabajador() {
	return trabajador;
}
/**
 * @param list_trabajador the list_trabajador to set
 */
public void setTrabajador(List<TrabajadorVO> trabajador) {
	this.trabajador = trabajador;
}

/**
 * @return el empleador
 */
public EmpleadorVO getEmpleador() {
	return empleador;
}
/**
 * @param empleador el empleador a establecer
 */
public void setEmpleador(EmpleadorVO empleador) {
	this.empleador = empleador;
}


}
