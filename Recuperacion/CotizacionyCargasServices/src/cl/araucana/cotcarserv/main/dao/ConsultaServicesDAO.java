package cl.araucana.cotcarserv.main.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cl.araucana.cotcarserv.dao.VO.CargasVO;
import cl.araucana.cotcarserv.dao.VO.CorreoVO;
import cl.araucana.cotcarserv.dao.VO.CotizacionesVO;
import cl.araucana.cotcarserv.dao.VO.InformeSAPVO;
import cl.araucana.cotcarserv.dao.VO.ParamVO;
import cl.araucana.cotcarserv.ibatis.MyClassSqlConfig;


import com.ibatis.sqlmap.client.SqlMapClient;


public class ConsultaServicesDAO {
	protected static Logger logger = Logger.getLogger("ConsultaCargasDAO");
	
	@SuppressWarnings("unchecked")
	public boolean consultaPeriodoCargas(String periodo) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List<String> queryForList = sqlMap.queryForList("cycservices.consultaPeriodoCargas", Integer.parseInt(periodo));
			if(queryForList!= null && queryForList.size()>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, String> consultaNombreEmpresa(int rut) throws Exception {
		SqlMapClient sqlMap = null;
		Map<String, String> nombre= new HashMap<String, String>();
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			nombre = (Map)sqlMap.queryForObject("cycservices.consultaNombreEmpresa", rut);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			nombre.put("RAZON_SOCIAL", "ERROR AL OBTENER INFORMACION");
			nombre.put("ESTADO", "");
		}
		return nombre;
	}
	
	@SuppressWarnings("unchecked")
	public List<CargasVO> consultaCargasAutorizadas(ParamVO param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List<CargasVO> queryForList = sqlMap.queryForList("cycservices.consultaCargasNoAutorizadasTrabajador", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CorreoVO> consultaCorreosCargasAutorizadas(int periodo) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {
			Map param= new HashMap();
			param.put("periodo", periodo);
			List<CorreoVO> queryForList = sqlMap.queryForList("cycservices.consultaCorreosCargas", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CotizacionesVO> consultaCotizaciones(ParamVO param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List<CotizacionesVO> queryForList = sqlMap.queryForList("cycservices.consultaNoCotizadosTrabajador", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CorreoVO> consultaCorreosCotizaciones(int periodo) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {
			Map param= new HashMap();
			param.put("periodo", periodo);
			List<CorreoVO> queryForList = sqlMap.queryForList("cycservices.consultaCorreosCotizaciones", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public CotizacionesVO consultaTrabajador(ParamVO param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			CotizacionesVO queryForObject = (CotizacionesVO)sqlMap.queryForObject("cycservices.consultaTrabajador", param);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CotizacionesVO> consultaListaTrabajadores(Map param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List<CotizacionesVO> queryForList = sqlMap.queryForList("cycservices.consultaListaTrabajadores", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public CotizacionesVO consultaCertificadoTrabajador(ParamVO param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			CotizacionesVO queryForObject = (CotizacionesVO)sqlMap.queryForObject("cycservices.consultaCertificadoTrabajador", param);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<InformeSAPVO> generaArchivoSAP() throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List<InformeSAPVO> queryForList = sqlMap.queryForList("cycservices.archivoSAP", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CorreoVO> consultaCorreos(int rutEmpresa) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List<CorreoVO> queryForList = sqlMap.queryForList("cycservices.consultaCorreos", rutEmpresa);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public int updateTrabajador(Map param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			int result = sqlMap.update("cycservices.updateEstadoTrabajador", param);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public int updateCargas(Map param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			int result = sqlMap.update("cycservices.updateEstadoCargas", param);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public int updateEstadoTrabajadorSIAGF(Map param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			int result = sqlMap.update("cycservices.updateEstadoSIAGF", param);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return 0;
	}
	
	
	@SuppressWarnings("unchecked")
	public int updateTrabajadores(List<CotizacionesVO> registros) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		int resultado=0;

		try {
			for (Iterator iterator = registros.iterator(); iterator.hasNext();) {
				CotizacionesVO cotizacionesVO = (CotizacionesVO) iterator
						.next();
				Map param= new HashMap();
				param.put("fechaDesvinculacion", cotizacionesVO.getFechaDesvinculacion());
				param.put("rutTrabajador", cotizacionesVO.getRutTrabajador());
				param.put("rutEmpresa", cotizacionesVO.getRutEmpresa());
				resultado += sqlMap.update("cycservices.updateEstadoTrabajador", param);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public int updateCargas(List<CotizacionesVO> registros) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		int resultado=0;

		try {
			for (Iterator iterator = registros.iterator(); iterator.hasNext();) {
				CotizacionesVO cotizacionesVO = (CotizacionesVO) iterator.next();
				Map param= new HashMap();
				param.put("fechaDesvinculacion", cotizacionesVO.getDateDesvinculacion());
				param.put("rutTrabajador", cotizacionesVO.getRutTrabajador());
				
				resultado += sqlMap.update("cycservices.updateEstadoCargas", param);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return resultado;
	}
	
	public Integer insertBitacora(Map<String, String> param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			Object resultado = sqlMap.insert("cycservices.insertBitacora", param);
			if(resultado!= null){
				return (Integer)resultado;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return 0;
	}

	public int deleteCorreoByID(String id) throws Exception {
		SqlMapClient sqlMap = null;
		int result=0;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			result = sqlMap.delete("cycservices.deleteCorreoByID", id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public int deleteCorreoByRUT(int rut) throws Exception {
		SqlMapClient sqlMap = null;
		int result=0;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			result = sqlMap.delete("cycservices.deleteCorreoByRUT", rut);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public int deleteCorreoVacio(int rut) throws Exception {
		SqlMapClient sqlMap = null;
		int result=0;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			result = sqlMap.delete("cycservices.deleteCorreoVacio", rut);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public Integer insertCorreo(CorreoVO correoVO) throws Exception {
		SqlMapClient sqlMap = null;
		Integer resultado=null;
				try {
					sqlMap = MyClassSqlConfig.getSqlMapInstance();
				} catch (Exception e) {			
					throw new Exception("Error al conectar al Datasource");
				}
				
				try {			
					Object result = sqlMap.insert("cycservices.insertCorreo", correoVO);
					if(result!= null){
						resultado= (Integer)result;
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
				return resultado;
			}

}
