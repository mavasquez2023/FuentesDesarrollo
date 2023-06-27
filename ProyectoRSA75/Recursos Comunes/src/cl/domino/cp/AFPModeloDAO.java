package cl.domino.cp;

import java.sql.*;

import cl.recursos.ConectaDB2;

public class AFPModeloDAO{
	ConectaDB2 db2;
	
public  AFPModeloDAO(String sistema, String usuario, String password) {
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

public  AFPModeloDAO(ConectaDB2 db2) {
		this.db2= db2;	
}


//Inserta un registro de trabajador en DB2
public boolean isCotizanteVigente(int rutCotizante){
	StringBuffer query=new StringBuffer();
	boolean vigente=true;
		//CotizanteTO cotizante= (CotizanteTO)obj;
		try {
			//llenaDerecha(rutemp, Len);
			query.append("SELECT ESTADO FROM CPDOMDTA.CP01F1 ");
			query.append("WHERE RUTCOT= ? ");
			db2.prepareQuery(query.toString());
			db2.setStatement(1, rutCotizante);
			db2.executeQuery();
			if (db2.next()) {
				String estado= db2.getString(1);
				//String codigoAFP= db2.getString(2);
				//cotizante.setActivo(true);
				//cotizante.setCodigoAFP_Previred(codigoAFP);
			}else{
				vigente= false;
				//cotizante.setActivo(false);
			}
		} catch (SQLException e) {
			//System.out.println("Error al consultar trabajador " + cotizante.getRutCotizante() + ", Mensaje: " + e.getMessage());
			System.out.println("Error al consultar trabajador " + rutCotizante);
		}
		return vigente;
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
}
