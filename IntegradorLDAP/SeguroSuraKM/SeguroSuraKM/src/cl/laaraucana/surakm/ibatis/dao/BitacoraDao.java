/**
 * 
 */
package cl.laaraucana.surakm.ibatis.dao;

import cl.laaraucana.surakm.ibatis.vo.BitacoraSuraVo;
import cl.laaraucana.surakm.ibatis.vo.DatosContactoVo;

/**
 * @author IBM Software Factory
 *
 */
public interface BitacoraDao {
	public void insertBitacora(BitacoraSuraVo registro) throws Exception;
	
	public void insertDatosContacto(DatosContactoVo registro) throws Exception;
}
