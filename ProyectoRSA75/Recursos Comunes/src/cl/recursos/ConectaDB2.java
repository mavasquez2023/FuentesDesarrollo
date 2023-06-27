/*
 * Creado el 17-07-2006
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.recursos;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
import java.sql.*;
import java.util.Calendar;
import javax.sql.DataSource;

public class ConectaDB2{
	private Connection connection;
	private Statement statement=null;
	private PreparedStatement prestmt=null;
	private ResultSet rs=null;
	private String driver = "com.ibm.as400.access.AS400JDBCDriver";


	public ConectaDB2(String sistema, String usuario, String contrasena) throws ClassNotFoundException, SQLException {
				
			//Se establece el driver de conexión
			Class.forName(driver);
			// Obtiene una conexión a la DB.
			connection = DriverManager.getConnection(sistema, usuario, contrasena);
	}

	public ConectaDB2(DataSource ds) throws SQLException{
			//Se establece la conexión
			connection= ds.getConnection();
	}

	public DatabaseMetaData getMetadata() throws SQLException{
		DatabaseMetaData meta = connection.getMetaData();
		return meta;
	}

	public void prepareQuery(String sql) throws SQLException{
		prestmt = connection.prepareStatement(sql);
	}

//	Para ejecutar este método se debe haber invocado el prepareQuery y haber seteado los parámetros
	public void executeQuery() throws SQLException{
		rs= prestmt.executeQuery();
	}

//	Para ir agregando querys en procesamiento por lotes
	public void addBatch() throws SQLException{
		prestmt.addBatch();
	}
//	procesamiento por lotes, Para ejecutar este método se debe haber invocado el prepareQuery y haber seteado los parámetros
	public int[] executeBatchPre() throws SQLException{
		return prestmt.executeBatch();
	}
//	Para ir agregando querys en procesamiento por lotes
	public void addBatch(String query) throws SQLException{
		if (statement==null) {
			statement = connection.createStatement();
		}
		statement.addBatch(query);
	}
	
	public void clearBatch() throws SQLException{
		statement.clearBatch();
	}
	public void clearBatchPre() throws SQLException{
		prestmt.clearBatch();
	}
//	procesamiento por lotes.
	public int[] executeBatch() throws SQLException{
		return statement.executeBatch();
	}


	public void executeQuery(String query) throws SQLException{
		if (statement==null) {
			statement = connection.createStatement();
		}
		rs = statement.executeQuery (query);
	}

//	Para ejecutar este método se debe haber invocado el prepareQuery y haber seteado los parámetros
	public int executeUpdate() throws SQLException{
		int numreg= prestmt.executeUpdate();
		//prestmt.close();
		return numreg;
	}

	public int executeUpdate(String query) throws SQLException{
		if (statement==null) {
			statement = connection.createStatement();
		}
		int numreg= statement.executeUpdate(query);
		return numreg;
	}

	public void setStatement(int index, String texto) throws SQLException{
		prestmt.setString(index, texto);
	}
	public void setStatement(int index, int value) throws SQLException{
		prestmt.setInt(index, value);
	}
	public void setStatement(int index, double value) throws SQLException{
		prestmt.setDouble(index, value);
	}
	public void setStatement(int index, Date date) throws SQLException{
		prestmt.setDate(index, date);
	}
	public void setStatement(int index, Time time) throws SQLException{
		prestmt.setTime(index, time);
	}
	public void setStatement(int index, Timestamp timestamp) throws SQLException{
		prestmt.setTimestamp(index, timestamp);
	}
	public void setStatement(int index, Timestamp timestamp, Calendar cal) throws SQLException{
		prestmt.setTimestamp(index, timestamp, cal);
	}
	public void setStatement(int index, Date date, Calendar cal) throws SQLException{
		prestmt.setDate(index, date, cal);
	}
	public void setStatement(int index, char character) throws SQLException{
		prestmt.setString(index, String.valueOf(character));
	}
	public void setMaxRows(int max) throws SQLException{
		prestmt.setMaxRows(max);
	}
	public boolean next() throws SQLException{
		boolean next= false;
		if (rs!= null){
			if (rs.next()){
				next= true;
			}else{
				closeStatement();
			}
		}
		return next;
	}

	public String getString(int index) throws SQLException{
		String campo=null;
		if (rs!=null){
			campo= rs.getString(index);
			if(campo!=null){
				campo= campo.trim();
			}
		}
		return campo;
	}

	public String getString(String name) throws SQLException{
		String campo=null;
		if (rs!=null){
			campo= rs.getString(name);
			if(campo!=null){
				campo= campo.trim();
			}
		}
		return campo;
	}
	
	public void closeStatement() throws SQLException{
		//System.out.println("Desconectando...");
			if (rs != null){
				rs.close();
			}
			if (statement != null){
				statement.close();
				statement=null;
			}
			if ( prestmt!= null){
				prestmt.close();
				prestmt=null;
			}		
	}
	
	public void desconectaDB2() throws SQLException{
			//System.out.println("Desconectando...");
				if (rs != null){
					rs.close();
				}
				if (statement != null){
					statement.close();
				}
				if ( prestmt!= null){
					prestmt.close();
				}
				if (connection != null)
					connection.close();			
	}

	public Connection getConnection(){
		return connection;
	}

	/**
	 * @return Devuelve isClosed.
	 * @throws SQLException
	 */
	public boolean isClosed() throws SQLException {
		
		return connection.isClosed();
	}

	public void deleteRow() throws SQLException {
		rs.deleteRow();
		
	}

	public Date getDate(int columnIndex) throws SQLException {
		return rs.getDate(columnIndex);
	}

	public Date getDate(String columnName) throws SQLException {
		return rs.getDate(columnName);
	}

	public Date getDate(int columnIndex, Calendar cal) throws SQLException {
		return rs.getDate(columnIndex, cal);
	}

	public Date getDate(String columnName, Calendar cal) throws SQLException {
		return rs.getDate(columnName, cal);
	}

	public double getDouble(int columnIndex) throws SQLException {
		return rs.getDouble(columnIndex);
	}

	public double getDouble(String columnName) throws SQLException {
		return rs.getDouble(columnName);
	}

	public int getFetchSize() throws SQLException {
		return rs.getFetchSize();
	}

	public float getFloat(int columnIndex) throws SQLException {
		return rs.getFloat(columnIndex);
	}

	public float getFloat(String columnName) throws SQLException {
		return rs.getFloat(columnName);
	}

	public int getInt(int columnIndex) throws SQLException {
		return rs.getInt(columnIndex);
	}

	public int getInt(String columnName) throws SQLException {
		return rs.getInt(columnName);
	}

	public long getLong(int columnIndex) throws SQLException {
		return rs.getLong(columnIndex);
	}

	public long getLong(String columnName) throws SQLException {
		return rs.getLong(columnName);
	}

	public short getShort(int columnIndex) throws SQLException {
		return rs.getShort(columnIndex);
	}

	public short getShort(String columnName) throws SQLException {
		return rs.getShort(columnName);
	}

	public Time getTime(int columnIndex) throws SQLException {
		return rs.getTime(columnIndex);
	}

	public Time getTime(String columnName) throws SQLException {
		return rs.getTime(columnName);
	}

	public Time getTime(int columnIndex, Calendar cal) throws SQLException {
		return rs.getTime(columnIndex, cal);
	}

	public Time getTime(String columnName, Calendar cal) throws SQLException {
		return rs.getTime(columnName, cal);
	}

	public Timestamp getTimestamp(int columnIndex) throws SQLException {
		return rs.getTimestamp(columnIndex);
	}

	public Timestamp getTimestamp(String columnName) throws SQLException {
		return rs.getTimestamp(columnName);
	}

	public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
		return rs.getTimestamp(columnIndex, cal);
	}

	public Timestamp getTimestamp(String columnName, Calendar cal) throws SQLException {
		return rs.getTimestamp(columnName, cal);
	}


	public boolean isAfterLast() throws SQLException {
		return rs.isAfterLast();
	}

	public boolean isBeforeFirst() throws SQLException {
		return rs.isBeforeFirst();
	}

	public boolean isFirst() throws SQLException {
		return rs.isFirst();
	}

	public boolean isLast() throws SQLException {
		return rs.isLast();
	}

	public static void main(String [] args){
		/*ConectaDB2 db2=null;
		try {
			System.out.println("IP Servidor:" + args[0]);
			System.out.println("Usuario:" + args[1]);
			System.out.println("Password:" + args[2]);
			db2 = new ConectaDB2(args[0], args[1], args[2]);
			String query= "select count(*) from testdta.testtable";
			System.out.println("Query a ejecutar:" + query);
			db2.prepareQuery(query.toString());
			db2.executeQuery();
			System.out.println("Conexión OK");
		} catch (Exception e) {
			System.out.println("Error, mensaje= " + e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				if(db2!=null){
					db2.desconectaDB2();
				}
			} catch (SQLException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
		}
		*/
	}

	}