/**
 * 
 */
package cl.laaraucana.licenciascompinemp.services;

import java.util.List;

import cl.laaraucana.licenciascompinemp.entities.CorreoBalanceo;
import cl.laaraucana.licenciascompinemp.entities.RegistroDocPendientes;



/**
 * @author J-Factory
 * Nueva interfaz para el registro de bitacora del sistema.
 *
 */
public interface RegistroDocPendientesService {
	
	public void save(RegistroDocPendientes Entity) throws Exception;
	
	public CorreoBalanceo getCorreoEjecutivo() throws Exception ;
	
	public List<RegistroDocPendientes> findByRut(String rut) throws Exception;

}
