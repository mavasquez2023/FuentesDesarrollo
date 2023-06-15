 package cl.araucana.ctasfam.integration.jdbc.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import cl.araucana.ctasfam.business.to.AfiliadosPropuestaTO;

public class AfiliadosPropuestaToMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int count) throws SQLException {
		AfiliadosPropuestaTO to = new AfiliadosPropuestaTO();
		to.setOficina(rs.getInt("AFOYA"));
		to.setRutEmpresa(rs.getInt("AFOVA"));
		to.setDvRutEmpresa(rs.getString("AFP0A"));
		to.setSucursal(rs.getInt("AFOZA"));
		to.setRutAfiliado(rs.getInt("AFOWA"));
		to.setDvRutAfiliado(rs.getString("AFP1A"));
		to.setNombreAfiliado(rs.getString("AFP2A"));
		to.setIngresoPromedio(rs.getInt("AF15UA"));
		to.setTramo(rs.getInt("AFCODTRA"));
		to.setValorTramo(rs.getInt("AFVALTRA"));
		to.setOrigen(rs.getString("AFP6A"));
		return to;
	}

}