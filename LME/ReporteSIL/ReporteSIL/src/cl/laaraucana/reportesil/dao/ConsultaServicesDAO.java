package cl.laaraucana.reportesil.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import cl.laaraucana.reportesil.dao.vo.BitacoraVO;
import cl.laaraucana.reportesil.dao.vo.ImpuestoVO;
import cl.laaraucana.reportesil.dao.vo.PeriodosRentaVO;
import cl.laaraucana.reportesil.dao.vo.RentasVO;
import cl.laaraucana.reportesil.dao.vo.ResumenLicenciaVO;
import cl.laaraucana.reportesil.dao.vo.TasaPrevisionalVO;
import cl.laaraucana.reportesil.dao.vo.TasaSISVO;
import cl.laaraucana.reportesil.ibatis.MyClassSqlConfig;


import com.ibatis.sqlmap.client.SqlMapClient;


public class ConsultaServicesDAO {
	protected static Logger logger = Logger.getLogger("ConsultaCargasDAO");
	
	public String nombreAfiliado(int rutAfiliado) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			String queryForObject = (String)sqlMap.queryForObject("reportesil.nombreAfiliado", rutAfiliado);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new Exception();
		}
	}
	
	public List<ResumenLicenciaVO> consultaLicencias(Map<String, Integer> param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List<ResumenLicenciaVO> queryForList = (List)sqlMap.queryForList("reportesil.consultaLicencias", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new Exception();
		}
	}
	
	public List<RentasVO> consultaRentas(Map<String, Integer> param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List<RentasVO> queryForList = (List)sqlMap.queryForList("reportesil.consultaRentas", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new Exception();
		}
	}
	
	public String consultaReliquidada(Map<String, Integer> param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			String queryForObject = (String)sqlMap.queryForObject("reportesil.consultaReliquidada", param);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new Exception();
		}
	}
	
	public List<HashMap> remuneracionesxPeriodo(Map<String, String> param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List<HashMap> queryForList = (List)sqlMap.queryForList("reportesil.remuneracionesxperiodo", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new Exception();
		}
	}
	
	public List<TasaPrevisionalVO> tasasPrevisonales(Map<String, Integer> param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List<TasaPrevisionalVO> queryForList = (List)sqlMap.queryForList("reportesil.tasasprevisionales", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new Exception();
		}
	}
	
	public List<TasaSISVO> tasasSIS() throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List<TasaSISVO> queryForList = (List)sqlMap.queryForList("reportesil.tasasSIS", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new Exception();
		}
	}
	
	public double tasaCesantia(Map<String, String> param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			Double queryForObject = (Double)sqlMap.queryForObject("reportesil.seguroCesantia", param);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new Exception();
		}
	}
	
	public double montoDiarioMinimo(Map<String, Integer> param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			Double queryForObject = (Double)sqlMap.queryForObject("reportesil.montodiariominimo", param);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new Exception();
		}
	}
	
	public List<ImpuestoVO> impuestoRenta(Map<String, Integer> param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List<ImpuestoVO> queryForList = (List)sqlMap.queryForList("reportesil.calculoImpuesto", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new Exception();
		}
	}
	
	public Map codigoPago_IPC(Map<String, String> param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			HashMap queryForObject = (HashMap)sqlMap.queryForObject("reportesil.codigoPagoeIPC", param);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new Exception();
		}
	}
	
	
	public BitacoraVO consultaBitacora(int licencia) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			BitacoraVO queryForObject = (BitacoraVO)sqlMap.queryForObject("reportesil.consultaRegistro", licencia);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new Exception();
		}
	}
	
	public Integer insertResumen(ResumenLicenciaVO resumen) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {
			int deleting= sqlMap.delete("reportesil.deleteContacto", resumen);
			Object resultado = sqlMap.insert("reportesil.insertContactoAfiliado", resumen);
			deleting= sqlMap.delete("reportesil.deleteRentas", resumen);
			for (Iterator iterator = resumen.getRentas().iterator(); iterator.hasNext();) {
				RentasVO renta = (RentasVO) iterator.next();
				renta.setNuminterno(resumen.getNuminterno());
				renta.setRutAfiliado(resumen.getRutAfiliado());
				renta.setFechaHasta(resumen.getFechaHastaInt());
				resultado = sqlMap.insert("reportesil.insertOtrasRentasAfiliado", renta);
			}
			if(resultado!= null){
				return (Integer)resultado;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return 0;
	}
	
	/*public Integer updateOtrasRentas(Map<String, String> param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {
			Object resultado = sqlMap.update("reportesil.updateOtrasRentasAfiliado", param);
			if(resultado!= null){
				return (Integer)resultado;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return 0;
	}*/
	
	public Integer insertBitacora(BitacoraVO registro) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {
			Object resultado = sqlMap.insert("reportesil.insertBitacora", registro);
			if(resultado!= null){
				return (Integer)resultado;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return 0;
	}
	
	public int isRentaCotizaciones(Map<String, Integer> param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			Integer queryForObject = (Integer)sqlMap.queryForObject("reportesil.isRentasCotizaciones", param);
			return queryForObject.intValue();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new Exception();
		}
	}

}
