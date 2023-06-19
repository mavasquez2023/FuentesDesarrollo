/**
 * 
 */
package cl.laaraucana.apofam.services;

import cl.laaraucana.apofam.entities.Cargas;

/**
 * @author IBM Software Factory
 *
 */
public interface BitacoraService {
	public void insertBitacora(Cargas data_afiliado, String rol) throws Exception;
	
}
