package cl.araucana.ctasfam.batch.dao.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import cl.araucana.ctasfam.batch.common.dto.CantidadPropuestasAfiliadoDto;

public class CantidadPropuestasExtractor {
	public CantidadPropuestasAfiliadoDto extractor(ResultSet rs) throws SQLException{
		CantidadPropuestasAfiliadoDto obj = new CantidadPropuestasAfiliadoDto();
		
		obj.setOrigen(rs.getString("AFP6A"));
		obj.setSecDigit(rs.getInt("AFAMA"));
		obj.setCantidad(rs.getInt("COUNT"));
		
		return obj;
	}
}
