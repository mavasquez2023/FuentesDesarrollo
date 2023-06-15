package cl.araucana.estasfam.app.persistence.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cl.araucana.estasfam.app.persistence.extractor.AfiliadosPorTipoExtractor;

public class AfiliadosPorTipoMapper implements RowMapper{
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		AfiliadosPorTipoExtractor extractor = new AfiliadosPorTipoExtractor();
		return extractor.extractor(rs);
	}

}