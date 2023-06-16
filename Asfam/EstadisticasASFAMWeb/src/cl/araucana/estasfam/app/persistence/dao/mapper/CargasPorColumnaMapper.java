package cl.araucana.estasfam.app.persistence.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cl.araucana.estasfam.app.persistence.extractor.CargasPorColumnaExtractor;

public class CargasPorColumnaMapper implements RowMapper{
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		CargasPorColumnaExtractor extractor = new CargasPorColumnaExtractor();
		return extractor.extractor(rs);
	}

}