package cl.domino.rentaspro;

import java.sql.*;

import cl.recursos.ConectaDB2;

public class RentasPromedioDAO{
	ConectaDB2 db2;
	
public  RentasPromedioDAO(String sistema, String usuario, String password) {
		try {
			db2= new ConectaDB2(sistema, usuario, password);
		} catch (ClassNotFoundException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}		
	
}		

//Realiza desconexión al DB2
public void desconectaDB2(){
	try {
		db2.desconectaDB2();
	} catch (SQLException e) {
		// TODO Bloque catch generado automáticamente
		e.printStackTrace();
	}
		
}


//Inserta un registro de trabajador en DB2
public int insertaTrabajador(Object obj){
	StringBuffer query=new StringBuffer();
	int rtdo=0;
		AfiliadoRPTO afiliado= (AfiliadoRPTO)obj;
		try {
			//llenaDerecha(rutemp, Len);
			query.append("INSERT INTO AFDTA.AFP64F1 ");
			query.append("( AFP7A, AFOVA, AFOWA, AFOYA, AFOZA, AFP0A, AFP1A, AFP2A, AF15NA, AF15OA, AF15PA, AF15QA, AF15RA, AF15SA, AF15TA, AF15UA, AF15VA, AFP6A, AFP8A, AFAMA, SAJKM94 )");
			query.append("values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
			db2.prepareQuery(query.toString());
			db2.setStatement(1, afiliado.getPeriodo());
			db2.setStatement(2, afiliado.getRutEmpresa());
			db2.setStatement(3, afiliado.getRutAfiliado());
			db2.setStatement(4, 0);
			db2.setStatement(5, 0);
			db2.setStatement(6, afiliado.getDvRutEmpresa());
			db2.setStatement(7, afiliado.getDvRutAfiliado());
			String nombrefull= afiliado.getApellidoPaterno().trim() + " " + afiliado.getApellidoMaterno().trim() + ", " + afiliado.getNombres().trim();
			if (nombrefull.length()>50){
				nombrefull= nombrefull.substring(0, 50);
			}
			db2.setStatement(8, nombrefull);
			db2.setStatement(9, afiliado.getRemuMismoEmpleador());
			db2.setStatement(10, afiliado.getRemuOtroEmpleador());
			db2.setStatement(11, afiliado.getRemuIndependiente());
			db2.setStatement(12, afiliado.getSubsidio());
			db2.setStatement(13, afiliado.getPensiones());
			db2.setStatement(14, afiliado.getTotalIngresos());
			db2.setStatement(15, afiliado.getNumMeses());
			db2.setStatement(16, afiliado.getPromedioMensual());
			db2.setStatement(17, afiliado.getDeclaracion());
			db2.setStatement(18, 'E');
			db2.setStatement(19, 'I');
			db2.setStatement(20, 0);
			db2.setStatement(21, "AUTOMATICO");
			
			rtdo=db2.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("Trabajador ya existía, Rut Empresa:" + afiliado.getRutEmpresa() + ", Rut Trabajador:" + afiliado.getRutAfiliado());
			System.out.println("Mensaje: " + e.getMessage());
		}
		return rtdo;
}

//Inserta un registro de trabajador en DB2
//public boolean insertaTrabajador(String rutemp, String ruttra, String dvrutemp, String dvruttra, String nomtra, String rentaimp, String montoboni, char origen, char estado){
public int insertRecepcion(Object obj){
	StringBuffer query=new StringBuffer();
	int rtdo=0;
		try {
			ParametrosEnvio param= (ParametrosEnvio)obj;
			//llenaDerecha(rutemp, Len);
			query.append("INSERT INTO afdta.rxrentapro ");
			query.append("( RUT_EMPRESA, DV_RUT_EMPRESA, RAZON_SOCIAL, RUT_ENCARGADO, DV_RUT_ENCARGADO, MAIL_ENCARGADO, MAIL_ENCARGADO2, MAIL_ENCARGADO3, ESTADO_PROCESO, FORMATO_ARCHIVO, CANTIDAD_ARCHIVOS )");
			query.append("values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
			db2.prepareQuery(query.toString());
			db2.setStatement(1, param.getRutEmpresa().getNumber());
			db2.setStatement(2, param.getRutEmpresa().getDV());
			db2.setStatement(3, param.getNombreUsuario());
			db2.setStatement(4, param.getRutUsuario().getNumber());
			db2.setStatement(5, param.getRutUsuario().getDV());
			db2.setStatement(6, param.getMailEncargados().elementAt(0).toString());
			db2.setStatement(7, "");
			if(param.getMailEncargados().size()>1){
				db2.setStatement(7, param.getMailEncargados().elementAt(1).toString());
			}
			db2.setStatement(8, "");
			if(param.getMailEncargados().size()>2){
				db2.setStatement(8, param.getMailEncargados().elementAt(2).toString());
			}
			db2.setStatement(9, param.getEstadoProceso());
			db2.setStatement(10, param.getFormatoEnvio());
			db2.setStatement(11, param.getCantidadArchivos());
			
			rtdo=db2.executeUpdate();
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return rtdo;
}

public int  deleteEmpresa(int periodo, int rutEmpresa){
	StringBuffer query=new StringBuffer();
	int rtdo=0;
	try {
		query.append("DELETE FROM AFDTA.AFP64F1 ");
		query.append("WHERE AFP7A= ? ");
		query.append("AND AFOVA= ? ");
		db2.prepareQuery(query.toString());
		db2.setStatement(1, periodo);
		db2.setStatement(2, rutEmpresa);
		rtdo=db2.executeUpdate();
	}catch (SQLException e) {
		// TODO Bloque catch generado automáticamente
		e.printStackTrace();
	}
	return rtdo;
}

public void commit(){
	try {
		db2.getConnection().commit();
	} catch (SQLException e) {
		// TODO Bloque catch generado automáticamente
		e.printStackTrace();
	}
}

public void rollback(){
	try {
		db2.getConnection().rollback();
	} catch (SQLException e) {
		// TODO Bloque catch generado automáticamente
		e.printStackTrace();
	}
}
public void setAutoCommit(boolean estado){
	try {
		db2.getConnection().setAutoCommit(estado);
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
}
