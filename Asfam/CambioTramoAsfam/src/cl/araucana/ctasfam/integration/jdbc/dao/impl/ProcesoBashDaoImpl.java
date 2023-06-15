package cl.araucana.ctasfam.integration.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.araucana.ctasfam.business.to.ProcesoBashTO;
import cl.araucana.ctasfam.presentation.struts.vo.PeticionProcesamientoDto;
import cl.araucana.ctasfam.resources.util.CustomDataSource;


public class ProcesoBashDaoImpl  {
	
	private static final String INSERT_BASH = "INSERT INTO AFDTA.AFP66F1(AFP7A, AFOVA, AFP66ARC, AFP66EST, SAJKM94, OBF002, OBF003, AFP66TRI, AFP66ORI, AFP66CIN) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String SELECT_PETICION_PROCESAMIENTO_BY_EMPRESA = "SELECT * FROM AFDTA.AFP66F1 WHERE AFOVA = ?";
	
	private static final Log log = LogFactory
			.getLog(ProcesoBashDaoImpl.class);
	
	private static Properties Config = null;
	
	
	public ProcesoBashDaoImpl()  {
		
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
			ps = cn.prepareStatement(ProcesoBashDaoImpl.INSERT_BASH);
			ps.setInt(1, procesoBashTO.getPeriodo());
			ps.setInt(2, procesoBashTO.getEmpresa());
			ps.setString(3, procesoBashTO.getRutaArchivo());
			ps.setString(4, procesoBashTO.getEstado());
			ps.setString(5, procesoBashTO.getUsuarioSube());
			ps.setDate(6, Date.valueOf(procesoBashTO.getFechaSubida()));
			ps.setTime(7, Time.valueOf(procesoBashTO.getHoraSubida()));
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
			ps = cn.prepareStatement(ProcesoBashDaoImpl.SELECT_PETICION_PROCESAMIENTO_BY_EMPRESA);
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
					obj.setFechaProcesamiento((rs.getTimestamp("AFP66FPA")!=null)?new Date(rs.getTimestamp("AFP66FPA").getTime()):null);
					obj.setTotalRegProcesados(rs.getInt("AFP66TRP"));
					obj.setCantidadIntentos(rs.getInt("AFP66CIN"));
					obj.setOrigen(rs.getString("AFP66ORI"));
					
					java.sql.Date creationDate = rs.getDate("OBF002");
					java.sql.Time creationTime = rs.getTime("OBF003");
//					java.sql.Date lasChangeDate = rs.getDate("OBF005");
//					java.sql.Time lasChangeTime = rs.getTime("OBF006");
					
					obj.setFechaSubida(new Date(creationDate.getTime() + creationTime.getTime()));
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
	

}
