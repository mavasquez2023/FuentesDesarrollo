package cl.araucana.estasfam.app.persistence.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cl.araucana.estasfam.app.persistence.extractor.PagosDirectoPorTipoExtractor;

public class PagosDirectoPorTipoMapper implements RowMapper{
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		PagosDirectoPorTipoExtractor extractor = new PagosDirectoPorTipoExtractor();
		return extractor.extractor(rs);
	}

}