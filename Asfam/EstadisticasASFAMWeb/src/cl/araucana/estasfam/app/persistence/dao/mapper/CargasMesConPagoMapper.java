package cl.araucana.estasfam.app.persistence.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cl.araucana.estasfam.app.persistence.extractor.CargasMesConPagoExtractor;

public class CargasMesConPagoMapper implements RowMapper{
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		CargasMesConPagoExtractor extractor = new CargasMesConPagoExtractor();
		return extractor.extractor(rs);
	}

}