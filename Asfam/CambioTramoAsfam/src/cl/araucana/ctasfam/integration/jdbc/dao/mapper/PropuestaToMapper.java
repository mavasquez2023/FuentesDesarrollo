package cl.araucana.ctasfam.integration.jdbc.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import cl.araucana.ctasfam.business.to.AceptacionPropuestaTO;

public class PropuestaToMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int count) throws SQLException {
		AceptacionPropuestaTO p = new AceptacionPropuestaTO();
		p.setRutEmpresa(rs.getInt("RUT_EMPRESA"));
		p.setDvRutEmpresa(rs.getString("DV_RUT_EMPRESA"));
		p.setRazonSocial(rs.getString("RAZON_SOCIAL"));
		p.setRutEncargado(rs.getInt("RUT_ENCARGADO"));
		p.setDvRutEncargado(rs.getString("DV_RUT_ENCARGADO"));
		p.setMailEncargado(rs.getString("MAIL_ENCARGADO"));
		p.setMailEncargado2(rs.getString("MAIL_ENCARGADO2"));
		p.setMailEncargado3(rs.getString("MAIL_ENCARGADO3"));
		p.setEstadoProceso(rs.getString("ESTADO_PROCESO"));
		p.setFormatoArchivo(rs.getString("FORMATO_ARCHIVO"));
		p.setCantiadadArchivos(rs.getInt("CANTIDAD_ARCHIVOS"));
		p.setFechaCreacion(rs.getDate("FECHA_CREACION"));
		p.setHoraCreacion(rs.getTime("HORA_CREACION"));
		return p;
	}

}