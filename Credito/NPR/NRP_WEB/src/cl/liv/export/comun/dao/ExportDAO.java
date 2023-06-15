package cl.liv.export.comun.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import cl.liv.export.comun.util.Mensajes;
import cl.liv.export.comun.util.bd.ConexionBD;
import cl.liv.export.comun.util.bd.ConexionBDAux;

public class ExportDAO {
	public static void main(String[] args) {
		String query = "SELECT * FROM test ";
		ArrayList data = ejecutarSelect(query, "datasources/localDS");
		Mensajes.info("registros: "+ data.size());
	}

	public static ArrayList<HashMap<String, Object>> ejecutarSelect(String query, String datasource) {
		Mensajes.info("ejecutando query: ["+query+","+datasource+"]");
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();
		Connection conn = ConexionBD.getConexion(datasource);
		Mensajes.info("Connectando ["+datasource+"]");
		if(conn == null){
			Mensajes.info("Conn ["+datasource+"] Es nulo...");
			HashMap<String, Object> dato1 = new HashMap<String, Object>();
			dato1.put("tipo_registro", "1");
			dato1.put("total", "2");
			dato1.put("vacio", "3");
			data.add(dato1);
			return data;
		}
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				HashMap<String, Object> item = new HashMap<String, Object>();
				for (int i = 1; i < 100; i++) {
					try {
						if (rs.getObject(i) != null) {
							item.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
						}
					} catch (Exception e) {
					}
				}
				data.add(item);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if (!!e.getMessage().contains("Cursor position") 
					&& !e.getMessage().contains("Cursor state")
					&& !e.getMessage().contains("After end of result set")
					)
				e.printStackTrace();

		} finally {
			ConexionBD.cerrar();
		}
		return data;
	}
	
	
	public static ArrayList<HashMap<String, Object>> ejecutarSelect(String query, String datasource, boolean useConexion2) {
		Mensajes.info("ejecutando query: ["+query+","+datasource+"]");
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();
		Connection conn = new ConexionBDAux().getConexion(datasource);
		Mensajes.info("Connectando ["+datasource+"]");
		if(conn == null){
			Mensajes.info("Conn ["+datasource+"] Es nulo...");
			HashMap<String, Object> dato1 = new HashMap<String, Object>();
			dato1.put("tipo_registro", "1");
			dato1.put("total", "2");
			dato1.put("vacio", "3");
			data.add(dato1);
			return data;
		}
		try {
			ResultSet rs = conn.createStatement().executeQuery(query);
			while (rs.next()) {
				HashMap<String, Object> item = new HashMap<String, Object>();
				for (int i = 1; i < 100; i++) {
					try {
						if (rs.getObject(i) != null) {
							item.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
						}
					} catch (Exception e) {
					}
				}
				data.add(item);
			}
			rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if (!!e.getMessage().contains("Cursor position") 
					&& !e.getMessage().contains("Cursor state")
					&& !e.getMessage().contains("After end of result set")
					)
				e.printStackTrace();

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}
	
	
	public static boolean ejecutarQuery(String query, String datasource) {
		boolean resultado = false;
		Mensajes.info("ejecutando query: ["+query+","+datasource+"]");
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();
		Connection conn = ConexionBD.getConexion(datasource);
		Mensajes.info("Connectando ["+datasource+"]");
		if(conn == null){
			Mensajes.info("Conn ["+datasource+"] Es nulo...");
			HashMap<String, Object> dato1 = new HashMap<String, Object>();
			dato1.put("tipo_registro", "1");
			dato1.put("total", "2");
			dato1.put("vacio", "3");
			data.add(dato1);
			return false;
		}
		try {
			resultado = conn.createStatement().execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if (!!e.getMessage().contains("Cursor position") 
					&& !e.getMessage().contains("Cursor state")
					&& !e.getMessage().contains("After end of result set")
					)
				e.printStackTrace();

		} finally {
			ConexionBD.cerrar();
		}
		return resultado;
	}
}
