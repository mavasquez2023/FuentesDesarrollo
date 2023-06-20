/**
 * 
 */
package cl.laaraucana.licenciascompin.services;

import java.util.List;

import cl.laaraucana.licenciascompin.entities.CorreoBalanceo;
import cl.laaraucana.licenciascompin.entities.RegistroDocPendientes;
import cl.laaraucana.licenciascompin.vo.RegistroDocPendienteVo;



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
