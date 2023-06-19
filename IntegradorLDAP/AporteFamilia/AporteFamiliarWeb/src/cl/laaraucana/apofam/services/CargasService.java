/**
 * 
 */
package cl.laaraucana.apofam.services;

import java.util.List;

import cl.laaraucana.apofam.entities.Cargas;


/**
 * @author IBM Software Factory
 *
 */
public interface CargasService {
	
	public Cargas findByRut(int rut) throws Exception;
	
}
