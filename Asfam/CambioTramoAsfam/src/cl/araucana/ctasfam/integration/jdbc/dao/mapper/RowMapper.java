package cl.araucana.ctasfam.integration.jdbc.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper {
	public Object mapRow(ResultSet rs, int count) throws SQLException;
}
