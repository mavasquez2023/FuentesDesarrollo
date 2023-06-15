package test.client;

import java.sql.*;

public class TestDatabase {

	public static void main(String[] args) {
		TestDatabase myTest = new TestDatabase();
		myTest.testProcedure();
	}

	public void testProcedure() {
		// Create a variable for the connection string.
		String connectionUrl = "jdbc:sqlserver://METRIC-LAPTOP-2:1433;"
				+ "databaseName=MotorCreditScoring;user=pepito;password=pepito";

		// Declare the JDBC objects.
		Connection con = null;
		CallableStatement cstmt = null;

		try {

			// Establish the connection.
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
			cstmt = con.prepareCall("{? = call dbo.sp_MyProcedure(?,?,?)}");
			cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
			cstmt.setInt(2, 5);
			cstmt.setString(3, "EEE VAMOSSS");
			cstmt.registerOutParameter(4, java.sql.Types.VARCHAR);
			cstmt.execute();
			System.out.println("RETURN STATUS: " + cstmt.getInt(1));
			System.out.println("Resultado : " + cstmt.getString(4));
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {

			if (cstmt != null)
				try {
					cstmt.close();
				} catch (Exception e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}
	}

	public void testSelect() {
		// Create a variable for the connection string.
		String connectionUrl = "jdbc:sqlserver://METRIC-LAPTOP-2:1433;"
				+ "databaseName=MotorCreditScoring;user=pepito;password=pepito";

		// Declare the JDBC objects.
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			// Establish the connection.
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);

			// Create and execute an SQL statement that returns a
			// set of data and then display it.
			String SQL = "SELECT * FROM AntiguedadLaboral;";
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
			displayRow("TITLE", rs);
		}

		// Handle any errors that may have occurred.
		catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}

	}

	public static void displayRow(String title, ResultSet rs) {
		try {
			System.out.println(title);
			while (rs.next()) {
				System.out.println(rs.getString("Nombre") + " : " + rs.getString("Desde"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
