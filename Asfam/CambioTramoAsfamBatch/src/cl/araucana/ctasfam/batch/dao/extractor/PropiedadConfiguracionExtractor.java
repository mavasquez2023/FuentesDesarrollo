package cl.araucana.ctasfam.batch.dao.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import cl.araucana.ctasfam.batch.common.dto.PropiedadConfiguracionDto;

public class PropiedadConfiguracionExtractor {
	public PropiedadConfiguracionDto extractor(ResultSet rs) throws SQLException{
		PropiedadConfiguracionDto obj = new PropiedadConfiguracionDto();
		
		obj.setLlave(rs.getString("AFP80COD"));
		obj.setValor(rs.getString("AFP80VAL"));
		
		return obj;
	}
}
