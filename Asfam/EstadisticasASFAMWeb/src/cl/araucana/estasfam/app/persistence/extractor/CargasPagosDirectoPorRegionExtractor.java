package cl.araucana.estasfam.app.persistence.extractor;


import java.sql.ResultSet;
import java.sql.SQLException;

import cl.araucana.estasfam.app.business.model.CargasPagosDirectoDTO;

public class CargasPagosDirectoPorRegionExtractor {

		public CargasPagosDirectoDTO extractor(ResultSet rs) throws SQLException{
			CargasPagosDirectoDTO obj = new CargasPagosDirectoDTO();
			
			obj.setCodRegion(rs.getInt("COD_REG"));
			obj.setCodActividad(rs.getInt("COD_ACT"));
			obj.setCantidad(rs.getInt("CANTIDAD"));
			
			return obj;
		}

}
