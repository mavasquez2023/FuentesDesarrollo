/**
 * 
 */
package cl.araucana.migra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @author usist199
 *
 */
public class MigracionDAO {
	//private static Logger log = Logger.getLogger(MigracionDAO.class);
	
	private Connection db2;
	private Connection sqlserver;
	
	public MigracionDAO(Connection db2, Connection sqlserver){
		this.db2= db2;
		this.sqlserver= sqlserver;
	}
	
	public int migrarTablaXX(String tabla_source, String tabla_target, String columns, int maxlote) throws SQLException{
		
		int totalLote=0;
		//conexión source
		System.out.println("Conexion source:" + !db2.isClosed());
		//conexión target
		System.out.println("Conexion target:" + !sqlserver.isClosed());
		if(db2!=null && sqlserver!=null){
			//********Query lectura
			String query= getSelect(tabla_source, columns, maxlote);
			System.out.println("Query=" + query);
			//Statement lectura
			Statement statement= db2.createStatement();
			//Se ejecuta la query
			ResultSet rs = statement.executeQuery (query);
			
			//********Query escritura
			query= getInsert(tabla_target, columns);
			//Prepared Statement insert
			PreparedStatement prestmt = sqlserver.prepareStatement(query);
			
			String[] cols= columns.split(",");
			int count=0;
			while (rs.next()) {
				for (int i = 0; i < cols.length; i++) {
					//System.out.println("set parameter : " + i);
					Object dato= rs.getObject(cols[i].trim());
					if(dato==null){
						dato="";
					}
					prestmt.setObject((i+1), dato);
				}
				prestmt.addBatch();
				count++;
				if(count==maxlote){
					System.out.println("Insertando Lote: " + (totalLote + maxlote));
					totalLote+= ejecutarBatch(sqlserver, prestmt);
					count=0;
				}
			}
			System.out.println("Insertando Lote: " + (totalLote + count));
			totalLote+= ejecutarBatch(sqlserver, prestmt);
			statement.close();
			rs.close();
			prestmt.close();
		}
		System.out.println("total registros " + tabla_source + ": " + totalLote);
		return totalLote;
	}
	
	public String getSelect(String source, String columns, int maxlote){
		String select= "select " + columns + " from " + source ;
		return select;
	}
	
	public String getInsert(String target, String columns){
		String select= "insert into " + target + " (" +  columns + ") values (" + getParams(columns) + ")" ;
		return select;
	}
	public String getParams(String columns){
		String params="";
		String[] cols= columns.split(",");
		for (int i = 0; i < cols.length; i++) {
			params+="?, ";
		}
		if(params.length()>2){
			params= params.substring(0, params.length()-2);
		}
		return params;
	}
	

	
public int ejecutarBatch(Connection conn, PreparedStatement psInsertar) {
	try {
		if (psInsertar != null) {

			//System.out.println("[MigracionDAO] executando Batch Parcial..."+ new Date());
			int[] resultado = psInsertar.executeBatch();
			System.out.println("[MigracionDAO] commit...");
			conn.commit();
			psInsertar.clearBatch();
			//System.out.println("[MigracionDAO] proceso terminado" + new Date());
			return resultado.length;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	return 0;
}

public int deleteTablaXX(String tabla) throws SQLException{
	
	int  del=0;
	
	String query= getDelete(tabla);
	System.out.println("Query=" + query);
			
	System.out.println("Conexion:" + !sqlserver.isClosed());
	if(sqlserver!=null){
		Statement statement= sqlserver.createStatement();
//		Se ejecuta la query
		del= statement.executeUpdate(query);
		//db2.prepareQuery(query.toString());
		
		statement.close();
	}
	System.out.println("total registros borrados " + tabla + ": " + del);
	return del;
}

private String getDelete(String tabla) {
	return "truncate table " + tabla;
}

}
