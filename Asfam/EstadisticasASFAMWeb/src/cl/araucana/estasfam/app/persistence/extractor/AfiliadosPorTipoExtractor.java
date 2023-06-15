package cl.araucana.estasfam.app.persistence.extractor;


import java.sql.ResultSet;
import java.sql.SQLException;

import cl.araucana.estasfam.app.business.model.AfiliadosPorTipoDTO;


public class AfiliadosPorTipoExtractor {

		public AfiliadosPorTipoDTO extractor(ResultSet rs) throws SQLException{
			AfiliadosPorTipoDTO obj = new AfiliadosPorTipoDTO();
			
			obj.setCodTipo(rs.getString("TIPO"));
			obj.setCantidad(rs.getInt("CANTIDAD"));
			obj.setCodTramo(rs.getInt("TRAMO"));
			
			return obj;
		}

}
