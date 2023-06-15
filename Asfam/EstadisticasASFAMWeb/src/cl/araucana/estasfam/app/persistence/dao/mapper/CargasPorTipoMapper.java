package cl.araucana.estasfam.app.persistence.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cl.araucana.estasfam.app.persistence.extractor.CargasPorTipoExtractor;

public class CargasPorTipoMapper implements RowMapper{
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		CargasPorTipoExtractor extractor = new CargasPorTipoExtractor();
		return extractor.extractor(rs);
	}

}