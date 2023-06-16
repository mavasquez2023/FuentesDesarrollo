package cl.araucana.estasfam.app.persistence.extractor;


import java.sql.ResultSet;
import java.sql.SQLException;

import cl.araucana.estasfam.app.business.model.CargasPorColumnaDTO;


public class CargasPorColumnaExtractor {

		public CargasPorColumnaDTO extractor(ResultSet rs) throws SQLException{
			CargasPorColumnaDTO obj = new CargasPorColumnaDTO();
			
			obj.setCodTipo(rs.getString("TIPO"));
			obj.setCantidad(rs.getInt("CANTIDAD"));
			obj.setCodColumna(rs.getInt("COLUMNA"));
			
			return obj;
		}

}
