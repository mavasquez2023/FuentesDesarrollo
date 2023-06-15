package cl.laaraucana.resultadonrp.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cl.laaraucana.resultadonrp.dao.vo.ArchivosVO;
import cl.laaraucana.resultadonrp.dao.vo.ConceptoGenVO;
import cl.laaraucana.resultadonrp.dao.vo.ConceptoVO;
import cl.laaraucana.resultadonrp.dao.vo.CorreoVO;
import cl.laaraucana.resultadonrp.dao.vo.FolioVO;
import cl.laaraucana.resultadonrp.dao.vo.ProductoVO;
import cl.laaraucana.resultadonrp.dao.vo.ResumenConsolidacionVO;
import cl.laaraucana.resultadonrp.dao.vo.ResumenDisponibilizacionVO;
import cl.laaraucana.resultadonrp.dao.vo.ResumenGeneracionVO;
import cl.laaraucana.resultadonrp.ibatis.MyClassSqlConfig;


import com.ibatis.sqlmap.client.SqlMapClient;


public class ConsultaServicesDAO {
	protected static Logger logger = Logger.getLogger("ConsultaCargasDAO");
	
	public int deleteConsolidacion(String periodo) throws Exception {
		SqlMapClient sqlMap = null;
		int result=0;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			result = sqlMap.delete("resultadonrp.deleteConsolidacionCabecera", periodo);
			result = sqlMap.delete("resultadonrp.deleteConsolidacionDetalle", periodo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public int deleteGeneracion(String periodo) throws Exception {
		SqlMapClient sqlMap = null;
		int result=0;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			result = sqlMap.delete("resultadonrp.deleteGeneracionCabecera", periodo);
			result = sqlMap.delete("resultadonrp.deleteGeneracionDetalle", periodo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public int deleteDisponibilizacion(String periodo) throws Exception {
		SqlMapClient sqlMap = null;
		int result=0;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			result = sqlMap.delete("resultadonrp.deleteDisponibilizacion", periodo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> consultaConceptos(String tipo) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List<String> queryForList = sqlMap.queryForList("resultadonrp.consultaConceptos", tipo);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductoVO> consultaProductos(String periodo) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {
			Map<String, String> param= new HashMap<String, String>();
			param.put("periodo", periodo);
			List<ProductoVO> queryForList = sqlMap.queryForList("resultadonrp.consultaProductos", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<FolioVO> consultaFolios(String periodo) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {
			Map<String, String> param= new HashMap<String, String>();
			param.put("periodo", periodo);
			List<FolioVO> queryForList = sqlMap.queryForList("resultadonrp.consultaFolios", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Long> consultaTotalesGenerales() throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			HashMap queryForList = (HashMap)sqlMap.queryForObject("resultadonrp.consultaTotalesRegistros", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Long> consultaTotalesFolios() throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			HashMap queryForList = (HashMap)sqlMap.queryForObject("resultadonrp.consultaTotalesFolios", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<ResumenConsolidacionVO> consultaResumenConsolidacion(String periodo, String periodo_anterior) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {
			HashMap<String, String> param = new HashMap<String, String>();
			param.put("periodo", periodo);
			param.put("periodo_anterior", periodo_anterior);
			List<ResumenConsolidacionVO> queryForList = sqlMap.queryForList("resultadonrp.consultaResumenConsolidacion", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<ArchivosVO> consultaArchivosGeneracion(String periodo) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {
			List<ArchivosVO> queryForList = sqlMap.queryForList("resultadonrp.consultaArchivosGeneracion", periodo);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<ResumenGeneracionVO> consultaResumenGeneracion(String periodo, String periodo_anterior) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {
			HashMap<String, String> param = new HashMap<String, String>();
			param.put("periodo", periodo);
			param.put("periodo_anterior", periodo_anterior);
			List<ResumenGeneracionVO> queryForList = sqlMap.queryForList("resultadonrp.consultaResumenGeneracion", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public List<ResumenDisponibilizacionVO> consultaResumenDisponibilizacion(String periodo) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {
			HashMap<String, String> param = new HashMap<String, String>();
			param.put("periodo", periodo);
			List<ResumenDisponibilizacionVO> queryForList = sqlMap.queryForList("resultadonrp.consultaResumenDisponibilizacion", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List estadisticasRegistrosConsolidacion(String periodo) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List queryForList = (List)sqlMap.queryForList("resultadonrp.estadisticasRegistrosConsolidacion", periodo);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List estadisticasMontoConsolidacion(String periodo) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List queryForList = (List)sqlMap.queryForList("resultadonrp.estadisticasMontoConsolidacion", periodo);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List estadisticasArchivosGeneracion(String periodo) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List queryForList = (List)sqlMap.queryForList("resultadonrp.estadisticasArchivosGeneracion", periodo);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List estadisticasMontoGeneracion(String periodo) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List queryForList = (List)sqlMap.queryForList("resultadonrp.estadisticasMontoGeneracion", periodo);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List estadisticasArchivosDisponibilizacion(String periodo) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List queryForList = (List)sqlMap.queryForList("resultadonrp.estadisticasArchivosDisponibilizacion", periodo);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CorreoVO> consultaCorreos(String proceso) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {
			Map param= new HashMap();
			param.put("proceso", proceso);
			List<CorreoVO> queryForList = sqlMap.queryForList("resultadonrp.consultaCorreosUsuarios", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public Integer insertConceptos(List<ConceptoVO> conceptos) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {
			Object resultado;
			HashMap<String, List<ConceptoVO>> param= new HashMap();
			param.put("List", conceptos);
			for (Iterator iterator = conceptos.iterator(); iterator.hasNext();) {
				ConceptoVO conceptoVO = (ConceptoVO) iterator.next();
				if(conceptoVO.getConcepto().equals("TOTGEN")){
					resultado = sqlMap.insert("resultadonrp.insertConsolidadoCabecera", conceptoVO);
					break;
				}
			}
			resultado = sqlMap.insert("resultadonrp.insertConsolidadoDetalle", param);
			if(resultado!= null){
				return (Integer)resultado;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return 0;
	}
	
	//Inser cabecera Generación
	public Integer insertConceptosGeneracion(List<ConceptoGenVO> conceptos) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {
			Object resultado;
			HashMap<String, List<ConceptoGenVO>> param= new HashMap();
			param.put("List", conceptos);
			resultado = sqlMap.insert("resultadonrp.insertGeneracionCabecera", param);
			if(resultado!= null){
				return (Integer)resultado;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return 0;
	}
	
	public Integer insertGeneracion(String periodo) throws Exception {
		SqlMapClient sqlMap = null;
		Integer resultado=0;
				try {
					sqlMap = MyClassSqlConfig.getSqlMapInstance();
				} catch (Exception e) {			
					throw new Exception("Error al conectar al Datasource");
				}
				
				try {			
					sqlMap.insert("resultadonrp.insertDetalleGeneracion", periodo);
					resultado= (Integer)sqlMap.queryForObject("resultadonrp.countDetalleGeneracion", periodo);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
				return resultado;
			}
	
	public Integer insertDisponibilizacion(ResumenDisponibilizacionVO param) throws Exception {
		SqlMapClient sqlMap = null;
		Integer resultado=null;
				try {
					sqlMap = MyClassSqlConfig.getSqlMapInstance();
				} catch (Exception e) {			
					throw new Exception("Error al conectar al Datasource");
				}
				
				try {			
					Object result = sqlMap.insert("resultadonrp.insertDisponibilizacion", param);
					if(result!= null){
						resultado= (Integer)result;
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
				return resultado;
			}
		
	public int deleteCorreoByID(int id) throws Exception {
		SqlMapClient sqlMap = null;
		int result=0;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			result = sqlMap.delete("resultadonrp.deleteCorreoByID", id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public int deleteCorreoProceso(CorreoVO correo) throws Exception {
		SqlMapClient sqlMap = null;
		int result=0;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {
			result = sqlMap.delete("resultadonrp.deleteUsuarioByCorreoProceso", correo);
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
					Object result = sqlMap.insert("resultadonrp.insertCorreo", correoVO);
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
