package cl.araucana.wsafiliado.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import cl.araucana.wsafiliado.sqlmap.SqlMapLocator;
import cl.araucana.wsafiliado.sqlmap.SqlMapLocatorAux;
import cl.araucana.wsafiliado.vo.DatoEntradaVO;

import com.ibatis.sqlmap.client.SqlMapClient;

public class AdministracionDAO {

	private static Log log = LogFactory.getLog(AdministracionDAO.class);

	public static Map<String, String> validaUsuarioWS(String usuario) throws SQLException{

		SqlMapClient sqlMap = SqlMapLocator.getInstance();

		@SuppressWarnings("unchecked")
		Map<String, String> resultado = (HashMap<String, String>)sqlMap.queryForObject("administracion.validaUsuario", usuario );

		return resultado;
	}

	public static int isUsuarioAutorizado(String usuario, String metodo) throws SQLException{


		SqlMapClient sqlMap = SqlMapLocator.getInstance();
		HashMap param= new HashMap();
		param.put("usuario", usuario);
		param.put("metodo", metodo);
		
		Integer estado = (Integer)sqlMap.queryForObject("administracion.obtenerAutorizacionUsuario", param );

		return estado.intValue();
	}
	

}