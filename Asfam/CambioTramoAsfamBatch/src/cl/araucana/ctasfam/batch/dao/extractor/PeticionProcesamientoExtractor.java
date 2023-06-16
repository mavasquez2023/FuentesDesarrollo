package cl.araucana.ctasfam.batch.dao.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import cl.araucana.ctasfam.batch.common.dto.PeticionProcesamientoDto;

public class PeticionProcesamientoExtractor {
	public PeticionProcesamientoDto extractor(ResultSet rs) throws SQLException{
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
//		java.sql.Date lasChangeDate = rs.getDate("OBF005");
//		java.sql.Time lasChangeTime = rs.getTime("OBF006");
		
		obj.setFechaSubida(new Date(creationDate.getTime() + creationTime.getTime()));
		obj.setUsuario(rs.getString("SAJKM94"));
		
		return obj;
	}
}
