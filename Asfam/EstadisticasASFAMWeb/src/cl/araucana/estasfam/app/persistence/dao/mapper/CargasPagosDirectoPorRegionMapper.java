package cl.araucana.estasfam.app.persistence.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cl.araucana.estasfam.app.persistence.extractor.CargasPagosDirectoPorRegionExtractor;

public class CargasPagosDirectoPorRegionMapper implements RowMapper{
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		CargasPagosDirectoPorRegionExtractor extractor = new CargasPagosDirectoPorRegionExtractor();
		return extractor.extractor(rs);
	}

}