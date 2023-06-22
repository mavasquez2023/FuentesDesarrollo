package cl.laaraucana.recepcionsil.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cl.laaraucana.config.Config;
import cl.laaraucana.config.ibatis.DaoException;
import cl.laaraucana.recepcionsil.persistencia.dao.ServIngDaoI;
import cl.laaraucana.recepcionsil.persistencia.dto.Ilfsering;
import cl.laaraucana.recepcionsil.service.vo.Ilf1100VO;
import cl.laaraucana.recepcionsil.service.vo.Ilf8600VO;
import cl.laaraucana.recepcionsil.service.vo.Ilfe031VO;
import cl.laaraucana.recepcionsil.service.vo.Ilfe033VO;
import cl.laaraucana.recepcionsil.service.vo.Ilfe034VO;
import cl.laaraucana.recepcionsil.service.vo.LicenciaNivel2VO;
import cl.laaraucana.recepcionsil.service.vo.LicenciaVO;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ServIngDao implements ServIngDaoI {
	Logger log = Logger.getLogger(this.getClass());
	private static SqlMapClient sqlMap;

	public ServIngDao(SqlMapClient sqlMapIn) {
		sqlMap = sqlMapIn;
	}

	@Override
	public void ingresarCadena(Ilfsering ing) throws DaoException {
		try {
			sqlMap.insert("insertServIng", ing);
			//sqlMap.insert("insertServIng_V", ing);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("Error en el ingreso", e);
		}

	}
	
	public void ingresarPre1000(LicenciaVO lic) throws DaoException {
		try {
			sqlMap.insert("insertIlfpre1000", lic);
			//sqlMap.insert("insertIlfpre1000_V", lic);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("Error en el ingreso pre1000", e);
		}
	}
	
	public int getOficina(int oficina){
		try {
			Integer count= (Integer)sqlMap.queryForObject("getOficina", oficina);
			return count.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getNumeroLicenciaCaja(int numeroLicenciaOperador){
		try {
			BigDecimal num= (BigDecimal)sqlMap.queryForObject("getNumeroLicenciaCaja", numeroLicenciaOperador);
			return num.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void update8600(Ilf8600VO vo8600) throws DaoException {
		try {
			sqlMap.update("updateIlf8600", vo8600);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("Error en el ingreso 8600", e);
		}
	}
	
	public void update1010(HashMap<String, Object> param) throws DaoException {
		try {
			sqlMap.update("updateIlf1010", param);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("Error en el ingreso 8600", e);
		}
	}
	
	public void ingresar1100(Ilf1100VO vo1100) throws DaoException {
		try {
			sqlMap.insert("insertIlf1100", vo1100);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("Error en el ingreso 1100", e);
		}
	}

	public void ingresar031(Ilfe031VO vo31) throws DaoException {
		try {
			sqlMap.insert("insertIlfe031", vo31);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("Error en el ingreso 1100", e);
		}
	}
	
	public void update031(Ilfe031VO vo31) throws DaoException {
		try {
			sqlMap.update("updateIlfe031", vo31);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("Error en el update 031", e);
		}
	}
	
	public void ingresar033(Ilfe033VO vo33) throws DaoException {
		try {
			sqlMap.insert("insertIlfe033", vo33);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("Error en el ingreso 033", e);
		}
	}
	
	public void ingresar034(Ilfe034VO vo34)throws DaoException {
		try {
			sqlMap.insert("insertIlfe034", vo34);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("Error en el ingreso 033", e);
		}
	}
	
	public List<HashMap<String, BigDecimal>> getLicAnteriores(HashMap<String, Integer> param){
		try {
			List listalic= sqlMap.queryForList("getLicenciasAnteriores", param);
			return listalic;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getTieneLicenciasAnteriores(HashMap<String, String> param){
		try {
			Integer count= (Integer)sqlMap.queryForObject("getTieneLicenciasAnteriores", param);
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public Object getIlfeOpe(HashMap<String, String> param){
		try {
			Object salida= sqlMap.queryForObject("getIlfeOpe", param);
			return salida;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public HashMap<String, BigDecimal> getIlfSering(HashMap<String, String> param){
		try {
			HashMap<String, BigDecimal> salida= (HashMap<String, BigDecimal>)sqlMap.queryForObject("getIlfSering", param);
			return salida;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getNomEmpresa(int rutemp){
		try {
			String nom= (String)sqlMap.queryForObject("getNomEmpresa", rutemp);
			return nom;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getNombreEntidad(String codigo){
		try {
			String nom= (String)sqlMap.queryForObject("getNombreEntidad", codigo);
			return nom;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getServicioSalud(int oficinaIngreso){
		try {
			Integer codigo= (Integer)sqlMap.queryForObject("getServicioSalud", oficinaIngreso);
			return codigo.toString();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "0";
	}
	
	public HashMap<String, String> getEmpleador(HashMap<String, Integer> param){
		try {
			HashMap<String, String> salida= (HashMap<String, String>)sqlMap.queryForObject("getEmpleador", param);
			return salida;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Map<String, String> getEndPoints() throws Exception {
		Map<String, String> endPointMap = null;
		try {
			String prioridad = Config.getConfig("prioridad.db");
			endPointMap =  sqlMap.queryForMap("getEndPoints", prioridad, "key", "value");
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new Exception("Error al obtener la lista de endpoint");
		}
		return endPointMap;
	}

	public Map<String, String> getParametrosBd() throws Exception {
		Map<String, String> map = null;
		try {
			map= sqlMap.queryForMap("getParametros","", "key","value");
		} catch (SQLException e) {
			//REQ-8000001332 - notificacion de error de conexion
			log.error("Error al obtener la lista de parámetros desde BD");
			throw new Exception("Error al obtener la lista de parámetros desde BD");
		}
		return map;
	}
	
	public void insertIlfPersist(HashMap param) throws DaoException{
		try {
			sqlMap.insert("insertIlfPersist", param);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("Error en el ingreso IlfPersist", e);
		}
	}
	
	public List<HashMap<String, BigDecimal>> getIlfPersist(int estado){
		try {
			List listalic= sqlMap.queryForList("getLicenciasPersist", estado);
			return listalic;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void updateIlfPersist(HashMap<String, String> param) throws DaoException {
		//int rtdo=0;
		try {
			int rtdo= sqlMap.update("updateIlfPersist", param);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("Error en el ingreso IlfPersist", e);
		}
	}
}
