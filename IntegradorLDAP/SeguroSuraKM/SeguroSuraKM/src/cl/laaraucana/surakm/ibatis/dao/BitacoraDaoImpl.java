/**
 * 
 */
package cl.laaraucana.surakm.ibatis.dao;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.laaraucana.surakm.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.surakm.ibatis.vo.BitacoraSuraVo;
import cl.laaraucana.surakm.ibatis.vo.DatosContactoVo;


/**
 * @author IBM Software Factory
 *
 */
public class BitacoraDaoImpl implements BitacoraDao {
	protected static Logger logger = Logger.getLogger("BitacoraDaoImpl");
	/* (non-Javadoc)
	 * @see cl.laaraucana.convenfsura.ibatis.dao.BitacoraDao#insertBitacora(cl.laaraucana.convenfsura.vo.AfiliadoVo)
	 */
	@Override
	public void insertBitacora(BitacoraSuraVo registro) throws Exception {
SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		Integer resultado = (Integer)sqlMap.insert("formweb.insertBitacora", registro);

	}
	
	@Override
	public void insertDatosContacto(DatosContactoVo registro) throws Exception {
SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		Integer resultado = (Integer)sqlMap.insert("formweb.insertDatosContacto", registro);

		
	}
}
