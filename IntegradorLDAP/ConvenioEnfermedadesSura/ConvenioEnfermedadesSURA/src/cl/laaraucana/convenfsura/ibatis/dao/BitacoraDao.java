/**
 * 
 */
package cl.laaraucana.convenfsura.ibatis.dao;

import cl.laaraucana.convenfsura.ibatis.vo.BitacoraSuraVo;
import cl.laaraucana.convenfsura.ibatis.vo.DatosContactoVo;

/**
 * @author IBM Software Factory
 *
 */
public interface BitacoraDao {
	public void insertBitacora(BitacoraSuraVo registro) throws Exception;
	
	public void insertDatosContacto(DatosContactoVo registro) throws Exception;
}
