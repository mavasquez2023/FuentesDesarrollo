package cl.araucana.estasfam.app.persistence.extractor;


import java.sql.ResultSet;
import java.sql.SQLException;

import cl.araucana.estasfam.app.business.model.PagosDirectoPorTipoDTO;


public class PagosDirectoPorTipoExtractor {

		public PagosDirectoPorTipoDTO extractor(ResultSet rs) throws SQLException{
			PagosDirectoPorTipoDTO obj = new PagosDirectoPorTipoDTO();
			
			obj.setCodTipo(rs.getString("TIPO"));
			obj.setCantidad(rs.getInt("CANTIDAD"));
			obj.setMonto(rs.getBigDecimal("MONTO").doubleValue());
			
			return obj;
		}

}
