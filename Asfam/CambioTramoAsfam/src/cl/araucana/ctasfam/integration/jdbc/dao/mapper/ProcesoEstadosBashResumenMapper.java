package cl.araucana.ctasfam.integration.jdbc.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import cl.araucana.ctasfam.business.to.ProcesoBashTO;

public class ProcesoEstadosBashResumenMapper implements RowMapper {
	
	public ProcesoBashTO mapRow(ResultSet rs, int count) throws SQLException {
	    ProcesoBashTO p = new ProcesoBashTO();
		p.setRutaArchivo(rs.getString("AFP66ARC"));
		p.setFechaSubida(rs.getString("OBF002"));
		p.setRegistrosInformados(rs.getInt("AFP66TRI"));
		p.setEstado(rs.getString("AFP66EST"));
		return p;	
	
	}

}
