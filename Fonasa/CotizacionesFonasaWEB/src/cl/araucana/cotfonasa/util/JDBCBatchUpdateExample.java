package cl.araucana.cotfonasa.util;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
public class JDBCBatchUpdateExample {
 
	
	
	private static final String DB_DRIVER = "com.ibm.as400.access.AS400JDBCDriver";
	private static final String DB_CONNECTION = "jdbc:as400://146.83.1.5;naming=sql;errors=full";
	private static final String DB_USER = "schema";
	private static final String DB_PASSWORD = "schema";
 
	public static void main(String[] argv) {
 
		try {
 
			batchInsertRecordsIntoTable();
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		}
 
	}
 
	private static void batchInsertRecordsIntoTable() throws SQLException {
 
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String insertTableSQL = "INSERT INTO COTFONASA.DBUSER"
				+ "(USER_ID, USERNAME, CREATED_BY) VALUES"
				+ "(?,?,?)";
 
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
 
			dbConnection.setAutoCommit(false);
            int i=0;
			
			/*
			preparedStatement.setInt(1, 101);
			preparedStatement.setString(2, "mkyong101");
			preparedStatement.setString(3, "system");
			//preparedStatement.setTimestamp(4, getCurrentTimeStamp());
			preparedStatement.addBatch();
 
			preparedStatement.setInt(1, 102);
			preparedStatement.setString(2, "mkyong102");
			preparedStatement.setString(3, "system");
			//preparedStatement.setTimestamp(4, getCurrentTimeStamp());
			preparedStatement.addBatch();
 
			preparedStatement.setInt(1, 103);
			preparedStatement.setString(2, "mkyong103");
			preparedStatement.setString(3, "system");
			//preparedStatement.setTimestamp(4, getCurrentTimeStamp());
			preparedStatement.addBatch();*/
			
			  while (i < 1000000){
				  
				  preparedStatement.setInt(1, i);
					preparedStatement.setString(2, "mkyong1"+i);
					preparedStatement.setString(3, "system"+i);
					//preparedStatement.setTimestamp(4, getCurrentTimeStamp());
					preparedStatement.addBatch();
				  
				  i++;
			  }
 
			preparedStatement.executeBatch();
 
			dbConnection.commit();
 
			System.out.println("Record is inserted into DBUSER table!");
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
			dbConnection.rollback();
 
		} finally {
 
			if (preparedStatement != null) {
				preparedStatement.close();
			}
 
			if (dbConnection != null) {
				dbConnection.close();
			}
 
		}
 
	}
 
	private static Connection getDBConnection() {
 
		Connection dbConnection = null;
 
		try {
 
			Class.forName(DB_DRIVER);
 
		} catch (ClassNotFoundException e) {
 
			System.out.println(e.getMessage());
 
		}
 
		try {
 
			dbConnection = DriverManager.getConnection(
                              DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		}
 
		return dbConnection;
 
	}
 
	private static java.sql.Timestamp getCurrentTimeStamp() {
 
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
 
	}
 
}