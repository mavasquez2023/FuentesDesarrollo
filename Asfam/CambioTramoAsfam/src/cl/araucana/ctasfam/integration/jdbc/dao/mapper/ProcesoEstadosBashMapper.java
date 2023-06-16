package cl.araucana.ctasfam.integration.jdbc.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import cl.araucana.ctasfam.business.to.ProcesoBashTO;

public class ProcesoEstadosBashMapper implements RowMapper {
	
	public ProcesoBashTO mapRow(ResultSet rs, int count) throws SQLException {
	    ProcesoBashTO p = new ProcesoBashTO();
		p.setEmpresa(rs.getInt("AFOVA"));
		p.setRutaArchivo(rs.getString("AFP66ARC"));
		p.setFechaSubida(rs.getString("OBF002"));
		p.setHoraSubida(rs.getString("OBF003"));
		p.setCantidadIntento(rs.getInt("AFP66TRP"));
		p.setEstado(rs.getString("AFP66EST"));
		p.setFechaProcesamiento(rs.getTimestamp("AFP66FPA"));
		p.setRegistrosInformados(rs.getInt("AFP66TRI"));
		return p;	
	
	}

}
