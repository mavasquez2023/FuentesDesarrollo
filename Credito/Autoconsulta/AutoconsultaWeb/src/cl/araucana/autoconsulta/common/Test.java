package cl.araucana.autoconsulta.common;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Test {

	private static DataSource datasource; 
	private Connection cnn;
	private PreparedStatement pst;
	private static Statement st;
	private static ResultSet rs;

	public static void main(String[] args) {
		// TODO Apéndice de método generado automáticamente
		datasource = getDataSource("jdbc/corporativo");
		Connection con = getConn(datasource);
		String sqlCommand =
			"SELECT 1 \"TIPODEUDA\", A.* "
			+ "FROM "
			+ " CRDTA"
			+ ".CSL1001 A, "
			+ "RCDTA"
			+ ".CSL200 B "
			+ "WHERE A.AFIRUT = ? "
			+ "AND B.XCREFOL=A.CREFOL AND B.XOFIPRO=A.OFIPRO "
			+ "AND B.CUOEST!=9 AND B.CUOEST!=8 AND B.CUOEST!=7 "
			+ "AND A.CREESTPTM != 8 "
			+ "ORDER BY CREFOL ASC";

		try {
			st = con.createStatement();
			rs = st.executeQuery(sqlCommand);
			while (rs.next()) {
				System.out.println("1: " + rs.getString(1) + " - 2: " + rs.getString(2) + " - 2: " + rs.getString(3));
			}
			rs.close();
			st.close();

		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}

	}

	
	private static DataSource getDataSource(String paramName){
		
		DataSource JdbcName = null; 
		try {
			InitialContext ic = new InitialContext();
			JdbcName = (DataSource)ic.lookup(paramName);
			return JdbcName;
		} catch (NamingException e){
			e.printStackTrace();
			return null;
		}		
	}
	
	private static Connection getConn(DataSource ds){
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	private void closeConn(Connection cn){
		try {
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	
	private int getConsultaPagoExceso(String rut) throws SQLException {
		
		ResultSet rs = null;
		int monto;
		st = cnn.createStatement();
		String sqlCommand = "SELECT sum(cuograpag + cuovalant + cuomonpag) FROM rcdta.rc03f1 where afirut="
							+ rut + " AND rc2ca='1' and cuopagfec >= '20030331'";
		try {
			rs = st.executeQuery(sqlCommand);
			rs.next();		
			monto = rs.getInt(1);
		} finally {
			if (rs != null) {
				try {
					rs.close();
					st.close();
				} catch (SQLException e) {}
			}
		}
		return monto;
	}

}
