package cl.araucana.estasfam.app.persistence.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cl.araucana.estasfam.app.persistence.extractor.ActividadEconomicaExtractor;

public class ActividadEconomicaMapper implements RowMapper{
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ActividadEconomicaExtractor extractor = new ActividadEconomicaExtractor();
		return extractor.extractor(rs);
	}

}