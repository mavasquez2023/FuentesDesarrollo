package cl.araucana.estasfam.app.persistence.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import cl.araucana.estasfam.app.business.model.TramosDTO;

public class TramosExtractor {
	
	public TramosDTO extractor(ResultSet rs) throws SQLException{
		TramosDTO obj = new TramosDTO();
		
		obj.setTramo(rs.getInt("TRAMO"));
		
		return obj;
	}

}
