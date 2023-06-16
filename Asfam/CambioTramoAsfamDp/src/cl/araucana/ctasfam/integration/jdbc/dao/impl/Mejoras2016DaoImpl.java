package cl.araucana.ctasfam.integration.jdbc.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.araucana.ctasfam.business.to.DetalleExcelTO;
import cl.araucana.ctasfam.business.to.ProcesoBashTO;
import cl.araucana.ctasfam.business.to.ResumenExcelTO;
import cl.araucana.ctasfam.integration.jdbc.dao.mapper.ProcesoEstadosBashResumenMapper;
import cl.araucana.ctasfam.integration.jdbc.dao.mapper.RowMapper;
import cl.araucana.ctasfam.integration.jdbc.exception.RentaPropuestasException;
import cl.araucana.ctasfam.presentation.struts.vo.PeticionProcesamientoDto;
import cl.araucana.ctasfam.resources.util.CustomDataSource;

public class Mejoras2016DaoImpl {
	
	private static final String INSERT_BASH = "INSERT INTO AFDTA.AFP66F1(AFP7A, AFOVA, AFP66ARC, AFP66EST, SAJKM94, OBF002, OBF003, AFP66TRI, AFP66ORI, AFP66CIN) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String SELECT_PETICION_PROCESAMIENTO_BY_EMPRESA = "SELECT * FROM AFDTA.AFP66F1 WHERE AFOVA = ?";
	
	private static final String SELECT_USUARIOS_ADMIN = "SELECT AFP80VAL FROM AFDTA.AFP80F1 WHERE AFP80COD = 'USUARIOS_ADMIN'";
	
	private static final String UPDATE_FEC_CAMBIO_TRAMO_INIC = "UPDATE AFDTA.AFP80F1 SET AFP80VAL = ? WHERE AFP80COD = 'FEC_CORTE_INIC'";
	
	private static final String SELECT_FEC_CAMBIO_TRAMO = "SELECT AFP80COD, AFP80VAL FROM AFDTA.AFP80F1";
	
	private static final String UPDATE_FEC_CAMBIO_TRAMO_FIN = "UPDATE AFDTA.AFP80F1 SET AFP80VAL = ? WHERE AFP80COD = 'FEC_CORTE_FIN'";
	
	private static final String SELECT_ESTADO_PROCESOS_BASH_RESUMEN = "SELECT AFOVA, AFP66ARC, OBF002, OBF003, AFP66TRP, AFP66EST, AFP66FPA, AFP66TRI   FROM AFDTA.AFP66F1 WHERE AFOVA = ?";
    
	//private static final String SELECT_RESUMEN_XLS = "SELECT 'PROCESADAS' , SUM(AFP66TRP), COUNT(1) FROM AFDTA.AFP66F1 WHERE AFOVA IN(?) AND AFP66EST = 'F' UNION SELECT 'EN PROCESO' , sum(AFP66TRP), COUNT(1) FROM AFDTA.AFP66F1 WHERE AFOVA IN(?) AND AFP66EST != 'F'";
	private static final String SELECT_RESUMEN_XLS_2 = "SELECT 'PROCESADAS' , SUM(AFP66TRP), COUNT(1) FROM AFDTA.AFP66F1 WHERE AFP66ORI = 'C' AND AFP66EST = 'F' UNION SELECT 'EN PROCESO', sum(AFP66TRP), COUNT(1) FROM AFDTA.AFP66F1 WHERE AFP66ORI = 'C' AND AFP66EST != 'F'";
	
	//private static final String SELECT_DETALLE_XLS = "SELECT AFOVA, RAZSOC, AFOYA, AFOZA, AFP66TRP, AFP66EST FROM AFDTA.AFP66F1 INNER JOIN DUDTA.LDAP2000 ON AFOVA = SUBSTR(RUTEMP,1,(LENGTH(RUTEMP))-3) WHERE AFOVA IN (?) GROUP BY AFP66EST,AFOVA, RAZSOC, AFOYA, AFOZA, AFP66TRP";
	private static final String SELECT_DETALLE_XLS_2 = "SELECT AFOVA, RAZSOC, AFOYA, AFOZA, AFP66TRP, AFP66EST FROM AFDTA.AFP66F1 INNER JOIN DUDTA.LDAP2000 ON CAST(AFOVA AS VARCHAR(10)) = SUBSTR(RUTEMP,1,(LENGTH(RUTEMP))-3) WHERE AFP66ORI = 'C' GROUP BY AFP66EST,AFOVA, RAZSOC, AFOYA, AFOZA, AFP66TRP";
	
	
	private static final Log log = LogFactory
			.getLog(Mejoras2016DaoImpl.class);
	
	private static Properties Config = null;
	
	
	public Mejoras2016DaoImpl()  {
		
		if (Config == null) {
			Config = new Properties();

			try {
				Config.load(getClass().getClassLoader().getResourceAsStream(
						"configuracion.properties"));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean insertBash(ProcesoBashTO procesoBashTO){
		

		PreparedStatement ps = null;
		CustomDataSource cds = new CustomDataSource();
		Connection cn = null;
		try {
			cds.openConnection();
			cn = cds.getConnection();
			ps = cn.prepareStatement(Mejoras2016DaoImpl.INSERT_BASH);
			ps.setInt(1, procesoBashTO.getPeriodo());
			ps.setInt(2, procesoBashTO.getEmpresa());
			ps.setString(3, procesoBashTO.getRutaArchivo());
			ps.setString(4, procesoBashTO.getEstado());
			ps.setString(5, procesoBashTO.getUsuarioSube());
			ps.setDate(6, java.sql.Date.valueOf(procesoBashTO.getFechaSubida()));
			ps.setTime(7, java.sql.Time.valueOf(procesoBashTO.getHoraSubida()));
			ps.setInt(8, procesoBashTO.getRegistrosInformados());
			ps.setString(9, procesoBashTO.getOrigen());
			ps.setInt(10, procesoBashTO.getCantidadIntento());
			
			

			int result = ps.executeUpdate();
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			log.error("Error: al insertar informacion para proceso bash, "
					+ e.getLocalizedMessage(), e);
		} catch (Exception e) {
			log.error("Error: al insertar informacion para proceso bash, "
					+ e.getLocalizedMessage(), e);
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				log.error("Error: al finalizar conexiones, "
						+ e.getLocalizedMessage(), e);

			}
		}
		
		
		return false;
	}

	public List<PeticionProcesamientoDto> selectPeticionProcesamiento(int rutEmpresa){
		List<PeticionProcesamientoDto> listResult = new ArrayList<PeticionProcesamientoDto>();
		
		CustomDataSource cds = new CustomDataSource();
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cds.openConnection();
			cn = cds.getConnection();
			ps = cn.prepareStatement(Mejoras2016DaoImpl.SELECT_PETICION_PROCESAMIENTO_BY_EMPRESA);
			ps.setInt(1, rutEmpresa);
			
			

			ResultSet rs = ps.executeQuery();
			if(rs != null){
				while(rs.next()){
					PeticionProcesamientoDto obj = new PeticionProcesamientoDto();
					obj.setIdPeticionProcesamiento(rs.getInt("AFP66ID"));
					obj.setPeriodo(rs.getInt("AFP7A"));
					obj.setRutEmpresa(rs.getInt("AFOVA"));
					obj.setOficina(rs.getInt("AFOYA"));
					obj.setSucursal(rs.getInt("AFOZA"));
					obj.setEstado(rs.getString("AFP66EST"));
					obj.setRutaArchivo(rs.getString("AFP66ARC").trim());
					
					obj.setTotalRegInformados(rs.getInt("AFP66TRI"));
					obj.setFechaProcesamiento((rs.getTimestamp("AFP66FPA")!=null)?new java.sql.Date(rs.getTimestamp("AFP66FPA").getTime()):null);
					obj.setTotalRegProcesados(rs.getInt("AFP66TRP"));
					obj.setCantidadIntentos(rs.getInt("AFP66CIN"));
					obj.setOrigen(rs.getString("AFP66ORI"));
					
					Date creationDate = new Date(rs.getDate("OBF002").getTime());
					String creationTime = rs.getTime("OBF003").toString();
//					java.sql.Date lasChangeDate = rs.getDate("OBF005");
//					java.sql.Time lasChangeTime = rs.getTime("OBF006");
					
					SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sdfDateComplet = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date fechaSubida = sdfDateComplet.parse(sdfDate.format(creationDate) + " " + creationTime);
					
					obj.setFechaSubida(fechaSubida);
					
					obj.setUsuario(rs.getString("SAJKM94"));
					listResult.add(obj);
				}
			}
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			
			return listResult;
		} catch (SQLException e) {
			log.error("Error: al leer informacion de peticion de proceasmiento, " + e.getLocalizedMessage(), e);
		} catch (Exception e) {
			log.error("Error: al leer informacion de peticion de proceasmiento, " + e.getLocalizedMessage(), e);
		}
		return listResult;
	}
	
	public List selectUsuariosAdmin()throws RentaPropuestasException{
		CustomDataSource cds = new CustomDataSource();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List result = new ArrayList();
		try {
			cds.openConnection();
			cn = cds.getConnection();
			
			ps = cn.prepareStatement(SELECT_USUARIOS_ADMIN);
			rs = ps.executeQuery();
			while (rs.next()) {
				result.add(rs.getString("AFP80VAL"));  //AQUI
			}
		} catch (SQLException e) {
			log.error("Error: consulta usuarios admin dao impl, "
					+ e.getLocalizedMessage(), e);
		} catch (Exception e) {
			log.error("Error: consulta usuarios admin dao impl, "
					+ e.getLocalizedMessage(), e);
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				log.error("Error: al finalizar conexiones, "
						+ e.getLocalizedMessage(), e);
				throw new RentaPropuestasException(
						"Error: al finalizar conexiones, "
								+ e.getLocalizedMessage());
			}
		}
		return result;
	}
	
	
	public Map<String, String> selectFecha()throws RentaPropuestasException{
		CustomDataSource cds = new CustomDataSource();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, String> params = new HashMap<String, String>();
		try {
			cds.openConnection();
			cn = cds.getConnection();
			
			ps = cn.prepareStatement(SELECT_FEC_CAMBIO_TRAMO);
			rs = ps.executeQuery();
			while (rs.next()) {
				params.put(rs.getString("AFP80COD").trim(), rs.getString("AFP80VAL").trim());
			}
		} catch (SQLException e) {
			log.error("Error: al seleccionar una ó mas fechas, "
					+ e.getLocalizedMessage(), e);
		} catch (Exception e) {
			log.error("Error: al seleccionar una ó mas fechas, "
					+ e.getLocalizedMessage(), e);
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				log.error("Error: al finalizar conexiones, "
						+ e.getLocalizedMessage(), e);
				throw new RentaPropuestasException(
						"Error: al finalizar conexiones, "
								+ e.getLocalizedMessage());
			}
		}
		return params;
	}
	
	public int updateFechaInicio(String fechaInicio) throws RentaPropuestasException{
		CustomDataSource cds = new CustomDataSource();
		Connection cn = null;
		PreparedStatement ps = null;
		int update = 0;

		try {
			cds.openConnection();
			cn = cds.getConnection();
			
			ps = cn.prepareStatement(UPDATE_FEC_CAMBIO_TRAMO_INIC);
			ps.setString(1, fechaInicio);
			
			if (ps.executeUpdate() > 0) {
				
				update = 1; 
			}
			
			else {
				
				update = 2; 
			}
				

		} catch (SQLException e) {
			log.error("Error: al seleccionar una ó mas fechas, "
					+ e.getLocalizedMessage(), e);
		} catch (Exception e) {
			log.error("Error: al seleccionar una ó mas fechas, "
					+ e.getLocalizedMessage(), e);
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				log.error("Error: al finalizar conexiones, "
						+ e.getLocalizedMessage(), e);
				throw new RentaPropuestasException(
						"Error: al finalizar conexiones, "
								+ e.getLocalizedMessage());
			}
		}
		return update;
	}
	
	public int updateFechafin(String fechaFin) throws RentaPropuestasException{
		CustomDataSource cds = new CustomDataSource();
		Connection cn = null;
		PreparedStatement ps = null;
		int update = 0;

		try {
			cds.openConnection();
			cn = cds.getConnection();
			
			ps = cn.prepareStatement(UPDATE_FEC_CAMBIO_TRAMO_FIN);
			ps.setString(1, fechaFin);
			
			if (ps.executeUpdate() > 0) {
				
				update = 1; 
			}
			
			else {
				
				update = 2; 
			}
				

		} catch (SQLException e) {
			log.error("Error: al seleccionar una ó mas fechas, "
					+ e.getLocalizedMessage(), e);
		} catch (Exception e) {
			log.error("Error: al seleccionar una ó mas fechas, "
					+ e.getLocalizedMessage(), e);
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				log.error("Error: al finalizar conexiones, "
						+ e.getLocalizedMessage(), e);
				throw new RentaPropuestasException(
						"Error: al finalizar conexiones, "
								+ e.getLocalizedMessage());
			}
		}
		return update;
	}
	
	
	public List selectEstadoProcesoResumen(String usuario)
			throws RentaPropuestasException {
		CustomDataSource cds = new CustomDataSource();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List result = new ArrayList();
		RowMapper rm = new ProcesoEstadosBashResumenMapper();
		try {
			cds.openConnection();
			cn = cds.getConnection();
			
			ps = cn.prepareStatement(SELECT_ESTADO_PROCESOS_BASH_RESUMEN);
			ps.setString(1, usuario);
			rs = ps.executeQuery();
			while (rs.next()) {
				result.add(rm.mapRow(rs, 0)); 
			}
		} catch (SQLException e) {
			log.error(
					"Error: al seleccionar una ó mas fechas, "
							+ e.getLocalizedMessage(), e);
		} catch (Exception e) {
			log.error(
					"Error: al seleccionar una ó mas fechas, "
							+ e.getLocalizedMessage(), e);
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				log.error(
						"Error: al finalizar conexiones, "
								+ e.getLocalizedMessage(), e);
				throw new RentaPropuestasException(
						"Error: al finalizar conexiones, "
								+ e.getLocalizedMessage());
			}
		}
		return result;
	}
	
	public List selectResumenXLS(String empresas)throws RentaPropuestasException{
		CustomDataSource cds = new CustomDataSource();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ResumenExcelTO> result = new ArrayList<ResumenExcelTO>();
		ResumenExcelTO resumen = null;
		try {
			cds.openConnection();
			cn = cds.getConnection();
			
			ps = cn.prepareStatement(SELECT_RESUMEN_XLS_2);
//			ps.setString(1, empresas);
//			ps.setString(2, empresas);
			rs = ps.executeQuery();
			while (rs.next()) {
				resumen = new ResumenExcelTO();
				resumen.setEstado(rs.getString(1));
				resumen.setTotalProcesados(rs.getInt(2));
				resumen.setCantidadEmpresas(rs.getInt(3));
				result.add(resumen);
			}
		} catch (SQLException e) {
			log.error("Error: al seleccionar una ó mas fechas, "
					+ e.getLocalizedMessage(), e);
		} catch (Exception e) {
			log.error("Error: al seleccionar una ó mas fechas, "
					+ e.getLocalizedMessage(), e);
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				log.error("Error: al finalizar conexiones, "
						+ e.getLocalizedMessage(), e);
				throw new RentaPropuestasException(
						"Error: al finalizar conexiones, "
								+ e.getLocalizedMessage());
			}
		}
		return result;
	}
	
	
	
	public List selectDetalleXLS(String empresas)throws RentaPropuestasException{
		CustomDataSource cds = new CustomDataSource();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<DetalleExcelTO> result = new ArrayList<DetalleExcelTO>();
		DetalleExcelTO detalle = null;
		try {
			cds.openConnection();
			cn = cds.getConnection();
			
			ps = cn.prepareStatement(SELECT_DETALLE_XLS_2);
//			ps.setString(1, empresas);
			rs = ps.executeQuery();
			while (rs.next()) {
				detalle = new DetalleExcelTO();
				detalle.setRut(rs.getInt(1));
				detalle.setNombre(rs.getString(2));
				String oficina = rs.getString(3);
				if (oficina != null) {
					detalle.setOficina(rs.getString(3));
				}
				else{
					detalle.setOficina("");
				}
				String sucursal = rs.getString(4);
				if (sucursal != null) {
					detalle.setSucursal(rs.getString(4));;
				}
				else{
					detalle.setSucursal("");;
				}
				detalle.setTotal(rs.getInt(5));
				detalle.setEstado(rs.getString(6));
				result.add(detalle);
			}
		} catch (SQLException e) {
			log.error("Error: al seleccionar una ó mas fechas, "
					+ e.getLocalizedMessage(), e);
		} catch (Exception e) {
			log.error("Error: al seleccionar una ó mas fechas, "
					+ e.getLocalizedMessage(), e);
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				log.error("Error: al finalizar conexiones, "
						+ e.getLocalizedMessage(), e);
				throw new RentaPropuestasException(
						"Error: al finalizar conexiones, "
								+ e.getLocalizedMessage());
			}
		}
		return result;
	}
}
