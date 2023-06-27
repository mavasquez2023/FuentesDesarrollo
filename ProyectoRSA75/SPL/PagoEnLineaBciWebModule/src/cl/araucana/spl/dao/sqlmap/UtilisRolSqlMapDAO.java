package cl.araucana.spl.dao.sqlmap;

import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.spl.action.roles.OpcionDVO;
import cl.araucana.spl.beans.Rol;
import cl.araucana.spl.dao.UtilisRolDAO;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;

/**
 * ...
 *
 * @author Gonzalo Mallea (gmallea@schema.cl)
 *
 * @version 1.0
 */

public class UtilisRolSqlMapDAO extends SqlMapDaoTemplate implements UtilisRolDAO {
	private static final String SQL_UTILIS_ROL = "sqlUtilisRol";	
	private static final String SQL_ROL = "sqlRol";
	
	private static final Logger logger = Logger.getLogger(UtilisRolSqlMapDAO.class);
			
	public UtilisRolSqlMapDAO(DaoManager daoManager) {
		super(daoManager);
	}
	
	public OpcionDVO[] getListaOpcionesBySistemaUsuario(String rutUsuario) {
		if (logger.isDebugEnabled())
			logger.debug("Entre a getListaOpcionesBySistemaUsuario, el rutUsuario : " +rutUsuario) ;
		List lista =  queryForList(SQL_UTILIS_ROL,rutUsuario);
		
		OpcionDVO[] resultado = (OpcionDVO[] )lista.toArray( new OpcionDVO[lista.size()] );
		return resultado;
	}
	
	public Rol[] getListaRol(String rutUsuario) {
		if (logger.isDebugEnabled())
			logger.debug("Entre a getListaRol, el rutUsuario : " +rutUsuario) ;
		
		List lista =  queryForList(SQL_ROL,rutUsuario);
		
		Rol[] rols = ( Rol[] )lista.toArray( new Rol[lista.size()] );
		
		return rols;
	}
}