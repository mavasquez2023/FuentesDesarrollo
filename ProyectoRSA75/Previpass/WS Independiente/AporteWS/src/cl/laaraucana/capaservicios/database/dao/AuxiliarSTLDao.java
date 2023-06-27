package cl.laaraucana.capaservicios.database.dao;

import java.util.List;
import com.ibatis.sqlmap.client.SqlMapClient;
import cl.laaraucana.capaservicios.webservices.vo.DatosAuxiliares.CodigoVO;

@SuppressWarnings("unchecked")
public class AuxiliarSTLDao extends DaoParent {
	
	/**
	 * Consulta por códigos de banco para realizar transferencias a través de STL
	 * @return lista de códigos de banco y descripción
	 * @throws Exception
	 */
	
	public List<CodigoVO> getListaBancos() throws Exception{
		List<CodigoVO> codigos = null;
		
		SqlMapClient sqlMap = getConn();
		try {
			codigos = (List<CodigoVO>)  sqlMap.queryForList("getListaBancos", null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al consutar lista de bancos");
		}
		return codigos;
	}
	
	/**
	 * Consulta por tipos de cuenta para realizar transferencias a través de STL
	 * @return lista de códigos de tipo de cuenta y descripción
	 * @throws Exception
	 */
	public List<CodigoVO> getTiposCuenta() throws Exception{
		List<CodigoVO> codigos = null;
		SqlMapClient sqlMap = getConn();
		try {
			codigos = (List<CodigoVO>)  sqlMap.queryForList("getTiposCuenta", null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al consultar tipos de cuenta");
		}
		return codigos;
	}
	
	/**
	 * Obtiene listado de communas con formato 'COD_COMUNA;COD_PROVICIA;COD_REGION' y Nombre de comuna
	 * @return
	 * @throws Exception
	 */
	public List<CodigoVO> getComunas() throws Exception{
		List<CodigoVO> codigos = null;
		SqlMapClient sqlMap = getConn();
		try {
			codigos = (List<CodigoVO>)  sqlMap.queryForList("getComunas", null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al obtener comunas");
		}
		return codigos;
	}
	
	/**
	 * Obtiene listado de provincias desde DB2
	 * @return
	 * @throws Exception
	 */
	public List<CodigoVO> getProvincias() throws Exception{
		List<CodigoVO> codigos = null;
		SqlMapClient sqlMap = getConn();
		try {
			codigos = (List<CodigoVO>)  sqlMap.queryForList("getProvincias", null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al obtener provincias");
		}
		return codigos;
	}
	
	/**
	 * Obtiene listado de cuentas desde DB2
	 * @return
	 * @throws Exception
	 */
	public List<CodigoVO> getRegiones() throws Exception{
		List<CodigoVO> codigos = null;
		SqlMapClient sqlMap = getConn();
		try {
			codigos = (List<CodigoVO>)  sqlMap.queryForList("getRegiones", null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al obtener regiones");
		}
		return codigos;
	}
}
