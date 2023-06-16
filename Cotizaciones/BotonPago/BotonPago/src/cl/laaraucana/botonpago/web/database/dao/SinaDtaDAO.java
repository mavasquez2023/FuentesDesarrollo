package cl.laaraucana.botonpago.web.database.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.botonpago.web.database.ibatis.domain.Sinat03;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Sinat04;

import com.ibatis.sqlmap.client.SqlMapClient;

public class SinaDtaDAO extends DaoParent {
	protected Logger logger = Logger.getLogger(SinaDtaDAO.class);

	/*****************************FIN SINAT03**************************/

	@SuppressWarnings("unchecked")
	public List<Sinat03> getSinat03() throws Exception {
		List<Sinat03> datos = new ArrayList<Sinat03>();
		try {
			datos = getConn().queryForList("getSinat03", null);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("" + e.getMessage());
			throw new Exception("Falló al realizar la invoación al procedimiento");
		}
//		logger.debug("Salida: " + datos);

		return datos;
	}

	public void ingresaSinat03(Sinat03 sinat03) throws Exception {
		SqlMapClient sqlMap = getConn();
		try {
			sqlMap.insert("ingresaSinat03", sinat03);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("" + e.getMessage());
			throw new Exception("Error al registrar Sinat03");
		}
	}

	public void eliminarSinat03(Sinat03 sinat03) throws Exception {
		SqlMapClient sqlMap = getConn();
		try {
			sqlMap.delete("eliminarSinat03", sinat03);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("" + e.getMessage());
			throw new Exception("Error eliminar Sinat03");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Sinat03> buscarSinat03(String entrada) throws Exception {
		List<Sinat03> datos = new ArrayList<Sinat03>();
		SqlMapClient sqlMap = getConn();
		try {
			datos = sqlMap.queryForList("buscarSinat03", entrada);
			//			sqlMap.openSession().close();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("" + e.getMessage());
			throw new Exception("Falló al realizar la invoación al procedimiento");
		}
//		logger.debug("Salida: " + datos);

		return datos;
	}

	public Sinat03 buscarEditarSinat03(String entrada) throws Exception {
		Sinat03 datos = new Sinat03();
		SqlMapClient sqlMap = getConn();
		try {
			datos = (Sinat03) sqlMap.queryForObject("buscarEditarSinat03", entrada);
			//			sqlMap.openSession().close();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("" + e.getMessage());
			throw new Exception("Falló al realizar la invoación al procedimiento");
		}
//		logger.debug("Salida: " + datos);

		return datos;
	}

	public void editarSinat03(Sinat03 sinat03) throws Exception {
		SqlMapClient sqlMap = getConn();
		try {
			sqlMap.update("editarSinat03", sinat03);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("" + e.getMessage());
			throw new Exception("Error al editar el sinat03");
		}
	}

	/*****************************FIN SINAT03**************************/

	/*****************************FIN SINAT04**************************/

	@SuppressWarnings("unchecked")
	public List<Sinat04> getSinat04(String codPro) throws Exception {
		List<Sinat04> datos = new ArrayList<Sinat04>();
		//		SqlMapClient sqlMap = getConn();
		try {
			datos = getConn().queryForList("getSinat04", codPro);
			//			sqlMap.openSession().close();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("" + e.getMessage());
			throw new Exception("Falló al realizar la invoación al procedimiento");
		}
//		logger.debug("Salida: " + datos);

		return datos;
	}

	public void ingresaSinat04(Sinat04 sinat04) throws Exception {
		SqlMapClient sqlMap = getConn();
		try {
			sqlMap.insert("ingresaSinat04", sinat04);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("" + e.getMessage());
			throw new Exception("Error al registrar Sinat04");
		}
	}

	public void eliminarSinat04(Sinat04 sinat04) throws Exception {
		SqlMapClient sqlMap = getConn();
		try {
			sqlMap.delete("eliminarSinat04", sinat04);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error eliminar Sinat04");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Sinat04> buscarSinat04(String entrada) throws Exception {
		List<Sinat04> datos = new ArrayList<Sinat04>();
		SqlMapClient sqlMap = getConn();
		try {
			datos = sqlMap.queryForList("buscarSinat04", entrada);
			//			sqlMap.openSession().close();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("" + e.getMessage());
			throw new Exception("Falló al realizar la invoación al procedimiento");
		}
//		logger.debug("Salida: " + datos);

		return datos;
	}

	public Sinat04 buscarEditarSinat04(Sinat04 sinat04Edit) throws Exception {
		Sinat04 datos = new Sinat04();
		SqlMapClient sqlMap = getConn();
		try {
			datos = (Sinat04) sqlMap.queryForObject("buscarEditarSinat04", sinat04Edit);
			//			sqlMap.openSession().close();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("" + e.getMessage());
			throw new Exception("Falló al realizar la invoación al procedimiento");
		}
//		logger.debug("Salida: " + datos);

		return datos;
	}

	public void editarSinat04(Sinat04 sinat04) throws Exception {
		SqlMapClient sqlMap = getConn();
		try {
			sqlMap.update("editarSinat04", sinat04);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("" + e.getMessage());
			throw new Exception("Error al editar el sinat04");
		}
	}

	/*****************************FIN SINAT04**************************/

	/*public static void ingresaUsuario(DatosUsuario datosUsuario) throws Exception{

		SqlMapClient sqlMap = null;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		}catch(Exception e){
			throw new Exception("Error al conectarse al datasource");
		}
		try{
			sqlMap.insert("ingresaUsuario", datosUsuario);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al registrar el usuario");
		}
	}
	
	public static void eliminarUsuario(DatosUsuario datosUsuario) throws Exception{
		SqlMapClient sqlMap = null;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		}catch(Exception e){
			throw new Exception("Error al conectarse al datasource");
		}
		try{
			sqlMap.delete("eliminarUsuario", datosUsuario);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al buscar el usuario");
		}
	}
	
	
	public static List<DatosUsuario> buscarUsuario(String rut) throws Exception{
		SqlMapClient sqlMap = null;
		List<DatosUsuario> datosUsuarioList = new ArrayList<DatosUsuario>();
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		}catch(Exception e){
			throw new Exception("Error al conectarse al datasource");
		}
		try{
			datosUsuarioList = (List<DatosUsuario>) sqlMap.queryForList("buscarUsuario", rut);	
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al buscar el usuario");
		}
		return datosUsuarioList;
	}
	
	public static DatosUsuario getUsuarioById(DatosUsuario entrada) throws Exception{
		SqlMapClient sqlMap = null;
		DatosUsuario datosUsuario;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		}catch(Exception e){
			throw new Exception("Error al conectarse al datasource");
		}
		try{
			datosUsuario = (DatosUsuario) sqlMap.queryForObject("getUsuarioById", entrada);	
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al buscar el usuario por id");
		}
		return datosUsuario;
	}
	
	public static void editarUsuario(DatosUsuario entrada) throws Exception{
		SqlMapClient sqlMap = null;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		}catch(Exception e){
			throw new Exception("Error al conectarse al datasource");
		}
		try{
			sqlMap.update("editarUsuario", entrada);	
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al editar el usuario");
		}
	}*/
}
