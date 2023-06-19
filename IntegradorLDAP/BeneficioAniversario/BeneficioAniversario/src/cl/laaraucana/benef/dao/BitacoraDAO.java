package cl.laaraucana.benef.dao;


import org.apache.log4j.Logger;
import com.ibatis.sqlmap.client.SqlMapClient;
import cl.laaraucana.benef.config.SqlConfig;
import cl.laaraucana.benef.vo.BitacoraVO;


public class BitacoraDAO {

	protected static Logger logger = Logger.getLogger(BitacoraDAO.class);

	
	public boolean insertBitacora(BitacoraVO param) throws Exception {

		SqlMapClient sqlMap = null;

		sqlMap = SqlConfig.getSqlMapInstance();

		Integer resultado= (Integer)sqlMap.insert("bitacoraService.insertBitacora", param);
		
		return resultado!=null && resultado>=0 ?true:false;
	}
	
}
