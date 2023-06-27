/**
 * 
 */
package cl.araucana.migra.action;

import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import cl.araucana.migra.dao.DB2DAO;
import cl.araucana.migra.dao.MigracionDAO;
import cl.araucana.migra.dao.SQLServerDAO;
import cl.araucana.migra.mail.EnviaMail;
import cl.araucana.migra.mail.FormatoMail;


/**
 * @author usist199
 *
 */
public class Migracion {
	private static ResourceBundle properties = ResourceBundle.getBundle("etc/querys");
	private static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");
	static String QUERY_LOTE = properties.getString("cantidad.registros.por.lote");
	
	public static void main(String[] args) {
		
			System.out.println("Entrando a Migracion SQL Server....");
			DB2DAO db2DAO= new DB2DAO();
			SQLServerDAO sqlserverDAO= new SQLServerDAO();
		try {
			StringBuffer mensaje= new StringBuffer();
			MigracionDAO mig= new MigracionDAO(db2DAO.getConnection(), sqlserverDAO.getConnection());
//			********Inicio transacción
			sqlserverDAO.setAutoCommit(false);
			String tabla_source=null;
			int i=0;
			do {
				System.out.println("Inicio Migracion tabla " + (i+1));
				try {
					tabla_source=null;
					tabla_source= properties.getString("query.tabla"+ (i+1) + ".source");
				} catch (Exception e) {
					System.out.println("No se encontró parametros asociados a: query.tabla"+ (i+1) + ".source");
				}
				if(tabla_source!=null){
					String tabla_columns= properties.getString("query.tabla"+ (i+1) + ".columns");
					String tabla_target= properties.getString("query.tabla"+ (i+1) + ".target");

					mensaje.append(" ==> Inicio migración " + tabla_source + ": " + new Date() + "<BR>");
					System.out.println(" ==> Inicio migración " + tabla_source + ": " + new Date() + "<BR>");
					
					//Borrado en tabla destino
					int del= mig.deleteTablaXX(tabla_target);
					mensaje.append(" ==> Registros borrados en " + tabla_target + ": " + del + " - " + new Date() + "<BR>");
					System.out.println(" ==> Registros borrados en " + tabla_target + ": " + del + " - " + new Date() + "<BR>");
					
					//Inicio lectura e inserción simultáneo
					int total= mig.migrarTablaXX(tabla_source, tabla_target, tabla_columns, Integer.parseInt(QUERY_LOTE));
					//Commit transacción
					sqlserverDAO.commit();
					mensaje.append(" ==> Registros insertados en " + tabla_target + ": " + total + " - " + new Date() + "<BR>");
					System.out.println(" ==> Registros leidos en " + tabla_target + ": " + total + " - " + new Date() + "<BR>");
					i++;
				}
				
			} while (tabla_source!=null);
		
			mensaje.append(" ==> FIN migración " + ": " + new Date() + "<BR>");
			System.out.println(" ==> FIN migración " + ": " + new Date() + "<BR>");
			
			//Envio de correo para notificar ejecución
			String mailinfo = mailProperties.getString("app.mail.usuario.info");
			EnviaMail.enviarMail("Migracion SQLServer. ",mailinfo, "clillo007@gmail.com", FormatoMail.obtenerTextoMailInfoMigra(mensaje.toString()));
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			try {
				if(sqlserverDAO!=null){
					sqlserverDAO.rollback();
				}
			} catch (SQLException e1) {
				// TODO Bloque catch generado automáticamente
				System.out.println("Error haciendo rollback");
			}
		}
		finally{
			db2DAO.closeConnectionDAO();
			sqlserverDAO.closeConnectionDAO();
		}
	}

}
