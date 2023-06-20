/**
 * 
 */
package cl.laaraucana.muvu.ibatis.dao;

import cl.laaraucana.muvu.ibatis.vo.BitacoraSuraVo;
import cl.laaraucana.muvu.ibatis.vo.DatosContactoVo;

/**
 * @author IBM Software Factory
 *
 */
public interface BitacoraDao {
	public void insertBitacora(BitacoraSuraVo registro) throws Exception;
	
	public void insertDatosContacto(DatosContactoVo registro) throws Exception;
}
