package cl.araucana.cp.adminHomologacion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import utilPub.UtilPub;

public class AdminHomologacionDAO {

	public List GetLista(String tipo) {
		
		String SQL = "";
		ResultSet result = null;
		List lista = new ArrayList();
		try {
			//nos conectamos a la BD 
			UtilPub util = new UtilPub();
			
			Connection conexion = util.getConnection();
			Statement stmt = conexion.createStatement();
			
			SQL = "SELECT * FROM pwdtad.homologaciondirecciontrabajo WHERE TIPO = '"+tipo.trim()+"'" ;
			try {
				result = stmt.executeQuery(SQL);
				HomologacionTO campos;
				while (result.next()) {
					campos = new HomologacionTO();
					campos.setId(result.getInt("ID"));
					campos.setTipo(result.getString("TIPO").trim());
					campos.setValorCaja(result.getString("CODIGOCAJA").trim());
					campos.setValorDT(result.getString("CODIGODT").trim());
					campos.setDesdcripcion(result.getString("DESCRIPCION").trim());
					
					lista.add(campos);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public int update(int id, String valorCaja, String valorDT, String descripcion) {
		
		String SQL = "";
		int x=0;
		try {
			//nos conectamos a la BD 
			UtilPub util = new UtilPub();
			
			Connection conexion = util.getConnection();
			Statement stmt = conexion.createStatement();
			
			SQL = "UPDATE pwdtad.homologaciondirecciontrabajo set CODIGOCAJA='"+valorCaja.trim()+"', CODIGODT='"+valorDT.trim()+"', DESCRIPCION='"+descripcion.trim()+"' where id = "+id ;
			try {
				
				x = stmt.executeUpdate(SQL);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return x;
	}

	public int validaExistencia(String tipo, String valorCaja, int id) {
		String SQL = "";
		int x=0;
		ResultSet result = null;
		try {
			//nos conectamos a la BD 
			UtilPub util = new UtilPub();
			
			Connection conexion = util.getConnection();
			Statement stmt = conexion.createStatement();
			
			SQL = "select count(id) as contador from pwdtad.homologaciondirecciontrabajo where UPPER(TIPO) = '"+tipo.trim().toUpperCase()+ "' and UPPER(CODIGOCAJA) = '"+valorCaja.trim().toUpperCase()+"' and ID NOT IN("+id+")" ;
			try {
				
				result = stmt.executeQuery(SQL);
				if (result.next()) {
					x = result.getInt("CONTADOR");
				}
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return x;
	}

	public HomologacionTO getCampo(int id) {
		String SQL = "";
		int x=0;
		ResultSet result = null;
		HomologacionTO campos = null;
		try {
			//nos conectamos a la BD 
			UtilPub util = new UtilPub();
			
			Connection conexion = util.getConnection();
			Statement stmt = conexion.createStatement();
			
			SQL = "select * from pwdtad.homologaciondirecciontrabajo where ID = "+id;
			try {
				
				result = stmt.executeQuery(SQL);
				if (result.next()) {
					campos = new HomologacionTO();
					campos.setId(result.getInt("ID"));
					campos.setTipo(result.getString("TIPO").trim());
					campos.setValorCaja(result.getString("CODIGOCAJA").trim());
					campos.setValorDT(result.getString("CODIGODT").trim());
					campos.setDesdcripcion(result.getString("DESCRIPCION").trim());
				}
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return campos;
	}
	
	

}
