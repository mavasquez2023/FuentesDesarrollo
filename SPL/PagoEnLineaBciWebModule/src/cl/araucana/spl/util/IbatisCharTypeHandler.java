package cl.araucana.spl.util;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ibatis.sqlmap.engine.type.StringTypeHandler;

public class IbatisCharTypeHandler extends StringTypeHandler {

	public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
		Object o = super.getResult(cs, columnIndex);
		if (o != null) {
			String s = (String) o;
			o = s.trim();
		}
		return o;
	}

	public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
		Object o = super.getResult(rs, columnIndex);
		if (o != null) {
			String s = (String) o;
			o = s.trim();
		}
		return o;
	}

	public Object getResult(ResultSet rs, String columnName) throws SQLException {
		Object o = super.getResult(rs, columnName);
		if (o != null) {
			String s = (String) o;
			o = s.trim();
		}
		return o;
	}

	public void setParameter(PreparedStatement ps, int i, Object parameter, String jdbcType) throws SQLException {
		super.setParameter(ps, i, parameter, jdbcType);
	}

}
