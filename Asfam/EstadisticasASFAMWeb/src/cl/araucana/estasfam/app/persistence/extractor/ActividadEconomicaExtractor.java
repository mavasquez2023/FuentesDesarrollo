package cl.araucana.estasfam.app.persistence.extractor;


import java.sql.ResultSet;
import java.sql.SQLException;

import cl.araucana.estasfam.app.business.model.ActividadEconomicaDTO;
import cl.araucana.estasfam.app.business.model.CargasPorTipoDTO;


public class ActividadEconomicaExtractor {

		public ActividadEconomicaDTO extractor(ResultSet rs) throws SQLException{
			ActividadEconomicaDTO obj = new ActividadEconomicaDTO();
			
			obj.setCodActividad(rs.getInt("COD"));
			obj.setDescActividad(rs.getString("DESC"));
			
			return obj;
		}

}
