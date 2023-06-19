/**
 * 
 */
package cl.laaraucana.convenfsura.services;

import cl.laaraucana.convenfsura.vo.AfiliadoVo;

/**
 * @author IBM Software Factory
 *
 */
public interface BitacoraService {
	public void insertBitacora(AfiliadoVo data_afiliado) throws Exception;
	
	public void insertDatosContacto(AfiliadoVo data_afiliado, String tipoDato);
}
