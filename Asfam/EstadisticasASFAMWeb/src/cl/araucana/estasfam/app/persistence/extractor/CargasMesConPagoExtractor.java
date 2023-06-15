package cl.araucana.estasfam.app.persistence.extractor;


import java.sql.ResultSet;
import java.sql.SQLException;

import cl.araucana.estasfam.app.business.model.CargasPorTipoDTO;


public class CargasMesConPagoExtractor {

		public CargasPorTipoDTO extractor(ResultSet rs) throws SQLException{
			CargasPorTipoDTO obj = new CargasPorTipoDTO();
			
			obj.setCodTipo(rs.getString("TIPO"));
			obj.setCodTramo(rs.getInt("TRAMO"));
			obj.setCantidad(rs.getInt("CANTIDAD"));
			obj.setMonto(rs.getInt("MONTO"));
			
			return obj;
		}

}
