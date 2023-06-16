package cl.araucana.estasfam.app.persistence.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cl.araucana.estasfam.app.persistence.extractor.TramosExtractor;

public class TramosMapper implements RowMapper{
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		TramosExtractor extractor = new TramosExtractor();
		return extractor.extractor(rs);
	}

}
