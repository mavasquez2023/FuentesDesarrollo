package cl.araucana.ea.edocs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import cl.araucana.ea.edocs.EstadisticaBean;
import cl.araucana.ea.edocs.bd.ConnectionDB;

public class EstadisticaDAO {
	public static void main(String[] args){
		EstadisticaBean estadistica = new EstadisticaBean();
		estadistica.setId(104);
		estadistica.setPeriodo(201102);
		estadistica.setRutUsuario(15057836);
		estadistica.setRutEmpresa(70016160);
		estadistica.setNombreEmpresa("Sistemas Araucana");
		estadistica.setCodigoOficina("111");
		estadistica.setCodigoNomina("XXX");
		estadistica.setIP("172.16.137.222");
		estadistica.setFechaCreacion(null);
		estadistica.setHoraCreacion(null);
		estadistica.setFormato("XLS");
		guardarEstadistica(estadistica);
		
	}
	
	public static boolean guardarEstadistica(EstadisticaBean estadistica) {
		
		System.out.println("guardando estadistica...");
		Connection conn = ConnectionDB.obtenerConexion();
		if(conn ==null){
			System.out.println("no pudo establecer la conexion");
			return false;
		}
		try{
			
			String SQL = 	"INSERT INTO PUBDTA.bitnc "+
						" (periodo,rut_usuario,rut_empresa,nombre_empresa, "+
						" codigo_oficina,codigo_nomina,IP,fecha_creacion,hora_creacion,formato)"+ 
						" VALUES "+
							" (?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStatement = conn.prepareStatement(SQL);
			
			preparedStatement.setInt(1, estadistica.getPeriodo());
			preparedStatement.setInt(2, estadistica.getRutUsuario());
			preparedStatement.setInt(3, estadistica.getRutEmpresa());
			preparedStatement.setString(4, estadistica.getNombreEmpresa());
			preparedStatement.setString(5, estadistica.getCodigoOficina());
			preparedStatement.setString(6, estadistica.getCodigoNomina());
			preparedStatement.setString(7, estadistica.getIP());
			preparedStatement.setDate(8,estadistica.getFechaCreacion());
			preparedStatement.setTime(9, estadistica.getHoraCreacion());
			preparedStatement.setString(10, estadistica.getFormato());
			
			preparedStatement.execute();
			System.out.println("se ha guardado correctamente");
			
			
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		} 
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
		}
		return true;
	}
	
}
