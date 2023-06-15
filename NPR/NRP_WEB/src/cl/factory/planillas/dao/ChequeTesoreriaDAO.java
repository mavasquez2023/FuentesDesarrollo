/*
 * Creado el 17-02-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de cï¿½digo - Plantillas de cï¿½digo
 */
package cl.factory.planillas.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cl.araucana.core.util.Rut;
import cl.factory.planillas.vo.PropuestaPagoTO;
import cl.liv.comun.utiles.logs.UtilLogErrores;


/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de cï¿½digo - Plantillas de cï¿½digo
 */
public class ChequeTesoreriaDAO {
	

	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#insert(java.lang.Object)
	 */
	public static int insert(PropuestaPagoTO chequeTO, Statement stmt) throws SQLException {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yy");
        DateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
        Date today = new Date();
        String dateOut = dateFormatter.format(today);
        String timeOut = timeFormatter.format(today);
        
        
		StringBuffer sqlstmt = new StringBuffer();
        sqlstmt.append("INSERT INTO TEDTA.TE07F1 ");
        sqlstmt.append("(");
        
        
        
        
        sqlstmt.append("TE3WA,");
        sqlstmt.append("TE3XA,");
        sqlstmt.append("TE3YA,");
        sqlstmt.append("TE3ZA,");
        sqlstmt.append("TE1SA,");
        sqlstmt.append("TE41A,");
        sqlstmt.append("TE40A,");
        sqlstmt.append("TE1TA,");
        sqlstmt.append("TE42A,");
        sqlstmt.append("TE43A,");
        sqlstmt.append("TE44A,");
        sqlstmt.append("TE7MA,");
        sqlstmt.append("TE7NA,");
        sqlstmt.append("TE4BA,");
        sqlstmt.append("TE4CA,");
        sqlstmt.append("TE4DA,");
        sqlstmt.append("TE4EA,");
        sqlstmt.append("CMBA,");
        sqlstmt.append("TEQA,");
        sqlstmt.append("TE1CA,");
        sqlstmt.append("OBF002,");
        sqlstmt.append("OBF003,");
        sqlstmt.append("OBF005,");
        sqlstmt.append("OBF006,");
        sqlstmt.append("SAJKM94,");
        sqlstmt.append("SAJKM92,");
        sqlstmt.append("TE49A");
        sqlstmt.append(")");
        sqlstmt.append("VALUES ");
        //sqlstmt.append("(?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?)");
        
        
        
        sqlstmt.append("(");
        
        sqlstmt.append("'"+ chequeTO.getFolioEgreso()+"',");
 		sqlstmt.append("'"+ "I"+"',");
 		sqlstmt.append("'"+ "I"+"',");
 		sqlstmt.append("'"+ dateOut+"',");
 		sqlstmt.append("'"+ timeOut+"',");
 		sqlstmt.append("'"+ "C"+"',");
 		sqlstmt.append("'"+ "01/01/01"+"',");
 		sqlstmt.append("'"+ "00:00:00"+"',");
 		sqlstmt.append("'"+ chequeTO.getRut().getNumber()+"',");
 		sqlstmt.append("'"+ chequeTO.getRut().getDV()+""+"',");
 		sqlstmt.append("'"+ chequeTO.getRazonSocial()+"',");
 		sqlstmt.append("'"+ chequeTO.getMontoTotal()+"',");
 		sqlstmt.append("'"+ chequeTO.getMontoTotal()+"',");
 		sqlstmt.append("'"+ "A"+"',");
 		sqlstmt.append("'"+ " "+"',");
 		sqlstmt.append("'"+ dateOut+"',");
 		sqlstmt.append("'"+ "N"+"',");
 		sqlstmt.append("'"+ chequeTO.getOficinaPago()+"',");
 		sqlstmt.append("'"+ "6"+"',");
 		sqlstmt.append("'"+ "01/01/01"+"',");
 		sqlstmt.append("'"+ dateOut+"',");
 		sqlstmt.append("'"+ timeOut+"',");
 		sqlstmt.append("'"+ dateOut+"',");
 		sqlstmt.append("'"+ timeOut+"',");
 		sqlstmt.append("'"+ "NRP"+"',");
 		sqlstmt.append("'"+ chequeTO.getProceso()+"',");
 		sqlstmt.append("'"+ chequeTO.getDescripcionSeccion()+"'");
        		
        		
 		sqlstmt.append(")");
        stmt.addBatch(sqlstmt.toString());
		return 1;
	}
	
	public static int insertDetalle(PropuestaPagoTO chequeTO, int correlativo,Statement stmt) throws SQLException{
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yy");
        DateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
        Date today = new Date();
        String dateOut = dateFormatter.format(today);
        String timeOut = timeFormatter.format(today);
        
		StringBuffer sqlstmt = new StringBuffer();
        sqlstmt.append("INSERT INTO TEDTA.TE07F2");
        sqlstmt.append("(TE4QA,TE4SA,TE2XA,TE4UA,TE1YA,TE3WA,OBF002,OBF003,OBF005,OBF006,SAJKM94,SAJKM92)");
        sqlstmt.append("VALUES ");
        //System.out.println("Query=" + sqlstmt.toString());
        
        
        
        
        sqlstmt.append("(");
        
        sqlstmt.append("'"+  correlativo +"',");
        sqlstmt.append("'"+  chequeTO.getMontoTotal() +"',");
        sqlstmt.append("'"+  1 +"',");
        sqlstmt.append("'"+  0 +"',");
        sqlstmt.append("'"+  chequeTO.getConceptoTesoreria() +"',");
        sqlstmt.append("'"+  chequeTO.getFolioEgreso() +"',");
        sqlstmt.append("'"+  dateOut +"',");
        sqlstmt.append("'"+  timeOut +"',");
        sqlstmt.append("'"+  dateOut +"',");
        sqlstmt.append("'"+  timeOut +"',");
        sqlstmt.append("'"+  "NRP" +"',");
        sqlstmt.append("'"+  chequeTO.getProceso() +"'");
        		
 		sqlstmt.append(")");
        

        //System.out.println("query -> " + sqlstmt.toString());
        return 1;
	}
	
	
	public static boolean insertarComprobante(PropuestaPagoTO chequeTO,Statement stmt){
		boolean retorno = false;
		

		try {
			int resultado = new ChequeTesoreriaDAO().insert(chequeTO,stmt);
			resultado = new ChequeTesoreriaDAO().insertDetalle(chequeTO, resultado,stmt);
			//System.out.println("res: "+ resultado);
			retorno = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			UtilLogErrores.debug("Error generación comprobante para : ["+chequeTO.getFolioEgreso()+","+chequeTO.getRut()+","+chequeTO.getMontoTotal()+"]");
			
		}
		return retorno;
	}
	
	public static void main(String[] args) {
		
		PropuestaPagoTO chequeTO = new PropuestaPagoTO();
		
		/*
		 properties a setear
		 
		db2.setInt(1, chequeTO.getFolioEgreso());
        db2.setInt(9, chequeTO.getRut().getNumber());
        db2.setString(10, chequeTO.getRut().getDV()+"");
        db2.setString(11, chequeTO.getRazonSocial());
        db2.setLong(12, chequeTO.getMontoTotal());
        db2.setString(18, chequeTO.getOficinaPago());
        db2.setInt(5, chequeTO.getConceptoTesoreria());
      
        */
		
		
		//40097171	CORP. EDUC. ORLANDO VERA                	65145501	4	15204824	6	3093
		 chequeTO.setFolioEgreso(40097171);
		 chequeTO.setRut(new Rut(15204824));
		 chequeTO.setRazonSocial("CORP. EDUC. ORLANDO VERA  ");
		 chequeTO.setMontoTotal(3093);
		 chequeTO.setConceptoTesoreria(602);
		 chequeTO.setOficinaPago("0");
		 chequeTO.setDescripcionSeccion("NOMINAS DE CREDITO CON VENCIMIENTO 99/99/9999");
		
		
	}

}
