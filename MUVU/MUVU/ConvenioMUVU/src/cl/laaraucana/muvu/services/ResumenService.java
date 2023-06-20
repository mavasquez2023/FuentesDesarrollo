/**
 * 
 */
package cl.laaraucana.muvu.services;

import java.util.List;

import cl.laaraucana.muvu.entities.Resumen;
import cl.laaraucana.muvu.vo.AfiliadoVo;

/**
 * @author IBM Software Factory
 *
 */
public interface ResumenService {
	public void insertResumen(AfiliadoVo data_afiliado) throws Exception;
	
	public void updateResumen(AfiliadoVo data_afiliado) throws Exception;
	
	public void updateResumen(Resumen resumen) throws Exception;
	
	public Resumen findByRut(int rut) throws Exception;
	
}
